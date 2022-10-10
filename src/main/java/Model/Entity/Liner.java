package Model.Entity;

import java.io.InputStream;

public class Liner {
    /**
     * Id of the liner id DB
     */
    private long id;
    /**
     * Liner's capacity
     */
    private int capacity;
    /**
     * Liner's name
     */
    private String name;
    /**
     * Amount of liner's daecks
     */
    private int deck_amount;
    /**
     * Photo of the liner
     */
    private InputStream liner_photo;

    public Liner() {
    }

    public Liner(long id, int capacity, String name, int deck_amount, InputStream liner_photo, Company company) {
        this.id = id;
        this.capacity = capacity;
        this.name = name;
        this.deck_amount = deck_amount;
        this.liner_photo = liner_photo;
        this.company = company;
    }

    private Company company;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeck_amount() {
        return deck_amount;
    }

    public void setDeck_amount(int deck_amount) {
        this.deck_amount = deck_amount;
    }

    public InputStream getLiner_photo() {
        return liner_photo;
    }

    public void setLiner_photo(InputStream liner_photo) {
        this.liner_photo = liner_photo;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Liner{" +
                "capacity=" + capacity +
                ", name='" + name + '\'' +
                ", deck_amount=" + deck_amount +
                ", company=" + company +
                '}';
    }
}
