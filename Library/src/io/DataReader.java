package io;

import model.Book;
import model.LibraryUser;
import model.Magazine;

import java.util.Scanner;

public class DataReader {
    private Scanner scanner = new Scanner(System.in);
    private ConsolePrinter printer;

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public String getString(){
        return scanner.nextLine();
    }

    public void close(){
        scanner.close();
    }

    public int getInt(){
        try{
            return scanner.nextInt();
        }finally {
            scanner.nextLine();
        }
    }

    public Book readAndCreateBook(){
        printer.printLine("Tytuł: ");
        String title = scanner.nextLine();
        printer.printLine("Autor: ");
        String author = scanner.nextLine();
        printer.printLine("Wydawca: ");
        String publisher = scanner.nextLine();
        printer.printLine("ISBN: ");
        String isbn = scanner.nextLine();
        printer.printLine("Rok wydania: ");
        int releaseDate = getInt();
        printer.printLine("Liczba stron: ");
        int pages = getInt();

        return new Book(title, author, releaseDate, pages, publisher, isbn);
    }

    public Magazine readAndCreateMagazine(){
        printer.printLine("Tytuł: ");
        String title = scanner.nextLine();
        printer.printLine("Wydawca: ");
        String publisher = scanner.nextLine();
        printer.printLine("Rok wydania: ");
        int releaseDate = getInt();
        printer.printLine("Miesiąc wydania: ");
        int releaseMonth = getInt();
        printer.printLine("Dzień wydania: ");
        int releaseDay = getInt();
        printer.printLine("Język: ");
        String language = scanner.nextLine();

        return new Magazine(title, publisher, releaseDate, releaseMonth, releaseDay, language);
    }

    public LibraryUser createLibraryUser(){
        printer.printLine("Imie: ");
        String firstName = scanner.nextLine();
        printer.printLine("Nazwisko: ");
        String lastName = scanner.nextLine();
        printer.printLine("Pesel: ");
        String pesel = scanner.nextLine();

        return new LibraryUser(firstName, lastName, pesel);
    }
}
