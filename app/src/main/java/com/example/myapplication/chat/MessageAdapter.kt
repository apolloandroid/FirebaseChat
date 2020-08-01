package com.example.myapplication.chat

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.repository.model.Message
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.github.library.bubbleview.BubbleTextView
import com.google.firebase.database.FirebaseDatabase

object MessageAdapter : FirebaseRecyclerAdapter<Message, MessageAdapter.MessageViewHolder>(
    Message::class.java,
    R.layout.message_list_item,
    MessageViewHolder::class.java,
    FirebaseDatabase.getInstance().reference
) {
    override fun populateViewHolder(
        viewHolder: MessageViewHolder,
        model: Message,
        position: Int
    ) {
        with(viewHolder) {
            messUser.text = model.userName
            messTime.text = android.text.format.DateFormat.format(
                "dd-MM-yyyy HH:mm:ss",
                model.messageTime
            )
            messText.text = model.textMessage

//            if (messUser.text=="hmelevskih94@gmail.com") messText.setBackgroundColor(#03DAC5)
//            messText.setBackgroundColor(Color.parseColor("#03DAC5"))
        }
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messUser: TextView = itemView.findViewById(R.id.text_message_user)
        val messTime: TextView = itemView.findViewById(R.id.text_message_time)
        val messText: BubbleTextView = itemView.findViewById(R.id.text_message_text)
    }
}

