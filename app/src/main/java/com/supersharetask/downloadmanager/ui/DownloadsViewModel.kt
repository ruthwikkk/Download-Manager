package com.supersharetask.downloadmanager.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.supersharetask.downloadmanager.data.DownloadItem
import com.supersharetask.downloadmanager.data.DownloadStatus
import kotlinx.coroutines.launch
import com.supersharetask.downloadmanager.repositories.DownloadsRepository

class DownloadsViewModel(application: Application) : AndroidViewModel(application) {

    val repository = DownloadsRepository()
    val downloadItems = repository.getDownloadItems()

    fun onPauseResumeToggle(downloadItem: DownloadItem) {
        downloadItem.progress?.status = DownloadStatus.PAUSED
        viewModelScope.launch {
            repository.saveDownloadItem(downloadItem)
        }
    }

    fun onCancelDownload(downloadItem: DownloadItem) {
        downloadItem.progress?.status = DownloadStatus.CANCELLED
        viewModelScope.launch {
            repository.saveDownloadItem(downloadItem)
        }
    }

    fun onDownload(downloadItem: DownloadItem) {
        viewModelScope.launch {
            repository.saveDownloadItem(downloadItem)
        }
    }
}
