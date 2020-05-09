/*
An emirp is a prime number that results in a different prime when its decimal digits are reversed.
For example, 13 is an emirp number because both 13 and 31 are prime numbers.

For example:
Input: 17
Output: true (17 and 71 are prime numbers)

Input: 113
Output: true (113 and 311 are prime numbers)

Input: 23
Output: false (23 is a prime number, but 32 is not)

Write a program to check if the user input is an emirp number or not.
 */
import java.util.Scanner;

public class EmripNumbers {
    public static void main(String[] args) {
        System.out.println("Enter number to check if it is an emrip number or not");
        System.out.print("Your number: ");
        String number = new Scanner(System.in).next();
        int numb = Integer.parseInt(number);
        if(isPrime(numb))
        {
            String number1 = "";
            for (int i = number.length()-1; i >= 0; i--) {
                number1+=number.charAt(i);
            }
            int numb1 = Integer.parseInt(number1);
            System.out.print("Reversed number: "+numb1);
            if(isPrime(numb1))
                System.out.println("\nYour number is emrip");
            else
                System.out.println("\nYour number is not emrip");
        }else {
            System.out.println("Your number is not emrip. \n"+number+" is not a prime number.");
        }
    }

    public static boolean isPrime(int x) {
        if(x%2==0)
            return false;
        else
            return true;
    }
}
