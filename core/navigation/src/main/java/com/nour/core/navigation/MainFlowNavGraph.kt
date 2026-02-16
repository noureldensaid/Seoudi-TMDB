package com.nour.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.movieDetails.ui.navigation.movieDetailsNavGraph
import com.moviesList.ui.navigation.MoviesListRoute
import com.moviesList.ui.navigation.moviesListNavGraph
import com.nour.core.common.result.ResponseState
import com.nour.core.ui.navigation.Route
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

@Serializable
object MainFlow : Route

internal fun NavGraphBuilder.mainFlowNavigation(
    isLoading: (show: Boolean) -> Unit,
    errorFlow: (error: Flow<ResponseState.Error>) -> Unit,
    onRetry: (() -> Unit) -> Unit,
) {
    navigation<MainFlow>(MoviesListRoute) {

        moviesListNavGraph(
            isLoading = isLoading,
            errorFlow = errorFlow,
            onRetry = onRetry
        )

        movieDetailsNavGraph(
            isLoading = isLoading,
            errorFlow = errorFlow,
            onRetry = onRetry
        )
    }
}