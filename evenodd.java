import java.util.Scanner;
public class evenodd {
    public static void main (String[]args) {
        Scanner input = new Scanner (System.in);
        System.out.print ("Podaj liczbe: ");
        int number = input.nextInt();
            if (number%2==0) {
                System.out.print ("Podales liczbe parzysta");
        }   else {
                System.out.print ("Liczba nieparzysta");
        }
       int x = 64;
            int y = 0;
            while (x % 2 == 0) {
                y++;
                x = x/2;
            }
System.out.println (x + " " + y);
        }

    }

