package model;

public class Produce extends Product {

    public enum produceType
    {
        Vegetable,
        Fruit
    }

    String countryOfOrigin;
    String name;
    produceType type;

    public String getType() {
        return "PRODUCE";
    }

    public String getName() {
        return this.name;
    }

    public Produce(String countryOfOrigin, String name, produceType type, float price, int currentId) {
        this.countryOfOrigin = countryOfOrigin;
        this.name = name;
        this.type = type;
        this.setPrice(price);
        this.setId(currentId);
    }

    public void print() {
        System.out.printf("PRODUCE\n" +
                "ID %d\n" +
                "Price: %f LEI\n" +
                "Type: %s\n" +
                "Name: %s\n" +
                "Country of Origin: %s\n", this.getId(), this.price, this.type.name(), this.name, this.countryOfOrigin);
    }

    @Override
    public String toString() {

        return  "ID " + this.getId() + " | " +
                "PRODUCE | " +
                "Price: " + this.price + "LEI | " +
                "Type: " + this.type.name() + " | " +
                "Name: " + this.name + " | " +
                "Country of Origin: " + this.countryOfOrigin;
    }

}
