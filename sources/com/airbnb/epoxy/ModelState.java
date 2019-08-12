package com.airbnb.epoxy;

class ModelState {
    int hashCode;

    /* renamed from: id */
    long f2683id;
    int lastMoveOp;
    EpoxyModel<?> model;
    ModelState pair;
    int position;

    ModelState() {
    }

    static ModelState build(EpoxyModel<?> model2, int position2, boolean immutableModel) {
        ModelState state = new ModelState();
        state.lastMoveOp = 0;
        state.pair = null;
        state.f2683id = model2.mo11715id();
        state.position = position2;
        if (immutableModel) {
            state.model = model2;
        } else {
            state.hashCode = model2.hashCode();
        }
        return state;
    }

    /* access modifiers changed from: 0000 */
    public void pairWithSelf() {
        if (this.pair != null) {
            throw new IllegalStateException("Already paired.");
        }
        this.pair = new ModelState();
        this.pair.lastMoveOp = 0;
        this.pair.f2683id = this.f2683id;
        this.pair.position = this.position;
        this.pair.hashCode = this.hashCode;
        this.pair.pair = this;
        this.pair.model = this.model;
    }

    public String toString() {
        return "ModelState{id=" + this.f2683id + ", model=" + this.model + ", hashCode=" + this.hashCode + ", position=" + this.position + ", pair=" + this.pair + ", lastMoveOp=" + this.lastMoveOp + '}';
    }
}
