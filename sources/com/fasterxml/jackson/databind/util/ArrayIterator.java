package com.fasterxml.jackson.databind.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterable<T>, Iterator<T> {

    /* renamed from: _a */
    private final T[] f3144_a;
    private int _index = 0;

    public ArrayIterator(T[] a) {
        this.f3144_a = a;
    }

    public boolean hasNext() {
        return this._index < this.f3144_a.length;
    }

    public T next() {
        if (this._index >= this.f3144_a.length) {
            throw new NoSuchElementException();
        }
        T[] tArr = this.f3144_a;
        int i = this._index;
        this._index = i + 1;
        return tArr[i];
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator() {
        return this;
    }
}
