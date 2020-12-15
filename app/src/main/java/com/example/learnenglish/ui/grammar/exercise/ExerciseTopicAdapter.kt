package com.example.learnenglish.ui.grammar.exercise
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learnenglish.R
import com.example.learnenglish.model.Exercise
import kotlinx.android.synthetic.main.item_exercise.view.*
import java.util.ArrayList

class ExerciseTopicAdapter(private var listExercise: MutableList<Exercise>) :
    RecyclerView.Adapter<ExerciseTopicAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false))
    }

    override fun getItemCount(): Int {
        return listExercise.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listExercise[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(exercise: Exercise) = with(itemView) {
            tvTopic.text = "Bài tập ${exercise.id}"

            var exerciseArrayList: ArrayList<Exercise>? = ArrayList()
            exerciseArrayList?.addAll(listExercise)
            itemView.setOnClickListener {
                context.startActivity(Intent(context, ExerciseActivity::class.java).apply {
                    putExtra("GRAMMAR_EXERCISE_ID", exercise.id)
                })
            }
        }
    }

}