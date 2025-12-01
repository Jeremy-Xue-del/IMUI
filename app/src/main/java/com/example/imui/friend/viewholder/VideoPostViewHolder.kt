package com.example.imui.friend.viewholder

import android.view.View
import android.widget.ImageView
import com.example.imui.R
import com.example.imui.friend.model.FriendItem

class VideoPostViewHolder(itemView: View) : BasePostViewHolder(itemView) {
    private val videoThumbnail: ImageView = itemView.findViewById(R.id.iv_video_thumbnail)

    fun bind(videoPost: FriendItem.VideoPost) {
        bindBaseData(videoPost)
        
        // 在实际应用中，这里会加载真实视频缩略图
        videoThumbnail.setImageResource(R.drawable.icon_back)
    }
}