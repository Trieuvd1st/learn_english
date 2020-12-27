package com.example.learnenglish.ui.practice

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learnenglish.model.*
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class PracticeViewModel : ViewModel() {

    private var storageRef: StorageReference =
        FirebaseStorage.getInstance().reference.child("toeic").child("exam_1")
    private var myRef: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child("shorted_toeic_exam").child("exam_1")

    val toeicListResponse = MutableLiveData<ToeicListResponse>()
    var toeicList = ToeicListResponse()
    var answerList = arrayListOf<Int>()
    var isShowDialog = MutableLiveData<Boolean>()

    init {
        getData()
    }

    fun getData() {
        isShowDialog.value = true
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                toeicList.time = snapshot.child("time").value as Long?
                //get pass 1
                val listsenPart1 = mutableListOf<ToeicSentence>()
                for (senSnapshot in snapshot.child("Part 1").children) {
                    listsenPart1.add(senSnapshot.getValue(ToeicSentence::class.java)!!)
                    answerList.add(senSnapshot.getValue(ToeicSentence::class.java)?.answer?.toInt()!!)
                }
                toeicList.part1 = listsenPart1
                //get pass 2
                val listsenPart2 = mutableListOf<ToeicSentence>()
                for (senSnapshot in snapshot.child("Part 2").children) {
                    listsenPart2.add(senSnapshot.getValue(ToeicSentence::class.java)!!)
                    answerList.add(senSnapshot.getValue(ToeicSentence::class.java)?.answer?.toInt()!!)
                }
                toeicList.part2 = listsenPart2
                //get pass 3
                for (senSnapshot in snapshot.child("Part 3").children) {
                    val part3 = Part3()
                    part3.audio = senSnapshot.child("audio").value.toString()
                    for (sen2Snapshot in senSnapshot.children) {
                        if (sen2Snapshot.hasChildren()) {
                            part3.listSen?.add(sen2Snapshot.getValue(ToeicSentence::class.java)!!)
                            answerList.add(sen2Snapshot.getValue(ToeicSentence::class.java)?.answer?.toInt()!!)
                        }
                    }
                    toeicList.part3?.add(part3)
                }
                //get pass 4
                for (senSnapshot in snapshot.child("Part 4").children) {
                    val part4 = Part4()
                    part4.audio = senSnapshot.child("audio").value.toString()
                    for (sen2Snapshot in senSnapshot.children) {
                        if (sen2Snapshot.hasChildren()) {
                            part4.listSen?.add(sen2Snapshot.getValue(ToeicSentence::class.java)!!)
                            answerList.add(sen2Snapshot.getValue(ToeicSentence::class.java)?.answer?.toInt()!!)
                        }
                    }
                    toeicList.part4?.add(part4)
                }
                //get pass 5
                val listsenPart5 = mutableListOf<ToeicSentence>()
                for (senSnapshot in snapshot.child("Part 5").children) {
                    listsenPart5.add(senSnapshot.getValue(ToeicSentence::class.java)!!)
                    answerList.add(senSnapshot.getValue(ToeicSentence::class.java)?.answer?.toInt()!!)
                }
                toeicList.part5 = listsenPart5
                //get pass 6
                for (senSnapshot in snapshot.child("Part 6").children) {
                    val part6 = Part6()
                    part6.text = senSnapshot.child("text").value.toString()
                    for (sen2Snapshot in senSnapshot.children) {
                        if (sen2Snapshot.hasChildren()) {
                            part6.listSen?.add(sen2Snapshot.getValue(ToeicSentence::class.java)!!)
                            answerList.add(sen2Snapshot.getValue(ToeicSentence::class.java)?.answer?.toInt()!!)
                        }
                    }
                    toeicList.part6?.add(part6)
                }
                //get pass 7
                for (senSnapshot in snapshot.child("Part 7").children) {
                    val part7 = Part7()
                    part7.text_1 = senSnapshot.child("text_1").value.toString()
                    part7.textNumber = senSnapshot.child("textNumber").value as Long?
                    if (senSnapshot.hasChild("text_2")) part7.text_2 = senSnapshot.child("text_2").value.toString()
                    for (sen2Snapshot in senSnapshot.children) {
                        if (sen2Snapshot.hasChildren()) {
                            part7.listSen?.add(sen2Snapshot.getValue(ToeicSentence::class.java)!!)
                            answerList.add(sen2Snapshot.getValue(ToeicSentence::class.java)?.answer?.toInt()!!)
                        }
                    }
                    toeicList.part7?.add(part7)
                }

                toeicListResponse.value = toeicList
                isShowDialog.value = false
            }
            override fun onCancelled(error: DatabaseError) {}

        })
    }
}