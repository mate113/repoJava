package model;

import java.util.Objects;

public class Book extends Publication{
    public static final String TYPE = "Ksiazka";
    private String author;
    private int pages;
    private String isbn;

    public Book(String title, String author, int releaseDate, int pages, String publisher) {
        super(title, releaseDate, publisher);
        this.pages = pages;
        this.author = author;
    }

    public Book(String title, String author, int releaseDate, int pages, String publisher, String isbn) {
        this(title, author, releaseDate, pages, publisher);
        this.isbn = isbn;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toCsv() {
        return TYPE + ";" +
                getTitle() + ";" +
                author + ";" +
                getReleaseDate() + ";" +
                pages + ";" +
                getPublisher() + ";" +
                isbn;
    }

    @Override
    public String toString() {
        String info = getTitle() + "; " + author + "; " + getReleaseDate() + "; " + pages + "; " + getPublisher();
        if (isbn != null) info = info + "; " + isbn;
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pages == book.pages &&
                author.equals(book.author) &&
                isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, pages, isbn);
    }
}
