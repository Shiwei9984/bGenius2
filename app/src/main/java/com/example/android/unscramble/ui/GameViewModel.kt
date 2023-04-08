import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.android.unscramble.data.allWords
import androidx.compose.runtime.mutableStateof
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.update
class GameViewModel : ViewModel(){
//Game Ui State
    private val_uiState = MutableStateFlow(GameUiState())
    private var usedWords:MutableSet<String> = mutableSetOf()
    //Backing property to avoid state updates from another classes
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
    private lateinit var currentWord: String
    var userGuess by mutableStateOf("")
      private set
    private fun pickRandomWordAndShuffle():String{
        currentWord = allWords.random()
        if(usedWord.contains(currentWord)){
            return pickRandomWordAndShuffle()
        }
        else{
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }
    private fun shuffleCurrentWord(word:String):String{
        val tempWord = word.toCharArray()
        tempWord.shuffle()
        while(String(tempWord).equals(word)){
            tempWord.shuffle()
        }
        return String(tempWord)
    }
    fun resetGame(){
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }
    init{
        resetGame()
    }
    fun updateUserGuess(guessedWord:String){
        userGuess = guessedWord
    }
    fun checkUserGuess(){
        if(userGuess.equals(currentWord, ignoreCase = true)){
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        } else{
            _uiState.update{currentState->
                currentState.copy(isGuessedWordWrong = true)
            }
        }
        updateUserGuess("")
    }
    private fun updateGameState(updatedScore: Int) {
        if (usedWords.size == MAX_NO_OF_WORDS) {
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    score = updatedScore,
                    isGameOver = true
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessWordWrong = false,
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    score = updatedScore,
                    currentWordCount = currentState.currentWordCount.inc(),
                )
            }
        }
    }
    fun skipWord(){
        updateGameState(_uiState.value.score)
        updateUserGuess("")
    }
}