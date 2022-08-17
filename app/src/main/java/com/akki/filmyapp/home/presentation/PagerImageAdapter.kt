package com.akki.filmyapp.home.presentation

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.akki.filmyapp.R
import com.akki.filmyapp.api.Constants
import com.akki.filmyapp.home.domain.model.MovieItem
import com.akki.filmyapp.imageloader.IImageLoader
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class PagerImageAdapter @Inject constructor(context: Context) :
    PagerAdapter() {

    private var mLayoutInflater: LayoutInflater? = null
    private var movieList: List<MovieItem> = listOf()

    private val utilitiesEntryPoint =
        EntryPointAccessors.fromActivity(context as Activity, IUtilityPagerAdapter::class.java)
    val imageLoader: IImageLoader = utilitiesEntryPoint.imageLoader

    init {
        mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    fun updateItems(list: List<MovieItem>) {
        this.movieList = list
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return movieList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = mLayoutInflater?.inflate(R.layout.pager_image_item, container, false)!!
        val imageView: ImageView = itemView.findViewById<View>(R.id.imageViewMain) as ImageView
        imageLoader.imageLoad(Constants.IMAGE_BASE_URL + movieList[position].posterPath, imageView)
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}