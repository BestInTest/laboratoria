import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String libPath = "/home/bowii/IdeaProjects/laboratoria/lib/";
        PlantUMLRunner.setJarPath(libPath + "plantuml-1.2025.2.jar");

        String data = "@startuml\n" +
                "Alice -> Bob: Hello Bob\n" +
                "Bob --> Alice: Hi Alice\n" +
                "@enduml";
        PlantUMLRunner.generateDiagram(data, libPath, "test");

        Person p1 = new Person("Jan", "Kowalski", null, null);
        Person p2 = new Person("Anna", "Kowalska", null, null);
        Person p3 = new Person("Marek", "Kowalski", null, null);
        p1.adopt(p2);
        p1.adopt(p3);

        PlantUMLRunner.generateDiagram(p1.getUml(), libPath, "test");
    }
}