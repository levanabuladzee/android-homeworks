package abuladze.levan.homework4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val arrayList = listOf<Int>(1, 2, 3, 4)

    inner class OddViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class EvenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ODD -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_1, parent, false)
                OddViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_2, parent, false)
                EvenViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            getItemViewType(position) == ODD -> {
                holder as OddViewHolder
                holder.itemView.findViewById<TextView>(R.id.tvTextView).text = arrayList[position].toString()
            }
            else -> {
                holder as EvenViewHolder
                holder.itemView.findViewById<TextView>(R.id.tvTextView).text = arrayList[position].toString()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            arrayList[position] % 2 == 0 -> {
                EVEN
            }
            else -> {
                ODD
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    companion object {
        const val ODD = 1
        const val EVEN = 2
    }
}