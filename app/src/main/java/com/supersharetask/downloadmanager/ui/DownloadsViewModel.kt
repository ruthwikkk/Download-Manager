package com.supersharetask.downloadmanager.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.supersharetask.downloadmanager.data.DownloadItem
import com.supersharetask.downloadmanager.data.DownloadStatus
import kotlinx.coroutines.launch
import com.supersharetask.downloadmanager.repositories.DownloadsRepository

class DownloadsViewModel(application: Application) : AndroidViewModel(application) {

    //val repository = DownloadsRepository()

    fun onCancelDownload(downloadItem: DownloadItem) {

    }

    fun onDownload(downloadItem: DownloadItem) {

    }
}
