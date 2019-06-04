package model;

abstract public class Product {
    public float price;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    abstract public String getType();
    abstract public String getName();

    public void setPrice(float price) {
        this.price = price;
    }

    public abstract void print();

}
