import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        char [][] plansza = new char[3][3];
        char znak = 'X';
        boolean wygrana = true;
        int liczbaRuchow=0;
        while(wygrana && liczbaRuchow<9) {
            wyswietlPlansze(plansza);
            System.out.println("Podaj numer wiersza i kolumny oddzielone spacją.");

            Scanner sc = new Scanner(System.in);
            int wiersz = sc.nextInt();
            int kolumna = sc.nextInt();
            if (wiersz>3 || wiersz<1 || kolumna<1 || kolumna>3 || plansza[wiersz-1][kolumna-1]!=0) {
                System.out.println("---Błędny ruch! Spróbuj jeszcze raz.---");
            }
            else {
                plansza[wiersz-1][kolumna-1] = znak;
                if(czyWygralesPoziomo(plansza, znak) || czyWygralesPionowo(plansza, znak) ||
                        czyWygralesUkosnie1(plansza, znak) || czyWygralesUkosnie2(plansza, znak)) {
                    wyswietlPlansze(plansza);
                    System.out.println("Gratulacje " + "\'" + znak + "\'" +  " - Wygrałeś!" );
                    break;
                }
                znak= znak=='X' ? 'O' : 'X';
                liczbaRuchow++;
            }
        }
        if (liczbaRuchow==9) System.out.println("REMIS!");
    }

    private static boolean czyWygralesUkosnie2(char[][] plansza, char znak) {
        int wiersze = plansza.length-1;
        int kolumny = plansza[wiersze-1].length;

        int powtorzenia = 0;
        for (int i = 0; i<kolumny; i++) {
            if (plansza[wiersze][i]!=znak) break;
            else {
                powtorzenia++;
                if (powtorzenia == 3) return true;
                wiersze--;
            }
        }
        return false;
    }

    private static boolean czyWygralesUkosnie1(char[][] plansza, char znak) {
        int powtorzenia = 0;
        for (int i = 0; i<plansza.length; i++) {
            if (plansza[i][i]!=znak) break;
            else {
                powtorzenia++;
                if (powtorzenia == 3) return true;
            }
        }
        return false;
    }

    private static boolean czyWygralesPionowo(char[][] plansza, char znak) {
        int wiersze = plansza.length;
        int kolumny = plansza[wiersze-1].length;
        for (int i = 0; i<kolumny; i++) {
            int powtorzenia = 0;
            for (int j = 0; j<wiersze; j++) {

                if (znak!=plansza[j][i]) break;
                else {
                    powtorzenia++;
                    if (powtorzenia == 3) return true;
                }
            }
        }
        return false;
    }

    private static boolean czyWygralesPoziomo(char[][] plansza, char znak) {
        for (int i = 0; i<plansza.length; i++) {
            int powtorzenia = 0;
            for (int j = 0; j<plansza[i].length; j++) {

                if (znak!=plansza[i][j]) break;
                else {
                    powtorzenia++;
                    if (powtorzenia == 3) return true;
                }
            }
        }
        return false;
    }

    private static void wyswietlPlansze(char[][] plansza) {
        int i = 1;
        System.out.println("\t1  2  3");
        for (char[] chars : plansza) {
            System.out.print(i + "\t");
            for (char aChar : chars) {
                if (aChar == 0)
                    aChar = ' ';
                System.out.print(aChar + "  ");
            }
            System.out.println();
            i++;
        }
    }

}
