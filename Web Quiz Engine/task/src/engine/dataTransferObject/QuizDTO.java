package engine.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class QuizDTO {

    private long id;

    @NotNull(message = "Enter title, please")
    @NotEmpty(message = "Title must not be empty")
    private String title;

    @NotNull(message = "Enter text, please")
    @NotEmpty(message = "Text must not be empty")
    private String text;

    @Size(min = 2, message = "Need more than 1 options.")
    private List<String> options = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Integer> answer = new TreeSet<>();

    public QuizDTO() {
    }

    public QuizDTO(String title, String text, List<String> options, Set<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer == null ? new TreeSet<>() : answer;
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
    public Set<Integer> getAnswer() {
        return answer;
    }

    //@JsonProperty()
    public void setAnswer(Set<Integer> answer) {
        this.answer = answer;
    }

}