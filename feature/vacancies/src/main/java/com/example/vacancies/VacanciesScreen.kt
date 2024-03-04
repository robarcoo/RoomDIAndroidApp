package com.example.vacancies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.common.dialog.fullScreenDialog
import com.example.common.swap.swap
import com.example.model.Network
import com.example.model.Vacancy
import com.example.repository.VacancyRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun VacanciesScreen(network: Network) {
    val vacancyRepository = VacancyRepositoryImpl(network)
    val vacancy = remember { mutableStateListOf<Vacancy>() }

    var responseText by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                vacancy.swap(vacancyRepository.getAllVacancies())
            } catch (e : Exception) {
                responseText = "Error. No vacancies found"
            }
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        if (vacancy.isNotEmpty()) {
            vacancy.forEach { vacancy ->
                var showVacancyDetails by remember { mutableStateOf(false) }
                ClickableText(
                    text = AnnotatedString(vacancy.name),
                    onClick = {
                        showVacancyDetails = true
                    }
                )
                if (showVacancyDetails) {
                    showVacancyDetails = fullScreenDialog(2, network, vacancy.id)
                }
                Text(text = "Level: ${vacancy.level}")
                Text(text = "Salary: ${vacancy.salary}")
                Text(text = "Company: ${vacancy.company}")
                Spacer(modifier = Modifier.height(50.dp))
            }
        } else {
            Text(text = responseText)
        }
    }

}




