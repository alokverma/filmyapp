package com.akki.filmyapp.home.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.akki.filmyapp.home.domain.model.MovieTabs
import javax.inject.Inject

class MoviePagerAdapter @Inject constructor(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var tabItem: List<MovieTabs> = emptyList()

    override fun getCount(): Int {
        return tabItem.size
    }

    fun updateItem(list: List<MovieTabs>) {
        this.tabItem = list
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return MovieListFragment()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabItem[position].title
    }

}