package com.example.mycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.RowScopeInstance.align
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycalculator.ui.theme.MyCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Calculator()
                }
            }
        }
    }
}




@Composable
fun Calculator(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf("") }
    var currentInput by remember { mutableStateOf("") }
    var previousNumber by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("") }
    var display by remember { mutableStateOf("") }

    val performCalculation: () -> Unit = {
        val prevNum = previousNumber.toIntOrNull() ?: 0
        val currentNum = currentInput.toIntOrNull() ?: 0
        when (operation) {
            "+" -> result = (prevNum + currentNum).toString()
            "-" -> result = (prevNum - currentNum).toString()
        }
        currentInput = result
        display = result
        operation = ""
    }

    val onNumberClick: (String) -> Unit = { number ->
        currentInput += number
        display += number
        result = currentInput
    }

    val onOperationClick: (String) -> Unit = { op ->
        operation = op
        result += op
        display += op
        previousNumber = currentInput
        currentInput = ""
    }

    val onResetClick: () -> Unit = {
        result = ""
        currentInput = ""
        previousNumber = ""
        operation = ""
        display = ""
    }


    Column(modifier = Modifier
            .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(
                text = "$display", fontSize = 24.sp, modifier = Modifier.padding(8.dp)
            )
            Row (modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Button(onClick = { onNumberClick("1") },
                    modifier = Modifier.size(64.dp)) {
                    Text(text = "1")
                }
                Button(onClick = { onNumberClick("2")  },
                    modifier = Modifier.size(64.dp)) {
                    Text(text = "2")
                }
                Button(onClick = { onNumberClick("3") },
                    modifier = Modifier.size(64.dp)) {
                    Text(text = "3")
                }

            }
            Row(modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center)  {
                Button(onClick = {onNumberClick("4") },
                    modifier = Modifier.size(64.dp) ){
                    Text(text = "4")
                }
                Button(onClick = { onNumberClick("5") },
                    modifier = Modifier.size(64.dp) ){
                    Text(text = "5")
                }
                Button(onClick = { onNumberClick("6")  },
                    modifier = Modifier.size(64.dp) ){
                    Text(text = "6")
                }

            }
            Row(modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Button(onClick = { onNumberClick("7") },
                    modifier = Modifier.size(64.dp)) {
                    Text(text = "7")
                }
                Button(onClick = { onNumberClick("8") },
                    modifier = Modifier.size(64.dp)) {
                    Text(text = "8")
                }
                Button(onClick = {onNumberClick("9")  },
                    modifier = Modifier.size(64.dp)) {
                    Text(text = "9")
                }

            }

            Row(modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Button(onClick = {onNumberClick("0")  },
                    modifier = Modifier.size(64.dp)) {
                    Text(text = "0")
                }

            }

        }
        Column(modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(start = 280.dp),
            verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally)
        {
            Button(onClick = { onOperationClick("+")},
                modifier = Modifier.size(64.dp)) {
                Text(text = "+")
            }
            Button(onClick = { onOperationClick("-") },
                modifier = Modifier.size(64.dp)) {
                Text(text = "-")
            }
            Button(onClick = performCalculation,
                modifier = Modifier.size(64.dp)) {
                Text(text = "=")
            }
            Button(onClick = onResetClick,
                modifier = Modifier.size(64.dp)) {
                Text(text = "C")
            }
        }
    }




@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    Calculator()
}