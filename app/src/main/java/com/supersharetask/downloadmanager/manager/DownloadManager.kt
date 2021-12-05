package com.supersharetask.downloadmanager.manager

import android.content.Context
import com.supersharetask.downloadmanager.data.DownloadItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow

class DownloadManager(
    private val context: Context,
    private val maxRetry: Int = 3,
    private val downloadScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
) {

    private val downloadMap: MutableMap<String, Pair<Job, MutableStateFlow<DownloadItem.DownloadProgress?>>> =
        mutableMapOf()


    // Creates a new download task
    fun createDownloadTask(
        url: String,
        onProgress: (DownloadItem) -> Unit = { },
        onError: (t: Throwable) -> Unit = { },
        onCompletion: (isCompleted: Boolean) -> Unit = { }
    ): DownloadItem {

        return startDownload(
            downloadItem = DownloadItem(url = url),
            onProgress = onProgress,
            onError = onError,
            onCompletion = onCompletion
        )
    }

    private fun startDownload(
        downloadItem: DownloadItem,
        onProgress: (DownloadItem) -> Unit,
        onError: (t: Throwable) -> Unit,
        onCompletion: (isCompleted: Boolean) -> Unit
    ): DownloadItem {

    }

    // This cancel the download by cancelling the job and removing its entry from
    // the in-memory map. This also deletes the file incomplete file from the Storage.
    fun cancelDownload(downloadItem: DownloadItem): Boolean {

    }
}
