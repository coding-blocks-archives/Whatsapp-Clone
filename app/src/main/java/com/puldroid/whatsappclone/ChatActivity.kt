package com.puldroid.whatsappclone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.puldroid.whatsappclone.utils.KeyboardVisibilityUtil
import kotlinx.android.synthetic.main.activity_chat.*

const val USER_ID = "userId"
const val USER_THUMB_IMAGE = "thumbImage"
const val USER_NAME = "userName"

class ChatActivity : AppCompatActivity() {

    private lateinit var keyboardVisibilityHelper: KeyboardVisibilityUtil
    private val mutableItems: MutableList<ChatEvent> = mutableListOf()
    private val mLinearLayout: LinearLayoutManager by lazy { LinearLayoutManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        keyboardVisibilityHelper = KeyboardVisibilityUtil(root) {
        }
    }

    override fun onResume() {
        super.onResume()
        root.viewTreeObserver
            .addOnGlobalLayoutListener(keyboardVisibilityHelper.visibilityListener)
    }


    override fun onPause() {
        super.onPause()
        root.viewTreeObserver
            .removeOnGlobalLayoutListener(keyboardVisibilityHelper.visibilityListener)
    }

    companion object {

        fun createChatActivity(context: Context, id: String, name: String, image: String): Intent {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra(USER_ID, id)
            intent.putExtra(USER_NAME, name)
            intent.putExtra(USER_THUMB_IMAGE, image)

            return intent
        }
    }
}