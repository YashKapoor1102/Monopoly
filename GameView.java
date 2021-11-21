/**
 * @author Robert Simionescu
 * Interface containing the basic functionality of all GameViews.
 */
public interface GameView
{
    void handleGameStatusUpdate(GameModel gameModel);

    void handleBuildingStatusUpdate(GameModel gameModel);
}
