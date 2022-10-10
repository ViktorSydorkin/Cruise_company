package Model.Entity;

public class Company {
    /**
     * Id of the company id DB
     */
    private long id;
    /**
     * Company's title
     */
    private String title;

    public Company() {
    }

    public Company(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Company{" +
                "title='" + title + '\'' +
                '}';
    }
}
