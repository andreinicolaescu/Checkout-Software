package model;

public class Album extends Product {

    public enum albumType
    {
        Rock,
        Jazz,
        Electronic,
        Rap
    }

    String title;
    String artist;
    int song_nr;
    albumType type;

    public String getType() {
        return "ALBUM";
    }

    public String getName() {
        return this.title;
    }

    public Album(albumType type,String title, String artist, int song_nr, float price, int currentId) {
        this.title = title;
        this.artist = artist;
        this.song_nr = song_nr;
        this.type = type;
        this.setPrice(price);
        this.setId(currentId);
    }

    public void print() {
        System.out.printf("ALBUM\n" +
                "ID %d\n" +
                "Price: %f LEI\n" +
                "Type: %s\n" +
                "Artist: %s\n" +
                "Title: %s\n" +
                "Song #: %d\n", this.getId(), this.price, this.type.name(), this.artist, this.title, this.song_nr );
    }

    @Override
    public String toString() {

        return  "ID " + this.getId() + " | " +
                "ALBUM |" +
                "Price: " + this.price + "LEI | " +
                "Type: " + this.type.name() + " | " +
                "Artist: " + this.artist + " | " +
                "Title: " + this.title + " | " +
                "Song #: " + this.song_nr;
    }
}
