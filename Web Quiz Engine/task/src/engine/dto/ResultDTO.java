package engine.dto;

public class ResultDTO {
    private boolean success;
    private String feedback;


    public ResultDTO(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public static ResultDTO successResult() {
        return new ResultDTO(true, "Congratulations, you're right!");
    }

    public static ResultDTO wrongResult() {
        return new ResultDTO(false, "Wrong answer! Please, try again.");
    }
}