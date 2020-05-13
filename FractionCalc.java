import java.util.Arrays;
import java.util.Scanner;

public class FractionCalc {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        String[] fraction = {"/", "/"};
        String operation = "";
        Fraction result;
        Fraction firstFraction = new Fraction (1);
        Fraction secondFraction = new Fraction (1);
        System.out.println("Welcome to Fraction Calculator by L.Hazy");
        System.out.println("It will add, substract, multiply and divide fractions until You type Q to quit.");
        System.out.println("Please enter your fractions in the form a/b where a and b are integers. ");
        System.out.println("----------------------------------------------------------------------------------");
        while (!operation.equals("q")) {
            while (!validOperation(operation)) {
                System.out.print("Please enter the operation +, -, *, /, =, or Q to quit: ");
                operation = getOperation(input.nextLine());
            }

            if (operation.equalsIgnoreCase("q")) {
                break;
            }
            for (int i = 1; i <= 2; i++) {
                while (!validFraction(fraction[i - 1])) {
                    System.out.print("Please enter fraction " + i + " : ");
                    fraction[i - 1] = getFraction(input.nextLine());
                }
            }
            //-------------------------------------------------------------
            int slashPosition = fraction[0].indexOf("/");
            if (slashPosition > 0) {
                firstFraction = new Fraction ((Integer.parseInt(fraction[0].substring(0, slashPosition))), (Integer.parseInt(fraction[0].substring(slashPosition + 1, fraction[0].length()))) );
                firstFraction.setNumerator(Integer.parseInt(fraction[0].substring(0, slashPosition)));
                firstFraction.setDenominator(Integer.parseInt(fraction[0].substring(slashPosition + 1, fraction[0].length())));

            } else {
                firstFraction.setNumerator(Integer.parseInt(fraction[0]));
                firstFraction.setDenominator(1);

            }
            //--------------------------------------------------------------
            slashPosition = fraction[1].indexOf("/");
            if (slashPosition > 0) {
                secondFraction.setNumerator(Integer.parseInt(fraction[1].substring(0, slashPosition)));
                secondFraction.setDenominator(Integer.parseInt(fraction[1].substring(slashPosition + 1, fraction[1].length())));

            } else {
                secondFraction.setNumerator(Integer.parseInt(fraction[1]));
                secondFraction.setDenominator(1);

            }
            System.out.println("First fraction numerator: " + firstFraction.getNumerator());
            System.out.println("First fraction denominator: " + firstFraction.getDenominator());
            System.out.println("Operation: " + operation);
            System.out.println("Second fraction numerator: " + secondFraction.getNumerator());
            System.out.println("Second fraction denominator: " + secondFraction.getDenominator());


            if (operation.equals("=")) {
                String resultEqual = finalResultEqual(firstFraction, secondFraction);
                System.out.println(firstFraction.toString() + " " + operation + " " + secondFraction.toString() + " = " + resultEqual);
            }
            else {
                result = finalResult(operation, firstFraction, secondFraction);
                if(result.getDenominator()!=0) {
                    System.out.println(firstFraction.toString() + " " + operation + " " + secondFraction.toString() + " = " + result.toString());
                   // result.toLowestTerms();
                   // firstFraction.toLowestTerms();
                   // secondFraction.toLowestTerms();
                   // System.out.println(firstFraction.toString() + " " + operation + " " + secondFraction.toString() + " = " + result.toString());
                }
                }
            operation = "";
            Arrays.fill(fraction, "/");
        }
        System.out.println("Thank You for using calculator");
    }

        public static String getOperation(String operation) {
        if(!validOperation(operation)){
                    System.out.println("Wrong operation");
                    return operation;
            }
            return operation;
        }
        public static boolean validOperation (String operation) {
            return (operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/")|| operation.equals("=") || operation.equalsIgnoreCase("q"));
    }
        public static String getFraction(String fraction) {

                if(!validFraction(fraction)){
                    System.out.println("Wrong fraction");
                }


            return fraction;

        }
        public static boolean validFraction(String fraction) {
            char[] fractionToCheck = fraction.toCharArray();
            int start = 0;
            int slashCount = 0;
                if (fractionToCheck.length == 0){
                    return false;
        }
                else if (fractionToCheck[fractionToCheck.length-1] == '/') {
                    //System.out.println("Slash at the end");
                     return false;
        }
                else if ((fractionToCheck.length>1) && (fractionToCheck[fractionToCheck.length - 1] == '0') && (fractionToCheck[fractionToCheck.length - 2] == '/')){
                    //System.out.println("Denominator can't be 0 !");
                    return false;
                }
                else if (fractionToCheck[0]=='-') {
                    start=1;
        }
        for (int i = start ; i<fractionToCheck.length; i++){
            if (!Character.isDigit(fractionToCheck[i]) && fractionToCheck[i] != '/') {
              return false;
            }
            else if (fractionToCheck[i] == '/') {
                slashCount++;
                if (slashCount>1) {
                    return false;
                }
            }
        }
        return true;

        }
        public static Fraction finalResult (String operation, Fraction firstFraction, Fraction secondFraction) {
            Fraction result = new Fraction();
            switch (operation) {
                case "+":
                    result = firstFraction.add(secondFraction);
                    return result;
                case "-":
                    result = firstFraction.substract(secondFraction);
                    return result;
                case "/":
                    result = firstFraction.divide(secondFraction);
                    if (secondFraction.getNumerator() == 0) {
                        System.out.println("You can't divide by zero !!!!!");
                        return result;
                    }
                    return result;
                case "*":
                    result = firstFraction.multiply(secondFraction);
                    return result;
                default:
                    System.out.println("Nic");
                    return result;
            }
        }
    public static String finalResultEqual(Fraction firstFraction, Fraction secondFraction) {
                    if (firstFraction.equals(secondFraction)) {
                        return "are equal";
                    }
                    else return "are NOT equal";
    }

}
