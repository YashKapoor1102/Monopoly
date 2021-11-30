/**
 * @author Robert Simionescu and Yash Kapoor
 * @version Milestone 3
 */

import gameexceptions.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.Serializable;
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
    private File file;

    /**
     * Constructor for a GameController. Takes a model and generates its list of views from the model.
     * @param model The model this is a controller for.
     */
    public GameController(GameModel model)
    {
        this.model = model;
        this.views = model.getGameViews();
        this.file = new File("SavedGame.txt");
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

        model.setBuildingState(GameModel.BuildingState.PLAYER_BUILDING);


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
    private void outOfJail() {
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
                            view.handleBuildingStatusUpdate(model);
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
    public void AITurn()
    {
        String AICommand = "";

        AICommand = ((PlayerAI)model.getCurrentPlayer()).playTurn();
        switch (AICommand)
        {
            case "Roll":
                roll();
                break;
            case "Buy Property":
                buyProperty();
                break;
            case "Build":
                AIBuild();
                break;
            case "Get Out of Jail":
                outOfJail();
                break;
            case "Pass turn":
                pass();
                break;
        }

        if (model.getCurrentPlayer() instanceof PlayerAI)
        {
            AITurn();
        }
    }

    public void save() {
        model.save(file);
    }

    public void load() {
        model.load(file);
    }

    /**
     * Handles all the mouse clicks in the game.
     *
     * It is used to allow the user to click on the squares of the gameboard.
     * Specifically, they can click on the squares to build houses/hotels on them one at a time.
     *
     * @param e The MouseEvent that has occurred
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel currentLabel = (JLabel) e.getSource();

        try {
            model.buildOnProperty(model.getCurrentPlayer(), currentLabel.getName());

            for (GameView view : views) {
                view.handleGameStatusUpdate(model);
                view.handleBuildingStatusUpdate(model);
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
            case "Add Player" -> addPlayer();
            case "Add AI" -> addAIPlayer();
            case "Start" -> start();
            case "Roll" -> roll();
            case "Buy Property" -> buyProperty();
            case "Build" -> buildOnProperty();
            case "Get Out of Jail" -> outOfJail();
            case "Pass turn" -> pass();
            case "Save" -> save();
            case "Load" -> load();
        }
        for (GameView view : views)
        {
            view.handleGameStatusUpdate(model);
            view.handleBuildingStatusUpdate(model);
        }

    }
}
