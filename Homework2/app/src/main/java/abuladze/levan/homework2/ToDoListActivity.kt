package abuladze.levan.homework2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_to_do_list.*

class ToDoListActivity : AppCompatActivity() {
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_list)

        mContext = this

        val data = ToDoListData.data

        val adapter = ToDoListAdapter(object: ToDoListAdapter.OnItemClickListener {
            override fun onItemClick(item: ItemToDoList) {
                val intent = Intent(mContext, ItemDetailsActivity::class.java)
                intent.putExtra("title", item.title)
                intent.putExtra("description", item.description)
                intent.putExtra("isCompleted", item.isCompleted)
                startActivity(intent)
            }
        })

        val dividerDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        rvToDoList.layoutManager = LinearLayoutManager(this)
        rvToDoList.adapter = adapter
        rvToDoList.addItemDecoration(dividerDecoration)

        adapter.updateData(data)
    }
}