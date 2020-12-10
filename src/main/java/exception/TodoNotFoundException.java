package exception;

public class TodoNotFoundException extends Exception {
    public TodoNotFoundException() {
        super("Todo Not Found.");
    }
}
