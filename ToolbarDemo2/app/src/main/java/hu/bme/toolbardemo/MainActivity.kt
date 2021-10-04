package hu.bme.toolbardemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import hu.bme.toolbardemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_help) {
            Toast.makeText(this, "HELP", Toast.LENGTH_LONG).show()
        } else if (item.itemId == R.id.action_start) {
            Toast.makeText(this, "START", Toast.LENGTH_LONG).show()
        } else if (item.itemId == R.id.action_stop) {
            Toast.makeText(this, "STOP", Toast.LENGTH_LONG).show()
        }

        return true
    }


}