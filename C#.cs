// 									C#
// 							   by Worknrole
// _____________________________________________________________________
//*********************************************************************
// 								  CLASSES
//*********************************************************************
using System;

namespace languagetour
{
	// BASKET .........................................................
	class Basket
	{
		private OwnerOrThief mOwner;
		private OwnerOrThief mThief;
		private OwnerOrThief mSleeper;

		/// <summary>
		/// Initializes a new instance of the <see cref="T:languagetour.Basket"/> class.
		/// It calls an another constructor to initialize itself
		/// </summary>
		public Basket() : this(null, null)
		{
		}

		/// <summary>
		/// Initializes a new instance of the <see cref="T:languagetour.Basket"/> class using to parameters
		/// </summary>
		/// <param name="owner">Owner: The basket owner</param>
		/// <param name="thief">Thief: The basket thief</param>
		public Basket(OwnerOrThief owner, OwnerOrThief thief)
		{
			this.mOwner = owner;
			this.mThief = thief;
			this.mSleeper = null;
		}

		/// <summary>
		/// Releases unmanaged resources and performs other cleanup operations before the <see cref="T:languagetour.Basket"/> is
		/// reclaimed by garbage collection.
		/// </summary>
		~Basket()
		{
		}

		/// <summary>
		/// When the owner or the thief sleep in the basket, the owner (or IAlarm) is alerted
		/// </summary>
		/// <param name="sleeper">Current basket sleeper.</param>
		public void sleepIn(OwnerOrThief sleeper)
		{
			Console.WriteLine("# " + sleeper.Name + " sleeps in the basket.");
			this.mSleeper = sleeper;

			if (mOwner is IAlarm)
			{
				((IAlarm)mOwner).alert(sleeper);
			}
		}

		/// <summary>
		/// Reverses the owner and thief.
		/// </summary>
		public void reverseOwnerAndThief()
		{
			OwnerOrThief tmp = this.mOwner;
			this.mOwner = this.mThief;
			this.mThief = tmp;
		}

		/// <summary>
		/// Accessors for owner.
		/// </summary>
		/// <value>The new owner.</value>
		public OwnerOrThief Owner
		{
			get { return mOwner; }
			set { mOwner = value; }
		}

		/// <summary>
		/// Accessors for thief.
		/// </summary>
		/// <value>The new thief.</value>
		public OwnerOrThief Thief
		{
			get { return mThief; }
			set { mThief = value; }
		}
	}



	// OWNER OR THIEF ................................................
	// This is some usage of an interface
	interface IAlarm
	{
		void alert(OwnerOrThief sleeper);
	}



	// Abstract class for Dog and Cat: OwnerOrThief implements the corresponding interface
	abstract class OwnerOrThief
	{
		/// <summary>
		/// Name of the owner or thief
		/// </summary>
		private string mName;

		/// <summary>
		/// Age of the owner or thief
		/// </summary>
		private int mAge;

		/// <summary>
		/// Initializes a new instance of the <see cref="T:languagetour.OwnerOrThief"/> class.
		/// </summary>
		/// <param name="name">Name of the owner or thief</param>
		/// <param name="age">Age of the owner or thief</param>
		public OwnerOrThief(string name, int age)
		{
			this.mName = name;
			this.mAge = age;
		}

		/// <summary>
		/// Define which class is used
		/// </summary>
		/// <returns>OwnerOrThief class name</returns>
		public string who()
		{
			return this.GetType().Name;
		}

		/// <summary>
		/// Return the definition of an <see cref="T:languagetour.OwnerOrThief"/>.
		/// This method is virtual to let all children classes override it
		/// </summary>
		/// <returns>Return the definition of an <see cref="T:languagetour.OwnerOrThief"/>.</returns>
		public virtual string ToString()
		{
			return "Owner or thief named " + mName + " (age: " + mAge +")";
		}

		/// <summary>
		/// Accessors for name.
		/// </summary>
		/// <value>The new name.</value>
		public string Name
		{
			get { return mName; }
			set { mName = value; }
		}

		/// <summary>
		/// Accessors for age.
		/// </summary>
		/// <value>The new age.</value>
		public int Age
		{
			get { return mAge; }
			set { mAge = value; }
		}
	}



	// CAT ..........................................................
	// Cat class has OwnerOrThief as parent
	class Cat : OwnerOrThief
	{
		/// <summary>
		/// Knowing of the Cat is special or not.
		/// </summary>
		private bool mIsSpecial;

