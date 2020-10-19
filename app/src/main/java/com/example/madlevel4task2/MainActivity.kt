package com.example.madlevel4task2

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebViewFragment
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private var currentFragment: Int = R.id.gameFragment;

    private lateinit var gameResultRepository: GameResultRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        gameResultRepository = GameResultRepository(applicationContext)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        /// Ended up using a variable to check what the current fragment is, since we access it
        /// anyway in this class.

        if (currentFragment == R.id.gameFragment) {
            //We're in the game fragment
            menu.findItem(R.id.action_delete).isVisible = false
            menu.findItem(R.id.action_return).isVisible = false
        } else {
            menu.findItem(R.id.action_history).isVisible = false
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.itemId == R.id.action_history) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_gameFragment_to_historyFragment)
            currentFragment = R.id.historyFragment
            invalidateOptionsMenu()
        } else if (item.itemId == R.id.action_return) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_historyFragment_to_gameFragment)
            currentFragment = R.id.gameFragment
            invalidateOptionsMenu()
        } else if (item.itemId == R.id.action_delete) {
            // TODO: Unsure of how to call .notifyDataSetChanged() on the adapter from here
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    gameResultRepository.deleteGameResults()
                }
            }
        }

        return true
    }
}