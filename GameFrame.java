import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/*

INSTRUCTIONS:

5 buttons: add players, start, roll, buy property, pass turn

When you add players, they will appear on the board after you click "start"
Maximum players = 8

Each player appears on the board as a button (1, 2, 3, ..., n)
You can click the individual buttons of the players to view their current state on the board.

After you are done your turn, click "pass turn" to let the next player roll the dice.
Game continues until there is one player remaining and all other players have gone bankrupt.

 */

public class GameFrame extends JFrame implements GameView {

    private final JButton addPlayers;
    private final JButton start;
    private final JButton roll;
    private final JButton bp;
    private final JButton pt;

    private final ArrayList<JLabel> jLabelList;
    private final JPanel bodyPanel;

    private int clicks;


    private final JButton[] buttons;

    private Gameboard gameboard;

    public GameFrame() {
        super("Funopoly");

        GameModel model = new GameModel();
        model.addGameView(this);

        buttons = new JButton[8];

        jLabelList = new ArrayList<>();

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

        bodyPanel = new JPanel(new BorderLayout());

        clicks = 0;

        // Close the JFrame when "x" is pressed
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Game is NOT called Monopoly due to copyright reasons

        getInitialSouthLabels();
        getInitialWestLabels();
        getInitialNorthLabels();
        getInitialEastLabels();

        bodyPanel.add(getNorth(), BorderLayout.NORTH);
        bodyPanel.add(getSouth(), BorderLayout.SOUTH);
        bodyPanel.add(getCenter(), BorderLayout.CENTER);
        bodyPanel.add(getEast(), BorderLayout.EAST);
        bodyPanel.add(getWest(), BorderLayout.WEST);

        setupPlayerButtons();

//        startGame();
//        rollDice();
//        buyPropertySetup();
//
//        passTurn();


        bodyPanel.setPreferredSize(new Dimension(1000, 1000));

        JPanel mainPanel = new JPanel();


        mainPanel.add(bodyPanel);

        this.add(mainPanel);
        this.pack();

        this.setVisible(true);

    }

