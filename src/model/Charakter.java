package model;

import java.util.List;

public class Charakter implements HasID{
    private Integer ID;
    private String name;
    private String herkunftsort;
    private List<Product> products;

    @Override
    public String toString() {
        return "Charakter{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", herkunftsort='" + herkunftsort + '\'' +
                ", products=" + products +
                '}';
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHerkunftsort() {
        return herkunftsort;
    }

    public void setHerkunftsort(String herkunftsort) {
        this.herkunftsort = herkunftsort;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Charakter(Integer ID, String name, String herkunftsort, List<Product> products) {
        this.ID = ID;
        this.name = name;
        this.herkunftsort = herkunftsort;
        this.products = products;
    }

    @Override
    public Integer getId() {
        return ID;
    }
}
