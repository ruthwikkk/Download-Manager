package com.supersharetask.downloadmanager.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.MutableStateFlow


@Entity(tableName = "download_items", indices = [Index(value = ["url"], unique = true)])

class DownloadItem() : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var status: DownloadStatus? = DownloadStatus.DEFAULT

    var url: String? = null

    @Ignore
    var progress: Int = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        status = DownloadStatus.getState(parcel.readInt())
        url = parcel.readString()
        progress = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(status?.code ?: 0)
        parcel.writeString(url)
        parcel.writeInt(progress)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DownloadItem> {
        override fun createFromParcel(parcel: Parcel): DownloadItem {
            return DownloadItem(parcel)
        }

        override fun newArray(size: Int): Array<DownloadItem?> {
            return arrayOfNulls(size)
        }
    }
}