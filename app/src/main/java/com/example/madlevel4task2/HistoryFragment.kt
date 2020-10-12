package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {
    private lateinit var gameResultRepository: GameResultRepository;
    private val mainScope = CoroutineScope(Dispatchers.Main)

    private val gameResults = arrayListOf<GameResult>()
    private val gameResultExpandedAdapter = GameResultExpandedAdapter(gameResults);

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameResultRepository = GameResultRepository(requireContext().applicationContext)

        getGameResultsFromDatabase()

        initViews()
    }

    private fun getGameResultsFromDatabase() {
        mainScope.launch {
            val allGameResults = withContext(Dispatchers.IO) {
                gameResultRepository.getAllGameResults()
            }
            gameResults.clear()
            gameResults.addAll(allGameResults)
            gameResultExpandedAdapter.notifyDataSetChanged()
        }
    }

    private fun initViews() {
        rv_gameresults.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        rv_gameresults.adapter = gameResultExpandedAdapter
        rv_gameresults.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    public fun deleteAllGameResults() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameResultRepository.deleteGameResults()
                gameResultExpandedAdapter.notifyDataSetChanged()
            }
        }
    }
}