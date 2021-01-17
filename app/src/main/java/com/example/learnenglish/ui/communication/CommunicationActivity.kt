package com.example.learnenglish.ui.communication

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnenglish.R
import com.example.learnenglish.database.CommunicationDatabase
import com.example.learnenglish.model.Communication
import com.example.learnenglish.ui.base.BaseActivity
import com.example.learnenglish.ui.communication.commtest.CommTestActivity
import com.example.learnenglish.utils.extension.isLogin
import com.example.learnenglish.widgets.VoiceDialog
import kotlinx.android.synthetic.main.activity_communication.*
import kotlinx.android.synthetic.main.include_toolbar.*
import pub.devrel.easypermissions.EasyPermissions

class CommunicationActivity : BaseActivity(), View.OnClickListener,  EasyPermissions.PermissionCallbacks {

    private lateinit var adapterCommunication: CommunicationAdapter
    private lateinit var commDatabase: CommunicationDatabase
    private var listComm: ArrayList<Communication>? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communication)

        //read data
        val topicIdReceive = intent.getIntExtra(TOPIC_ID_EXTRA, 0)
        val nameTopic = intent.getStringExtra(TOPIC_NAME_EXTRA)

        nameTopic?.let { setTitleActionBar(toolbar, it) }

        commDatabase = CommunicationDatabase(this)
        listComm = commDatabase.getListCommByTopicId(topicIdReceive)

        btnTest.setOnClickListener(this)

        adapterCommunication = listComm?.let { CommunicationAdapter(it) }!!

        rvCommunication.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = adapterCommunication
        }

        tvNumberPoint.isVisible = !isLogin()
        ivAddPoint.isVisible = !isLogin()
        tvNumberPoint.text = "+${listComm?.size.toString()}"

        adapterCommunication.setListener(object : CommunicationAdapter.CommunicationAdapterListener {
            override fun onMicItemClick(comm: Communication) {
                Log.d("COMM permission:", comm.enSentence)
                checkPermissionRecord(comm)
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            btnTest.id -> {
                CommTestActivity.startNewActivity(this)
            }
        }
    }

    //@AfterPermissionGranted(4466)
    private fun checkPermissionRecord(comm: Communication) {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.RECORD_AUDIO)) {
            VoiceDialog(this, comm).show()
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                    this,
                    "Bạn cần cho phép sử dụng micro để sử dụng tính năng này",
                    4466,
                    Manifest.permission.RECORD_AUDIO
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        //VoiceDialog(this, comm).show()
        if (requestCode == 1234) {

        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (requestCode == 1234) {

        }
    }

    companion object {
        fun startNewActivity(context: Context, topicId: Int, nameTopic: String) {
            context.startActivity(Intent(context, CommunicationActivity::class.java).apply {
                putExtra(TOPIC_ID_EXTRA, topicId)
                putExtra(TOPIC_NAME_EXTRA, nameTopic)
            })
        }

        const val TOPIC_ID_EXTRA = "com.example.learnenglish.activity.TOPIC_ID"
        const val TOPIC_NAME_EXTRA = "com.example.learnenglish.activity.TOPIC_NAME_EXTRA"
    }

}