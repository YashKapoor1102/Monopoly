/**
 * @author Yash Kapoor (Student ID: 101163338)
 * @version Milestone 1
 */

import java.util.*;

/**
 * GameUI class creates the User Interface for this game.
 * Displays all the necessary messages and commands for the user to play the game.
 */
public class GameUI {

    private Gameboard gameboard;
    private final List<Property> PROPERTY;

    /**
     * Constructor for GameUI that initializes
     * a gameboard and a PROPERTY ArrayList
     */
    public GameUI() {
        this.gameboard = null;
        this.PROPERTY = new ArrayList<>();
    }

    /**
     * Getting the gameboard to add squares to it
     *
     * @return      Gameboard object, the entire gameboard consisting of many individual squares
     */
    public Gameboard getGameboard() {

        return this.gameboard;

    }

    /**
     * Allows the user to enter the number of players that are going to be playing the game
     * Adds each player to the ArrayList of players.
     *
     * @param player    an ArrayList, list of players
     * @return          an ArrayList, list of players
     */
    public ArrayList<Player> displayPlayerInput(ArrayList<Player> player) {
        Scanner numOfPlayers = new Scanner(System.in);

        int num = 0;

        boolean noError = false;

        do {
            // do-while accounts for errors (if user enters a wrong command)
            System.out.println("Enter how many players you want to play this game: ");
            if (numOfPlayers.hasNextInt()) {
                num = numOfPlayers.nextInt();
                noError = true;
                if (num <= 1) {
                    System.out.println("\nYou must enter a valid positive integer. Try again!");
                    System.out.println("This game must be played with two or more players.\n");
                    noError = false;
                }
            } else {
                numOfPlayers.nextLine();
                System.out.println("\nYou must enter a valid positive integer. Try again!\n");
            }
        } while (!noError);

        Scanner name = new Scanner(System.in);

        for (int n = 0; n < num; n++) {
            // goes through number of players and asks them their name
            System.out.println("\nWhat is Player " + (n + 1) + "'s name? ");
            String n1 = name.nextLine();

            boolean duplicate = false;
            for(int i = 0; i < player.toArray().length; i++)
            // ensuring there are no duplicate names in the list of players
            {
                String playerName = player.get(i).getName();
                if(playerName.equals(n1)) {
                    // name for each player must be different
                    duplicate = true;
                    System.out.println("\nPlease enter a different name! No duplicates!");
                    break;
                }
            }

            if(!duplicate) {

                player.add(new Player(n1, 100));

            }
            else {
                n--;
            }

        }
        return player;
    }

