package com.example.design_thinking_final

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.room.Room
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

class EditDateActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_date)

        val db = Room.databaseBuilder(
            applicationContext, AppDatabase::class.java, "database"
        ).allowMainThreadQueries().build()

        var mode = 0
            // 1 : e-campus, 2 : email, 0 : another
        val modedata = intent.getStringExtra("mode")
        if(modedata=="new"){
            val wheredata = intent.getStringExtra("where")
            if(wheredata == "email") mode=2
            else if(wheredata == "campus") mode=1
            else mode=0
        } else if(modedata == "edit"){
            val id = intent.getIntExtra("id", 0)
            val data = db.dao().getDataById(id)
        }

         // 1 : now, 2 : 10min, 3 : 30min, 4 : 1h, 5 : 1d
        var spinnerData = listOf("알림없음", "정시에", "10분 전", "30분 전", "1시간 전", "1일 전")
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinnerData)
        findViewById<Spinner>(R.id.editdate_alarm).adapter = adapter

        var startDay = LocalDate.parse("${DateManagement.thisYear.toString().padStart(4, '0')}${DateManagement.thisMonth.toString().padStart(2, '0')}${DateManagement.getThisDay().toString().padStart(2, '0')}", DateTimeFormatter.ofPattern("yyyyMMdd"))

        var endDay = LocalDate.parse("${DateManagement.thisYear.toString().padStart(4, '0')}${DateManagement.thisMonth.toString().padStart(2, '0')}${DateManagement.getThisDay().toString().padStart(2, '0')}", DateTimeFormatter.ofPattern("yyyyMMdd"))

        var color = when (mode) {
            0 -> {
                "#A8C879"
            }
            1 -> {
                "#EDAA7D"
            }
            else -> {
                "#96B1D0"
            }
        }

        if(mode==2){
            findViewById<EditText>(R.id.editdate_title).setText(intent.getStringExtra("title"))
            findViewById<EditText>(R.id.editdate_logic).setText(intent.getStringExtra("content"))
        }

        findViewById<TextView>(R.id.editdate_startday).text = startDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        findViewById<TextView>(R.id.editdate_endday).text = endDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        findViewById<TextView>(R.id.editdate_startday).setOnClickListener {
            val cal = Calendar.getInstance()    //캘린더뷰 만들기
            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                startDay = LocalDate.parse("${year.toString().padStart(4, '0')}${(1+month).toString().padStart(2, '0')}${dayOfMonth.toString().padStart(2, '0')}", DateTimeFormatter.ofPattern("yyyyMMdd"))
                findViewById<TextView>(R.id.editdate_startday).text = startDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                if(endDay.isBefore(startDay)) endDay = startDay
                findViewById<TextView>(R.id.editdate_endday).text = endDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

            }
            DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()

        }
        findViewById<TextView>(R.id.editdate_endday).setOnClickListener {
            val cal = Calendar.getInstance()    //캘린더뷰 만들기
            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                endDay = LocalDate.parse("${year.toString().padStart(4, '0')}${(1+month).toString().padStart(2, '0')}${dayOfMonth.toString().padStart(2, '0')}", DateTimeFormatter.ofPattern("yyyyMMdd"))
                findViewById<TextView>(R.id.editdate_endday).text = endDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                if(endDay.isBefore(startDay)) startDay = endDay
                findViewById<TextView>(R.id.editdate_startday).text = startDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

            }
            DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        findViewById<ImageView>(R.id.editdate_push).setOnClickListener{

            val entity = Entity(
                title = findViewById<EditText>(R.id.editdate_title).text.toString(),
                startDay = startDay,
                endDay = endDay,
                section = mode,
                allDay = findViewById<CheckBox>(R.id.editdate_allday).isChecked,
                place = findViewById<EditText>(R.id.editdate_location).text.toString(),
                alarm = findViewById<Spinner>(R.id.editdate_alarm).selectedItemPosition,
                detail = findViewById<EditText>(R.id.editdate_logic).text.toString(),
                memo = findViewById<EditText>(R.id.editdate_memo).text.toString(),
                color = color
            )

            db.dao().insert(entity)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}