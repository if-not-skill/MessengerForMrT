package com.example.messenger.models

import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.example.messenger.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.latest_massage_row.view.*

class LatestMessageRow(val chatMessage: ChatMessage) : Item<GroupieViewHolder>(){

    var chatPartnerUser : User? = null

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        var startMessage = ""
        val chatPartnerId : String

        if(chatMessage.fromId == FirebaseAuth.getInstance().uid){
            chatPartnerId = chatMessage.toId
            startMessage = "You: "
        }else{
            chatPartnerId = chatMessage.fromId
        }

        viewHolder.itemView.textview_message_latest_message.text = (startMessage + chatMessage.text)

        val ref = FirebaseDatabase.getInstance().getReference("/users/$chatPartnerId")
        ref.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                chatPartnerUser = snapshot.getValue(User::class.java)
                viewHolder.itemView.textview_username_latest_message.text = chatPartnerUser?.username

                val targetImageView = viewHolder.itemView.imageview_latest_message
                Picasso.get().load(chatPartnerUser?.profileImageUrl).into(targetImageView)
            }

            override fun onCancelled(error: DatabaseError) {}
        })

    }

    override fun getLayout(): Int {
        return R.layout.latest_massage_row
    }
}