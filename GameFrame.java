/**
 * @author Robert Simionescu and Yash Kapoor
 * @version Milestone 3
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * @author Robert Simionescu and Yash Kapoor
 * Class handling the UI for Monopoly to the user.
 */
public class GameFrame extends JFrame implements GameView {

    private final JButton addPlayer;
    private final JButton addAIPlayer;
    private final JButton start;
    private final JButton roll;
    private final JButton bp;
    private final JButton pt;
    private final JButton jail;
    private final JButton build;

    private GameController gameController;

    // Buttons to be implemented in Milestone 4
    private final JButton save;
    private final JButton load;

    private ArrayList<JPanel> jPanelList;
    private final JPanel gameboardPanel;
    private final JPanel playerPanel;
    private final JPanel bodyPanel;

    private final JPanel messageBox;
    private final JLabel[] messages;
    private final JLabel[] playerNames;
    private final JPanel[] fullPlayerPanels;
    private final JPanel[] simplePlayerPanels;

    /**
     * @author Robert Simionescu and Yash Kapoor
     * Constructor for a GameFrame. Initializes all the necessary Swing components to begin the game.
     */
    public GameFrame() {
        // Game is NOT called Monopoly due to copyright reasons
        super("Funopoly");

        GameModel model = new GameModel();
        model.addGameView(this);

        // Used to display all the players and their total money on the side of the screen at all times
        fullPlayerPanels = new JPanel[GameModel.MAX_PLAYERS];

        // Used to indicate where the players are on the board
        simplePlayerPanels = new JPanel[GameModel.MAX_PLAYERS];
        playerNames = new JLabel[GameModel.MAX_PLAYERS];

        this.setResizable(false);

        // Text field that contains the past 3 messages to the user
        messageBox = new JPanel(new GridLayout(4, 1));
        messages = new JLabel[4];
        for (JLabel message : messages) {
            message = new JLabel();
            message.setHorizontalAlignment(SwingConstants.CENTER);
            message.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
            messageBox.add(message);
        }

        displayMessage("Welcome to Funopoly! Add at least 2 players to begin.");

        // List of labels used to represent each square
        jPanelList = new ArrayList<>();

        // Buttons used to interface with the game
        addPlayer = new JButton("Add Player");
        addAIPlayer = new JButton("Add AI");
        start = new JButton("Start");
        roll = new JButton("Roll");
        bp = new JButton("Buy Property");
        pt = new JButton("Pass turn");
        jail = new JButton("Get Out of Jail");

        build = new JButton("Build");
        save = new JButton("Save");
        load = new JButton("Load");

        gameController = new GameController(model);

        addPlayer.addActionListener(gameController);
        addAIPlayer.addActionListener(gameController);
        start.addActionListener(gameController);
        roll.addActionListener(gameController);
        bp.addActionListener(gameController);
        build.addActionListener(gameController);
        jail.addActionListener(gameController);
        pt.addActionListener(gameController);
        save.addActionListener(gameController);
        load.addActionListener(gameController);

        addPlayer.setEnabled(true);
        addAIPlayer.setEnabled(false);
        start.setEnabled(false);
        roll.setEnabled(false);
        bp.setEnabled(false);
        jail.setEnabled(false);
        build.setEnabled(false);
        pt.setEnabled(false);

        // The section of the UI that displays the gameboard, controls, message box, and the players on the board
        gameboardPanel = new JPanel(new BorderLayout());

        // The section of the UI that displays the list of players and how much money they have
        playerPanel = new JPanel(new GridLayout(GameModel.MAX_PLAYERS, 1));
        playerPanel.setPreferredSize(new Dimension(220, 1000));

        // The main panel that contains everything
        bodyPanel = new JPanel(new BorderLayout());
        bodyPanel.add(gameboardPanel, BorderLayout.CENTER);
        bodyPanel.add(playerPanel, BorderLayout.LINE_END);

        // Add all squares to the gameboardPanel
        GamePanelsInitialization.initializeSouthPanels(jPanelList, model.getGameboard());
        GamePanelsInitialization.initializeWestPanels(jPanelList, model.getGameboard());
        GamePanelsInitialization.initializeNorthPanels(jPanelList, model.getGameboard());
        GamePanelsInitialization.initializeEastPanels(jPanelList, model.getGameboard());

        gameboardPanel.add(getNorth(), BorderLayout.NORTH);
        gameboardPanel.add(getSouth(), BorderLayout.SOUTH);
        gameboardPanel.add(getCenter(), BorderLayout.CENTER);
        gameboardPanel.add(getEast(), BorderLayout.EAST);
        gameboardPanel.add(getWest(), BorderLayout.WEST);
        gameboardPanel.setPreferredSize(new Dimension(1000, 1250));

        // ADD the window listener
        // we no longer want the frame to close immediately when we press "x"
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    // close it down!
                    setVisible(false);
                    dispose();
                }
            }
        });

        this.add(bodyPanel);
        this.pack();

        this.setVisible(true);

    }

    /**
     * @param message The message that will be displayed to the message board.
     * @author Robert Simionescu
     * Displays a message on the bottom of the message field. Shifts the existing messages up and removes the oldest one.
     */
    public void displayMessage(String message) {
        ((JLabel) messageBox.getComponent(0)).setText(((JLabel) messageBox.getComponent(1)).getText());
        ((JLabel) messageBox.getComponent(1)).setText(((JLabel) messageBox.getComponent(2)).getText());
        ((JLabel) messageBox.getComponent(2)).setText(((JLabel) messageBox.getComponent(3)).getText());
        ((JLabel) messageBox.getComponent(3)).setText(message);
    }

    /**
     * @return A JPanel with all the labels for the south side of the board.
     * @author Yash Kapoor
     * Getter for the squares on the south side of the board.
     */
    private JPanel getSouth() {

        JPanel south = new JPanel();

        JPanel squarePanel = new JPanel(new BorderLayout());
        BoxLayout boxlayout = new BoxLayout(squarePanel, BoxLayout.X_AXIS);
        squarePanel.setLayout(boxlayout);

        int startIndex = GamePanelsInitialization.SOUTH_SQUARES - 1;
        int endIndex = 0;
        for (int i = startIndex; i >= endIndex; i--)
        {
            squarePanel.add(jPanelList.get(i));
        }

        south.setLayout(new GridLayout());
        south.add(squarePanel);

        south.setBackground(new Color(187, 255, 202));
        south.setPreferredSize(new Dimension(500, 150));

        return south;
    }

    /**
     * @return A JPanel with all the labels for the west side of the board.
     * @author Yash Kapoor
     * Getter for the squares on the west side of the board.
     */
    private JPanel getWest() {

        JPanel west = new JPanel();

        JPanel squarePanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(squarePanel, BoxLayout.Y_AXIS);
        squarePanel.setLayout(boxlayout);

        int startIndex = GamePanelsInitialization.SOUTH_SQUARES + GamePanelsInitialization.WEST_SQUARES - 1;
        int endIndex = GamePanelsInitialization.SOUTH_SQUARES;
        for (int i = startIndex; i >= endIndex; i--)
        {
            squarePanel.add(jPanelList.get(i));
        }

        west.setLayout(new GridLayout());
        west.add(squarePanel);
        west.setBackground(new Color(187, 255, 202));

        west.setPreferredSize(new Dimension(110, 2000));

        return west;
    }

    /**
     * @return A JPanel with all the labels for the north side of the board.
     * @author Yash Kapoor
     * Getter for the squares on the north side of the board.
     */
    private JPanel getNorth() {

        JPanel north = new JPanel();

        JPanel squarePanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(squarePanel, BoxLayout.X_AXIS);
        squarePanel.setLayout(boxlayout);

        int startIndex = GamePanelsInitialization.SOUTH_SQUARES + GamePanelsInitialization.WEST_SQUARES;
        int endIndex = GamePanelsInitialization.SOUTH_SQUARES + GamePanelsInitialization.WEST_SQUARES + GamePanelsInitialization.NORTH_SQUARES - 1;
        for (int i = startIndex; i <= endIndex; i++)
        {
            squarePanel.add(jPanelList.get(i));
        }

        north.setLayout(new GridLayout());
        north.add(squarePanel);

        north.setBackground(new Color(187, 255, 202));
        north.setPreferredSize(new Dimension(500, 150));

        return north;
    }

    /**
     * @return A JPanel with all the labels for the east side of the board.
     * @author Yash Kapoor
     * Getter for the squares on the east side of the board.
     */
    private JPanel getEast() {

        JPanel east = new JPanel();

        JPanel squarePanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(squarePanel, BoxLayout.Y_AXIS);
        squarePanel.setLayout(boxlayout);

        int startIndex = GamePanelsInitialization.SOUTH_SQUARES + GamePanelsInitialization.WEST_SQUARES + GamePanelsInitialization.NORTH_SQUARES;
        int endIndex = GamePanelsInitialization.SOUTH_SQUARES + GamePanelsInitialization.WEST_SQUARES + GamePanelsInitialization.NORTH_SQUARES + GamePanelsInitialization.EAST_SQUARES - 1;
        for (int i = startIndex; i <= endIndex; i++)
        {
            squarePanel.add(jPanelList.get(i));
        }

        east.setLayout(new GridLayout());
        east.add(squarePanel);

        east.setBackground(new Color(187, 255, 202));
        east.setPreferredSize(new Dimension(110, 200));

        return east;
    }

    /**
     * @return A JPanel with all the labels for the south side of the board.
     * @author Robert Simionescu and Yash Kapoor
     * Getter for center of the board, containing the funopoly logo, the buttons used to interface with the game, and
     * the message board.
     */
    private JPanel getCenter() {
        JPanel center = new JPanel(new BorderLayout());

        JLabel funopoly = new JLabel();
        ImageIcon fun = new ImageIcon(this.getClass().getResource("Images/Funopoly.png"));
        Image f = fun.getImage().getScaledInstance(600, 800, Image.SCALE_DEFAULT);
        ImageIcon bd = new ImageIcon(f);

        funopoly.setIcon(bd);
        center.add(funopoly, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new GridLayout(3, 4));

        buttons.add(addPlayer);
        buttons.add(addAIPlayer);
        buttons.add(start);
        buttons.add(roll);
        buttons.add(bp);
        buttons.add(build);
        buttons.add(jail);
        buttons.add(pt);

        buttons.add(save);
        buttons.add(load);

        buttons.setBackground(new Color(187, 255, 202));

        center.add(buttons, BorderLayout.PAGE_START);

        center.add(messageBox, BorderLayout.PAGE_END);


        return center;

    }

    /**
     * @author Yash Kapoor
     *
     * Updates the GUI to reflect any changes made by the previous action.
     * It is used to allow the user to build houses/hotels on their streets.
     *
     * They can do so by clicking on the street in the GUI and building houses one by one.
     * Then, eventually upgrading to a hotel (indicated by 5 houses) if they wish.
     *
     * @param gameModel     a GameModel Class Object, the model to update the GUI to match
     */
    private void handleBuildingStatusUpdate(GameModel gameModel) {

        if (gameModel.getBuildingState() == GameModel.BuildingState.PLAYER_BUILDING) {

            // iterating through the jLabelList ArrayList
            // and assigning a MouseListener to every component
            for(int i = 0; i < jPanelList.size(); i++) {

                // avoiding more than one mouse listener at all times
                jPanelList.get(i).removeMouseListener(gameController);
                jPanelList.get(i).addMouseListener(gameController);
            }

        }

        for (int i = 0; i < gameModel.getPlayers().size(); i++) {

            // Update all the property indicators on the board
            for (Property property : gameModel.getPlayers().get(i).getProperties()) {

                jPanelList.get(gameModel.getGameboard().getSquares().indexOf(property)).setLayout(new FlowLayout());

                if (property instanceof Street) {

                    if (((Street) property).getBuildableColour(gameModel.getCurrentPlayer(), gameModel.getGameboard()) != null) {
                        // player owns all the properties in the color set

                        build.setEnabled(true);

                    }

                    if (((Street) property).getHouses() == 1) {
                        // player has one house on their street

                        JLabel houseLab = new JLabel("");
                        houseLab.setOpaque(true);
                        houseLab.setPreferredSize(new Dimension(20, 10));
                        houseLab.setBackground(Color.GREEN);

                        jPanelList.get(gameModel.getGameboard().getSquares().indexOf(property)).add(houseLab);

                    } else if (((Street) property).getHouses() == 2) {
                        // player has 2 houses on their street

                        for (int k = 0; k < 2; k++) {

                            JLabel houseLab = new JLabel("");
                            houseLab.setOpaque(true);
                            houseLab.setPreferredSize(new Dimension(20, 10));
                            houseLab.setBackground(Color.GREEN);

                            jPanelList.get(gameModel.getGameboard().getSquares().indexOf(property)).add(houseLab);

                        }

                    } else if (((Street) property).getHouses() == 3) {
                        // player has 3 houses on their street

                        for (int k = 0; k < 3; k++) {

                            JLabel houseLab = new JLabel("");
                            houseLab.setOpaque(true);
                            houseLab.setPreferredSize(new Dimension(20, 10));
                            houseLab.setBackground(Color.GREEN);

                            jPanelList.get(gameModel.getGameboard().getSquares().indexOf(property)).add(houseLab);

                        }

                    } else if (((Street) property).getHouses() == 4) {
                        // player has 4 houses on their street

                        for (int k = 0; k < 4; k++) {

                            JLabel houseLab = new JLabel("");
                            houseLab.setOpaque(true);
                            houseLab.setPreferredSize(new Dimension(20, 10));
                            houseLab.setBackground(Color.GREEN);

                            jPanelList.get(gameModel.getGameboard().getSquares().indexOf(property)).add(houseLab);

                        }

                    } else if (((Street) property).getHouses() == 5) {
                        // player has a hotel (which is technically the same as 5 houses) on their street

                        JLabel houseLab = new JLabel("");
                        houseLab.setOpaque(true);
                        houseLab.setPreferredSize(new Dimension(20, 10));
                        houseLab.setBackground(Color.RED);

                        jPanelList.get(gameModel.getGameboard().getSquares().indexOf(property)).add(houseLab);
                        jPanelList.get(gameModel.getGameboard().getSquares().indexOf(property)).removeMouseListener(gameController);

                    }

                }

                // updating the owners of each property
                JPanel propertyPanel = new JPanel();
                JLabel propertyLabel = new JLabel("Owner: " + property.getOwner().getName());

                propertyLabel.setFont(new Font("Sans Serif", Font.BOLD, 11));
                propertyPanel.add(propertyLabel);
                propertyPanel.setBackground(new Color(255, 255, 255));
                Border blackBorder = BorderFactory.createLineBorder(Color.black);
                propertyPanel.setBorder(blackBorder);

                propertyPanel.setPreferredSize(new Dimension(104, 25));

                // There is an explanation for how this works in handleGameStatusUpdate()
                for (Component c: jPanelList.get(gameModel.getPlayers().get(i).getPosition()).getComponents())
                {
                    if (c instanceof JPanel)
                    {
                        if (((JPanel)c).getLayout() instanceof BorderLayout)
                        {
                            for (Component c2: ((JPanel)c).getComponents())
                            {
                                if (c2 instanceof JPanel)
                                {
                                    ((JPanel)c2).add(propertyPanel, new GridLayout(3, 3));
                                }
                            }
                        }
                    }
                }

                //jPanelList.get(gameModel.getGameboard().getSquares().indexOf(property)).add(propertyPanel);

            }
        }

        if (gameModel.getBuildingState() == GameModel.BuildingState.ALL_HOUSES_BUILT) {
            displayMessage("You have built all four houses. Click again on the streets to upgrade to a hotel!");
        }

        if (gameModel.getBuildingState() == GameModel.BuildingState.ALL_HOTELS_BUILT) {
            displayMessage("You cannot build anymore hotels on these set of streets.");
        }

        gameModel.setBuildingState(GameModel.BuildingState.PLAYER_NOT_BUILDING);

    }

    /**
     * @author Robert Simionescu and Yash Kapoor
     * Updates the GUI to reflect any changes made by the previous action. Also, hides certain buttons depending on the
     * state of the game.
     * @param gameModel      a GameModel Class Object, the model to update the GUI to match.
     */
    @Override
    public void handleGameStatusUpdate(GameModel gameModel) {

        // Clear everything in the UI to prevent duplicate entries from appearing
        for(JPanel panel: jPanelList)
        {
            panel.removeAll();
        }

        jPanelList = new ArrayList<>();

        GamePanelsInitialization.initializeSouthPanels(jPanelList, gameModel.getGameboard());
        GamePanelsInitialization.initializeWestPanels(jPanelList, gameModel.getGameboard());
        GamePanelsInitialization.initializeNorthPanels(jPanelList, gameModel.getGameboard());
        GamePanelsInitialization.initializeEastPanels(jPanelList, gameModel.getGameboard());

        handleBuildingStatusUpdate(gameModel);


        playerPanel.removeAll();


        for (int i = 0; i < gameModel.getPlayers().size(); i++)
        {
            // Update player positions on the gameboard. Only display players that are not bankrupt.
            if (!gameModel.getPlayers().get(i).isBankrupt())
            {
                // player is not bankrupt
                // initializing panels
                simplePlayerPanels[i] = new JPanel();
                playerNames[i] = new JLabel();
                simplePlayerPanels[i].setPreferredSize(new Dimension(40, 25));
                simplePlayerPanels[i].setBackground(new Color(255, 255, 255));
                simplePlayerPanels[i].setEnabled(false);
                Border blackBorder = BorderFactory.createLineBorder(Color.black);
                simplePlayerPanels[i].setBorder(blackBorder);
                simplePlayerPanels[i].add(playerNames[i]);

                fullPlayerPanels[i] = new JPanel(new GridLayout(1, 2));
                fullPlayerPanels[i].setPreferredSize(new Dimension(50, 50));
                fullPlayerPanels[i].setEnabled(false);

                simplePlayerPanels[i].setEnabled(true);
                playerNames[i].setText(gameModel.getPlayers().get(i).getName());
                jPanelList.get(gameModel.getPlayers().get(i).getPosition()).setLayout(new FlowLayout());

                // Player icons are added to a JPanel with a GridLayout located inside a JPanel with a BorderLayout at
                // BorderLayout.CENTER, which is itself placed inside a BorderLayout. It's overly complex, but it works.
                // Swing is gross.
                for (Component c: jPanelList.get(gameModel.getPlayers().get(i).getPosition()).getComponents())
                {
                    if (c instanceof JPanel)
                    {
                        if (((JPanel)c).getLayout() instanceof BorderLayout)
                        {
                            for (Component c2: ((JPanel)c).getComponents())
                            {
                                if (c2 instanceof JPanel)
                                {
                                    ((JPanel)c2).add(simplePlayerPanels[i], new GridLayout(3, 3));
                                }
                            }
                        }
                    }
                }
                // adding players to the GUI gameboard

                fullPlayerPanels[i].removeAll();

                fullPlayerPanels[i].setBackground(new Color(255, 204, 204));

                fullPlayerPanels[i].add(new JLabel("<html>Name: " + gameModel.getPlayers().get(i).getName() + "<br/>Money: $" + gameModel.getPlayers().get(i).getMoney() + "</html>"));

                playerPanel.add(fullPlayerPanels[i]);

            }
            else        // Otherwise, player is bankrupt
            {
                // player bankrupt, returning all houses to the bank
                gameModel.setTotalNumberHouses(gameModel.getTotalNumberHouses() + gameModel.getPlayers().get(i).getTotalNumberHouses());

                // player bankrupt, returning all hotels to the bank
                gameModel.setTotalNumberHotels(gameModel.getTotalNumberHotels() + gameModel.getPlayers().get(i).getTotalNumberHouses());

                fullPlayerPanels[i].removeAll();

                // positioning these panels correctly if the current player needs to be removed
                // since he/she is bankrupt
                fullPlayerPanels[i].setBackground(new Color(255, 204, 204));

                fullPlayerPanels[i].add(new JLabel("<html>Name: " + gameModel.getPlayers().get(i).getName() + "<br/>Money: $" + gameModel.getPlayers().get(i).getMoney() + "</html>"));

                playerPanel.add(fullPlayerPanels[(i + 1) % gameModel.getPlayers().size()]);

                jPanelList.get(gameModel.getPlayers().get((i + 1) % gameModel.getPlayers().size()).getPosition()).add(simplePlayerPanels[(i + 1) % gameModel.getPlayers().size()], new GridLayout(4, 4));

                revalidate();
                repaint();

                // Remove bankrupt players from the gameboard.
                gameModel.removePlayer(gameModel.getPlayers().get(i));
                gameModel.onePlayerRemaining();

                if(gameModel.getGameState() != GameModel.GameState.GAME_OVER) {
                    // if game isn't over, go to the next iteration of the for-loop
                    continue;
                }

            }

            gameboardPanel.removeAll();

            gameboardPanel.add(getSouth(), BorderLayout.SOUTH);
            gameboardPanel.add(getNorth(), BorderLayout.NORTH);
            gameboardPanel.add(getEast(), BorderLayout.EAST);
            gameboardPanel.add(getWest(), BorderLayout.WEST);
            gameboardPanel.add(getCenter(), BorderLayout.CENTER);

            gameboardPanel.revalidate();
            gameboardPanel.repaint();
        }


        if (gameModel.getGameState() == GameModel.GameState.GAME_OVER)
        {
            JOptionPane.showMessageDialog(null, gameModel.getPlayers().get(0).getName() + " wins!");
            addPlayer.setEnabled(false);
            addAIPlayer.setEnabled(false);
            start.setEnabled(false);
            roll.setEnabled(false);
            bp.setEnabled(false);
            jail.setEnabled(false);
            pt.setEnabled(false);
        }
        else if (gameModel.getGameState() == GameModel.GameState.ADDING_PLAYERS)
        {
            // disable all buttons except for add player and start. Enable start only if the minimum number of players
            // has been reached. Enable adding AI players only after a real player has been added.
            start.setEnabled(gameModel.getPlayers().size() >= GameModel.MIN_PLAYERS);
            addPlayer.setEnabled(true);
            if (gameModel.getPlayers().size() == 0)
            {
                addAIPlayer.setEnabled(false);
            }
            else
            {
                addAIPlayer.setEnabled(true);
            }
            roll.setEnabled(false);
            bp.setEnabled(false);
            jail.setEnabled(false);
            pt.setEnabled(false);
        }
        else if ((gameModel.getGameState() == GameModel.GameState.PLAYER_ROLLED_NORMAL
                || gameModel.getGameState() == GameModel.GameState.PLAYER_ROLLED_DOUBLES
                || gameModel.getGameState() == GameModel.GameState.DOUBLES_ROLLED_THRICE
                || gameModel.getGameState() == GameModel.GameState.DOUBLES_ROLLED_IN_JAIL
                ))
        {
            bp.setEnabled(false);
            // disable all buttons except for buy property and pass. Enable buy property only if the property is not owned.
            try {
                if (gameModel.getGameboard().getSquare(gameModel.getCurrentPlayer().getPosition()) instanceof Property) {
                    if (((Property) gameModel.getGameboard().getSquare(gameModel.getCurrentPlayer().getPosition())).getOwner() == null) {
                        bp.setEnabled(true);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                bp.setEnabled(false);
            }


            // player can get out of jail by rolling doubles
            if (gameModel.getGameState() == GameModel.GameState.DOUBLES_ROLLED_IN_JAIL && bp.isEnabled()) {
                displayMessage("You have rolled doubles, so you get out of jail and your turn ends.");
            }

            // player can go to jail by rolling doubles three times in a row
            if (gameModel.getGameState() == GameModel.GameState.DOUBLES_ROLLED_THRICE) {
                roll.setEnabled(false);
                pt.setEnabled(true);
                displayMessage("You have rolled double three times in a row, so you go to jail.");
            }

            // user turn ends if they are in jail
            if (gameModel.getCurrentPlayer().getInJail()) {
                displayMessage("You are in jail, so your turn has ended.");
                displayMessage("Pay $50 to get out of jail or roll doubles once in three turns.");
                jail.setEnabled(true);
                pt.setEnabled(true);
            }

            addPlayer.setEnabled(false);
            addAIPlayer.setEnabled(false);
            start.setEnabled(false);
            roll.setEnabled(false);
            jail.setEnabled(false);
            pt.setEnabled(true);

        }
        else
        {
            // Otherwise, the GameState == PLAYER_ROLLING
            // disable all buttons except for roll

            if (gameModel.getCurrentPlayer().getInJail()) {
                jail.setEnabled(true);
            }
            else {
                jail.setEnabled(false);
                gameModel.setGameState(GameModel.GameState.PLAYER_ROLLING);
            }

            addPlayer.setEnabled(false);
            addAIPlayer.setEnabled(false);
            start.setEnabled(false);
            roll.setEnabled(true);
            bp.setEnabled(false);
            pt.setEnabled(false);
            build.setEnabled(false);
        }

    }

    public static void main(String[] args) {
        new GameFrame();
    }
}
