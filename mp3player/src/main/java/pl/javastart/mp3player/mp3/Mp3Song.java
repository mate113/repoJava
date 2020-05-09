package pl.javastart.mp3player.mp3;

public class Mp3Song {
    private String title;
    private String author;
    private String album;
    private String path;

    public Mp3Song(String title, String author, String album, String path) {
        this.title = title;
        this.author = author;
        this.album = album;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Mp3Song [title=" + title + ", author=" + author + ", album=" + album + "]";
    }
}
