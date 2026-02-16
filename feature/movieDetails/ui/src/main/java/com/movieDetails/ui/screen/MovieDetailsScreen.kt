package com.movieDetails.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.movieDetails.ui.model.MovieDetailsScreenEvents
import com.movieDetails.ui.viewModel.MovieDetailsViewModel
import com.nour.core.common.result.ResponseState
import kotlinx.coroutines.flow.Flow

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel,
    isLoading: (show: Boolean) -> Unit,
    errorFlow: (error: Flow<ResponseState.Error>) -> Unit,
    onRetry: (() -> Unit) -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    isLoading(state.isLoading)

    errorFlow(viewModel.errorFlow)

    onRetry { viewModel.onEvent(MovieDetailsScreenEvents.GetMovieDetails) }

    MovieDetailsScreenRoot(
        state = state,
        onEvent = viewModel::onEvent,
        modifier = modifier
    )
}