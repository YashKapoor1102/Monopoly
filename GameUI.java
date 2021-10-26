/**
 * @author Yash Kapoor (Student ID: 101163338)
 * @version Milestone 1
 */

import java.util.*;

/**
 * GameUI class creates the User Interface for this game.
 * Displays all the necessary messages and commands for the user to play the game.
 */
public class GameUI {



    /**
     * @author Yash Kapoor
     * default constructor for a GameUI
     */
    public GameUI() {
    }


    /**
     * @author Robert Simionescu
     * Displays a message to the user. In the current milestone, this is just a print statement. In future milestones,
     * it may be a textbox or something eslse.
     * @param message The message to be displayed.
     */
    public void displayMessage(String message)
    {
        System.out.println(message);
    }


    /**
     * @author Oliver Lu and Robert Simionescu
     * Prompts the user to type in the roll command until they successfully do so.
     */
    public void displayRollCommand()
    {
        Scanner roll = new Scanner(System.in);

        while (true) {
            // accounting for errors (if user enters wrong command)

            System.out.println("Type \"R\" to roll your dice: ");

            if (roll.next().equalsIgnoreCase("R")) {
                return;
            }
            else
            {
                roll.nextLine();
                System.out.println("\nYou must enter \"R\" to roll your dice. Try again!\n");
            }
        }
    }


    /**
     * @author Yash Kapoor and Robert Simionescu
     * Allows the user to enter the number of players that are going to be playing the game
     * Adds each player to the ArrayList of players.
     * @return          an ArrayList, list of players
     */
    public ArrayList<Player> displayPlayerSelection() {
        ArrayList<Player> players = new ArrayList<Player>();

        Scanner numOfPlayers = new Scanner(System.in);

        int num = 0;

        boolean noError = false;

        do {
            // do-while accounts for errors (if user enters a wrong command)
            System.out.println("Enter how many players you want to play this game: ");
            if (numOfPlayers.hasNextInt()) {
                num = numOfPlayers.nextInt();
                noError = true;
                if (num <= 1) {
                    System.out.println("\nYou must enter a valid positive integer. Try again!");
                    System.out.println("This game must be played with two or more players.\n");
                    noError = false;
                }
            } else {
                numOfPlayers.nextLine();
                System.out.println("\nYou must enter a valid positive integer. Try again!\n");
            }
        } while (!noError);

        Scanner name = new Scanner(System.in);

        for (int n = 0; n < num; n++) {
            // goes through number of players and asks them their name
            System.out.println("\nWhat is Player " + (n + 1) + "'s name? ");
            String n1 = name.nextLine();

            boolean duplicate = false;
            for(int i = 0; i < players.toArray().length; i++)
            // ensuring there are no duplicate names in the list of players
            {
                String playerName = players.get(i).getName();
                if(playerName.equalsIgnoreCase(n1)) {
                    // name for each player must be different
                    duplicate = true;
                    System.out.println("\nPlease enter a different name! No duplicates!");
                    break;
                }
            }

            if(!duplicate) {

                players.add(new Player(n1, 1500));

            }
            else {
                n--;
            }

        }
        return players;
    }


    /**
     * @author Yash Kapoor
     * Printing all the squares on the gameboard
     * to show the user what the entire gameboard looks like.
     */
    public void printBoard(Gameboard gameboard, ArrayList<Player> players) {

        System.out.println("\nEntire gameboard:\n");

        for (Square square : gameboard.getSquares())
        {
            System.out.print(square);
            if (square instanceof Property)
            {
                if (((Property) square).getOwner() == (null)) {
                    System.out.print(" | Cost: $" + ((Property) square).getCost());
                }
                else
                {
                    System.out.print(" | Owner: " + ((Property) square).getOwner().getName());
                    System.out.print(" | Rent: $" + ((Property) square).calculateRent(gameboard));
                }
            }
            ArrayList<Player> playersOnSquare = new ArrayList<>();
            for (Player player : players)
            {

                if (gameboard.getSquare(player.getPosition()) == square)
                {
                    playersOnSquare.add(player);
                }
            }
            if (!playersOnSquare.isEmpty())
            {
                System.out.print(" | Players on square: ");
                for (Player player : playersOnSquare)
                {
                    if (playersOnSquare.get(0) == player)
                    {
                        System.out.print(player.getName());
                    }
                    else
                    {
                        System.out.print(", " + player.getName());
                    }
                }
            }
            System.out.println();
        }
    }


    /**
     * @author Yash Kapoor and Robert Simionescu
     * Allows the user to input the name of a player to check the state of.
     * @return String containing the name of the player selected.
     */
    public String selectPlayer()
    {
        Scanner choosePlayer = new Scanner(System.in);
        System.out.println("Which player would you like to view the state of? Please type their name: ");
        String cp = choosePlayer.next();

        return cp;
    }


    /**
     * @author Yash Kapoor and Robert Simionescu
     * Allows user to input a command, then verifies that it is a valid command. Users can input the command "Help" to
     * display a full list of commands.
     * @return A string containing a valid command.
     */
    //public String displayCommands(ArrayList<Player> player, int rd, int rd2, int index, Square propertyBlock, GameUI ui) {
    public String displayCommands() {
        // Prompts the user to input a command until they input a valid one.
        while (true) {
            Scanner command = new Scanner(System.in);

            System.out.println("\nEnter a command (Type \"help\" for the list of commands): ");

            String c = command.next();

            if (c.equalsIgnoreCase("help")) {
                // all available commands

                System.out.println("\nList of Commands");
                System.out.println("------------------");
                System.out.println("State of Player: Type SP");
                System.out.println("Buy Property: Type BP");
                System.out.println("Pass your turn: Type PT");
                System.out.println("Quit: Type Q");
            } else if (c.equalsIgnoreCase("SP")) {
                return "SP";

            } else if (c.equalsIgnoreCase("BP")) {
                return "BP";

            } else if (c.equalsIgnoreCase("PT")) {
                return "PT";

            } else if (c.equalsIgnoreCase("Q")) {
                return "Q";

            } else {
                System.out.println("You must enter a valid command! Try again!");
            }
        }

    }


    /**
     * @author Yash Kapoor and Robert Simionescu
     * Displays the first and second dice roll of the player
     * and adds them together to display how many squares the
     * player will move.
     *
     * @param firstRoll     an int, first dice roll
     * @param secondRoll    an int, second dice roll
     */
    public void displayDiceRoll(int firstRoll, int secondRoll) {

        System.out.println("You rolled a " + firstRoll + " and a " + secondRoll);

        int sum = firstRoll + secondRoll;
        if (firstRoll != secondRoll)
        {
            System.out.println("Hence, you will move " + sum + " spaces on the gameboard.");
        }
        else
        {
            System.out.println("Hence, you will move " + sum + " spaces on the gameboard and will get to roll again.");
        }

    }
}
