package com.rinoindraw.githubyangke3

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rinoindraw.githubyangke3.DetailUserActivity.Companion.EXTRA_USERNAME
import com.rinoindraw.githubyangke3.databinding.FragmentFollowersBinding


class FragmentFollowers : Fragment(R.layout.fragment_followers) {

    private var _binding : FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var username: String
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragmentFollowersBinding.bind(view)


        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = adapter
        }



        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        viewModel.setListFollowers(username)
        viewModel.listFollowers.observe(viewLifecycleOwner) { followers ->
            followers?.let {
                binding.apply {
                    adapter.setList(it)
                    showLoading(false)
                }

            }
        }

    }



    private fun showLoading(state: Boolean){
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }




}