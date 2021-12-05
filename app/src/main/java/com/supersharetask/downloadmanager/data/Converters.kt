package com.supersharetask.downloadmanager.data

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromDownloadState(status: DownloadStatus): Int = status.value

    @TypeConverter
    fun toDownloadState(value: Int): DownloadStatus = when (value) {
        1 -> DownloadStatus.PENDING
        2 -> DownloadStatus.DOWNLOADING
        3 -> DownloadStatus.PAUSED
        4 -> DownloadStatus.COMPLETED
        else -> DownloadStatus.CANCELLED
    }
}