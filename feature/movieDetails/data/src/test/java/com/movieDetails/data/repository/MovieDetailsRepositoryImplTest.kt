package com.movieDetails.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.movieDetails.data.mapper.MovieDetailsDtoToEntityMapper
import com.movieDetails.data.mapper.MovieDetailsEntityToModelMapper
import com.movieDetails.data.remote.MovieDetailsRemoteDataSource
import com.movieDetails.data.remote.dto.MovieDetailsDto
import com.movieDetails.domain.model.MovieDetailsModel
import com.nour.core.common.result.ResponseState
import com.nour.core.database.dao.MovieDetailsDao
import com.nour.core.database.entity.MovieDetailsEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MovieDetailsRepositoryImplTest {

    private val remote = mockk<MovieDetailsRemoteDataSource>()
    private val dao = mockk<MovieDetailsDao>()
    private val dtoToEntity = mockk<MovieDetailsDtoToEntityMapper>()
    private val entityToModel = mockk<MovieDetailsEntityToModelMapper>()

    private val repo = MovieDetailsRepositoryImpl(
        remoteDataSource = remote,
        movieDetailsDao = dao,
        movieDetailsDtoToEntityMapper = dtoToEntity,
        movieDetailsEntityToModelMapper = entityToModel
    )

    @Test
    fun `getMovieDetails success preserves favorite from DB (db is source of truth)`() = runTest {
        val movieId = 7

        val dto = mockk<MovieDetailsDto>(relaxed = true)

        // incoming entity from mapper (favorite false because mapper sets false)
        val incomingEntity = mockk<MovieDetailsEntity>(relaxed = true)
        every { incomingEntity.id } returns movieId
        every { incomingEntity.isFavorite } returns false

        // entity observed from DB after upsertPreservingFavorite logic (favorite true)
        val dbEntity = mockk<MovieDetailsEntity>(relaxed = true)
        every { dbEntity.id } returns movieId
        every { dbEntity.isFavorite } returns true

        val model = mockk<MovieDetailsModel>(relaxed = true)

        coEvery { remote.getMovieDetails(movieId) } returns ResponseState.Success(dto)
        every { dtoToEntity.map(dto) } returns incomingEntity

        // repo should call this, not plain upsert
        coEvery { dao.upsertPreservingFavorite(incomingEntity) } returns Unit

        every { dao.observeMovieDetails(movieId) } returns flowOf(dbEntity)
        every { entityToModel.map(dbEntity) } returns model

        val result = repo.getMovieDetails(movieId)
        assertThat(result).isInstanceOf(ResponseState.Success::class.java)

        val flow = (result as ResponseState.Success).data

        flow.test {
            assertThat(awaitItem()).isEqualTo(model)
            cancelAndConsumeRemainingEvents()
        }

        coVerify(exactly = 1) { dao.upsertPreservingFavorite(incomingEntity) }
        coVerify(exactly = 1) { dao.observeMovieDetails(movieId) }
        coVerify(exactly = 0) { dao.upsert(any()) }
    }
}