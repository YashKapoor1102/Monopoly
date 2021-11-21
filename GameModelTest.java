/**
 * @author Himanshu Singh
 * @version Milestone 3
 */

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * The type Game model test.
 */
public class GameModelTest {

    GameModel gm;
    /**
     * @Author Himanshu Singh
     * Test add game view.
     */
    @Test
    public void testAddGameView() {
        gm = new GameModel();
        GameView ad = null;
        gm.addGameView(ad);
        assertEquals(ad, gm.getGameViews().get(0));
        assertEquals(gm.getGameViews().size(), 1);
    }

    /**
     * @Author Himanshu Singh
     * Test remove game view.
     */
    @Test
    public void testRemoveGameView() {
        gm = new GameModel();
        GameView ad = null;
        gm.addGameView(ad);
        assertEquals(gm.getGameViews().size(), 1);
        gm.removeGameView(ad);
        assertEquals(gm.getGameViews().size(), 0);
    }

    /**
     * @Author Himanshu Singh
     * Test create and get gameboard.
     */
    /*
    @Test
    public void testCreateAndGetGameboard() {
        System.out.println("Testing GetGameboard and CreatingGameboard Function...");
        gm = new GameModel();
        gm.createGameboard();
        ArrayList<Square> squares = new ArrayList<>();
        Collections.addAll(squares, new Square() {
            @Override
            public String getName() {
                return "Initial Starting Point";
            }

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
        System.out.println("Expected: " + squares.toString() + "" + "\nActual: " +
                gm.createGameboard().getSquares().toString() + "\n");
        assertEquals(squares.toString(), gm.createGameboard().getSquares().toString());
    }

     */

    /**
     * @Author Himanshu Singh
     * Test initial gamestate.
     */
    @Test
    public void testInitialGamestate() {
        System.out.println("Testing InitialGamestate Function...");
        gm = new GameModel();
        GameModel.GameState testGamestate = GameModel.GameState.ADDING_PLAYERS;
        System.out.println("Expected: " + testGamestate + " | Actual: " + gm.getGameState() + "\n");
        assertEquals(testGamestate,gm.getGameState());
    }

    /**
     * @Author Himanshu Singh
     * Test add player.
     */
    @Test
    public void testAddPlayer(){
        System.out.println("Testing AddPlayer Function...");
        Player a = new Player("Himanshu",1500);
        System.out.println("Player named himanshu is added and given 1500 dollars");
        gm = new GameModel();
        gm.addPlayer(a);
        String PlayerName = "Himanshu";
        int StartingMoney = 1500;
        System.out.println("Expected: " + a.getName() + " | Actual: " + PlayerName);
        assertEquals(PlayerName,a.getName());
        System.out.println("Expected: " + a.getMoney() + " | Actual: " + StartingMoney + "\n");
        assertEquals(StartingMoney,a.getMoney());
    }

    /**
     * @Author Himanshu Singh
     * Test one player remaining.
     */
    @Test
    public void testOnePlayerRemaining(){
        System.out.println("Testing OnePlayerRemaining Function...");
        Player a = new Player("Himanshu",1500);
        gm = new GameModel();
        gm.addPlayer(a);
        gm.onePlayerRemaining();
        GameModel.GameState testGamestate = GameModel.GameState.GAME_OVER;
        System.out.println("Expected: " + testGamestate + " | Actual: " + gm.getGameState() + "\n");
        assertEquals(testGamestate,gm.getGameState());

    }

