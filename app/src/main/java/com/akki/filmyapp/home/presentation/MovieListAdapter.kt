package com.akki.filmyapp.home.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.akki.filmyapp.R
import com.akki.filmyapp.api.Constants
import com.akki.filmyapp.home.domain.model.MovieItem
import com.akki.filmyapp.imageloader.IImageLoader
import javax.inject.Inject


class MovieListAdapter @Inject constructor(val imageLoader: IImageLoader) :
    Adapter<MovieListAdapter.MovieViewHolder>() {

    private var list: List<MovieItem>? = null

    fun updateItem(list: List<MovieItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View =
            LayoutInflater.from(parent.getContext())
                .inflate(
                    R.layout.movie_item,
                    parent,
                    false
                )
        return MovieViewHolder(view, imageLoader)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        list?.get(position)?.let {
            holder.bindView(it)
        }

    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class MovieViewHolder(view: View, val imageLoader: IImageLoader) :
        RecyclerView.ViewHolder(view) {
        private var title: TextView? = view.findViewById(R.id.title)
        private var desc: TextView? = view.findViewById(R.id.desc)
        private var image: ImageView? = view.findViewById(R.id.image_movie)

        fun bindView(movieItem: MovieItem) {
            title?.text = movieItem.title
            desc?.text = movieItem.overview
            imageLoader.imageLoad(Constants.IMAGE_BASE_URL + movieItem.posterPath, image!!)
        }
    }

}