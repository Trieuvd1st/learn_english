package com.example.learnenglish.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.learnenglish.R
import com.example.learnenglish.fragment.EnSenToMicFragment
import com.example.learnenglish.fragment.EnSenToViSenFragment
import com.example.learnenglish.fragment.SoundToTextFragment
import com.example.learnenglish.fragment.ViSenToEnSenFragment
import kotlinx.android.synthetic.main.activity_comm_test.*
import kotlin.random.Random

class CommTestActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModelCommTest: CommTestViewModel
    private var fragmentType = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comm_test)

        ivBack.setOnClickListener(this)
        ivSkip.setOnClickListener(this)
        btnTest.setOnClickListener(this)

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
                setRandomLayout()
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
                if (it) {
                    Toast.makeText(this@CommTestActivity, "ĐÚng cmnr", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@CommTestActivity, "Sai vl", Toast.LENGTH_SHORT).show()
                }
            })
        }

        viewModelCommTest.getListCommSelected(this)
        viewModelCommTest.getRandomMutileChoice(this)
    }

    private fun showLayoutFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frameTest, fragment).commit()
    }

    private fun setRandomLayout() {
        fragmentType = Random.nextInt(1, 5)
        when (fragmentType) {
            1 -> showLayoutFragment(EnSenToViSenFragment())
            2 -> showLayoutFragment(SoundToTextFragment())
            3 -> showLayoutFragment(EnSenToMicFragment())
            else -> showLayoutFragment(ViSenToEnSenFragment())
        }
    }

    companion object {
        fun startNewActivity(context: Context) {
            context.startActivity(Intent(context, CommTestActivity::class.java).apply {

            })
        }
    }
}