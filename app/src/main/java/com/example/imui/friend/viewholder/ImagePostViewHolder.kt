package com.example.imui.friend.viewholder

import android.view.View
import android.widget.ImageView
import com.example.imui.R
import com.example.imui.friend.model.FriendItem

class ImagePostViewHolder(itemView: View) : BasePostViewHolder(itemView) {
    private val postImage: ImageView = itemView.findViewById(R.id.iv_image)

    fun bind(imagePost: FriendItem.ImagePost) {
        bindBaseData(imagePost)
        
        // 在实际应用中，这里会加载真实图片
        postImage.setImageResource(R.drawable.icon_back)
    }
}