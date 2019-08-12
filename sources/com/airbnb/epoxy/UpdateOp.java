package com.airbnb.epoxy;

import java.util.ArrayList;

class UpdateOp {
    int itemCount;
    ArrayList<EpoxyModel<?>> payloads;
    int positionStart;
    int type;

    private UpdateOp() {
    }

    static UpdateOp instance(int type2, int positionStart2, int itemCount2, EpoxyModel<?> payload) {
        UpdateOp op = new UpdateOp();
        op.type = type2;
        op.positionStart = positionStart2;
        op.itemCount = itemCount2;
        op.addPayload(payload);
        return op;
    }

    /* access modifiers changed from: 0000 */
    public int positionEnd() {
        return this.positionStart + this.itemCount;
    }

    /* access modifiers changed from: 0000 */
    public boolean isAfter(int position) {
        return position < this.positionStart;
    }

    /* access modifiers changed from: 0000 */
    public boolean contains(int position) {
        return position >= this.positionStart && position < positionEnd();
    }

    /* access modifiers changed from: 0000 */
    public void addPayload(EpoxyModel<?> payload) {
        if (payload != null) {
            if (this.payloads == null) {
                this.payloads = new ArrayList<>(1);
            } else if (this.payloads.size() == 1) {
                this.payloads.ensureCapacity(10);
            }
            this.payloads.add(payload);
        }
    }

    public String toString() {
        return "UpdateOp{type=" + this.type + ", positionStart=" + this.positionStart + ", itemCount=" + this.itemCount + '}';
    }
}
