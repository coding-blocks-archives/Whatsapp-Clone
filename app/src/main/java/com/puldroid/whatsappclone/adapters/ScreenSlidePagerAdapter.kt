package com.puldroid.whatsappclone.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.puldroid.whatsappclone.fragments.ChatsFragment
import com.puldroid.whatsappclone.fragments.PeopleFragment

class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val NUM_PAGES: Int = 2

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment = when(position){
        0 -> ChatsFragment()
        else -> PeopleFragment()
    }
}