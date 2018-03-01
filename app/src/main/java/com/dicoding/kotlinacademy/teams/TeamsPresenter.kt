package com.dicoding.kotlinacademy.teams

import com.dicoding.kotlinacademy.api.ApiRepository
import com.dicoding.kotlinacademy.api.TheSportDBApi
import com.dicoding.kotlinacademy.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import kotlin.coroutines.experimental.CoroutineContext

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamList(league: String?) {
        view.showLoading()

        async(contextPool.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeams(league)),
                        TeamResponse::class.java
                )
            }
            view.showTeamList(data.await().teams)
            view.hideLoading()
        }
    }

}

open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { UI }
}