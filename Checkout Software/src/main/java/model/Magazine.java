package model;

public class Magazine extends Product {

    public enum magazineType
    {
        Gaming,
        Men,
        Women
    }

    String title;
    magazineType type;

    public String getType() {
        return "MAGAZINE";
    }

    public String getName() {
        return this.title;
    }

    public Magazine(String title, magazineType type, float price, int currentId) {
        this.title = title;
        this.type = type;
        this.setPrice(price);
        this.setId(currentId);
    }

    public void print() {
        System.out.printf("MAGAZINE\n" +
                "ID %d\n" +
                "Price: %f LEI\n" +
                "Type: %s\n" +
                "Title: %s\n", this.getId(), this.price, this.type.name(),this.title);
    }

    @Override
    public String toString() {

        return  "ID " + this.getId() + " | " +
                "MAGAZINE | " +
                "Price: " + this.price + "LEI | " +
                "Type: " + this.type.name() + " | " +
                "Title: " + this.title;
    }

}
