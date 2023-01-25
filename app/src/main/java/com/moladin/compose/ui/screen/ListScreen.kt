package com.moladin.compose.ui.screen

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.moladin.compose.data.CarEntity
import com.moladin.compose.ui.theme.ListColor


@OptIn(ExperimentalUnitApi::class)
@Composable
private fun CarItem(
    carEntity: CarEntity,
    onItemClick: (CarEntity) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = "${carEntity.imageUrl}?random=${carEntity.carId}",
            contentDescription = null,
            contentScale = ContentScale.Crop,            // crop the image if it's not a square
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 5.dp)
                .clickable {
                    onItemClick(carEntity)
                }
        ) {
            Text(
                text = "CAR ID: ${carEntity.carId}",
                style = TextStyle(
                    fontSize = TextUnit(12f, TextUnitType.Sp)
                ),
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = carEntity.name,
                style = TextStyle(
                    fontSize = TextUnit(20f, TextUnitType.Sp)
                ),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                modifier = Modifier
                    .background(Color.LightGray)
                    .height(1.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun CarList(
    modifier: Modifier,
    cars: SnapshotStateList<CarEntity>,
    onItemClick: (CarEntity) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        content = {
            items(
                cars,
                itemContent = {
                    CarItem(
                        carEntity = it,
                        onItemClick = onItemClick
                    )
                }
            )
        })
}