package com.rinoindraw.githubyangke3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.rinoindraw.githubyangke3.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {
    private var _binding: ActivityDetailUserBinding? = null
    private val binding get() = _binding!!
    private val userDetailViewModel by viewModels<DetailUserViewModel>()


    companion object {
        const val EXTRA_USER = "extra_user"
        const val EXTRA_FRAGMENT = "extra_fragment"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_followers,
            R.string.tab_following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showLoading(true)

        userDetailViewModel.listDetail.observe(this, { detailList ->
            setDataToView(detailList)
            showLoading(false)
        })

        setTabLayoutView()

    }

    private fun setTabLayoutView() {
        val userIntent = intent.getParcelableExtra<GithubUser>(EXTRA_USER) as GithubUser
        userDetailViewModel.setUserDetail(userIntent.login)

        val login = Bundle()
        login.putString(EXTRA_FRAGMENT, userIntent.login)

        val sectionPagerAdapter = SectionPagerAdapter(this, login)
        val viewPager: ViewPager2 = binding.viewPager

        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun setDataToView(detailList: DetailResponse) {
        binding.apply {
            Glide.with(this@DetailUserActivity)
                .load(detailList.avatarUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageProfile)
            textName.text = detailList.name ?: "No Name."
            textUsername.text = detailList.login ?: "No Username."
            textFollowers.text = resources.getString(R.string.follower, detailList.followers)
            textFollowing.text = resources.getString(R.string.following, detailList.following)
            textRepository.text = resources.getString(R.string.repository, detailList.publicRepos)
            textWebsite.text = if (detailList.blog == "") "No website/blog." else detailList.blog
        }
    }

    private fun showLoading(state: Boolean){
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

}