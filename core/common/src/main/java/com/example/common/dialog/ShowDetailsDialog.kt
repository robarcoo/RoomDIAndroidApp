package com.example.common.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.Company
import com.example.model.FullVacancy
import com.example.model.Network
import com.example.repository.CompanyRepositoryImpl
import com.example.repository.VacancyRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun fullScreenDialog(screenType : Int, network: Network, id: Int) : Boolean {
    var showDialog by remember { mutableStateOf(true) }
    Dialog(onDismissRequest = { showDialog = false },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(0.dp)) {
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                when (screenType) {
                    1 -> CompanyDialog(network, id)
                    2 -> VacancyDialog(network, id)
                }
            }
        }
    }
    return showDialog
}

@Composable
fun CompanyDialog(network: Network, id : Int) {

    val companyRepository = CompanyRepositoryImpl(network)
    var responseText by remember { mutableStateOf("") }
    var companyDetails by remember { mutableStateOf(Company()) }
    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                companyDetails = companyRepository.getCompany(id)
            } catch (e: Exception) {
                responseText = "Error. This company isn't found."
            }
        }
    }
    if (companyDetails.id == 0) {
        Text (responseText)
    } else {
        Text(companyDetails.name)
        Text(companyDetails.activity)
        Text(companyDetails.contacts)
        Spacer(modifier = Modifier.height(50.dp))
        Text("List of vacancies")
        companyDetails.vacancies.forEach { vacancy ->
            var showVacancy by remember { mutableStateOf(false) }
            ClickableText(
                text = AnnotatedString(vacancy.profession),
                onClick = {
                    showVacancy = true
                }
            )

            if (showVacancy) {
                showVacancy = fullScreenDialog(screenType = 2, network = network, id = vacancy.id)
            }

            Text(vacancy.level)
            Text(vacancy.salary.toString())
            Spacer(modifier = Modifier.height(50.dp))
        }

    }
}

@Composable
fun VacancyDialog(network: Network, id : Int) {
    val vacancyRepository = VacancyRepositoryImpl(network)
    var responseText by remember { mutableStateOf("") }
    var vacancyDetails by remember { mutableStateOf(FullVacancy()) }
    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                vacancyDetails = vacancyRepository.getVacancyById(id)
            } catch (e: Exception) {
                responseText = "Error. This vacancy isn't found."
            }
        }
    }
    if (vacancyDetails.id == 0) {
        Text (responseText)
    } else {
        var showCompany by remember { mutableStateOf(false) }
        Text(vacancyDetails.name)
        Text("Level: ${vacancyDetails.level}")
        Text("Salary: ${vacancyDetails.salary}")
        Text("Field: ${vacancyDetails.field}")
        Text("Job description: ${vacancyDetails.description}")
        ClickableText(
            text = AnnotatedString(vacancyDetails.company),
            onClick = {
                showCompany = true
            }
        )
        if (showCompany) {
            showCompany  = fullScreenDialog(screenType = 1, network = network, id = vacancyDetails.companyid)
        }
        Text("Call now: ${vacancyDetails.phone}")
    }

}
