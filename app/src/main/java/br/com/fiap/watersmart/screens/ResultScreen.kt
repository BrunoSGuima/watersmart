package br.com.fiap.watersmart.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.*


@Composable
fun ResultScreen(navController: NavController, consumptionRecommendation: Float) {
    var consumedAmountInput by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
        ) {
            //----- header -----
            WaterHeader()

            // --- Form ---
            Card(
                modifier = Modifier
                    .height(350.dp)
                    .fillMaxWidth()
                    .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp)
                ) {
                    Text(
                        text = "Informe:",
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = "Quanto de água você já bebeu hoje?",
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                    OutlinedTextField(
                        value = consumedAmountInput,
                        onValueChange = { newValue -> consumedAmountInput = newValue },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Água consumida (em litros)") },
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            val consumed = consumedAmountInput.toFloatOrNull() ?: 0f
                            if (consumed >= consumptionRecommendation) {
                                resultMessage = "Parabéns! Você já consumiu o recomendado para o dia de hoje."
                            } else {
                                val remaining = consumptionRecommendation - consumed
                                resultMessage = "Você ainda precisa beber ${remaining} litros de água hoje."
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(0.7f)
                            .padding(16.dp)
                            .height(56.dp)
                    ) {
                        Text(text = "Enviar", fontSize = 20.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp)) // Espaço depois do botão
                }
            }

            Spacer(modifier = Modifier.height(16.dp)) // Espaço entre os cartões

            // --- Result Card ---
            if (resultMessage != null) {
                Card(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xff329F6B)),
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize().padding(16.dp)
                    ) {
                        Text(
                            text = resultMessage!!,
                            color = Color.White,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}



