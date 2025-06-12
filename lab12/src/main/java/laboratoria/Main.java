package laboratoria;

public class Main {
    public static void main(String[] args) {
        ImageHandler handler = new ImageHandler();
        //handler.saveImage("obrazKopia.jpg");

        // Wczytanie obrazu
        handler.loadImage("lab12/obraz.jpg");


        // Zwiększenie jasności obrazu o 100
        long startTimeParallel = System.currentTimeMillis();
        //handler.adjustBrightness(100);
        long endTimeParallel = System.currentTimeMillis();
        System.out.println("Czas wykonania jeden wątek: " + (endTimeParallel - startTimeParallel) + " ms");

        handler.saveImage("lab12/obrazKopia.jpg");


        int prcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("\nLiczba dostępnych rdzeni procesora: " + prcessors);

        long startTimeMultiThreaded = System.currentTimeMillis();
        // Zwiększenie jasności obrazu o 100 w wielu wątkach
        //handler.adjustBrightnessMultithread(100, prcessors);
        handler.adjustBrightnessParallel(100, prcessors);
        System.out.println("Czas wykonania dla wielu wątków: " + (System.currentTimeMillis() - startTimeMultiThreaded) + " ms");
        handler.saveImage("lab12/obrazKopia.jpg");
    }
}