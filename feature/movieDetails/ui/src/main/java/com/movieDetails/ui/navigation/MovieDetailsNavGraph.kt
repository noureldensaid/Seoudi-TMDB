package com.movieDetails.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.movieDetails.ui.screen.MovieDetailsScreen
import com.movieDetails.ui.viewModel.MovieDetailsViewModel
import com.nour.core.common.result.ResponseState
import com.nour.core.ui.navigation.Route
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data class MovieDetailsRoute(val id: Int) : Route

fun NavGraphBuilder.movieDetailsNavGraph(
    isLoading: (show: Boolean) -> Unit,
    errorFlow: (error: Flow<ResponseState.Error>) -> Unit,
    onRetry: (() -> Unit) -> Unit,
) {
    composable<MovieDetailsRoute> { backStackEntry ->

    val viewModel: MovieDetailsViewModel = koinViewModel(viewModelStoreOwner = backStackEntry)

        MovieDetailsScreen(
            viewModel = viewModel,
            isLoading = isLoading,
            errorFlow = errorFlow,
            onRetry = onRetry
        )
    }
}