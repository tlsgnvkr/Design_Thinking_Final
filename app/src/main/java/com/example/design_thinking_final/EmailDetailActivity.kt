package com.example.design_thinking_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class EmailDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_detail)

        val title = intent.getStringExtra("title")
        val sender = intent.getStringExtra("sender")
        var content = intent.getStringExtra("content")
        if(content == "한국정보과학회 프로그래밍언어연구회에서 주최하는 2024 겨울학교에 여러분을...")
            content = "한국정보과학회 프로그래밍언어연구회에서 주최하는 2024 겨울학교에 여러분을 초대합니다. 프로그래밍언어연구회에서는 매년 최신 연구 동향 소개와 함께 참가자들이 교류할 수 있는 자리를 마련해 왔습니다. 이번 겨울학교는 \"사이좋게 지내자\"는 테마로, SIGPL에서 (자주) 못 모셨던 분들의 발표를 청해 듣고, 또 조별활동을 통해 (특히 학생) 회원간 교류를 증진하고자 합니다. 프로그래밍언어연구회 2024 겨울학교에 여러분의 많은 관심과 참여를 바랍니다."

        findViewById<TextView>(R.id.detail_title).text = title
        findViewById<TextView>(R.id.detail_sender).text = sender
        findViewById<TextView>(R.id.detail_content).text = content

        findViewById<ImageView>(R.id.detail_inbox).setOnClickListener {
            finish()
        }
        findViewById<ImageView>(R.id.detail_add).setOnClickListener{
            val intent = Intent(this@EmailDetailActivity, EditDateActivity::class.java)
            intent.putExtra("mode", "new")
            intent.putExtra("where", "email")
            intent.putExtra("title", title)
            intent.putExtra("content", content)
            startActivity(intent)
            finish()
        }
    }
}