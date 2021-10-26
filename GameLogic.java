/**
 * @author Oliver Lu
 * @version Milestone 1
 */

import java.util.*;

/**
 * GameLogic Class that deals with the logic of the game
 * and uses GameUI to print out important commands for each
 * player that is playing the game.
 */
public class GameLogic {

    private ArrayList<Player> players;
    private GameUI ui;
    private Gameboard gameboard;


    /**
     * @author Yash Kapoor and Robert Simionescu
     * Creating the gameboard by adding squares to it.
     * Those squares can represent properties (e.g., streets) and the initial starting point.
     * Jail, GO, and Utilities will be added in later milestones.
     * @return The gameboard that will be used for the game.
     */
    private Gameboard createGameboard()
    {
        ArrayList<Square> squares = new ArrayList<>();

        // adding all squares to the ArrayList
        Collections.addAll(squares, new Square() {

            /**
             * Getting the name of the square
             *
             * @return a String, the name of the square
             */
            @Override
            public String getName() {
                return "Initial Starting Point";
            }

            /**
             * Setting the property that exists on the square (if any)
             * Null if there is no property on that specific square.
             * <p>
             * This will be modified slightly in later milestones when we have to account for more than
             * one Streets on the gameboard.
             *
             * @return a Street Object, the street that is present on the square
             */
            @Override
            public Street setProperty() {
                return null;
            }

            /**
             * Shows the user a text representation of what square they are currently on.
             *
             * @return a String, the name of the square
             */
            @Override
            public String toString() {
                return String.format("%s", getName());
            }


        }, new Street("Mediterranean Avenue", "Brown", 40) {
        }, new Street("Baltic Avenue", "Brown", 60) {

        }, new Street("Oriental Avenue", "Blue", 100) {
        }, new Street("Vermont Avenue", "Blue", 100) {
        }, new Street("Connecticut Avenue", "Blue", 120) {

        }, new Street("St. Charles Place", "Pink", 140) {
        }, new Street("States Avenue", "Pink", 140) {
        }, new Street("Virginia Avenue", "Pink", 160) {

        }, new Street("St. James Place", "Orange", 180) {
        }, new Street("Tennessee Avenue", "Orange", 180) {
        }, new Street("New York Avenue", "Orange", 200) {

        }, new Street("Kentucky Avenue", "Red", 220) {
        }, new Street("Indiana Avenue", "Red", 220) {
        }, new Street("Illinois Avenue", "Red", 240) {

        }, new Street("Atlantic Avenue", "Yellow", 260) {
        }, new Street("Ventnor Avenue", "Yellow", 260) {
        }, new Street("Marvin Gardens", "Yellow", 280) {

        }, new Street("Pacific Avenue", "Green", 300) {
        }, new Street("North Carolina Avenue", "Green", 300) {
        }, new Street("Pennsylvania Avenue", "Green", 320) {

        }, new Street("Park Place", "Dark Blue", 350) {
        }, new Street("Board Walk", "Dark Blue", 400));

        return new Gameboard(squares);

    }


    /**
     * @author Yash Kapoor and Robert Simionescu
     * Handles bankuptcy of a player. Bankrupt player forfeits all their properties to the player they are indepted to
     * and is removed from the game.
     * @param deptor Player who is in dept and has gone bankrupt.
     * @param creditor Player who is owed money by the bankrupt Player.
     */
    private void bankruptcy(Player deptor, Player creditor)
    {
        for (Property property : deptor.getProperties())
        {
            property.setOwner(creditor);
            creditor.addProperty(property);
        }
        players.remove(deptor);

        if (players.size() == 1)
        {
            ui.displayMessage(players.get(0).getName() + " has won!");
            quitGame();
        }
    }


    /**
     * @author Yash Kapoor and Robert Simionescu
     * player can view the state of any player by typing their name
     * state displays where they are on the gameboard, how much money they have,
     * and the list of properties that they currently own.
     */
    private void inspectPlayer()
    {

        boolean playerName = false;

        while (!playerName)
        {
            String cp = ui.selectPlayer();

            for (Player player : players) {
                if (cp.equalsIgnoreCase(player.getName())) {
                    // entered player matches the one found by the loop
                    // display their state

                    System.out.println(player.getName() + " has $" + player.getMoney());

                    try {
                        System.out.println(player.getName() + " is currently on " + gameboard.getSquare(player.getPosition()));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(player.getName() + " is currently on " + gameboard.getSquare(0));
                    }
                    System.out.println(player.getName() + " currently owns " + player.getProperties().toString());
                    // [] (Empty List) indicates player owns no properties

                    playerName = true;
                }
            }
            if (!playerName) {
                ui.displayMessage("Sorry! This name does not exist in the list of players.");
            }
        }
    }


