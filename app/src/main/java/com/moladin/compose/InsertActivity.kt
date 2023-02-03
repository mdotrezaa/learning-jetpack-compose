package com.moladin.compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.moladin.compose.data.CarEntity
import com.moladin.compose.data.Database
import com.moladin.compose.ui.screen.EditScreen
import com.moladin.compose.ui.theme.MoladinJetpackComposeTheme


class InsertActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MutableCollectionMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentIndex = Database.sources.size

        setContent {
            MoladinJetpackComposeTheme {

                var carName by remember { mutableStateOf(TextFieldValue("")) }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Create New Car")
                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    finish()
                                }) {
                                    Icon(Icons.Filled.ArrowBack, "back-button")
                                }
                            }
                        )
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 24.dp)
                    ) {
                        EditScreen(
                            placeholder = "",
                            value = carName,
                            onValueChange = {
                                carName = it
                            })
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(10.dp),
                            onClick = {
                                insert(currentIndex + 1, carName.text,"https://loremflickr.com/320/240/cars,sport")
                                setResult(SAVE_CODE)
                                finish()
                            }
                        ) {
                            Text(text = "Save")
                        }
                    }
                }
            }
        }
    }
    private fun insert(carId: Int, carName: String, imageUrl: String) {
        Database.Instance.insert(CarEntity(carId, carName,imageUrl))
    }
}