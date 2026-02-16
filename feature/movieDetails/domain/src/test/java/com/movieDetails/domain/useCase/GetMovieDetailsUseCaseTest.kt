package com.movieDetails.domain.useCase

import com.google.common.truth.Truth.assertThat
import com.movieDetails.domain.repository.MovieDetailsRepository
import com.nour.core.common.result.ResponseState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test


class GetMovieDetailsUseCaseTest {
    private val repo = mockk<MovieDetailsRepository>()
    private val useCase = GetMovieDetailsUseCase(repo)

    @Test
    fun `invoke calls repository`() = runTest {
        coEvery { repo.getMovieDetails(1) } returns ResponseState.Success(emptyFlow())

        val result = useCase(1)

        assertThat(result).isInstanceOf(ResponseState.Success::class.java)
        coVerify(exactly = 1) { repo.getMovieDetails(1) }
    }
}