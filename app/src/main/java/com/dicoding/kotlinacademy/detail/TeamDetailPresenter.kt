package com.dicoding.kotlinacademy.detail

import com.dicoding.kotlinacademy.api.ApiRepository
import com.dicoding.kotlinacademy.api.TheSportDBApi
import com.dicoding.kotlinacademy.model.TeamResponse
import com.dicoding.kotlinacademy.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by root on 2/3/18.
 */
class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()

        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                        .doRequestAsync(TheSportDBApi.getTeamDetail(teamId)).await(),
                        TeamResponse::class.java)

            view.showTeamDetail(data.teams)
            view.hideLoading()
        }
    }
}