/**
 * @author Robert Simionescu and Yash Kapoor
 * @version Milestone 2
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * @author Robert Simionescu and Yash Kapoor
 * Class handling the UI for Monopoly to the user.
 */
public class GameFrame extends JFrame implements GameView {

    private final JButton addPlayers;
    private final JButton start;
    private final JButton roll;
    private final JButton bp;
    private final JButton pt;

    private final ArrayList<JLabel> jLabelList;
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
        messageBox = new JPanel(new GridLayout(3, 1));
        messages = new JLabel[3];
        for (JLabel message : messages)
        {
            message = new JLabel();
            message.setHorizontalAlignment(SwingConstants.CENTER);
            message.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
            messageBox.add(message);
        }

        displayMessage("Welcome to Funopoly! Add at least 2 players to begin.");

        // List of labels used to represent each square
        jLabelList = new ArrayList<>();

        // Buttons used to interface with the game
        addPlayers = new JButton("Add Players");
        start = new JButton("Start");
        roll = new JButton("Roll");
        bp = new JButton("Buy Property");
        pt = new JButton("Pass turn");

        GameController gameController = new GameController(model);

        addPlayers.addActionListener(gameController);
        start.addActionListener(gameController);
        roll.addActionListener(gameController);
        bp.addActionListener(gameController);
        pt.addActionListener(gameController);

        addPlayers.setEnabled(true);
        start.setEnabled(false);
        roll.setEnabled(false);
        bp.setEnabled(false);
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
        initializeSouthLabels();
        initializeWestLabels();
        initializeNorthLabels();
        initializeEastLabels();

        gameboardPanel.add(getNorth(), BorderLayout.NORTH);
        gameboardPanel.add(getSouth(), BorderLayout.SOUTH);
        gameboardPanel.add(getCenter(), BorderLayout.CENTER);
        gameboardPanel.add(getEast(), BorderLayout.EAST);
        gameboardPanel.add(getWest(), BorderLayout.WEST);
        gameboardPanel.setPreferredSize(new Dimension(1000, 1000));

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
     * @author Yash Kapoor
     * Initializes all the labels on the south side of the board with their names and images.
     */
    private void initializeSouthLabels() {
        ImageIcon mediterraneanAvenue = new ImageIcon("Images/MediterraneanAvenue.PNG");
        Image ma = mediterraneanAvenue.getImage().getScaledInstance(157, 140, Image.SCALE_DEFAULT);
        ImageIcon mt = new ImageIcon(ma);

        ImageIcon balticAvenue = new ImageIcon("Images/BalticAvenue.PNG");
        Image ba = balticAvenue.getImage().getScaledInstance(157, 140, Image.SCALE_DEFAULT);
        ImageIcon b = new ImageIcon(ba);

        ImageIcon orientalAvenue = new ImageIcon("Images/OrientalAvenue.PNG");
        Image oa = orientalAvenue.getImage().getScaledInstance(157, 140, Image.SCALE_DEFAULT);
        ImageIcon o = new ImageIcon(oa);

        ImageIcon vermontAvenue = new ImageIcon("Images/VermontAvenue.PNG");
        Image vma = vermontAvenue.getImage().getScaledInstance(157, 140, Image.SCALE_DEFAULT);
        ImageIcon vm = new ImageIcon(vma);

        ImageIcon connecticutAvenue = new ImageIcon("Images/ConnecticutAvenue.PNG");
        Image ca = connecticutAvenue.getImage().getScaledInstance(157, 140, Image.SCALE_DEFAULT);
        ImageIcon c = new ImageIcon(ca);

        ImageIcon startingPoint = new ImageIcon("Images/StartingPoint.PNG");
        Image sp = startingPoint.getImage().getScaledInstance(157, 140, Image.SCALE_DEFAULT);
        ImageIcon s = new ImageIcon(sp);


        JLabel spl = new JLabel();
        spl.setIcon(s);
        spl.setName("Initial Starting Point");
        jLabelList.add(0, spl);

        JLabel mal = new JLabel();
        mal.setIcon(mt);
        mal.setName("Mediterranean Avenue");
        jLabelList.add(1, mal);

        JLabel btal = new JLabel();
        btal.setIcon(b);
        btal.setName("Baltic Avenue");
        jLabelList.add(2, btal);

        JLabel oal = new JLabel();
        oal.setIcon(o);
        oal.setName("Oriental Avenue");
        jLabelList.add(3, oal);

        JLabel vtal = new JLabel();
        vtal.setIcon(vm);
        vtal.setName("Vermont Avenue");
        jLabelList.add(4, vtal);

        JLabel cal = new JLabel();
        cal.setIcon(c);
        cal.setName("Connecticut Avenue");
        jLabelList.add(5, cal);

    }

