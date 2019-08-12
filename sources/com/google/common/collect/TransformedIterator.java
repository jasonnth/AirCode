package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Iterator;

abstract class TransformedIterator<F, T> implements Iterator<T> {
    final Iterator<? extends F> backingIterator;

    /* access modifiers changed from: 0000 */
    public abstract T transform(F f);

    TransformedIterator(Iterator<? extends F> backingIterator2) {
        this.backingIterator = (Iterator) Preconditions.checkNotNull(backingIterator2);
    }

    public final boolean hasNext() {
        return this.backingIterator.hasNext();
    }

    public final T next() {
        return transform(this.backingIterator.next());
    }

    public final void remove() {
        this.backingIterator.remove();
    }
}
