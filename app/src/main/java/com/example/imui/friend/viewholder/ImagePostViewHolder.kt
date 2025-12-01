package com.example.imui.friend.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imui.R
import com.example.imui.friend.model.FriendItem

class ImagePostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val avatarImage: ImageView = itemView.findViewById(R.id.iv_avatar)
    private val usernameText: TextView = itemView.findViewById(R.id.tv_username)
    private val timeText: TextView = itemView.findViewById(R.id.tv_time)
    private val contentText: TextView = itemView.findViewById(R.id.tv_content)
    private val postImage: ImageView = itemView.findViewById(R.id.iv_image)
    private val likesText: TextView = itemView.findViewById(R.id.tv_likes)
    private val commentsText: TextView = itemView.findViewById(R.id.tv_comments)

    fun bind(imagePost: FriendItem.ImagePost) {
        usernameText.text = imagePost.username
        timeText.text = imagePost.time
        contentText.text = imagePost.content
        likesText.text = "${imagePost.likes} 赞"
        commentsText.text = "${imagePost.comments} 评论"
        
        // 在实际应用中，这里会加载真实头像和图片
        avatarImage.setImageResource(R.drawable.icon_back)
        postImage.setImageResource(R.drawable.icon_back)
    }
    
    fun updateLikes(likes: Int) {
        likesText.text = "$likes 赞"
    }
}