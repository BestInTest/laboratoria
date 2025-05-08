import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class Person implements Comparable<Person>, Serializable {

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
                    Person p = fromCsvLine(line);
                    if (alreadyExists(persons, p)) {
                        throw new AmbiguousPersonException(p);
                    }
                    persons.add(p);

                    /* Dziwne to zadanie
                    String dane[] = line.split(",");
                    if (dane[dane.length - 2] != null) {
                        if (!dane[3].isEmpty()) {
                            System.err.println(1);
                        }
                    }*/
                }
                //line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (NegativeLifespanException | AmbiguousPersonException | IOException e) {
            System.err.println(e.getMessage());
        }
        //Zmodyfikuj metodę fromCsv(), by w obiektach rodziców ustawiała referencje do obiektów dzieci.

        return persons;
    }

    private static boolean alreadyExists(List<Person> persons, Person newPerson) {
        for (Person person : persons) {
            if (person.name.equals(newPerson.name) && person.surName.equals(newPerson.surName)) {
                return true;
            }
        }
        return false;
    }

    public static void toBinaryFile(File file, List<Person> persons) {
        if (!file.exists()) {
            System.err.println("File not found: " + file.getAbsolutePath());
            return;
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(persons);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Person> fromBinaryFile(File file) {
        if (!file.exists()) {
            System.err.println("File not found: " + file.getAbsolutePath());
            return null;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Person>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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

    public String getUml() {
        Function<String, String> f1 = (name) -> {
            return "object \"" + name + "\" as " + name.replaceAll(" ", "_") + "\n";
        };

        Function<String[], String> relation = (name) -> {
            return name[0].replaceAll(" ", "_") + " <-- " + name[1].replaceAll(" ", "_") + "\n";
        };

        StringBuilder f1Sb = new StringBuilder();
        f1Sb.append(f1.apply(name + " " + surName));

        String[] relationGraph = new String[2];
        relationGraph[0] = name + surName;

        StringBuilder relationSb = new StringBuilder();
        for (Person child : children) {
            f1Sb.append(f1.apply(child.name + " " + child.surName));
            relationGraph[1] = child.name + " " + child.surName;
            relationSb.append(relation.apply(relationGraph));
        }

        return String.format("@startuml\n %s \n %s \n @enduml", f1Sb, relationSb);
    }

    public static String getUmlTree(List<Person> people) {
        StringBuilder f1Sb = new StringBuilder();
        StringBuilder relationSb = new StringBuilder();

        Set<Person> addedPerson = new HashSet<>();

        Consumer<Person> addPerson = (person) -> {
            Function<String, String> f1 = (name) -> {
                return "object \"" + name + "\" as " + name.replaceAll(" ", "_") + "\n";
            };

            Function<String[], String> relation = (name) -> {
                return name[0].replaceAll(" ", "_") + " <-- " + name[1].replaceAll(" ", "_") + "\n";
            };

            if (addedPerson.contains(person)) {
                return;
            }
            addedPerson.add(person);
            /*
            f1Sb.append(f1.apply(person.name + " " + person.surName));
            String[] relationGraph = new String[2];
            relationGraph[0] = person.name + " " + person.surName;

            for (Person child : person.children) {
                f1Sb.append(f1.apply(child.name + " " + child.surName));
                relationGraph[1] = child.name + " " + child.surName;
                relationSb.append(relation.apply(relationGraph));
                addPerson.accept(child);
            }*/
        };

        people.forEach(addPerson);
        return String.format("@startuml\n %s \n %s \n @enduml", f1Sb, relationSb);
    }
}
