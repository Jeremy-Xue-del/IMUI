package com.example.imui.friend.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imui.R
import com.example.imui.friend.model.FriendItem
import com.example.imui.friend.model.FriendItemDiffCallback
import com.example.imui.friend.viewholder.*

class FriendAdapter(
    private val onLikeClicked: (FriendItem) -> Unit = {},
    private val onCommentClicked: (FriendItem) -> Unit = {}
) : ListAdapter<FriendItem, RecyclerView.ViewHolder>(FriendItemDiffCallback()) {
    
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is FriendItem.TextPost -> VIEW_TYPE_TEXT_POST
            is FriendItem.ImagePost -> VIEW_TYPE_IMAGE_POST
            is FriendItem.VideoPost -> VIEW_TYPE_VIDEO_POST
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_TEXT_POST -> {
                val view = inflater.inflate(R.layout.item_friend_text, parent, false)
                TextPostViewHolder(view)
            }
            VIEW_TYPE_IMAGE_POST -> {
                val view = inflater.inflate(R.layout.item_friend_image, parent, false)
                ImagePostViewHolder(view)
            }
            VIEW_TYPE_VIDEO_POST -> {
                val view = inflater.inflate(R.layout.item_friend_video, parent, false)
                VideoPostViewHolder(view)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is TextPostViewHolder -> {
                holder.bind(item as FriendItem.TextPost)
                setupClickListeners(holder, item)
            }
            is ImagePostViewHolder -> {
                holder.bind(item as FriendItem.ImagePost)
                setupClickListeners(holder, item)
            }
            is VideoPostViewHolder -> {
                holder.bind(item as FriendItem.VideoPost)
                setupClickListeners(holder, item)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            // 局部刷新处理
            val payload = payloads[0]
            if (payload is Map<*, *>) {
                when (holder) {
                    is TextPostViewHolder -> {
                        val item = getItem(position) as FriendItem.TextPost
                        if (payload.containsKey("likes")) {
                            holder.updateLikes(item.likes)
                        }
                        if (payload.containsKey("comments")) {
                            holder.updateComments(item.comments)
                        }
                    }
                    is ImagePostViewHolder -> {
                        val item = getItem(position) as FriendItem.ImagePost
                        if (payload.containsKey("likes")) {
                            holder.updateLikes(item.likes)
                        }
                        if (payload.containsKey("comments")) {
                            holder.updateComments(item.comments)
                        }
                    }
                    is VideoPostViewHolder -> {
                        val item = getItem(position) as FriendItem.VideoPost
                        if (payload.containsKey("likes")) {
                            holder.updateLikes(item.likes)
                        }
                        if (payload.containsKey("comments")) {
                            holder.updateComments(item.comments)
                        }
                    }
                }
            } else {
                super.onBindViewHolder(holder, position, payloads)
            }
        }
    }

    private fun setupClickListeners(holder: BasePostViewHolder, item: FriendItem) {
        // 设置点赞点击事件
        holder.itemView.findViewById<View>(R.id.tv_likes)?.setOnClickListener {
            onLikeClicked(item)
        }
        
        // 设置评论点击事件
        holder.itemView.findViewById<View>(R.id.tv_comments)?.setOnClickListener {
            onCommentClicked(item)
        }
    }

    companion object {
        const val VIEW_TYPE_TEXT_POST = 1
        const val VIEW_TYPE_IMAGE_POST = 2
        const val VIEW_TYPE_VIDEO_POST = 3
    }
}