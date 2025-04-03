import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Person implements Comparable<Person> {

    public String name;
    public String surName;
    public LocalDate birthDate;
    public LocalDate deathDate;
    private Set<Person> children = new HashSet<>();

    public Person(String name, String surName, LocalDate birthDate, LocalDate deathDate) {
        this.name = name;
        this.surName = surName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
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

    public static Person fromCsvLine(String line) throws NegativeLifespanException {
        String[] spl = line.split(",");

        String nameAndSurname = spl[0];
        String name = nameAndSurname.split(" ")[0];
        String surname = nameAndSurname.split(" ")[1];

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthDate = LocalDate.parse(spl[1], dtf);
        LocalDate deathDate = null;
        if (!spl[2].isEmpty()) {
            deathDate = LocalDate.parse(spl[2], dtf);
        }

        // Sprawdzenie czy data urodzenia jest późniejsza od daty śmierci
        if (deathDate != null && birthDate.isAfter(deathDate)) {
            throw new NegativeLifespanException(birthDate, deathDate);
        }

        return new Person(name, surname, birthDate, deathDate);
    }

    public static List<Person> fromCsv(String path) {
        List<Person> persons = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            //String line = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("imię i nazwisko")) { // Pominięcie nagłówka
                    persons.add(fromCsvLine(line));
                }
                //line = br.readLine();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return persons;
    }

    /*
    public static List<Person> fromCsv(String path) {
        File f = new File(path);
        List<Person> persons = new ArrayList<>();

        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.startsWith("imię i nazwisko")) { // Pominięcie nagłówka
                    persons.add(fromCsvLine(line));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return persons;
    }*/

    @Override
    public String toString() {
        return name + " " + surName + ", urodzony: " + birthDate + ", zmarł: " + deathDate;
    }
}
