package com.dicoding.kotlinacademy.teams

import com.dicoding.kotlinacademy.model.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}