public class ArrayDeque<T> {

    private static final int DEFAULT_SIZE = 8;
    private int size;
    private int capacity;
    private int begin;
    private int end;
    private T[] array;

    public ArrayDeque() {
        this.size = 0;
        this.capacity = DEFAULT_SIZE;
        this.begin = 0;
        this.end = 1;

        this.array = (T[]) new Object[DEFAULT_SIZE];
    }

    // public ArrayDeque(ArrayDeque other) {
    // this.size = 0;
    // this.begin = 0;
    // this.end = 1;
    // this.capacity = other.capacity;
    // this.array = (T[]) new Object[this.capacity];
    // for (int i = 0; i < other.size(); ++i) {
    // this.addLast((T) other.get(i));
    // }
    // }

    private void resize(int capacity) {
        if (capacity < DEFAULT_SIZE) {
            capacity = DEFAULT_SIZE;
        }
        if (this.capacity == capacity) {
            return;
        }
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < this.size; ++i) {
            newArray[i] = this.array[(this.begin + i + 1) % this.capacity];
        }
        this.capacity = capacity;
        this.begin = this.capacity - 1;
        this.end = this.size;
        this.array = newArray;
    }

    public void addFirst(T item) {
        if (this.size == this.capacity) {
            resize((int) (this.size * 2));
        }
        this.size++;
        this.array[this.begin] = item;
        this.end = (this.begin + this.size) % this.capacity;
        this.begin = (this.begin + this.capacity - 1) % this.capacity;
    }

    public void addLast(T item) {
        if (this.size == this.capacity) {
            resize((int) (this.capacity * 2));
        }
        this.array[this.end] = item;
        this.end = (this.end + 1) % this.capacity;
        this.size++;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        for (int i = 0; i < this.size; ++i) {
            System.out.print(this.get(i));
            System.out.print(" ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int realBegin = (this.begin + 1) % this.capacity;
        T result = this.array[realBegin];
        this.array[realBegin] = null;
        this.begin = realBegin;
        this.size--;
        if (((double) this.size / (double) (this.capacity)) < 0.25) {
            resize(this.capacity / 2);
        }
        return result;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int realEnd = (this.end + this.capacity - 1) % this.capacity;
        T result = this.array[realEnd];
        this.array[realEnd] = null;
        this.end = realEnd;
        this.size--;
        if (((double) this.size / (double) (this.capacity)) < 0.25) {
            resize(this.capacity / 2);
        }
        return result;
    }

    public T get(int index) {
        if (index >= this.size) {
            return null;
        }
        return this.array[(this.begin + index + 1) % this.capacity];
    }
}
