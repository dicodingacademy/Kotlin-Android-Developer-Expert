package com.dicoding.kotlinacademy.detail

import com.dicoding.kotlinacademy.model.Team

/**
 * Created by root on 2/3/18.
 */

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}