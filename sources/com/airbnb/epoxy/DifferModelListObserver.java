package com.airbnb.epoxy;

class DifferModelListObserver extends UpdateOpHelper implements ModelListObserver {
    DifferModelListObserver() {
    }

    public void onItemRangeInserted(int positionStart, int itemCount) {
        add(positionStart, itemCount);
    }

    public void onItemRangeRemoved(int positionStart, int itemCount) {
        remove(positionStart, itemCount);
    }

    /* access modifiers changed from: 0000 */
    public boolean hasNoChanges() {
        return !hasInsertions() && !hasRemovals();
    }

    /* access modifiers changed from: 0000 */
    public boolean hasOnlyInsertions() {
        return !hasRemovals() && hasInsertions();
    }

    /* access modifiers changed from: 0000 */
    public boolean hasOnlyRemovals() {
        return !hasInsertions() && hasRemovals();
    }
}
