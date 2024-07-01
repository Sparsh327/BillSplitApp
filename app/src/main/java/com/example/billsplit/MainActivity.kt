package com.example.billsplit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.billsplit.components.InputField
import com.example.billsplit.ui.theme.BillSplitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
          MyApp {
              TopHeader()
          }
        }
    }
}





@Composable
fun MyApp(content: @Composable () -> Unit){
    BillSplitTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}





@Composable
fun TopHeader(totalSplit : Double = 222.0 ){
    Surface(
        color = Color(color = 0xFFE9D7F7),
        modifier= Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(
                shape = RoundedCornerShape(corner = CornerSize(12.dp))
            )) {
         Column (modifier = Modifier.padding(12.dp),
                 horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement = Arrangement.Center
             ){
             val total = "%.2f".format(totalSplit)
             Text(text = "Total Per Person",
                   style = MaterialTheme.typography.headlineMedium
                 )
             Text(text = "$$total",
                 style = MaterialTheme.typography.headlineLarge
               , fontWeight = FontWeight.Bold
                 )
         }
    }
}

@Preview

@Composable
fun MainContent(){
   BillForm(){ billAmt ->
       Log.d("de","ede $billAmt")
   }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(modifier: Modifier= Modifier,
onValChange :(String) -> Unit={}
             ){
    val totalBillState= remember {
        mutableStateOf(value = "")
    }
    val  validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }
    val  keyboardController = LocalSoftwareKeyboardController.current
    Surface(modifier = Modifier
        .padding(2.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column(modifier=Modifier.padding(6.dp),
verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
            ) {
            InputField(valueState = totalBillState,
                labelId ="Enter Bill" , enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions{
                    if(!validState)
                        return@KeyboardActions
                   onValChange(totalBillState.value.trim())
                    keyboardController?.hide()
                }
            )
            if(validState){
                Row(modifier=Modifier.padding(3.dp),
horizontalArrangement = Arrangement.Start,

                    ) {
Text(text = "Split", modifier = Modifier.align(alignment = Alignment.CenterVertically))
Spacer(modifier = Modifier.width(120.dp))
                    Row(modifier=Modifier.padding(horizontal = 3.dp),
horizontalArrangement = Arrangement.End
                        ) {

                    }
                }

            }else{
                Box {

                }
            }
        }
    }
}


@Preview
@Composable
fun GreetingPreview() {
   MyApp {
      TopHeader()
   }
}