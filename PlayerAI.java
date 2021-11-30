import java.io.Serializable;

/**
 * @author Robert Simionescu
 * Subclass of Player handling AI controlled players.
 */
public class PlayerAI extends Player implements Serializable
{
    private GameModel model;

    public enum PlayerState {ROLLING, ROLLED, IN_JAIL, TURN_OVER}

    private PlayerState state;

    /**
     * @author Robert Simionescu
     * Constructor for the PlayerAI.
     * @param name String containing the AI Player's name
     * @param money Int containing the starting money for the player
     * @param model GameModel that this player is a part of
     */
    public PlayerAI(String name, int money, GameModel model)
    {
        super(name, money);
        state = PlayerState.ROLLING;
        this.model = model;
    }

    /**
     * @author Robert Simionescu
     * Handles playing the AI player's turn and issuing commands to GameController to execute those commands.
     * @return A string representing the command the AI is attempting to use.
     */
    public String playTurn()
    {
        if (state == PlayerState.ROLLING)
        {
            state = PlayerState.ROLLED;
            return "Roll";
        }
        else if (state == PlayerState.ROLLED)
        {
            Square currentSquare = model.getGameboard().getSquare(model.getCurrentPlayer().getPosition());
            if (currentSquare instanceof Property)
            {
                state = PlayerState.TURN_OVER;
                if (((Property)currentSquare).getOwner() == null)
                {
                    if (this.getMoney() > ((Property)currentSquare).getCost())
                    {
                        return "Buy Property";
                    }
                }
            }
            else if (currentSquare.getName().equals("Go to Jail"))
            {
                this.state = PlayerState.IN_JAIL;
                return "Pass turn";
            }
            else
            {
                state = PlayerState.TURN_OVER;
            }
            return "Build";
        }
        else if (state == PlayerState.IN_JAIL)
        {
            if (this.getMoney() > 50)
            {
                this.state = PlayerState.ROLLING;
                return "Get Out of Jail";
            }
            else
            {
                this.state = PlayerState.IN_JAIL;
                return "Pass turn";
            }
        }
        state = PlayerState.ROLLING;
        return "Pass turn";
    }
}
