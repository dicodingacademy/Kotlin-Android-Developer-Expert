package com.dicoding.kotlinacademy.teams

import com.dicoding.kotlinacademy.api.ApiRepository
import com.dicoding.kotlinacademy.api.TheSportDBApi
import com.dicoding.kotlinacademy.model.Team
import com.dicoding.kotlinacademy.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.Unconfined
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by root on 2/28/18.
 */
class TeamsPresenterTest {
    @Mock
    private
    lateinit var view: TeamsView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: TeamsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamsPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getTeamList() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val league = "English Premiere League"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(league)),
                TeamResponse::class.java
        )).thenReturn(response)

        presenter.getTeamList(league)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }

}

class TestContextProvider : CoroutineContextProvider() {
    override val main: CoroutineContext = Unconfined
}