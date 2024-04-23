package com.organdonation.ui.donorBodyForm

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.organdonation.R
import com.organdonation.ui.theme.OrganDonationAppTheme
import com.organdonation.ui.theme.black
import com.organdonation.ui.theme.appColor
import com.organdonation.ui.theme.white
import com.organdonation.utils.OrganField
import com.organdonation.utils.RoundedButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalAnimationApi::class
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DonorBodyForm(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var name by remember { mutableStateOf("") }
    var fName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var relativeName by remember { mutableStateOf("") }
    var relativeMobile by remember { mutableStateOf("") }


    var isGroup by remember {
        mutableStateOf(false)
    }
    val bloodGroup = arrayListOf<String>("A-","A+",
        "AB-","AB+","B-","B+","O-","O+")

    val selectedBloodGroup = remember {
        mutableStateListOf<String>()

    }

    var isSubmit by remember { mutableStateOf(false) }
    OrganDonationAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(black)
                    .verticalScroll(scrollState)
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
                                text = "Donor Body Form", color = black,
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
                            "Donor Name",
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
                            "Father's Name",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(color = black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OrganField(
                            value = fName,
                            backgroundColor = black,
                            onValueChange = { text ->
                                fName = text
                            },
                            placeholder = "Enter father name",
                            keyboardType = KeyboardType.Text,
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            "Age",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(color = black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OrganField(
                            value = age,
                            backgroundColor = black,
                            onValueChange = { text ->
                                age = text
                            },
                            placeholder = "Enter age",
                            keyboardType = KeyboardType.Number,
                        )

                        Spacer(modifier = Modifier.height(20.dp))
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
                            placeholder = "Donor Mobile No",
                            keyboardType = KeyboardType.Phone,
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            "Correspondence Address",
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
                            placeholder = "Correspondence address",
                            keyboardType = KeyboardType.Text,
                        )



                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            "Select blood group",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(color = black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        ExposedDropdownMenuBox(
                            expanded = isGroup,
                            onExpandedChange = { isGroup = it }
                        ) {
                            TextField(
                                value = selectedBloodGroup.joinToString(", "),
                                onValueChange = {},
                                placeholder = {
                                    Text(text = "Select blood group")
                                },
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isGroup)
                                },
                                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                                modifier = Modifier.fillMaxWidth().padding(start = 20.dp,end = 20.dp)
                            )
                            ExposedDropdownMenu(
                                expanded = isGroup,
                                onDismissRequest = { isGroup = false }
                            ) {
                                bloodGroup.forEach { name ->
                                    AnimatedContent(
                                        targetState = selectedBloodGroup.contains(name)
                                    ) { isSelected ->
                                        if (isSelected) {
                                            DropdownMenuItem(
                                                text = {
                                                    Text(text = name)
                                                },
                                                onClick = {
                                                    selectedBloodGroup.remove(name)
                                                },
                                                leadingIcon = {
                                                    Icon(
                                                        imageVector = Icons.Rounded.Check,
                                                        contentDescription = null
                                                    )
                                                }
                                            )
                                        } else {
                                            DropdownMenuItem(
                                                text = {
                                                    Text(text = name)
                                                },
                                                onClick = {
                                                    selectedBloodGroup.add(name)
                                                },
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            "Name of relative",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(color = black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OrganField(
                            value = relativeName,
                            backgroundColor = black,
                            onValueChange = { text ->
                                relativeName = text
                            },
                            placeholder = "Enter name of relative",
                            keyboardType = KeyboardType.Text,
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            "Mobile of relative",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(color = black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OrganField(
                            value = relativeMobile,
                            backgroundColor = black,
                            onValueChange = { text ->
                                relativeMobile = text
                            },
                            placeholder = "Enter mobile of relative",
                            keyboardType = KeyboardType.Phone,
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
                                            if (fName.isNotEmpty()) {
                                                if (age.isNotEmpty()) {
                                                    if (mobile.isNotEmpty()) {
                                                        if (address.isNotEmpty()) {
                                                            if (relativeName.isNotEmpty()) {
                                                                if (relativeMobile.isNotEmpty()) {
                                                                    isSubmit = true
                                                                }else{
                                                                    Toast.makeText(
                                                                        context,
                                                                        "Please enter number of relative.",
                                                                        Toast.LENGTH_LONG
                                                                    ).show()
                                                                }
                                                            }else{
                                                                Toast.makeText(
                                                                    context,
                                                                    "Please enter name of relative.",
                                                                    Toast.LENGTH_LONG
                                                                ).show()
                                                            }
                                                        }else{
                                                            Toast.makeText(
                                                                context,
                                                                "Please enter correspondence address.",
                                                                Toast.LENGTH_LONG
                                                            ).show()
                                                        }
                                                    }else{
                                                        Toast.makeText(
                                                            context,
                                                            "Please enter mobile number.",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                    }
                                                }else{
                                                    Toast.makeText(
                                                        context,
                                                        "Please enter age.",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                }
                                            }else{
                                                Toast.makeText(
                                                    context,
                                                    "Please enter father name.",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }

                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Please enter donor name.",
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
        if (isSubmit) {
            AlertDialog(
                onDismissRequest = {
                    isSubmit = false
                },
                title = { Text(stringResource(id = R.string.app_name)) },
                text = { Text("Your body form is submitted successfully.") },
                confirmButton = {
                    RoundedButton(
                        text = "Ok",
                        backgroundColor = appColor,
                        textColor = black,
                        onClick = {
                            navController.navigateUp()
                            isSubmit = false
                        }
                    )
                },
                dismissButton = {}
            )
        }

    }
}