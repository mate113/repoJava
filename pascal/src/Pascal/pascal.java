package Pascal;

import java.util.Scanner;

public class pascal
{
    public static void main(String[] args) {
        System.out.println("Ile poziomów trójkąta pascala narysować? (max 34)");
        Scanner scanner = new Scanner(System.in);
        int ilePoziomow = scanner.nextInt();
        if(ilePoziomow>34) {
            System.out.println("Za dużo poziomów. Spróbuj ponownie. ");
            main(null);
        }else trojkat(ilePoziomow);
    }

    public static void trojkat(int ilePoziomow){
        int[] tablica = new int[ilePoziomow];
        int ile = ilePoziomow;
        int[] tab;
        for(int i=0;i<ilePoziomow;i++){
            for(int j=0;j<ile;j++) {
                System.out.format("%6s", "");
            }
            tab = oblicz(i+1, tablica);
            for (int j=0;j<tab.length;j++) {
                System.out.format("%-12s", tab[j]);
            }
            ile--;
            tablica=tab;
            System.out.println();
        }
    }

    public static int[] oblicz(int pietro, int tablica[]) {
        int[] tablicaLiczb = new int[pietro];
        tablicaLiczb[0]=1;
        tablicaLiczb[pietro-1]=1;
        if (pietro>1){
            for (int i=1;i<pietro-1;i++){
                tablicaLiczb[i] = tablica[i-1] + tablica[i];
            }
        }
        return tablicaLiczb;
    }

}