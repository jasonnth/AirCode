package com.airbnb.p027n2.utils;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.view.View;

/* renamed from: com.airbnb.n2.utils.ObjectAnimatorFactory */
public class ObjectAnimatorFactory {
    private final ObjectAnimator animator;
    /* access modifiers changed from: private */
    public AnimatorStepListener cancelStepAction;
    private int duration = 200;
    /* access modifiers changed from: private */
    public AnimatorStepListener endStepAction;
    private final SimpleAnimatorListener listener;
    /* access modifiers changed from: private */
    public AnimatorStepListener repeatStepAction;
    private int startDelay;
    /* access modifiers changed from: private */
    public AnimatorStepListener startStepAction;

    /* renamed from: com.airbnb.n2.utils.ObjectAnimatorFactory$AnimatorStepListener */
    public interface AnimatorStepListener {
        void onAnimatorEvent(Animator animator);
    }

    /* renamed from: com.airbnb.n2.utils.ObjectAnimatorFactory$SimpleAnimatorListener */
    private class SimpleAnimatorListener implements AnimatorListener {
        private SimpleAnimatorListener() {
        }

        public void onAnimationStart(Animator animation) {
            if (ObjectAnimatorFactory.this.startStepAction != null) {
                ObjectAnimatorFactory.this.startStepAction.onAnimatorEvent(animation);
            }
        }

        public void onAnimationEnd(Animator animation) {
            if (ObjectAnimatorFactory.this.endStepAction != null) {
                ObjectAnimatorFactory.this.endStepAction.onAnimatorEvent(animation);
            }
        }

        public void onAnimationCancel(Animator animation) {
            if (ObjectAnimatorFactory.this.cancelStepAction != null) {
                ObjectAnimatorFactory.this.cancelStepAction.onAnimatorEvent(animation);
            }
        }

        public void onAnimationRepeat(Animator animation) {
            if (ObjectAnimatorFactory.this.repeatStepAction != null) {
                ObjectAnimatorFactory.this.repeatStepAction.onAnimatorEvent(animation);
            }
        }
    }

    public static ObjectAnimatorFactory fade(View view, boolean show) {
        return show ? fadeIn(view) : fadeOut(view);
    }

    public static ObjectAnimatorFactory fadeIn(View view) {
        return new ObjectAnimatorFactory(ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.0f, 1.0f}));
    }

    public static ObjectAnimatorFactory fadeOut(View view) {
        return new ObjectAnimatorFactory(ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 0.0f}));
    }

    private ObjectAnimatorFactory(ObjectAnimator animator2) {
        this.animator = animator2;
        this.listener = new SimpleAnimatorListener();
    }

    public ObjectAnimatorFactory setDuration(int duration2) {
        this.duration = duration2;
        return this;
    }

    public ObjectAnimatorFactory setStartDelay(int startDelay2) {
        this.startDelay = startDelay2;
        return this;
    }

    public ObjectAnimatorFactory onStartStep(AnimatorStepListener startStepAction2) {
        this.startStepAction = startStepAction2;
        return this;
    }

    public ObjectAnimatorFactory onEndStep(AnimatorStepListener endStepAction2) {
        this.endStepAction = endStepAction2;
        return this;
    }

    public void buildAndStart() {
        this.animator.setDuration((long) this.duration);
        this.animator.setStartDelay((long) this.startDelay);
        this.animator.addListener(this.listener);
        this.animator.start();
    }
}
