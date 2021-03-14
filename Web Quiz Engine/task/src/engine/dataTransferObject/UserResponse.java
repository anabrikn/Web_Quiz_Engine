package engine.dataTransferObject;

import java.util.TreeSet;

public class UserResponse {
    private TreeSet<Integer> answer;

    public UserResponse() {

    }

    public UserResponse(TreeSet<Integer> answer) {
        this.answer = answer == null ? new TreeSet<>(){} : answer;
    }

    public TreeSet<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(TreeSet<Integer> answer) {
        this.answer = answer == null ? new TreeSet<>(){} : answer;
    }
}
