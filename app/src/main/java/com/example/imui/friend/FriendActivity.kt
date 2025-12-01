package com.example.imui.friend

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imui.databinding.ActivityFriendBinding
import com.example.imui.friend.adapter.FriendAdapter
import com.example.imui.friend.decoration.FriendItemDecoration
import com.example.imui.friend.model.FriendItem
import java.util.Random

class FriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFriendBinding
    private lateinit var adapter: FriendAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupRecyclerView()
        loadSampleData()
        
        binding.back.setOnClickListener {
            finish()
        }
    }
    
    private fun setupRecyclerView() {
        adapter = FriendAdapter()
        
        binding.friendList.apply {
            layoutManager = LinearLayoutManager(this@FriendActivity)
            adapter = this@FriendActivity.adapter
            
            // 添加分割线
            addItemDecoration(FriendItemDecoration(1))
            
            // 设置复用池大小（针对不同类型的ViewHolder）
            val recycledViewPool = recycledViewPool
            recycledViewPool.setMaxRecycledViews(FriendAdapter.VIEW_TYPE_TEXT_POST, 5)
            recycledViewPool.setMaxRecycledViews(FriendAdapter.VIEW_TYPE_IMAGE_POST, 5)
            recycledViewPool.setMaxRecycledViews(FriendAdapter.VIEW_TYPE_VIDEO_POST, 5)
            
            // 启用预取功能提高滚动性能
            layoutManager?.isItemPrefetchEnabled = true
        }
    }
    
    private fun loadSampleData() {
        val items = mutableListOf<FriendItem>()
        
        val usernames = listOf("张三", "李四", "王五", "赵六", "钱七")
        val contents = listOf(
            "今天天气真好，出去走走。",
            "新学了一道菜，味道不错！",
            "最近在看一本好书，推荐给大家。",
            "周末去爬山，风景美极了！",
            "工作中遇到一个技术难题，终于解决了。"
        )
        
        val random = Random()
        
        repeat(20) { index ->
            val username = usernames[random.nextInt(usernames.size)]
            val content = contents[random.nextInt(contents.size)]
            val likes = random.nextInt(100)
            val comments = random.nextInt(20)
            val time = "${random.nextInt(23) + 1}小时前"
            
            when (index % 4) {
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
                3 -> {
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
            }
        }
        
        adapter.submitList(items)
        
        // 模拟点赞功能
        binding.friendList.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                
                // 模拟用户交互，随机增加某个item的点赞数
                if (dy > 0 && random.nextInt(100) < 5) { // 有一定概率触发点赞
                    val currentList = adapter.currentList.toMutableList()
                    if (currentList.isNotEmpty()) {
                        val position = random.nextInt(currentList.size)
                        when (val item = currentList[position]) {
                            is FriendItem.TextPost -> {
                                val updated = item.copy(likes = item.likes + 1)
                                currentList[position] = updated
                                adapter.submitList(currentList)
                            }
                            is FriendItem.ImagePost -> {
                                val updated = item.copy(likes = item.likes + 1)
                                currentList[position] = updated
                                adapter.submitList(currentList)
                            }
                            is FriendItem.VideoPost -> {
                                val updated = item.copy(likes = item.likes + 1)
                                currentList[position] = updated
                                adapter.submitList(currentList)
                            }
                        }
                    }
                }
            }
        })
    }
}