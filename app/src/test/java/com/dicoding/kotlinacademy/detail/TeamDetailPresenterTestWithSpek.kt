package com.dicoding.kotlinacademy.detail

import com.dicoding.kotlinacademy.TestContextProvider
import com.dicoding.kotlinacademy.api.ApiRepository
import com.dicoding.kotlinacademy.api.TheSportDBApi
import com.dicoding.kotlinacademy.model.TeamResponse
import com.dicoding.kotlinacademy.teams.TeamsPresenter
import com.dicoding.kotlinacademy.teams.TeamsView
import com.google.gson.Gson
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(JUnitPlatform::class)
class TeamDetailPresenterTestWithSpek: Spek({
    describe("testing teams detail presenter") {

        val gson = Mockito.mock(Gson::class.java)
        val view = Mockito.mock(TeamDetailView::class.java)
        val teamResponse = Mockito.mock(TeamResponse::class.java)
        val apiRepository = Mockito.mock(ApiRepository::class.java)

        val presenter = TeamDetailPresenter(view, apiRepository, gson, TestContextProvider())
        val id = "1234"

        on("get team detail") {
            Mockito.`when`(
                    gson.fromJson(
                            apiRepository.doRequest(TheSportDBApi.getTeamDetail(id)),
                            TeamResponse::class.java)).thenReturn(teamResponse)
            presenter.getTeamDetail(id)

            it("show loading") {
                Mockito.verify(view).showLoading()
            }
            it("success get team detail") {
                Mockito.verify(view).showTeamDetail(teamResponse.teams)
            }
            it("hide loading") {
                Mockito.verify(view).hideLoading()
            }

        }

    }

})