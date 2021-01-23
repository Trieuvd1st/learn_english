package com.example.learnenglish.ui.grammar

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnenglish.R
import com.example.learnenglish.database.GrammarDatabase
import com.example.learnenglish.database.UserManager
import com.example.learnenglish.model.Grammar
import com.example.learnenglish.ui.authentication.signin.SignInActivity
import com.example.learnenglish.ui.base.BaseActivity
import com.example.learnenglish.ui.grammar.exercise.ExerciseTopicGrammarActivity
import com.example.learnenglish.widgets.MinusPointDialog
import com.example.learnenglish.widgets.MyPointNotEnoughDialog
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_grammar_topic.*
import kotlinx.android.synthetic.main.include_toolbar.*

class GrammarTopicActivity : BaseActivity() {

    private lateinit var grammarDatabase: GrammarDatabase
    private var listGrammar: ArrayList<Grammar>? = null
    private lateinit var adapterGrammar: GrammarAdapter

    private lateinit var viewModel: GrammarTopicViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_topic)

        setTitleActionBar(toolbar, " Ngữ pháp")

        grammarDatabase = GrammarDatabase(this)
        listGrammar = grammarDatabase.getListGrammar()

        adapterGrammar = GrammarAdapter(listGrammar!!)

        rvGrammarTopic.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = adapterGrammar
        }

        viewModel = ViewModelProviders.of(this).get(GrammarTopicViewModel::class.java).apply {

        }

        adapterGrammar.setListener(object : GrammarAdapter.GrammarAdapterListener {
            override fun onItemClick(pos: Int) {
                if (listGrammar?.get(pos)?.pointRequired!! > 0) {
                    if (Firebase.auth.currentUser?.isAnonymous == null) {
                        startActivity(Intent(this@GrammarTopicActivity, SignInActivity::class.java))
                    } else {
                        MinusPointDialog(
                            this@GrammarTopicActivity,
                            listGrammar?.get(pos)?.pointRequired!!,
                            UserManager.getMyPoint(this@GrammarTopicActivity),
                            object : MinusPointDialog.MinusDialogListener {
                                override fun onOk() {
                                    if (UserManager.getMyPoint(this@GrammarTopicActivity) - POINT_REQUIRED >= 0) {
                                        listGrammar?.get(pos)?.pointRequired = 0
                                        updatePointMinus(
                                            listGrammar?.get(pos)!!,
                                            UserManager.getMyPoint(this@GrammarTopicActivity) - POINT_REQUIRED
                                        )
                                        viewModel.unlockTopic(listGrammar?.get(pos)!!.id)
                                    } else {
                                        MyPointNotEnoughDialog(this@GrammarTopicActivity).show()
                                    }
                                }

                            }).show()
                    }
                } else {
                    GrammarContentActivity.startNewActivity(this@GrammarTopicActivity, listGrammar?.get(pos)!!)
                }

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.grammar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.exercise -> {
                startActivity(Intent(this, ExerciseTopicGrammarActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updatePointMinus(grammar: Grammar, pointRemain: Int) {
        UserManager.setMyPoint(this, UserManager.getMyPoint(this) - 50)
        FirebaseDatabase.getInstance().reference.child("users")
            .child(Firebase.auth.currentUser?.uid!!).child("myPoint").setValue(pointRemain.toLong())
        grammarDatabase.updatePointById(grammar.id)
        adapterGrammar.notifyDataSetChanged()
        //viewModel.downloadItemVoca(context!!, 8)
    }

    companion object {
        private const val POINT_REQUIRED = 50
    }
}
