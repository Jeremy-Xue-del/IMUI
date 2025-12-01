package com.example.imui.friend.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imui.R
import com.example.imui.friend.model.FriendItem
import com.example.imui.friend.viewholder.*

class FriendAdapter : ListAdapter<FriendItem, RecyclerView.ViewHolder>(FriendItemCallback()) {
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
        when (holder) {
            is TextPostViewHolder -> holder.bind(getItem(position) as FriendItem.TextPost)
            is ImagePostViewHolder -> holder.bind(getItem(position) as FriendItem.ImagePost)
            is VideoPostViewHolder -> holder.bind(getItem(position) as FriendItem.VideoPost)
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
                            //TODO 添加更新评论数的方法
                        }
                    }
                    is ImagePostViewHolder -> {
                        val item = getItem(position) as FriendItem.ImagePost
                        if (payload.containsKey("likes")) {
                            holder.updateLikes(item.likes)
                        }
                        if (payload.containsKey("comments")) {
                            //TODO 添加更新评论数的方法
                        }
                    }
                    is VideoPostViewHolder -> {
                        val item = getItem(position) as FriendItem.VideoPost
                        if (payload.containsKey("likes")) {
                            holder.updateLikes(item.likes)
                        }
                        if (payload.containsKey("comments")) {
                            //TODO 添加更新评论数的方法
                        }
                    }
                }
            } else {
                super.onBindViewHolder(holder, position, payloads)
            }
        }
    }

    companion object {
        const val VIEW_TYPE_TEXT_POST = 1
        const val VIEW_TYPE_IMAGE_POST = 2
        const val VIEW_TYPE_VIDEO_POST = 3
    }
}