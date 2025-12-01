package com.example.imui.friend

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imui.databinding.ActivityFriendBinding
import com.example.imui.friend.adapter.FriendAdapter
import com.example.imui.friend.data.LocalFriendRepository
import com.example.imui.friend.decoration.FriendItemDecoration
import com.example.imui.friend.model.FriendItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFriendBinding
    private lateinit var adapter: FriendAdapter
    private val repository = LocalFriendRepository()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupRecyclerView()
        loadFriendPosts()
        
        binding.back.setOnClickListener {
            finish()
        }
    }
    
    private fun setupRecyclerView() {
        adapter = FriendAdapter(
            onLikeClicked = { item ->
                handleLikeClick(item)
            },
            onCommentClicked = { item ->
                handleCommentClick(item)
            }
        )
        
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
    
    private fun loadFriendPosts() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val posts = withContext(Dispatchers.IO) {
                    repository.getFriendPosts()
                }
                adapter.submitList(posts)
            } catch (e: Exception) {
                Toast.makeText(this@FriendActivity, "加载失败: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun handleLikeClick(item: FriendItem) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val success = withContext(Dispatchers.IO) {
                    repository.likePost(item.id)
                }
                
                if (success) {
                    // 更新列表中的点赞数
                    val currentList = adapter.currentList.toMutableList()
                    val index = currentList.indexOfFirst { it.id == item.id }
                    
                    if (index != -1) {
                        // 使用当前列表中的最新数据，而不是传入的item参数
                        val updatedItem = when (val currentItem = currentList[index]) {
                            is FriendItem.TextPost -> currentItem.copy(likes = currentItem.likes + 1)
                            is FriendItem.ImagePost -> currentItem.copy(likes = currentItem.likes + 1)
                            is FriendItem.VideoPost -> currentItem.copy(likes = currentItem.likes + 1)
                            else -> currentItem
                        }

                        Log.d("FriendActivity", "Updated item: $updatedItem")
                        
                        currentList[index] = updatedItem
                        adapter.submitList(currentList) {
                            Toast.makeText(this@FriendActivity, "点赞成功", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(this@FriendActivity, "点赞失败: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun handleCommentClick(item: FriendItem) {
        // 示例：显示评论功能
        Toast.makeText(this@FriendActivity, "评论功能待实现，点击了 ${item.username} 的动态", Toast.LENGTH_SHORT).show()
    }
}