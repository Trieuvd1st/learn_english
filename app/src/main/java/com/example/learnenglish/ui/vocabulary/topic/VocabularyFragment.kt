package com.example.learnenglish.ui.vocabulary.topic

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.learnenglish.R
import com.example.learnenglish.database.UserManager
import com.example.learnenglish.database.VocabularyDatabase
import com.example.learnenglish.model.Vocabulary
import com.example.learnenglish.ui.authentication.signin.SignInActivity
import com.example.learnenglish.ui.vocabulary.item.VocaItemActivity
import com.example.learnenglish.widgets.MinusPointDialog
import com.example.learnenglish.widgets.MyPointNotEnoughDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_vocabulary.*
import java.io.File
import java.util.*

class VocabularyFragment : Fragment() {
    var vocabularyArrayList: ArrayList<Vocabulary>? = null
    var vocabularyAdapter: VocabularyAdapter? = null
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

        vocabularyArrayList = ArrayList()
        vocabularyDatabase = VocabularyDatabase(context)
        vocabularyArrayList = vocabularyDatabase!!.listVocabulary
        vocabularyAdapter =
            VocabularyAdapter(context, vocabularyArrayList, R.layout.item_voca_topic)
        gridview.adapter = vocabularyAdapter
        gridview.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
        gridview.isVerticalScrollBarEnabled = false
        vocabularyAdapter!!.setListener { pos ->
            if (vocabularyArrayList?.get(pos)?.pointRequired!! > 0) {
                if (Firebase.auth.currentUser?.isAnonymous == null) {
                    startActivity(Intent(context!!, SignInActivity::class.java))
                } else {
                    MinusPointDialog(
                        context!!,
                        vocabularyArrayList?.get(pos)?.pointRequired!!,
                        UserManager.getMyPoint(context!!),
                        object : MinusPointDialog.MinusDialogListener {
                            override fun onOk() {
                                if (UserManager.getMyPoint(context!!) - POINT_REQUIRED >= 0) {
                                    vocabularyArrayList?.get(pos)?.pointRequired = 0
                                    updatePointMinus(
                                        vocabularyArrayList?.get(pos)!!,
                                        UserManager.getMyPoint(context!!) - POINT_REQUIRED
                                    )
                                    viewModel.unlockTopic(vocabularyArrayList?.get(pos)!!.idVocabulary)
                                } else {
                                    MyPointNotEnoughDialog(context!!).show()
                                }
                            }

                        }).show()
                }
            } else {
                val intent = Intent(activity, VocaItemActivity::class.java)
                intent.putExtra("position", vocabularyArrayList?.get(pos))
                intent.putExtra(
                    "vocabulary_title",
                    vocabularyArrayList?.get(pos)?.enTopicVocabulary
                )
                startActivity(intent)
            }
        }
    }

    private fun updatePointMinus(vocabulary: Vocabulary, pointRemain: Int) {
        UserManager.setMyPoint(context!!, UserManager.getMyPoint(context!!) - 50)
        FirebaseDatabase.getInstance().reference.child("users")
            .child(Firebase.auth.currentUser?.uid!!).child("myPoint").setValue(pointRemain.toLong())
        vocabularyDatabase?.updatePointById(vocabulary.idVocabulary)
        vocabularyAdapter?.notifyDataSetChanged()
        //viewModel.downloadItemVoca(context!!, 8)
    }

    companion object {
        private const val POINT_REQUIRED = 50
    }
}