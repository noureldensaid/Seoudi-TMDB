package com.nour.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.nour.core.common.result.ResponseState
import com.nour.core.ui.navigation.Route
import com.nour.ui.navigation.MoviesListRoute
import com.nour.ui.navigation.moviesListNavGraph
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

@Serializable
object MainFlow : Route

internal fun NavGraphBuilder.mainFlowNavigation(
    navController: NavHostController,
    isLoading: (show: Boolean) -> Unit,
    errorFlow: (error: Flow<ResponseState.Error>) -> Unit,
) {
    navigation<MainFlow>(MoviesListRoute) {

        moviesListNavGraph(
            navController = navController,
            isLoading = isLoading,
            errorFlow = errorFlow,
        )
    }
}