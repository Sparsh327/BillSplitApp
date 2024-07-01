package com.example.billsplit.widgets


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


val IconButtonSizeModifier =Modifier.padding(40.dp)
@Composable
fun RoundIconButton(
    modifier: Modifier,
    imageVector: ImageVector,
    onClick: () ->Unit,
    tint: Color = Color.Black.copy(alpha = 0.8f),
    backGroundColor: Color= MaterialTheme.colorScheme.onBackground,
    elevation: Dp = 4.dp
){
    Card(modifier = modifier.padding(all = 4.dp).clickable {
        onClick.invoke()
    }.then(IconButtonSizeModifier)

        ) {

    }
}