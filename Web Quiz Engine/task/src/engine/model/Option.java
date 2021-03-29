package engine.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "options")
public class Option implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private boolean isRight;

    @Column(nullable = false)
    Integer number;

    public Option() {
    }

    public Option(String body, boolean isRight, Integer number) {
        this.body = body;
        this.isRight = isRight;
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
