package com.example.design_thinking_final

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

class DateManagement {
    companion object {
        var thisYear : Int = 0
        var thisMonth : Int = 0
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun resetDate(){
        val nowDate = LocalDate.now()
        thisYear = nowDate.year
        thisMonth = nowDate.monthValue
    }
}