package com.google.common.collect;

import java.util.Queue;

public abstract class ForwardingQueue<E> extends ForwardingCollection<E> implements Queue<E> {
    /* access modifiers changed from: protected */
    public abstract Queue<E> delegate();

    protected ForwardingQueue() {
    }

    public boolean offer(E o) {
        return delegate().offer(o);
    }

    public E poll() {
        return delegate().poll();
    }

    public E remove() {
        return delegate().remove();
    }

    public E peek() {
        return delegate().peek();
    }

    public E element() {
        return delegate().element();
    }
}
