package com.android4you.space.presentation.missions.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android4you.space.presentation.missions.LandingPadsFragment
import com.android4you.space.presentation.missions.LaunchPadsFragment

class PadsPagerAdapter(fragmentActivity: FragmentActivity, private var totalCount: Int) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return totalCount
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LaunchPadsFragment()
            1 -> LandingPadsFragment()
            else -> LaunchPadsFragment()
        }
    }
}
