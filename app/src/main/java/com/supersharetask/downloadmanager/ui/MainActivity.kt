package com.supersharetask.downloadmanager.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.*
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.supersharetask.downloadmanager.data.DownloadItem
import com.supersharetask.downloadmanager.databinding.ActivityMainBinding
import com.supersharetask.downloadmanager.manager.DownloadManager
import com.supersharetask.downloadmanager.utils.ACTION_BROADCAST_DOWNLOAD_PROGRESS
import com.supersharetask.downloadmanager.utils.PARAM_BROADCAST_DOWNLOAD_PROGRESS

class MainActivity : AppCompatActivity() {

   // private lateinit var downloadService: DownloadService
    private var isServiceBound = false

    private lateinit var binding: ActivityMainBinding

    private val viewModel: DownloadsViewModel by viewModels()
    var counter = 0

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
          //  val binder = service as DownloadService.LocalBinder
           // downloadService = binder.getService()
            isServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isServiceBound = false
        }
    }

    // Try downloading these links by passing them to `beginDownload()` and check the logs for streaming results.
    val imageUrl = "https://unsplash.com/photos/Yu2Bt4cjRxA/download?force=true"
    val videoUrl =
        "https://www.pexels.com/video/4706000/download/?search_query=&tracking_id=aw3934mxaif"
    val pdfUrl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()
        initReceiver()

        /*val downloadListAdapter = DownloadListAdapter(
            onPauseResumeClick = { downloadItem ->
                viewModel.onPauseResumeToggle(downloadItem)
            },
            onCancelDownloadClick = { downloadItem ->
                viewModel.onCancelDownload(downloadItem)
            }
        )*/

       // initRecyclerView(downloadListAdapter)
       // setupClickListeners()
    }

    /*private fun setupClickListeners() {
        binding.apply {
            btnDownload.setOnClickListener {
                val enteredUrl = etUrl.text.toString()
                it.hideKeyboard()
                beginDownload(enteredUrl)
            }
        }
    }

    private fun initRecyclerView(downloadListAdapter: DownloadListAdapter) {
        binding.rvDownloadItems.apply {
            adapter = downloadListAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }*/

    fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("imp", "name", importance)
            mChannel.description = "descriptionText"
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    private fun initReceiver() {
        val bm = LocalBroadcastManager.getInstance(this)

        val filterProgress = IntentFilter()
        filterProgress.addAction(ACTION_BROADCAST_DOWNLOAD_PROGRESS)
        bm.registerReceiver(progressBroadcastReceiver, filterProgress)
    }

    private val progressBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            try {
                if (intent.action == ACTION_BROADCAST_DOWNLOAD_PROGRESS) {
                    val item = intent.getParcelableExtra(PARAM_BROADCAST_DOWNLOAD_PROGRESS) as DownloadItem?
                    //todo update progress in UI
                }
            } catch (e: java.lang.Exception) {
            }
        }
    }

    override fun onDestroy() {
        val bm = LocalBroadcastManager.getInstance(this)
        bm.unregisterReceiver(progressBroadcastReceiver)
        super.onDestroy()
    }

}