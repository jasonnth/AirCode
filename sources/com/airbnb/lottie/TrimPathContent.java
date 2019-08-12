package com.airbnb.lottie;

import java.util.ArrayList;
import java.util.List;

class TrimPathContent implements AnimationListener, Content {
    private final BaseKeyframeAnimation<?, Float> endAnimation;
    private final List<AnimationListener> listeners = new ArrayList();
    private String name;
    private final BaseKeyframeAnimation<?, Float> offsetAnimation;
    private final BaseKeyframeAnimation<?, Float> startAnimation;
    private final Type type;

    TrimPathContent(BaseLayer layer, ShapeTrimPath trimPath) {
        this.name = trimPath.getName();
        this.type = trimPath.getType();
        this.startAnimation = trimPath.getStart().createAnimation();
        this.endAnimation = trimPath.getEnd().createAnimation();
        this.offsetAnimation = trimPath.getOffset().createAnimation();
        layer.addAnimation(this.startAnimation);
        layer.addAnimation(this.endAnimation);
        layer.addAnimation(this.offsetAnimation);
        this.startAnimation.addUpdateListener(this);
        this.endAnimation.addUpdateListener(this);
        this.offsetAnimation.addUpdateListener(this);
    }

    public void onValueChanged() {
        for (int i = 0; i < this.listeners.size(); i++) {
            ((AnimationListener) this.listeners.get(i)).onValueChanged();
        }
    }

    public void setContents(List<Content> list, List<Content> list2) {
    }

    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: 0000 */
    public void addListener(AnimationListener listener) {
        this.listeners.add(listener);
    }

    /* access modifiers changed from: 0000 */
    public Type getType() {
        return this.type;
    }

    public BaseKeyframeAnimation<?, Float> getStart() {
        return this.startAnimation;
    }

    public BaseKeyframeAnimation<?, Float> getEnd() {
        return this.endAnimation;
    }

    public BaseKeyframeAnimation<?, Float> getOffset() {
        return this.offsetAnimation;
    }
}