    /**
     * @author Yash Kapoor
     * Initializes all the labels on the west side of the board with their names and images.
     */
    private void initializeWestLabels() {
        ImageIcon newYorkAvenue = new ImageIcon("Images/NewYorkAvenue.PNG");
        Image nya = newYorkAvenue.getImage().getScaledInstance(140, 117, Image.SCALE_DEFAULT);
        ImageIcon ny = new ImageIcon(nya);

        ImageIcon tennesseeAvenue = new ImageIcon("Images/TennesseeAvenue.PNG");
        Image ta = tennesseeAvenue.getImage().getScaledInstance(140, 117, Image.SCALE_DEFAULT);
        ImageIcon t = new ImageIcon(ta);

        ImageIcon stJamesPlace = new ImageIcon("Images/StJamesPlace.PNG");
        Image sjp = stJamesPlace.getImage().getScaledInstance(140, 117, Image.SCALE_DEFAULT);
        ImageIcon sj = new ImageIcon(sjp);

        ImageIcon virginiaAvenue = new ImageIcon("Images/VirginiaAvenue.PNG");
        Image vga = virginiaAvenue.getImage().getScaledInstance(140, 117, Image.SCALE_DEFAULT);
        ImageIcon vg = new ImageIcon(vga);

        ImageIcon statesAvenue = new ImageIcon("Images/StatesAvenue.PNG");
        Image sta = statesAvenue.getImage().getScaledInstance(140, 117, Image.SCALE_DEFAULT);
        ImageIcon st = new ImageIcon(sta);

        ImageIcon stCharlesPlace = new ImageIcon("Images/StCharlesPlace.PNG");
        Image scp = stCharlesPlace.getImage().getScaledInstance(140, 117, Image.SCALE_DEFAULT);
        ImageIcon sc = new ImageIcon(scp);


        JLabel scpl = new JLabel();
        scpl.setIcon(sc);
        scpl.setName("St. Charles Place");
        jLabelList.add(6, scpl);

        JLabel sal = new JLabel();
        sal.setIcon(st);
        sal.setName("States Avenue");
        jLabelList.add(7, sal);

        JLabel vgal = new JLabel();
        vgal.setIcon(vg);
        vgal.setName("Virginia Avenue");
        jLabelList.add(8, vgal);


        JLabel sjpl = new JLabel();
        sjpl.setIcon(sj);
        sjpl.setName("St. James Place");
        jLabelList.add(9, sjpl);


        JLabel tal = new JLabel();
        tal.setIcon(t);
        tal.setName("Tennessee Avenue");
        jLabelList.add(10, tal);

        JLabel nyl = new JLabel();
        nyl.setIcon(ny);
        nyl.setName("New York Avenue");
        jLabelList.add(11, nyl);

    }

    /**
     * @author Yash Kapoor
     * Initializes all the labels on the north side of the board with their names and images.
     */
    private void initializeNorthLabels() {

        ImageIcon kentuckyAvenue = new ImageIcon("Images/KentuckyAvenue.PNG");
        Image ka = kentuckyAvenue.getImage().getScaledInstance(157, 140, Image.SCALE_DEFAULT);
        ImageIcon k = new ImageIcon(ka);

        ImageIcon indianaAvenue = new ImageIcon("Images/IndianaAvenue.PNG");
        Image ina = indianaAvenue.getImage().getScaledInstance(157, 140, Image.SCALE_DEFAULT);
        ImageIcon in = new ImageIcon(ina);

        ImageIcon illinoisAvenue = new ImageIcon("Images/IllinoisAvenue.PNG");
        Image ia = illinoisAvenue.getImage().getScaledInstance(157, 140, Image.SCALE_DEFAULT);
        ImageIcon i = new ImageIcon(ia);

        ImageIcon atlanticAvenue = new ImageIcon("Images/AtlanticAvenue.PNG");
        Image aa = atlanticAvenue.getImage().getScaledInstance(157, 140, Image.SCALE_DEFAULT);
        ImageIcon a = new ImageIcon(aa);

        ImageIcon ventnorAvenue = new ImageIcon("Images/VentnorAvenue.PNG");
        Image va = ventnorAvenue.getImage().getScaledInstance(157, 140, Image.SCALE_DEFAULT);
        ImageIcon v = new ImageIcon(va);

        ImageIcon marvinGardens = new ImageIcon("Images/MarvinGardens.PNG");
        Image mg = marvinGardens.getImage().getScaledInstance(157, 140, Image.SCALE_DEFAULT);
        ImageIcon m = new ImageIcon(mg);


        JLabel kal = new JLabel();
        kal.setIcon(k);
        kal.setName("Kentucky Avenue");
        jLabelList.add(12, kal);

        JLabel inal = new JLabel();
        inal.setIcon(in);
        inal.setName("Indiana Avenue");
        jLabelList.add(13, inal);

        JLabel ial = new JLabel();
        ial.setIcon(i);
        ial.setName("Illinois Avenue");
        jLabelList.add(14, ial);

        JLabel aal = new JLabel();
        aal.setIcon(a);
        aal.setName("Atlantic Avenue");
        jLabelList.add(15, aal);

        JLabel val = new JLabel();
        val.setIcon(v);
        val.setName("Ventnor Avenue");
        jLabelList.add(16, val);

        JLabel mgl = new JLabel();
        mgl.setIcon(m);
        mgl.setName("Marvin Gardens");
        jLabelList.add(17, mgl);

    }

