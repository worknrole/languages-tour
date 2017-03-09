
//                               SCALA
//                            by Worknrole
//_____________________________________________________________________
//*********************************************************************
//                               CLASSES
//*********************************************************************

// BASKET .............................................................
/**
 * Class of a basket
 * Constructor with custom parameters
 */
class Basket(owner: OwnerOrThief, thief: OwnerOrThief) {
  private var mOwner: OwnerOrThief = owner
  private var mThief: OwnerOrThief = thief
  private var mSleeper: OwnerOrThief = null
  
  /**
   * When the owner or the thief sleep in the basket, the owner is alerted
   * 
   * Need to check if the owner can be alerted (= instance of Alarm)
   * then use a cast to call the specific method
   */
  def sleepIn(sleeper: OwnerOrThief) {
    println(s"# ${sleeper.mName} in the basket")
    this.mSleeper = sleeper
    
    if (mOwner.isInstanceOf[Alarm]) {
      var owner:Alarm = this.mOwner.asInstanceOf[Alarm]
      owner.alert(sleeper)
    }
  }
  
  /**
   * Reverse owner and thief.
   */
  def reverOwnerAndThief {
    println("\n >> Reverse owner and thief")
    var tmp = this.mOwner
    this.mOwner =this. mThief
    this mThief = tmp
  }
}



// OWNER OR THIEF ....................................................
/**
 * Interface for Owner who want to be alerted
 */
trait Alarm {
  /**
   * Alert the owner about the thief
   */
  def alert(sleeper: OwnerOrThief)
}



/**
 * Class of an owner or thief
 * Constructor with custom parameters
 */
abstract class OwnerOrThief(name: String, age: Int) {
  var mName: String = name
  var mAge: Int = age
  
  /**
   * Return the class name using reflection
   */
  def who: String = {
    this.getClass.getSimpleName
  }
  
  /**
   * Override toString method for describe a basic owner or thief
   */
  override def toString: String = {
    f"Owner or thief named $mName (age: $mAge)"
  }
}



// CAT ...............................................................
/**
 * Class of a Cat extending OwnerOrThief
 * Constructor with custom parameters
 */
class Cat(name: String, age: Int, isSpecial: Boolean) extends OwnerOrThief(name, age) {
  var mIsSpecial: Boolean = isSpecial
  
  /**
   * Constructor with no parameter
   */
  def this() {
    this("Kitty", 0, false)
  }
  
  /**
   * Override toString method for describe a Cat
   */
  override def toString: String = {
    var isSpecial = if (mIsSpecial) "special" else "normal"
    super.toString + f" > This is a ${isSpecial} cat."
  }
}



// DOG ...............................................................
/**
 * Class of a Dog extending OwnerOrThief
 * Constructor with custom parameters
 */
class Dog(name: String, age: Int, nbOfBiscuitEaten:Float) extends OwnerOrThief(name, age) with Alarm {
  var mNbOfBiscuitEaten: Float = nbOfBiscuitEaten
  
  /**
   * Constructor with no parameter
   */
  def this() {
    this("Dog", 0, 0.0f)
  }
  
  /**
   * Alert the owner that someone (the kitty) sleeps in it basket
   */
  def alert(sleeper: OwnerOrThief) {
    if (sleeper.isInstanceOf[Cat]) {
      println("Dog alerted")
    }
  }
  
  /**
   * Override toString method for describe a Dog
   */
  override def toString: String = {
    var plurial = if (mNbOfBiscuitEaten > 1) "s" else ""
    super.toString + f" > This dog has eaten ${mNbOfBiscuitEaten} biscuit${plurial}."
  }
}






//*********************************************************************
//                               MAIN
//*********************************************************************
// Creating a basket, a owner and a thief
object LanguageTour {
    def main(args: Array[String]): Unit = {
      val someCat = new Cat()
      val someDog = new Dog("Lilly", 2, 2.5f)
      val someBasket = new Basket(someDog, someCat)
      
      // Print 'someCat' and 'someDog' descriptions
      println("\n > Description ********************")
      someCat.mAge = 1
      println(s"${someCat.who} > $someCat")
      println(s"${someDog.who} > $someDog")
      
      // Access and set attributes
      println("\n > Accessors **********************")
      println(s"Kitty age: ${someCat.mAge} year")
      println(s"Special kitty ? ${someCat.mIsSpecial}")
      println("Biscuits eaten by Dog: ${someDog.mNbOfBiscuitEaten}")
      
      // Call some basket methods
      println("\n > Tests calls ********************")
      someBasket.sleepIn(someCat)
      someBasket.sleepIn(someDog)
      someBasket.reverOwnerAndThief
      someBasket.sleepIn(someCat)
    }
  }
