package exception;

public class IncorrectArgumentException extends RuntimeException {

    private String argument;

    public IncorrectArgumentException() {
        super();
    }

    public IncorrectArgumentException(String argument) {
        super();
        this.argument = argument;
    }

    public String getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return "Аргумент " + argument + " принимает невалидные значения!";
    }
}
