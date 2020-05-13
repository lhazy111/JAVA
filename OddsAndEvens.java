import java.util.*;

public class OddsAndEvens {
    public static void main(String[] args) {
        String winner = "computer";
        String choice = "n";
        String again = "y";
        int fingers = 6;

        //intro
        //taking user name
        Scanner input = new Scanner(System.in);
        System.out.println("Let's play a game called \" Odds and Evens \"");
        System.out.print("What is your name?");
        String name = input.nextLine();
        while (again.equals("y")) {
            while (!choice.equals("o") && !choice.equals("e")) {
                System.out.println("Siemandero " + name + " , which do you choose? (O)dds or (E)vens");
                choice = input.nextLine();
                choice = (choice.toLowerCase());
                if (choice.equals("o")) {
                    System.out.println(name + " has picked odds. Computer will play evens.");
                } else if (choice.equals("e")) {
                    System.out.println(name + " has picked evens. Computer will play odds");
                } else {
                    System.out.println("Wrong choice. You have to choose o or e. You pressed : " + choice);
                }
            }
            System.out.println("--------------------------------------------------------------");
            //randomiser, choosing finger numbers
            Random rand = new Random();
            int computer = rand.nextInt(5)+1;
            System.out.println("OK. Let's play !!!");
            while (fingers < 1 || fingers > 5) {
                System.out.println("How many fingers do You put out? (1-5)");
                fingers = input.nextInt();
                if (fingers < 1 || fingers > 5) {
                    System.out.println("Hey!! It must be 1,2,3,4 or 5 and not " + fingers);
                }
            }
            System.out.println("OK " + name + " You have picked " + fingers);
            System.out.println("Computer plays " + computer);
            System.out.println("--------------------------------------------------------------");
            int sum = fingers + computer;
            System.out.println(fingers + " + " + computer + " = " + sum);
            if (sum % 2 == 0) {
                System.out.println(sum + " is even");
                if (choice.equals("e")) {
                    winner = name;
                }
            } else {
                System.out.println(sum + " is odd");
                if (choice.equals("o")) {
                    winner = name;
                }
            }
            System.out.println("The winner is : " + winner);
            System.out.println ( "-------------------------------------------------");
            System.out.println("Play again? (y/n)");
            again = input.next();
            System.out.println ( "-------------------------------------------------");
            choice = "n";
            fingers = 6 ;
            winner = "computer";
            input.nextLine();
        }
            System.out.println("Thanks for playing Odds and evens " + name);
        }
    }


