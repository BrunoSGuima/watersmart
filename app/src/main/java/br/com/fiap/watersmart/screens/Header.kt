package br.com.fiap.watersmart.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color
import br.com.fiap.watersmart.R
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text




@Composable
fun WaterHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(Color.DarkGray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.water),
            contentDescription = "imagem de água",
            modifier = Modifier
                .size(75.dp)
                .padding(top = 10.dp)
        )
        Text(
            text = "Consumo ideal de água.",
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)
        )
    }
}

