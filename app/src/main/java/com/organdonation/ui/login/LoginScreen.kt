package com.organdonation.ui.login

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.organdonation.R
import com.organdonation.routing.Screen
import com.organdonation.ui.organ_preference.OrganPreference
import com.organdonation.ui.theme.*
import com.organdonation.utils.OrganField
import com.organdonation.utils.RoundedButton
import com.organdonation.utils.isValidEmail

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val preference = remember {
        OrganPreference(context)
    }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val db = Firebase.firestore
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
                        .padding(10.dp)
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    Card(
                        modifier = Modifier
                            .width(120.dp)
                            .height(120.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(60.dp),
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

                    Spacer(modifier = Modifier.height(30.dp))
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

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Password",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = black)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OrganField(
                        value = password,
                        backgroundColor = black,
                        onValueChange = { text ->
                            password = text
                        },
                        placeholder = "Enter Password",
                        keyboardType = KeyboardType.Password,
                        visualTransformation = PasswordVisualTransformation(),
                    )

                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        modifier = Modifier
                    ) {
                        RoundedButton(
                            text = "Login",
                            backgroundColor = black,
                            textColor = appColor,
                            onClick = {
                                if (email.isNotEmpty()) {
                                    if (!isValidEmail(email.trim())) {
                                        if (password.isNotEmpty()) {
                                            db.collection("users")
                                                .get()
                                                .addOnSuccessListener { result ->
                                                    if (result.isEmpty) {
                                                        Toast.makeText(
                                                            context,
                                                            "Invalid user.",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                        return@addOnSuccessListener
                                                    } else {
                                                        for (document in result) {
                                                            Log.e(
                                                                "TAG",
                                                                "setOnClick: $document"
                                                            )
                                                            if (document.data["email"] == email &&
                                                                document.data["password"] == password
                                                            ) {
                                                                preference.saveData(
                                                                    "isLogin",
                                                                    true
                                                                )
                                                                Toast.makeText(
                                                                    context,
                                                                    "Login successfully.",
                                                                    Toast.LENGTH_LONG
                                                                ).show()
                                                                navController.navigate(
                                                                    Screen.MainScreen.route
                                                                ) {
                                                                    popUpTo(Screen.LoginScreen.route) {
                                                                        inclusive = true
                                                                    }
                                                                }
                                                            } else {
                                                                Toast.makeText(
                                                                    context,
                                                                    "Invalid user.",
                                                                    Toast.LENGTH_LONG
                                                                ).show()
                                                                return@addOnSuccessListener
                                                            }
                                                        }
                                                    }

                                                }
                                                .addOnFailureListener { exception ->
                                                    Toast.makeText(
                                                        context,
                                                        exception.message.toString(),
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                }
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Please enter password.",
                                                Toast.LENGTH_LONG
                                            ).show()

                                        }
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Please enter valid email.",
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
                            }
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(appColor)
                            .padding(bottom = 20.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Don't have an account?",
                                textAlign = TextAlign.End,
                                style = TextStyle(color = black)
                            )

                            Text(
                                " Register", modifier = Modifier.clickable {
                                    navController.navigate(Screen.RegisterScreen.route)
                                }, textAlign = TextAlign.End,
                                style = TextStyle(color = black)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }


            }


        }
    }
}