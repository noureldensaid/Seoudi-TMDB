package com.moviesList.domain.useCase

import com.google.common.truth.Truth.assertThat
import com.moviesList.domain.repository.MoviesListRepository
import com.nour.core.common.result.ResponseState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetPopularMoviesUseCaseTest {

    private val repo = mockk<MoviesListRepository>()
    private val useCase = GetPopularMoviesUseCase(repo)

    @Test
    fun `invoke calls repository getPopularMovies`() = runTest {
        val page = "1"
        coEvery { repo.getPopularMovies(page) } returns ResponseState.Success(mockk(relaxed = true))

        val result = useCase(page)

        assertThat(result).isInstanceOf(ResponseState.Success::class.java)
        coVerify(exactly = 1) { repo.getPopularMovies(page) }
    }
}