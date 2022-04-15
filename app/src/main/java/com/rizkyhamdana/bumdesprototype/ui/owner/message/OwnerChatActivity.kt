package com.rizkyhamdana.bumdesprototype.ui.owner.message

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import co.intentservice.chatui.models.ChatMessage
import com.google.firebase.database.FirebaseDatabase
import com.rizkyhamdana.bumdesprototype.data.ChatResponse
import com.rizkyhamdana.bumdesprototype.databinding.ActivityChatBinding
import com.rizkyhamdana.bumdesprototype.util.Const

class OwnerChatActivity: AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var viewModel: OwnerMessageViewModel

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_MYID = "extra_myid"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[OwnerMessageViewModel::class.java]

        val myId = intent.getStringExtra(EXTRA_MYID) as String
        val otherId = intent.getStringExtra(EXTRA_ID) as String
        val otherName = intent.getStringExtra(EXTRA_NAME) as String

        setSupportActionBar(binding.toolBar)
        title = otherName

        viewModel.getChatbyStand(myId, otherId).observe(this) {
            for ((index, i) in it.withIndex()) {
                binding.chatView.removeMessage(index)
                if (i.idSender == myId) {
                    val chatMessage = ChatMessage(
                        i.message,
                        i.date,
                        ChatMessage.Type.SENT
                    )
                    binding.chatView.addMessage(chatMessage)
                } else {
                    val chatMessage = ChatMessage(
                        i.message,
                        i.date,
                        ChatMessage.Type.RECEIVED
                    )
                    binding.chatView.addMessage(chatMessage)
                }
            }

        }

        binding.chatView.setOnSentMessageListener { chatMessage ->
            val time = System.currentTimeMillis()
            binding.chatView.clearMessages()
            if (chatMessage != null) {
                val chatResponse = ChatResponse(
                    myId,
                    otherId,
                    chatMessage.message,
                    time
                )
                val firebaseDb = FirebaseDatabase.getInstance(Const.BASE_URL)
                val reference = firebaseDb.getReference("chat")
                val childReference = reference.child(myId)
                val key = childReference.push().key as String
                childReference.child(key).setValue(chatResponse)
                finish()
                overridePendingTransition(0, 0)
                startActivity(intent)
                overridePendingTransition(0, 0)
                false
            } else {
                binding.chatView.clearMessages()
                false
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onStart() {
        super.onStart()
        binding.chatView.clearMessages()
    }

    override fun onResume() {
        super.onResume()
        binding.chatView.clearMessages()
    }
}
