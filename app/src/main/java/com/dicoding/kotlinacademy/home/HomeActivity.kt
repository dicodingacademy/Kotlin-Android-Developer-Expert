package com.dicoding.kotlinacademy.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.kotlinacademy.R
import com.dicoding.kotlinacademy.R.id.favorites
import com.dicoding.kotlinacademy.R.id.teams
import com.dicoding.kotlinacademy.R.layout.activity_home
import com.dicoding.kotlinacademy.favorites.FavoriteTeamsFragment
import com.dicoding.kotlinacademy.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                teams -> {
                    loadTeamsFragment(savedInstanceState)
                }
                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = teams
    }


    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamsFragment(), TeamsFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteTeamsFragment(), FavoriteTeamsFragment::class.simpleName)
                    .commit()
        }
    }
}
