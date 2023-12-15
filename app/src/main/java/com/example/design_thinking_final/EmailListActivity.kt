package com.example.design_thinking_final

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ClipDrawable.VERTICAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EmailListActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_list)

        findViewById<ImageView>(R.id.mail_inlist).setOnClickListener {
            findViewById<ImageView>(R.id.mail_inlist).setBackgroundColor(Color.parseColor("#d3d3d3"))
            findViewById<ImageView>(R.id.mail_inbox).setBackgroundColor(Color.parseColor("#3d3d3d"))

        }
        findViewById<ImageView>(R.id.mail_inbox).setOnClickListener {
            findViewById<ImageView>(R.id.mail_inbox).setBackgroundColor(Color.parseColor("#d3d3d3"))
            findViewById<ImageView>(R.id.mail_inlist).setBackgroundColor(Color.parseColor("#3d3d3d"))
        }

        val board = findViewById<RecyclerView>(R.id.mail_list)


        val itemList = ArrayList<BoardItem>()

        itemList.add(BoardItem("논문 관련 질문","김민재 [thisisidmail@gmail.com]","2023 한국컴퓨터종합학술대회 논문집에서 논문을 읽은 한 대학생입니다."))
        itemList.add(BoardItem("[17대 총장 선임 행정지원단] 총장 후보 숙의위원회 운영 결과","제17대 총장 선임 행정지원단 [17khup@khu.ac.kr]","결과 공고 드립니다."))
        itemList.add(BoardItem("[교수학습개발원] 겨울방학 프로그램 안내","교수학습개발원 [s.ctl@khu.ac.kr]","겨울방학 프로그램에 많은 참여 바랍니다."))
        itemList.add(BoardItem("[1차사전등록~12.29] 2024 프로그래밍언어연구회 겨울학교","한국정보과학회 [bhpark@kiise.or.kr]","한국정보과학회 프로그래밍언어연구회에서 주최하는 2024 겨울학교에 여러분을..."))

        val decoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        board.addItemDecoration(decoration)
        val boardAdapter = BoardAdapter(itemList)
        boardAdapter.notifyDataSetChanged()
        board.adapter = boardAdapter
        board.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}