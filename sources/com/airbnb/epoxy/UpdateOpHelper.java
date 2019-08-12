package com.airbnb.epoxy;

import java.util.ArrayList;
import java.util.List;

class UpdateOpHelper {
    private UpdateOp lastOp;
    final List<UpdateOp> moves = new ArrayList();
    private int numInsertionBatches;
    private int numInsertions;
    private int numRemovalBatches;
    private int numRemovals;
    final List<UpdateOp> opList = new ArrayList();

    UpdateOpHelper() {
    }

    /* access modifiers changed from: 0000 */
    public void reset() {
        this.opList.clear();
        this.moves.clear();
        this.lastOp = null;
        this.numInsertions = 0;
        this.numInsertionBatches = 0;
        this.numRemovals = 0;
        this.numRemovalBatches = 0;
    }

    /* access modifiers changed from: 0000 */
    public void add(int indexToInsert) {
        add(indexToInsert, 1);
    }

    /* access modifiers changed from: 0000 */
    public void add(int startPosition, int itemCount) {
        boolean batchWithLast;
        this.numInsertions += itemCount;
        if (!isLastOp(0) || (!this.lastOp.contains(startPosition) && this.lastOp.positionEnd() != startPosition)) {
            batchWithLast = false;
        } else {
            batchWithLast = true;
        }
        if (batchWithLast) {
            addItemsToLastOperation(itemCount, null);
            return;
        }
        this.numInsertionBatches++;
        addNewOperation(0, startPosition, itemCount);
    }

    /* access modifiers changed from: 0000 */
    public void update(int indexToChange, EpoxyModel<?> payload) {
        if (!isLastOp(2)) {
            addNewOperation(2, indexToChange, 1, payload);
        } else if (this.lastOp.positionStart == indexToChange + 1) {
            addItemsToLastOperation(1, payload);
            this.lastOp.positionStart = indexToChange;
        } else if (this.lastOp.positionEnd() == indexToChange) {
            addItemsToLastOperation(1, payload);
        } else if (this.lastOp.contains(indexToChange)) {
            addItemsToLastOperation(0, payload);
        } else {
            addNewOperation(2, indexToChange, 1, payload);
        }
    }

    /* access modifiers changed from: 0000 */
    public void remove(int indexToRemove) {
        remove(indexToRemove, 1);
    }

    /* access modifiers changed from: 0000 */
    public void remove(int startPosition, int itemCount) {
        this.numRemovals += itemCount;
        boolean batchWithLast = false;
        if (isLastOp(1)) {
            if (this.lastOp.positionStart == startPosition) {
                batchWithLast = true;
            } else if (this.lastOp.isAfter(startPosition) && startPosition + itemCount >= this.lastOp.positionStart) {
                this.lastOp.positionStart = startPosition;
                batchWithLast = true;
            }
        }
        if (batchWithLast) {
            addItemsToLastOperation(itemCount, null);
            return;
        }
        this.numRemovalBatches++;
        addNewOperation(1, startPosition, itemCount);
    }

    private boolean isLastOp(int updateType) {
        return this.lastOp != null && this.lastOp.type == updateType;
    }

    private void addNewOperation(int type, int position, int itemCount) {
        addNewOperation(type, position, itemCount, null);
    }

    private void addNewOperation(int type, int position, int itemCount, EpoxyModel<?> payload) {
        this.lastOp = UpdateOp.instance(type, position, itemCount, payload);
        this.opList.add(this.lastOp);
    }

    private void addItemsToLastOperation(int numItemsToAdd, EpoxyModel<?> payload) {
        this.lastOp.itemCount += numItemsToAdd;
        this.lastOp.addPayload(payload);
    }

    /* access modifiers changed from: 0000 */
    public void move(int from, int to) {
        this.lastOp = null;
        UpdateOp op = UpdateOp.instance(3, from, to, null);
        this.opList.add(op);
        this.moves.add(op);
    }

    /* access modifiers changed from: 0000 */
    public int getNumRemovals() {
        return this.numRemovals;
    }

    /* access modifiers changed from: 0000 */
    public boolean hasRemovals() {
        return this.numRemovals > 0;
    }

    /* access modifiers changed from: 0000 */
    public int getNumInsertions() {
        return this.numInsertions;
    }

    /* access modifiers changed from: 0000 */
    public boolean hasInsertions() {
        return this.numInsertions > 0;
    }

    /* access modifiers changed from: 0000 */
    public int getNumMoves() {
        return this.moves.size();
    }
}
