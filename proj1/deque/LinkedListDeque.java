package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        private T item;
        private Node prev, next;

        Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }
    private Node sentinel;
    private int size;

    /** Creates an empty list. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    public void printDeque() {
        StringBuilder returnSB = new StringBuilder("{");
        for (Node n = sentinel.next; n != sentinel.prev; n = n.next) {
            returnSB.append(n.item);
            returnSB.append(", ");
        }
        returnSB.append(sentinel.prev.item);
        returnSB.append("}");
        System.out.println(returnSB);
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T returnItem = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return returnItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T returnItem = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;

        return  returnItem;
    }

    @Override
    public T get(int index) {
        if (index > size || index < 0) {
            return null;
        }
        Node currentNode = sentinel.next;
        for (int i = 0; i < index; i += 1) {
            currentNode = currentNode.next;
        }
        return currentNode.item;
    }

    private T getHelper(int cnt, int index, Node n) {
        if (cnt == index) {
            return n.item;
        }
        return getHelper(++cnt, index, n.next);
    }
    public T getRecursive(int index) {
        if (index > size || index < 0) {
            return null;
        }
        return getHelper(0, index, sentinel.next);
    }

    private class DequeIterator implements Iterator<T> {
        private Node currentNode;

        DequeIterator() {
            currentNode = sentinel.next;
        }

        public boolean hasNext() {
            return !isEmpty() && currentNode != sentinel;
        }

        public T next() {
            if (!hasNext()) {
                return null;
            }

            T returnItem = currentNode.item;
            currentNode = currentNode.next;
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
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (other.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!(other.get(i).equals(this.get(i)))) {
                return false;
            }
        }
        return true;
    }
}
