package com.example.lab09

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ScreenQuotes(navController: NavHostController, servicio: QuoteApiService) {
    var listaQuotes = remember { mutableStateListOf<QuoteModel>() }

    LaunchedEffect(Unit) {
        val response = servicio.getQuotes()
        listaQuotes.addAll(response.quotes)
    }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        items(listaQuotes) { quote ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(0.85f)) {
                        Text(text = "\"${quote.quote}\"", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "- ${quote.author}", color = MaterialTheme.colorScheme.primary)
                    }
                    IconButton(
                        onClick = { navController.navigate("quotesVer/${quote.id}") },
                        modifier = Modifier.weight(0.15f)
                    ) {
                        Icon(imageVector = Icons.Outlined.Visibility, contentDescription = "Ver detalle")
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenQuoteDetail(navController: NavHostController, servicio: QuoteApiService, id: Int) {
    var quote by remember { mutableStateOf<QuoteModel?>(null) }

    LaunchedEffect(Unit) {
        quote = servicio.getQuoteById(id)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        if (quote != null) {
            OutlinedTextField(
                value = quote!!.id.toString(),
                onValueChange = {},
                label = { Text("ID") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = quote!!.quote,
                onValueChange = {},
                label = { Text("Quote") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = quote!!.author,
                onValueChange = {},
                label = { Text("Author") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}