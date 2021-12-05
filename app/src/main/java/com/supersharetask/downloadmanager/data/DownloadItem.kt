package com.supersharetask.downloadmanager.data

import androidx.room.Entity
import androidx.room.Ignore
import kotlinx.coroutines.flow.MutableStateFlow


@Entity(tableName = "download_items")
data class DownloadItem(
    // todo fill in with meaninful data
) {
    @Ignore
    val progressStateFlow: MutableStateFlow<DownloadProgress?> = MutableStateFlow(null)

    data class DownloadProgress(
        val bytesDownloaded: Long = 0,
        val totalBytes: Long = 0,
        var status: DownloadStatus = DownloadStatus.PENDING
    )
}