package com.airbnb.lottie;

import android.graphics.Path;
import java.util.ArrayList;
import java.util.List;

class MaskKeyframeAnimation {
    private final List<BaseKeyframeAnimation<?, Path>> maskAnimations;
    private final List<Mask> masks;

    MaskKeyframeAnimation(List<Mask> masks2) {
        this.masks = masks2;
        this.maskAnimations = new ArrayList(masks2.size());
        for (int i = 0; i < masks2.size(); i++) {
            this.maskAnimations.add(((Mask) masks2.get(i)).getMaskPath().createAnimation());
        }
    }

    /* access modifiers changed from: 0000 */
    public List<Mask> getMasks() {
        return this.masks;
    }

    /* access modifiers changed from: 0000 */
    public List<BaseKeyframeAnimation<?, Path>> getMaskAnimations() {
        return this.maskAnimations;
    }
}
