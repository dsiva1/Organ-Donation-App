package com.organdonation.ui.donorForm

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
fun DonorForm(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var name by remember { mutableStateOf("") }
    var fName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var isOrgan by remember {
        mutableStateOf(false)
    }
    val organDonation = arrayListOf<String>("Heart","Lungs",
        "Kidney","Liver","Pancreas","All Organs")

    val selectedOrganDonation = remember {
        mutableStateListOf<String>()

    }

    var isTissue by remember {
        mutableStateOf(false)
    }
    val tissueDonation = arrayListOf<String>("Corneas/Eye Balls","Skin",
        "Bones","Heart Valved","Blood Vessels","All Tissue")

    val selectedTissueDonation = remember {
        mutableStateListOf<String>()

    }

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
                                text = "Donor Form", color = black,
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
                            "Select organ donation",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(color = black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        ExposedDropdownMenuBox(
                            expanded = isOrgan,
                            onExpandedChange = { isOrgan = it }
                        ) {
                            TextField(
                                value = selectedOrganDonation.joinToString(", "),
                                onValueChange = {},
                                placeholder = {
                                    Text(text = "Select organ donation")
                                },
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isOrgan)
                                },
                                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                                modifier = Modifier.fillMaxWidth().padding(start = 20.dp,end = 20.dp)
                            )
                            ExposedDropdownMenu(
                                expanded = isOrgan,
                                onDismissRequest = { isOrgan = false }
                            ) {
                                organDonation.forEach { name ->
                                    AnimatedContent(
                                        targetState = selectedOrganDonation.contains(name)
                                    ) { isSelected ->
                                        if (isSelected) {
                                            DropdownMenuItem(
                                                text = {
                                                    Text(text = name)
                                                },
                                                onClick = {
                                                    selectedOrganDonation.remove(name)
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
                                                    selectedOrganDonation.add(name)
                                                },
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            "Select tissue donation",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(color = black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        ExposedDropdownMenuBox(
                            expanded = isTissue,
                            onExpandedChange = { isTissue = it }
                        ) {
                            TextField(
                                value = selectedTissueDonation.joinToString(", "),
                                onValueChange = {},
                                placeholder = {
                                    Text(text = "Select tissue donation")
                                },
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isTissue)
                                },
                                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                                modifier = Modifier.fillMaxWidth().padding(start = 20.dp,end = 20.dp)
                            )
                            ExposedDropdownMenu(
                                expanded = isTissue,
                                onDismissRequest = { isTissue = false }
                            ) {
                                tissueDonation.forEach { name ->
                                    AnimatedContent(
                                        targetState = selectedTissueDonation.contains(name)
                                    ) { isSelected ->
                                        if (isSelected) {
                                            DropdownMenuItem(
                                                text = {
                                                    Text(text = name)
                                                },
                                                onClick = {
                                                    selectedTissueDonation.remove(name)
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
                                                    selectedTissueDonation.add(name)
                                                },
                                            )
                                        }
                                    }
                                }
                            }
                        }
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
                                                            if (organDonation.isNotEmpty()) {
                                                                if (tissueDonation.isNotEmpty()) {
                                                                    if (bloodGroup.isNotEmpty()) {
                                                                        isSubmit = true
                                                                    }else{
                                                                        Toast.makeText(
                                                                            context,
                                                                            "Please select blood group.",
                                                                            Toast.LENGTH_LONG
                                                                        ).show()
                                                                    }
                                                                }else{
                                                                    Toast.makeText(
                                                                        context,
                                                                        "Please select tissue donation.",
                                                                        Toast.LENGTH_LONG
                                                                    ).show()
                                                                }
                                                            }else{
                                                                Toast.makeText(
                                                                    context,
                                                                    "Please select organ donation.",
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
                text = { Text("Your form is submitted successfully.") },
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