package com.kai.filmreviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListFilmAdapter(private val listFilm: ArrayList<Film>) : RecyclerView.Adapter<ListFilmAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_film, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, synopsis, posterImage, backdropImage, releaseDate, voteAverage, voteCount,
        originalLanguage) = listFilm[position]

        Glide.with(holder.itemView.context)
            .load(posterImage) // URL Gambar
            .into(holder.imgPhotoPoster) // imageView mana yang akan diterapkan

        holder.tvName.text = name

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listFilm[holder.adapterPosition])
        }
//        holder.itemView.setOnClickListener {
//            Toast.makeText(holder.itemView.context, "Kamu memilih " + listFilm[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()
//        }

    }

    override fun getItemCount(): Int = listFilm.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhotoPoster: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Film)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


}