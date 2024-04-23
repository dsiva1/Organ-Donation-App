package com.organdonation.ui.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.organdonation.R
import com.organdonation.routing.Screen
import com.organdonation.ui.organ_preference.OrganPreference
import com.organdonation.ui.theme.OrganDonationAppTheme
import com.organdonation.ui.theme.appColor
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    val preference = remember {
        OrganPreference(context)
    }
    LaunchedEffect(Unit) {
        delay(3.seconds)
        if(preference.getData("isLogin")) {
            navController.navigate(Screen.MainScreen.route) {
                popUpTo(Screen.SplashScreen.route) {
                    inclusive = true
                }
            }
        }else{
            navController.navigate(Screen.LoginScreen.route) {
                popUpTo(Screen.SplashScreen.route) {
                    inclusive = true
                }
            }
        }

    }
    OrganDonationAppTheme {
        Scaffold {
            Column(
                modifier = Modifier.fillMaxSize().background(appColor).padding(10.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(bottom = 20.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        contentAlignment = Alignment.Center
                    ) {
                        Card(
                            modifier = Modifier.width(240.dp).height(240.dp),
                            shape = RoundedCornerShape(120.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(5.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_donor),
                                contentDescription = "",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}