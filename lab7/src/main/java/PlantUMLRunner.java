import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PlantUMLRunner {
    private static String jarPath;

    public static void setJarPath(String jarPath) {
        PlantUMLRunner.jarPath = jarPath;
    }

    public static void generateDiagram(String inputData, String outputDir, String fileName) throws IOException {
        //Tworzenie folderów gdzie zapisać pliki
        new File(outputDir).mkdirs();

        //Zapisywanie inputData do pliku
        File dataFile = new File(outputDir, fileName + ".puml");
        try (FileWriter fw = new FileWriter(dataFile)) {
            System.out.println(dataFile.getAbsolutePath());
            fw.write(inputData);
            fw.flush();
        }

        String[] command = {
            "java",
            "-jar",
            jarPath,
            dataFile.getAbsolutePath()
        };

        //Runtime.getRuntime().exec(command);

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        pb.start();
    }
}
