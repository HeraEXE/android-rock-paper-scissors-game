package hera.com.rockpaperscissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hera.com.rockpaperscissors.databinding.ActivityMainBinding
import hera.com.rockpaperscissors.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        val zoomOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
        val zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in)

        binding.rockButton.setOnClickListener {
            viewModel.rockChosen()
            viewModel.aiChoose()
            viewModel.checkResult()
        }

        binding.paperButton.setOnClickListener {
            viewModel.paperChosen()
            viewModel.aiChoose()
            viewModel.checkResult()
        }

        binding.scissorsButton.setOnClickListener {
            viewModel.scissorsChosen()
            viewModel.aiChoose()
            viewModel.checkResult()
        }

        viewModel.playerChoice.observe(this, Observer { playerChoice ->
            when (playerChoice) {
                1 -> binding.rockButton.startAnimation(zoomOut)
                2 -> binding.paperButton.startAnimation(zoomOut)
                3 -> binding.scissorsButton.startAnimation(zoomOut)
            }
        })

        viewModel.aiChoice.observe(this, Observer { aiChoice ->
            when (aiChoice) {
                1 -> binding.aiChoiceImg.setImageResource(R.drawable.ai_rock_img)
                2 -> binding.aiChoiceImg.setImageResource(R.drawable.ai_paper_img)
                3 -> binding.aiChoiceImg.setImageResource(R.drawable.ai_scissors_img)
            }
            binding.aiChoiceImg.startAnimation(zoomOut)
        })

        viewModel.result.observe(this, Observer { result ->
            when(result) {
                1 -> { binding.resultText.setText(R.string.draw)
                    binding.resultText.setBackgroundResource(R.color.state_grey) }
                2 -> {binding.resultText.setText(R.string.player_won)
                    binding.resultText.setBackgroundResource(R.color.go_green)}
                3 -> {binding.resultText.setText(R.string.ai_won)
                    binding.resultText.setBackgroundResource(R.color.lava)}
            }
            binding.resultText.startAnimation(zoomIn)
        })
    }
}