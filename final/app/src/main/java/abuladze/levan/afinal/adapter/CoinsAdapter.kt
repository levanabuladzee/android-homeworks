package abuladze.levan.afinal.adapter

import abuladze.levan.afinal.R
import abuladze.levan.afinal.database.Coins
import abuladze.levan.afinal.util.ImageLoader
import abuladze.levan.afinal.util.UIHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

class CoinsAdapter(private val onItemClickCallback: OnItemClickCallback) :
        RecyclerView.Adapter<CoinsAdapter.CoinsViewHolder>() {

    private val coins: ArrayList<Coins> = arrayListOf()

    fun updateData(coinsList : ArrayList<Coins>) {
        this.coins.clear()
        this.coins.addAll(coinsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder = CoinsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_coins_list, parent, false)
    )

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) = holder.bind(coins[position], onItemClickCallback)

    override fun getItemCount(): Int = coins.size

    class CoinsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: Coins, onItemClickCallback: OnItemClickCallback) {
            itemView.findViewById<TextView>(R.id.coinsItemSymbolTextView).text = model.symbol
            itemView.findViewById<TextView>(R.id.coinsItemNameTextView).text = model.name
            itemView.findViewById<TextView>(R.id.coinsItemPriceTextView).text = "${DecimalFormat("#,##0.00").format(model.price)}\$"
            UIHelper.showChangePercent(itemView.findViewById(R.id.coinsItemChangeTextView), model.changePercent)
            ImageLoader.loadImage(itemView.findViewById(R.id.coinsItemImageView), model.image ?: "")

            itemView.findViewById<ImageView>(R.id.favouriteImageView).setImageResource(
                if (model.isFavourite) R.drawable.ic_baseline_favorite_24
                else R.drawable.ic_baseline_favorite_border_24
            )

            itemView.findViewById<ImageView>(R.id.favouriteImageView).setOnClickListener {
                onItemClickCallback.onFavouriteClick(model.symbol)
            }

            itemView.setOnClickListener {
                onItemClickCallback.onItemClick(
                    model.symbol,
                    model.id ?: model.symbol
                )
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClick(symbol: String, id: String)
        fun onFavouriteClick(symbol: String)
    }

}