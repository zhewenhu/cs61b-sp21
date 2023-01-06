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

    private void expand() {
        int capacity = size * 2;
        T[] newItems = (T[]) new Object[capacity];
        int newFirst = capacity / 2 - size / 2;
        if (nextFirst + items.length > size) {
            int firstTrunk = items.length - nextFirst - 1;
            System.arraycopy(items, nextFirst + 1, newItems, newFirst, firstTrunk);
            System.arraycopy(items, 0, newItems, newFirst + firstTrunk, size - firstTrunk);
        } else {
            System.arraycopy(items, nextFirst, newItems, newFirst, size);
        }
        nextFirst = newFirst - 1;
        nextLast = newFirst + size;
        items = newItems;
    }

    private void shrink() {

    }

    @Override
    public void addFirst(T item) {
        if (item == null) {
            throw new IllegalArgumentException("can't add null");
        }

        if (items.length == size) {
            expand();
        }

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

        if (items.length == size) {
            expand();
        }

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
        nextLast = (nextLast == 0) ? (items.length - 1) : --nextLast;
        T returnItem = items[nextLast];
        items[nextLast] = null;
        size--;
        return returnItem;
    }

    @Override
    public T get(int index) {
        if (nextFirst + size > items.length) {
            return items[index - (items.length - nextFirst)];
        }
        return items[nextFirst + index + 1];
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