    /**
     * @author Yash Kapoor and Robert Simionescu
     * Attempts to purchase the property the player is on. Fails if the player is not on a property, the property is
     * already owned, or the player does not have enough money to make the purchase.
     * @param buyer The player attempting to buy the property.
     */
    private void buyProperty(Player buyer)
    {
        Square property = gameboard.getSquare(buyer.getPosition());
        // Checks that the player is on a property square.
        if (property instanceof Property)
        {
            // Checks that the property does not already have an owner
            if (((Property) property).getOwner() == null)
            {
                if (((Property) property).getCost() <= buyer.getMoney())
                {
                    buyer.addProperty((Property) property);
                    buyer.removeMoney(((Property) property).getCost());
                    ((Property) property).setOwner(buyer);
                    System.out.println("You have purchased " + property.getName());
                }
                else
                {
                    ui.displayMessage("You do not have enough money to purchase this property!");
                }
            }
            else
            {
                ui.displayMessage("This property is already owned by " + ((Property) property).getOwner().getName() + "!");
            }
        }
        else
        {
            ui.displayMessage("You are not on a property!");
        }
    }


    /**
     * @author Yash Kapoor
     * Quits the game
     */
    private void quitGame()
    {
        ui.displayMessage("\nThank you for playing!");
        System.exit(0);
    }


    /**
     * Oliver Lu and Robert Simionescu
     * Handles a player landing on a square. In this milestone, all squares are properties except the inital square. In
     * future milestones, there will be other types of squares.
     * @param player The player who has just rolled.
     * @param square The square on which they have landed.
     */
    private void playerLandOnSquare(Player player, Square square)
    {
        // If the player lands on a property
        if (square instanceof Property)
        {
            // If that property is owned
            if (((Property) square).getOwner() != null && ((Property) square).getOwner() != player)
            {
                // If the player has enough money to pay rent for that property
                if (((Property) square).calculateRent(gameboard) < player.getMoney())
                {
                    player.removeMoney(((Property) square).calculateRent(gameboard));
                    ((Property) square).getOwner().addMoney(((Property) square).calculateRent(gameboard));
                    ui.displayMessage(player.getName()
                            + " pays $" + ((Property) square).calculateRent(gameboard)
                            + " to " + ((Property) square).getOwner().getName());
                }
                else
                {
                    ui.displayMessage(player + " could not pay their rent and has gone bankrupt. All their property has been transfered to " + ((Property) square).getOwner() + ".");
                    bankruptcy(player, ((Property) square).getOwner());
                }
            }
        }
    }


    /**
     * @author Oliver Lu, Robert Simionescu
     * The core loop of the game, this repeats until the game is done.
     */
    private void mainLoop()
    {
        // Game is NOT called Monopoly due to copyright reasons
        System.out.println("\nWelcome to the game of Funopoly!\n");

        ui = new GameUI();
        gameboard = createGameboard();
        players = new ArrayList<>();
        String command;

        // ask the user how many players are going to be playing the game and their names
        players = ui.displayPlayerSelection();


        int sum = 0;

        while (true) {
            for (int currentPlayer = 0; currentPlayer < players.size(); currentPlayer++) {
                // go through each player

                ui.printBoard(gameboard, players);

                ui.displayMessage("\nPlayer " + (currentPlayer + 1) + "'s (" + players.get(currentPlayer).getName() + ") turn: ");

                int rollDie = 0;
                int rollDie2 = 0;

                ui.displayRollCommand();

                Die d = new Die();
                Die d2 = new Die();

                rollDie = d.roll();
                rollDie2 = d2.roll();

                sum = rollDie + rollDie2;
                ui.displayDiceRoll(rollDie, rollDie2);
                int newPosition = (players.get(currentPlayer).getPosition() + sum) % gameboard.getSquares().size();

                players.get(currentPlayer).setPosition(newPosition);

                // getting the square that the user is currently on
                Square currentPosition = gameboard.getSquare(players.get(currentPlayer).getPosition());

                ui.displayMessage("You landed on " + currentPosition.getName());

                playerLandOnSquare(players.get(currentPlayer), currentPosition);

                do
                {
                    command = ui.displayCommands();

                    switch (command)
                    {
                        case "BP":
                            buyProperty(players.get(currentPlayer));
                            break;
                        case "SP":
                            inspectPlayer();
                            break;
                        case "PT":
                            break;
                        case "Q":
                            quitGame();
                    }
                } while (!command.equals("PT"));

                if (rollDie == rollDie2) {
                    // doubles are rolled, same player goes again
                    currentPlayer--;
                }

            }
        }
    }

    /**
     * Main method that deals with all the logic
     * of the game. It goes through each player and
     * allows them to roll their dice.
     *
     * If a player rolls doubles,
     * they get to go again.
     *
     * @param args
     */
    public static void main(String[] args) {
        GameLogic game = new GameLogic();
        game.mainLoop();

    }
}





