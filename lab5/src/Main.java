import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Andrzej", "Nowak", LocalDate.of(2000, 10, 10)));
        persons.add(new Person("Jan", "Kowalski", LocalDate.of(1990, 5, 5)));
        persons.add(new Person("Mike", "Kowalski", LocalDate.of(2000, 12, 3)));

        persons.get(0).adopt(persons.get(1));
        persons.get(0).adopt(persons.get(2));
        System.out.println(persons.get(0).getYoungestChild().name);
    }
}