package io.file;

import exception.DataExportException;
import exception.DataImportException;
import exception.InvalidDataException;
import model.*;

import java.io.*;
import java.util.Collection;

public class CsvFileManager implements FileManager {
    private final String PUBLICATIONS_FILE_NAME = "Library.csv";
    private final String USERS_FILE_NAME = "LibraryUsers.csv";

    @Override
    public Library importData() {
        Library library = new Library();
        importPublications(library);
        importUsers(library);
        return library;
    }

    public void importPublications(Library library) {
        try(
                FileReader fileReader = new FileReader(PUBLICATIONS_FILE_NAME);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ){
            bufferedReader.lines()
                    .map(this::createPublicationFromString)
                    .forEach(library::addPublication);
        }catch(FileNotFoundException e){
            throw new DataImportException("Plik o nazwie " + PUBLICATIONS_FILE_NAME + " nie istnieje.");
        }catch(IOException e){
            throw new DataExportException("Błąd odczytu z pliku " + PUBLICATIONS_FILE_NAME);
        }
    }

    private Publication createPublicationFromString(String line){
        String[] data = line.split(";");
        if(data[0].equals("Książka")){
            return createBook(data);
        }else if(data[0].equals("Magazyn")){
            return createMagazine(data);
        }else throw new InvalidDataException("Nieobsługiwany typ publikacji: " + data[0]);
    }

    private Book createBook(String[] data){
        String title = data[1];
        String author = data[2];
        int releaseDate = Integer.parseInt(data[3]);
        int pages = Integer.parseInt(data[4]);
        String publisher = data[5];
        String isbn = data[6];
        return new Book(title, author, releaseDate, pages, publisher, isbn);
    }

    private Magazine createMagazine(String[] data){
        String title = data[1];
        String publisher = data[2];
        int releaseDate = Integer.parseInt(data[3]);
        int releaseMonth = Integer.parseInt(data[4]);
        int releaseDay = Integer.parseInt(data[5]);
        String language = data[6];
        return new Magazine(title, publisher, releaseDate, releaseMonth, releaseDay, language);
    }

    public void importUsers(Library library) {
        try(
                FileReader fileReader = new FileReader(USERS_FILE_NAME);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ){
            bufferedReader.lines()
                    .map(this::createUser)
                    .forEach(library::addUser);
        } catch(FileNotFoundException e){
            throw new DataImportException("Plik o nazwie " + USERS_FILE_NAME + " nie istnieje.");
        }catch(IOException e){
            throw new DataExportException("Błąd odczytu z pliku " + USERS_FILE_NAME);
        }
    }

    private LibraryUser createUser(String line){
        String[] data = line.split(";");
        String firstName = data[1];
        String lastName = data[2];
        String pesel = data[3];
        return new LibraryUser(firstName, lastName, pesel);
    }

    @Override
    public void exportData(Library library) {
        exportPublications(library);
        exportUsers(library);
    }

    private void exportPublications(Library library){
        exportToCsv(library.getPublications().values(), PUBLICATIONS_FILE_NAME);
    }

    private void exportUsers(Library library){
        exportToCsv(library.getUsers().values(), USERS_FILE_NAME);
    }

    private <T extends CsvConvertible> void exportToCsv(Collection<T> collection, String fileName){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))
        ){
            for (T t : collection) {
                bufferedWriter.write(t.toCsv());
                bufferedWriter.newLine();
            }
        }catch(IOException e){
            throw new DataExportException("Błąd zapisu do pliku " + USERS_FILE_NAME);
        }

    }

}
