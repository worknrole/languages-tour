<?php
    
//                                PHP
//                            by Worknrole
//_____________________________________________________________________
//*********************************************************************
//                               CLASSES
//*********************************************************************

// BASKET .............................................................
/**
 * Class of a basket
 */
class Basket {
    /**
     * Attributes
     */
    private $owner;
    private $thief;
    private $sleeper;

    /**
     * Constructor with custom parameters
     */
    public function __construct($owner, $thief) {
          $this->owner = $owner;
          $this->thief = $thief;
          $this->sleeper = null;
    }

    /**
     * When the owner or the thief sleep in the basket, the owner is alerted
     * 
     * Need to check if the owner can be alerted (= instance of Alarm)
     * then use a cast to call the specific method
     */
    public function sleepIn(OwnerOrThief $sleeper) {
        echo sprintf("# %s sleeps in the basket", $sleeper->getName());
        if ($this->owner instanceof Alarm) {
            $this->owner->alert($sleeper);
        }
    }

    /**
     * Reverse owner and thief.
     */ 
    public function reverseOwnerAndThief() {
        echo ">> Reverse owner and thief";
        $tmp = $this->owner;
        $this->owner = $this->thief;
        $this->thief = $tmp;
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
    public function alert($sleeper);
}



/**
 * Class of an owner or thief
 */
class OwnerOrThief {   
    /**
     * Attributes
     */
    protected $name;
    protected $age;
    
    /**
     * Constructor with custom parameters
     */
    public function __construct($name = "Unknown", $age = 0) {
        $this->name = $name;
        $this->age = $age;
    }
    
    /**
     * Accessors
     */
    public function getName() { return $this->name; }
    public function setName($value) { $this->name = $value; }
    public function getAge() { return $this->age; }
    public function setAge($value) { $this->age = $value; }

    /**
     * Return the class name using reflection
     */
    public function who() {
        return get_class($this);
    }
    
    /**
     * Override toString method for describe a basic owner or thief
     */
    public function __toString() {
        return sprintf('Owner or thief named %s (age: %d)', $this->name, $this->age);
    }
}




// CAT ...............................................................
/**
 * Class of a Cat extending OwnerOrThief
 */
class Cat extends OwnerOrThief {
    /**
     * Attributes
     */
    private $isSpecial;
    
    /**
     * Constructor with custom parameters
     */
    public function __construct($name = "Kitty", $age = 0, $isSpecial = false) {
        $this->isSpecial = $isSpecial;   
        parent::__construct($name, $age);
    }
    
    /**
     * Accessors
     */
    public function isSpecial() { return $this->isSpecial; }
    public function setIsSpecial($value) { $this->isSpecial = $value; }
    
    /**
     * Override toString method for describe a Cat
     */
    public function __toString() {
        return sprintf("%s > This is a %s Cat.", parent::__toString(), ($this->isSpecial ? "special" : "normal"));
    }
}



// DOG ...............................................................
/**
 * Class of a Dog extending OwnerOrThief
 */
class Dog extends OwnerOrThief implements Alarm {
    /**
     * Attributes
     */
    private $nbOfBiscuitEaten;
    
    /**
     * Constructor with custom parameters
     */
    public function __construct($name = "Dog", $age = 0, $nbOfBiscuitEaten = 0.0) {
        $this->nbOfBiscuitEaten = $nbOfBiscuitEaten;
        parent::__construct($name, $age);
    }
    
    /**
     * Accessors
     */
    public function getNbOfBiscuitEaten() { return $this->nbOfBiscuitEaten; }
    public function setNbOfBiscuitEaten($value) { $this->nbOfBiscuitEaten = $value; }
    
    /**
     * Alert the owner that someone (the kitty) sleeps in it basket
     */
    public function alert($sleeper) {
        if ($sleeper instanceof Cat) {
        echo "<br># Dog alerted.";
        }
    }
    
    /**
     * Override toString method for describe a Dog
     */
    public function __toString() {
        return sprintf("%s > This dog has eaten %.2f biscuit%s!", parent::__toString(), 
            $this->nbOfBiscuitEaten, ($this->nbOfBiscuitEaten > 1 ? "s" : ""));
    }
}






//*********************************************************************
//                               MAIN
//*********************************************************************
// Creating a basket, a owner and a thief
$someCat = new Cat("Kitty", 1, true);
$someDog = new Dog("Lilly", 2, 2.50);
$someBasket = new Basket($someDog, $someCat);

// Print 'someCat' and 'someDog' descriptions
echo "> Description ********************";
$someCat->setAge(1);
echo sprintf("<br>%s > %s", $someCat->who(), $someCat);
echo sprintf("<br>%s > %s", $someDog->who(), $someDog);

// Access and set attributes
echo "<br><br> > Accessors **********************";
echo sprintf("<br>Kitty age: %d year", $someCat->getAge());
echo sprintf("<br>Special kitty ? %s", $someCat->isSpecial() ? "true" : "false");
echo sprintf("<br>Biscuits eaten by Dog: %.2f", $someDog->getNbOfBiscuitEaten());

// Call some basket methods
echo "<br><br> > Tests calls ********************";
echo "<br>";
$someBasket->sleepIn($someCat);
echo "<br>";
$someBasket->sleepIn($someDog);
echo "<br><br>";
$someBasket->reverseOwnerAndThief();
echo "<br>";
$someBasket->sleepIn($someCat);
echo "\n";

?>
