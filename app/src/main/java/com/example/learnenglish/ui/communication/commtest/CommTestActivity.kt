package com.example.learnenglish.ui.communication.commtest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.learnenglish.R
import com.example.learnenglish.widgets.AnswerTestDialog
import kotlinx.android.synthetic.main.activity_comm_test.*
import kotlin.random.Random

class CommTestActivity : AppCompatActivity(), View.OnClickListener, AnswerTestDialog.VoCaAnswerTestDialogListener {

    private lateinit var viewModelCommTest: CommTestViewModel
    private var fragmentType = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comm_test)

        ivBack.setOnClickListener(this)
        ivSkip.setOnClickListener(this)
        btnTest.setOnClickListener(this)

        //showHideBtnTest(false)

        initViewModel()

        setRandomLayout()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            ivBack.id -> {
                finish()
            }

            ivSkip.id -> {
                viewModelCommTest.getRandomMutileChoice(this)
                //setRandomLayout()
            }

            btnTest.id -> {
                if (fragmentType == 1) viewModelCommTest.checkViResult()
                else viewModelCommTest.checkEnResult()
            }
        }
    }

    private fun initViewModel() {
        viewModelCommTest = ViewModelProviders.of(this).get(CommTestViewModel::class.java).apply {
            listSize.observe(this@CommTestActivity, Observer {
                pbCountNumber.max = it
            })

            commAnswerData.observe(this@CommTestActivity, Observer {

            })

            isAnswer.observe(this@CommTestActivity, Observer {
                if (it == "1") {
                    AnswerTestDialog(this@CommTestActivity, true, it, this@CommTestActivity).show()
                } else {
                    AnswerTestDialog(this@CommTestActivity, false, it, this@CommTestActivity).show()
                }
            })

            listChoiceData.observe(this@CommTestActivity, Observer {
                if (it.isNotEmpty()) {
                    setRandomLayout()
                }
            })

            isBtnTextEnable.observe(this@CommTestActivity, Observer {
                showHideBtnTest(it)
            })

            totalItemData.observe(this@CommTestActivity, Observer {
                pbCountNumber.max = it
            })

            currentCountData.observe(this@CommTestActivity, Observer {
                pbCountNumber.progress = it
            })
        }

        viewModelCommTest.getListCommSelected(this)
        viewModelCommTest.getRandomMutileChoice(this)
    }

    private fun showLayoutFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frameTest, fragment).commit()
    }

    private fun setRandomLayout() {
        showHideBtnTest(false)
        fragmentType = Random.nextInt(1, 5)
        when (fragmentType) {
            1 -> showLayoutFragment(EnSenToViSenFragment())
            2 -> showLayoutFragment(SoundToTextFragment())
            3 -> showLayoutFragment(EnSenToMicFragment())
            else -> showLayoutFragment(ViSenToEnSenFragment())
        }
    }

    private fun showHideBtnTest(isEnable: Boolean) {
        if (isEnable) {
            btnTest.isEnabled = true
            btnTest.setBackgroundResource(R.drawable.bg_border_soft_orange)
            btnTest.setTextColor(ContextCompat.getColor(this, R.color.white))
        } else {
            btnTest.isEnabled = false
            btnTest.setBackgroundResource(R.drawable.bg_gray_corner_hard)
            btnTest.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
    }

    override fun onBtnNext() {
        viewModelCommTest.getRandomMutileChoice(this)
        //setRandomLayout()
    }

    companion object {
        fun startNewActivity(context: Context) {
            context.startActivity(Intent(context, CommTestActivity::class.java).apply {

            })
        }
    }
}