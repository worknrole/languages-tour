
#                                 RUBY
#                            by Worknrole
#_____________________________________________________________________
#*********************************************************************
#                               CLASSES
#*********************************************************************
# We don't need any accessors accept if we want to manage extra process

# BASKET .............................................................
class Basket
    attr_accessor :owner, :thief, :sleeper

    # Constructor with default values
    def initialize(owner, thief)
    	@owner = owner
    	@thief = thief
    	@sleeper = nil
	end

    # When the owner or the thief sleep in the basket, the owner is alerted
    # We don't need to check who has sleep and neither who is the owner (Dog?)
    # because ruby use the duck typing
	def sleepIn(sleeper)
		puts "# #{sleeper.name} in the basket"
		@sleeper = sleeper

		begin
			owner.alert(sleeper)
		rescue NoMethodError => e
			puts "<!> #{sleeper.who} class doesn't implement the alert() method"
		end
	end

    # Reverse owner and thief
	def reverseOwnerAndThief
		owner = @owner
		@owner = @thief
		@thief = owner
	end

end



# OWNER OR THIEF .....................................................
# Abstract class for Dog and Cat: OwnerOrThief
class OwnerOrThief
	attr_accessor :name, :age

    # Constructor with default values
	def initialize(name="Unknow", age=-1)
		@name = name
		@age = age
	end

    # Define which class is used
	def who
		return self.class.name
	end

    # Generic description
	def to_s
		"Owner or thief named #{@name} (age: #{@age})"
	end
end



# CAT ...............................................................
# Cat class has OwnerOrThief as parent
class Cat < OwnerOrThief
    attr_accessor :isSpecial

    # Constructor with default values
    def initialize(name="Kitty", age=-1, isSpecial=false)
    	super(name, age)
    	@isSpecial = isSpecial
	end

    # Cat description
	def to_s
		spec = "normal"
		if @isSpecial == true
			spec = "special"
		end
		return super + ". This is a #{spec} cat."
	end
end



# DOG ...............................................................
# Dog class has OwnerOrThief as parent
class Dog < OwnerOrThief
	attr_accessor :nbOfBiscuitEaten

    # Constructor with default values
    def initialize(name="Doggy", age=-1, nbOfBiscuitEaten=0.0)
    	super(name, age)
    	@nbOfBiscuitEaten = nbOfBiscuitEaten
	end

    # Alert the owner that someone (the kitty) sleeps in it basket
	def alert(sleeper)
		if sleeper.instance_of? Cat
			puts "# Dog alerted"
		end
	end

    # Dog description
	def to_s
		plurial = "s"
		if (@nbOfBiscuitEaten <= 0.0)
			plurial = ""
		end
		return super + ". This dog has #{@nbOfBiscuitEaten} biscuit#{plurial}."
	end
end




#*********************************************************************
#                               MAIN
#*********************************************************************
# Creating a basket, a owner and a thief
someCat = Cat.new
someDog = Dog.new "Lilly", 2, 2.5 # or spec: Dog.new age:2, nbOfBiscuitEaten:5
someBasket = Basket.new someDog, someCat

# Print 'someCat' and 'someDog' descriptions
puts "\n > Description ********************"
puts "#{someCat.who} > #{someCat}"
puts "#{someDog.who} > #{someDog}"

# Access and set attributes
puts "\n > Accessors **********************"
someCat.age = 1
puts "Kitty age: #{someCat.age} year"
puts "Special kitty ? #{someCat.isSpecial.to_s}"
puts "Biscuits eaten by dog: #{someDog.nbOfBiscuitEaten}"

# Call some basket methods
puts "\n > Tests calls ********************"
someBasket.sleepIn(someCat)
someBasket.sleepIn(someDog)

# Throw error: The kitty doesn't implements the alert method
puts "\n > Throw error ********************"
someBasket.reverseOwnerAndThief()
someBasket.sleepIn(someCat)