    /**
     * @author Yash Kapoor
     * Initializes all the labels on the east side of the board with their names and images.
     */
    private void initializeEastLabels() {

        ImageIcon pacificAvenue = new ImageIcon("Images/PacificAvenue.PNG");
        Image pa = pacificAvenue.getImage().getScaledInstance(140, 141, Image.SCALE_DEFAULT);
        ImageIcon p = new ImageIcon(pa);

        ImageIcon northCarolinaAvenue = new ImageIcon("Images/NorthCarolinaAvenue.PNG");
        Image nca = northCarolinaAvenue.getImage().getScaledInstance(140, 141, Image.SCALE_DEFAULT);
        ImageIcon nc = new ImageIcon(nca);

        ImageIcon pennsylvaniaAvenue = new ImageIcon("Images/PennsylvaniaAvenue.PNG");
        Image pna = pennsylvaniaAvenue.getImage().getScaledInstance(140, 141, Image.SCALE_DEFAULT);
        ImageIcon pn = new ImageIcon(pna);

        ImageIcon parkPlace = new ImageIcon("Images/ParkPlace.PNG");
        Image park = parkPlace.getImage().getScaledInstance(140, 141, Image.SCALE_DEFAULT);
        ImageIcon pp = new ImageIcon(park);

        ImageIcon boardWalk = new ImageIcon("Images/BoardWalk.PNG");
        Image bdw = boardWalk.getImage().getScaledInstance(140, 141, Image.SCALE_DEFAULT);
        ImageIcon bd = new ImageIcon(bdw);


        JLabel pal = new JLabel();
        pal.setIcon(p);
        pal.setName("Pacific Avenue");
        jLabelList.add(18, pal);

        JLabel ncal = new JLabel();
        ncal.setIcon(nc);
        ncal.setName("North Carolina Avenue");
        jLabelList.add(19, ncal);

        JLabel plal = new JLabel();
        plal.setIcon(pn);
        plal.setName("Pennsylvania Avenue");
        jLabelList.add(20, plal);

        JLabel ppl = new JLabel();
        ppl.setIcon(pp);
        ppl.setName("Park Place");
        jLabelList.add(21, ppl);

        JLabel bwl = new JLabel();
        bwl.setIcon(bd);
        bwl.setName("Board Walk");
        jLabelList.add(22, bwl);

    }

    /**
     * @author Robert Simionescu
     * Displays a message on the bottom of the message field. Shifts the existing messages up and removes the oldest one.
     * @param message The message that will be displayed to the message board.
     */
    public void displayMessage(String message)
    {
        ((JLabel)messageBox.getComponent(0)).setText(((JLabel)messageBox.getComponent(1)).getText());
        ((JLabel)messageBox.getComponent(1)).setText(((JLabel)messageBox.getComponent(2)).getText());
        ((JLabel)messageBox.getComponent(2)).setText(message);
    }

