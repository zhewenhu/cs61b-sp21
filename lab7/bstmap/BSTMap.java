package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private BSTNode root;               // root of BST

    private class BSTNode {
        private K key;                  // sorted by key
        private V val;                  // associated data
        private BSTNode left, right;    // left and right subtrees
        private int size;               // number of nodes in subtree

        public BSTNode(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(root, key) != null;
    }

    @Override
    public V get(K key) {
        BSTNode node = get(root, key);
        if (node == null) {
            return null;
        } else {
            return node.val;
        }
    }

    private BSTNode get(BSTNode x, K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode x) {
        if (x == null) return 0;
        else return x.size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
//        if (value == null) {
//            return;
//        }
        root = put(root, key, value);
    }

    private BSTNode put(BSTNode x, K key, V val) {
        if (x == null) return new BSTNode(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    @Override
    public Set<K> keySet()  {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key)  {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value)  {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator()  {
        throw new UnsupportedOperationException();
    }
}
