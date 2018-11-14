package com.dicoding.kotlinacademy.teams

import com.dicoding.kotlinacademy.api.ApiRepository
import com.dicoding.kotlinacademy.api.TheSportDBApi
import com.dicoding.kotlinacademy.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson) {

    fun getTeamList(league: String?) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(league)).await(),
                    TeamResponse::class.java
            )
            view.showTeamList(data.teams)
            view.hideLoading()
        }
    }

}