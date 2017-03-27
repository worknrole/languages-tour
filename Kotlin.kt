//                               KOTLIN
//                            by Worknrole
//_____________________________________________________________________
//*********************************************************************
//                               CLASSES
//*********************************************************************

// BASKET .............................................................
// Default constructor with an optional attribute
class Basket(var owner: OwnerOrThief, var thief: OwnerOrThief) {
    var sleeper: OwnerOrThief? = null
    
    init {
        // This is the initialize block
    }
    
    /**
    * When the owner or the thief sleep in the basket, the owner is alerted
    * 
    * Need to check if the owner can be alerted (= instance of Alarm)
    * then use a cast to call the specific method
    */
    fun sleepIn(sleeper: OwnerOrThief) {
        println("${sleeper.name} in the basket")
        this.sleeper = sleeper
        
        if (this.owner is Alarm) {
            val currentOwner: Alarm = this.owner as Alarm
            currentOwner.alert(sleeper)
            
            // if this.owner is in read-only (= val not var)
            // it's automatically cast to Alarm after the if condition
        }
    }
  
    /**
    * Reverse owner and thief.
    */
    fun reverseOwnerAndThief() {
        println(">> Reverse owner and thief")
        val tmp = this.owner
        this.owner = this.thief
        this.thief = tmp
    }
}



// OWNER OR THIEF ....................................................
/**
 * Interface for Owner who want to be alerted
 */
interface Alarm {
    /**
    * Alert the owner about the thief
    */
    fun alert(sleeper: OwnerOrThief) { /* Can create default body */ }
}

/**
 * Class of an owner or thief
 */
open class OwnerOrThief {
    var name: String = "Unknown"
    var age: Int = 0
    
    /**
    * Constructor with no parameter
    */
    constructor()
    
    /**
    * Constructor with custom parameters
    */
    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
  
    /**
    * Return the class name using reflection
    */
    fun who(): String {
        return this::class.java.name
    }
  
    /**
    * Override toString method for describing a basic owner or thief
    */
    override fun toString() = 
        "Owner or thief named ${this.name} (age: ${this.age})"
}



// CAT ...............................................................
/**
 * Class of a Cat extending OwnerOrThief
 */
class Cat : OwnerOrThief {
    var isSpecial: Boolean = false
    
    /**
    * Cconstructor with no parameter
    */
    constructor() : super() {
        this.name = "Kitty"
    }
    
    /**
    * Constructor with custom parameters
    */
    constructor(name: String, age: Int, isSpecial: Boolean) : super(name, age) {
        this.isSpecial = isSpecial
    }
    
      
    /**
    * Override toString method for describing a Cat
    */
    override fun toString() = 
        "${super.toString()} > This is a ${if (this.isSpecial) "special" else "normal"} cat."
}



// DOG ...............................................................
/**
 * Class of a Dog extending OwnerOrThief
 */
class Dog : OwnerOrThief, Alarm {
    var nbOfBiscuitEaten: Float = 0.0f
    
    /**
    * We create a constructor with no parameter
    */
    constructor() : super()
    
    /**
    * We create a constructor with custom parameters
    */
    constructor(name: String, age: Int, nbOfBiscuitEaten: Float) : super(name, age) {
        this.nbOfBiscuitEaten = nbOfBiscuitEaten
    }
    
    /**
    * Alert the owner that someone (the kitty) sleeps in it basket
    */
    override fun alert(sleeper: OwnerOrThief) {
        if (sleeper is Cat) {
            println("Dog alerted")
        }
    }
      
    /**
    * Override toString method for describing a Dog
    */
    override fun toString() = 
        "${super.toString()} > This dog has eaten ${this.nbOfBiscuitEaten} " +
        "biscuit${if (this.nbOfBiscuitEaten > 0) "s" else ""}!"
}






//*********************************************************************
//                               MAIN
//*********************************************************************
fun main(args: Array<String>) {
    // Creating a basket, a owner and a thief
    val someCat = Cat()
    val someDog = Dog("Lilly", 2, 2.5f)
    val someBasket = Basket(someDog, someCat)

    // Print 'someCat' and 'someDog' descriptions
    println("\n> Description ********************")
    someCat.age = 1
    println("${someCat.who()} > ${someCat}")
    println("${someDog.who()} > ${someDog}")
      
    // Access and set attributes
    println("\n> Accessors **********************")
    println("Kitty age: ${someCat.age} year")
    println("Special kitty ? ${someCat.isSpecial}")
    println("Biscuits eaten by Dog: ${someDog.nbOfBiscuitEaten}")
    
    // Call some basket methods
    println("\n> Tests calls ********************")
    someBasket.sleepIn(someCat)
    someBasket.sleepIn(someDog)
}
