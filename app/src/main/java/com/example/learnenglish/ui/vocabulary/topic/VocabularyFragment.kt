package com.example.learnenglish.ui.vocabulary.topic

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.learnenglish.R
import com.example.learnenglish.database.UserManager
import com.example.learnenglish.database.VocabularyDatabase
import com.example.learnenglish.model.VocaTopic
import com.example.learnenglish.ui.authentication.signin.SignInActivity
import com.example.learnenglish.ui.vocabulary.item.VocaItemActivity
import com.example.learnenglish.widgets.MinusPointDialog
import com.example.learnenglish.widgets.MyPointNotEnoughDialog
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_vocabulary.*
import java.util.*

class VocabularyFragment : Fragment() {
    var vocaTopicArrayList: ArrayList<VocaTopic>? = null
    var vocaTopicAdapter: VocaTopicAdapter? = null
    var vocabularyDatabase: VocabularyDatabase? = null
    private lateinit var viewModel: VocalbularyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vocabulary, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(VocalbularyViewModel::class.java).apply {

        }

        viewModel.downloadItemVoca(context!!, 8)

        vocaTopicArrayList = ArrayList()
        vocabularyDatabase = VocabularyDatabase(context)
        vocaTopicArrayList = vocabularyDatabase!!.listVocabulary
        vocaTopicAdapter = VocaTopicAdapter(vocaTopicArrayList!!)
        /*vocabularyAdapter =
            VocabularyAdapter(context, vocaTopicArrayList, R.layout.item_voca_topic)
        gridview.adapter = vocabularyAdapter
        gridview.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
        gridview.isVerticalScrollBarEnabled = false*/

        rvVocaTopic.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = vocaTopicAdapter
        }

        vocaTopicAdapter!!.setListener(object : VocaTopicAdapter.VocaTopicAdapterListener {
            override fun onItemClick(pos: Int) {
                if (vocaTopicArrayList?.get(pos)?.pointRequired!! > 0) {
                    if (Firebase.auth.currentUser?.isAnonymous == null) {
                        startActivity(Intent(context!!, SignInActivity::class.java))
                    } else {
                        MinusPointDialog(
                            context!!,
                            vocaTopicArrayList?.get(pos)?.pointRequired!!,
                            UserManager.getMyPoint(context!!),
                            object : MinusPointDialog.MinusDialogListener {
                                override fun onOk() {
                                    if (UserManager.getMyPoint(context!!) - POINT_REQUIRED >= 0) {
                                        vocaTopicArrayList?.get(pos)?.pointRequired = 0
                                        updatePointMinus(
                                            vocaTopicArrayList?.get(pos)!!,
                                            UserManager.getMyPoint(context!!) - POINT_REQUIRED
                                        )
                                        viewModel.unlockTopic(vocaTopicArrayList?.get(pos)!!.id)
                                    } else {
                                        MyPointNotEnoughDialog(context!!).show()
                                    }
                                }

                            }).show()
                    }
                } else {
                    val intent = Intent(activity, VocaItemActivity::class.java)
                    intent.putExtra("position", vocaTopicArrayList?.get(pos))
                    intent.putExtra(
                        "vocabulary_title",
                        vocaTopicArrayList?.get(pos)?.enName
                    )
                    startActivity(intent)
                }
            }

        })
    }

    private fun updatePointMinus(vocaTopic: VocaTopic, pointRemain: Int) {
        UserManager.setMyPoint(context!!, UserManager.getMyPoint(context!!) - 50)
        FirebaseDatabase.getInstance().reference.child("users")
            .child(Firebase.auth.currentUser?.uid!!).child("myPoint").setValue(pointRemain.toLong())
        vocabularyDatabase?.updatePointById(vocaTopic.id)
        vocaTopicAdapter?.notifyDataSetChanged()
        //viewModel.downloadItemVoca(context!!, 8)
    }

    companion object {
        private const val POINT_REQUIRED = 50
    }
}