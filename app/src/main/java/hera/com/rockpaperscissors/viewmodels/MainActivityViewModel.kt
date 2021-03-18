package hera.com.rockpaperscissors.viewmodels

import androidx.core.content.res.TypedArrayUtils.getText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainActivityViewModel: ViewModel() {
    private val _playerChoice = MutableLiveData<Int>()
    val playerChoice get() = _playerChoice

    private val _aiChoice = MutableLiveData<Int>()
    val aiChoice get() = _aiChoice

    private val _result = MutableLiveData<Int>()
    val result get() = _result

    init {
        _playerChoice.value = 0
        _aiChoice.value = 0
        _result.value = 0
    }

    fun rockChosen() {
        _playerChoice.value = 1
    }

    fun paperChosen() {
        _playerChoice.value = 2
    }

    fun scissorsChosen() {
        _playerChoice.value = 3
    }

    fun aiChoose() {
        _aiChoice.value = Random.nextInt(1, 4)
    }

    fun checkResult() {
        _result.value = when {
            _playerChoice.value == _aiChoice.value -> 1

            (_playerChoice.value == 1 && _aiChoice.value == 3) ||
                    (_playerChoice.value == 2 && _aiChoice.value == 1) ||
                    (_playerChoice.value == 3 && _aiChoice.value == 2) -> 2

            else -> 3
        }
    }
}