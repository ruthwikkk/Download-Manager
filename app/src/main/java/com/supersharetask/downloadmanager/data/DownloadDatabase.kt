package com.supersharetask.downloadmanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [DownloadItem::class],
    version = 1,
    exportSchema = false
)
abstract class DownloadDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "download_item_db"
    }

    abstract fun downloadItemDao(): DownloadItemDao
}