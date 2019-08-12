package com.google.common.collect;

import java.io.Serializable;
import java.lang.Comparable;
import java.util.NoSuchElementException;
import java.util.Set;

final class EmptyContiguousSet<C extends Comparable> extends ContiguousSet<C> {

    private static final class SerializedForm<C extends Comparable> implements Serializable {
        private final DiscreteDomain<C> domain;

        private SerializedForm(DiscreteDomain<C> domain2) {
            this.domain = domain2;
        }

        private Object readResolve() {
            return new EmptyContiguousSet(this.domain);
        }
    }

    EmptyContiguousSet(DiscreteDomain<C> domain) {
        super(domain);
    }

    public C first() {
        throw new NoSuchElementException();
    }

    public C last() {
        throw new NoSuchElementException();
    }

    public int size() {
        return 0;
    }

    public Range<C> range() {
        throw new NoSuchElementException();
    }

    /* access modifiers changed from: 0000 */
    public ContiguousSet<C> headSetImpl(C c, boolean inclusive) {
        return this;
    }

    /* access modifiers changed from: 0000 */
    public ContiguousSet<C> subSetImpl(C c, boolean fromInclusive, C c2, boolean toInclusive) {
        return this;
    }

    /* access modifiers changed from: 0000 */
    public ContiguousSet<C> tailSetImpl(C c, boolean fromInclusive) {
        return this;
    }

    public boolean contains(Object object) {
        return false;
    }

    public UnmodifiableIterator<C> iterator() {
        return Iterators.emptyIterator();
    }

    public UnmodifiableIterator<C> descendingIterator() {
        return Iterators.emptyIterator();
    }

    /* access modifiers changed from: 0000 */
    public boolean isPartialView() {
        return false;
    }

    public boolean isEmpty() {
        return true;
    }

    public ImmutableList<C> asList() {
        return ImmutableList.m1284of();
    }

    public String toString() {
        return "[]";
    }

    public boolean equals(Object object) {
        if (object instanceof Set) {
            return ((Set) object).isEmpty();
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean isHashCodeFast() {
        return true;
    }

    public int hashCode() {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public Object writeReplace() {
        return new SerializedForm(this.domain);
    }

    /* access modifiers changed from: 0000 */
    public ImmutableSortedSet<C> createDescendingSet() {
        return ImmutableSortedSet.emptySet(Ordering.natural().reverse());
    }
}
