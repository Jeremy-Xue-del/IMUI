package com.example.imui.friend.viewholder

import android.view.View
import com.example.imui.friend.model.FriendItem

class TextPostViewHolder(itemView: View) : BasePostViewHolder(itemView) {

    fun bind(textPost: FriendItem.TextPost) {
        bindBaseData(textPost)
    }
}