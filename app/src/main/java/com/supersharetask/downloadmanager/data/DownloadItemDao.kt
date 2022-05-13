package com.supersharetask.downloadmanager.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DownloadItemDao {

    @Query("SELECT * FROM download_items")
    fun getAllDownloadItems(): Flow<List<DownloadItem>>

    @Query("SELECT * FROM download_items WHERE id=:id")
    fun getAllDownloadItem(id: Int): DownloadItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(downloadItem: DownloadItem): Long

    @Update
    suspend fun updateStatus(downloadItem: DownloadItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(downloadItems: List<DownloadItem>)
}