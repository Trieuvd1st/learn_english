package com.example.learnenglish.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnenglish.R
import com.example.learnenglish.dialog.VoiceDialog
import com.example.learnenglish.model.Communication
import kotlinx.android.synthetic.main.activity_communication.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class CommunicationActivity : AppCompatActivity(), View.OnClickListener,  EasyPermissions.PermissionCallbacks {

    private lateinit var adapterCommunication: ComminicationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communication)



        btnTest.setOnClickListener(this)

        val listComm = mutableListOf<Communication>()
        val comm = Communication().apply {
            id = 1
            topicId = 1
            enSentence = "Good evening Sir."
            viSentence = "Chào ông (buổi tối)"
            nameSound = "good_evening_sir"
            false
            false
        }
        listComm.add(comm)
        listComm.add(comm)
        listComm.add(comm)
        listComm.add(comm)
        listComm.add(comm)

        adapterCommunication = ComminicationAdapter(listComm)

        rvCommunication.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = adapterCommunication
        }

        adapterCommunication.setListener(object : ComminicationAdapter.CommunicationAdapterListener {
            override fun onMicItemClick(comm: Communication) {
                Log.d("COMM permission:",  "${comm.enSentence}")
                checkPermissionRecord(comm)
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            btnTest.id -> {
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

}