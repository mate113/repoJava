package Main;

public class Pracownik {
    private String imie;
    private String nazwisko;
    private String spec;

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Pracownik(String imie, String nazwisko, String spec) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.spec = spec;
    }
}
