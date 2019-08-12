package com.google.common.collect;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

abstract class ImmutableAsList<E> extends ImmutableList<E> {

    static class SerializedForm implements Serializable {
        final ImmutableCollection<?> collection;

        SerializedForm(ImmutableCollection<?> collection2) {
            this.collection = collection2;
        }

        /* access modifiers changed from: 0000 */
        public Object readResolve() {
            return this.collection.asList();
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract ImmutableCollection<E> delegateCollection();

    ImmutableAsList() {
    }

    public boolean contains(Object target) {
        return delegateCollection().contains(target);
    }

    public int size() {
        return delegateCollection().size();
    }

    public boolean isEmpty() {
        return delegateCollection().isEmpty();
    }

    /* access modifiers changed from: 0000 */
    public boolean isPartialView() {
        return delegateCollection().isPartialView();
    }

    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    /* access modifiers changed from: 0000 */
    public Object writeReplace() {
        return new SerializedForm(delegateCollection());
    }
}
