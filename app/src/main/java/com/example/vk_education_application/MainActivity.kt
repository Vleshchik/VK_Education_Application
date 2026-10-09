package com.example.vk_education_application

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var inputText by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Введите текст или номер телефона") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { openSecondActivity(context, inputText) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Далее")
        }

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = { callFriend(context, inputText) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Позвонить другу")
        }

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = { shareText(context, inputText) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Поделиться текстом")
        }
    }
}

// ---------- 1. Явный Intent ----------
private fun openSecondActivity(context: Context, rawText: String) {
    val text = rawText.trim()
    if (text.isEmpty()) {
        toast(context, "Поле не должно быть пустым")
        return
    }
    val intent = Intent(context, SecondActivity::class.java).apply {
        putExtra(SecondActivity.EXTRA_TEXT, text)
    }
    context.startActivity(intent)
}

// ---------- 2. Неявный Intent: звонок ----------
private fun callFriend(context: Context, rawText: String) {
    val text = rawText.trim()
    if (text.isEmpty()) {
        toast(context, "Введите номер телефона")
        return
    }
    if (!text.matches(Regex("^[+]?[0-9\\s\\-()]{5,}$"))) {
        toast(context, "Некорректный номер телефона")
        return
    }

    val dialIntent = Intent(Intent.ACTION_DIAL).apply {
        data = "tel:${Uri.encode(text)}".toUri()
    }

    try {
        context.startActivity(dialIntent)
    } catch (e: ActivityNotFoundException) {
        toast(context, "Приложение для звонков не найдено")
    }
}

// ---------- 3. Системный Intent: поделиться ----------
private fun shareText(context: Context, rawText: String) {
    val text = rawText.trim()
    if (text.isEmpty()) {
        toast(context, "Нечего отправлять")
        return
    }

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(Intent.createChooser(sendIntent, "Поделиться через…"))
}

private fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MaterialTheme {
        MainScreen()
    }
}