package br.com.fiap.watersmart
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.watersmart.screens.ResultScreen
import br.com.fiap.watersmart.screens.WaterConsumptionScreen
import br.com.fiap.watersmart.ui.theme.WaterSmartTheme




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaterSmartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController,
                        startDestination = "waterConsumption"){
                        composable(route = "waterConsumption"){
                            WaterConsumptionScreen(navController = navController)
                        }
                        composable("resultScreen/{recommendation}") { backStackEntry ->
                            val recommendation = backStackEntry.arguments?.getString("recommendation")?.toFloatOrNull() ?: 0f
                            ResultScreen(navController = navController, consumptionRecommendation = recommendation)
                        }

                    }
                }
            }
        }
    }
}

