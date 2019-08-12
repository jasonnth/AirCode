package com.airbnb.epoxy;

import android.support.p000v4.util.LongSparseArray;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BoundViewHolders implements Iterable<EpoxyViewHolder> {
    /* access modifiers changed from: private */
    public final LongSparseArray<EpoxyViewHolder> holders = new LongSparseArray<>();

    private class HolderIterator implements Iterator<EpoxyViewHolder> {
        private int position;

        private HolderIterator() {
            this.position = 0;
        }

        public boolean hasNext() {
            return this.position < BoundViewHolders.this.holders.size();
        }

        public EpoxyViewHolder next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            LongSparseArray access$100 = BoundViewHolders.this.holders;
            int i = this.position;
            this.position = i + 1;
            return (EpoxyViewHolder) access$100.valueAt(i);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public EpoxyViewHolder get(EpoxyViewHolder holder) {
        return (EpoxyViewHolder) this.holders.get(holder.getItemId());
    }

    public void put(EpoxyViewHolder holder) {
        this.holders.put(holder.getItemId(), holder);
    }

    public void remove(EpoxyViewHolder holder) {
        this.holders.remove(holder.getItemId());
    }

    public int size() {
        return this.holders.size();
    }

    public Iterator<EpoxyViewHolder> iterator() {
        return new HolderIterator();
    }
}
