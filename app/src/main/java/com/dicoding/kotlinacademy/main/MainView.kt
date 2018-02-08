package com.dicoding.kotlinacademy.main

import com.dicoding.kotlinacademy.model.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}