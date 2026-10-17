package com.example.vk_education_application.feature.applist.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.vk_education_application.R
import com.example.vk_education_application.feature.applist.domain.model.App
import com.example.vk_education_application.feature.applist.domain.model.Category

@Composable
fun AppListItem(
    app: App,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        // Иконка приложения
        AsyncImage(
            model = app.iconUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(12.dp)),
        )
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            // Категория
            Text(
                text = categoryText(app.category),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 12.sp,
            )
            Spacer(Modifier.height(2.dp))
            // Название
            Text(
                text = app.name,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.height(4.dp))
            // Краткое описание
            Text(
                text = app.description,
                fontSize = 13.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun categoryText(category: Category): String = when (category) {
    Category.APP -> stringResource(R.string.category_app)
    Category.GAME -> stringResource(R.string.category_game)
}

@Preview
@Composable
private fun Preview() {
    AppListItem(
        app = App(
            id = 1,
            name = "Гильдия Героев: Экшен ММО РПГ",
            description = "Легендарный рейд героев в Фэнтези РПГ. Станьте героем гильдии и сразите мастера подземелья!",
            category = Category.GAME,
            iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
        ),
    )
}