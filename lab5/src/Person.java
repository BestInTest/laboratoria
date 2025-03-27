import java.time.LocalDate;
import java.util.*;

public class Person implements Comparable<Person> {

    public String name;
    public String surName;
    private LocalDate birthDate;
    private Set<Person> children = new HashSet<>();

    public Person(String name, String surName, LocalDate birthDate) {
        this.name = name;
        this.surName = surName;
        this.birthDate = birthDate;
    }

    public boolean adopt(Person child) {
        return this.children.add(child);
    }

    public Person getYoungestChild() {
        if (children == null || children.isEmpty()) {
            return null;
        }

        Person youngest = null;
        for (Person child : children) {
            if (youngest == null) {
                youngest = child;
                continue;
            }

            if (child.compareTo(youngest) > 0) {
                youngest = child;
            }
            /*
            if (child.birthDate.isAfter(youngest.birthDate)) {
                youngest = child;
            }*/
        }
        return youngest;
    }

    @Override
    public int compareTo(Person person) {
        return this.birthDate.compareTo(person.birthDate);
        /*
        if (this.birthDate.isBefore(person.birthDate)) {
            return -1;
        } else if (this.birthDate.isAfter(person.birthDate)) {
            return 1;
        }*/
    }

    public List<Person> getChildren() {
        List<Person> sortedChildren = new ArrayList<>(children);
        //sortedChildren.sort(Person::compareTo);
        Collections.sort(sortedChildren);
        return sortedChildren;
    }
}
