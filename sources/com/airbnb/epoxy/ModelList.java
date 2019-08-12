package com.airbnb.epoxy;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

class ModelList extends ArrayList<EpoxyModel<?>> {
    private boolean notificationsPaused;
    private ModelListObserver observer;

    private class Itr implements Iterator<EpoxyModel<?>> {
        int cursor;
        int expectedModCount;
        int lastRet;

        private Itr() {
            this.lastRet = -1;
            this.expectedModCount = ModelList.this.modCount;
        }

        public boolean hasNext() {
            return this.cursor != ModelList.this.size();
        }

        public EpoxyModel<?> next() {
            checkForComodification();
            int i = this.cursor;
            this.cursor = i + 1;
            this.lastRet = i;
            return (EpoxyModel) ModelList.this.get(i);
        }

        public void remove() {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            }
            checkForComodification();
            try {
                ModelList.this.remove(this.lastRet);
                this.cursor = this.lastRet;
                this.lastRet = -1;
                this.expectedModCount = ModelList.this.modCount;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        /* access modifiers changed from: 0000 */
        public final void checkForComodification() {
            if (ModelList.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class ListItr extends Itr implements ListIterator<EpoxyModel<?>> {
        ListItr(int index) {
            super();
            this.cursor = index;
        }

        public boolean hasPrevious() {
            return this.cursor != 0;
        }

        public int nextIndex() {
            return this.cursor;
        }

        public int previousIndex() {
            return this.cursor - 1;
        }

        public EpoxyModel<?> previous() {
            checkForComodification();
            int i = this.cursor - 1;
            if (i < 0) {
                throw new NoSuchElementException();
            }
            this.cursor = i;
            this.lastRet = i;
            return (EpoxyModel) ModelList.this.get(i);
        }

        public void set(EpoxyModel<?> e) {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            }
            checkForComodification();
            try {
                ModelList.this.set(this.lastRet, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(EpoxyModel<?> e) {
            checkForComodification();
            try {
                int i = this.cursor;
                ModelList.this.add(i, e);
                this.cursor = i + 1;
                this.lastRet = -1;
                this.expectedModCount = ModelList.this.modCount;
            } catch (IndexOutOfBoundsException e2) {
                throw new ConcurrentModificationException();
            }
        }
    }

    interface ModelListObserver {
        void onItemRangeInserted(int i, int i2);

        void onItemRangeRemoved(int i, int i2);
    }

    private static class SubList extends AbstractList<EpoxyModel<?>> {
        private final ModelList fullList;
        private int offset;
        private int size;

        private static final class SubListIterator implements ListIterator<EpoxyModel<?>> {
            private int end;
            private final ListIterator<EpoxyModel<?>> iterator;
            private int start;
            private final SubList subList;

            SubListIterator(ListIterator<EpoxyModel<?>> it, SubList list, int offset, int length) {
                this.iterator = it;
                this.subList = list;
                this.start = offset;
                this.end = this.start + length;
            }

            public void add(EpoxyModel<?> object) {
                this.iterator.add(object);
                this.subList.sizeChanged(true);
                this.end++;
            }

            public boolean hasNext() {
                return this.iterator.nextIndex() < this.end;
            }

            public boolean hasPrevious() {
                return this.iterator.previousIndex() >= this.start;
            }

            public EpoxyModel<?> next() {
                if (this.iterator.nextIndex() < this.end) {
                    return (EpoxyModel) this.iterator.next();
                }
                throw new NoSuchElementException();
            }

            public int nextIndex() {
                return this.iterator.nextIndex() - this.start;
            }

            public EpoxyModel<?> previous() {
                if (this.iterator.previousIndex() >= this.start) {
                    return (EpoxyModel) this.iterator.previous();
                }
                throw new NoSuchElementException();
            }

            public int previousIndex() {
                int previous = this.iterator.previousIndex();
                if (previous >= this.start) {
                    return previous - this.start;
                }
                return -1;
            }

            public void remove() {
                this.iterator.remove();
                this.subList.sizeChanged(false);
                this.end--;
            }

            public void set(EpoxyModel<?> object) {
                this.iterator.set(object);
            }
        }

        SubList(ModelList list, int start, int end) {
            this.fullList = list;
            this.modCount = this.fullList.modCount;
            this.offset = start;
            this.size = end - start;
        }

        public void add(int location, EpoxyModel<?> object) {
            if (this.modCount != this.fullList.modCount) {
                throw new ConcurrentModificationException();
            } else if (location < 0 || location > this.size) {
                throw new IndexOutOfBoundsException();
            } else {
                this.fullList.add(this.offset + location, object);
                this.size++;
                this.modCount = this.fullList.modCount;
            }
        }

        public boolean addAll(int location, Collection<? extends EpoxyModel<?>> collection) {
            if (this.modCount != this.fullList.modCount) {
                throw new ConcurrentModificationException();
            } else if (location < 0 || location > this.size) {
                throw new IndexOutOfBoundsException();
            } else {
                boolean result = this.fullList.addAll(this.offset + location, collection);
                if (result) {
                    this.size += collection.size();
                    this.modCount = this.fullList.modCount;
                }
                return result;
            }
        }

        public boolean addAll(Collection<? extends EpoxyModel<?>> collection) {
            if (this.modCount == this.fullList.modCount) {
                boolean result = this.fullList.addAll(this.offset + this.size, collection);
                if (result) {
                    this.size += collection.size();
                    this.modCount = this.fullList.modCount;
                }
                return result;
            }
            throw new ConcurrentModificationException();
        }

        public EpoxyModel<?> get(int location) {
            if (this.modCount != this.fullList.modCount) {
                throw new ConcurrentModificationException();
            } else if (location >= 0 && location < this.size) {
                return (EpoxyModel) this.fullList.get(this.offset + location);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        public Iterator<EpoxyModel<?>> iterator() {
            return listIterator(0);
        }

        public ListIterator<EpoxyModel<?>> listIterator(int location) {
            if (this.modCount != this.fullList.modCount) {
                throw new ConcurrentModificationException();
            } else if (location >= 0 && location <= this.size) {
                return new SubListIterator(this.fullList.listIterator(this.offset + location), this, this.offset, this.size);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        public EpoxyModel<?> remove(int location) {
            if (this.modCount != this.fullList.modCount) {
                throw new ConcurrentModificationException();
            } else if (location < 0 || location >= this.size) {
                throw new IndexOutOfBoundsException();
            } else {
                EpoxyModel<?> result = this.fullList.remove(this.offset + location);
                this.size--;
                this.modCount = this.fullList.modCount;
                return result;
            }
        }

        /* access modifiers changed from: protected */
        public void removeRange(int start, int end) {
            if (start == end) {
                return;
            }
            if (this.modCount == this.fullList.modCount) {
                this.fullList.removeRange(this.offset + start, this.offset + end);
                this.size -= end - start;
                this.modCount = this.fullList.modCount;
                return;
            }
            throw new ConcurrentModificationException();
        }

        public EpoxyModel<?> set(int location, EpoxyModel<?> object) {
            if (this.modCount != this.fullList.modCount) {
                throw new ConcurrentModificationException();
            } else if (location >= 0 && location < this.size) {
                return this.fullList.set(this.offset + location, object);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        public int size() {
            if (this.modCount == this.fullList.modCount) {
                return this.size;
            }
            throw new ConcurrentModificationException();
        }

        /* access modifiers changed from: 0000 */
        public void sizeChanged(boolean increment) {
            if (increment) {
                this.size++;
            } else {
                this.size--;
            }
            this.modCount = this.fullList.modCount;
        }
    }

    ModelList(int expectedModelCount) {
        super(expectedModelCount);
    }

    ModelList() {
    }

    /* access modifiers changed from: 0000 */
    public void pauseNotifications() {
        if (this.notificationsPaused) {
            throw new IllegalStateException("Notifications already paused");
        }
        this.notificationsPaused = true;
    }

    /* access modifiers changed from: 0000 */
    public void resumeNotifications() {
        if (!this.notificationsPaused) {
            throw new IllegalStateException("Notifications already resumed");
        }
        this.notificationsPaused = false;
    }

    /* access modifiers changed from: 0000 */
    public void setObserver(ModelListObserver observer2) {
        this.observer = observer2;
    }

    private void notifyInsertion(int positionStart, int itemCount) {
        if (!this.notificationsPaused && this.observer != null) {
            this.observer.onItemRangeInserted(positionStart, itemCount);
        }
    }

    private void notifyRemoval(int positionStart, int itemCount) {
        if (!this.notificationsPaused && this.observer != null) {
            this.observer.onItemRangeRemoved(positionStart, itemCount);
        }
    }

    public EpoxyModel<?> set(int index, EpoxyModel<?> element) {
        EpoxyModel<?> previousModel = (EpoxyModel) super.set(index, element);
        if (previousModel.mo11715id() != element.mo11715id()) {
            notifyRemoval(index, 1);
            notifyInsertion(index, 1);
        }
        return previousModel;
    }

    public boolean add(EpoxyModel<?> epoxyModel) {
        notifyInsertion(size(), 1);
        return super.add(epoxyModel);
    }

    public void add(int index, EpoxyModel<?> element) {
        notifyInsertion(index, 1);
        super.add(index, element);
    }

    public boolean addAll(Collection<? extends EpoxyModel<?>> c) {
        notifyInsertion(size(), c.size());
        return super.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends EpoxyModel<?>> c) {
        notifyInsertion(index, c.size());
        return super.addAll(index, c);
    }

    public EpoxyModel<?> remove(int index) {
        notifyRemoval(index, 1);
        return (EpoxyModel) super.remove(index);
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }
        notifyRemoval(index, 1);
        super.remove(index);
        return true;
    }

    public void clear() {
        if (!isEmpty()) {
            notifyRemoval(0, size());
            super.clear();
        }
    }

    /* access modifiers changed from: protected */
    public void removeRange(int fromIndex, int toIndex) {
        if (fromIndex != toIndex) {
            notifyRemoval(fromIndex, toIndex - fromIndex);
            super.removeRange(fromIndex, toIndex);
        }
    }

    public boolean removeAll(Collection<?> collection) {
        boolean result = false;
        Iterator<?> it = iterator();
        while (it.hasNext()) {
            if (collection.contains(it.next())) {
                it.remove();
                result = true;
            }
        }
        return result;
    }

    public boolean retainAll(Collection<?> collection) {
        boolean result = false;
        Iterator<?> it = iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
                result = true;
            }
        }
        return result;
    }

    public Iterator<EpoxyModel<?>> iterator() {
        return new Itr();
    }

    public ListIterator<EpoxyModel<?>> listIterator() {
        return new ListItr(0);
    }

    public ListIterator<EpoxyModel<?>> listIterator(int index) {
        return new ListItr(index);
    }

    public List<EpoxyModel<?>> subList(int start, int end) {
        if (start < 0 || end > size()) {
            throw new IndexOutOfBoundsException();
        } else if (start <= end) {
            return new SubList(this, start, end);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
