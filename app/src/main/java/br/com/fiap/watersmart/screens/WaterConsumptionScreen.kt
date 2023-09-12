package br.com.fiap.watersmart.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.watersmart.ui.theme.WaterSmartTheme


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun WaterConsumptionScreen(navController: NavController) {
    val (weight, setWeight) = remember { mutableStateOf("") }
    val (consumptionRecommendation, setConsumptionRecommendation) = remember { mutableStateOf(0f) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            //----- header -----
            WaterHeader()

            // --- Formulário ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Card(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .offset(y = (-30).dp),
                    colors = CardDefaults
                        .cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp),
                    //border = BorderStroke(width = 1.dp, Color.White),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(
                            vertical = 16.dp,
                            horizontal = 32.dp
                        )
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
                            text = "Seu peso",
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                        OutlinedTextField(
                            value = weight,
                            onValueChange = {setWeight(it)},
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Seu peso em Kg.")
                            },
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                val recommended = weight.toFloatOrNull()?.times(35)?.div(1000) ?: 0f
                                setConsumptionRecommendation(recommended)
                                keyboardController?.hide()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(16.dp),
                        ) {
                            Text(
                                text = "CALCULAR",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 20.sp
                            )
                        }
                    } // fechamento do Button
                } // fechamento da Column
            } // fechamento do Card
        }// fechamento da Column externa
        // --- card resultado
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .padding(horizontal = 32.dp, vertical = 24.dp)
                .align(Alignment.BottomCenter),
            colors = CardDefaults.cardColors(containerColor = Color(0xff329F6B)),
            elevation = CardDefaults.cardElevation(4.dp),
            //border = BorderStroke(width = 1.dp, Color(0xffed145b))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 36.dp)
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Column() {
                    Text(
                        text = "Consumo ideal:",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 28.sp
                    )
                }
                Text(
                    text = "%.2f".format(consumptionRecommendation),
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 32.sp,
                    textAlign = TextAlign.End
                )
            }
            Button(onClick = {
                navController.navigate("resultScreen/${consumptionRecommendation}")
            },modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)) {
                Text("Próximo", fontSize = 20.sp)
            }

        }
    }
}


@Preview
@Composable
fun WaterConsumptionScreenPreview() {
    val navController = rememberNavController()
    WaterConsumptionScreen(navController)
}
