package engine.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Quiz {

    private static Integer count = 1;
    private int id;

    @NotNull(message = "Enter title, please")
    @NotEmpty(message = "Title must not be empty")
    private String title;

    @NotNull(message = "Enter text, please")
    @NotEmpty(message = "Text must not be empty")
    private String text;

    @Size(min = 2, message = "Need more than 1 options.")
    private List<String> options = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    TreeSet<Integer> answer = new TreeSet<>();


    public Quiz() {
        this.id = getNextId();
    }

    public Quiz(String title, String text, List<String> options, TreeSet<Integer> answer) {
        this.id = getNextId();
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public static int getNextId() {
        return count++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    //@JsonIgnore
    public TreeSet<Integer> getAnswer() {
        return answer;
    }

    //@JsonProperty()
    public void setAnswer(TreeSet<Integer> answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}