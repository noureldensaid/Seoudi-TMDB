package com.moviesList.ui.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
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
    navController: NavHostController,
    isLoading: (show: Boolean) -> Unit,
    errorFlow: (error: Flow<ResponseState.Error>) -> Unit,
    onRetry: (() -> Unit) -> Unit,
) {
    composable<MoviesListRoute> { backStackEntry ->

        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(navController.graph.id)
        }

        val viewModel: MoviesListViewModel = koinViewModel(viewModelStoreOwner = parentEntry)

        MoviesListScreen(
            viewModel = viewModel,
            isLoading = isLoading,
            errorFlow = errorFlow,
            onRetry = onRetry
        )
    }
}