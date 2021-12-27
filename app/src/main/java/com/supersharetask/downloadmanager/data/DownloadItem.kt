package com.supersharetask.downloadmanager.data

import androidx.room.Entity
import androidx.room.Ignore
import kotlinx.coroutines.flow.MutableStateFlow


@Entity(tableName = "download_items")

data class DownloadItem(
    val status: DownloadStatus
    // todo fill in with meaningful data
)