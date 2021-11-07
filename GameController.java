import gameexceptions.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;

public class GameController implements ActionListener
{
    GameModel model;
    List<GameView> views;

    public GameController(GameModel model)
    {
        this.model = model;
        this.views = model.getGameViews();
    }

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

    public void start()
    {
        try
        {
            model.startGame();
        }
        catch (NotEnoughPlayersException exception)
        {
            displayMessage(exception.getMessage());
        }
    }

    public void buyProperty()
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

    public void pass()
    {
        model.passTurn();
        displayMessage(model.getCurrentPlayer().getName() + "'s turn.");
    }

    public void roll()
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
            if (((Property)square).getOwner() != null)
            {
                displayMessage("You must pay $" + ((Property)square).calculateRent(model.getGameboard()) + " to " + ((Property)square).getOwner().getName());
            }
        }
        if (model.getCurrentPlayer().isBankrupt())
        {
            displayMessage("You could not pay your dept and have gone bankrupt.");
            pass();
        }
    }

    private void addPlayers()
    {
        Player newPlayer = new Player(String.valueOf(random()), GameModel.STARTING_MONEY);    //todo: add a text input for the name

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
     * @Author Robert Simionescu and Yash Kapoor
     * Handles all button presses in the game. Calls the corresponding methods in GameModel and outputs whatever messages
     * should be output to GameFrame.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand()) {
            case "Add Players":
                addPlayers();
                break;

            case "Start":
                start();
                break;

            case "Roll":
                roll();
                break;

            case "Buy Property":
                buyProperty();
                break;

            case "Pass turn":
                pass();
                break;
        }
        for (GameView view : views)
        {
            view.handleGameStatusUpdate(model);
        }
    }
}
