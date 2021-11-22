/**
 * @author Oliver Lu, Yash Kapoor, and Robert Simionescu
 * @version Milestone 3
 */

import gameexceptions.*;

import java.util.*;

/**
 * @author Yash Kapoor and Robert Simionescu
 *
 * GameModel Class that deals with the logic of the game
 * and uses GameFrame to print out important commands for each
 * player that is playing the game.
 */
public class GameModel {

    public static final int MAX_PLAYERS = 8;
    public static final int MIN_PLAYERS = 2;
    public static final int STARTING_MONEY = 1500;

    public enum GameState {ADDING_PLAYERS, PLAYER_ROLLING, PLAYER_ROLLED_DOUBLES, PLAYER_ROLLED_NORMAL, PLAYER_PASSED_GO,
        DOUBLES_ROLLED_THRICE, DOUBLES_ROLLED_IN_JAIL, GAME_OVER}

    public enum BuildingState {PLAYER_NOT_BUILDING, PLAYER_BUILDING, ALL_HOUSES_BUILT, ALL_HOTELS_BUILT}

    private List<GameView> views;

    private BuildingState buildingState;
    private GameState gameState;
    private ArrayList<Player> players;

    private Gameboard gameboard;

    private int currentPlayer;
    private int totalNumberHouses;
    private int totalNumberHotels;

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

        this.totalNumberHouses = 32;
        this.totalNumberHotels = 12;

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

    public BuildingState getBuildingState()
    {
        return buildingState;
    }

    public void setBuildingState(BuildingState bs){
        this.buildingState = bs;
    }



