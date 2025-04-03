import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Person.fromCsv("family.csv");

        for (Person person : persons) {
            System.out.println(person.toString());
        }
    }
}