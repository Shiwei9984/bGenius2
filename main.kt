
enum class Difficulty{
  Easy, Medium, Hard
}

interface ProgressPrintable{
  val progressText:String
  fun printProgressBar()
}
class Quiz: ProgressPrintable{
  override val progressText:String get() = "${answered} of ${total} answered."
    override fun printProgressBar(){
    repeat(Quiz.answered){print("▓")}
  repeat(Quiz.total-Quiz.answered){print("▒")}
  println()
  println(progressText)
  }
  val question1 = Question<String>("What is your name", "Shiwei",Difficulty.Easy)
  val question2 = Question<Boolean>("1+1=3", false,Difficulty.Medium)
  val question3 =  Question<Int>("1+1", 2,Difficulty.Hard)

  companion object StudentProgress{
  var total : Int = 10
  var answered: Int = 3
}

  fun printQuiz(){
    question1.let()
    {
    println(it.questionText)
    println(it.answer)
    println(it.difficulty)
    }
    println()
        question2.let()
    {
    println(it.questionText)
    println(it.answer)
    println(it.difficulty)
    }
    println()
        question3.let()  
    {
    println(it.questionText)
    println(it.answer)
    println(it.difficulty)
    }
    println()
  }
}

data class Question<T>(
  val questionText:String,
  val answer:T,
  val difficulty:Difficulty
)

fun main() {
 // Quiz.printProgressBar()
  val quiz  = Quiz().apply(){
    printQuiz()
  }
}