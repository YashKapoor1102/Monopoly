/**
 * @author Oliver Lu, Yash Kapoor, and Robert Simionescu
 * @version Milestone 1
 */

import java.util.*;

/**
 * GameLogic Class that deals with the logic of the game
 * and uses GameUI to print out important commands for each
 * player that is playing the game.
 */
public class GameLogic {

    private GameFrame gui;

    /**
     * @author Yash Kapoor and Robert Simionescu
     * Creating the gameboard by adding squares to it.
     * Those squares can represent properties (e.g., streets) and the initial starting point.
     * Jail, GO, and Utilities will be added in later milestones.
     * @return The gameboard that will be used for the game.
     */
    public Gameboard createGameboard()
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

    public ArrayList<Integer> calculateRoll() {
        int rollDie;
        int rollDie2;

        Die d = new Die();

        rollDie = d.roll();
        rollDie2 = d.roll();

        ArrayList<Integer> rolls = new ArrayList<>();

        rolls.add(0, rollDie);
        rolls.add(1, rollDie2);

        return rolls;

    }

    /**
     * @author Yash Kapoor and Robert Simionescu
     * Handles bankuptcy of a player. Bankrupt player forfeits all their properties to the player they are indepted to
     * and is removed from the game.
     * @param debtor Player who is in dept and has gone bankrupt.
     * @param creditor Player who is owed money by the bankrupt Player.
     * @return      a boolean, notifying methods when there is only one player remaining and all other players have gone bankrupt.
     */
    private boolean bankruptcy(Player debtor, Player creditor, ArrayList<Player> players)
    {
        for (Property property : debtor.getProperties())
        {
            property.setOwner(creditor);
            creditor.addProperty(property);
        }
        players.remove(debtor);

        if (players.size() == 1)
        {
            gui.displayMessage(players.get(0).getName() + " has won!");
            return true;
        }
        return false;
    }


    /**
     * @author Yash Kapoor and Robert Simionescu
     * player can view the state of any player by typing their name
     * state displays where they are on the gameboard, how much money they have,
     * and the list of properties that they currently own.
     */
    public void inspectPlayer(String name, ArrayList<Player> players, Gameboard gameboard)
    {

        gui = new GameFrame();
        StringBuilder sb = new StringBuilder();

        for(Player p: players) {

            if(p.getName().equals(name)) {
                gui = new GameFrame();

                // entered player matches the one found by the loop
                // display their state

                sb.append(p.getName() + " has $" + p.getMoney() + "\n");

                try {
                    sb.append(name + " is currently on " + gameboard.getSquare(p.getPosition()) + "\n");
                } catch (IndexOutOfBoundsException e) {
                    sb.append(p.getName() + " is currently on " + gameboard.getSquare(0) + "\n");
                }
                sb.append(p.getName() + " currently owns " + p.getProperties().toString() + "\n");
                // [] (Empty List) indicates player owns no properties

            }

        }

        gui.displayMessage(sb.toString());

    }


    /**
     * @author Yash Kapoor and Robert Simionescu
     * Attempts to purchase the property the player is on. Fails if the player is not on a property, the property is
     * already owned, or the player does not have enough money to make the purchase.
     * @param buyer The player attempting to buy the property.
     */
    public void buyProperty(Player buyer, Gameboard gameboard)
    {
        gui = new GameFrame();

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
                    gui.displayMessage("You have purchased " + property.getName());
                }
                else
                {
                    gui.displayMessage("You do not have enough money to purchase this property!");
                }
            }
            else
            {
                gui.displayMessage("This property is already owned by " + ((Property) property).getOwner().getName() + "!");
            }
        }
        else
        {
            gui.displayMessage("You are not on a property!");
        }
    }

    /**
     * Oliver Lu and Robert Simionescu
     * Handles a player landing on a square. In this milestone, all squares are properties except the inital square. In
     * future milestones, there will be other types of squares.
     * @param player The player who has just rolled.
     * @param square The square on which they have landed.
     *
     * @return      a boolean, false if there is only one player remaining, true otherwise
     */
    public boolean playerLandOnSquare(Player player, Square square, Gameboard gameboard, ArrayList<Player> listPlayers)
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
                    gui.displayMessage(player.getName()
                            + " pays $" + ((Property) square).calculateRent(gameboard)
                            + " to " + ((Property) square).getOwner().getName());
                }
                else
                {
                    gui.displayMessage(player.getName() + " could not pay their rent and has gone bankrupt. All their property has been transfered to " + ((Property) square).getOwner().getName() + ".");
                    if(bankruptcy(player, ((Property) square).getOwner(), listPlayers)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}





