package com.example.design_thinking_final

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

class DateManagement {
    companion object {
        var thisYear : Int = 0
        var thisMonth : Int = 0
        private var dayMax = listOf<Int>(0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

        @RequiresApi(Build.VERSION_CODES.O)
        fun resetDate(){
            if ((thisYear != 0) and (thisMonth != 0)){
                return
            }
            val nowDate = LocalDate.now()
            thisYear = nowDate.year
            thisMonth = nowDate.monthValue
            reset4Year()
        }

        fun dateBackward(){
            thisMonth -= 1
            if(thisMonth == 0){
                thisYear -= 1
                thisMonth = 12
            }
            reset4Year()
        }
        fun dateForward(){
            thisMonth += 1
            if(thisMonth == 13){
                thisYear += 1
                thisMonth = 1
            }
            reset4Year()
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getFirstDay(): Int {
            val nowDate = LocalDate.of(thisYear, thisMonth, 1)
            return nowDate.dayOfWeek.value % 7
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getWeekOfDay(dateValue: Int): Int {
            val nowDate = LocalDate.of(thisYear, thisMonth, dateValue)
            return nowDate.dayOfWeek.value % 7
        }

        private fun reset4Year(){
            dayMax = if((thisYear % 4 == 0) and ((thisYear % 100 != 0 ) or (thisYear % 400 == 0))){
                listOf<Int>(0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
            } else{
                listOf<Int>(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
            }
        }
        fun getMaxDay(): Int{
            return dayMax[thisMonth]
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getThisDay(): Int{
            val nowDate = LocalDate.now()
            return nowDate.dayOfMonth
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun isInMonth(data: Entity): Boolean{
            var startDay = data.startDay
            var endDay = data.endDay

            var yearStart = startDay.year
            var yearEnd = endDay.year
            var monthStart = startDay.month.value
            var monthEnd = endDay.month.value

//            if((data.repeat and 256) == 256){
//                return monthStart <= thisMonth && thisMonth <= monthEnd
//            }
//            if(data.repeat > 0){
//                return true
//            }
            if(yearStart <= thisYear && thisYear <= yearEnd){
                if(monthStart <= thisMonth && thisMonth <= monthEnd){
                    return true
                }
            }

            return false
        }
        @RequiresApi(Build.VERSION_CODES.O)
        fun isInDay(start: LocalDate, end: LocalDate, now: LocalDate): Boolean{
            if(start.year <= now.year && now.year <= end.year){
                if(start.dayOfYear <= now.dayOfYear && now.dayOfYear <= end.dayOfYear){
                    return true
                }
                // 사실 예외가 있음 : 연도를 초월하는 경우
            }
            return false
        }
    }
}