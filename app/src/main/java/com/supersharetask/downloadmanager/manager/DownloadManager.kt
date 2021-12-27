package com.supersharetask.downloadmanager.manager

import com.supersharetask.downloadmanager.data.DownloadItem

class DownloadManager(
) {

    // this method adds the download request.
    fun startDownload(
        // todo add parameters if required
    ): DownloadItem {

    }

    // This cancel the download by cancelling the job and removing its entry from
    // the in-memory map. This also deletes the file incomplete file from the Storage.
    fun cancelDownload(downloadItem: DownloadItem): Boolean {

    }
}
