package com.example.gumiproject8

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(val fragmentManager: FragmentManager, lifecycle: Lifecycle, val listFragment: MutableList<Fragment>): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = listFragment.size

    override fun createFragment(position: Int): Fragment = listFragment.get(position)
}