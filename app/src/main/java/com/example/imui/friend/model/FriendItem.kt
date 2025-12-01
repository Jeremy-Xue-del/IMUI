package com.example.imui.friend.model

sealed class FriendItem {
    abstract val id: Long

    abstract val username: String
    abstract val time: String
    abstract val content: String

    data class TextPost(
        override val id: Long,
        override val username: String,
        override val content: String,
        override val time: String,
        val avatarUrl: String?,
        val likes: Int,
        val comments: Int,

        ) : FriendItem()

    data class ImagePost(
        override val id: Long,
        override val username: String,
        override val content: String,
        override val time: String,

        val avatarUrl: String?,

        val imageUrl: String,
        val likes: Int,
        val comments: Int,
    ) : FriendItem()

    data class VideoPost(
        override val id: Long,
        override val username: String,
        override val content: String,
        override val time: String,
        val avatarUrl: String?,
        val videoThumbnailUrl: String,
        val videoUrl: String,
        val likes: Int,
        val comments: Int,
    ) : FriendItem()
}