    /**
     * @author Robert Simionescu
     * Meant for testing purposes
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

        }, new Street("Mediterranean Avenue", "Brown", 60, 50, 50) {
        }, new Street("Baltic Avenue", "Brown", 60, 50, 50) {

        }, new Railroad("Reading Railroad", 200) {

        }, new Street("Oriental Avenue", "Blue", 100, 50, 50) {
        }, new Street("Vermont Avenue", "Blue", 100, 50, 50) {
        }, new Street("Connecticut Avenue", "Blue", 120, 50, 50) {

        }, new Square() {

            /**
             * Getting the name of the square
             *
             * @return a String, the name of the square
             */
            @Override
            public String getName() {
                return "Jail";
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
        }, new Street("St. Charles Place", "Pink", 140, 100, 100) {
        }, new Utility("Electric Company", 150) {
        }, new Street("States Avenue", "Pink", 140, 100, 100) {
        }, new Street("Virginia Avenue", "Pink", 160, 100, 100) {

        }, new Railroad("Pennsylvania Railroad", 200) {

        }, new Street("St. James Place", "Orange", 180, 100, 100) {
        }, new Street("Tennessee Avenue", "Orange", 180, 100, 100) {
        }, new Street("New York Avenue", "Orange", 200, 100, 100) {

        }, new Street("Kentucky Avenue", "Red", 220, 150, 150) {
        }, new Street("Indiana Avenue", "Red", 220, 150, 150) {
        }, new Street("Illinois Avenue", "Red", 240, 150, 150) {

        }, new Railroad("B. & O. Railroad", 200) {

        }, new Street("Atlantic Avenue", "Yellow", 260, 150, 150) {
        }, new Street("Ventnor Avenue", "Yellow", 260, 150, 150) {
        }, new Utility("Water Works", 150) {
        }, new Street("Marvin Gardens", "Yellow", 280, 150, 150) {

        }, new Square() {

            /**
             * Getting the name of the square
             *
             * @return a String, the name of the square
             */
            @Override
            public String getName() {
                return "Go to Jail";
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

        }, new Street("Pacific Avenue", "Green", 300, 200, 200) {
        }, new Street("North Carolina Avenue", "Green", 300, 200, 200) {
        }, new Street("Pennsylvania Avenue", "Green", 320, 200, 200) {

        }, new Railroad("Short Line Railroad", 200) {

        }, new Street("Park Place", "Dark Blue", 350, 200, 200) {
        }, new Street("Board Walk", "Dark Blue", 400, 200, 200));

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
     *
     * Modified to account for Jail and GO.
     * Player goes to "Jail" if they land on "Go to Jail" or roll doubles 3 times in succession, and turn ends immediately.
     * Player collects $200 when they land or pass "GO".
     *
     * @return      an array of integers, the dice rolls
     */
    public int[] roll() {

        int die1;
        int die2;

        Die d = new Die();

        die1 = d.roll();
        die2 = d.roll();

        int[] rolls = {0, 0};

        rolls[0] = die1;
        rolls[1] = die2;

        int dc = players.get(currentPlayer).getDoubleCount();
        players.get(currentPlayer).setTotalRoll(rolls[0] + rolls[1]);

        if (players.get(currentPlayer).getInJail()) {
            if (rolls[0] != rolls[1]) {
                gameState = GameState.PLAYER_ROLLED_NORMAL;
                // doubles are not rolled
                dc++;
                players.get(currentPlayer).setDoubleCount(dc);

            }
            if (rolls[0] == rolls[1] || players.get(currentPlayer).getDoubleCount() == 3) {

                if (players.get(currentPlayer).getDoubleCount() == 3) {
                    // third attempt, must pay if doubles are not rolled

                    players.get(currentPlayer).removeMoney(50);
                    players.get(currentPlayer).setDoubleCount(0);

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
                dc++;
                players.get(currentPlayer).setDoubleCount(dc);

                if (players.get(currentPlayer).getDoubleCount() == 3) {
                    // player rolls doubles 3 times in a row
                    // goes to jail immediately on 3rd double roll

                    players.get(currentPlayer).setPosition(7);
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
                players.get(currentPlayer).setDoubleCount(0);

                gameState = GameState.PLAYER_ROLLED_NORMAL;
            }

            int newPosition = (players.get(currentPlayer).getPosition() + rolls[0] + rolls[1]) % gameboard.getSquares().size();

            int oldPosition = players.get(currentPlayer).getPosition();

            while(oldPosition != newPosition && !players.get(currentPlayer).getInJail()) {

                oldPosition = (oldPosition + 1) % gameboard.getSquares().size();

                if (newPosition == 24) {
                    // user lands on "Go to Jail"
                    // set position to "Jail"
                    players.get(currentPlayer).setPosition(7);
                    players.get(currentPlayer).setInJail(true);
                }
                else {
                    players.get(currentPlayer).setPosition(oldPosition);
                }

                if (oldPosition == 0) {
                    // User passes go, award them $200
                    players.get(currentPlayer).addMoney(200);
                    gameState = GameState.PLAYER_PASSED_GO;
                }
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
     *
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
     * Get the total number of houses that are in the bank.
     *
     * There are 32 houses in the bank. Hence, this value is initialized to 32.
     * @return      an int, representing the total number of houses
     */
    public int getTotalNumberHouses() {
        return this.totalNumberHouses;
    }

    /**
     * Set the total number of houses that are in the bank.
     * @param houses       an int, representing the number of houses.
     */
    public void setTotalNumberHouses(int houses) {
        this.totalNumberHouses = houses;
    }

    /**
     * Get the total number of hotels that are in the bank.
     * @return      an int, representing the total number of hotels
     */
    public int getTotalNumberHotels() {
        return this.totalNumberHotels;
    }

    /**
     * Set the total number of hotels that are in the bank.
     * @param hotels    an int, representing the number of hotels.
     */
    public void setTotalNumberHotels(int hotels) {
        this.totalNumberHotels = hotels;
    }

    /**
     * Allows the player to build houses/hotels on the streets that they own.
     *
     * Keeps track of the number of houses/hotels on each street that the player clicks on,
     * ensuring that the number of houses/hotels do not exceed the total number
     * of houses and hotels in the bank.
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
                    if (((Street) s).buildHouses(getCurrentPlayer(), gameboard) == null) {
                        // player does not own all the properties in the color set

                        return;
                    }
                    else {
                        // player owns all the properties in the same color set

                        ArrayList<Street> ownedSquaresMatching = ((Street) s).buildHouses(getCurrentPlayer(), gameboard);

                        int totalHouses = 0;
                        for(int sameStreet = 0; sameStreet < ownedSquaresMatching.size(); sameStreet++) {
                            // iterating through the ArrayList and calculating the total number of houses
                            // on each street of a specific color set

                            totalHouses += ownedSquaresMatching.get(sameStreet).getHouses();

                        }


                        // calculation below that ensures the player is building houses evenly
                        float average = totalHouses / (float) ownedSquaresMatching.size();

                        int numHouses = ((Street) s).getHouses();

                        if (numHouses > average && !((Street) s).getMaxCapacityReached()) {

                            throw new BuildHousesException("You must build houses evenly! Try again.");
                            // cannot build house since player is not building evenly
                        }

                        if (((Street) s).getHouseCost() <= buyer.getMoney()) {
                            // the cost of the house must be less than or equal to the buyer's total amount of money

                            if(!((Street) s).getMaxCapacityReached()) {
                                // runs as long as the maximum capacity (5 houses) is not reached
                                // The 5th house is technically the hotel

                                totalNumberHouses--;

                                if (totalNumberHouses >= 0) {
                                    // runs as long as there are enough houses available in the bank
                                    // for the player to buy

                                    buyer.removeMoney(((Street) s).getHouseCost());
                                    ((Street) s).setHouses(++numHouses);

                                    if (totalHouses == (4 * ownedSquaresMatching.size()) - 1) {
                                        // 4 houses have been built on each street of a specific color set

                                        buildingState = BuildingState.ALL_HOUSES_BUILT;
                                    }

                                    getCurrentPlayer().setTotalNumberHouses(getCurrentPlayer().getTotalHouses() + 1);

                                    if (numHouses == 5) {
                                        // hotel is built on the street, all houses are returned to the bank
                                        ((Street) s).setMaxCapacityReached(true);

                                        totalNumberHouses += 5;
                                        // houses returned to the bank, so plus 5

                                        totalNumberHotels--;

                                        if(totalNumberHotels >= 0) {
                                            // 5 houses technically equal 1 hotel, so we set hotels = 1
                                            ((Street) s).setHotels(1);

                                            int totalHotels = 0;
                                            for(int sameStreet = 0; sameStreet < ownedSquaresMatching.size(); sameStreet++) {
                                                // iterating through the ArrayList and calculating the total number of hotels
                                                // on each street of a specific color set

                                                totalHotels += ownedSquaresMatching.get(sameStreet).getHotel();

                                                if (totalHotels == ownedSquaresMatching.size()) {
                                                    buildingState = BuildingState.ALL_HOTELS_BUILT;
                                                }

                                            }

                                            getCurrentPlayer().setTotalNumberHotels(getCurrentPlayer().getTotalNumberHotels() + 1);

                                            return;
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
                        } else {
                            throw new InsufficientMoneyException("You do not have enough money to purchase this house.");

                        }

                    }
                }
            }
        }
    }
}





