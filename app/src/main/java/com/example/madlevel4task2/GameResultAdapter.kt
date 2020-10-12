package com.example.madlevel4task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GameResultAdapter(private val gameResult:List<GameResult>) : RecyclerView.Adapter<GameResultAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val binding = ItemReminderBinding.bind(itemView);

        fun databind(reminder: GameResult) {
            //binding.tvReminder.text = reminder.reminderText;
            //itemView.tvReminder.text = reminder.reminderText;
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate((R.layout.item_gameresult), parent, false)
        );
    }


    override fun getItemCount(): Int {
        return gameResult.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(gameResult[position])
    }

}