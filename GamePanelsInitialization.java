import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Robert Simionescu
 * Initializes all the labels for the squares on the gameboard
 */
public class GamePanelsInitialization implements Serializable
{
    public static final int SOUTH_SQUARES = 8;
    public static final int WEST_SQUARES = 8;
    public static final int NORTH_SQUARES = 9;
    public static final int EAST_SQUARES = 6;

    public static final String BROWN = "Brown";
    public static final String BLUE = "Blue";
    public static final String PINK = "Pink";
    public static final String ORANGE = "Orange";
    public static final String RED = "Red";
    public static final String YELLOW = "Yellow";
    public static final String GREEN = "Green";
    public static final String DARK_BLUE = "Dark Blue";

    private static JPanel createPanel(int i, Gameboard gameboard)
    {
        // Swing is gross and awful and disgusting and I hope I never have to deal with it again in my life.
        Square square = gameboard.getSquare(i);

        String name = square.getName();
        JPanel squarePanel = new JPanel();
        squarePanel.setName(name);
        squarePanel.setPreferredSize(new Dimension(200, 200));
        Border blackBorder = BorderFactory.createLineBorder(Color.black);
        squarePanel.setBorder(blackBorder);
        squarePanel.setLayout(new BorderLayout());

        if (square instanceof Street)
        {
            JPanel colourPanel = new JPanel();
            colourPanel.setBorder(blackBorder);
            switch (((Street) gameboard.getSquare(i)).getColour()) {
                case BROWN -> colourPanel.setBackground(new Color(104, 55, 0));
                case BLUE -> colourPanel.setBackground(new Color(0, 0, 255));
                case PINK -> colourPanel.setBackground(new Color(255, 0, 190));
                case ORANGE -> colourPanel.setBackground(new Color(255, 75, 0));
                case RED -> colourPanel.setBackground(new Color(255, 0, 0));
                case YELLOW -> colourPanel.setBackground(new Color(255, 255, 0));
                case GREEN -> colourPanel.setBackground(new Color(0, 255, 0));
                case DARK_BLUE -> colourPanel.setBackground(new Color(0, 0, 150));
            }
            colourPanel.setPreferredSize(new Dimension(300, 30));
            squarePanel.add(colourPanel, BorderLayout.PAGE_START);

            // This awful looking garbage allows for player icons to be displayed over top of the squares by adding the
            // icon to the GridLayout.

            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new BorderLayout());
            centerPanel.add(new JLabel("<html><body style='text-align: center'>"+ name+"</html>", SwingConstants.CENTER), BorderLayout.PAGE_START);
            centerPanel.add(new JLabel("<html><body style='text-align: center'>$"+ ((Street)square).getCost() +"</html>", SwingConstants.CENTER), BorderLayout.PAGE_END);
            JPanel playerPanel = new JPanel(new GridLayout(3, 3));
            centerPanel.add(playerPanel, BorderLayout.CENTER);
            playerPanel.setBackground(new Color(187, 255, 202));
            centerPanel.setBackground(new Color(187, 255, 202));

            squarePanel.add(centerPanel, BorderLayout.CENTER);

            //squarePanel.add(new JLabel("<html><body style='text-align: center'>"+ name+"</html>", SwingConstants.CENTER), BorderLayout.CENTER);
            //squarePanel.add(new JLabel("<html><body style='text-align: center'>$"+ ((Street)square).getCost() +"</html>", SwingConstants.CENTER), BorderLayout.PAGE_END);
        }
        else if (square instanceof Railroad)
        {
            // Blank panel added so that the names of non-streets align with those of streets.
            JPanel blankPanel = new JPanel();
            blankPanel.setPreferredSize(new Dimension(100, 25));
            blankPanel.setBackground(new Color(187, 255, 202));
            squarePanel.add(blankPanel, BorderLayout.PAGE_START);

            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new BorderLayout());
            centerPanel.add(new JLabel("<html><body style='text-align: center'>"+ name+"</html>", SwingConstants.CENTER), BorderLayout.PAGE_START);
            centerPanel.add(new JLabel("<html><body style='text-align: center'>$"+ ((Railroad)square).getCost() +"</html>", SwingConstants.CENTER), BorderLayout.PAGE_END);
            JPanel playerPanel = new JPanel(new GridLayout(3, 3));
            centerPanel.add(playerPanel, BorderLayout.CENTER);
            playerPanel.setBackground(new Color(187, 255, 202));
            centerPanel.setBackground(new Color(187, 255, 202));

            squarePanel.add(centerPanel, BorderLayout.CENTER);

