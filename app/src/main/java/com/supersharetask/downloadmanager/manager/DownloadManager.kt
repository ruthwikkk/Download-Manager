package com.supersharetask.downloadmanager.manager

import android.content.Context
import android.content.Intent
import android.util.Log
import com.supersharetask.downloadmanager.data.DownloadItem
import com.supersharetask.downloadmanager.data.DownloadStatus
import com.supersharetask.downloadmanager.repositories.DownloadsRepository
import com.supersharetask.downloadmanager.utils.ACTION_DOWNLOAD_START
import kotlinx.coroutines.*

class DownloadManager(val context: Context) {

    private val managerScope by lazy { CoroutineScope(Dispatchers.IO) }
    val repository = DownloadsRepository(context)
    // this method adds the download request.
    fun startDownload(
        url: String
    ): DownloadItem {
        val item = DownloadItem()
        item.url = url
        item.status = DownloadStatus.DEFAULT

        managerScope.launch {
            item.id = repository.insertDownloadItem(item).toInt()
            withContext(Dispatchers.Main){
                val intent = Intent(context, DownloadService::class.java)
                intent.action = ACTION_DOWNLOAD_START
                intent.putExtra("DATA", item)
                context.startForegroundService(intent)
            }
        }
        return item
    }

    // This cancel the download by cancelling the job and removing its entry from
    // the in-memory map. This also deletes the file incomplete file from the Storage.
    fun cancelDownload(downloadItem: DownloadItem): Boolean {

        val intent = Intent(context, DownloadService::class.java)
        intent.action = ACTION_DOWNLOAD_START
        intent.putExtra("DATA", downloadItem)
        context.startForegroundService(intent)

        return false
    }
}
