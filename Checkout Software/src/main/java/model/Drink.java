package model;

public class Drink extends Product {

    public enum drinkType
    {
        Fizzy,
        Alcoholic,
        Still
    }

    String brand;
    int quantity;
    drinkType type;

    public String getType() {
        return "DRINK";
    }

    public String getName() {
        return this.brand;
    }

    public Drink(String brand, int quantity, drinkType type, float price, int currentId) {
        this.brand = brand;
        this.quantity = quantity;
        this.type = type;
        this.setPrice(price);
        this.setId(currentId);
    }

    public void print() {
        System.out.printf("DRINK\n" +
                "ID %d\n" +
                "Price: %f LEI\n" +
                "Type: %s\n" +
                "Brand: %s\n" +
                "Quantity: %d ml\n", this.getId(), this.price, this.type.name(), this.brand, this.quantity);
    }

    @Override
    public String toString() {

        return  "ID " + this.getId() + " | " +
                "DRINK | " +
                "Price: " + this.price + "LEI | " +
                "Type: " + this.type.name() + " | " +
                "Brand: " + this.brand + " | " +
                "Quantity: " + this.quantity;
    }

}
