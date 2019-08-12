package com.airbnb.epoxy;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

class UnmodifiableList<E> implements List<E> {
    final List<? extends E> list;

    UnmodifiableList(List<? extends E> list2) {
        this.list = list2;
    }

    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public boolean contains(Object o) {
        return this.list.contains(o);
    }

    public Object[] toArray() {
        return this.list.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return this.list.toArray(a);
    }

    public String toString() {
        return this.list.toString();
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {

            /* renamed from: i */
            private final Iterator<? extends E> f2684i = UnmodifiableList.this.list.iterator();

            public boolean hasNext() {
                return this.f2684i.hasNext();
            }

            public E next() {
                return this.f2684i.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection<?> coll) {
        return this.list.containsAll(coll);
    }

    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object o) {
        return o == this || this.list.equals(o);
    }

    public int hashCode() {
        return this.list.hashCode();
    }

    public E get(int index) {
        return this.list.get(index);
    }

    public E set(int index, E e) {
        throw new UnsupportedOperationException();
    }

    public void add(int index, E e) {
        throw new UnsupportedOperationException();
    }

    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    public int indexOf(Object o) {
        return this.list.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return this.list.lastIndexOf(o);
    }

    public boolean addAll(int index, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    public ListIterator<E> listIterator(final int index) {
        return new ListIterator<E>() {

            /* renamed from: i */
            private final ListIterator<? extends E> f2685i = UnmodifiableList.this.list.listIterator(index);

            public boolean hasNext() {
                return this.f2685i.hasNext();
            }

            public E next() {
                return this.f2685i.next();
            }

            public boolean hasPrevious() {
                return this.f2685i.hasPrevious();
            }

            public E previous() {
                return this.f2685i.previous();
            }

            public int nextIndex() {
                return this.f2685i.nextIndex();
            }

            public int previousIndex() {
                return this.f2685i.previousIndex();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public void set(E e) {
                throw new UnsupportedOperationException();
            }

            public void add(E e) {
                throw new UnsupportedOperationException();
            }
        };
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return new UnmodifiableList(this.list.subList(fromIndex, toIndex));
    }
}
