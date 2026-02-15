package com.nour.ui.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.nour.core.common.result.ResponseState
import com.nour.core.ui.navigation.Route
import com.nour.ui.screen.MoviesListScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

@Serializable
data object MoviesListRoute : Route

fun NavGraphBuilder.moviesListNavGraph(
    navController: NavHostController,
    isLoading: (show: Boolean) -> Unit,
    errorFlow: (error: Flow<ResponseState.Error>) -> Unit,
) {
    composable<MoviesListRoute> { backStackEntry ->

        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(navController.graph.id)
        }
        // val viewModel: TablesViewModel = koinViewModel(viewModelStoreOwner = parentEntry)

        MoviesListScreen(
//            viewModel = viewModel,
//            isLoading = isLoading,
//            errorFlow = errorFlow,
        )
    }
}