    private void getInitialSouthLabels() {
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

    private void getInitialWestLabels() {
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

    private void getInitialNorthLabels() {

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

    private void getInitialEastLabels() {

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

    public void displayMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }

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

    private JPanel getCenter() {
        JPanel center = new JPanel();

        JLabel funopoly = new JLabel();
        ImageIcon fun = new ImageIcon("Images/Funopoly.png");
        Image f = fun.getImage().getScaledInstance(600, 800, Image.SCALE_DEFAULT);
        ImageIcon bd = new ImageIcon(f);

        funopoly.setIcon(bd);

        funopoly.setLayout(new FlowLayout());
        funopoly.add(addPlayers);
        funopoly.add(start);
        funopoly.add(roll);
        funopoly.add(bp);
        funopoly.add(pt);

        center.add(funopoly);

        return center;

    }



//    private void startGame() {
//
//        start.setEnabled(false);
//
//        start.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                roll.setEnabled(true);
//                start.setEnabled(false);
//                addPlayers.setEnabled(false);
//
//
//                ActionListener listener = new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if(e.getSource() instanceof JButton) {
//
//                            for(int k = 0; k < buttons.length; k++) {
//
//                                if(buttons[k] == e.getSource()) {
//                                    gl.inspectPlayer(players.get(k).getName(), players);
//                                }
//
//                            }
//
//                        }
//                    }
//                };
//
//                for(int j = 0; j < players.size(); j++) {
//
//                    buttons[j].addActionListener(listener);
//
//                }
//
//                for(int i = 0; i < players.size(); i++) {
//
//
//                    buttons[i].setVisible(true);
//
//
//                    jLabelList.get(0).setLayout(new FlowLayout());
//                    jLabelList.get(0).add(buttons[i], new GridLayout(4, 4));
//
//                    bodyPanel.removeAll();
//
//                    bodyPanel.add(getSouth(), BorderLayout.SOUTH);
//                    bodyPanel.add(getNorth(), BorderLayout.NORTH);
//                    bodyPanel.add(getEast(), BorderLayout.EAST);
//                    bodyPanel.add(getWest(), BorderLayout.WEST);
//                    bodyPanel.add(getCenter(), BorderLayout.CENTER);
//
//                    bodyPanel.revalidate();
//                    bodyPanel.repaint();
//
//                }
//
//                displayMessage("Player " + (currentPlayer + 1) + "'s turn");
//
//
//                roll.setVisible(true);
//
//            }
//        });
//    }
//
//    private void rollDice() {
//
//        roll.setEnabled(false);
//
//        roll.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                StringBuilder sb = new StringBuilder();
//
//                roll.setEnabled(false);
//                bp.setEnabled(true);
//                pt.setEnabled(true);
//
//
//                int rollDie = gl.calculateRoll().get(0);
//                int rollDie2 = gl.calculateRoll().get(1);
//
//
//                sb.append("You rolled a " + rollDie + " and a " + rollDie2);
//
//                int sum = rollDie + rollDie2;
//                if (rollDie != rollDie2)
//                {
//                    sb.append("\nHence, you will move " + sum + " spaces on the gameboard.");
//                }
//                else
//                {
//                    sb.append("\nHence, you will move " + sum + " spaces on the gameboard\nand will get to roll again.");
//                }
//
//                displayMessage(sb.toString());
//
//                int newPosition = (players.get(currentPlayer).getPosition() + sum) % gameboard.getSquares().size();
//
//                players.get(currentPlayer).setPosition(newPosition);
//
//                // getting the square that the user is currently on
//                Square currentPosition = gameboard.getSquare(players.get(currentPlayer).getPosition());
//
//
//                if(currentPosition.getName().equals(jLabelList.get(newPosition).getName())) {
//
//                    jLabelList.get(players.get(currentPlayer).getPosition()).setLayout(new FlowLayout());
//                    jLabelList.get(players.get(currentPlayer).getPosition()).add(buttons[currentPlayer], new GridLayout(4, 4));
//
//
//                    bodyPanel.removeAll();
//
//                    bodyPanel.add(getSouth(), BorderLayout.SOUTH);
//                    bodyPanel.add(getNorth(), BorderLayout.NORTH);
//                    bodyPanel.add(getEast(), BorderLayout.EAST);
//                    bodyPanel.add(getWest(), BorderLayout.WEST);
//                    bodyPanel.add(getCenter(), BorderLayout.CENTER);
//
//                    bodyPanel.revalidate();
//                    bodyPanel.repaint();
//
//                }
//
//                displayMessage("You landed on " + currentPosition.getName());
//
//                if (!gl.playerLandOnSquare(players.get(currentPlayer), currentPosition, gameboard, players)) {
//                    GameFrame.super.dispose();
//                }
//
//                if (rollDie == rollDie2) {
//                    roll.setEnabled(true);
//                    pt.setEnabled(false);
//                    // doubles are rolled, same player goes again
//
//                }
//
//
//            }
//        });
//
//    }

    private void setupPlayerButtons() {

        for(int i = 0; i < buttons.length; i++) {

            JButton button = new JButton();

            int playerNumber = i + 1;

            button.setVisible(false);
            button.setName("" + playerNumber);
            button.setText("" + playerNumber);
            buttons[i] = button;

        }
    }

//    private void buyPropertySetup() {
//
//        bp.setEnabled(false);
//
//        bp.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                gl.buyProperty(players.get(currentPlayer), gameboard);
//
//                bp.setEnabled(false);
//            }
//        });
//
//    }
//
//    private void passTurn() {
//
//        pt.setEnabled(false);
//
//        pt.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                currentPlayer = (currentPlayer + 1) % players.size();
//
//                roll.setEnabled(true);
//                pt.setEnabled(false);
//                bp.setEnabled(false);
//
//                displayMessage("Player " + (currentPlayer + 1) + "'s turn");
//
//            }
//        });
//
//    }


    /**
     * Updates the GUI to reflect any changes made by the previous action. Also, hides certain buttons depending on the
     * state of the game.
     * @param gameModel
     */
    @Override
    public void handleGameStatusUpdate(GameModel gameModel) {
        //todo: update gameboard

        for (Player player: gameModel.getPlayers())
        {
            jLabelList.get(player.getPosition()).add(new JLabel(player.getName()));
        }

        if (gameModel.getGameState() == GameModel.GameState.ADDING_PLAYERS)
        {
            // hide all buttons except for add player and start
            addPlayers.setEnabled(true);
            start.setEnabled(true);
            roll.setEnabled(false);
            bp.setEnabled(false);
            pt.setEnabled(false);
        }
        else if (gameModel.getGameState() == GameModel.GameState.PLAYER_ROLLING)
        {
            // hide all buttons except for roll
            addPlayers.setEnabled(false);
            start.setEnabled(false);
            roll.setEnabled(true);
            bp.setEnabled(false);
            pt.setEnabled(false);
        }
        else if (gameModel.getGameState() == GameModel.GameState.PLAYER_ROLLED_NORMAL
                || gameModel.getGameState() == GameModel.GameState.PLAYER_ROLLED_DOUBLES)
        {
            // hide all buttons except for buy property and pass
            addPlayers.setEnabled(false);
            start.setEnabled(false);
            roll.setEnabled(false);
            bp.setEnabled(true);
            pt.setEnabled(true);

        }
        else if (gameModel.getGameState() == GameModel.GameState.GAME_OVER)
        {
            //todo: handle a player winning
        }
    }

    public static void main(String[] args) {
        GameFrame game = new GameFrame();
    }
}
