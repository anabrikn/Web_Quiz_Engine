package engine.dataTransferObject;

public class Result {
    private boolean success;
    private String feedback;


    public Result(boolean success, String feedback) {
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

    public static Result successResult() {
        return new Result(true, "Congratulations, you're right!");
    }

    public static Result wrongResult() {
        return new Result(false, "Wrong answer! Please, try again.");
    }
}