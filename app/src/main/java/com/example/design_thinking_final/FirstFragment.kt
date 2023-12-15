package com.example.design_thinking_final

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.room.Room
import com.example.design_thinking_final.databinding.FragmentFirstBinding
import com.google.type.DateTime
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dateBackward.setOnClickListener {
            DateManagement.dateBackward()
        }
        binding.dateForward.setOnClickListener {
            DateManagement.dateForward()
        }
        DateManagement.resetDate()

        val week = DateManagement.getFirstDay()

        for(i: Int in 0 until week){
            val resId = resources.getIdentifier("date_${i}","id", activity?.packageName)
            val tview = getView()?.findViewById<TextView>(resId)
            tview?.text = ""
        }
        for(i: Int in DateManagement.getMaxDay()+week .. 41){
            val resId = resources.getIdentifier("date_${i}","id", activity?.packageName)
            val tview = getView()?.findViewById<TextView>(resId)
            tview?.text = ""
        }

        val lastLine = getView()?.findViewById<LinearLayout>(R.id.date_lastline)
        if(DateManagement.getMaxDay()+week-1 >= 35) lastLine!!.visibility = View.VISIBLE
        else lastLine!!.visibility = View.INVISIBLE

        for(i: Int in 1..DateManagement.getMaxDay()){
            val resId = resources.getIdentifier("date_${i+week-1}","id", activity?.packageName)
            val tview = getView()?.findViewById<TextView>(resId)
            tview?.text = "$i"

//            val resId2 = resources.getIdentifier("date_content_${i+week-1}","id", activity?.packageName)
//            val lview = getView()?.findViewById<LinearLayout>(resId2)
//            lview?.setOnClickListener {
//
//            }
        }

        val yearView = getView()?.findViewById<TextView>(R.id.date_month)
        yearView!!.text = "${DateManagement.thisYear}년 ${DateManagement.thisMonth}월"

        val backView = getView()?.findViewById<TextView>(R.id.date_backward)
        val forView = getView()?.findViewById<TextView>(R.id.date_forward)

        backView!!.setOnClickListener {
            DateManagement.dateBackward()
            activity?.recreate()
        }
        forView!!.setOnClickListener {
            DateManagement.dateForward()
            activity?.recreate()
        }

        // database
        val db = Room.databaseBuilder(
            activity!!.applicationContext, AppDatabase::class.java, "database"
        ).allowMainThreadQueries().build()

        // db에 저장된 데이터 불러오기
        val data = db.dao().getAll()

        for(x: Entity in data){
//            Log.d("확인부분", "3차 성공")
//            Log.d("확인부분", "${x.startDay.year} ${x.startDay.month} ${x.startDay.day}")
//            Log.d("확인부분", "${x.endDay.year } ${x.endDay.month} ${x.endDay.day}")
//            Log.d("확인부분", "${DateManagement.thisYear } ${DateManagement.thisMonth} ${DateManagement.getThisDay()}")
//            val v = SimpleDateFormat("yyyyMMdd").parse("${DateManagement.thisYear}${DateManagement.thisMonth}${DateManagement.getThisDay()}");
//            Log.d("확인부분", v.toString())
//            Log.d("확인부분", "${v.year } ${v.month} ${v.day}")

            if(DateManagement.isInMonth(x)){
                Log.d("확인부분", "1차 성공")
//                if((x.repeat and 256) == 256){
//                    var startDate = x.startDay
//                    var endDate = x.endDay
//                    startDate.year = DateManagement.thisYear
//                    endDate.year = DateManagement.thisYear
//
//                    for(i: Int in 1..DateManagement.getMaxDay()){
//                        var nowDate: Date = SimpleDateFormat("yyyyMMdd").parse("${DateManagement.thisYear}${DateManagement.thisMonth}${i.toString(2)}")
//                        if(nowDate.before(startDate) && nowDate.after(endDate)){
//                            val resId = resources.getIdentifier("date_content_${i+week-1}","id", activity?.packageName)
//                            val view = getView()?.findViewById<LinearLayout>(resId)
//                            val addView = TextView(context)
//                            addView.text = x.title
//                            addView.isSingleLine = true
//                            val param: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//                            addView.layoutParams = param
//                            addView.setBackgroundColor(Color.parseColor(x.color))
//                            view?.addView(addView)
//                        }
//                    }
//                    continue
//                }


                var startDate = x.startDay
                var endDate = x.endDay

                for(i: Int in 1..DateManagement.getMaxDay()){
                    var nowDate: LocalDate = LocalDate.parse("${DateManagement.thisYear.toString().padStart(4, '0')}${DateManagement.thisMonth.toString().padStart(2, '0')}${i.toString().padStart(2, '0')}", DateTimeFormatter.ofPattern("yyyyMMdd"))
                    if(DateManagement.isInDay(startDate, endDate, nowDate)){
                        Log.d("확인부분", "2차 성공")
                        val resId = resources.getIdentifier("date_content_${i+week-1}","id", activity?.packageName)
                        val view = getView()?.findViewById<LinearLayout>(resId)
                        val addView = TextView(context)
                        addView.text = x.title
                        addView.isSingleLine = true
                        val param: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        addView.layoutParams = param
                        addView.setBackgroundColor(Color.parseColor(x.color))
                        view?.addView(addView)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}