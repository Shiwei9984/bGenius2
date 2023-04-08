data class GameUiState(
    val currentScrambleWord: String = "",
    val isGuessedWordWrong : Boolean = false,
    val score : Int = 1,
    val currentWordCount: Int = 0,
    val isGameOver: Boolean = false
)