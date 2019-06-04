package model;

public class Book extends Product {

    public enum bookType
    {
        SF,
        History,
        Literature
    }

    String author;
    String title;
    int pages;
    bookType type;

    public String getType() {
        return "BOOK";
    }

    public String getName() {
        return this.title;
    }

    public Book(bookType type, String author, String title, float price, int currentId) {
        this.author = author;
        this.title = title;
        this.type = type;
        this.setPrice(price);
        this.setId(currentId);
    }

    public void print() {
        System.out.printf("BOOK\n" +
                "ID %d\n" +
                "Price: %f LEI\n" +
                "Type: %s\n" +
                "Author: %s\n" +
                "Title: %s\n", this.getId(), this.price, this.type.name(), this.author, this.title);

    }

    @Override
    public String toString() {

        return  "ID " + this.getId() + " | " +
                "BOOK | " +
                "Price: " + this.price + "LEI | " +
                "Type: " + this.type.name() + " | " +
                "Artist: " + this.author + " | " +
                "Title: " + this.title;
    }
}
