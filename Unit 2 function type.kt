fun main() {
 // val trickFunction =::tricks
 // val trick ={
 //   println("No treats!")
 // }
 // val treat :()->Unit = {
 //   println("Have a treat")
 // }
 // trickFunction()
 // tricks()
 // treat()
  val coins: (Int)-> String = {
  "$it quarters"}
  val treatFunction = trickOrTreat(false, coins)
  val trickFunction = trickOrTreat(true, null)
  repeat(4){
  treatFunction()
  }
  trickFunction()
}
fun tricks(){
    println("No treats!")
  }

fun trickOrTreat(isTrick:Boolean, extraTreat:((Int)->String)?):()-> Unit{
  val trick = {
    println("No treats")
  }
  val treat = {
    println("Have a treat")
  }
  if(isTrick){
    return trick
  }
  else{
    if(extraTreat!= null){
    println(extraTreat(5))
    }
    return treat
  }
}
