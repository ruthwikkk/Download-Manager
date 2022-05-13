package com.supersharetask.downloadmanager.utils

import android.content.Context
import android.os.Environment
import java.io.File
import java.math.RoundingMode
import java.text.DecimalFormat


private const val MEGA_BYTES_MULTIPLIER = 0.000001
private const val DECIMAL_PERCENT_FORMAT = "#.##"

fun getRandomFileName(length: Int): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}

/*fun getDownloadPercentage(progress: DownloadItem.DownloadProgress?) =
    progress?.let {
        ((progress.bytesDownloaded / progress.totalBytes.toDouble()) * 100).toInt()
    }.toString().plus("%")*/

val percentageFormat =
    DecimalFormat(DECIMAL_PERCENT_FORMAT).apply { roundingMode = RoundingMode.CEILING }

fun getDisplayPercentage(bytesRead: Long, totalBytes: Long): String =
    percentageFormat.format((bytesRead.toDouble() / totalBytes.toDouble()) * 100)

fun convertBytesToMB(bytes: Long): String =
    percentageFormat.format(bytes * MEGA_BYTES_MULTIPLIER)

/*
fun getDownloadProgress(downloadProgress: DownloadItem.DownloadProgress?) =
    downloadProgress?.let {
        "${convertBytesToMB(downloadProgress.bytesDownloaded)}MB / ${
            convertBytesToMB(
                downloadProgress.totalBytes
            )
        }MB"
    } ?: ""*/

fun getVideoDownloadFile(context: Context, fileName: String): File {
    val baseDirVideo = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            .toString() + File.separator + "PDF" + File.separator)
    if (!baseDirVideo.exists()) {
        baseDirVideo.mkdir()
    }

    val directory = File(baseDirVideo.absolutePath)
    if (!directory.exists()) {
        directory.mkdir()
    }
    return File(directory.absolutePath + File.separator + fileName + ".pdf")
}
