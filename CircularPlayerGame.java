
import java.util.Scanner;

class Player {
    int id;
    String name;
    Player next;

    Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.next = null;
    }
}

public class CircularPlayerGame {
    Player head = null;
    Player current = null;
    Scanner sc = new Scanner(System.in);

    // Insert a player into the circular linked list
    void insertPlayer() {
        System.out.print("Enter Player ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Player Name: ");
        String name = sc.nextLine();

        Player newPlayer = new Player(id, name);

        if (head == null) {
            head = newPlayer;
            head.next = head;
            current = head;
        } else {
            Player temp = head;

            while (temp.next != head) {
                temp = temp.next;
            }

            temp.next = newPlayer;
            newPlayer.next = head;
        }

        System.out.println("Player inserted successfully!");
    }

    // Delete a player using Player ID
    void deletePlayer() {
        if (head == null) {
            System.out.println("No players in the game.");
            return;
        }

        System.out.print("Enter Player ID to delete: ");
        int id = sc.nextInt();

        Player temp = head;
        Player previous = null;

        do {
            if (temp.id == id) {
                break;
            }
            previous = temp;
            temp = temp.next;
        } while (temp != head);

        if (temp.id != id) {
            System.out.println("Player not found.");
            return;
        }

        // Only one player exists
        if (temp.next == temp) {
            head = null;
            current = null;
        } else {
            if (previous == null) {
                Player last = head;

                while (last.next != head) {
                    last = last.next;
                }

                head = head.next;
                last.next = head;
            } else {
                previous.next = temp.next;
            }

            if (current == temp) {
                current = temp.next;
            }
        }

        System.out.println("Player deleted successfully!");
    }

    // Display all players exactly once
    void displayPlayers() {
        if (head == null) {
            System.out.println("No players in the game.");
            return;
        }

        Player temp = head;

        System.out.println("\n--- Players in the Circle ---");

        do {
            System.out.println("Player ID: " + temp.id
                    + ", Name: " + temp.name);
            temp = temp.next;
        } while (temp != head);

        System.out.println("Back to the first player!");
    }

    // Give the turn to the current player
    void nextTurn() {
        if (current == null) {
            System.out.println("No players available.");
            return;
        }

        System.out.println("Current Turn: "
                + current.name + " (ID: " + current.id + ")");

        current = current.next;
    }

    public static void main(String[] args) {
        CircularPlayerGame game = new CircularPlayerGame();
        int choice;

        do {
            System.out.println("\n===== Circular Player Game =====");
            System.out.println("1. Insert Player");
            System.out.println("2. Delete Player");
            System.out.println("3. Display Players");
            System.out.println("4. Next Turn");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = game.sc.nextInt();

            switch (choice) {
                case 1:
                    game.insertPlayer();
                    break;
                case 2:
                    game.deletePlayer();
                    break;
                case 3:
                    game.displayPlayers();
                    break;
                case 4:
                    game.nextTurn();
                    break;
                case 5:
                    System.out.println("Game ended.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);

        game.sc.close();
    }
}
