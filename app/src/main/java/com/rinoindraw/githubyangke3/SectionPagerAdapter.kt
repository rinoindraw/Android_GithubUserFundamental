package com.rinoindraw.githubyangke3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(activity: FragmentActivity, data: Bundle) : FragmentStateAdapter(activity) {

    private var  fragmentBundle: Bundle

    init {
        fragmentBundle = data
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FragmentFollowers()
            1 -> fragment = FragmentFollowing()
        }
        return fragment as Fragment
    }

//    var username: String = ""
//    override fun createFragment(position: Int): Fragment {
//        val fragment = FragmentFollowers()
//        fragment.arguments = Bundle().apply {
//            putInt(FragmentFollowers.ARG_POSITION, position + 1)
//            putString(FragmentFollowers.ARG_USERNAME, username)
//        }
//        return fragment
//    }
//    override fun getItemCount(): Int {
//        return 2
//    }

}