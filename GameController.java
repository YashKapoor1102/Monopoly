/**
 * @author Robert Simionescu and Yash Kapoor
 * @version Milestone 2
 */

import gameexceptions.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Yash Kapoor and Robert Simionescu
 *
 * Controller Class for GameModel
 * Responsible for controlling the functions of the buttons
 */
public class GameController implements ActionListener
{
    private GameModel model;
    private List<GameView> views;

    /**
     * Constructor for a GameController. Takes a model and generates its list of views from the model.
     * @param model The model this is a controller for.
     */
    public GameController(GameModel model)
    {
        this.model = model;
        this.views = model.getGameViews();
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
     * @author Robert Simionescu and Yash Kapoor
     * Passes the turn to the next player and outputs a message indicating which player's turn it is.
     */
    private void pass()
    {

        model.passTurn(false);

        displayMessage(model.getCurrentPlayer().getName() + "'s turn.");

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
        if (roll[0] == roll[1])
        {
            displayMessage("You rolled a " + roll[0] + " and a " + roll[1] + ", so you will move " + (roll[0] + roll[1]) + " spaces and will get to go again.");
        }
        else
        {
            displayMessage("You rolled a " + roll[0] + " and a " + roll[1] + ", so you will move " + (roll[0] + roll[1]) + " spaces.");
        }

        Square square = model.getGameboard().getSquare(model.getCurrentPlayer().getPosition());

        if (square instanceof Property)
        {
            if (((Property)square).getOwner() != null && ((Property)square).getOwner() != model.getCurrentPlayer())
            {
                displayMessage("You have landed on " + ((Property)square).getOwner().getName() + "'s property and must pay them $" + ((Property)square).calculateRent(model.getGameboard()));
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
     * @author Robert Simionescu and Yash Kapoor
     * Handles all button presses in the game. Calls the corresponding methods in GameModel and outputs whatever messages
     * should be output to GameFrame.
     * @param e The actionevent that has occured.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand()) {
            case "Add Players" -> addPlayer();
            case "Start" -> start();
            case "Roll" -> roll();
            case "Buy Property" -> buyProperty();
            case "Pass turn" -> pass();
        }
        for (GameView view : views)
        {
            view.handleGameStatusUpdate(model);
        }
    }
}
