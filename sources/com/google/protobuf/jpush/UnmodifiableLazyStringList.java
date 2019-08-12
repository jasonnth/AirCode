package com.google.protobuf.jpush;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.RandomAccess;

public class UnmodifiableLazyStringList extends AbstractList<String> implements LazyStringList, RandomAccess {
    /* access modifiers changed from: private */
    public final LazyStringList list;

    public UnmodifiableLazyStringList(LazyStringList list2) {
        this.list = list2;
    }

    public String get(int index) {
        return (String) this.list.get(index);
    }

    public int size() {
        return this.list.size();
    }

    public ByteString getByteString(int index) {
        return this.list.getByteString(index);
    }

    public void add(ByteString element) {
        throw new UnsupportedOperationException();
    }

    public ListIterator<String> listIterator(final int index) {
        return new ListIterator<String>() {
            ListIterator<String> iter = UnmodifiableLazyStringList.this.list.listIterator(index);

            public boolean hasNext() {
                return this.iter.hasNext();
            }

            public String next() {
                return (String) this.iter.next();
            }

            public boolean hasPrevious() {
                return this.iter.hasPrevious();
            }

            public String previous() {
                return (String) this.iter.previous();
            }

            public int nextIndex() {
                return this.iter.nextIndex();
            }

            public int previousIndex() {
                return this.iter.previousIndex();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public void set(String o) {
                throw new UnsupportedOperationException();
            }

            public void add(String o) {
                throw new UnsupportedOperationException();
            }
        };
    }

    public Iterator<String> iterator() {
        return new Iterator<String>() {
            Iterator<String> iter = UnmodifiableLazyStringList.this.list.iterator();

            public boolean hasNext() {
                return this.iter.hasNext();
            }

            public String next() {
                return (String) this.iter.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
