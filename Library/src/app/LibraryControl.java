package app;

import exception.*;
import io.ConsolePrinter;
import io.DataReader;
import io.file.FileManager;
import io.file.FileManagerBuilder;
import model.*;

import java.util.Comparator;
import java.util.InputMismatchException;

public class LibraryControl {
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;
    private Library library;

    LibraryControl(){
        fileManager = new FileManagerBuilder(printer, dataReader).build();
        try {
            library = fileManager.importData();
            printer.printLine("Zaimportowano dane z pliku");
        } catch(DataImportException | InvalidDataException e){
            printer.printLine(e.getMessage());
            printer.printLine("Zainicjowano nową bazę.");
            library = new Library();
        }
    }

    void control(){
        Option option;

        do {
            printOptions();
            option = getOption();
            switch (option) {
                case ADD_BOOK:
                    addBook();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case PRINT_MAGAZINES:
                    printMagazines();
                    break;
                case DELETE_BOOK:
                    deleteBook();
                    break;
                case DELETE_MAGAZINE:
                    deleteMagazine();
                    break;
                case ADD_USER:
                    addUser();
                    break;
                case PRINT_USER:
                    printUsers();
                    break;
                case FIND_PUBLICATION:
                    findPublication();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printLine("Brak dostępnej opcji o takim numerze. Spróbuj ponownie");
            }
        }while (option!=Option.EXIT);
    }

    private void addUser() {
        try {
            library.addUser(dataReader.createLibraryUser());
            printer.printLine("Dodano użytkowanika");
        } catch(UserAlreadyExistsException e){
            printer.printLine(e.getMessage());
        }
    }

    private void addBook(){
        try {
            library.addPublication(dataReader.readAndCreateBook());
            printer.printLine("Dodano książkę");
        } catch (InputMismatchException e) {
            printer.printLine("Niepoprawne dane.\n");
        } catch (ArrayIndexOutOfBoundsException e){
            printer.printLine("Osiągnięto limit publikacji\n");
        }
    }

    private void addMagazine(){
        try {
            library.addPublication(dataReader.readAndCreateMagazine());
            printer.printLine("Dodano magazyn");
        } catch (InputMismatchException e) {
            printer.printLine("Niepoprawne dane.\n");
        } catch (ArrayIndexOutOfBoundsException e){
            printer.printLine("Osiągnięto limit publikacji\n");
        }
    }

    private void printBooks(){
        printer.printBooks(library.getSortedPublications(Comparator.comparing(Publication::getTitle, String.CASE_INSENSITIVE_ORDER)));
    }

    private void printMagazines(){
        printer.printMagazines(library.getSortedPublications(Comparator.comparing(Publication::getTitle, String.CASE_INSENSITIVE_ORDER)));
    }

    private void printUsers(){
        printer.printUsers(library.getSortedUsers(Comparator.comparing(User::getLastName, String.CASE_INSENSITIVE_ORDER)));
    }

    private void deleteBook() {
        try{
            if(library.removePublication(dataReader.readAndCreateBook())) printer.printLine("Książka została usunięta");
            else printer.printLine("W bibliotece nie znaleziono podanej książki");
        }catch(InputMismatchException e){
            printer.printLine("Błędne dane książki.");
        }
    }

    private void deleteMagazine() {
        try{
            if(library.removePublication(dataReader.readAndCreateMagazine())) printer.printLine("Magazyn została usunięta");
            else printer.printLine("W bibliotece nie znaleziono podanego magazynu");
        }catch(InputMismatchException e){
            printer.printLine("Błędne dane magazynu.");
        }
    }

    private void findPublication(){
        printer.printLine("Podaj tytuł publikacji do wyszukania.");
        String title = dataReader.getString();
        library.findPublicationByTitle(title)
                .map(Publication::toString)
                .ifPresentOrElse(System.out::println, () -> printer.printLine("Brak publikacji o podanym tytule."));
    }

    private void printOptions(){
        System.out.println("Zarządzanie.");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
        printer.printLine("Podaj numer opcji, którą chcesz wykonać: ");
    }

    private Option getOption(){
        Option option = null;
        boolean isOk = false;
        while(!isOk){
            try{
                option = Option.optionFromInt(dataReader.getInt());
                isOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage() + ". Spróbuj jeszcze raz.");
            } catch (InputMismatchException e){
                printer.printLine("Podana wartość nie jest liczbą. Spróbuj jeszcze raz.");
            }
        }
        return option;
    }

    private void exit(){
        try {
            fileManager.exportData(library);
            printer.printLine("Dane zostały wyexportowane do pliku.");
        }catch (DataExportException e){
            printer.printLine(e.getMessage());
        }
        printer.printLine("Koniec programu.");
        dataReader.close();
    }

    private enum Option {
        ADD_BOOK(1, "Dodaj książkę"),
        ADD_MAGAZINE(2, "Dodaj magazyn"),
        PRINT_BOOKS(3, "Wyświetl książki"),
        PRINT_MAGAZINES(4, "Wyświetl magazyny"),
        DELETE_BOOK(5, "Usuń ksiazke"),
        DELETE_MAGAZINE(6, "Usuń magazyn"),
        ADD_USER(7, "Dodaj użytkownika"),
        PRINT_USER(8, "Wyświetl użytkowników"),
        FIND_PUBLICATION(9, "Wyszukaj publikację"),
        EXIT(0, "Wyjdź z programu");

        private final int optionNumber;
        private final String optionName;

        Option(int optionNumber, String optionName) {
            this.optionNumber = optionNumber;
            this.optionName = optionName;
        }

        @Override
        public String toString() {
            return optionNumber + ". " + optionName;
        }

        public static Option optionFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option-1];
            }catch (ArrayIndexOutOfBoundsException e){
                throw new NoSuchOptionException("Brak opcji o id: " + option);
            }
        }
    }
}