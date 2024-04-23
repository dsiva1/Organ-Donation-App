package com.organdonation.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.organdonation.R
import com.organdonation.drawer.DrawerBody
import com.organdonation.drawer.DrawerHeader
import com.organdonation.drawer.TopBar
import com.organdonation.routing.Screen
import com.organdonation.ui.model.DonorModel
import com.organdonation.ui.organ_preference.OrganPreference
import com.organdonation.ui.theme.*
import com.organdonation.utils.CustomSearchView
import com.organdonation.utils.RoundedButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val preference = remember {
        OrganPreference(context)
    }
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var isLogout by remember { mutableStateOf(false) }
    var search by remember { mutableStateOf("") }
    val list = arrayListOf<DonorModel>().apply {
        add(DonorModel(name = "Test Donor", mobile = "9879879879", donate = "Kidney"))
        add(DonorModel(name = "Test Donor", mobile = "9879879879", donate = "Liver"))
        add(DonorModel(name = "Test Donor", mobile = "9879879879", donate = "Lungs"))
        add(DonorModel(name = "Test Donor", mobile = "9879879879", donate = "Heart"))
        add(DonorModel(name = "Test Donor", mobile = "9879879879", donate = "Pancreas"))
        add(DonorModel(name = "Test Donor", mobile = "9879879879", donate = "Intestines"))
        add(DonorModel(name = "Test Donor", mobile = "9879879879", donate = "Hand"))
        add(DonorModel(name = "Test Donor", mobile = "9879879879", donate = "Face"))
        add(DonorModel(name = "Test Donor", mobile = "9879879879", donate = "Kidney"))
        add(DonorModel(name = "Test Donor", mobile = "9879879879", donate = "Liver"))
        add(DonorModel(name = "Test Donor", mobile = "9879879879", donate = "Lungs"))
        add(DonorModel(name = "Test Donor", mobile = "9879879879", donate = "Heart"))
        add(DonorModel(name = "Test Donor", mobile = "9879879879", donate = "Pancreas"))
        add(DonorModel(name = "Test Donor", mobile = "9879879879", donate = "Intestines"))
        add(DonorModel(name = "Test Donor", mobile = "9879879879", donate = "Hand"))
        add(DonorModel(name = "Test Donor", mobile = "9879879879", donate = "Face"))

    }
    OrganDonationAppTheme {
        androidx.compose.material.Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBar(
                    navController = navController,
                    onNavigationIconClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            },
            modifier = Modifier.background(color = black),
            drawerContent = {
                DrawerHeader()
                DrawerBody(onLogout = {
                    isLogout = true
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                })
            },
            drawerBackgroundColor = appColor,
            contentColor = black,
            drawerContentColor = black
        ) { paddingValues ->
            Modifier.padding(
                bottom = paddingValues.calculateBottomPadding()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(appColor)
                    .verticalScroll(scrollState)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(appColor).padding(5.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        CustomSearchView(search = search, onValueChange = {
                            search = it
                        }, onClick = {
                            navController.navigate(Screen.SearchList.route)
                        })
                    }
                    Spacer(Modifier.height(10.dp))
                    list.forEachIndexed { index, donorModel ->
                        Card(
                            modifier = Modifier
                                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                                .fillMaxWidth()
                                .height(300.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = white),
                            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                        ) {
                            Spacer(Modifier.height(10.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_person_24),
                                    contentDescription = "Image",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .width(40.dp)
                                        .height(40.dp)
                                )
                                Column {
                                    Text(
                                        donorModel.name ?: "",
                                        fontSize = 14.sp,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .padding(vertical = 5.dp, horizontal = 10.dp)
                                    )
                                    Text(
                                        donorModel.mobile ?: "",
                                        fontSize = 14.sp,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .padding(vertical = 5.dp, horizontal = 10.dp)
                                    )
                                }
                            }
                            Spacer(Modifier.height(10.dp))
                            Divider(
                                thickness = 1.5.dp,
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(10.dp))
                            Text(
                                "Donate : ${donorModel.donate}",
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                            Spacer(Modifier.height(10.dp))
                            Divider(
                                thickness = 1.5.dp,
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Box(Modifier.padding(10.dp)) {
                                RoundedButton(
                                    text = "Donor Form",
                                    backgroundColor = black,
                                    textColor = white,
                                    onClick = {
                                        navController.navigate(Screen.DonorForm.route)
                                    }
                                )
                            }
                            Box(Modifier.padding(10.dp)) {
                                RoundedButton(
                                    text = "Donor Body Form",
                                    backgroundColor = black,
                                    textColor = white,
                                    onClick = {
                                        navController.navigate(Screen.DonorBodyForm.route)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
        if (isLogout) {
            AlertDialog(
                onDismissRequest = {
                    isLogout = false
                },
                title = { Text(stringResource(id = R.string.app_name)) },
                text = { Text("Are you sure you want to logout ?") },
                confirmButton = {
                    RoundedButton(
                        text = "Cancel",
                        backgroundColor = appColor,
                        textColor = black,
                        onClick = { isLogout = false }
                    )
                },
                dismissButton = {

                    RoundedButton(
                        text = "Logout",
                        backgroundColor = appColor,
                        textColor = black,
                        onClick = {
                            preference.saveData("isLogin", false)
                            navController.navigate(
                                Screen.LoginScreen.route
                            ) {
                                popUpTo(Screen.MainScreen.route) {
                                    inclusive = true
                                }
                            }
                            isLogout = false
                        }
                    )

                }
            )
        }

    }

}