package com.example.learnenglish.ui.communication

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnenglish.R
import com.example.learnenglish.database.CommunicationDatabase
import com.example.learnenglish.model.Communication
import com.example.learnenglish.ui.communication.test.CommTestActivity
import com.example.learnenglish.widgets.VoiceDialog
import kotlinx.android.synthetic.main.activity_communication.*
import pub.devrel.easypermissions.EasyPermissions
import kotlin.random.Random

class CommunicationActivity : AppCompatActivity(), View.OnClickListener,  EasyPermissions.PermissionCallbacks {

    private lateinit var adapterCommunication: CommunicationAdapter
    private lateinit var commDatabase: CommunicationDatabase
    private var listComm: ArrayList<Communication>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communication)

        Log.d("RANDOM_TEST", "${Random.nextInt(0, 10)}")

        //read data
        val topicIdReceive = intent.getIntExtra(TOPIC_ID_EXTRA, 0)
        commDatabase = CommunicationDatabase(this)
        listComm = commDatabase.getListCommByTopicId(topicIdReceive)

        btnTest.setOnClickListener(this)

        adapterCommunication = listComm?.let { CommunicationAdapter(it) }!!

        rvCommunication.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = adapterCommunication
        }

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
        fun startNewActivity(context: Context, topicId: Int) {
            context.startActivity(Intent(context, CommunicationActivity::class.java).apply {
                putExtra(TOPIC_ID_EXTRA, topicId)
            })
        }

        const val TOPIC_ID_EXTRA = "com.example.learnenglish.activity.TOPIC_ID"
    }

}