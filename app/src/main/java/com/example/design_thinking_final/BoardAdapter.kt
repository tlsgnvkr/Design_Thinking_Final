package com.example.design_thinking_final

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BoardAdapter(val itemList: ArrayList<BoardItem>) :
    RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.email_item, parent, false)
        return BoardViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.email_title.text = itemList[position].title
        holder.email_sender.text = itemList[position].sender
        holder.email_content.text = itemList[position].content
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }


    inner class BoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val email_title = itemView.findViewById<TextView>(R.id.email_title)
        val email_sender = itemView.findViewById<TextView>(R.id.email_sender)
        val email_content = itemView.findViewById<TextView>(R.id.email_content)
    }
}