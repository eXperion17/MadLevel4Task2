package com.example.madlevel4task2

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebViewFragment
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    private var currentFragment: Int = R.id.gameFragment;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
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
            //var fragment: HistoryFragment = supportFragmentManager.findFragmentById(R.id.historyFragment) as HistoryFragment
            //fragment.deleteAllGameResults()
        }

        return true
    }
}