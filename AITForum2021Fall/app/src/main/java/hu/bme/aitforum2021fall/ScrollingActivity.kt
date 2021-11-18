package hu.bme.aitforum2021fall

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import hu.bme.aitforum2021fall.adapter.PostsAdapter
import hu.bme.aitforum2021fall.data.Post
import hu.bme.aitforum2021fall.databinding.ActivityScrollingBinding

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding
    private lateinit var adapter: PostsAdapter
    private var listenerReg: ListenerRegistration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            startActivity(Intent(this, CreatePostActivity::class.java))
        }

        adapter = PostsAdapter(this,
            FirebaseAuth.getInstance().currentUser!!.uid)
        binding.recyclerPosts.adapter = adapter

        initFirebaseQuery()
    }

    private fun initFirebaseQuery() {
        val queryRef = FirebaseFirestore.getInstance().collection(CreatePostActivity.COLLECTION_POSTS) // "points to the "posts" collection

        val eventListener = object : EventListener<QuerySnapshot> {
            override fun onEvent(querySnapshot: QuerySnapshot?, e: FirebaseFirestoreException?) {
                if (e != null) {
                    Toast.makeText(
                        this@ScrollingActivity, "Error: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    return
                }

                for (docChange in querySnapshot?.getDocumentChanges()!!) {
                    if (docChange.type == DocumentChange.Type.ADDED) {
                        // converts the document from the "posts" collection into a Post object
                        val post = docChange.document.toObject(Post::class.java)
                        adapter.addPost(post, docChange.document.id)
                    } else if (docChange.type == DocumentChange.Type.REMOVED) {
                        // DELETE post
                        adapter.removePostByKey(docChange.document.id)
                    } else if (docChange.type == DocumentChange.Type.MODIFIED) {

                    }
                }

            }
        }

        listenerReg = queryRef.addSnapshotListener(eventListener)
    }


    override fun onDestroy() {
        super.onDestroy()
        listenerReg?.remove()
    }

}