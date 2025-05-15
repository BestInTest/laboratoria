package laboratoria;

public class Main {
    public static void main(String[] args) {
        CustomList<Integer> list = new CustomList<>();
        list.add(1);
        list.add(2);
        System.out.println("Size: " + list.size());
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
    }
}