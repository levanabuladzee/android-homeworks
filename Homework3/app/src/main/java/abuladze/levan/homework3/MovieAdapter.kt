package abuladze.levan.homework3

import abuladze.levan.homework3.model.ItemMovie
import android.graphics.Color
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = arrayListOf<ItemMovie>()

    fun updateData(newData : ArrayList<ItemMovie>) {
        data.clear()
        data.addAll(newData)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            NORMAL_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
                MovieViewHolder(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
                TopRatedMovieViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            getItemViewType(position) == NORMAL_TYPE -> {
                val movie = data[position]
                holder as MovieViewHolder
                holder.bind(data[position])
                Glide.with(holder.itemView.context).load(movie.image.medium.replace("http", "https")).into(holder.itemView.tvPoster)
            }
            getItemViewType(position) == TOP_RATED -> {
                val movie = data[position]
                holder as TopRatedMovieViewHolder
                holder.bind(data[position])
                Glide.with(holder.itemView.context).load(movie.image.medium.replace("http", "https")).into(holder.itemView.tvPoster)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            data[position].rating.average <= 9.0 -> {
                NORMAL_TYPE
            }
            else -> {
                TOP_RATED
            }
        }
    }

    inner class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item : ItemMovie) {
            itemView.tvName.text = item.name
            itemView.tvSummary.text = Html.fromHtml(item.summary).toString()
            itemView.tvRating.text = item.rating.average.toString()
            itemView.tvGenre.text = item.genres[0]

            itemView.setOnClickListener() {
                listener.onItemClick(item)
            }
        }
    }

    inner class TopRatedMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item : ItemMovie) {
            itemView.tvName.text = item.name
            itemView.tvSummary.text = Html.fromHtml(item.summary).toString()
            itemView.tvRating.text = item.rating.average.toString()
            itemView.tvGenre.text = item.genres[0]
            itemView.tvRating.setTextColor(Color.parseColor("#f1c40f"))

            itemView.setOnClickListener() {
                listener.onItemClick(item)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: ItemMovie)
    }

    companion object {
        const val NORMAL_TYPE = 1
        const val TOP_RATED = 2
    }
}