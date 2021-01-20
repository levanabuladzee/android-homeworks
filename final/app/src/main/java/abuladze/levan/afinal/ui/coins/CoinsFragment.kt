package abuladze.levan.afinal.ui.coins

import abuladze.levan.afinal.R
import abuladze.levan.afinal.adapter.CoinsAdapter
import abuladze.levan.afinal.database.Coins
import abuladze.levan.afinal.database.CoinsDatabase
import abuladze.levan.afinal.ui.MainActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CoinsFragment : Fragment() {
    private lateinit var rootView: View
    private lateinit var syncTV : TextView
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var mActivity: MainActivity

    private val adapter = CoinsAdapter(object : CoinsAdapter.OnItemClickCallback {
        override fun onItemClick(symbol: String, id: String) {
            Toast.makeText(mActivity.ctx, symbol, Toast.LENGTH_SHORT).show()
        }

        override fun onFavouriteClick(symbol: String) {
            clickFavourite(symbol)
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_list, container, false)
        mActivity = activity as MainActivity

        syncTV = rootView.findViewById(R.id.syncedTextView)
        swipeLayout = rootView.findViewById(R.id.swiperefresh)

        val rvCoinsList = rootView.findViewById<RecyclerView>(R.id.rvCoins)
        rvCoinsList.layoutManager = LinearLayoutManager(this.activity)
        rvCoinsList.adapter = adapter

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeLayout.setOnRefreshListener {
            mActivity.coinGeckoClient.fetchCoinsList()
            swipeLayout.isRefreshing = false
        }
        mActivity.model.sync.observe(viewLifecycleOwner, {
            syncTV.text = it
        })
        mActivity.model.allCoinsList.observe(viewLifecycleOwner, {
            adapter.updateData(it as ArrayList<Coins>)
        })
        swipeLayout.setColorSchemeResources(R.color.color_accent)
    }

    private fun clickFavourite(symbol: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val dao = CoinsDatabase.getDatabase(mActivity.ctx).coinsDao()

            dao.findBySymbol(symbol)?.let {
                val coin = Coins(
                    it.symbol,
                    it.id,
                    it.name,
                    it.price,
                    it.changePercent,
                    it.image,
                    it.isFavourite.not()
                )

                dao.update(coin)

                mActivity.coinGeckoClient.fetchCoinsList(null, false)
            }
        }
    }
}