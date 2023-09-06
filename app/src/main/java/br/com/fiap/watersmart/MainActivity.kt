import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.watersmart.R
import br.com.fiap.watersmart.ui.theme.WaterSmartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaterSmartTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    WaterConsumptionScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun WaterConsumptionScreen() {
    val (weight, setWeight) = remember { mutableStateOf("") }
    val (consumptionRecommendation, setConsumptionRecommendation) = remember { mutableStateOf(0f) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            //----- header -----
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
            Button(onClick = {},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            ) {
                Text("Próximo", fontSize = 20.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WaterConsumptionScreenPreview() {
    WaterSmartTheme {
        WaterConsumptionScreen()
    }
}

