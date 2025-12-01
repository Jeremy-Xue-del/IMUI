package com.example.imui.friend.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imui.R
import com.example.imui.friend.model.FriendItem

/**
 * 朋友圈动态ViewHolder基类，封装公共功能
 */
abstract class BasePostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    protected val avatarImage: ImageView = itemView.findViewById(R.id.iv_avatar)
    protected val usernameText: TextView = itemView.findViewById(R.id.tv_username)
    protected val timeText: TextView = itemView.findViewById(R.id.tv_time)
    protected val contentText: TextView = itemView.findViewById(R.id.tv_content)
    protected val likesText: TextView = itemView.findViewById(R.id.tv_likes)
    protected val commentsText: TextView = itemView.findViewById(R.id.tv_comments)

    /**
     * 绑定基础数据
     */
    protected fun bindBaseData(item: FriendItem) {
        usernameText.text = item.username
        timeText.text = item.time
        contentText.text = item.content
        
        when (item) {
            is FriendItem.TextPost -> {
                likesText.text = "${item.likes} 赞"
                commentsText.text = "${item.comments} 评论"
            }
            is FriendItem.ImagePost -> {
                likesText.text = "${item.likes} 赞"
                commentsText.text = "${item.comments} 评论"
            }
            is FriendItem.VideoPost -> {
                likesText.text = "${item.likes} 赞"
                commentsText.text = "${item.comments} 评论"
            }
        }
        
        // 实际项目中应该使用图片加载库如Glide或Picasso加载真实图片
        avatarImage.setImageResource(R.drawable.icon_back)
    }
    
    /**
     * 更新点赞数显示
     */
    fun updateLikes(likes: Int) {
        likesText.text = "$likes 赞"
    }
    
    /**
     * 更新评论数显示
     */
    fun updateComments(comments: Int) {
        commentsText.text = "$comments 评论"
    }
}