import java.io.IOException;
import java.util.Scanner;

public class Kalkulator {
    public static void main(String[] args) throws IOException {
        System.out.println("Kalkulator");
        System.out.println("Obslugiwane dzialania: dodawanie, odejmowanie, mnozenie, dzielenie");
        System.out.print("Wprowadz dzialanie(bez przerw): ");
        Scanner scanner = new Scanner(System.in);
        String dzialanie="";
        long wynik;
        String znak = "";
        dzialanie = scanner.nextLine();

        if (dzialanie.contains("+"))
            znak = "+";
        else if (dzialanie.contains("-"))
            znak = "-";
        else if (dzialanie.contains("*"))
            znak = "*";
        else if (dzialanie.contains("/"))
            znak = "/";
        else {
            System.out.println("Nie wprowadzono znaku działania.");
            return;
        }
        String[] numbers = dzialanie.split(znak);

        try {
            long a = Long.parseLong(numbers[0]);
            long b = Long.parseLong(numbers[1]);
            System.out.print(dzialanie + "=");
            if (dzialanie.contains("+")) {
                wynik = a + b;
                System.out.println(wynik);
            } else if (dzialanie.contains("-")) {
                wynik = a - b;
                System.out.println(wynik);
            } else if (dzialanie.contains("*")) {
                wynik = a * b;
                System.out.println(wynik);
            } else if (dzialanie.contains("/")) {
                wynik = a / b;
                long reszta = a % b;
                System.out.println(wynik + " r=" + reszta);
            }
        }
        catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println("Wprowadzono błędne działanie.");
        }
    }
}
