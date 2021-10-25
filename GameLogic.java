/**
 * @author Oliver Lu
 * @version Milestone 1
 */

import java.util.*;

/**
 * GameLogic Class that deals with the logic of the game
 * and uses GameUI to print out important commands for each
 * player that is playing the game.
 */
public class GameLogic {

    /**
     * Main method that deals with all the logic
     * of the game. It goes through each player and
     * allows them to roll their dice.
     *
     * If a player rolls doubles,
     * they get to go again.
     *
     * @param args
     */
    public static void main(String[] args) {

        // Game is NOT called Monopoly due to copyright reasons
        System.out.println("\nWelcome to the game of Funopoly!\n");

        GameUI ui = new GameUI();

        ArrayList<Player> player = new ArrayList<>();

        player = ui.displayPlayerInput(player);
        // ask the user how many players are going to be playing the game and their names

        int sum = 0;

        while (true) {
            for (int currentPlayer = 0; currentPlayer < player.size(); currentPlayer++) {
                // go through each player

                ui.printBoard();

                System.out.println("\nPlayer " + (currentPlayer + 1) + "'s (" + player.get(currentPlayer).getName() + ") turn: ");

                Scanner roll = new Scanner(System.in);

                boolean errorFree = false;

                int rollDie = 0;
                int rollDie2 = 0;

                while (!errorFree) {
                    // accounting for errors (if user enters wrong command)

                    System.out.println("Type \"R\" to roll your dice: ");

                    if (roll.next().equals("R")) {

                        Die d = new Die();
                        Die d2 = new Die();

                        rollDie = d.roll();
                        rollDie2 = d2.roll();

                        sum = ui.displayDiceRoll(rollDie, rollDie2);

                        if (player.get(currentPlayer).getPosition().isEmpty()) {
                            // user is at position 0 if the list of positions is empty
                            player.get(currentPlayer).addPosition(0);
                        }

                        int p = ((player.get(currentPlayer).getPosition().get(player.get(currentPlayer).getPosition().size() - 1) + sum) % ui.createBoard().size());
                        player.get(currentPlayer).addPosition(p);

                        errorFree = true;

                    } else {
                        roll.nextLine();
                        System.out.println("\nYou must enter \"R\" to roll your dice. Try again!\n");
                    }
                }

                Square currentPosition = ui.getGameboard().getSquare(player.get(currentPlayer).getPosition().get(player.get(currentPlayer).getPosition().size() - 1));
                // getting the square that the user is currently on

                System.out.println("You landed on " + currentPosition.getName());

                ui.displayCommands(player, rollDie, rollDie2, currentPlayer, currentPosition, ui);

                if (rollDie == rollDie2) {
                    // doubles are rolled, same player goes again
                    currentPlayer--;
                }

            }


        }
    }
}





