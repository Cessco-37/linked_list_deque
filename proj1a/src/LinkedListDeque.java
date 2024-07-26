import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque<T> implements Deque<T> {

    private TNode sentinel;
    private int size;
    private class TNode {
        private TNode next;
        private T item;
        private TNode prev;

        public TNode(T x, TNode n) {
            item = x;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new TNode(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        TNode a = new TNode(x, sentinel.next);
        sentinel.next.prev = a;
        sentinel.next = a;
        a.prev = sentinel;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        TNode a = new TNode(x, sentinel);
        a.prev = sentinel.prev;
        sentinel.prev.next = a;
        sentinel.prev = a;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();

        TNode a = sentinel.next;
        for (int i = 0; i < size; i++) {
            T element = a.item;
            returnList.add(element);
            a = a.next;
        }

        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return first;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return last;
    }

    @Override
    public T get(int index) {
        TNode a = sentinel.next;

        if (index >= size || size == 0) {
            return null;
        }

        for (int i = 0; i < index; i++) {
            a = a.next;
        }

        return a.item;
    }
    public T getRecursiveHelper(int index, int curr, TNode a) {
        if (index == curr) {
            return a.item;
        }
        return getRecursiveHelper(index, curr + 1, a.next);
    }

    @Override
    public T getRecursive(int index) {
        TNode a = sentinel.next;
        int curr = 0;

        if (index >= size || size == 0) {
            return null;
        }
        return getRecursiveHelper(index, curr, a);
    }
}
