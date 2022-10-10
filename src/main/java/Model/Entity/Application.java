package Model.Entity;

import Model.Entity.Enums.Approved;
import Model.Entity.Enums.Closed;
import Model.Entity.Enums.Ended;
import Model.Entity.Enums.Paid;

import java.io.InputStream;

public class Application {
    /**
     * Id of application in the DB
     */
    private long id;
    /**
     * Photo of the user's passport
     */
    private InputStream pass_photo;
    /**
     * State of application (Yes/No)
     *
     * @see Approved
     */
    private Approved approved;
    /**
     * State of application (Closed/Opened)
     *
     * @see Closed
     */
    private Closed closed;
    /**
     * State of application (Paid/Unpaid)
     *
     * @see Paid
     */
    private Paid paid;
    /**
     * State of application (Yes/No)
     *
     * @see Ended
     */
    private Ended ended;
    /**
     * User that applied for the cruise
     *
     * @see User
     */
    private User user;
    /**
     * Cruise the user applied for
     *
     * @see Cruise
     */
    private Cruise cruise;

    public Application() {
    }

    public Application(long id, InputStream pass_photo, Approved approved, Closed closed, Paid paid, Ended ended, User user, Cruise cruise) {
        this.id = id;
        this.pass_photo = pass_photo;
        this.approved = approved;
        this.closed = closed;
        this.paid = paid;
        this.ended = ended;
        this.user = user;
        this.cruise = cruise;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public InputStream getPass_photo() {
        return pass_photo;
    }

    public void setPass_photo(InputStream pass_photo) {
        this.pass_photo = pass_photo;
    }

    public Approved getApproved() {
        return approved;
    }

    public void setApproved(Approved approved) {
        this.approved = approved;
    }

    public Closed getClosed() {
        return closed;
    }

    public void setClosed(Closed closed) {
        this.closed = closed;
    }

    public Paid getPaid() {
        return paid;
    }

    public void setPaid(Paid paid) {
        this.paid = paid;
    }

    public Ended getEnded() {
        return ended;
    }

    public void setEnded(Ended ended) {
        this.ended = ended;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cruise getCruise() {
        return cruise;
    }

    public void setCruise(Cruise cruise) {
        this.cruise = cruise;
    }

    @Override
    public String toString() {
        return "Application{" +
                "approved=" + approved +
                ", closed=" + closed +
                ", paid=" + paid +
                ", ended=" + ended +
                ", user=" + user +
                ", cruise=" + cruise +
                '}';
    }
}
