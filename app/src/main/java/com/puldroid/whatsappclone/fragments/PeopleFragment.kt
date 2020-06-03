package com.puldroid.whatsappclone.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.firebase.ui.firestore.paging.LoadingState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.puldroid.whatsappclone.R
import com.puldroid.whatsappclone.models.User
import kotlinx.android.synthetic.main.fragment_chats.*

class PeopleFragment : Fragment() {

    private lateinit var mAdapter: FirestorePagingAdapter<User, UsersViewHolder>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val database by lazy {
        FirebaseFirestore.getInstance().collection("users")
            .orderBy("name", Query.Direction.DESCENDING)
    }
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewManager = LinearLayoutManager(requireContext())
        setupAdapter()
        return inflater.inflate(R.layout.fragment_chats, container, false)
    }

    private fun setupAdapter() {
        // Init Paging Configuration
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPrefetchDistance(2)
            .setPageSize(10)
            .build()

        // Init Adapter Configuration
        val options = FirestorePagingOptions.Builder<User>()
            .setLifecycleOwner(this)
            .setQuery(database, config, User::class.java)
            .build()

        // Instantiate Paging Adapter
        mAdapter = object : FirestorePagingAdapter<User, UsersViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
                val view = layoutInflater.inflate(R.layout.list_item, parent, false)
                return UsersViewHolder(view)
            }

            override fun onBindViewHolder(viewHolder: UsersViewHolder, position: Int, post: User) {
                // Bind to ViewHolder
                viewHolder.bind(post)
            }

            override fun onError(e: Exception) {
                super.onError(e)
                Log.e("MainActivity", e.message)
            }

            override fun onLoadingStateChanged(state: LoadingState) {
                when (state) {
                    LoadingState.LOADING_INITIAL -> {
                    }

                    LoadingState.LOADING_MORE -> {
                    }

                    LoadingState.LOADED -> {
                    }

                    LoadingState.ERROR -> {
                        Toast.makeText(
                            requireContext(),
                            "Error Occurred!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    LoadingState.FINISHED -> {
                    }
                }
            }

            // Finally Set the Adapter to RecyclerView

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mAdapter
        }

    }

}