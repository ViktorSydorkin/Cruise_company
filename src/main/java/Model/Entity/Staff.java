package Model.Entity;

public class Staff {
    /**
     * Id of staff in DB
     */
    private long id;
    /**
     * Staff's name
     */
    private String name;
    /**
     * Staff's surname
     */
    private String surname;
    /**
     * Staff's post
     */
    private String post;
    /**
     * Liner the staff works on
     *
     * @see Liner
     */
    private Liner liner;

    public Staff() {
    }

    public Staff(long id, String name, String surname, String post, Liner liner) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.post = post;
        this.liner = liner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Liner getLiner() {
        return liner;
    }

    public void setLiner(Liner liner) {
        this.liner = liner;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", post='" + post + '\'' +
                ", liner=" + liner +
                '}';
    }
}
