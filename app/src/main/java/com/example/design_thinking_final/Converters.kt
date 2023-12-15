package com.example.design_thinking_final

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Converters {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it, DateTimeFormatter.ofPattern("yyyy-MM-dd")) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): String? {
        return date?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }
}