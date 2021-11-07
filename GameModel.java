/**
 * @author Oliver Lu, Yash Kapoor, and Robert Simionescu
 * @version Milestone 1
 */

import gameexceptions.*;

import java.security.InvalidParameterException;
import java.util.*;

/**
 * GameModel Class that deals with the logic of the game
 * and uses GameFrame to print out important commands for each
 * player that is playing the game.
 */
public class GameModel {

    public static final int MAX_PLAYERS = 8;
    public static final int MIN_PLAYERS = 2;
    public static final int STARTING_MONEY = 1500;



    public enum GameState {ADDING_PLAYERS, PLAYER_ROLLING, PLAYER_ROLLED_DOUBLES, PLAYER_ROLLED_NORMAL, GAME_OVER;}
    private List<GameView> views;

    private GameState gameState;
    private ArrayList<Player> players;
    private GameFrame gui;
    private Gameboard gameboard;
    private int currentPlayer;


    /**
     * @author Robert Simionescu
     * Constructor for a GameModel.
     */
    public GameModel()
    {
        this.gameboard = createGameboard();
        this.players = new ArrayList<Player>();
        currentPlayer = 0;
        gameState = GameState.ADDING_PLAYERS;
        views = new ArrayList<GameView>();
    }

    /**
     * @author Robert Simionescu
     * Adds a GameView to the list of views.
     * @param view The view to be added.
     */
    public void addGameView(GameView view)
    {
        views.add(view);
    }

    /**
     * @author Robert Simionescu
     * Getter for the list of views.
     * @return a List containing all of the GameViews for the game.
     */
    public List<GameView> getGameViews() {
        return views;
    }

    /**
     * @author Robert Simionescu
     * Returns the current player.
     * @return The current player, stored as a Player.
     */
    public Player getCurrentPlayer()
    {
        return players.get(currentPlayer);
    }

    /**
     * @author Robert Simionescu
     * Removes a view from the list of views.
     * @param view The view to be removed.
     */
    public void removeGameView(GameView view)
    {
        views.remove(view);
    }

    /**
     * @author Robert Simionescu
     * Returns the current GameState.
     * @return The current GameState.
     */
    public GameState getGameState()
    {
        return gameState;
    }

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

    /**
     * @author Robert Simionescu
     * Returns the gameboard.
     * @return The gameboard, stored as a Gameboard.
     */
    public Gameboard getGameboard() {
        return gameboard;
    }

    /**
     * @author Robert Simionescu
     * If the maximum number of players has not been reached, adds a new player to the list of players. If adding a new
     * player would exceed the limit, throws a TooManyPlayersException. If the new player shares a name with an existing
     * player, throws a DuplicateNameException.
     * @param player The player to attempt to add.
     */
    public void addPlayer(Player player)
    {
        if (gameState == GameState.ADDING_PLAYERS)
        {
            if (players.size() == MAX_PLAYERS)
            {
                throw new TooManyPlayersException("Maximum number of players reached");
            }
            for (Player p : players)
            {
                if (p.getName().equals(player.getName()))
                {
                    throw new DuplicateNameException("Player with name " + player.getName() + " already exists");
                }
            }
            players.add(player);
        }
        else
        {
            throw new InvalidStateException("You cannot add new players right now.");
        }
    }

    /**
     * @author Robert Simionescu
     * If enough players have been added, sets the game state to PLAYER_ROLLING. If not, throws a
     * NotEnoughPlayersException.
     */
    public void startGame()
    {
        if (gameState == GameState.ADDING_PLAYERS)
        {
            if (players.size() < MIN_PLAYERS)
            {
                throw new NotEnoughPlayersException("Minimum number of players not reached");
            }
            gameState = GameState.PLAYER_ROLLING;
        }
        else
        {
            throw new InvalidStateException("You cannot start the game right now.");
        }
    }

