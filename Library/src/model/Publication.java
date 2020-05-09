package model;

import java.io.Serializable;
import java.time.Year;
import java.util.Objects;

public abstract class Publication implements Serializable, Comparable<Publication>, CsvConvertible {
    private String title;
    private Year releaseDate;
    private String publisher;

    Publication(String title, int releaseDate, String publisher) {
        this.title = title;
        this.releaseDate = Year.of(releaseDate);
        this.publisher = publisher;
    }

    public void setReleaseDate(Year releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public abstract String toCsv();

    public Year getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString(){
        return title + ", " + publisher + ", " + releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(releaseDate, that.releaseDate) &&
                Objects.equals(publisher, that.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseDate, publisher);
    }

    @Override
    public int compareTo(Publication publication) {
        return title.compareToIgnoreCase(publication.title);
    }
}
