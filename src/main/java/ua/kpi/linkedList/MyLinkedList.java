package ua.kpi.linkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * Created by Evgeniy on 26.11.2016.
 */
public class MyLinkedList<T> implements Iterable<T>, Queue<T>{

    private Node<T> first;
    private Node<T> last;
    private int modificationCount = 0;
    private int size;

    private class Node<T> {

        private T value;
        private Node next;
        private Node previous;

        public Node() {
        }

        public Node(T value) {
            this.value = value;
        }
    }

    public boolean checkIndex(int index) {
        if (index > 0 || index <= size()){
            return true;
        }
        throw new IllegalArgumentException("Invalid value of index. Should be from range [ 0 ; " + size + "] ");
    }

    public T getFirst() {
        return (T) first.value;
    }

    public T getLast() {
        return (T) last.value;
    }

    public void addFirst(T element) {

        if (first == null) {
            first = new Node(element);
            last = first;
            size++;
            modificationCount++;
            return;
        }
        Node node = new Node(element);
        first.previous = node;
        node.next = first;
        first = node;
        size++;
        modificationCount++;
    }

    @Override
    public boolean add(T element) {

        if (first == null) {
            first = new Node(element);
            last = first;
            size++;
            modificationCount++;
            return true;
        }
        Node temp = first;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(element);
        last = temp.next;
        last.previous = temp;
        size++;
        modificationCount++;
        return true;
    }

    public void add(int index, T element) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        else if (index == 0) {
            addFirst(element);
            return;
        }
        else if (index == size) {
            add(element);
            return;
        }
        modificationCount++;
        Node temp = getNode(index - 1);
        Node node = new Node(element);
        temp.next.previous = node;
        node.next = temp.next;
        node.previous = temp;
        temp.next = node;
        size++;
    }

    public T get(int index) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        Node temp = getNode(index);
        return (T) temp.value;
    }

    public void set(int index, T value) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        modificationCount++;
        Node temp = getNode(index);
        temp.value = value;
    }

    private Node getNode(int index) {

        int i;
        Node temp;
        if (index < size / 2) {
            i = 0;
            temp = first;
            while (i != index) {
                temp = temp.next;
                i++;
            }
        } else {
            i = size - 1;
            temp = last;
            while (i != index) {
                temp = temp.previous;
                i--;
            }
        }
        return temp;
    }

    public T removeByIndex(int index){

        checkIndex(index);
        Node<T> current;
        T removedValue;

        if(index < size()/2){
            current = first;
            for(int i = 1; i < index; i++){
                current = current.next;
            }
        }else{
            current = last;
            for(int i = size-2; i > index; i--){
                current = current.previous;
            }
        }

        if (current.next != null){
            current.next.previous = current.previous;
        }
        if (current.previous != null){
            current.previous.next = current.next;
        }

        removedValue = (T) current.value;
        return removedValue;
    }

    public T removeFirst() {

        if (first == null && last == null) {
            throw new UnsupportedOperationException();
        } else if (first == last) {
            size--;
            Node temp = first;
            first = last = null;
            return (T) temp.value;
        }
        modificationCount++;
        Node temp = first;
        first = first.next;
        first.previous = null;
        size--;
        return (T) temp.value;
    }

    public T removeLast() {

        if (first == null && last == null) {
            throw new UnsupportedOperationException();
        } else if (last == first) {
            size--;
            Node temp = first;
            first = last = null;
            return (T) temp.value;
        }
        modificationCount++;
        Node temp = last;
        last = last.previous;
        last.next = null;
        size--;
        return (T) temp.value;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

//    @Override
//    public boolean add(T t) {
//        return false;
//    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
