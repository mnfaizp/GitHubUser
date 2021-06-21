package com.example.githubuser.detail


import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.core.data.Resource
import com.example.githubuser.core.domain.model.User
import com.example.githubuser.databinding.ActivityDetailUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    private val detailUserViewModel: DetailUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailUser = intent.getParcelableExtra<User>(EXTRA_DATA)

        if (detailUser != null) {

            detailUserViewModel.getUser(detailUser).observe(this, { user ->
                if (user != null) {
                    when (user) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> showDetailUser(user.data)
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                user.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })
        }

    }

    private fun showDetailUser(detailUser: User?) {
        detailUser?.let { user ->

            with(binding) {
                tvDetailLogin.text = user.login
                tvDetailUrl.text = user.url
                tvDetailName.text = user.userDetail?.name
                tvDetailFollowers.text = user.userDetail?.follower.toString()
                tvDetailFollowing.text = user.userDetail?.following.toString()
                tvDetailRepository.text = user.userDetail?.repository.toString()
            }

            Glide.with(this)
                .load(user.avatarUrl)
                .into(binding.imgDetailAvatar)


            var statusFavorite = user.isFavortie.apply {
                setStatusFavorite(this)
            }

            binding.fabFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailUserViewModel.setFavoriteUser(
                    user,
                    statusFavorite
                )
                setStatusFavorite(statusFavorite)
            }

        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_not_favorite_border_24
                )
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}