            //squarePanel.add(new JLabel("<html><body style='text-align: center'>"+ name+"</html>", SwingConstants.CENTER), BorderLayout.CENTER);
            //squarePanel.add(new JLabel("<html><body style='text-align: center'>$"+ ((Railroad)square).getCost() +"</html>", SwingConstants.CENTER), BorderLayout.PAGE_END);
        }
        else if (square instanceof Utility)
        {
            // Blank panel added so that the names of non-streets align with those of streets.
            JPanel blankPanel = new JPanel();
            blankPanel.setPreferredSize(new Dimension(100, 25));
            blankPanel.setBackground(new Color(187, 255, 202));
            squarePanel.add(blankPanel, BorderLayout.PAGE_START);

            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new BorderLayout());
            centerPanel.add(new JLabel("<html><body style='text-align: center'>"+ name+"</html>", SwingConstants.CENTER), BorderLayout.PAGE_START);
            centerPanel.add(new JLabel("<html><body style='text-align: center'>$"+ ((Utility)square).getCost() +"</html>", SwingConstants.CENTER), BorderLayout.PAGE_END);
            JPanel playerPanel = new JPanel(new GridLayout(3, 3));
            centerPanel.add(playerPanel, BorderLayout.CENTER);
            playerPanel.setBackground(new Color(187, 255, 202));
            centerPanel.setBackground(new Color(187, 255, 202));

            squarePanel.add(centerPanel, BorderLayout.CENTER);

            //squarePanel.add(new JLabel("<html><body style='text-align: center'>"+ name+"</html", SwingConstants.CENTER), BorderLayout.CENTER);
            //squarePanel.add(new JLabel("<html><body style='text-align: center'>$"+ ((Utility)square).getCost() +"</html", SwingConstants.CENTER), BorderLayout.PAGE_END);
        }
        else
        {
            // Blank panel added so that the names of non-streets align with those of streets.
            JPanel blankPanel = new JPanel();
            blankPanel.setPreferredSize(new Dimension(100, 25));
            blankPanel.setBackground(new Color(187, 255, 202));
            squarePanel.add(blankPanel, BorderLayout.PAGE_START);

            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new BorderLayout());
            centerPanel.add(new JLabel("<html><body style='text-align: center'>"+ name+"</html>", SwingConstants.CENTER), BorderLayout.PAGE_START);
            JPanel playerPanel = new JPanel(new GridLayout(3, 3));
            centerPanel.add(playerPanel, BorderLayout.CENTER);
            playerPanel.setBackground(new Color(187, 255, 202));
            centerPanel.setBackground(new Color(187, 255, 202));

            squarePanel.add(centerPanel, BorderLayout.CENTER);
            // Blank label added for the same reason.
            //squarePanel.add(new JLabel("<html><body style='text-align: center'>"+ name+"</html", SwingConstants.CENTER), BorderLayout.PAGE_START);
            squarePanel.add(new JLabel(" "), BorderLayout.PAGE_END);
        }


        squarePanel.setVisible(true);
        squarePanel.setBackground(new Color(187, 255, 202));
        return squarePanel;
    }

    /**
     * @author Yash Kapoor
     * Initializes all the labels on the south side of the board with their names and images.
     */
    public static void initializeSouthPanels(ArrayList<JPanel> jPanelList, Gameboard gameboard) {
        for (int i = 0; i < SOUTH_SQUARES; i++)
        {
            jPanelList.add(i, createPanel(i, gameboard));
        }
    }

    /**
     * @author Yash Kapoor
     * Initializes all the labels on the west side of the board with their names and images.
     */
    public static void initializeWestPanels(ArrayList<JPanel> jPanelList, Gameboard gameboard) {
        for (int i = SOUTH_SQUARES; i < SOUTH_SQUARES + WEST_SQUARES; i++)
        {
            jPanelList.add(i, createPanel(i, gameboard));
        }
    }

    /**
     * @author Yash Kapoor
     * Initializes all the labels on the north side of the board with their names and images.
     */
    public static void initializeNorthPanels(ArrayList<JPanel> jPanelList, Gameboard gameboard) {

        for (int i = SOUTH_SQUARES + WEST_SQUARES; i < SOUTH_SQUARES + WEST_SQUARES + NORTH_SQUARES; i++)
        {
            jPanelList.add(i, createPanel(i, gameboard));
        }
    }

    /**
     * @author Yash Kapoor
     * Initializes all the labels on the east side of the board with their names and images.
     */
    public static void initializeEastPanels(ArrayList<JPanel> jPanelList, Gameboard gameboard) {

        for (int i = SOUTH_SQUARES + WEST_SQUARES + NORTH_SQUARES; i < SOUTH_SQUARES + WEST_SQUARES + NORTH_SQUARES + EAST_SQUARES; i++)
        {
            jPanelList.add(i, createPanel(i, gameboard));
        }
    }
}
