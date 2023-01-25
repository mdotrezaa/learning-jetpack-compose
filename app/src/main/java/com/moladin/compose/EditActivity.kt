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

const val SAVE_CODE = 1000
class EditActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MutableCollectionMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val carId = intent.getIntExtra("car_id", 0)
        val imageUrl = intent.getStringExtra("img_url")
        setContent {
            MoladinJetpackComposeTheme {

                val carEntity = Database.Instance.getCarById(carId)
                var carName by remember { mutableStateOf(TextFieldValue("")) }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "CAR ID: $carId")
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
                            placeholder = carEntity?.name.orEmpty(),
                            value = carName,
                            onValueChange = {
                                carName = it
                            })

                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(horizontal = 5.dp),
                                onClick = {
                                    if (imageUrl != null) {
                                        update(carId, carName.text, imageUrl)
                                    }
                                    setResult(SAVE_CODE)
                                    finish()
                                }) {
                                Text(text = "Delete")
                            }
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(horizontal = 5.dp),
                                onClick = {
                                    if (imageUrl != null) {
                                        update(carId, carName.text,imageUrl)
                                    }
                                    setResult(SAVE_CODE)
                                    finish()
                                }) {
                                Text(text = "Save")
                            }
                        }

                    }
                }
            }
        }
    }

    private fun update(carId: Int, newCarName: String, imageUrl: String) {
        if (newCarName.isNotEmpty()) {
            Database.Instance.update(CarEntity(carId, newCarName,imageUrl))
        }
    }
}