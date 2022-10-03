package com.android4you.space.presentation.launches.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android4you.space.presentation.launches.LaunchUpcomingFragment
import com.android4you.space.presentation.launches.LaunchesAllFragment
import com.android4you.space.presentation.launches.LaunchesPastFragment

class LaunchesPagerAdapter(fragmentActivity: FragmentActivity, private var totalCount: Int) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return totalCount
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LaunchesAllFragment()
            1 -> LaunchesPastFragment()
            2 -> LaunchUpcomingFragment()
            else -> LaunchesAllFragment()
        }
    }
}
