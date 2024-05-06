Description:
------------
This project is a Java-based implementation of the classic board game Monopoly. It features a fully 
functional game environment where players can interact with the game board, manage properties, and
compete against other players controlled by AI or other human players. The game includes features
like property buying, building houses, managing funds, and following the standard rules of Monopoly.
This version of the game uses a graphical user interface to make it easier and more enjoyable 
for players to interact with the game. 

The users can either use a regular monopoly game board or an international game board. 
For the international game board, we chose to do a Nintendo-themed Monopoly game board. 
This board features unique properties, each priced differently. 

Installation:
-------------
Most versions of Java will be able to run this program, but JDK 11 or higher is recommended 
for optimal performance.

For those new to Java development or needing an IDE, Eclipse or IntelliJ IDEA are
recommended as well.

If you don't have Eclipse or IntelliJ installed, you can follow the step-by-step instructions
provided in the links below to install them on various operating systems:

**Eclipse Installation:**
https://www.eclipse.org/downloads/packages/installer

**IntelliJ Installation:**
https://www.jetbrains.com/idea/download

Usage:
------
**Step 1:** Clone the repository:
```
git clone https://github.com/YashKapoor1102/Monopoly-Game-Engine.git
```
**Step 2:** Navigate to the project directory:
```
cd Monopoly-Game-Engine
```
**Step 3:** Compile the Java files:
```
javac GameFrame.java GameController.java Player.java Property.java Square.java Utility.java Railroad.java GameModel.java
```
**Step 4:** Execute the file containing the main method, which is the "GameFrame.java" file
in this project. 
```
java GameFrame
```

Once you get the game running, you can:
- Choose between two versions of game boards (Nintendo-themed game board or regular game board).
- Roll the dice to move around the board.
- Buy properties when you land on them.
- Pay rent to other players if they own the property you land on.
- Use the game's interface to add AI, build houses, pass turn, and get out of jail if required.
- You can also save the game's state, exit, and then load your saved game when you open it again.

Once the game begins between X players, each player starts with $1500. The last player remaining
that does not go bankrupt (money does not equal $0) wins.

Credits:
--------
Yash Kapoor  
Robert Simionescu  
Himanshu Singh
