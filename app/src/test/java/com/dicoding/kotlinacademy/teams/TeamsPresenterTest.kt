package com.dicoding.kotlinacademy.teams

import com.dicoding.kotlinacademy.TestContextProvider
import com.dicoding.kotlinacademy.api.ApiRepository
import com.dicoding.kotlinacademy.api.TheSportDBApi
import com.dicoding.kotlinacademy.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito

/**
 * Created by root on 2/28/18.
 */

@RunWith(JUnitPlatform::class)
class TeamsPresenterTest : Spek({

    describe("test get team list") {

        val gson = Mockito.mock(Gson::class.java)
        val view = Mockito.mock(TeamsView::class.java)
        val teamResponse = Mockito.mock(TeamResponse::class.java)
        val apiRepository = Mockito.mock(ApiRepository::class.java)
        val presenter = TeamsPresenter(view, apiRepository, gson, TestContextProvider())
        val league = "Spanish La liga"

        on("get team list") {
            Mockito.`when`(
                    gson.fromJson(
                            apiRepository.doRequest(TheSportDBApi.getTeams(league)),
                            TeamResponse::class.java)).thenReturn(teamResponse)
            presenter.getTeamList(league)

            it("show loading") {
                Mockito.verify(view).showLoading()
            }
            it("success get team list") {
                Mockito.verify(view).showTeamList(teamResponse.teams)
            }
            it("hide loading") {
                Mockito.verify(view).hideLoading()
            }
        }
    }
})
