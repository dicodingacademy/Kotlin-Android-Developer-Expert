package com.dicoding.kotlinacademy.detail

import com.dicoding.kotlinacademy.TestContextProvider
import com.dicoding.kotlinacademy.api.ApiRepository
import com.dicoding.kotlinacademy.api.TheSportDBApi
import com.dicoding.kotlinacademy.model.Team
import com.dicoding.kotlinacademy.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by root on 3/1/18.
 */
class TeamDetailPresenterTest {
    @Mock
    private
    lateinit var view: TeamDetailView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: TeamDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamDetail() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val id = "1234"

       GlobalScope.launch {
           Mockito.`when`(gson.fromJson(apiRepository
                   .doRequest(TheSportDBApi.getTeamDetail(id)).await(),
                   TeamResponse::class.java
           )).thenReturn(response)

           presenter.getTeamDetail(id)

           Mockito.verify(view).showLoading()
           Mockito.verify(view).showTeamDetail(teams)
           Mockito.verify(view).hideLoading()
       }
    }

}