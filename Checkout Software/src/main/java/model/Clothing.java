package model;

public class Clothing extends Product {

    public enum clothingType
    {
        Men,
        Women,
        Kids
    }

    public enum clothingPart
    {
        Upper,
        Lower,
        Head
    }

    String brand;
    String name;
    clothingPart part;
    clothingType type;

    public String getType() {
        return "CLOTHING";
    }

    public String getName() {
        return this.name + this.brand;
    }

    public Clothing(String brand, String name, clothingPart part, clothingType type, float price, int currentId) {
        this.brand = brand;
        this.name = name;
        this.part = part;
        this.type = type;
        this.setPrice(price);
        this.setId(currentId);
    }

    public void print() {
        System.out.printf("CLOTHING\n" +
                "ID %d\n" +
                "Price: %f LEI\n" +
                "Type: %s\n" +
                "Part: %s\n" +
                "Name: %s\n" +
                "Brand: %s\n", this.getId(), this.price, this.type.name(), this.part.name(), this.name, this.brand );
    }

    @Override
    public String toString() {

        return  "ID " + this.getId() + " | " +
                "CLOTHING | " +
                "Price: " + this.price + "LEI | " +
                "Type: " + this.type.name() + " | " +
                "Part: " + this.part.name() + " | " +
                "Name: " + this.name + " | " +
                "Brand: " + this.brand;
    }

}
