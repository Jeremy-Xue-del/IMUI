package com.example.imui.friend.data

import com.example.imui.friend.model.FriendItem

/**
 * 朋友圈数据仓库接口
 */
interface FriendRepository {
    suspend fun getFriendPosts(): List<FriendItem>
    suspend fun likePost(postId: Long): Boolean
    suspend fun addComment(postId: Long, comment: String): Boolean
}

/**
 * 本地数据实现（用于演示）
 */
class LocalFriendRepository : FriendRepository {
    override suspend fun getFriendPosts(): List<FriendItem> {
        // 模拟网络延迟
        kotlinx.coroutines.delay(1000)
        
        val items = mutableListOf<FriendItem>()
        val usernames = listOf("张三", "李四", "王五", "赵六", "钱七")
        val contents = listOf(
            "今天天气真好，出去走走。",
            "新学了一道菜，味道不错！",
            "最近在看一本好书，推荐给大家。",
            "周末去爬山，风景美极了！",
            "工作中遇到一个技术难题，终于解决了。"
        )

        repeat(20) { index ->
            val username = usernames[index % usernames.size]
            val content = contents[index % contents.size]
            val likes = (0..100).random()
            val comments = (0..20).random()
            val time = "${(1..24).random()}小时前"

            when (index % 3) {
                0 -> {
                    // 文本帖子
                    items.add(
                        FriendItem.TextPost(
                            id = index.toLong(),
                            username = username,
                            avatarUrl = null,
                            content = content,
                            likes = likes,
                            comments = comments,
                            time = time
                        )
                    )
                }
                1 -> {
                    // 图片帖子
                    items.add(
                        FriendItem.ImagePost(
                            id = index.toLong(),
                            username = username,
                            avatarUrl = null,
                            content = content,
                            imageUrl = "",
                            likes = likes,
                            comments = comments,
                            time = time
                        )
                    )
                }
                2 -> {
                    // 视频帖子
                    items.add(
                        FriendItem.VideoPost(
                            id = index.toLong(),
                            username = username,
                            avatarUrl = null,
                            content = content,
                            videoThumbnailUrl = "",
                            videoUrl = "",
                            likes = likes,
                            comments = comments,
                            time = time
                        )
                    )
                }
            }
        }
        return items
    }

    override suspend fun likePost(postId: Long): Boolean {
        // 模拟网络请求
        kotlinx.coroutines.delay(300)
        return true
    }

    override suspend fun addComment(postId: Long, comment: String): Boolean {
        // 模拟网络请求
        kotlinx.coroutines.delay(300)
        return true
    }
}