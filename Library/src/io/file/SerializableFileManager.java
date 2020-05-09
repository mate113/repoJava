package io.file;

import exception.DataExportException;
import exception.DataImportException;
import model.Library;

import java.io.*;

public class SerializableFileManager implements FileManager {
    private static String FILE_NAME = "Library.o";

    @Override
    public Library importData() {
        try (
                FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ){
            return (Library) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportException("Plik o nazwie " + FILE_NAME + " nie istnieje.");
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu danych z pliku " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Niezgodny typ danych zapisanych w pliku " + FILE_NAME);
        }
    }

    @Override
    public void exportData(Library library) {
        try(
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ){
            objectOutputStream.writeObject(library);
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            throw new DataImportException("Plik o nazwie " + FILE_NAME + " nie istnieje.");
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + FILE_NAME);
        }
    }
}
