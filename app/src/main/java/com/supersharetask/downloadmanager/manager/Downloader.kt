package com.supersharetask.downloadmanager.manager

import android.content.Context
import android.util.Log
import com.supersharetask.downloadmanager.data.DownloadItem
import com.supersharetask.downloadmanager.utils.getVideoDownloadFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.FileOutputStream

class Downloader {

    suspend fun download(item: DownloadItem, context: Context, onProgress: (item: DownloadItem) -> Unit) = withContext(Dispatchers.IO) {

        //todo download logic
        for(i in 1..10){
            delay(1000)
            onProgress(item).also {
                item.progress = i*10
            }
        }
    }
}