package laboratoria;

import java.util.*;
import java.util.stream.Stream;

public class CustomList<T> extends AbstractList<T> {

    private Node head; // początek listy
    private Node tail; // koniec listy

    public CustomList() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public int size() {
        int counter = 0;
        Node currentNode = head;
        while (currentNode != null) {
            counter++;
            currentNode = currentNode.next;
        }
        return counter;
    }

    @Override
    public T get(int index) {
        if (head == null) {
            throw new NoSuchElementException();
        }

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        int counter = 0;
        Node currentNode = head;
        while (counter < index) {
            counter++;
            currentNode = currentNode.next;
            if (currentNode == null) {
                throw new NoSuchElementException();
            }
        }

        return currentNode.value;
    }

    public boolean add(T element) {
        addLast(element);
        return true;
    }

    //public CustomList(ArrayList<T> arr) {
    //    head = arr.get(0);
    //    tail = arr.get(arr.size() - 1);
    //}

    void addLast(T value) {
        Node lastNode = new Node(value, null);
        if (head == null) {
            head = lastNode;
        } else {
            tail.next = lastNode;
        }
        tail = lastNode;
    }

    public T getLast() {
        return this.tail.value;
    }

    public T getFirst() {
        return this.head.value;
    }

    void addFirst(T value) {
        Node firstNode = new Node(value, this.head);
        if (head == null) {
            tail = firstNode;
        }
        this.head = firstNode;

    }

    /*
    Nadpisz i zaprogramuj metody:

    Iterator<T> iterator() - zwracającą iterator do listy. Zdefiniuj w niej iterator,
    Stream<T> stream() - zwracającą strumień z zawartością listy.
     */

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = currentNode.value;
                currentNode = currentNode.next;
                return value;
            }
        };
    }

    public static List<Object> getSimilar(List<Object> list, Object type) {
        List<Object> result = new ArrayList<>();
        for (Object element : list) {
            if (element.getClass().equals(type.getClass())) {
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node currentNode = head;
        while (currentNode != null) {
            sb.append(currentNode.value).append(", ");
            currentNode = currentNode.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private class Node {
        T value;
        Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
