package com.supersharetask.downloadmanager.repositories

import com.supersharetask.downloadmanager.data.DownloadDatabase
import com.supersharetask.downloadmanager.data.DownloadItem
import kotlinx.coroutines.flow.Flow

class DownloadsRepository {
    private val db = DownloadDatabase
    private val downloadItemDao = db.downloadItemDao()

    fun getDownloadItems(): Flow<List<DownloadItem>> = downloadItemDao.getAllDownloadItems()

    suspend fun saveDownloadItem(downloadItem: DownloadItem) {
        downloadItemDao.insert(downloadItem)
    }
}