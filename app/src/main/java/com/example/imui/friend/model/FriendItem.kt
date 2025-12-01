package com.example.imui.friend.model

sealed class FriendItem {
    abstract val id: Long

    data class TextPost(
        override val id: Long,
        val username: String,
        val avatarUrl: String?,
        val content: String,
        val likes: Int,
        val comments: Int,
        val time: String
    ) : FriendItem()

    data class ImagePost(
        override val id: Long,
        val username: String,
        val avatarUrl: String?,
        val content: String,
        val imageUrl: String,
        val likes: Int,
        val comments: Int,
        val time: String
    ) : FriendItem()

    data class VideoPost(
        override val id: Long,
        val username: String,
        val avatarUrl: String?,
        val content: String,
        val videoThumbnailUrl: String,
        val videoUrl: String,
        val likes: Int,
        val comments: Int,
        val time: String
    ) : FriendItem()
}