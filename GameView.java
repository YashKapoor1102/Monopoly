/**
 * @author Robert Simionescu and Yash Kapoor
 * Interface containing the basic functionality of all GameViews.
 */
public interface GameView
{
    public void handleGameStatusUpdate(GameModel gameModel);

    public void handleBuildingStatusUpdate(GameModel gameModel);
}