    /**
     * @author Robert Simionescu and Yash Kapoor
     * Rolls the dice and moves the current player by that many squares. If the player rolls doubles, sets the game
     * state to PLAYER_ROLLED_DOUBLES. If not, sets the state to PLAYER_ROLLED_NORMAL.
     */
    public int[] roll()
    {
        if (gameState == GameState.PLAYER_ROLLING)
        {
            int die1;
            int die2;

            Die d = new Die();

            die1 = d.roll();
            die2 = d.roll();

            int[] rolls = {0, 0};

            rolls[0] = die1;
            rolls[1] = die2;

            int newPosition = (players.get(currentPlayer).getPosition() + rolls[0] + rolls[1]) % gameboard.getSquares().size();
            players.get(currentPlayer).setPosition(newPosition);
            playerLandOnSquare(players.get(currentPlayer), gameboard.getSquare(newPosition));

            if (gameState == GameState.GAME_OVER)
            {
                return rolls;
            }

            if (rolls[0] == rolls[1])
            {
                gameState = GameState.PLAYER_ROLLED_DOUBLES;
            }
            else
            {
                gameState = GameState.PLAYER_ROLLED_NORMAL;
            }
            return rolls;
        }
        else
        {
            throw new InvalidStateException("You cannot roll the dice right now.");
        }
    }


    /**
     * @author Robert Simionescu
     * Changes the current player to the next one in the order, skipping bankrupt players. If the current player rolled
     * doubles on their previous roll, they go again.
     */
    public void passTurn()
    {
        if (gameState == GameState.PLAYER_ROLLED_DOUBLES)
        {
            gameState = GameState.PLAYER_ROLLING;
        }
        else if (gameState == GameState.PLAYER_ROLLED_NORMAL)
        {
            currentPlayer = (currentPlayer + 1) % players.size();
            if (players.get(currentPlayer).isBankrupt())
            {
                passTurn();
            }
            else
            {
                gameState = GameState.PLAYER_ROLLING;
            }
        }
        else
        {
            throw new InvalidStateException("You cannot pass your turn right now.");
        }
    }



    /**
     * @author Yash Kapoor and Robert Simionescu
     * Handles bankuptcy of a player. Bankrupt player forfeits all their properties to the player they are indepted to
     * and is removed from the game.
     * @param debtor Player who is in dept and has gone bankrupt.
     * @param creditor Player who is owed money by the bankrupt Player.
     * @return      a boolean, notifying methods when there is only one player remaining and all other players have gone bankrupt.
     */
    private boolean bankruptcy(Player debtor, Player creditor)
    {
        for (Property property : debtor.getProperties())
        {
            property.setOwner(creditor);
            creditor.addProperty(property);
        }
        debtor.setBankruptcy(true);


        if (players.size() == 1)
        {
            gui.displayMessage(players.get(0).getName() + " has won!"); //TODO
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
    public void inspectPlayer(String name, ArrayList<Player> players)
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
     * Returns a Player in the game with the requested name. Throws a PlayerNotFoundException if there is no player
     * with that name.
     * @param name The name of the Player to get.
     * @return The Player with the requested name.
     */
    public Player getPlayer(String name)
    {
        for (Player player : players)
        {
            if (player.getName().equals(name))
            {
                return player;
            }
        }
        throw new PlayerNotFoundException("No player named " + name + " exists.");
    }

    /**
     * @author Robert Simionescu
     * Returns the list of players.
     * @return Arraylist containing all the players in the game.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @author Yash Kapoor and Robert Simionescu
     * Attempts to purchase the property the player is on. Fails if the player is not on a property, the property is
     * already owned, or the player does not have enough money to make the purchase.
     * @param buyer The player attempting to buy the property.
     */
    public void buyProperty(Player buyer)
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
                }
                else
                {
                    throw new InsufficientMoneyException("You do not have enough money to purchase this property");
                }
            }
            else
            {
                throw new PropertyOwnedException("This property is already owned by " + ((Property) property).getOwner().getName() + "!");
            }
        }
        else
        {
            throw new InvalidSquareTypeException("You are not on a property!");
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
    public boolean playerLandOnSquare(Player player, Square square)
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
                    if(bankruptcy(player, ((Property) square).getOwner())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}





