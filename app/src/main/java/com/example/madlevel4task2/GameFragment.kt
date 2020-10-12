package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date
import java.time.LocalDateTime
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {
    private lateinit var gameResultRepository: GameResultRepository;
    private val mainScope = CoroutineScope(Dispatchers.Main)

    private val gameResults = arrayListOf<GameResult>()
    private val gameResultAdapter = GameResultAdapter(gameResults);



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameResultRepository = GameResultRepository(requireContext())

        ib_rock.setOnClickListener {
            playGame(GameMove.ROCK)
        }

        ib_paper.setOnClickListener {
            playGame(GameMove.PAPER)
        }

        ib_scissors.setOnClickListener {
            playGame(GameMove.SCISSORS)
        }

        initViews()
    }

    private fun initViews() {
        rv_gameresult.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        rv_gameresult.adapter = gameResultAdapter
        //Hide it until the first game has been played
        tv_result.text = ""
    }


    // Game Logic

    private fun playGame(player:GameMove) {
        var computerMove = GameMove.values().random();
        var result:GameState = GameState.DRAW

        if (computerMove == GameMove.SCISSORS) {
            if (player == GameMove.PAPER) {
                // Lose
                result = GameState.LOSE
            } else if  (player == GameMove.ROCK) {
                // Win
                result = GameState.WIN
            }
        }
        else if (computerMove == GameMove.ROCK) {
            if (player == GameMove.SCISSORS) {
                // Lose
                result = GameState.LOSE
            } else if  (player == GameMove.PAPER) {
                // Win
                result = GameState.WIN
            }
        }
        else if (computerMove == GameMove.PAPER) {
            if (player == GameMove.ROCK) {
                // Lose
                result = GameState.LOSE
            } else if  (player == GameMove.SCISSORS) {
                // Win
                result = GameState.WIN
            }
        }

        registerGameResult(player, computerMove, result)
    }

    private fun registerGameResult(player: GameMove, computer:GameMove, result:GameState) {
        mainScope.launch {
            val gameResult = GameResult(
                Calendar.getInstance().time,
                computer,
                player,
                result
            )

            withContext(Dispatchers.IO) {
                gameResultRepository.insertGameResult(gameResult)
            }

            getMostRecentGame()
        }
    }

    private fun getMostRecentGame() {
        mainScope.launch {
            val recentGame = withContext(Dispatchers.IO) {
                gameResultRepository.getRecentGameResult()
            }
            gameResults.clear()
            gameResults.add(recentGame)
            gameResultAdapter.notifyDataSetChanged()
        }
    }

}