    /**
     * @Author Himanshu Singh
     * Test rolls.
     */
    @Test
    public void testRolls(){
        System.out.println("Testing Roll Function...");
        gm = new GameModel();
        Player a = new Player("Yash",1500);
        Player b = new Player("Himanshu",1500);
        gm.addPlayer(a);
        gm.addPlayer(b);
        gm.startGame();
        GameModel.GameState testGamestate = GameModel.GameState.PLAYER_ROLLING;
        System.out.println("Expected: " + testGamestate + " | Actual: " + gm.getGameState());
        assertEquals(testGamestate,gm.getGameState());
        gm.roll();
        GameModel.GameState testGamestate2 = GameModel.GameState.PLAYER_ROLLED_DOUBLES;
        GameModel.GameState testGamestate3 = GameModel.GameState.PLAYER_ROLLED_NORMAL;
        if(gm.getGameState().equals(testGamestate2)){
            System.out.println("Expected: " + testGamestate2 + " | Actual: " + gm.getGameState() + "\n");
            assertEquals(testGamestate2,gm.getGameState());
        }
        else{
            System.out.println("Expected: " + testGamestate3 + " | Actual: " + gm.getGameState() + "\n");
            assertEquals(testGamestate3,gm.getGameState());
        }
    }

    /**
     * @Author Himanshu Singh
     * Test get players.
     */
    @Test
    public void testGetPlayers(){
        System.out.println("Testing getPlayers Function...");
        Player a = new Player("Himanshu",1500);
        Player b = new Player("Yash", 1500);
        Player c = new Player("Oliver", 1500);
        Player d = new Player("Robert", 1500);
        ArrayList<String> names = new ArrayList<String>();
        names.add("Himanshu");
        names.add("Yash");
        names.add("Oliver");
        names.add("Robert");
        int startingMoney = 1500;
        gm = new GameModel();
        gm.addPlayer(a);
        gm.addPlayer(b);
        gm.addPlayer(c);
        gm.addPlayer(d);
        for(int i = 0; i < 4; i++) {
            System.out.println("Expected: " + names.get(i) + " has " + 1500 + " | Actual: " +
                    gm.getPlayers().get(i).getName() + " has " + gm.getPlayers().get(i).getMoney());

            assertEquals(names.get(i),gm.getPlayers().get(i).getName());
            assertEquals(startingMoney,gm.getPlayers().get(i).getMoney());
        }
        System.out.println("\n");
    }

    /**
     * @Author Himanshu Singh
     * Test remove player.
     */
    @Test
    public void testRemovePlayer(){
        System.out.println("Testing RemovePlayer Function...");
        Player a = new Player("Himanshu",1500);
        Player b = new Player("Yash", 1500);
        gm = new GameModel();
        GameModel gm2 = new GameModel();
        gm.addPlayer(a);
        gm.addPlayer(b);
        gm2.addPlayer(b);
        System.out.println("Before Removal: " + gm.getPlayers());
        gm.removePlayer(a);
        System.out.println("After Removal (Removed Himanshu): " + gm.getPlayers());
        System.out.println("Expected: " + gm2.getPlayers().get(0).getName() + " | Actual: " + gm.getPlayers().get(0).getName() + "\n");
        assertArrayEquals(gm.getPlayers().toArray(),gm2.getPlayers().toArray());
    }

    /**
     * @Author Himanshu Singh
     * Test get current player.
     */
    @Test
    public void testGetCurrentPlayer(){
        System.out.println("Testing GetCurrentPlayer Function...");
        Player a = new Player("Himanshu",1500);
        Player b = new Player("Yash", 1500);
        gm = new GameModel();
        gm.addPlayer(a);
        gm.addPlayer(b);
        gm.startGame();
        gm.roll();
        GameModel.GameState testGameState = GameModel.GameState.PLAYER_ROLLED_NORMAL;
        System.out.println(gm.getCurrentPlayer().getName());
        System.out.println(gm.getPlayers().get(0).getName());
        System.out.println(gm.getPlayers().get(1).getName());
        gm.setGameState(GameModel.GameState.PLAYER_ROLLED_NORMAL);
        if(gm.getGameState() == testGameState){
            String expectedPlayer = "Yash";
            System.out.println("Expected: " + expectedPlayer + " | Actual: " + gm.getCurrentPlayer().getName() + "\n");
            gm.passTurn(false);
            assertEquals(gm.getCurrentPlayer().getName(),expectedPlayer);
        }
        GameModel.GameState testGameState2 = GameModel.GameState.PLAYER_ROLLED_DOUBLES;
        gm.setGameState(GameModel.GameState.PLAYER_ROLLED_DOUBLES);
        if(gm.getGameState()==testGameState2){
            String expectedPlayer = "Yash";
            gm.passTurn(false);
            assertEquals(gm.getCurrentPlayer().getName(),expectedPlayer);

        }
    }


