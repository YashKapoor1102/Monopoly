Email: Yash.Kapoor@Carleton.ca
       Robert.Simionescu@Carleton.ca
       Oliver.Lu@Carleton.ca
       Himanshu.Singh@Carleton.ca
       
Description:
------------
- This program consists of a game called Funopoly that is a text-based playable version of Monopoly. 
Players can play the game via the console using the keyboard. 

The rules are simple. The user chooses the number of players that are going to play the game by entering a 
positive integer value. It must be greater than or equal to 2 because this game cannot be played with only 
one player. Then, they must enter the players' names, which must be unique (two players cannot have duplicate
names). Each player starts off with an initial amount of $1500.

Player 1 always starts first. Then, the remaining players complete their turn. All players start at an initial
starting point that is part of the gameboard. When it is a specific player's turn, they are prompted with a 
message to roll the dice. The result of their roll is displayed afterwards. If they roll doubles, they get to
roll again after their current turn is finished. After they roll, they will move a specific number of places
on the gameboard and land on a property. Once they land on a property, they have the option to buy it. However, 
if the player chooses not to buy a property, they can skip their turn and pass it to the next player. 

Each player has the option to view every players state on the board during their turn if they wish. 
Players shall not buy properties that are owned by another player or already owned by them. When 
other players land on a property is owned by another player, they must pay the other player 10% rent. 
However, if that specific player owns all the properties of the same color, the other player must 
pay them 20% rent. There is no change given in monopoly; therefore, the amount of money to be paid
by the player will always be rounded to the nearest whole number. 

Once a player no longer has enough money to pay the specified rent, they are bankrupt and eliminated 
from the game. Once all players are eliminated but one, that last player standing shall be the winner
of the game. 


- This program is made up of 8 files:

	Die.java		Allows the user to roll the dice
	Gameboard.java		Gameboard of Monopoly
	GameUI.java		Displays the user interface
	GameLogic.java		Responsible for all the logic of the game
	Player.java		Deals with all the players
	Property.java		Deals with all the properties
	Square.java		an interface outlining the characteristics of squares on the gameboard
	Street.java		a type of property


Installation:
-------------

- Most versions of Java will be able to run this game. A Java IDE such as IntelliJ is recommended. 

If IntelliJ is not already installed, use the following link that provides step-by-step instructions
on how to install it for popular operating systems:

https://www.jetbrains.com/help/idea/installation-guide.html#snap


Usage:
-------

Step 1: Extract all the files in Milestone1_Group38.zip mentioned above to a folder of your choice.

Step 2: Open the game of Funopoly in an IDE of your choice. 

Step 3: Run the program.

Step 4: Enter how many players you want to play the game (Must be greater than or equal to 2) and their names
	must be unique.

Step 5: Each player takes turns rolling their dice and buying properties/paying rent. All other
	necessary instructions are given in the console. 


List of Commands After Each Player has Rolled:

SP: Allows the player to view the state of any player of their choice by entering their name. 
    To view the state of where they are on the board after they roll their dice, 
    they must use this command and enter their name. 

BP: Allows the player to buy properties that the land on. 

PT: Allows the player to pass their turn once they roll their dice or after they buy the property that
    they landed on. More specifically, if the user does not wish to buy the property, they can skip their
    turn using this command.

Q: Allows the player to quit the game at any time.


Step 6: The game continues until one player remains standing and all other
	players have gone bankrupt. Have fun playing the game! 


Future Plans:
-------------

We hope to transform this text-based version of the game into a Graphical User Interface in 
the upcoming week. Additional features such as houses, hotels, and special properties such 
as "Jail", "Go", "Railroad", and "Utility" shall be added in the later stages of this project.
These squares should not be difficult to add since we created a "Square" Interface that 
allows us to add new squares to the gameboard. Right now, we have a Square listed as 
"Initial Starting Point", which shall get replaced by "Go" in the upcoming milestones.  

Eventually, we shall add a set of AI players as well that plays against the user. In the 
final part of this project, we are going to add that allow the user to save and load their 
current gameboard once they want to play again. Lastly, we shall transform this local version
of Monopoly into a set of international versions with custom street names, values, and currencies. 



Credits:
--------

Yash Kapoor		- Team Member

Robert Simionescu	- Team Member

Oliver Lu		- Team Member

Himanshu Singh		- Team Member



This README.txt file is written by Yash Kapoor. 