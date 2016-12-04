package ua.kpi.linkedList;

import java.util.*;

/**
 * Created by Evgeniy on 26.11.2016.
 */
public class MyLinkedList<E> extends AbstractList<E> implements List<E>, Deque<E>{

    private int size;
    private Node<E> first;
    private Node<E> last;
    private int modificationCount = 0;

    public MyLinkedList() {}

    public MyLinkedList(Collection<? extends E> collection) {
        this();
        addAll(collection);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> current = first;
        if(o == null) {
            while (current != null) {
                if(current.data == null) {
                    return true;
                }
                current = current.next;
            }
        } else {
            while (current != null) {
                if (o.equals(current.data)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public void addFirst(E e) {
        Node<E> newFirst = new Node<E>(e,null,first);

        if(first != null){
            first.previous = newFirst;
        }
        first = newFirst;
        size++;
        modificationCount++;
    }

    @Override
    public void addLast(E e) {
        add(e);
    }

    @Override
    public boolean offerFirst(E e) {
        modificationCount++;
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        add(e);
        return true;
    }

    @Override
    public E removeFirst() {
        modificationCount++;
        return null;
    }

    @Override
    public E removeLast() {
        modificationCount++;
        return null;
    }

    @Override
    public E pollFirst() {
        modificationCount++;
        return null;
    }

    @Override
    public E pollLast() {
        modificationCount++;
        return null;
    }

    @Override
    public E getFirst() {
        return (E) first.data;
    }

    @Override
    public E getLast() {
        return (E) last.data;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        modificationCount++;
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        modificationCount++;
        return false;
    }

    @Override
    public boolean add(E e) {
        Node<E> current = new Node<E>(e);
        if(this.isEmpty()) {
            first = current;
            last = current;
        } else {
            last.next =current;
            current.previous = last;
            last = current;
        }
        size++;
        modificationCount++;
        return true;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        modificationCount++;
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public void push(E e) {
        modificationCount++;
    }

    @Override
    public E pop() {
        modificationCount++;
        return null;
    }

    @Override
    public boolean remove(Object o) {
        modificationCount++;
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Objects.requireNonNull(c);
        c.stream().forEach(this::add);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
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
    public E get(int index) {
        if( index >= 0 && index < size) {
            if (!isEmpty()) {
                Node<E> current = first;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
                return current.data;
            } else {
                throw new NoSuchElementException();
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E set(int index, E element) {
        Node<E> current = findNodeByIndex(index);
        E prev = current.data;
        current.data = element;
        return prev;
    }

    private Node<E> findNodeByIndex(int index) {
        if( index >= 0 && index < size) {
            if (!isEmpty()) {
                Node<E> current = first;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
                return current;
            } else {
                throw new NoSuchElementException();
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void add(int index, E element) {
        if(!isEmpty()){
            if(index == size) {
                add(element);
            } else if(index == 0) {
                addFirst(element);
            } else {
                Node<E> current = findNodeByIndex(index);
                Node<E> newNode = new Node<E>
                        (element, current.previous, current.next);
                current.previous.next = newNode;
                current.previous = newNode;
                size++;
            }
        }
    }

    @Override
    public E remove(int index) {
        Node<E> current = findNodeByIndex(index);
        return removeNode(current);
    }

    private E removeNode(Node<E> current) {
        modificationCount++;
        E element = current.data;

        if (size == 1) {
            first = last = null;
            size--;
            return element;
        }

        if (current == first) {
            first = current.next;
            first.previous = null;
        } else if (current == last) {
            last = current.previous;
            last.next = null;
        } else {
            current.previous.next = current.next;
            current.next.previous = current.previous;
        }

        size--;
        return element;
    }

    @Override
    public int indexOf(Object o) {
        int ind = 0;

        Node<E> current = first;
        while (current != null) {
            if (o == null) {
                if (current.data == null) {
                    return ind;
                }
            } else {
                if (o.equals(current.data)) {
                    return ind;
                }
            }

            current = current.next;
            ind++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyIterator(){
            {
                current = findNodeByIndex(index);
                cursor = index;
            }
        };
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    private class Node<E> {
        Node<E> previous;
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }

        public Node(E data,Node<E> previous, Node<E> next) {
            this(data);
            this.previous = previous;
            this.next = next;
        }
    }

    private class MyIterator implements ListIterator<E> {
        int cursor = 0;
        int iteratorModCount = modificationCount;
        Node<E> current = first ;
        Node<E> lastReturned = null;

        @Override
        public boolean hasNext() {
            checkCoModification();
            return current != null;
        }

        private void checkCoModification() {
            if( iteratorModCount != modificationCount ){
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public E next() {
            checkCoModification();
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            lastReturned = current;
            current = current.next;
            cursor++;
            return lastReturned.data;
        }

        @Override
        public boolean hasPrevious() {
            checkCoModification();
            if( cursor > 0 && size != 0 ){
                return true;
            }
            return false;
        }

        @Override
        public E previous() {
            checkCoModification();
            if( ! hasPrevious()){
                throw new NoSuchElementException();
            }
            cursor--;
            if( current == null && lastReturned != null){
                current = lastReturned;
                return lastReturned.data;
            }
            lastReturned = current.previous;
            current = current.previous;
            return lastReturned.data;
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor -1;
        }

        @Override
        public void remove() {
            checkCoModification();
            if( lastReturned == null ){
                throw new IllegalArgumentException();
            }
            iteratorModCount++;
            removeNode(lastReturned);
            lastReturned = null;
        }

        @Override
        public void set(E e) {
            checkCoModification();
            if( lastReturned == null ){
                throw new NoSuchElementException();
            }
            modificationCount++;
            iteratorModCount++;
            lastReturned.data = e;
        }

        @Override
        public void add(E e) {
            checkCoModification();
            iteratorModCount++;
            if(current == null ){
                add(e);
            } else {
                Node<E> temp = new Node<E>(e, current.previous, current);
                if( current.previous != null ) {
                    current.previous.next = temp;
                }
                current.previous = temp;
            }
        }
    }
}
