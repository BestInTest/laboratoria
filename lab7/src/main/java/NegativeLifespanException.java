import java.time.LocalDate;

public class NegativeLifespanException extends Exception {

    public NegativeLifespanException(LocalDate birthDate, LocalDate deathDate) {
        super("Negative lifespan: " + birthDate + " - " + deathDate);
    }

    /*
    public NegativeLifespanException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeLifespanException(Throwable cause) {
        super(cause);
    }*/

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