    /**
     * Creating the gameboard by adding squares to it.
     * Those squares can represent properties (e.g., streets) and the initial starting point.
     * Jail, GO, and Utilities will be added in later milestones.
     *
     * @return      an ArrayList, representing all the squares on the gameboard
     */
    public ArrayList<Square> createBoard() {
        ArrayList<Square> s = new ArrayList<>();

        // adding all squares to the ArrayList
        Collections.addAll(s, new Square() {

            /**
             * Getting the name of the square
             *
             * @return      a String, the name of the square
             */
            @Override
            public String getName() {
                return "Initial Starting Point";
            }

            /**
             * Setting the property that exists on the square (if any)
             * Null if there is no property on that specific square.
             *
             * This will be modified slightly in later milestones when we have to account for more than
             * one Streets on the gameboard.
             *
             * @return      a Street Object, the street that is present on the square
             */
            @Override
            public Street setProperty() {
                return null;
            }

            /**
             * Shows the user a text representation of what square they are currently on.
             *
             * @return      a String, the name of the square
             */
            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {
            @Override
            public String getName() {
                return "Mediterranean Avenue";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Brown",60);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "Baltic Avenue";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Brown",60);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "Oriental Avenue";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Blue",100);
                PROPERTY.add(s);
                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "Vermont Avenue";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Blue",100);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "Connecticut Avenue";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Blue",120);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "St. Charles Place";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Pink",140);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "States Avenue";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Pink",140);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "Virginia Avenue";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Pink",160);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "St. James Place";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Orange",180);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "Tennessee Avenue";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Orange",180);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "New York Avenue";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Orange",200);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "Kentucky Avenue";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Red",220);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "Indiana Avenue";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Red",220);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "Illinois Avenue";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Red",240);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "Atlantic Avenue";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Yellow",260);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "Ventnor Avenue";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Yellow",260);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "Marvin Gardens";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Yellow",280);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "Pacific Avenue";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Green",300);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "North Carolina Avenue";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Green",300);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "Pennsylvania Avenue";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Green",320);
                PROPERTY.add(s);

                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "Park Place";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Dark Blue",350);
                PROPERTY.add(s);
                return s;

            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        }, new Square() {

            @Override
            public String getName() {
                return "Board Walk";
            }

            @Override
            public Street setProperty() {
                Street s = new Street(getName(), "Dark Blue",400);
                PROPERTY.add(s);
                return s;
            }

            @Override
            public String toString() {
                return String.format("%s", getName());
            }

        });

        return s;
    }

    /**
     * Printing all the squares on the gameboard
     * to show the user what the entire gameboard looks like.
     */
    public void printBoard() {

        this.gameboard = new Gameboard(createBoard());

        System.out.println("\nEntire gameboard:\n");
        for(int square = 0; square < gameboard.getSquares().size(); square++) {
            System.out.println(gameboard.getSquares().get(square));
        }
    }

    /**
     * Displaying all available commands the game has to offer after the user rolls their dice.
     *
     * @param player    an ArrayList of players, list of the players that are currently playing the game
     * @param rd        an int, first dice roll
     * @param rd2       an int, second dice roll
     * @param index     an int, index of the player in the ArrayList of players
     * @param propertyBlock     a Square Object, the current position of the player on the gameboard
     * @param ui                a GameUI object, used to get the current gameboard that the player is playing on
     */
    public void displayCommands(ArrayList<Player> player, int rd, int rd2, int index, Square propertyBlock, GameUI ui) {

        displayPayment(player, propertyBlock, index);
        while (true) {
            // keep prompting user to enter a command until they choose to
            // pass their turn

            Scanner command = new Scanner(System.in);

            System.out.println("\nEnter a command (Type \"help\" for the list of commands): ");

            String c = command.next();

            if (c.equals("help")) {
                // all available commands

                System.out.println("\nList of Commands");
                System.out.println("------------------");
                System.out.println("State of Player: Type SP");
                System.out.println("Buy Property: Type BP");
                System.out.println("Pass your turn: Type PT");
                System.out.println("Quit: Type Q");
            } else if (c.equals("SP")) {
                /*
                player can view the state of any player by typing their name
                state displays where they are on the gameboard, how much money they have,
                and the list of properties that they currently own.
                */

                Scanner choosePlayer = new Scanner(System.in);
                System.out.println("Which player would you like to view the state of? Please type their name: ");
                String cp = choosePlayer.next();

                boolean playerName = false;
                for (int q = 0; q < player.size(); q++) {
                    if (cp.equals(player.get(q).getName())) {
                        // entered player matches the one found by the loop
                        // display their state

                        System.out.println(player.get(q).getName() + " has $" + player.get(q).getMoney());

                        try {
                            System.out.println(player.get(q).getName() + " is currently on " + ui.getGameboard().getSquare(player.get(q).getPosition().get(player.get(q).getPosition().size() - 1)));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(player.get(q).getName() + " is currently on " + ui.getGameboard().getSquare(0));
                        }
                        System.out.println(player.get(q).getName() + " currently owns " + player.get(q).getProperties().toString());
                        // [] (Empty List) indicates player owns no properties

                        playerName = true;
                    }
                }
                if (!playerName) {
                    System.out.println("Sorry! This name does not exist in the list of players.");
                }
            } else if (c.equals("BP")) {
                /*
                player can buy properties that they land on
                 */

                for (int j = 0; j < ui.createBoard().size(); j++) {
                    // player now owns the PROPERTY
                    if (propertyBlock.getName().equals(ui.createBoard().get(j).getName())) {
                        for (int z = 0; z < player.size(); z++) {
                            if (!player.get(index).equals(player.get(z))) {
                                // if current player does not equal other players in the game
                                // scan all properties of other players to see if they own that PROPERTY
                                if (player.get(z).getProperties().toString().contains(propertyBlock.getName())) {
                                    System.out.println("\nOops! Somebody already owns this PROPERTY! Try again!\n");
                                    break;
                                }
                                try {
                                    if (player.get(index).addProperty(ui.createBoard().get(j).setProperty()) == -1) {
                                        // adding property to player's list of properties
                                        // -1 if property is already in the list, so cannot add

                                        System.out.println("\nOops, you already own this PROPERTY!\n");
                                    }
                                    else {
                                        System.out.println("\n" + propertyBlock.getName() + " has been added to your list of properties!\n");
                                    }
                                    break;
                                } catch (NullPointerException e) {
                                    // if property == null, then the player must be on the initial starting point.
                                    System.out.println("You are not at a PROPERTY. You are at the initial starting point!");
                                }
                            }
                        }
                        break;
                    }
                }
            } else if (c.equals("PT")) {
                // player can pass their turn to the next player

                if (rd == rd2) {
                    // current player rolled doubles
                    System.out.println("Since you rolled doubles, you get to go again!");
                    return;

                } else {
                    System.out.println("You have successfully completed your turn!\n");
                    break;
                }

            } else if (c.equals("Q")) {
                // player can quit the game

                System.out.println("\nThank you for playing!");
                System.exit(0);
            } else {
                System.out.println("You must enter a valid command! Try again!");
            }
        }

    }

    /**
     * Displays the first and second dice roll of the player
     * and adds them together to get the total spaces the player moves
     * on the gameboard.
     *
     * @param firstRoll     an int, first dice roll
     * @param secondRoll    an int, second dice roll
     * @return              an int, sum of the first and second dice roll
     */
    public int displayDiceRoll(int firstRoll, int secondRoll) {

        System.out.println("You rolled a " + firstRoll + " and a " + secondRoll);

        int sum = firstRoll + secondRoll;
        System.out.println("Hence, you will move " + sum + " spaces on the gameboard");

        return sum;

    }

    /**
     * Displays the amount that the player needs to pay the property owner.
     *
     * @param player            an ArrayList of players, list of the players that are currently playing the game
     * @param propertyBlock     a Square Object, the current position of the player on the gameboard
     * @param payer             an int, index of the player in the ArrayList of players
     */
    public void displayPayment(ArrayList<Player> player, Square propertyBlock, int payer)  {
        for (int payee = 0; payee < player.size(); payee++) {

            if (!player.get(payer).equals(player.get(payee))) {
                // if current player does not equal other players in the game
                // scan all properties of other players to see if they own that PROPERTY
                if (player.get(payee).getProperties().toString().contains(propertyBlock.getName())) {
                    // another player has been found that owns the property that the current player landed on
                    // so they must pay the other player rent.

                    System.out.println("\n" + player.get(payer).getName() + " must pay $" + propertyBlock.setProperty().calculateRent(player, getGameboard()) + " to " + player.get(payee).getName());

                    player.get(payer).removeMoney(propertyBlock.setProperty().calculateRent(player, getGameboard()));
                    // removing the rent that the current player had to pay

                    player.get(payee).addMoney(propertyBlock.setProperty().calculateRent(player, getGameboard()));
                    // adding the rent received from the current player

                    if (player.get(payee).getMoney() <= 0) {
                        // if player has no money left after paying the other player, then they have gone bankrupt
                        System.out.println(player.get(payee).getName() + " cannot pay rent. Hence, they have gone bankrupt.");
                        player.remove(player.get(payee));

                        if (player.size() == 1) {
                            // Check if only one player is left
                            // Only one player left, game over
                            System.out.println("\n" + player.get(0).getName() + " has won! Congratulations!");
                            System.exit(0);
                        }


                    }

                }
            }
        }
    }


}
