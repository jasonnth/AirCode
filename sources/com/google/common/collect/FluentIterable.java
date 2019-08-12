package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

public abstract class FluentIterable<E> implements Iterable<E> {
    private final Optional<Iterable<E>> iterableDelegate;

    protected FluentIterable() {
        this.iterableDelegate = Optional.absent();
    }

    FluentIterable(Iterable<E> iterable) {
        Preconditions.checkNotNull(iterable);
        if (this == iterable) {
            iterable = null;
        }
        this.iterableDelegate = Optional.fromNullable(iterable);
    }

    private Iterable<E> getDelegate() {
        return (Iterable) this.iterableDelegate.mo41059or(this);
    }

    public static <E> FluentIterable<E> from(final Iterable<E> iterable) {
        return iterable instanceof FluentIterable ? (FluentIterable) iterable : new FluentIterable<E>(iterable) {
            public Iterator<E> iterator() {
                return iterable.iterator();
            }
        };
    }

    public static <E> FluentIterable<E> from(E[] elements) {
        return from((Iterable<E>) Arrays.asList(elements));
    }

    @Deprecated
    public static <E> FluentIterable<E> from(FluentIterable<E> iterable) {
        return (FluentIterable) Preconditions.checkNotNull(iterable);
    }

    public static <T> FluentIterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b) {
        return concat(ImmutableList.m1286of(a, b));
    }

    public static <T> FluentIterable<T> concat(final Iterable<? extends Iterable<? extends T>> inputs) {
        Preconditions.checkNotNull(inputs);
        return new FluentIterable<T>() {
            public Iterator<T> iterator() {
                return Iterators.concat(Iterables.transform(inputs, Iterables.toIterator()).iterator());
            }
        };
    }

    @Deprecated
    /* renamed from: of */
    public static <E> FluentIterable<E> m1283of(E[] elements) {
        return from((Iterable<E>) Lists.newArrayList(elements));
    }

    /* renamed from: of */
    public static <E> FluentIterable<E> m1282of(E element, E... elements) {
        return from((Iterable<E>) Lists.asList(element, elements));
    }

    public String toString() {
        return Iterables.toString(getDelegate());
    }

    public final int size() {
        return Iterables.size(getDelegate());
    }

    public final FluentIterable<E> append(Iterable<? extends E> other) {
        return from(concat(getDelegate(), other));
    }

    public final FluentIterable<E> append(E... elements) {
        return from(concat(getDelegate(), Arrays.asList(elements)));
    }

    public final FluentIterable<E> filter(Predicate<? super E> predicate) {
        return from(Iterables.filter(getDelegate(), predicate));
    }

    public final <T> FluentIterable<T> filter(Class<T> type) {
        return from(Iterables.filter(getDelegate(), type));
    }

    public final boolean anyMatch(Predicate<? super E> predicate) {
        return Iterables.any(getDelegate(), predicate);
    }

    public final boolean allMatch(Predicate<? super E> predicate) {
        return Iterables.all(getDelegate(), predicate);
    }

    public final Optional<E> firstMatch(Predicate<? super E> predicate) {
        return Iterables.tryFind(getDelegate(), predicate);
    }

    public final <T> FluentIterable<T> transform(Function<? super E, T> function) {
        return from(Iterables.transform(getDelegate(), function));
    }

    public <T> FluentIterable<T> transformAndConcat(Function<? super E, ? extends Iterable<? extends T>> function) {
        return from(concat(transform(function)));
    }

    public final Optional<E> first() {
        Iterator<E> iterator = getDelegate().iterator();
        return iterator.hasNext() ? Optional.m1897of(iterator.next()) : Optional.absent();
    }

    public final Optional<E> last() {
        E current;
        Iterable<E> iterable = getDelegate();
        if (iterable instanceof List) {
            List<E> list = (List) iterable;
            if (list.isEmpty()) {
                return Optional.absent();
            }
            return Optional.m1897of(list.get(list.size() - 1));
        }
        Iterator<E> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return Optional.absent();
        }
        if (iterable instanceof SortedSet) {
            return Optional.m1897of(((SortedSet) iterable).last());
        }
        do {
            current = iterator.next();
        } while (iterator.hasNext());
        return Optional.m1897of(current);
    }

    public final FluentIterable<E> skip(int numberToSkip) {
        return from(Iterables.skip(getDelegate(), numberToSkip));
    }

    public final FluentIterable<E> limit(int maxSize) {
        return from(Iterables.limit(getDelegate(), maxSize));
    }

    public final boolean isEmpty() {
        return !getDelegate().iterator().hasNext();
    }

    public final ImmutableList<E> toList() {
        return ImmutableList.copyOf(getDelegate());
    }

    public final ImmutableList<E> toSortedList(Comparator<? super E> comparator) {
        return Ordering.from(comparator).immutableSortedCopy(getDelegate());
    }

    public final ImmutableSet<E> toSet() {
        return ImmutableSet.copyOf(getDelegate());
    }

    public final <K> ImmutableMap<K, E> uniqueIndex(Function<? super E, K> keyFunction) {
        return Maps.uniqueIndex(getDelegate(), keyFunction);
    }

    public final E[] toArray(Class<E> type) {
        return Iterables.toArray(getDelegate(), type);
    }

    public final String join(Joiner joiner) {
        return joiner.join((Iterable<?>) this);
    }

    public final E get(int position) {
        return Iterables.get(getDelegate(), position);
    }
}
