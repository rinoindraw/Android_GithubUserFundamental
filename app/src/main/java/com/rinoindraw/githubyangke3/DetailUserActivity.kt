package com.rinoindraw.githubyangke3

import android.content.Intent
import android.content.Intent.EXTRA_USER
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.rinoindraw.githubyangke3.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    private lateinit var adapter: UserAdapter
//    private lateinit var data: ItemsItem

    companion object{
        const val ARG_USERNAME = "username_argument"
        const val EXTRA_USERNAME = "extra_username"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_followers,
            R.string.tab_following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        val bundle = Bundle()
        bundle.putString("EXTRA_USERNAME", username)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)

        viewModel.setUserDetail(username!!)
        viewModel.getUserDetail().observe(this) {
            if (it != null) {
                binding.apply {
                    textProfile.text = it.name
                    textUsername.text = it.login
                    textFollowers.text = "${it.followers} Followers"
                    textFollowing.text = "${it.following} Following"
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatarUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(imageProfile)
                }
            }
        }

        setTabLayout()

    }



    private fun setTabLayout() {
        val sectionsPagerAdapter = SectionPagerAdapter(this, Bundle())
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

}