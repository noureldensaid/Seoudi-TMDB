package com.movieDetails.domain.useCase

import com.movieDetails.domain.repository.MovieDetailsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ToggleFavoriteUseCaseTest {

    private val repo = mockk<MovieDetailsRepository>()
    private val useCase = ToggleFavoriteUseCase(repo)

    @Test
    fun `invoke calls repository updateFavoriteState`() = runTest {
        coEvery { repo.updateFavoriteState(5, true) } returns Unit

        useCase(5, true)

        coVerify(exactly = 1) { repo.updateFavoriteState(5, true) }
    }
}