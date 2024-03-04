package com.example.companies

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.common.di.DaggerNetworkModuleComponent
import com.example.common.di.NetworkModuleComponent
import com.example.common.dialog.fullScreenDialog
import com.example.common.swap.swap
import com.example.model.Company
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.retrofit.CompanyApi
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import com.example.di.NetworkModule
import com.example.model.FullVacancy
import com.example.model.Network
import com.example.repository.CompanyRepositoryImpl
import com.example.repository.VacancyRepositoryImpl


@Composable
fun CompaniesScreen(network : Network) {
    val companyRepository = CompanyRepositoryImpl(network)
    val company = remember { mutableStateListOf<Company>() }
    var responseText by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                company.swap(companyRepository.getAllCompanies())
            } catch (e : Exception) {
                responseText = "Error. No companies found"
            }
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        if (company.isNotEmpty()) {
            company.forEach { company ->
                var showDialog by remember { mutableStateOf(false) }
                ClickableText(
                    text = AnnotatedString(company.name),
                    onClick = {
                        showDialog = true
                    }
                )
                Text(text = "Field: ${company.activity}")
                Spacer(modifier = Modifier.height(50.dp))
                if (showDialog) {
                    showDialog = fullScreenDialog(1, network, company.id)
                }

            }
        } else {
            Text(text = responseText)
        }
    }

}