		public Cat() : this("Kitty", -1, false)
		{
		}

		/// <summary>
		/// Initializes a new instance of the <see cref="T:languagetour.Cat"/> class.
		/// </summary>
		/// <param name="name">Name of the owner or thief</param>
		/// <param name="age">Age of the owner or thief</param>
		public Cat(string name, int age, bool isSpecial) : base(name, age)
		{
			this.mIsSpecial = isSpecial;
		}

		/// <summary>
		/// Favorites the foods with an arbitrary number of parameters
		/// </summary>
		/// <param name="foods">Foods.</param>
		public void favoriteFoods(params string[] foods)
		{
			string res = "FavoriteFoods: ";
			foreach (string food in foods)
			{
				res += food + " ";
			}
			Console.WriteLine(res);
		}

		/// <summary>
		/// Return the definition of an <see cref="T:languagetour.Cat"/>.
		/// </summary>
		/// <returns>Return the definition of an <see cref="T:languagetour.Cat"/>.</returns>
		public override string ToString()
		{
			return base.ToString() + " > This is a " + (mIsSpecial ? "special" : "normal") + " cat.";
		}

		/// <summary>
		/// Accessors for speciality.
		/// </summary>
		/// <value>The new speciality.</value>
		public bool IsSpecial
		{
			get { return mIsSpecial; }
			set { mIsSpecial = value; }
		}
	}



	// DOG ..........................................................
	// Dog class has OwnerOrThief as parent
	class Dog : OwnerOrThief, IAlarm
	{
		/// <summary>
		/// The nb of biscuit eaten by the dog
		/// </summary>
		private float mNbOfBiscuitEaten;

		/// <summary>
		/// Initializes a new instance of the <see cref="T:languagetour.Dog"/> class.
		/// </summary>
		/// <param name="name">Name of the owner or thief</param>
		/// <param name="age">Age of the owner or thief</param>
		public Dog(string name, int age, float nbOfBiscuitEaten) : base(name, age)
		{
			this.mNbOfBiscuitEaten = nbOfBiscuitEaten;
		}

		/// <summary>
		/// Alert dog that a thief sleeps in it basket
		/// </summary>
		/// <param name="sleeper">The current basket sleeper</param>
		public void alert(OwnerOrThief sleeper)
		{
			if (sleeper is Cat)
			{
				Console.WriteLine("# Dog alerted");
			}
		}

		/// <summary>
		/// Return the definition of an <see cref="T:languagetour.Dog"/>.
		/// </summary>
		/// <returns>Return the definition of an <see cref="T:languagetour.Dog"/>.</returns>
		public override string ToString()
		{
			return base.ToString() + " > This dog has eaten " + this.mNbOfBiscuitEaten + " biscuit" + (mNbOfBiscuitEaten > 1 ? "s" : "");
		}

		/// <summary>
		/// Accessors for the number of biscuit eaten.
		/// </summary>
		/// <value>The new number of biscuit eaten.</value>
		public float NbOfBiscuitEaten
		{
			get { return mNbOfBiscuitEaten; }
			set { mNbOfBiscuitEaten = value; }
		}
	}



	//******************************************************************
	//                               MAIN
	//******************************************************************
	// Creating a basket, a owner and a thief
	class AppMain
	{
		static void Main(string[] args)
		{
			// Creating a basket, a owner and a thief
			Cat someCat = new Cat();
			Dog someDog = new Dog("Lilly", 2, 2.5f);
			Basket someBasket = new Basket(someDog, someCat);

			// Access and set attributes
			Console.WriteLine("\n > Description ********************");
			Console.WriteLine(someCat.who() + " > " + someCat.ToString());
			Console.WriteLine(someDog.who() + " > " + someDog.ToString());

			// Print 'someCat' and 'someDog' descriptions
			Console.WriteLine("\n > Accessors **********************");
			Console.WriteLine("Kitty age: " + someCat.Age + " year");
			Console.WriteLine("Special kitty ? " + someCat.IsSpecial);
			Console.WriteLine("Biscuits eaten by Dog: " + someDog.NbOfBiscuitEaten);

			// Call some basket methods
			Console.WriteLine("\n > Tests calls *********************");
			someBasket.sleepIn(someCat);
			someBasket.sleepIn(someDog);
		}
	}
}