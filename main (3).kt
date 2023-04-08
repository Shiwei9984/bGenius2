fun main() {
  val morningNotification = 51
  val eveningNotification = 135
  val child = 5
  val adult = 28
  val senior = 87
  val isMonday = true
  val finalTemp: (Double)->Double = {80.6}
  val finalTemp2: (Double)->Double = {76.85}
  val finalTemp3: (Double)->Double = {260.93}
  val amanda = Person("Amanda", 33, "plat tennis", null)
  val atiqah = Person("Atiqah", 28, "climb", amanda)
  val newPhone = FoldablePhone()
  newPhone.switchOn()
  newPhone.checkPhoneScreenLight()
  newPhone.unfold()
  newPhone.switchOn()
  newPhone.checkPhoneScreenLight()
  amanda.showProfile()
  atiqah.showProfile()
  printNotificationSummary(morningNotification)
  printNotificationSummary(eveningNotification)
  println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
  println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
  println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")
  printFinalTemperature(27.0,"Celsius", "Fahrenheit",finalTemp)
  printFinalTemperature(350.0,"Kelvin", "Celsius",finalTemp2)
  printFinalTemperature(10.0,"Fahrenheit", "Kelvin",finalTemp3)
}

fun printNotificationSummary(message:Int){
  if(message>=100){
    println("Your phone is blowing up! You have 99+ notifications")
  }
  else{
    println("You have $message notifications.")
  }
}

fun ticketPrice(age: Int, isMonday:Boolean): Int{
  if(age>=1&&age<=12){
    return 15
  }
  else if(age>=13&&age<=60){
    if(isMonday){
      return 25
    }
    else{
      return 28
    }
  }
  else if(age>=61){
    return 20
  }
  else{
    return -1
  }
}

fun printFinalTemperature(
  initialMeasurement: Double,
  initialUnit:String,
  finalUnit: String,
  conversionFormula:(Double)->Double){
   val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement))
   println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
  }

class Person(val name: String, val age: Int, val hobby: String?, val referrer:Person?){
  fun showProfile(){
    println("Name: $name")
    println("Age: $age")
    if(referrer!=null){
      if(hobby!=null){
        println("Like to $hobby. Has a referred named ${referrer.name}, who likes ${referrer.hobby}")
      }
      else{
        println("Don't have a hobby.Has a referred named ${referrer.name}, who likes ${referrer.hobby}")
      }
    }
    else{
      if(hobby!=null){
        println("Like to $hobby. Doesn't have a referrer")
      }
      else{
        println("Don't have a hobby.Doesn't have a referrer")
      }
    }
  }
}

open class Phone(var isScreenLightOn :Boolean = false){
  fun switchOn(){
    isScreenLightOn = true
  }
  fun switchOff(){
    isScreenLightOn = false
  }
  fun checkPhoneScreenLight(){
    val phoneScreenLight = if(isScreenLightOn)"on" else "off"
    println("The phone screen's light is $phoneScreenLight")
  }
}

class FoldablePhone(isFolded: Boolean = true): Phone(){
    override fun switchOn(){
    if(!isFolded){
      isScreenLightOn = true
    }

    fun fold(){
      isFolded = true
    }
    fun unfold(){
      isFolded = false
    }
  }
}
