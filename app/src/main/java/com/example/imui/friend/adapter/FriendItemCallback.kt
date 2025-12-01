package com.example.imui.friend.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.imui.friend.model.FriendItem

class FriendItemCallback : DiffUtil.ItemCallback<FriendItem>() {
    override fun areItemsTheSame(oldItem: FriendItem, newItem: FriendItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FriendItem, newItem: FriendItem): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: FriendItem, newItem: FriendItem): Any? {
        if (oldItem::class != newItem::class) return null

        return when {
            oldItem is FriendItem.TextPost && newItem is FriendItem.TextPost -> {
                val payloads = mutableMapOf<String, Any>()
                if (oldItem.likes != newItem.likes) {
                    payloads["likes"] = newItem.likes
                }
                if (oldItem.comments != newItem.comments) {
                    payloads["comments"] = newItem.comments
                }
                payloads.ifEmpty { null }
            }
            oldItem is FriendItem.ImagePost && newItem is FriendItem.ImagePost -> {
                val payloads = mutableMapOf<String, Any>()
                if (oldItem.likes != newItem.likes) {
                    payloads["likes"] = newItem.likes
                }
                if (oldItem.comments != newItem.comments) {
                    payloads["comments"] = newItem.comments
                }
                payloads.ifEmpty { null }
            }
            oldItem is FriendItem.VideoPost && newItem is FriendItem.VideoPost -> {
                val payloads = mutableMapOf<String, Any>()
                if (oldItem.likes != newItem.likes) {
                    payloads["likes"] = newItem.likes
                }
                if (oldItem.comments != newItem.comments) {
                    payloads["comments"] = newItem.comments
                }
                payloads.ifEmpty { null }
            }
            else -> null
        }
    }
}