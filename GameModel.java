/**
 * @author Oliver Lu, Yash Kapoor, and Robert Simionescu
 * @version Milestone 4
 */

import gameexceptions.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.*;

/**
 * @author Yash Kapoor and Robert Simionescu
 *
 * GameModel Class that deals with the logic of the game
 * and uses GameFrame to print out important commands for each
 * player that is playing the game.
 */
public class GameModel implements Serializable {

    public static final int MAX_PLAYERS = 8;    // Maximum number of players.
    public static final int MIN_PLAYERS = 2;    // Minimum number of players before the game can begin.
    public static final int STARTING_MONEY = 1500;  // Initial amount of money for players.
    private static final int GO_MONEY = 200; // Money given when passing Go.
    private static final int MAX_HOUSES = 4; // Max houses that can be built on a street before a hotel is built.
    private static final int MAX_HOTELS = 1;
    private static final int MAX_CAPACITY = MAX_HOUSES + MAX_HOTELS;
    private static final int TOTAL_HOUSES = 32;
    private static final int TOTAL_HOTELS = 12;

    public enum GameState {ADDING_PLAYERS, PLAYER_ROLLING, PLAYER_ROLLED_DOUBLES, PLAYER_ROLLED_NORMAL, PLAYER_PASSED_GO,
        DOUBLES_ROLLED_THRICE, DOUBLES_ROLLED_IN_JAIL, GAME_OVER}

    private List<GameView> views;

    private GameState gameState;
    private ArrayList<Player> players;

    private Gameboard gameboard;

    private int currentPlayer;

    private int housesInBank;
    private int hotelsInBank;

