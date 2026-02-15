package com.nour.tmdb.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.nour.core.common.result.ResponseState
import com.nour.core.common.util.ObserveAsEvents
import com.nour.core.common.util.SnackbarAction
import com.nour.core.common.util.SnackbarController
import com.nour.core.navigation.AppNavGraph
import com.nour.core.ui.components.loading.DefaultLoadingComponent
import com.nour.core.ui.components.snackBar.DefaultSnackbar
import com.nour.core.ui.theme.AppTheme
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    var isOfflineMode: Boolean? by rememberSaveable { mutableStateOf(null) }

    var isLoading by rememberSaveable { mutableStateOf(false) }

    var errorFlow by remember { mutableStateOf(flowOf<ResponseState.Error>()) }

    val snackbarHostState = remember { SnackbarHostState() }

    val navController = rememberNavController()

//    ObserveAsEvents(flow = errorFlow) { error ->
//        val errorMessage =
//            if (error.error == NetworkError.NO_INTERNET_CONNECTION) context.getString(
//                R.string.you_re_offline
//            ) else error.errorBody?.message
//        when (error.error) {
//            else -> scope.launch {
//                SnackbarController.sendEvent(
//                    event = SnackbarAction.SendEvent(
//                        name = errorMessage ?: error.error.toString(),
//                        label = context.getString(R.string.ok)
//                    )
//                )
//            }
//        }
//    }

    ObserveAsEvents(
        flow = SnackbarController.events,
        snackbarHostState
    ) { event ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            when (event) {
                is SnackbarAction.Dismiss -> snackbarHostState.currentSnackbarData?.dismiss()
                is SnackbarAction.SendEvent -> {
                    snackbarHostState.showSnackbar(
                        message = event.name,
                        actionLabel = event.label,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    AppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            snackbarHost = {
                SnackbarHost(
                    modifier = Modifier.padding(16.dp),
                    hostState = snackbarHostState
                ) {
                    DefaultSnackbar(
                        message = it.visuals.message,
                        actionText = it.visuals.actionLabel
                    ) {
                        scope.launch {
                            SnackbarController.sendEvent(
                                event = SnackbarAction.Dismiss
                            )
                        }
                    }
                }
            },
            content = { _ ->
                Box(Modifier.fillMaxSize()) {
                    AppNavGraph(
                        modifier = Modifier.fillMaxSize(),
                        navController = navController,
                        isLoading = { isLoading = it },
                        errorFlow = { errorFlow = it },
                    )
                    DefaultLoadingComponent(isLoading)
                }
            },
        )
    }
}