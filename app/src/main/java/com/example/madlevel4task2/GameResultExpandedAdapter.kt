package com.example.madlevel4task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_gameresultexpanded.view.*

class GameResultExpandedAdapter(private val gameResult:List<GameResult>) : RecyclerView.Adapter<GameResultExpandedAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(result: GameResult) {
            itemView.iv_handcomputer.setImageResource(getDrawableFromGameMove(result.computer))
            itemView.iv_handplayer.setImageResource(getDrawableFromGameMove(result.player))
            itemView.tv_result.text = result.result.toString()
            itemView.tv_date.text = result.date.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate((R.layout.item_gameresultexpanded), parent, false)
        );
    }


    override fun getItemCount(): Int {
        return gameResult.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(gameResult[position])
    }

    @DrawableRes
    private fun getDrawableFromGameMove(move:GameMove):Int {
        // Resorted to using if statements because Kotlin doesn't have switches & it feels like
        // adding a lot of extra space for the same end result.
        if (move == GameMove.ROCK) {
            return R.drawable.rock;
        }
        else if (move == GameMove.PAPER) {
            return R.drawable.paper;
        } else if (move == GameMove.SCISSORS) {
            return R.drawable.scissors;
        }

        // Probably needs a more obvious sign that says 'error'
        return R.drawable.ic_delete_white_24dp
    }
}