package proj1b;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node<Type> {
        Type item;
        Node<Type> prev;
        Node<Type> next;
    };

    private Node<T> node;
    private int size;

    public LinkedListDeque() {
        this.node = new Node<T>();
        this.node.prev = node;
        this.node.next = node;
        this.size = 0;
    }

    // public LinkedListDeque(LinkedListDeque other) {
    // this.node = new Node<T>();
    // this.node.prev = node;
    // this.node.next = node;
    // for (int i = 0; i < other.size(); ++i) {
    // this.addFirst((T) other.get(i));
    // }
    // }

    public void addFirst(T item) {
        Node<T> newNode = new Node<>();
        newNode.prev = this.node;
        newNode.next = this.node.next;
        newNode.item = item;
        this.node.next.prev = newNode;
        this.node.next = newNode;
        ++size;
    }

    public void addLast(T item) {
        Node<T> newNode = new Node<>();
        newNode.prev = this.node.prev;
        newNode.next = this.node;
        this.node.prev.next = newNode;
        this.node.prev = newNode;
        newNode.item = item;
        ++size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node<T> p = this.node.next;
        for (int i = 0; i < size; ++i) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T result = this.node.next.item;
        this.node.next = this.node.next.next;
        this.node.next.prev = this.node;
        --size;
        return result;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T result = this.node.prev.item;
        --size;
        this.node.prev = this.node.prev.prev;
        this.node.prev.next = this.node;
        return result;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node<T> p = this.node.next;
        for (int i = 0; i < index; ++i) {
            p = p.next;
        }
        return p.item;
    }

    private T getRecursive(Node<T> start, int index) {
        if (index == 0) {
            return start.item;
        }
        return getRecursive(start.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }

        return getRecursive(this.node.next, index);
    }
}
