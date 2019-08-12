package com.google.common.collect;

import com.google.common.base.Preconditions;

class RegularImmutableList<E> extends ImmutableList<E> {
    static final ImmutableList<Object> EMPTY = new RegularImmutableList(new Object[0], 0);
    final transient Object[] array;
    private final transient int size;

    RegularImmutableList(Object[] array2, int size2) {
        this.array = array2;
        this.size = size2;
    }

    public int size() {
        return this.size;
    }

    /* access modifiers changed from: 0000 */
    public boolean isPartialView() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public int copyIntoArray(Object[] dst, int dstOff) {
        System.arraycopy(this.array, 0, dst, dstOff, this.size);
        return this.size + dstOff;
    }

    public E get(int index) {
        Preconditions.checkElementIndex(index, this.size);
        return this.array[index];
    }

    public UnmodifiableListIterator<E> listIterator(int index) {
        return Iterators.forArray(this.array, 0, this.size, index);
    }
}
