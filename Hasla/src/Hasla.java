import java.util.Random;
import java.util.Scanner;

public class Hasla {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Wprowadz haslo");
        System.out.println("2. Wygeneruj haslo");
        System.out.print("Wybierz opcje: ");
        int option = 0;
        try{
            option = sc.nextInt();
        }catch(Exception e)
        {
            System.out.println("Błędna liczba!");

        }
        switch (option) {
            case 1: {
                enterPassword();
                break;
            }
            case 2: {
                System.out.println("\nGenerator haseł");
                System.out.print("Ile cyfr powinno mieć hasło: ");
                int charsNumber = 0;
                try{
                    charsNumber = new Scanner(System.in).nextInt();
                    String password = generatePassword(charsNumber);
                    System.out.print("Twoje hasło: " + password);
                }catch (Exception e) {
                    System.out.println("Błędna liczba");
                }
                break;
            }
        }
    }

    private static void enterPassword() {
        System.out.println("\nWprowadz haslo skladające się z 5-15 znaków oraz \n" +
                "zawierające dużą literę, małą literę i cyfrę, bez przerw");

        System.out.print("Hasło: ");
        String password = new Scanner(System.in).nextLine();
        if(validatePassword(password))
        {
            System.out.print("Zaakceptowano haslo: " + password);
        }else
            System.out.println("Wprowadzono błędne hasło.");
    }

    private static boolean validatePassword(String password) {
        if(password.length()>=5&&password.length()<=15&&
                password.matches(".*[A-Z]+.*")&&
                password.matches(".*[a-z]+.*")&&
                password.matches(".*[0-9]+.*")&&
                !password.contains(" "))
            return true;
        else
            return false;
    }

    private static String generatePassword(int number){
        String chars = "abcdefghijklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUWXYZ0123456789";
        String password = "";
        Random r = new Random();
        for (int i = 0; i < number; i++) {
            password += chars.charAt(r.nextInt(chars.length()));
        }
        return password;
    }
}
