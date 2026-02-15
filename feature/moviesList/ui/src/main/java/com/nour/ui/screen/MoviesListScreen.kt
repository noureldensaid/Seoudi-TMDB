package com.nour.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nour.core.common.result.ResponseState
import com.nour.ui.model.MoviesListScreenEvents
import com.nour.ui.viewmodel.MoviesListViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun MoviesListScreen(
    viewModel: MoviesListViewModel,
    isLoading: (show: Boolean) -> Unit,
    errorFlow: (error: Flow<ResponseState.Error>) -> Unit,
    onRetry: (() -> Unit) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    isLoading(state.isLoading)

    errorFlow(viewModel.errorFlow)

    onRetry {
        viewModel.onEvent(MoviesListScreenEvents.OnRetry)
    }

    MoviesListScreenRoot(
        state = state,
        onEvent = viewModel::onEvent,
        modifier = modifier
    )
}