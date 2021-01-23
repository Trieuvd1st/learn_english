package com.example.learnenglish.ui.vocabulary.topic

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learnenglish.R
import com.example.learnenglish.model.VocaTopic
import kotlinx.android.synthetic.main.item_voca_topic.view.*
import java.io.IOException
import java.io.InputStream


class VocaTopicAdapter(private var listVocaTopic: MutableList<VocaTopic>) :
    RecyclerView.Adapter<VocaTopicAdapter.ViewHolder>() {

    private var listener: VocaTopicAdapterListener? = null

    fun setListener(listener: VocaTopicAdapterListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_voca_topic, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listVocaTopic[position])
    }

    override fun getItemCount(): Int {
        return listVocaTopic.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(vocaTopic: VocaTopic) = with(itemView) {
            var ims: InputStream? = null
            try {
                Log.d("duc123", "getView: " + vocaTopic.imageId)
                ims = context?.assets?.open("img/" + vocaTopic.imageId + ".jpg")
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val d = Drawable.createFromStream(ims, null)
            image.setImageDrawable(d)
            tvEnTopic.setText(vocaTopic.getEnName())
            tvViTopic.setText(vocaTopic.getViName())
            btnDetail.setOnClickListener{ listener?.onItemClick(adapterPosition)}
            if (vocaTopic.getPointRequired() > 0) {
                ivPointRequired.setVisibility(View.VISIBLE)
            } else {
                ivPointRequired.setVisibility(View.GONE)
            }
        }
    }

    interface VocaTopicAdapterListener {
        fun onItemClick(pos: Int)
    }

}