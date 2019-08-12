package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.lang.Comparable;
import java.util.NoSuchElementException;

public abstract class ContiguousSet<C extends Comparable> extends ImmutableSortedSet<C> {
    final DiscreteDomain<C> domain;

    /* access modifiers changed from: 0000 */
    public abstract ContiguousSet<C> headSetImpl(C c, boolean z);

    public abstract Range<C> range();

    /* access modifiers changed from: 0000 */
    public abstract ContiguousSet<C> subSetImpl(C c, boolean z, C c2, boolean z2);

    /* access modifiers changed from: 0000 */
    public abstract ContiguousSet<C> tailSetImpl(C c, boolean z);

    public static <C extends Comparable> ContiguousSet<C> create(Range<C> range, DiscreteDomain<C> domain2) {
        Preconditions.checkNotNull(range);
        Preconditions.checkNotNull(domain2);
        Range<C> effectiveRange = range;
        try {
            if (!range.hasLowerBound()) {
                effectiveRange = effectiveRange.intersection(Range.atLeast(domain2.minValue()));
            }
            if (!range.hasUpperBound()) {
                effectiveRange = effectiveRange.intersection(Range.atMost(domain2.maxValue()));
            }
            if (effectiveRange.isEmpty() || Range.compareOrThrow(range.lowerBound.leastValueAbove(domain2), range.upperBound.greatestValueBelow(domain2)) > 0) {
                return new EmptyContiguousSet(domain2);
            }
            return new RegularContiguousSet(effectiveRange, domain2);
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(e);
        }
    }

    ContiguousSet(DiscreteDomain<C> domain2) {
        super(Ordering.natural());
        this.domain = domain2;
    }

    public ContiguousSet<C> headSet(C toElement) {
        return headSetImpl((C) (Comparable) Preconditions.checkNotNull(toElement), false);
    }

    public ContiguousSet<C> headSet(C toElement, boolean inclusive) {
        return headSetImpl((C) (Comparable) Preconditions.checkNotNull(toElement), inclusive);
    }

    public ContiguousSet<C> subSet(C fromElement, C toElement) {
        boolean z;
        Preconditions.checkNotNull(fromElement);
        Preconditions.checkNotNull(toElement);
        if (comparator().compare(fromElement, toElement) <= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        return subSetImpl(fromElement, true, toElement, false);
    }

    public ContiguousSet<C> subSet(C fromElement, boolean fromInclusive, C toElement, boolean toInclusive) {
        Preconditions.checkNotNull(fromElement);
        Preconditions.checkNotNull(toElement);
        Preconditions.checkArgument(comparator().compare(fromElement, toElement) <= 0);
        return subSetImpl(fromElement, fromInclusive, toElement, toInclusive);
    }

    public ContiguousSet<C> tailSet(C fromElement) {
        return tailSetImpl((C) (Comparable) Preconditions.checkNotNull(fromElement), true);
    }

    public ContiguousSet<C> tailSet(C fromElement, boolean inclusive) {
        return tailSetImpl((C) (Comparable) Preconditions.checkNotNull(fromElement), inclusive);
    }

    /* access modifiers changed from: 0000 */
    public ImmutableSortedSet<C> createDescendingSet() {
        return new DescendingImmutableSortedSet(this);
    }

    public String toString() {
        return range().toString();
    }
}
