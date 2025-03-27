import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Family {

    private Map<String, List<Person>> family = new HashMap<>();

    public void add(Person person) {
        String fullName = person.name + " " + person.surName;
        if (family.containsKey(fullName)) {
            family.get(fullName).add(person);
        } else {
            List<Person> personList = new ArrayList<>();
            personList.add(person);
            family.put(fullName, personList);
        }
    }

    public List<Person> get(String fullName) {
        List<Person> sorted = family.get(fullName);
        sorted.sort(Person::compareTo);
        return sorted;
    }

    /*Wersja dla unikalnych nazw osób

    private Map<String, Person> family = new HashMap<>();

    public void add(Person person) {
        family.put(person.name + " " + person.surName, person);
    }

    public void add(Person... personList) {
        for (Person person : personList) {
            add(person);
        }
    }

    //public void add(List<Person> personList) {
    //    for (Person person : personList) {
    //        add(person);
    //    }
    //}

    public Person get(String nameAndSurName) {
        return family.get(nameAndSurName);
    }*/
}
