package university.entity;

import java.util.Date;

public class LectionComment {

    private long id;
    private long lectionId;
    private User user;
    private Date creationDate;
    private String text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLectionId() {
        return lectionId;
    }

    public void setLectionId(long lectionId) {
        this.lectionId = lectionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "LectionComment{" +
                "id=" + id +
                ", lectionId=" + lectionId +
                ", user=" + user +
                ", creationDate=" + creationDate +
                ", text='" + text + '\'' +
                '}';
    }
}
