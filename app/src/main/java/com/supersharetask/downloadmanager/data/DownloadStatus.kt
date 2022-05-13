package com.supersharetask.downloadmanager.data

import androidx.room.TypeConverter

enum class DownloadStatus(val code: Int) {
    DEFAULT(0),
    DOWNLOADING(1),
    DOWNLOADED(2),
    ERROR(3),
    CANCELLED(4),
    QUEUED(5),
    PAUSE(6),
    RESTART(7);

    companion object {
        @TypeConverter
        fun getState(numeral: Int?): DownloadStatus? {
            if (numeral == null) {
                return DEFAULT
            }
            for (state in values()) {
                if (state.code == numeral) {
                    return state
                }
            }
            return null
        }
    }
}