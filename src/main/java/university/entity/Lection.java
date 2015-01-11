package university.entity;

public class Lection {

    private long id;
    private String name;
    private User lecture;
    private Subject subject;
    private String text;

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

    public User getLecture() {
        return lecture;
    }

    public void setLecture(User lecture) {
        this.lecture = lecture;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lection lection = (Lection) o;

        if (id != lection.id) return false;
        if (lecture != null ? !lecture.equals(lection.lecture) : lection.lecture != null) return false;
        if (name != null ? !name.equals(lection.name) : lection.name != null) return false;
        if (subject != null ? !subject.equals(lection.subject) : lection.subject != null) return false;
        if (text != null ? !text.equals(lection.text) : lection.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lecture != null ? lecture.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Lection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lecture=" + lecture +
                ", subject=" + subject +
                ", text='" + text + '\'' +
                '}';
    }
}
