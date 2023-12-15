package com.example.design_thinking_final

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Entity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var title: String = "",
    var startDay: LocalDate,
    var endDay: LocalDate,
    var section: Int = 0, // 1 : e-campus, 2 : email, 0 : another
    var allDay: Boolean = true,
    var place: String = "",
    var alarm: Int = 0, // 1 : now, 2 : 10min, 3 : 30min, 4 : 1h, 5 : 1d
//    var repeat : Int = 0, // 1, 2, 4, 8, 16, 32, 64 : 일~토, 128 : 매월 256 : 매년
    var detail: String = "",
    var memo: String = "",
    var color: String = "#A8C879"
)