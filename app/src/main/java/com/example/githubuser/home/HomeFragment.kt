package com.example.githubuser.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.example.githubuser.core.ui.UserAdapter
import com.example.githubuser.databinding.FragmentHomeBinding
import com.example.githubuser.detail.DetailUserActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val userAdapter = UserAdapter()
            userAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.users.observe(viewLifecycleOwner, { users ->
                if (users != null) {
                    when (users) {
                        is com.example.githubuser.core.data.Resource.Loading -> binding.progressBar.visibility =
                            View.VISIBLE
                        is com.example.githubuser.core.data.Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            userAdapter.setData(users.data)
                        }
                        is com.example.githubuser.core.data.Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                users.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvUsers) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = userAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}