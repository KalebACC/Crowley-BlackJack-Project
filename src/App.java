import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
    
    static ArrayList<Integer> playerCards = new ArrayList<>(); // Creates an empty array to hold all the player's cards
    static ArrayList<Integer> dealerCards = new ArrayList<>(); // Creates an empty array to hold all the dealer's cards
    static Scanner scanner = new Scanner(System.in); // Creates a scanner to get player's selections
    public static boolean dealerBusted = false;
    public static boolean playerStand = false; // Flag to check if the player stands
    
    public static void main(String[] args) throws Exception {   
        
        while (true) {
            System.out.println("Welcome to blackjack!");
            startGame();
            
            while (!playerStand && getCardSum(playerCards) <= 21) { // Add check for 'playerStand'
                playerChoice();
            }
            
            if (getCardSum(playerCards) > 21) {
                System.out.println("You busted");
            } else if (!dealerBusted) {
                compareScore();
            }
            break;
        }
    }

    public static void dealCard(String who) {
        int[] cards = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}; // List of all possible cards you can get
        int randomCard = cards[new Random().nextInt(cards.length)]; // Picks a random card from the list
        if (who.equals("Player")) {
            playerCards.add(randomCard);
        } else {
            dealerCards.add(randomCard);
        }
    }

    public static void printCards() {
        System.out.println("Your Cards: " + playerCards); // Prints out the player's cards
        System.out.println("Dealer Cards: " + dealerCards); // Prints dealer's cards
    }

    public static void startGame() { // Deals the player 2 cards and the dealer 1
        dealCard("Player");
        dealCard("Player");
        dealCard("Dealer");
        printCards();
    }

    public static int getCardSum(ArrayList<Integer> hand) {
        int sum = 0;
        for (int card : hand) {
            sum += card;
        }
        return sum;
    }

    public static void playerChoice() {
        System.out.println("Hit? y/n");
        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("y")) {
            dealCard("Player");
            printCards();
        } else if (response.equals("n")) {
            playerStand = true; // Set the flag to true when the player stands
            while (getCardSum(dealerCards) <= 17) {
                dealerChoice();
                if (getCardSum(dealerCards) > 21) {
                    dealerBusted = true;
                    System.out.println("Dealer Busted");
                }
            }
        }
    }

    public static void dealerChoice() {
        dealCard("Dealer");
        printCards();
    }

    public static void compareScore() {
        if (getCardSum(playerCards) > getCardSum(dealerCards) || dealerBusted) {
            System.out.println("You Won");
        } else {
            System.out.println("You Lost");
        }
    }
}
