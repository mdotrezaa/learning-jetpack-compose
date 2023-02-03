package com.moladin.compose

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moladin.compose.data.CarEntity
import com.moladin.compose.data.Database
import com.moladin.compose.ui.screen.CarList
import com.moladin.compose.ui.theme.MoladinJetpackComposeTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MutableCollectionMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoladinJetpackComposeTheme {
                val cars = remember { mutableStateListOf<CarEntity>() }
                cars.addAll(Database.Instance.getCars())
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Workshop Android")
                            }
                        )
                    },
                    floatingActionButton = {
                        val startLauncher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartActivityForResult(),
                            onResult = {
                                if (it.resultCode == SAVE_CODE) {
                                    reload(cars)
                                }
                            }
                        )
                        FloatingActionButton(onClick = {
                            Intent(this, InsertActivity::class.java).apply {
                                startLauncher.launch(this)
                            }
                        }) {
                            Icon(Icons.Filled.Add, contentDescription = "add item")
                        }
                    }
                ) {
                    val startLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult(),
                        onResult = {
                            if (it.resultCode == SAVE_CODE) {
                                reload(cars)
                            }
                        }
                    )
                    CarList(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        cars = cars,
                        onItemClick = {
                            Intent(this, EditActivity::class.java).apply {
                                putExtra("car_id", it.carId)
                                startLauncher.launch(this)
                            }
                        }
                    )
                }
            }
        }
    }

    private fun reload(snapshots: SnapshotStateList<CarEntity>){
        snapshots.clear()
        snapshots.addAll(Database.Instance.getCars())
    }


    /*private fun removeCar(snapshots: SnapshotStateList<CarEntity>, carId: Int) {
        for (car in snapshots) {
            if (car.carId == carId) {
                snapshots.remove(car)
                break
            }
        }

        //remove database as well
        Database.Instance.delete(carId)
    }*/
}