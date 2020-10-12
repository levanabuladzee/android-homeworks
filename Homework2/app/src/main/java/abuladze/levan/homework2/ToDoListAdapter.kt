package abuladze.levan.homework2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_to_do_list.view.*
import kotlinx.android.synthetic.main.item_to_do_list.view.cbCompleted
import kotlinx.android.synthetic.main.item_to_do_list.view.tvTitle

class ToDoListAdapter(val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = arrayListOf<ItemToDoList>()

    fun updateData(newData : ArrayList<ItemToDoList>) {
        data.clear()
        data.addAll(newData)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            NORMAL_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_to_do_list, parent, false)
                ToDoListViewHolder(view)
            }
            AD -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ad, parent, false)
                AdViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_to_do_list_title_only, parent, false)
                ToDoListTitleOnlyViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            getItemViewType(position) == NORMAL_TYPE -> {
                holder as ToDoListViewHolder
                holder.bind(data[position])
            }
            getItemViewType(position) == AD -> {
                holder as AdViewHolder
            }
            else -> {
                holder as ToDoListTitleOnlyViewHolder
                holder.bind(data[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            data[position].description == null -> {
                TITLE_ONLY
            }
            position % AD_POSITION == 0 && position != 0 -> {
                AD
            }
            else -> {
                NORMAL_TYPE
            }
        }
    }

    inner class ToDoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ItemToDoList) {
            itemView.tvTitle.text = item.title
            itemView.tvDescription.text = item.description
            itemView.cbCompleted.isChecked = item.isCompleted

            itemView.setOnClickListener() {
                listener.onItemClick(item)
            }

            itemView.cbCompleted.setOnCheckedChangeListener() { compoundButton: CompoundButton, b: Boolean ->
                if (compoundButton.isChecked) {
                    data[item.id.toInt()].isCompleted = true
                }
                if (!compoundButton.isChecked) {
                    data[item.id.toInt()].isCompleted = false
                }
            }
        }
    }

    inner class ToDoListTitleOnlyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ItemToDoList) {
            itemView.tvTitle.text = item.title
            itemView.cbCompleted.isChecked = item.isCompleted

            itemView.setOnClickListener() {
                listener.onItemClick(item)
            }

            itemView.cbCompleted.setOnCheckedChangeListener() { compoundButton: CompoundButton, b: Boolean ->
                if (compoundButton.isChecked) {
                    data[item.id.toInt()].isCompleted = true
                }
                if (!compoundButton.isChecked) {
                    data[item.id.toInt()].isCompleted = false
                }
            }
        }
    }

    inner class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    interface OnItemClickListener {
        fun onItemClick(item: ItemToDoList)
    }

    companion object {
        const val NORMAL_TYPE = 1
        const val TITLE_ONLY = 2
        const val AD = 3
        const val AD_POSITION = 6
    }
}