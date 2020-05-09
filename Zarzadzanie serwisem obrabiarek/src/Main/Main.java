package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean end=true;
        boolean wroc=true;
        int wybor,wyb = 0;
        int wyb1=0;
        ArrayList<Grupy> grupy = new ArrayList<>();

        do {
            System.out.println("PROGRAM ZARZADZANIA FIRMA ZAJMUJACA SIE REMONTAMI OBRABIAREK");
            System.out.println("------------------------------------------------------------");
            System.out.println("[1] -> Zarzadzanie grupami serwisowymi");
            System.out.println("[2] -> Zarzadzanie wyposazeniem");
            System.out.println("[3] -> Zarzadzanie pracownikami");
            System.out.println("[4] -> Wyjscie z programu");
            System.out.print("Wybierz opcje: ");
            try {
                wybor = new Scanner(System.in).nextInt();
            }catch(Exception e){
                System.out.println("Wprowadzono bledny numer. Program zostanie zakonczony.");
                System.out.println("Nacisnij enter, aby kontynuowac.");
                System.in.read();
                break;
            }
                switch (wybor) {
                    case 1:
                    {
                        do {
                            System.out.println("");
                            System.out.println("ZARZADZANIE GRUPAMI SERWISOWYMI");
                            System.out.println("-------------------------------");
                            System.out.println("[1] -> Dodaj grupe serwisowa");
                            System.out.println("[2] -> Wyswietl grupy serwisowe");
                            System.out.println("[3] -> Usuwanie grupy serwisowej");
                            System.out.println("[4] -> Wroc");
                            System.out.print("Wybierz opcje: ");
                            try{
                                wyb = new Scanner(System.in).nextInt();
                            }catch(Exception e) {
                                System.out.println("Wprowadzono bledny numer.");
                                System.out.println("Nacisnij enter, aby kontynuowac.");
                                System.in.read();
                                break;
                            }
                            wroc=true;
                            switch(wyb) {
                                case 1:
                                {
                                    System.out.println("DODAWANIE GRUPY SERWISOWEJ");
                                    System.out.println("**************************");
                                    System.out.print("Podaj ID nowej grupy: ");
                                    String id = new Scanner(System.in).nextLine();
                                    if(dostepnosc(id,grupy))
                                    {
                                        grupy.add(new Grupy(id));
                                        System.out.println("Dodano grupe: "+id);
                                    }else System.out.println("Podana grupa juz istnieje.");
                                    System.in.read();
                                    break;
                                }
                                case 2:
                                {
                                    if(grupy.size()>0) {
                                        wyswietlanieGrup(grupy);
                                    } else System.out.println("Brak grup do wyświetlenia.");
                                    System.out.println("Naciśnij Enter, aby kontynuować");
                                    System.in.read();
                                    break;
                                }
                                case 3:
                                {
                                    System.out.println("USUWANIE GRUPY SERWISOWEJ");
                                    System.out.println("*************************");
                                    if(grupy.size()==0) System.out.println("Brak grup.");
                                    else {
                                        wyswietlanieGrup(grupy);

                                        System.out.print("Podaj numer grupy do usunięcia: ");
                                        try{
                                            wyb1 = new Scanner(System.in).nextInt();
                                        }catch(Exception e) {
                                            System.out.println("Wprowadzono bledny numer.");
                                            System.out.println("Nacisnij enter, aby kontynuowac.");
                                            System.in.read();
                                            break;
                                        }
                                        if(wyb1>grupy.size()||wyb1<=0) {
                                            System.out.println("Nieporawny numer grupy.");
                                            break;
                                        }
                                        grupy.remove(wyb1-1);
                                        System.out.println("Grupa zotała usunięta.");
                                        break;
                                    }
                                }
                                case 4:
                                {
                                    wroc=false;
                                    break;
                                }
                            }
                        }while(wroc);
                        break;
                    }
                    case 2:
                    {
                        do {
                            System.out.println("");
                            System.out.println("ZARZADZANIE WYPOSAŻENIEM GRUP SERWISOWYCH");
                            System.out.println("-----------------------------------------");
                            System.out.println("[1] -> Dodaj wyposażenie");
                            System.out.println("[2] -> Edytuj wyposażenie");
                            System.out.println("[3] -> Wyświetl wyposażenie");
                            System.out.println("[4] -> Usuń wyposażenie");
                            System.out.println("[5] -> Wróć");
                            System.out.print("Wybierz opcję: ");
                            try{
                                wyb = new Scanner(System.in).nextInt();
                            }catch(Exception e) {
                                System.out.println("Wprowadzono błędny numer.");
                                System.out.println("Naciśnij enter, aby kontynuowac.");
                                System.in.read();
                                break;
                            }
                            wroc=true;
                            int iloscWyp=0;
                            switch(wyb) {
                                case 1:
                                {
                                    System.out.println("DODAWANIE WYPOSAZENIA");
                                    System.out.println("*********************");
                                    if(grupy.size()>0)
                                    {
                                        wyswietlanieGrup(grupy);
                                        System.out.println("*********************");
                                        System.out.print("Podaj numer grupy: ");
                                        try{
                                            wyb = new Scanner(System.in).nextInt();
                                        }catch(Exception e) {
                                            System.out.println("Wprowadzono bledny numer.");
                                            System.out.println("Naciśnij enter, aby kontynuować.");
                                            System.in.read();
                                            break;
                                        }
                                        if(wyb>grupy.size()||wyb<=0) break;
                                        System.out.print("Podaj nazwę wyposażenia: ");
                                        String nazwaWyp = new Scanner(System.in).nextLine();
                                        System.out.print("Podaj ilość wyposażenia: ");

                                        try{
                                            iloscWyp = new Scanner(System.in).nextInt();
                                        }catch(Exception e) {
                                            System.out.println("Wprowadzono błędną ilość.");
                                            System.out.println("Nacisnij enter, aby kontynuowac.");
                                            System.in.read();
                                            break;
                                        }
                                        if(iloscWyp<0) break;
                                        grupy.get(wyb-1).wyposazenie.add(new Wyposazenie(nazwaWyp, iloscWyp));
                                        System.out.println("Dodano wyposażenie.");
                                    }
                                    else System.out.println("Brak grup serwisowych.");
                                    System.out.println("Nacisnij enter, aby kontynuowac.");
                                    System.in.read();
                                    break;
                                }
                                case 2:
                                {
                                    System.out.println("ZMIANA ILOSCI WYPOSAZENIA");
                                    System.out.println("*************************");
                                    if(grupy.size()>0) {
                                        wyswietlanieGrup(grupy);
                                        System.out.println("*************************");
                                        System.out.print("Podaj numer grupy: ");
                                        try{
                                            wyb = new Scanner(System.in).nextInt();
                                        }catch(Exception e) {
                                            System.out.println("Wprowadzono bledny numer.");
                                            System.out.println("Nacisnij enter, aby kontynuowac.");
                                            System.in.read();
                                            break;
                                        }
                                        if(wyb>grupy.size()||wyb<=0) break;

                                        if(grupy.get(wyb-1).wyposazenie.size()>0)
                                        {
                                            wyswietlanieWyp(grupy.get(wyb-1).wyposazenie);
                                            System.out.println("*********************");
                                            System.out.print("Podaj numer wyposazenia, ktorego ilosc chcesz zmienic: ");
                                            try{
                                                wyb1= new Scanner(System.in).nextInt();
                                            }catch(Exception e) {
                                                System.out.println("Wprowadzono bledny numer.");
                                                System.out.println("Nacisnij enter, aby kontynuowac.");
                                                System.in.read();
                                                break;
                                            }
                                            System.out.print("Podaj nowa ilosc: ");
                                            try{
                                                iloscWyp= new Scanner(System.in).nextInt();
                                            }catch(Exception e) {
                                                System.out.println("Wprowadzono bledną ilość.");
                                                System.out.println("Nacisnij enter, aby kontynuowac.");
                                                System.in.read();
                                                break;
                                            }
                                            if(iloscWyp<0) break;
                                            else if(iloscWyp==0) grupy.get(wyb-1).wyposazenie.remove(wyb1-1);
                                            else grupy.get(wyb-1).wyposazenie.get(wyb1-1).setIlosc(iloscWyp);
                                            System.out.println("Zmieniono ilość wyposażenia.");
                                        }
                                        else System.out.println("Brak wyposażenie w danej grupie.");

                                    } else System.out.println("Brak grup serwisowych.");
                                    System.out.println("Naciśnij Enter, aby kontynuować");
                                    System.in.read();
                                    break;
                                }
                                case 3:
                                {
                                    System.out.println("WYSWIETLENIE WYPOSAZENIA");
                                    System.out.println("************************");
                                    if(grupy.size()>0) {
                                        wyswietlanieGrup(grupy);
                                        System.out.println("*************************");
                                        System.out.print("Podaj numer grupy: ");
                                        try{
                                            wyb = new Scanner(System.in).nextInt();
                                        }catch(Exception e) {
                                            System.out.println("Wprowadzono bledny numer.");
                                            System.out.println("Nacisnij enter, aby kontynuowac.");
                                            System.in.read();
                                            break;
                                        }
                                        if(wyb>grupy.size()||wyb<=0) break;

                                        if(grupy.get(wyb-1).wyposazenie.size()>0) {
                                            wyswietlanieWyp(grupy.get(wyb - 1).wyposazenie);
                                            System.out.println("*********************");
                                        } else System.out.println("Brak wyposażenia w danej grupie.");
                                    }
                                    else System.out.println("Brak grup serwisowych.");
                                    System.out.println("Naciśnij Enter, aby kontynuować");
                                    System.in.read();
                                    break;
                                    }

                                case 4:
                                    {
                                        System.out.println("USUWANIE WYPOSAZENIA");
                                        System.out.println("********************");
                                        if(grupy.size()>0) {
                                            wyswietlanieGrup(grupy);
                                            System.out.println("*************************");
                                            System.out.print("Podaj numer grupy: ");
                                            try{
                                                wyb = new Scanner(System.in).nextInt();
                                            }catch(Exception e) {
                                                System.out.println("Wprowadzono bledny numer.");
                                                System.out.println("Nacisnij enter, aby kontynuowac.");
                                                System.in.read();
                                                break;
                                            }
                                            if(wyb>grupy.size()||wyb<=0) break;

                                            if(grupy.get(wyb-1).wyposazenie.size()>0)
                                            {
                                                wyswietlanieWyp(grupy.get(wyb-1).wyposazenie);
                                                System.out.println("*********************");
                                                System.out.print("Podaj numer wyposazenia, ktore chcesz usunąć: ");
                                                try{
                                                    wyb1= new Scanner(System.in).nextInt();
                                                }catch(Exception e) {
                                                    System.out.println("Wprowadzono bledny numer.");
                                                    System.out.println("Nacisnij enter, aby kontynuowac.");
                                                    System.in.read();
                                                    break;
                                                }
                                                if(wyb1<=0||wyb1>grupy.get(wyb-1).wyposazenie.size()) break;
                                                else grupy.get(wyb-1).wyposazenie.remove(wyb1-1);
                                                System.out.println("Wposażenie zostało usunięte.");
                                            }
                                            else System.out.println("Brak wyposażenie w danej grupie.");
                                        } else System.out.println("Brak grup serwisowych.");
                                        System.out.println("Naciśnij Enter, aby kontynuować");
                                        System.in.read();
                                        break;
                                    }
                                case 5:
                                    {
                                      wroc=false;
                                      break;
                                    }
                            }
                        }while(wroc);
                        break;
                    }
                    case 3:
                    {
                        do {
                            System.out.println("");
                            System.out.println("ZARZADZANIE PRACOWNIKAMI");
                            System.out.println("--------------------------");
                            System.out.println("[1] -> Dodaj pracownika");
                            System.out.println("[2] -> Wyświetl pracowników");
                            System.out.println("[3] -> Usuń pracownika");
                            System.out.println("[4] -> Wróć");
                            System.out.print("Wybierz opcję: ");
                            try{
                                wyb = new Scanner(System.in).nextInt();
                            }catch(Exception e) {
                                System.out.println("Wprowadzono błędny numer.");
                                System.out.println("Naciśnij enter, aby kontynuowac.");
                                System.in.read();
                                break;
                            }
                            wroc=true;
                            switch(wyb) {
                                case 1:
                                {
                                    System.out.println("DODAWANIE PRACOWNIKA");
                                    System.out.println("*********************");
                                    if(grupy.size()>0)
                                    {
                                        wyswietlanieGrup(grupy);
                                        System.out.println("*********************");
                                        System.out.print("Podaj numer grupy: ");
                                        try{
                                            wyb = new Scanner(System.in).nextInt();
                                        }catch(Exception e) {
                                            System.out.println("Wprowadzono bledny numer.");
                                            System.out.println("Naciśnij enter, aby kontynuować.");
                                            System.in.read();
                                            break;
                                        }
                                        if(wyb>grupy.size()||wyb<=0) break;
                                        System.out.print("Podaj imię pracownika: ");
                                        String imie = new Scanner(System.in).nextLine();
                                        System.out.print("Podaj nazwisko pracownika: ");
                                        String nazwisko = new Scanner(System.in).nextLine();
                                        System.out.print("Podaj specjaloność pracownika: ");
                                        String spec = new Scanner(System.in).nextLine();
                                        grupy.get(wyb).pracownicy.add(new Pracownik(imie, nazwisko, spec));
                                        System.out.println("Dodano pracownika.");
                                    }
                                    else System.out.println("Brak grup serwisowych.");
                                    System.out.println("Nacisnij enter, aby kontynuowac.");
                                    System.in.read();
                                    break;
                                }
                                case 2:
                                {
                                    System.out.println("WYSWIETLENIE PRACOWNIKÓW");
                                    System.out.println("************************");
                                    if(grupy.size()>0) {
                                        wyswietlanieGrup(grupy);
                                        System.out.println("*************************");
                                        System.out.print("Podaj numer grupy: ");
                                        try{
                                            wyb = new Scanner(System.in).nextInt();
                                        }catch(Exception e) {
                                            System.out.println("Wprowadzono bledny numer.");
                                            System.out.println("Nacisnij enter, aby kontynuowac.");
                                            System.in.read();
                                            break;
                                        }
                                        if(wyb>grupy.size()||wyb<=0) break;

                                        if(grupy.get(wyb-1).pracownicy.size()>0) {
                                            wyswietlaniePrac(grupy.get(wyb-1).pracownicy);
                                            System.out.println("*********************");
                                        } else System.out.println("Brak pracowników w danej grupie.");
                                    }
                                    else System.out.println("Brak grup serwisowych.");
                                    System.out.println("Naciśnij Enter, aby kontynuować");
                                    System.in.read();
                                    break;
                                }

                                case 3:
                                {
                                    System.out.println("USUWANIE PRACOWNIKA");
                                    System.out.println("********************");
                                    if(grupy.size()>0) {
                                        wyswietlanieGrup(grupy);
                                        System.out.println("*************************");
                                        System.out.print("Podaj numer grupy: ");
                                        try{
                                            wyb = new Scanner(System.in).nextInt();
                                        }catch(Exception e) {
                                            System.out.println("Wprowadzono bledny numer.");
                                            System.out.println("Nacisnij enter, aby kontynuowac.");
                                            System.in.read();
                                            break;
                                        }
                                        if(wyb>grupy.size()||wyb<=0) break;
                                        if(grupy.get(wyb-1).pracownicy.size()>0)
                                        {
                                            wyswietlanieWyp(grupy.get(wyb-1).pracownicy);
                                            System.out.println("*********************");
                                            System.out.print("Podaj numer pracownika, ktorego chcesz usunąć: ");
                                            try{
                                                wyb1= new Scanner(System.in).nextInt();
                                            }catch(Exception e) {
                                                System.out.println("Wprowadzono bledny numer.");
                                                System.out.println("Nacisnij enter, aby kontynuowac.");
                                                System.in.read();
                                                break;
                                            }
                                            if(wyb1<=0||wyb1>grupy.get(wyb-1).pracownicy.size()) break;
                                            else grupy.get(wyb-1).pracownicy.remove(wyb1-1);
                                            System.out.println("Pracownik został usunięty.");
                                        }
                                        else System.out.println("Brak pracowników w danej grupie.");
                                    } else System.out.println("Brak grup serwisowych.");
                                    System.out.println("Naciśnij Enter, aby kontynuować");
                                    System.in.read();
                                    break;
                                }
                                case 5:
                                {
                                    wroc=false;
                                    break;
                                }
                            }
                        }while(wroc);
                        break;
                    }
                    case 4:
                    {
                        end=false;
                        break;
                    }
                }
        }while(end);
    }

    public static boolean dostepnosc(String id, ArrayList<Grupy> grup) {
        for (int i = 0; i < grup.size(); i++) {
            if(id.equals(grup.get(i).getIdGrupy()))
                return false;
        }
        return true;
    }

    public static void wyswietlanieGrup(ArrayList<Grupy> grup) {
        System.out.println("NR      ID Grupy");
        for (int i = 0; i < grup.size(); i++) {
            System.out.printf("%03d", i+1);
            System.out.println("     "+grup.get(i).getIdGrupy());
        }
    }

    public static void wyswietlanieWyp(ArrayList<Wyposazenie> wyp) {
        System.out.println("WYPOSAZENIE GRUPY");
        System.out.println("******************");
        System.out.println("NR      Nazwa(Ilość)");
        for (int i = 0; i < wyp.size(); i++) {
            System.out.printf("%03d", i+1);
            System.out.println("     "+wyp.get(i).getNazwa()+"("+wyp.get(i).getIlosc()+")");
        }
    }

    public static void wyswietlaniePrac(ArrayList<Pracownik> prac) {
        System.out.println("PRACOWNICY");
        System.out.println("************");
        System.out.println("NR      Imie Nazwisko(Specjalność)");
        for (int i = 0; i < prac.size(); i++) {
            System.out.printf("%03d", i+1);
            System.out.println("     "+prac.get(i).getImie()+" "+prac.get(i).getNazwisko()+"("+prac.get(i).getSpec()+")");
        }
    }
}
