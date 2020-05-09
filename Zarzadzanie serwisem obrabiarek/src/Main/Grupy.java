package Main;

import java.util.ArrayList;

public class Grupy {
    private String idGrupy;

    public ArrayList<Pracownik> pracownicy= new ArrayList<>();
    public ArrayList<Wyposazenie> wyposazenie= new ArrayList<>();

    public Grupy(String idGrupy) {
        this.idGrupy = idGrupy;
    }

    public String getIdGrupy() {
        return idGrupy;
    }

    public void setIdGrupy(String idGrupy) {
        this.idGrupy = idGrupy;
    }

    public void dodajPracownika(String imie, String nazwisko, String spec) {
        pracownicy.add(new Pracownik(imie, nazwisko, spec));
    }

    public String imiePracownika(int n) {
        return pracownicy.get(n).getImie();
    }

    public String nazwiskoPracownika(int n) {
        return pracownicy.get(n).getNazwisko();
    }

    public String specPracownika(int n) {
        return pracownicy.get(n).getSpec();
    }

    public void usunPracownika(int n) {
        pracownicy.remove(n);
    }

    public void dodajWyposazenie(String nazwa, int ilosc) {
        wyposazenie.add(new Wyposazenie(nazwa, ilosc));
    }

    public void edytujWyposazenie(int n, int ilosc) {
        wyposazenie.get(n).setIlosc(ilosc);
        if(wyposazenie.get(n).getIlosc()==0)
            wyposazenie.remove(n);
    }

    public String nazwaWyposazenia(int n) {
        return wyposazenie.get(n).getNazwa();
    }

    public int iloscWyposazenia(int n) {
        return wyposazenie.get(n).getIlosc();
    }

    public void usunWyposazenie(int n) {
        wyposazenie.remove(n);
    }
}
