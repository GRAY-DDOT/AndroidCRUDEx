package com.example.userapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.userapp.ui.screen.UserListScreen
import com.example.userapp.ui.screens.UserDetailScreen
import com.example.userapp.ui.theme.UserAppTheme
import com.example.userapp.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge() // 전체 화면 만들어주는 것
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "userList") {
                composable(
                    route= "userList"
                ) {
                    UserListScreen(navController = navController, viewModel = userViewModel)
                }
                composable(
                    route = "userDetail/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.StringType })
                    ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getLong("userId") ?: 0L
                    UserDetailScreen(viewModel = userViewModel, userId = userId, navController = navController)
                }
            }
        }
    }
}
