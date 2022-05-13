package com.supersharetask.downloadmanager.repositories

import android.content.Context
import com.supersharetask.downloadmanager.data.DownloadDatabase
import com.supersharetask.downloadmanager.data.DownloadItem
import com.supersharetask.downloadmanager.data.DownloadStatus
import kotlinx.coroutines.flow.Flow

class DownloadsRepository(val context: Context) {

    val database = DownloadDatabase.getDatabase(context)

    suspend fun insertDownloadItem(item: DownloadItem): Long{
        return database.downloadItemDao().insert(item)
    }

    suspend fun updateDownloadItemStatus(item: DownloadItem, status: DownloadStatus){
        item.status = status
        database.downloadItemDao().updateStatus(item)
    }

    fun getDownloadItem(id: Int): DownloadItem{
        return database.downloadItemDao().getAllDownloadItem(id)
    }
}