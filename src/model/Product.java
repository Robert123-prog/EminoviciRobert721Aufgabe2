package model;

public class Product implements HasID{
    private Integer ID;
    private String name;
    private Integer price;
    private String herkunftsregion;

    public Product(Integer ID, String name, Integer price, String herkunftsregion) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.herkunftsregion = herkunftsregion;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", herkunftsregion='" + herkunftsregion + '\'' +
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getHerkunftsregion() {
        return herkunftsregion;
    }

    public void setHerkunftsregion(String herkunftsregion) {
        this.herkunftsregion = herkunftsregion;
    }

    @Override
    public Integer getId() {
        return ID;
    }
}
