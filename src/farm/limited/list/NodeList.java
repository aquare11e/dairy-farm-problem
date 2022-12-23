package farm.limited.list;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class NodeList<T> {
    private int size;
    private Node<T> root;

    public NodeList() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public void add(T value) {
        Node<T> newNode =  new Node<>(value);

        if (size == 0) {
            root = newNode;
            size++;
            return;
        }

        Node<T> temp = root;
        while(temp.getNext() != null) {
            temp = temp.getNext();
        }

        temp.setNext(newNode);
        size++;
    }

    public void addAll(NodeList<T> from) {
        if (from.root == null) {
            return;
        }

        Node<T> temp = from.root;
        while (temp != null) {
            add(temp.getValue());
            temp = temp.getNext();
        }
    }

    public T remove(Predicate<T> predicate) {
        if (root == null) {
            return null;
        }

        if (predicate.test(root.getValue())) {
            T valueToRemove = root.getValue();
            root = root.getNext();
            size--;
            return valueToRemove;
        }

        Node<T> temp = root;
        while(temp.getNext() != null && !predicate.test(temp.getNext().getValue())) {
            temp = temp.getNext();
        }

        if (temp.getNext() == null) {
            return null;
        }

        T valueToRemove = temp.getNext().getValue();
        temp.setNext(temp.getNext().getNext());
        size--;

        return valueToRemove;
    }

    public T find(Predicate<T> predicate) {
        if (root == null) {
            return null;
        }

        if (predicate.test(root.getValue())) {
            return root.getValue();
        }

        Node<T> temp = root;
        while(temp.getNext() != null && !predicate.test(temp.getNext().getValue())) {
            temp = temp.getNext();
        }

        if (temp.getNext() == null) {
            return null;
        }

        return temp.getNext().getValue();
    }

    public void each(Consumer<T> action) {
        if (root == null) {
            return;
        }

        Node<T> temp = root;
        while (temp != null) {
            action.accept(temp.getValue());
            temp = temp.getNext();
        }
    }
}