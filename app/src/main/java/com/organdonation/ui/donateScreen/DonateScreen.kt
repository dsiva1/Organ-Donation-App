package com.organdonation.ui.donateScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.organdonation.ui.theme.OrganDonationAppTheme
import com.organdonation.ui.theme.black
import com.organdonation.ui.theme.appColor
import com.organdonation.ui.theme.white
import com.organdonation.utils.OrganField
import com.organdonation.utils.RoundedButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DonateScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    OrganDonationAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(black)
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(appColor)
                ) {
                    SmallTopAppBar(
                        title = {
                            Text(
                                text = "Donate Screen", color = black,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                style = TextStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp
                                )
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    navController.navigateUp()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.ArrowBack,
                                    tint = black,
                                    contentDescription = "Back"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = appColor,
                            titleContentColor = black
                        )
                    )

                    Column(
                        modifier = Modifier.fillMaxSize().background(color = white).padding(10.dp)
                    ) {
                        Text(
                            "Name",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(color = black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OrganField(
                            value = name,
                            backgroundColor = black,
                            onValueChange = { text ->
                                name = text
                            },
                            placeholder = "Enter name",
                            keyboardType = KeyboardType.Text,
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            "Email",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(color = black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OrganField(
                            value = email,
                            backgroundColor = black,
                            onValueChange = { text ->
                                email = text
                            },
                            placeholder = "Enter email",
                            keyboardType = KeyboardType.Email,
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Mobile Number",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(color = black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OrganField(
                            value = mobile,
                            backgroundColor = black,
                            onValueChange = { text ->
                                mobile = text
                            },
                            placeholder = "Enter mobile",
                            keyboardType = KeyboardType.Phone,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Address",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(color = black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OrganField(
                            value = address,
                            backgroundColor = black,
                            onValueChange = { text ->
                                address = text
                            },
                            placeholder = "Enter address",
                            keyboardType = KeyboardType.Text,
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Column(
                            modifier = Modifier.fillMaxSize().padding(bottom = 20.dp),
                            verticalArrangement = Arrangement.Bottom
                        ) {

                            Box(Modifier.padding(15.dp)) {
                                RoundedButton(
                                    text = "Submit",
                                    textColor = white,
                                    backgroundColor = black,
                                    onClick = {
                                        if (name.isNotEmpty()) {
                                            if (email.isNotEmpty()) {
                                                if (mobile.isNotEmpty()) {
                                                    if (address.isNotEmpty()) {
                                                        Toast.makeText(
                                                            context,
                                                            "Submitted Successfully.",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                        navController.navigateUp()
                                                    } else {
                                                        Toast.makeText(
                                                            context,
                                                            "Please enter address.",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                    }
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        "Please enter mobile.",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                }
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Please enter email.",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Please enter name.",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }

                }
            }
        }

    }
}