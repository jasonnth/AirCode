package com.google.protobuf.jpush;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

public class LazyStringArrayList extends AbstractList<String> implements LazyStringList, RandomAccess {
    public static final LazyStringList EMPTY = new UnmodifiableLazyStringList(new LazyStringArrayList());
    private final List<Object> list;

    public LazyStringArrayList() {
        this.list = new ArrayList();
    }

    public LazyStringArrayList(List<String> from) {
        this.list = new ArrayList(from);
    }

    public String get(int index) {
        Object o = this.list.get(index);
        if (o instanceof String) {
            return (String) o;
        }
        ByteString bs = (ByteString) o;
        String s = bs.toStringUtf8();
        if (Internal.isValidUtf8(bs)) {
            this.list.set(index, s);
        }
        return s;
    }

    public int size() {
        return this.list.size();
    }

    public String set(int index, String s) {
        return asString(this.list.set(index, s));
    }

    public void add(int index, String element) {
        this.list.add(index, element);
        this.modCount++;
    }

    public boolean addAll(int index, Collection<? extends String> c) {
        boolean ret = this.list.addAll(index, c);
        this.modCount++;
        return ret;
    }

    public String remove(int index) {
        Object o = this.list.remove(index);
        this.modCount++;
        return asString(o);
    }

    public void clear() {
        this.list.clear();
        this.modCount++;
    }

    public void add(ByteString element) {
        this.list.add(element);
        this.modCount++;
    }

    public ByteString getByteString(int index) {
        Object o = this.list.get(index);
        if (!(o instanceof String)) {
            return (ByteString) o;
        }
        ByteString b = ByteString.copyFromUtf8((String) o);
        this.list.set(index, b);
        return b;
    }

    private String asString(Object o) {
        if (o instanceof String) {
            return (String) o;
        }
        return ((ByteString) o).toStringUtf8();
    }
}
