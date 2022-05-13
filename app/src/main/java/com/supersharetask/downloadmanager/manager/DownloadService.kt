package com.supersharetask.downloadmanager.manager

import android.app.Notification
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.supersharetask.downloadmanager.data.DownloadItem
import com.supersharetask.downloadmanager.data.DownloadStatus
import com.supersharetask.downloadmanager.repositories.DownloadsRepository
import com.supersharetask.downloadmanager.utils.ACTION_BROADCAST_DOWNLOAD_PROGRESS
import com.supersharetask.downloadmanager.utils.ACTION_DOWNLOAD_START
import com.supersharetask.downloadmanager.utils.ACTION_DOWNLOAD_STOP
import com.supersharetask.downloadmanager.utils.PARAM_BROADCAST_DOWNLOAD_PROGRESS
import kotlinx.coroutines.*

class DownloadService: Service() {

    private val serviceScope by lazy { CoroutineScope(Dispatchers.IO) }
    private var repository : DownloadsRepository? = null

    private lateinit var notificationManager: NotificationManager
    private lateinit var notification: Notification
    private var progressNotificationBuilder: NotificationCompat.Builder? = null

    private val map = HashMap<Int, Job>()
    private val progressMap = HashMap<Int, Int>()

    override fun onCreate() {

        super.onCreate()
        repository = DownloadsRepository(this)

        progressNotificationBuilder = NotificationCompat.Builder(applicationContext, "imp")
        notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(13572) // Remove download failed notifications

        notification = buildNotification(0, "Downloading...") ?: Notification()
        startForeground(13570, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let { intent->
            when(intent.action){
                ACTION_DOWNLOAD_START -> {
                    val item = intent.getParcelableExtra<DownloadItem>("DATA")
                    item?.let {
                        map[it.id] = startDownload(it)
                    }
                }
                ACTION_DOWNLOAD_STOP -> {
                    val item = intent.getParcelableExtra<DownloadItem>("DATA")
                    item?.let {
                        cancelDownload(it)
                    }
                }
                else -> {

                }
            }
        }
        return START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        serviceScope.cancel()
        super.onDestroy()
    }

    private fun startDownload(item: DownloadItem): Job {
        return serviceScope.launch {
            val downloader = Downloader()
            item.url?.let {
                repository?.updateDownloadItemStatus(item, DownloadStatus.DOWNLOADING)
                downloader.download(item, this@DownloadService) { item ->
                    broadcastProgress(item)
                    progressMap[item.id] = item.progress
                }
            }
            repository?.updateDownloadItemStatus(item, DownloadStatus.DOWNLOADED)
            progressMap.remove(item.id)
            checkIfAllCompleted()
        }
    }

    private fun cancelDownload(item: DownloadItem){
        map[item.id]?.cancel()
        serviceScope.launch {
            repository?.updateDownloadItemStatus(item, DownloadStatus.CANCELLED)
        }
    }

    private fun checkIfAllCompleted(){
        if (progressMap.isEmpty())
            stopSelf()
    }

    private fun broadcastProgress(item: DownloadItem){
        val intent = Intent(ACTION_BROADCAST_DOWNLOAD_PROGRESS)
        intent.putExtra(PARAM_BROADCAST_DOWNLOAD_PROGRESS, item)
        val bm = LocalBroadcastManager.getInstance(applicationContext)
        bm.sendBroadcast(intent)
    }

    private fun buildNotification(progress : Int, data : String?) : Notification?{

        progressNotificationBuilder?.apply {

            if(data.isNullOrEmpty())
                setContentTitle("Downloading Videos")
            else
                setContentTitle(data)
            setAutoCancel(true)
            setOngoing(true)
            setShowWhen(false)
            setOnlyAlertOnce(true)
            setContentText("$progress %")
            setProgress(100, progress, false)
        }

        return progressNotificationBuilder?.build()
    }
}