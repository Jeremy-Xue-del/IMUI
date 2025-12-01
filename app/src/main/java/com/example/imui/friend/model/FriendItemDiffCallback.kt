package com.example.imui.friend.model

import androidx.recyclerview.widget.DiffUtil

/**
 * FriendItem DiffUtil 回调，用于优化列表更新
 */
class FriendItemDiffCallback : DiffUtil.ItemCallback<FriendItem>() {
    override fun areItemsTheSame(oldItem: FriendItem, newItem: FriendItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FriendItem, newItem: FriendItem): Boolean {
        return when {
            oldItem is FriendItem.TextPost && newItem is FriendItem.TextPost -> {
                oldItem.username == newItem.username &&
                oldItem.content == newItem.content &&
                oldItem.likes == newItem.likes &&
                oldItem.comments == newItem.comments &&
                oldItem.time == newItem.time
            }
            oldItem is FriendItem.ImagePost && newItem is FriendItem.ImagePost -> {
                oldItem.username == newItem.username &&
                oldItem.content == newItem.content &&
                oldItem.imageUrl == newItem.imageUrl &&
                oldItem.likes == newItem.likes &&
                oldItem.comments == newItem.comments &&
                oldItem.time == newItem.time
            }
            oldItem is FriendItem.VideoPost && newItem is FriendItem.VideoPost -> {
                oldItem.username == newItem.username &&
                oldItem.content == newItem.content &&
                oldItem.videoThumbnailUrl == newItem.videoThumbnailUrl &&
                oldItem.likes == newItem.likes &&
                oldItem.comments == newItem.comments &&
                oldItem.time == newItem.time
            }
            else -> false
        }
    }

    override fun getChangePayload(oldItem: FriendItem, newItem: FriendItem): Any? {
        val diffBundle = mutableMapOf<String, Any>()

        when {
            oldItem is FriendItem.TextPost && newItem is FriendItem.TextPost -> {
                if (oldItem.likes != newItem.likes) {
                    diffBundle["likes"] = newItem.likes
                }
                if (oldItem.comments != newItem.comments) {
                    diffBundle["comments"] = newItem.comments
                }
            }
            oldItem is FriendItem.ImagePost && newItem is FriendItem.ImagePost -> {
                if (oldItem.likes != newItem.likes) {
                    diffBundle["likes"] = newItem.likes
                }
                if (oldItem.comments != newItem.comments) {
                    diffBundle["comments"] = newItem.comments
                }
            }
            oldItem is FriendItem.VideoPost && newItem is FriendItem.VideoPost -> {
                if (oldItem.likes != newItem.likes) {
                    diffBundle["likes"] = newItem.likes
                }
                if (oldItem.comments != newItem.comments) {
                    diffBundle["comments"] = newItem.comments
                }
            }
        }

        return diffBundle.ifEmpty { null }
    }
}