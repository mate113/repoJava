package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Anagramy {
    public static void main(String[] args) {

        Anagramy anagram = new Anagramy();
        List<String> listaSlow = anagram.wczytajPlik();
        System.out.println(String.format("Liczba wczytanych wyrazow: %,d",listaSlow.size()));
        List<String> anagramy = anagram.wyszukajAnagramy(listaSlow);
        System.out.println(String.format("Liczba znalezionych anagramów: %,d \n", anagramy.size()));

        anagram.zapiszAnagramyDoPliku(anagramy);
        System.out.println("Anagramy zostały zapisane do pliku.");
//        System.out.println("Znalezione anagramy: ");
//        for (String s :anagramy) {
//            System.out.println(s);
//        }

    }

    public void zapiszAnagramyDoPliku(List<String> lista) {

        try{
            FileWriter fileWriter = new FileWriter("znalezione_anagramy.txt");
            for (int i = 0; i < lista.size(); i++) {
                fileWriter.write(lista.get(i)+"\n");
            }
            fileWriter.close();
        }catch(Exception e){
            System.out.println("Błąd w zapisie pliku.");
        }
    }

    public List<String> wczytajPlik() {
        List<String> slowa = new ArrayList<>();
        Set<String> slowaUnik = new HashSet<>();
        try{
            BufferedReader r = new BufferedReader(new FileReader("anagram.txt"));
            String linia;

            while((linia=r.readLine()) != null) {
                if(linia!="")
                {
                    slowaUnik.add(linia);
                }
            }
            r.close();
            slowa.addAll(slowaUnik);
        } catch(Exception e) {
            System.out.println("Błąd odczytu pliku.");
        }
        return slowa;
    }

    public List<String> wyszukajAnagramy(List<String> lista) {
        List<String> anagramy = new ArrayList<>();
        int ile = 0;

        System.out.println("Wyszukiwanie anagramów");
        for (int i = 0; i < lista.size(); i++) {
            String str1 = lista.get(i);
            for (int j = i+1; j < lista.size(); j++) {
                String str2 = lista.get(j);
                String sl1 = sortowanieWyrazow(str1);
                String sl2 = sortowanieWyrazow(str2);
                ile++;

                if(sl1.length() == sl2.length()) {
                    if (sl1.equals(sl2)) {
                        anagramy.add(str1 + " - " + str2);
                    }
                }
                if (ile%5000000 == 0){
                    System.out.print(".");
                }
            }
        }
        System.out.println(String.format("\nLiczba porownań: %,d", ile));
        return anagramy;
    }

    public String sortowanieWyrazow(String str) {
        char[] wyraz = str.toCharArray();
        Arrays.sort(wyraz);
        String posortowane = new String(wyraz);
        return posortowane;
    }
}
