package hu.ait.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hu.ait.tictactoe.databinding.ActivityMainBinding
import hu.ait.tictactoe.model.TicTacToeModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReset.setOnClickListener {
            binding.ticTacToeView.resetGame()
        }

    }

    fun showTextMessage(msg: String) {
        binding.tvPlayer.text = msg
    }

    fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun isFlagMode() : Boolean {
        return binding.tgbtnIsFlag.isChecked()
    }
}
