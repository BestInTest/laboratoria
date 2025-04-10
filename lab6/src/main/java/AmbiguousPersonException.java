public class AmbiguousPersonException extends RuntimeException {

    public AmbiguousPersonException(Person p) {
        super("Person named " + p.name + " " + p.surName + " already exists.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