    /**
     * @Author Himanshu Singh
     * Test player land on square.
     */
    @Test
    public void testPlayerLandOnSquare(){
        System.out.println("Testing PlayerLandOnSquare Function...");
        gm = new GameModel();
        Player a = new Player("a",3000000);
        Player b = new Player("b", 15);
        gm.createGameboard();
        gm.addPlayer(a);
        gm.addPlayer(b);
        gm.startGame();
        int z[]=gm.roll();
        if (z[0] == z[1]){
            z[0] = z[0]-1;
        }
        int total = 8;
        System.out.println("In the case that the player lands on unowned property and has enough money... ");
        if(gm.getGameboard().getSquare(total) instanceof Property){
            a.setPosition(total);
            gm.buyProperty(a);
            System.out.println(a.getMoney());
        }
        else{
            System.out.println("Not a property");
        }
        System.out.println("Expected: Player is not Bankrupt, set bankruptcy to FALSE" + " | Actual: " +
                gm.playerLandOnSquare(a, a.getProperties().get(0)) + "\n");
        assertFalse(gm.playerLandOnSquare(a, a.getProperties().get(0)));
        System.out.println("In the case that the player lands on owned property and does not have money... ");

        gm.passTurn(false);
        b.setPosition(total);
        b.addProperty((Property)gm.getGameboard().getSquare(total - 1));

        System.out.println("Player B owns " + b.getProperties().get(0));
        System.out.println();
        assertTrue(gm.playerLandOnSquare(b,gm.getGameboard().getSquares().get(total)));
    }


    /**
     * @Author Himanshu Singh
     * Test pass turn.
     */
    @Test
    public void testPassTurn(){
        gm = new GameModel();
        Player a = new Player("a",3000000);
        Player b = new Player("b", 15);
        gm.createGameboard();
        gm.addPlayer(a);
        gm.addPlayer(b);
        gm.startGame();
        gm.roll();
        GameModel.GameState testGameState = GameModel.GameState.PLAYER_ROLLED_NORMAL;
        gm.setGameState(testGameState);
        gm.passTurn(false);
        assertEquals(b.getName(),gm.getCurrentPlayer().getName());
        GameModel.GameState testGameStateDoubles = GameModel.GameState.PLAYER_ROLLED_DOUBLES;
        gm.setGameState(testGameStateDoubles);
        gm.passTurn(false);
        assertEquals(b.getName(),gm.getCurrentPlayer().getName());
    }

    /**
     * @Author Himanshu Singh
     * Test buy property.
     */
    @Test
    public void testBuyProperty(){

        gm = new GameModel();
        Player a = new Player("a",1500);
        Player b = new Player("b", 1500);
        gm.createGameboard();
        gm.addPlayer(a);
        gm.addPlayer(b);
        gm.startGame();
        int z[]=gm.roll();
        int total = z[0]+z[1];
        if(gm.getGameboard().getSquare(total) instanceof Property){
            gm.getCurrentPlayer().getProperties();
            assertEquals(gm.getCurrentPlayer().getProperties().size(), 0);
            gm.buyProperty(a);
            assertEquals(gm.getCurrentPlayer().getProperties().size(), 1);
            // we're not testing if the player owns the property already because in that case an exception is thrown

        }
        else{
            System.out.println("Not a property");
        }
    }

    /**
     * @Author Himanshu Singh
     * Test start game.
     */
    @Test
    public void testStartGame(){
        gm = new GameModel();
        Player a = new Player("a",1500);
        Player b = new Player("b", 1500);
        gm.createGameboard();
        gm.addPlayer(a);
        gm.addPlayer(b);
        GameModel.GameState testGameState = GameModel.GameState.PLAYER_ROLLING;
        gm.startGame();
        assertEquals(gm.getGameState(),testGameState);

    }

}
