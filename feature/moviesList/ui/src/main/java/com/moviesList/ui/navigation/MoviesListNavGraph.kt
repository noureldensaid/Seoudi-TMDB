package com.moviesList.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moviesList.ui.screen.MoviesListScreen
import com.moviesList.ui.viewmodel.MoviesListViewModel
import com.nour.core.common.result.ResponseState
import com.nour.core.ui.navigation.Route
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object MoviesListRoute : Route

fun NavGraphBuilder.moviesListNavGraph(
    isLoading: (show: Boolean) -> Unit,
    errorFlow: (error: Flow<ResponseState.Error>) -> Unit,
    onRetry: (() -> Unit) -> Unit,
) {
    composable<MoviesListRoute> { backStackEntry ->

        val viewModel: MoviesListViewModel = koinViewModel(viewModelStoreOwner = backStackEntry)

        MoviesListScreen(
            viewModel = viewModel,
            isLoading = isLoading,
            errorFlow = errorFlow,
            onRetry = onRetry
        )
    }
}