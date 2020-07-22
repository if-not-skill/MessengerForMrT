package com.example.messenger.models

import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.example.messenger.R
import kotlinx.android.synthetic.main.latest_massage_row.view.*

class LatestMessageRow(val chatMessage: ChatMessage) : Item<GroupieViewHolder>(){

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textview_message_latest_message.text = chatMessage.text
        viewHolder.itemView.textview_username_latest_message.text = chatMessage.fromId
    }

    override fun getLayout(): Int {
        return R.layout.latest_massage_row
    }
}