    /**
     * @author Yash Kapoor
     * Getter for the squares on the south side of the board.
     * @return A JPanel with all the labels for the south side of the board.
     */
    private JPanel getSouth() {

        JPanel south = new JPanel();

        JPanel labelPanel = new JPanel(new BorderLayout());
        BoxLayout boxlayout = new BoxLayout(labelPanel, BoxLayout.X_AXIS);
        labelPanel.setLayout(boxlayout);

        labelPanel.add(jLabelList.get(5));
        labelPanel.add(jLabelList.get(4));
        labelPanel.add(jLabelList.get(3));
        labelPanel.add(jLabelList.get(2));
        labelPanel.add(jLabelList.get(1));
        labelPanel.add(jLabelList.get(0));

        south.setLayout(new GridBagLayout());
        south.add(labelPanel);

        south.setBackground(new Color (187, 255, 202));
        south.setPreferredSize(new Dimension(500, 145));

        return south;
    }

    /**
     * @author Yash Kapoor
     * Getter for the squares on the west side of the board.
     * @return A JPanel with all the labels for the west side of the board.
     */
    private JPanel getWest() {

        JPanel west = new JPanel();

        JPanel labelPanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(labelPanel, BoxLayout.Y_AXIS);
        labelPanel.setLayout(boxlayout);

        labelPanel.add(jLabelList.get(11));
        labelPanel.add(jLabelList.get(10));
        labelPanel.add(jLabelList.get(9));
        labelPanel.add(jLabelList.get(8));
        labelPanel.add(jLabelList.get(7));
        labelPanel.add(jLabelList.get(6));

        west.setLayout(new GridBagLayout());
        west.add(labelPanel);
        west.setBackground(new Color (187, 255, 202));

        west.setPreferredSize(new Dimension(200, 200));

        return west;
    }

    /**
     * @author Yash Kapoor
     * Getter for the squares on the north side of the board.
     * @return A JPanel with all the labels for the north side of the board.
     */
    private JPanel getNorth() {

        JPanel north = new JPanel();

        JPanel labelPanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(labelPanel, BoxLayout.X_AXIS);
        labelPanel.setLayout(boxlayout);

        labelPanel.add(jLabelList.get(12));

        labelPanel.add(jLabelList.get(13));

        labelPanel.add(jLabelList.get(14));
        labelPanel.add(jLabelList.get(15));

        labelPanel.add(jLabelList.get(16));
        labelPanel.add(jLabelList.get(17));

        north.setLayout(new GridBagLayout());
        north.add(labelPanel);

        north.setBackground(new Color (187, 255, 202));
        north.setPreferredSize(new Dimension(500, 145));

        return north;
    }

    /**
     * @author Yash Kapoor
     * Getter for the squares on the east side of the board.
     * @return A JPanel with all the labels for the east side of the board.
     */
    private JPanel getEast() {

        JPanel east = new JPanel();

        JPanel labelPanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(labelPanel, BoxLayout.Y_AXIS);
        labelPanel.setLayout(boxlayout);

        labelPanel.add(jLabelList.get(18));
        labelPanel.add(jLabelList.get(19));

        labelPanel.add(jLabelList.get(20));
        labelPanel.add(jLabelList.get(21));

        labelPanel.add(jLabelList.get(22));

        east.setLayout(new GridBagLayout());
        east.add(labelPanel);

        east.setBackground(new Color (187, 255, 202));
        east.setPreferredSize(new Dimension(200, 200));

        return east;
    }

    /**
     * @author Robert Simionescu and Yash Kapoor
     * Getter for center of the board, containing the funopoly logo, the buttons used to interface with the game, and
     * the message board.
     * @return A JPanel with all the labels for the south side of the board.
     */
    private JPanel getCenter() {
        JPanel center = new JPanel(new BorderLayout());


        JLabel funopoly = new JLabel();
        ImageIcon fun = new ImageIcon("Images/Funopoly.png");
        Image f = fun.getImage().getScaledInstance(600, 800, Image.SCALE_DEFAULT);
        ImageIcon bd = new ImageIcon(f);

        funopoly.setIcon(bd);
        center.add(funopoly, BorderLayout.CENTER);


        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(addPlayers);
        buttons.add(start);
        buttons.add(roll);
        buttons.add(bp);
        buttons.add(pt);

        buttons.setBackground(new Color (187, 255, 202));

        center.add(buttons, BorderLayout.PAGE_START);

        center.add(messageBox, BorderLayout.PAGE_END);


        return center;

    }

