package Model.Entity;

public class Role {
    /**
     * Id of the role id DB
     */
    private long id;
    /**
     * Type of the role
     */
    protected String type;

    public Role() {
    }

    public Role(long id, String type) {
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Role{" +
                "type='" + type + '\'' +
                '}';
    }
}
