package com.example.common.swap

import androidx.compose.runtime.snapshots.SnapshotStateList

fun <T> SnapshotStateList<T>.swap(allCompanies: Collection<T>) {
    clear()
    addAll(allCompanies)
}