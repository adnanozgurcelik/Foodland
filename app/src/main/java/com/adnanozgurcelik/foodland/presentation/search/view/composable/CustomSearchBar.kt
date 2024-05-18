package com.adnanozgurcelik.foodland.presentation.search.view.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    maxLines: Int = 1,
    onValueChanged: (String) -> Unit = {},
    singleLine: Boolean = true
) {
    Box(modifier = modifier) {
        BasicTextField(
            value = value,
            onValueChange = onValueChanged,
            textStyle = textStyle,
            maxLines = maxLines,
            singleLine = singleLine,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 5.dp)
                .clip(
                    shape = RoundedCornerShape(15.dp)
                ),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxWidth()) {
                    if (value.isEmpty()) {
                        Text(
                            text = hint,
                            color = Color.DarkGray
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}