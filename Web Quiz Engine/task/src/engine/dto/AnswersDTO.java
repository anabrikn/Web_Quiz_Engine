package engine.dto;

import java.util.Set;
import java.util.TreeSet;

public class AnswersDTO {
    private Set<Integer> answers = new TreeSet<>();

    public AnswersDTO() {

    }

    public AnswersDTO(TreeSet<Integer> answers) {
        this.answers = answers == null ? new TreeSet<>() : answers;
    }

    public Set<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Integer> answers) {
        this.answers = answers == null ? new TreeSet<>() : answers;
    }
}