    /**
     * @author Robert Simionescu and Yash Kapoor
     * Updates the GUI to reflect any changes made by the previous action. Also, hides certain buttons depending on the
     * state of the game.
     * @param gameModel The model to update the GUI to match.
     */
    @Override
    public void handleGameStatusUpdate(GameModel gameModel) {

        // Clear everything in the UI to prevent duplicate entries from appearing
        for(JLabel label: jLabelList)
        {
            label.removeAll();
        }
        playerPanel.removeAll();

        for (int i = 0; i < gameModel.getPlayers().size(); i++)
        {
            // Update player positions on the gameboard. Only display players that are not bankrupt.
            if (!gameModel.getPlayers().get(i).isBankrupt())
            {
                // initializing panels
                simplePlayerPanels[i] = new JPanel();
                playerNames[i] = new JLabel();
                simplePlayerPanels[i].setPreferredSize(new Dimension(52, 25));
                simplePlayerPanels[i].setEnabled(false);
                simplePlayerPanels[i].add(playerNames[i]);

                fullPlayerPanels[i] = new JPanel(new GridLayout(1, 2));
                fullPlayerPanels[i].setPreferredSize(new Dimension(50, 50));
                fullPlayerPanels[i].setEnabled(false);

                // Update all the property indicators on the board
                for (Property property : gameModel.getPlayers().get(i).getProperties())
                {
                    JPanel propertyPanel = new JPanel();
                    JLabel propertyLabel = new JLabel("Owner: " + property.getOwner().getName());

                    propertyLabel.setFont(new Font("Sans Serif", Font.BOLD, 11));
                    propertyPanel.add(propertyLabel);

                    propertyPanel.setPreferredSize(new Dimension(104, 25));
                    jLabelList.get(gameModel.getGameboard().getSquares().indexOf(property)).setLayout(new FlowLayout());
                    jLabelList.get(gameModel.getGameboard().getSquares().indexOf(property)).add(propertyPanel, new GridLayout(4, 4));
                }

                simplePlayerPanels[i].setEnabled(true);
                playerNames[i].setText(gameModel.getPlayers().get(i).getName());
                jLabelList.get(gameModel.getPlayers().get(i).getPosition()).setLayout(new FlowLayout());
                jLabelList.get(gameModel.getPlayers().get(i).getPosition()).add(simplePlayerPanels[i], new GridLayout(4, 4));

                fullPlayerPanels[i].removeAll();

                fullPlayerPanels[i].setBackground(new Color(255, 204, 204));

                fullPlayerPanels[i].add(new JLabel("<html>Name: " + gameModel.getPlayers().get(i).getName() + "<br/>Money: $" + gameModel.getPlayers().get(i).getMoney() + "</html>"));

                playerPanel.add(fullPlayerPanels[i]);

            }
            else        // Otherwise, player is bankrupt
            {
                fullPlayerPanels[i].removeAll();

                // positioning these panels correctly if the current player needs to be removed
                // since he/she is bankrupt
                fullPlayerPanels[i].setBackground(new Color(255, 204, 204));

                fullPlayerPanels[i].add(new JLabel("<html>Name: " + gameModel.getPlayers().get(i).getName() + "<br/>Money: $" + gameModel.getPlayers().get(i).getMoney() + "</html>"));

                playerPanel.add(fullPlayerPanels[(i + 1) % gameModel.getPlayers().size()]);

                jLabelList.get(gameModel.getPlayers().get((i + 1) % gameModel.getPlayers().size()).getPosition()).add(simplePlayerPanels[(i + 1) % gameModel.getPlayers().size()], new GridLayout(4, 4));

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
            addPlayers.setEnabled(false);
            start.setEnabled(false);
            roll.setEnabled(false);
            bp.setEnabled(false);
            pt.setEnabled(false);
        }
        else if (gameModel.getGameState() == GameModel.GameState.ADDING_PLAYERS)
        {
            // disable all buttons except for add player and start. Enable start only if the minimum number of players has been reached.
            start.setEnabled(gameModel.getPlayers().size() >= GameModel.MIN_PLAYERS);
            addPlayers.setEnabled(true);
            roll.setEnabled(false);
            bp.setEnabled(false);
            pt.setEnabled(false);
        }
        else if (gameModel.getGameState() == GameModel.GameState.PLAYER_ROLLED_NORMAL
                || gameModel.getGameState() == GameModel.GameState.PLAYER_ROLLED_DOUBLES)
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

            addPlayers.setEnabled(false);
            start.setEnabled(false);
            roll.setEnabled(false);
            pt.setEnabled(true);

        }
        else
        {
            // Otherwise, the GameState == PLAYER_ROLLING
            // disable all buttons except for roll
            addPlayers.setEnabled(false);
            start.setEnabled(false);
            roll.setEnabled(true);
            bp.setEnabled(false);
            pt.setEnabled(false);
        }

    }

    public static void main(String[] args) {
        new GameFrame();
    }
}
