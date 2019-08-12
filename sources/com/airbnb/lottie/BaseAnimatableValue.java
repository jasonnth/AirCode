package com.airbnb.lottie;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

abstract class BaseAnimatableValue<V, O> implements AnimatableValue<O> {
    final V initialValue;
    final List<Keyframe<V>> keyframes;

    BaseAnimatableValue(V initialValue2) {
        this(Collections.emptyList(), initialValue2);
    }

    BaseAnimatableValue(List<Keyframe<V>> keyframes2, V initialValue2) {
        this.keyframes = keyframes2;
        this.initialValue = initialValue2;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [V, O] */
    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=V, code=null, for r1v0, types: [V, O] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public O convertType(V r1) {
        /*
            r0 = this;
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.BaseAnimatableValue.convertType(java.lang.Object):java.lang.Object");
    }

    public boolean hasAnimation() {
        return !this.keyframes.isEmpty();
    }

    public O getInitialValue() {
        return convertType(this.initialValue);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("parseInitialValue=").append(this.initialValue);
        if (!this.keyframes.isEmpty()) {
            sb.append(", values=").append(Arrays.toString(this.keyframes.toArray()));
        }
        return sb.toString();
    }
}
