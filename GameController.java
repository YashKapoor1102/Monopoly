/**
 * @author Robert Simionescu and Yash Kapoor
 * @version Milestone 4
 */

import gameexceptions.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Yash Kapoor and Robert Simionescu
 *
 * Controller Class for GameModel
 * Responsible for controlling the functions of the buttons
 */
public class GameController extends MouseAdapter implements ActionListener, Serializable
{
    private GameModel model;
    private List<GameView> views;

    // Strings representing all of the commands that players can execute.
    private static final String ADD_PLAYER = "Add Player";
    private static final String ADD_AI = "Add AI";
    private static final String START = "Start";
    private static final String ROLL = "Roll";
    private static final String BUY_PROPERTY = "Buy Property";
    private static final String BUILD = "Build";
    private static final String GET_OUT_OF_JAIL = "Get Out of Jail";
    private static final String PASS_TURN = "Pass turn";
    private static final String SAVE = "Save";
    private static final String LOAD = "Load";

    private static final String REGULAR_VERSION = "Regular Monopoly";
    private static final String INTERNATIONAL_VERSION = "Nintendo Monopoly";

    private static final File FILE = new File("SavedGame.txt");

    /**
     * @author Yash Kapoor
     * Constructor for a GameController. Takes a model and generates its list of views from the model.
     *
     * Also provides the user with a drop-down menu, so they can select the version
     * (regular or international) that they would like to play
     *
     * @param model The model this is a controller for.
     */
    public GameController(GameModel model)
    {
        this.model = model;
        this.views = model.getGameViews();

        String[] versions = new String[2];
        versions[0] = REGULAR_VERSION;
        versions[1] = INTERNATIONAL_VERSION;

        try {
            // drop-down menu using a JOptionPane at the beginning of the game
            // allowing the user to select the version that they would like to play
            Object selectionObject = JOptionPane.showInputDialog(null,
                    "Choose your version", "Version Selection",
                    JOptionPane.QUESTION_MESSAGE, null, versions, REGULAR_VERSION);

            if (selectionObject.equals(REGULAR_VERSION)) {
                model.createGameboard("Standard_Gameboard.xml");
            }
            else {
                model.createGameboard("International_Gameboard.xml");
            }

        }
        catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Oops! You did not select a version of Monopoly.");
            System.exit(0);
        }

    }

    /**
     * @author Robert Simionescu
     * Displays a message to all views.
     * @param message The message to be displayed.
     */
    private void displayMessage(String message)
    {
        for (GameView view : views)
        {
            if (view instanceof GameFrame)
            {
                ((GameFrame)view).displayMessage(message);
            }
        }
    }

    /**
     * @author Robert Simionescu and Yash Kapoor
     * Attempts to start the game. Outputs an error message if there are not enough players to begin.
     */
    private void start()
    {
        try
        {
            // first player always starts
            displayMessage("1's turn.");

            model.startGame();
        }
        catch (NotEnoughPlayersException exception)
        {
            displayMessage(exception.getMessage());
        }
    }

    /**
     * @author Robert Simionescu and Yash Kapoor
     * Attempts to purchase the property the requesting player is on. Outputs an error message if they attempt to purchase
     * something they cannot detailing the reason the purchase cannot be completed.
     */
    private void buyProperty()
    {
        try
        {
            model.buyProperty(model.getCurrentPlayer());

            Property propertyPurchased = (Property)model.getGameboard().getSquare(model.getCurrentPlayer().getPosition());

            displayMessage("You have purchased " + propertyPurchased.getName() + " for $" + propertyPurchased.getCost());



        }
        catch(InsufficientMoneyException exception)
        {
            displayMessage(exception.getMessage());
        }
        catch(PropertyOwnedException exception)
        {
            displayMessage(exception.getMessage());
        }
        catch(InvalidSquareTypeException exception)
        {
            displayMessage(exception.getMessage());
        }
    }


    /**
     * @author Yash Kapoor
     *
     * Sets the building state as "PLAYER_BUILDING"
     * to indicate that the player is building either houses/hotels
     * on their properties.
     */
    private void buildOnProperty() {
        displayMessage("Click on the street that you own that you would like to build a house on.");
        displayMessage("Ensure that you build the houses evenly.");
    }


    /**
     * @author Robert Simionescu and Yash Kapoor
     * Passes the turn to the next player and outputs a message indicating which player's turn it is.
     */
    private void pass()
    {
        model.passTurn(false);

        displayMessage(model.getCurrentPlayer().getName() + "'s turn.");

        if (model.getCurrentPlayer() instanceof PlayerAI)
        {
            AITurn();
        }

    }

    /**
     * @author Robert Simionescu and Yash Kapoor
     * Rolls the dice for a player and moves them the appropriate amount of squares. Outputs the results of the roll. If
     * the player landed on another player's property, displays the payment that must be made and a message if the player
     * cannot pay and has gone bankrupt.
     */
    private void roll()
    {
        int[] roll = model.roll();

        if (model.getGameState() == GameModel.GameState.PLAYER_PASSED_GO) {
            displayMessage("You passed GO, so you get $200.");
        }

        if (roll[0] == roll[1] && !model.getCurrentPlayer().getInJail() && model.getGameState() != GameModel.GameState.DOUBLES_ROLLED_IN_JAIL)
        {
            displayMessage("You rolled a " + roll[0] + " and a " + roll[1] + ", so you will move " + (roll[0] + roll[1]) + " spaces and will get to go again.");
            model.setGameState(GameModel.GameState.PLAYER_ROLLED_DOUBLES);
        }
        else
        {
            displayMessage("You rolled a " + roll[0] + " and a " + roll[1] + ", so you will move " + (roll[0] + roll[1]) + " spaces.");
            model.setGameState(GameModel.GameState.PLAYER_ROLLED_NORMAL);
        }

        Square square = model.getGameboard().getSquare(model.getCurrentPlayer().getPosition());

        if (square instanceof Property)
        {
            if (((Property)square).getOwner() != null && ((Property)square).getOwner() != model.getCurrentPlayer())
            {
                displayMessage("You have landed on " + ((Property)square).getOwner().getName() + "'s property and must pay them $" + ((Property)square).calculateRent(model.getCurrentPlayer(), model.getGameboard()));
            }
        }
        if (model.getCurrentPlayer().isBankrupt())
        {
            model.passTurn(true);
            displayMessage("You could not pay your dept and have gone bankrupt.");

        }
    }

    /**
     * @author Robert Simionescu and Yash Kapoor
     * Attempts to add a new player to the game with the name of their position (e.g. first player added will be named "1").
     * Outputs an error message if adding the player failed.
     */
    private void addPlayer()
    {
        Player newPlayer = new Player(String.valueOf(model.getPlayers().size() + 1), GameModel.STARTING_MONEY);

        try
        {
            model.addPlayer(newPlayer);
            displayMessage("Player " + newPlayer.getName() + " added!");
        }
        catch (TooManyPlayersException exception)
        {
            displayMessage(exception.getMessage());
        }
        catch (DuplicateNameException exception)
        {
            displayMessage(exception.getMessage());
        }
    }

    /**
     * @author Robert Simionescu
     * Adds an AI controlled player to the game with the name of their position and [AI] added to the end (e.g. "2 [AI]")
     */
    private void addAIPlayer()
    {
        Player newPlayer = new PlayerAI(String.valueOf(model.getPlayers().size() + 1) + " [AI]", GameModel.STARTING_MONEY, model);

        try
        {
            model.addPlayer(newPlayer);
            displayMessage("AI player " + newPlayer.getName() + " added!");
        }
        catch (TooManyPlayersException exception)
        {
            displayMessage(exception.getMessage());
        }
        catch (DuplicateNameException exception)
        {
            displayMessage(exception.getMessage());
        }
    }

    /**
     * Allows the player to get out of jail by paying a fine of $50.
     */
    private void getOutOfJail() {
        if (model.getCurrentPlayer().getMoney() > 50)
        {
            model.getCurrentPlayer().removeMoney(50);
            model.getCurrentPlayer().setInJail(false);
        }
        else
        {
            displayMessage("You do not have enough money to get out of jail.");
        }

    }

    /**
     * @author Robert Simionescu
     * Allows the AI to build structures on its properties. AI will only build if it will not bring its total money
     * down below $200. It will attempt to build on a random owned property 5 times. If the AI does not own any streets,
     * nothing happens.
     */
    private void AIBuild()
    {
        int numProperties = model.getCurrentPlayer().getProperties().size();
        Random r = new Random();

        if (numProperties != 0)
        {
            for (int i = 0; i < 5; i++)
            {
                Property property = model.getCurrentPlayer().getProperties().get(r.nextInt(numProperties));
                if (property instanceof Street)
                {
                    try {
                        model.buildOnProperty(model.getCurrentPlayer(), property.getName());

                        for (GameView view : views) {
                            view.handleGameStatusUpdate(model);
                            //view.handleBuildingStatusUpdate(model);
                        }
                    }
                    catch(InsufficientMoneyException exception)
                    {
                        displayMessage(exception.getMessage());
                    }
                    catch(BuildHousesException exception) {
                        displayMessage(exception.getMessage());
                    }
                    catch(NotEnoughHotelsException exception) {
                        displayMessage(exception.getMessage());
                    }
                    catch(NotEnoughHousesException exception) {
                        displayMessage(exception.getMessage());
                    }
                }
            }
        }

    }

    /**
     * @author Robert Simionescu
     * Allows the AI to play its turn.
     */
    private void AITurn()
    {
        String AICommand = "";

        AICommand = ((PlayerAI)model.getCurrentPlayer()).playTurn();
        switch (AICommand) {
            case ROLL -> roll();
            case BUY_PROPERTY -> buyProperty();
            case BUILD -> AIBuild();
            case GET_OUT_OF_JAIL -> getOutOfJail();
            case PASS_TURN -> pass();
        }

        if (model.getCurrentPlayer() instanceof PlayerAI)
        {
            AITurn();
        }
    }

    /**
     * @author Yash Kapoor
     * Saves the game in a text file called SavedGame.txt
     */
    private void save() {
        model.save(FILE);

        JOptionPane.showMessageDialog(null, "Your game has been saved!");
    }

    /**
     * @author Yash Kapoor
     * loads the game from a text file called SavedGame.txt
     */
    private void load() {

        model.load(FILE);

        JOptionPane.showMessageDialog(null, "Your game has been loaded!");
    }

    /**
     * @author Yash Kapoor
     * Handles all the mouse clicks in the game.
     *
     * It is used to allow the user to click on the squares of the gameboard.
     * Specifically, they can click on the squares to build houses/hotels on them one at a time.
     *
     * @param e The MouseEvent that has occurred
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        JPanel currentLabel = (JPanel) e.getSource();

        try {

            ArrayList<Square> gameboardSquares = model.getGameboard().getSquares();
            boolean streetMaximumCapacityReached = false;

            for (Square s : gameboardSquares) {
                if (s instanceof Street) {
                    if ((s.getName().equals(currentLabel.getName()))) {
                        streetMaximumCapacityReached = ((Street) s).getMaxCapacityReached();
                    }
                }
            }

            if(streetMaximumCapacityReached) {
                JOptionPane.showMessageDialog(null,
                        "This street has reached its maximum capacity. No more houses/hotels can be built.");
            }
            else {
                model.buildOnProperty(model.getCurrentPlayer(), currentLabel.getName());
            }

            for (GameView view : views) {
                view.handleGameStatusUpdate(model);
            }

        }
        catch(InsufficientMoneyException exception)
        {
            displayMessage(exception.getMessage());
        }
        catch(BuildHousesException exception) {
            displayMessage(exception.getMessage());
        }
        catch(NotEnoughHotelsException exception) {
            displayMessage(exception.getMessage());
        }
        catch(NotEnoughHousesException exception) {
            displayMessage(exception.getMessage());
        }
    }

    /**
     * @author Robert Simionescu and Yash Kapoor
     * Handles all button presses in the game. Calls the corresponding methods in GameModel and outputs whatever messages
     * should be output to GameFrame.
     * @param e The ActionEvent that has occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand()) {
            case ADD_PLAYER -> addPlayer();
            case ADD_AI -> addAIPlayer();
            case START -> start();
            case ROLL -> roll();
            case BUY_PROPERTY -> buyProperty();
            case BUILD -> buildOnProperty();
            case GET_OUT_OF_JAIL -> getOutOfJail();
            case PASS_TURN -> pass();
            case SAVE -> save();
            case LOAD -> load();
        }
        for (GameView view : views)
        {
            view.handleGameStatusUpdate(model);
        }

    }
}
