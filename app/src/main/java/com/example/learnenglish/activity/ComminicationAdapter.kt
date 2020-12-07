package com.example.learnenglish.activity

import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learnenglish.R
import com.example.learnenglish.dialog.VoiceDialog
import com.example.learnenglish.model.Communication
import kotlinx.android.synthetic.main.item_communication.*
import kotlinx.android.synthetic.main.item_communication.view.*

class ComminicationAdapter(private var listCommunication: MutableList<Communication>) :
        RecyclerView.Adapter<ComminicationAdapter.ViewHolder>() {

    private var listener: CommunicationAdapterListener? = null

    interface CommunicationAdapterListener {
        fun onMicItemClick(comm: Communication)
    }

    fun setListener(listener: CommunicationAdapterListener) {
        this.listener = listener
    }

    fun setData(listComm: List<Communication>) {
        this.listCommunication.clear()
        this.listCommunication.addAll(listComm)
        notifyDataSetChanged()
    }

    fun addData(listComm: List<Communication>) {
        this.listCommunication.addAll(listComm)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComminicationAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_communication, parent, false))
    }

    override fun onBindViewHolder(holder: ComminicationAdapter.ViewHolder, position: Int) {
        holder.bind(listCommunication[position])
    }

    override fun getItemCount(): Int = listCommunication.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(comm: Communication) = with(itemView) {
            tvEnSen.text = comm.enSentence
            tvViSen.text = comm.viSentence

            tvEnSen.setOnClickListener {
                comm.isExpand = !comm.isExpand
                if (comm.isExpand) {
                    clEn.visibility = View.VISIBLE
                } else {
                    clEn.visibility = View.GONE
                }
            }

            ivSpeaker.setOnClickListener {
                val resourceFromName = resources.getIdentifier(comm.nameSound, "raw", context.packageName)
                val mediaPlayer = MediaPlayer.create(context, resourceFromName)
                mediaPlayer.start()
            }

            ivVoice.setOnClickListener {
                Log.d("COMM ADAPTER:",  "${comm.enSentence}")
                listener?.onMicItemClick(comm)
            }
        }
    }
}