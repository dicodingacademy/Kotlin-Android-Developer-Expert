package com.dicoding.kotlinacademy.teams

import com.dicoding.kotlinacademy.TestContextProvider
import com.dicoding.kotlinacademy.api.ApiRepository
import com.dicoding.kotlinacademy.model.Team
import com.dicoding.kotlinacademy.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by root on 2/28/18.
 */
class TeamsPresenterTest {
    @Mock
    private lateinit var view: TeamsView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: TeamsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamsPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamList() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val league = "English Premiere League"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                    .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                    gson.fromJson(
                            "",
                            TeamResponse::class.java
                    )
            ).thenReturn(response)

            presenter.getTeamList(league)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}
