package com.example.vk_education_application.feature.applist.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.vk_education_application.R

@Composable
fun AppListToolbar(modifier: Modifier = Modifier) {
    Row(
            modifier = modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary) // акцентный цвет задника
                    .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            ) {
        AsyncImage(
                model = "https://static5.tgstat.ru/channels/_0/d5/d571940850985e3c923bdc99bb88a0a2.jpg",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(8.dp)),
                )
        Text(
                text = stringResource(R.string.toolbar_title),
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 12.dp),
                )
    }
}

@Preview
@Composable
private fun ToolbarPreview() {
    AppListToolbar()
}
