package deque;

import java.util.Iterator;
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size, nextFirst, nextLast;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private void resize() {
        int capacity;
        if (items.length == size) {
            capacity = items.length * 2;
        } else if ((items.length >= 16) && (size <= 0.25 * items.length)) {
            capacity = items.length / 2;
        } else {
            return;
        }
        T[] newItems = (T[]) new Object[capacity];
        int newFirst = capacity / 4;
        if (nextFirst + size > items.length) {
            int firstTrunk = items.length - nextFirst - 1;
            System.arraycopy(items, nextFirst + 1, newItems, newFirst, firstTrunk);
            System.arraycopy(items, 0, newItems, newFirst + firstTrunk, items.length - firstTrunk);
            nextFirst = newFirst - 1;
            nextLast = newFirst + size;
        } else {
            System.arraycopy(items, nextFirst, newItems, newFirst, size + 1);
            nextFirst = newFirst;
            nextLast = newFirst + size + 1;
        }
        items = newItems;
    }

    @Override
    public void addFirst(T item) {
        if (item == null) {
            throw new IllegalArgumentException("can't add null");
        }

        resize();

        items[nextFirst] = item;

        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (item == null) {
            throw new IllegalArgumentException("can't add null");
        }

        resize();

        items[nextLast] = item;

        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        StringBuilder returnSB = new StringBuilder("{");
        for (int i = 0; i < size - 1; i += 1) {
            returnSB.append(get(i).toString());
            returnSB.append(", ");
        }
        returnSB.append(get(size - 1));
        returnSB.append("}");
        System.out.println(returnSB);
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        resize();
        nextFirst = (nextFirst == items.length - 1) ? 0 : ++nextFirst;
        T returnItem = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return returnItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        resize();
        nextLast = (nextLast == 0) ? (items.length - 1) : --nextLast;
        T returnItem = items[nextLast];
        items[nextLast] = null;
        size--;
        return returnItem;
    }

    @Override
    public T get(int index) {
        if (nextFirst + index + 1 >= items.length) {
            return items[index - (items.length - nextFirst) + 1];
        }
        return items[nextFirst + 1 + index];
    }

    private class DequeIterator implements Iterator<T> {
        private int index, cnt;

        DequeIterator() {
            index = nextFirst + 1;
        }

        public boolean hasNext() {
            return cnt < size;
        }

        public T next() {
            T returnItem = items[index];
            index = (index == items.length - 1) ? 0 : ++index;
            ++cnt;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (other.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; ++i) {
            if (this.get(i) != other.get(i)) {
                return false;
            }
        }
        return true;
    }
}
