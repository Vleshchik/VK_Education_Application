package com.example.vk_education_application.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vk_education_application.feature.appdetails.presentation.AppDetailsScreen
import com.example.vk_education_application.feature.applist.presentation.AppListScreen

/**
 * Корневой навигационный граф приложения.
 *
 * Регистрирует экран списка приложений ([AppListScreen]) как стартовый
 * и экран карточки ([AppDetailsScreen]) с целочисленным аргументом `appId`.
 *
 * @param navController контроллер навигации; по умолчанию создаётся заново.
 * @param modifier модификатор `NavHost`.
 */
@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.APP_LIST,
        modifier = modifier,
    ) {
        // Экран списка приложений
        composable(AppRoutes.APP_LIST) {
            AppListScreen(
                onAppClick = { appId ->
                    navController.navigate(AppRoutes.appDetails(appId))
                },
            )
        }

        // Экран карточки приложения
        composable(
            route = AppRoutes.APP_DETAILS_ROUTE,
            arguments = listOf(
                navArgument(AppRoutes.ARG_APP_ID) { type = NavType.StringType },
            ),
        ) { backStackEntry ->
            val appId = backStackEntry.arguments?.getString(AppRoutes.ARG_APP_ID) ?: return@composable

            AppDetailsScreen(
                appId = appId,
                onBackClick = { navController.popBackStack() },
                onShowcaseAppClick = { newAppId ->
                    navController.navigate(AppRoutes.appDetails(newAppId))
                },
            )
        }
    }
}