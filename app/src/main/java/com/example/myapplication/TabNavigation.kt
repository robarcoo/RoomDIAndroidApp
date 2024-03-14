package com.example.myapplication
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import CandidateScreen
import com.example.common.di.DaggerNetworkModuleComponent
import com.example.common.di.NetworkModuleComponent
import com.example.common.event.CandidateEvent
import com.example.common.viewmodel.CandidateState
import com.example.companies.CompaniesScreen
import com.example.model.Network
import com.example.vacancies.VacanciesScreen


@Composable
fun TabRowScreen(state: CandidateState,
                 onEvent: (CandidateEvent) -> Unit) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val networkComponent: NetworkModuleComponent = DaggerNetworkModuleComponent.create()
    val network: Network = networkComponent.provideNetwork()
    val tabs = listOf("Companies", "Vacancies", "Candidates")


    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed {
                    index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> CompaniesScreen(network)
            1 -> VacanciesScreen(network)
            2 -> CandidateScreen(state, onEvent)
        }
    }
}