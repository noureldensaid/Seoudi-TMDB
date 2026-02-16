package com.moviesList.data.repository

import com.google.common.truth.Truth.assertThat
import com.moviesList.data.mapper.MoviesPaginatedResponseToDomainMapper
import com.moviesList.data.remote.MoviesListRemoteDataSource
import com.moviesList.data.remote.dto.MoviesPaginatedResponse
import com.moviesList.domain.model.MoviesPage
import com.nour.core.common.error.NetworkError
import com.nour.core.common.result.ResponseState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MoviesListRepositoryImplTest {

    private val remote = mockk<MoviesListRemoteDataSource>()
    private val mapper = mockk<MoviesPaginatedResponseToDomainMapper>()

    private val repo = MoviesListRepositoryImpl(
        moviesListRemoteDataSource = remote,
        paginatedResponseToDomainMapper = mapper
    )

    @Test
    fun `getPopularMovies when remote error returns Error and does not map`() = runTest {
        val err = NetworkError.NO_INTERNET_CONNECTION
        coEvery { remote.getPopularMovies("1") } returns ResponseState.Error(err, null)

        val result = repo.getPopularMovies("1")

        assertThat(result).isInstanceOf(ResponseState.Error::class.java)
        coVerify(exactly = 0) { mapper.map(any()) }
    }

    @Test
    fun `getPopularMovies when remote success maps and returns Success`() = runTest {
        val response = mockk<MoviesPaginatedResponse>(relaxed = true)
        val mappedPage = MoviesPage(page = 1, movies = emptyList(), totalPages = 1, totalResults = 0)

        coEvery { remote.getPopularMovies("2") } returns ResponseState.Success(response)
        every { mapper.map(response) } returns mappedPage

        val result = repo.getPopularMovies("2")

        assertThat(result).isInstanceOf(ResponseState.Success::class.java)
        assertThat((result as ResponseState.Success).data).isEqualTo(mappedPage)

        coVerify(exactly = 1) { mapper.map(response) }
    }
}