package Model.Entity;

public class User {
    /**
     * Id of user in DB
     */
    private long id;
    /**
     * User's name
     */
    private String name;
    /**
     * User's surname
     */
    private String surname;
    /**
     * User's email address
     */
    private String email;
    /**
     * User's password. In DB - is encrypted
     */
    private String password;
    /**
     * User's role
     *
     * @see Role
     */
    private Role role;
    /**
     * User's preferred language
     *
     * @see Language
     */
    private Language language;

    public User() {
    }

    public User(long id, String name, String surname, String email, String password, Role role, Language language) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.language = language;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", language=" + language +
                '}';
    }
}
