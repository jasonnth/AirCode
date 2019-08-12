package com.airbnb.lottie;

import java.util.ArrayList;
import java.util.List;

abstract class BaseKeyframeAnimation<K, A> {
    private Keyframe<K> cachedKeyframe;
    private boolean isDiscrete = false;
    private final List<? extends Keyframe<K>> keyframes;
    final List<AnimationListener> listeners = new ArrayList();
    private float progress = 0.0f;

    interface AnimationListener {
        void onValueChanged();
    }

    /* access modifiers changed from: 0000 */
    public abstract A getValue(Keyframe<K> keyframe, float f);

    BaseKeyframeAnimation(List<? extends Keyframe<K>> keyframes2) {
        this.keyframes = keyframes2;
    }

    /* access modifiers changed from: 0000 */
    public void setIsDiscrete() {
        this.isDiscrete = true;
    }

    /* access modifiers changed from: 0000 */
    public void addUpdateListener(AnimationListener listener) {
        this.listeners.add(listener);
    }

    /* access modifiers changed from: 0000 */
    public void setProgress(float progress2) {
        if (progress2 < getStartDelayProgress()) {
            progress2 = 0.0f;
        } else if (progress2 > getEndProgress()) {
            progress2 = 1.0f;
        }
        if (progress2 != this.progress) {
            this.progress = progress2;
            for (int i = 0; i < this.listeners.size(); i++) {
                ((AnimationListener) this.listeners.get(i)).onValueChanged();
            }
        }
    }

    private Keyframe<K> getCurrentKeyframe() {
        if (this.keyframes.isEmpty()) {
            throw new IllegalStateException("There are no keyframes");
        } else if (this.cachedKeyframe != null && this.cachedKeyframe.containsProgress(this.progress)) {
            return this.cachedKeyframe;
        } else {
            int i = 0;
            Keyframe<K> keyframe = (Keyframe) this.keyframes.get(0);
            if (this.progress < keyframe.getStartProgress()) {
                this.cachedKeyframe = keyframe;
                return keyframe;
            }
            while (!keyframe.containsProgress(this.progress) && i < this.keyframes.size()) {
                keyframe = (Keyframe) this.keyframes.get(i);
                i++;
            }
            this.cachedKeyframe = keyframe;
            return keyframe;
        }
    }

    private float getCurrentKeyframeProgress() {
        if (this.isDiscrete) {
            return 0.0f;
        }
        Keyframe<K> keyframe = getCurrentKeyframe();
        if (keyframe.isStatic()) {
            return 0.0f;
        }
        return keyframe.interpolator.getInterpolation((this.progress - keyframe.getStartProgress()) / (keyframe.getEndProgress() - keyframe.getStartProgress()));
    }

    private float getStartDelayProgress() {
        if (this.keyframes.isEmpty()) {
            return 0.0f;
        }
        return ((Keyframe) this.keyframes.get(0)).getStartProgress();
    }

    private float getEndProgress() {
        if (this.keyframes.isEmpty()) {
            return 1.0f;
        }
        return ((Keyframe) this.keyframes.get(this.keyframes.size() - 1)).getEndProgress();
    }

    public A getValue() {
        return getValue(getCurrentKeyframe(), getCurrentKeyframeProgress());
    }

    /* access modifiers changed from: 0000 */
    public float getProgress() {
        return this.progress;
    }
}
