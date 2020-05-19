public class InvalidAssignmentException extends Exception {
    public InvalidAssignmentException(String var) {
        super("No valid assignment for variable:' " + var + "'");
    }
}
