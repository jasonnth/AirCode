package com.apollographql.apollo.internal.util;

import java.util.ArrayList;
import java.util.List;

public class SimpleStack<E> {
    private List<E> backing = new ArrayList();

    public void push(E element) {
        this.backing.add(element);
    }

    public E pop() {
        if (!isEmpty()) {
            return this.backing.remove(this.backing.size() - 1);
        }
        throw new IllegalStateException("Stack is empty.");
    }

    public boolean isEmpty() {
        return this.backing.isEmpty();
    }
}
