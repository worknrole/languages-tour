
//                                SWIFT
//                            by Worknrole
//_____________________________________________________________________
//*********************************************************************
//                               CLASSES
//*********************************************************************

// BASKET .............................................................

class Basket {
	var mOwner: OwnerOrThief
	var mThief: OwnerOrThief
	var mSleeper: OwnerOrThief? = nil
	
	/**
	* Constructor with custom parameters
	* mSleeper is optional
	*/
	init(owner: OwnerOrThief, thief: OwnerOrThief) {
		self.mOwner = owner
		self.mThief = thief
	}
	
	/**
	* When the owner or the thief sleep in the basket, the owner is alerted
	* 
	* Need to check if the owner can be alerted (= instance of Alarm)
	* then use a cast to call the specific method
	*/
	func sleepIn(sleeper: OwnerOrThief) {
		print("\n \(sleeper.mName) sleeps in the basket")
		self.mSleeper = sleeper
		
		if let owner = mOwner as? Alarm {
			owner.alert(sleeper: sleeper)
		}
	}
  
	/**
	* Reverse owner and thief.
	*/
	func reverseOwnerAndThief() {
		print("\n >> Reverse owner and thief")
		let tmp = self.mOwner
		self.mOwner = self.mThief
		self.mThief = tmp
	}
}



// OWNER OR THIEF ....................................................
/**
 * Interface for Owner who want to be alerted
 */
protocol Alarm {
	/**
	* Alert the owner about the thief
	*/
	func alert(sleeper: OwnerOrThief)
}

/**
 * Class of an owner or thief
 * Extends CustomStringConvertible to use the 'description' function
 */
class OwnerOrThief : CustomStringConvertible {
	var mName: String
	var mAge: Int
	
	/**
	* Constructor with no parameter
	*/
	init() {
		self.mName = "Unknown"
		self.mAge = 0
	}
	
	/**
	* Constructor with custom parameters
	*/
	init(name: String, age: Int) {
		self.mName = name
		self.mAge = age
	}
  
	/**
	* Return the class name using reflection
	*/
	func who() -> String {
		return String(describing: type(of: self))
	}
  
	/**
	* Override toString method for describe a basic owner or thief
	*/
	var description: String {
		return "Owner or thief named \(self.mName) (age: \(self.mAge))"
	}
}



// CAT ...............................................................
/**
 * Class of a Cat extending OwnerOrThief
 */
class Cat : OwnerOrThief {
	var mIsSpecial: Bool
	
	/**
	* Cconstructor with no parameter
	*/
	override init() {
		self.mIsSpecial = false
		super.init()
	}
	
	/**
	* Constructor with custom parameters
	*/
	init(name: String, age: Int, isSpecial: Bool) {
		self.mIsSpecial = isSpecial // Init child properties first
		super.init(name: name, age: age)
	}
	
	  
	/**
	* Override toString method for describe a Cat
	*/
	override var description: String {
		return "\(super.description) > This if a \(self.mIsSpecial ? "special" : "normal") cat."
	}
}



// DOG ...............................................................
/**
 * Class of a Dog extending OwnerOrThief
 */
class Dog : OwnerOrThief, Alarm {
	var mNbOfBiscuitEaten: Float
	
	/**
	* We create a constructor with no parameter
	*/
	override init() {
		self.mNbOfBiscuitEaten = 0.0 // Init child properties first
		super.init()
	}
	
	/**
	* We create a constructor with custom parameters
	*/
	init(name: String, age: Int, nbOfBiscuitEaten: Float) {
		self.mNbOfBiscuitEaten = nbOfBiscuitEaten // Init child properties first
		super.init(name: name, age: age)
	}
	
	/**
	* Alert the owner that someone (the kitty) sleeps in it basket
	*/
	func alert(sleeper: OwnerOrThief) {
		if sleeper is Cat {
			print("# Dog alerted")
		}
	}
	  
	/**
	* Override toString method for describe a Dog
	*/
	override var description: String {
		return "\(super.description) > This dog has eaten \(self.mNbOfBiscuitEaten) biscuit\(self.mNbOfBiscuitEaten > 1 ? "s" : "")!"
	}
}






//*********************************************************************
//                               MAIN
//*********************************************************************
// Creating a basket, a owner and a thief
let someCat = Cat()
let someDog = Dog(name: "Lilly", age: 2, nbOfBiscuitEaten: 2.5)
let someBasket = Basket(owner: someDog, thief: someCat)

// Print 'someCat' and 'someDog' descriptions
print("> Description ********************")
someCat.mAge = 1
print("\(someCat.who()) > \(someCat)")
print("\(someDog.who()) > \(someDog)")
      
// Access and set attributes
print(" ")
print("> Accessors **********************")
print("Kitty age: \(someCat.mAge) year")
print("Special kitty ? \(someCat.mIsSpecial)")
print("Biscuits eaten by Dog: \(someDog.mNbOfBiscuitEaten)")
      
// Call some basket methods
print(" ")
print("> Tests calls ********************")
someBasket.sleepIn(sleeper: someCat)
someBasket.sleepIn(sleeper: someDog)
someBasket.reverseOwnerAndThief()
someBasket.sleepIn(sleeper: someCat)
