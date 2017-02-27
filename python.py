#*********************************************************************
#                               CLASSES
#                            by Worknrole
#*********************************************************************
# We don't need any accessors accept if we want to manage extra process

# BASKET .............................................................
class Basket(object):
    # Constructor with default values
    def __init__(self, owner=None, thief=None):
        self.owner = owner
        self.thief = thief
        self.sleeper = None

    # When the owner or the thief sleep in the basket, the owner is alerted
    # We don't need to check who has sleep in neither who is the owner (Dog?)
    # because python use the duck typing
    def sleepIn(self, sleeper):
        print("# %s sleeps in the basket" % (sleeper.name))
        self.sleeper = sleeper

        try:
            self.owner.alert(sleeper)
        except (AttributeError, TypeError):
            print("<!> %s class doesn't implement the alert() method" % (sleeper.who()))

    # Reverse owner and thief.
    # Use can use help() for this method
    def reverseOwnerAndThief(self):
        '''
            This method reverse the owner and the thief
        '''
        owner = self.owner
        self.owner = self.thief
        self.thief = owner



# OWNER OR THIEF .....................................................
# Abstract class for Dog and Cat: OwnerOrThief
class OwnerOrThief(object):
    # Constructor with default values
    def __init__(self, name="Unknow", age=-1):
        self.name = name
        self.age = age

    # Dog description
    def who(self):
        return type(self).__name__ # == self.__class__.__name__

    # Generic description
    def __str__(self):
        return "Owner or thief named {0} (age: {1})".format(self.name, self.age)



# CAT ...............................................................
# Cat class has OwnerOrThief as parent
class Cat(OwnerOrThief):
    # Constructor with default values
    def __init__(self, name="Kitty", age=-1, isSpecial=False):
        super().__init__(name, age)
        self.isSpecial = isSpecial

    # Cat description
    def __str__(self):
        return super().__str__() + " > This is a %s cat." \
            %("Special" if self.isSpecial == True else "Normal")



# DOG ...............................................................
# Dog class has OwnerOrThief as parent
class Dog(OwnerOrThief):
    # Constructor with default values
    def __init__(self, name="Doggy", age=-1, nbOfBiscuitEaten=0.0):
        super().__init__(name, age)
        self.nbOfBiscuitEaten = nbOfBiscuitEaten

    # Alert the owner that someone (the kitty) sleeps in it basket
    def alert(self, sleeper):
        if isinstance(sleeper, Cat):
            print("# Dog alerted")

    # Dog description
    def __str__(self):
        return super().__str__() + " > This dog has eaten %.1f biscuit%s !" \
            %(self.nbOfBiscuitEaten, "s" if self.nbOfBiscuitEaten > 0.0 else "")






#*********************************************************************
#                               MAIN
#*********************************************************************
# Creating a basket, a owner and a thief
someCat = Cat()
someDog = Dog("Lilly", 2, 2.5)
someBasket = Basket(someDog, someCat)

# Print 'someCat' and 'someDog' descriptions
print("\n > Description ********************")
print(" %s > %s" % (someCat.who(), someCat))
print(" %s > %s" % (someDog.who(), someDog))

# Access and set attributes
print("\n > Accessors **********************")
someCat.age = 1
print("Kitty age: %i year" % (someCat.age))
print("Special kitty ? %r" % (someCat.isSpecial))
print("Biscuits eaten by Dog: %.1f" % (someDog.nbOfBiscuitEaten))

# Call some basket methods
print("\n > Tests calls ********************")
someBasket.sleepIn(someCat)
someBasket.sleepIn(someDog)

# Throw error: The kitty doesn't implements the alert method
print("\n > Throw error ********************")
someBasket.reverseOwnerAndThief()
someBasket.sleepIn(someCat)

# docstring is a cool stuff the know what a method does
print("\n > Help on methods ****************")
help(someBasket.reverseOwnerAndThief)