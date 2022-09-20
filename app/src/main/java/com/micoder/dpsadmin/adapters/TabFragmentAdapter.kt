package com.micoder.dpsadmin.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.micoder.dpsadmin.fragments.FirstTermFragment
import com.micoder.dpsadmin.fragments.FourthTermFragment
import com.micoder.dpsadmin.fragments.SecondTermFragment
import com.micoder.dpsadmin.fragments.ThirdTermFragment

@Suppress("DEPRECATION")
internal class TabFragmentAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
    ) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FirstTermFragment()
            }
            1 -> {
                SecondTermFragment()
            }
            2 -> {
                ThirdTermFragment()
            }
            3 -> {
                FourthTermFragment()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}