    /**
     * @author Robert Simionescu
     * Constructor for a GameModel.
     */
    public GameModel()
    {
        this.players = new ArrayList<Player>();
        currentPlayer = 0;
        gameState = GameState.ADDING_PLAYERS;
        views = new ArrayList<GameView>();

        this.housesInBank = TOTAL_HOUSES;
        this.hotelsInBank = TOTAL_HOTELS;

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
     * @author Yash Kapoor
     *
     * Sets the current player of the game
     * @param currentPlayer     an int, the index of the current player in the game
     */
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
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
     * @author Robert Simionescu
     * Set the game state
     *
     * @return The current GameState.
     */
    public void setGameState(GameState gm){
        this.gameState = gm;
    }

    /**
     * @author Yash Kapoor and Robert Simionescu
     * Creating the gameboard by adding squares to it.
     * Those squares can represent properties (e.g., streets) and the initial starting point.
     * Jail, GO, and Utilities will be added in later milestones.
     *
     * @return      a Gameboard Object, the gameboard that is going to be used in the game
     */
    public Gameboard createGameboard(String fileName)
    {
        try
        {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser s = spf.newSAXParser();
            GameboardHandler gameboardHandler = new GameboardHandler();

            s.parse(fileName, gameboardHandler);

            gameboard = gameboardHandler.getGameboard();

            return gameboard;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @author Robert Simionescu
     * Returns the gameboard.
     * @return      a Gameboard Object, the gameboard that is going to be used in the game
     */
    public Gameboard getGameboard() {
        return gameboard;
    }

    /**
     * @author Yash Kapoor
     * Sets the gameboard (containing a list of squares) to be used in the game
     *
     * @param gameboard     a Gameboard Object, the gameboard that is going to be used in the game
     */
    public void setGameboard(Gameboard gameboard) {
        this.gameboard = gameboard;
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
     *
     * Modified to account for Jail and GO.
     * Player goes to "Jail" if they land on "Go to Jail" or roll doubles 3 times in succession, and turn ends immediately.
     * Player collects $200 when they land or pass "GO".
     *
     * @return      an array of integers, the dice rolls
     */
    public int[] roll() {
        Die d = new Die();
        int[] rolls = {0, 0};

        rolls[0] = d.roll();
        rolls[1] = d.roll();

        players.get(currentPlayer).setTotalRoll(rolls[0] + rolls[1]);

        if (players.get(currentPlayer).getInJail()) {
            if (rolls[0] != rolls[1]) {
                gameState = GameState.PLAYER_ROLLED_NORMAL;
                // doubles are not rolled
                players.get(currentPlayer).incrementDoubleCount();

            }
            if (rolls[0] == rolls[1] || players.get(currentPlayer).getDoubleCount() == 3) {

                if (players.get(currentPlayer).getDoubleCount() == 3) {
                    // third attempt, must pay if doubles are not rolled

                    players.get(currentPlayer).removeMoney(50);
                    players.get(currentPlayer).resetDoubleCount();

                    players.get(currentPlayer).setInJail(false);

                }
                // doubles are rolled, player gets to get out of jail
                // turn ends after doubles are rolled
                gameState = GameState.DOUBLES_ROLLED_IN_JAIL;

                players.get(currentPlayer).setInJail(false);

                int newPosition = (players.get(currentPlayer).getPosition() + rolls[0] + rolls[1]) % gameboard.getSquares().size();

                int oldPosition = players.get(currentPlayer).getPosition();
                while(oldPosition != newPosition) {

                    oldPosition = (oldPosition + 1) % gameboard.getSquares().size();

                    players.get(currentPlayer).setPosition(oldPosition);

                }

                playerLandOnSquare(players.get(currentPlayer), gameboard.getSquare(newPosition));

            }

            if (gameState == GameState.GAME_OVER)
            {
                return rolls;
            }

            return rolls;
        }
        else if (gameState == GameState.PLAYER_ROLLING) {

            if (rolls[0] == rolls[1])
            {
                players.get(currentPlayer).incrementDoubleCount();

                if (players.get(currentPlayer).getDoubleCount() == 3) {
                    // player rolls doubles 3 times in a row
                    // goes to jail immediately on 3rd double roll

                    players.get(currentPlayer).setPosition(gameboard.getJail());
                    players.get(currentPlayer).setInJail(true);

                    gameState = GameState.DOUBLES_ROLLED_THRICE;
                }
                else {

                    gameState = GameState.PLAYER_ROLLED_DOUBLES;
                }
            }
            else
            {
                // resetting counter if doubles not rolled
                players.get(currentPlayer).resetDoubleCount();

                gameState = GameState.PLAYER_ROLLED_NORMAL;
            }

            int newPosition = (players.get(currentPlayer).getPosition() + rolls[0] + rolls[1]) % gameboard.getSquares().size();

            int oldPosition = players.get(currentPlayer).getPosition();

            // Since the player's position resets to 0 when reaching go, this condition will only occur when the player
            // has passed go.
            if (newPosition != oldPosition + rolls[0] + rolls[1])
            {
                players.get(currentPlayer).addMoney(GO_MONEY);
                gameState = GameState.PLAYER_PASSED_GO;
            }

            // Player has landed on "Go to Jail"
            if (newPosition == gameboard.getGoToJail())
            {
                // set position to "Jail"
                players.get(currentPlayer).setPosition(gameboard.getJail());
                players.get(currentPlayer).setInJail(true);
            }
            else
            {
                players.get(currentPlayer).setPosition(newPosition);
            }

            if (!players.get(currentPlayer).getInJail()) {
                // player isn't in jail, so rent must be paid to the other player
                playerLandOnSquare(players.get(currentPlayer), gameboard.getSquare(newPosition));
            }

            if (gameState == GameState.GAME_OVER)
            {
                return rolls;
            }

            return rolls;
        }
        else
        {
            throw new InvalidStateException("You cannot roll the dice right now.");
        }
    }

    /**
     * @author Robert Simionescu and Yash Kapoor
     * Changes the current player to the next one in the order, skipping bankrupt players. If the current player rolled
     * doubles on their previous roll, they go again unless they are in jail.
     *
     * @param  playerBankrupt       a boolean, true if player is bankrupt, false otherwise
     */
    public void passTurn(boolean playerBankrupt)
    {
        if (getCurrentPlayer().getInJail()) {
            currentPlayer = (currentPlayer + 1) % players.size();
            gameState = GameState.PLAYER_ROLLING;
        }
        else if (gameState == GameState.PLAYER_ROLLED_DOUBLES)
        {
            gameState = GameState.PLAYER_ROLLING;
        }
        else if (gameState == GameState.PLAYER_ROLLED_NORMAL || gameState == GameState.DOUBLES_ROLLED_IN_JAIL)
        {
            if (!playerBankrupt) {
                currentPlayer = (currentPlayer + 1) % players.size();
                gameState = GameState.PLAYER_ROLLING;
            }
            else {
                // decrement currentPlayer counter by 1 to get the correct
                // turn of the next available player if current player is
                // bankrupt
                currentPlayer = (currentPlayer - 1) % players.size();
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
     * @return     a boolean, returns true indicating that the player has gone bankrupt
     */
    private boolean bankruptcy(Player debtor, Player creditor)
    {
        for (Property property : debtor.getProperties())
        {
            property.setOwner(creditor);
            creditor.addProperty(property);
        }
        debtor.setBankruptcy(true);

        return true;
    }

    /**
     * @author Yash Kapoor
     * Checks to see if there is one player remaining
     */
    public void onePlayerRemaining() {
        if (players.size() == 1)
        {
            gameState = GameState.GAME_OVER;
        }
    }

    /**
     * @author Yash Kapoor
     * Remove a player from the ArrayList
     *
     * @param player        a Player Object, the player to be removed
     */
    public void removePlayer(Player player) {
        players.remove(player);
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
     * Sets the list of players in the game
     *
     * @param players       an ArrayList, containing all the players in the game
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
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
     * @return      a boolean, true if player landing on the square has no money left (becomes bankrupt), false otherwise
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
                if (((Property) square).calculateRent(player, gameboard) < player.getMoney())
                {
                    player.removeMoney(((Property) square).calculateRent(player, gameboard));
                    ((Property) square).getOwner().addMoney(((Property) square).calculateRent(player, gameboard));
                }
                else
                {
                    return bankruptcy(player, ((Property) square).getOwner());
                }
            }
        }
        return false;
    }

    /**
     * @author Yash Kapoor
     *
     * Get the total number of houses that are in the bank.
     *
     * There are 32 houses in the bank. Hence, this value is initialized to 32.
     * @return      an int, representing the total number of houses
     */
    public int getTotalHouses() {
        return this.housesInBank;
    }

    /**
     * @author Yash Kapoor
     *
     * Set the total number of houses that are in the bank.
     * @param houses       an int, representing the number of houses.
     */
    public void setTotalHouses(int houses) {
        this.housesInBank = houses;
    }

    /**
     * @author Yash Kapoor
     *
     * Get the total number of hotels that are in the bank.
     * @return      an int, representing the total number of hotels
     */
    public int getHotelsInBank() {
        return this.hotelsInBank;
    }

    /**
     * @author Yash Kapoor
     *
     * Set the total number of hotels that are in the bank.
     * @param hotels    an int, representing the number of hotels.
     */
    public void setHotelsInBank(int hotels) {
        this.hotelsInBank = hotels;
    }

    /**
     * @author Yash Kapoor
     *
     * Helper method for buildOnProperty()
     *
     * Keeps track of the number of houses/hotels on each street that the player clicks on,
     * ensuring that the number of houses/hotels do not exceed the total number
     * of houses and hotels in the bank.
     *
     * @param buyer         a Player Object, the player who is buying the houses/hotels on the street
     * @param s             a Street Object, the street that the player clicked on to build a house/hotel on
     */
    private void assignHouses(Player buyer, Street s) {

        ArrayList<Street> ownedSquaresMatching = s.getBuildableColour(getCurrentPlayer(), gameboard);

        if (ownedSquaresMatching == null) {
            // player does not own all the properties in the color set

            return; // This is unnecessary, but if anything gets added after the else it will become necessary.
        }
        else {
            // player owns all the properties in the same color set

            int totalHouses = 0;
            for(int sameStreet = 0; sameStreet < ownedSquaresMatching.size(); sameStreet++) {
                // iterating through the ArrayList and calculating the total number of houses
                // on each street of a specific color set

                totalHouses += ownedSquaresMatching.get(sameStreet).getHouses();

            }

            // calculation below that ensures the player is building houses evenly
            float average = totalHouses / (float) ownedSquaresMatching.size();

            int numHouses = s.getHouses();

            if (numHouses > average && numHouses != MAX_CAPACITY) {

                throw new BuildHousesException("You must build houses evenly! Try again.");
            }

            if (s.getHouseCost() <= buyer.getMoney()) {
                // the cost of the house must be less than or equal to the buyer's total amount of money

                if (housesInBank >= 0 || numHouses == MAX_HOUSES) {
                    // runs as long as there are enough houses available in the bank to build another or the player
                    // has reached the max number of houses and will attempt to build a hotel.

                    housesInBank--;

                    buyer.removeMoney(s.getHouseCost());
                    numHouses += 1;
                    s.setHouses(numHouses);

                    getCurrentPlayer().incrementTotalNumberHouses();

                    if (numHouses == MAX_CAPACITY) {

                        s.setMaxCapacityReached(true);

                        if(hotelsInBank >= 0) {
                            housesInBank += MAX_CAPACITY;
                            // houses returned to the bank, plus the newly added extra.

                            hotelsInBank--;

                            // 5 houses technically equal 1 hotel, so we set hotels = 1
                            s.setHotels(1);

                            getCurrentPlayer().incrementTotalNumberHotels();

                            // hotel built
                        }
                        else {
                            throw new NotEnoughHotelsException("There are no more hotels left in the bank. Sorry!");
                        }
                    }
                }
                else {
                    throw new NotEnoughHousesException("There are no more houses left in the bank. Sorry!");
                }
            }
            else {
                throw new InsufficientMoneyException("You do not have enough money to purchase this house.");
            }
        }
    }

    /**
     * @author Yash Kapoor
     *
     * Allows the player to build houses/hotels on the streets that they own.
     *
     * @param buyer     a Player Object, the player who is buying the houses/hotels on the street
     * @param name      a String, the name of the street that the player clicked on to build a house/hotel on
     */
    public void buildOnProperty(Player buyer, String name) {

        // Iterates over all squares on the gameboard and counts how many are streets of the same colour as this one.
        ArrayList<Square> gameboardSquares = gameboard.getSquares();

        for (Square s : gameboardSquares) {
            if (s instanceof Street) {
                if ((s.getName().equals(name))) {
                    assignHouses(buyer, (Street) s);
                }
            }
        }
    }

    /**
     * Save the game to a specific file
     *
     * @param file      a File Object, the file that the game is going to get saved to
     */
    public void save(File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileOutputStream);

            objectStream.writeInt(currentPlayer);
            objectStream.writeObject(getGameState());
            objectStream.writeObject(getPlayers());
            objectStream.writeObject(getGameboard());

            fileOutputStream.close();
            objectStream.close();
        }
        catch (Exception e) {
            System.out.println("Unable to save: " + e.getMessage());
        }
    }

    /**
     * Load the game from a specific file
     *
     * @param file      a File Object, the file that the game is going to get saved to
     */
    public void load(File file) {
        ArrayList<Player> player;
        GameState gs;
        Gameboard gb;
        int cp;

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            cp = objectInputStream.readInt();
            gs = (GameState) objectInputStream.readObject();
            player = (ArrayList<Player>) objectInputStream.readObject();
            gb = (Gameboard) objectInputStream.readObject();

            setPlayers(player);
            setGameState(gs);
            setGameboard(gb);
            setCurrentPlayer(cp);

            fileInputStream.close();
            objectInputStream.close();
        }
        catch (Exception e) {
            System.out.println("Unable to load saved data: " + e.getMessage());
        }
    }

}





