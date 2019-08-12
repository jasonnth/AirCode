package p334uk.p335co.deanwild.materialshowcaseview;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import p334uk.p335co.deanwild.materialshowcaseview.IAnimationFactory.AnimationEndListener;
import p334uk.p335co.deanwild.materialshowcaseview.IAnimationFactory.AnimationStartListener;

/* renamed from: uk.co.deanwild.materialshowcaseview.AnimationFactory */
public class AnimationFactory implements IAnimationFactory {
    private final AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();

    public void fadeInView(View target, long duration, final AnimationStartListener listener) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(target, "alpha", new float[]{0.0f, 1.0f});
        oa.setDuration(duration).addListener(new AnimatorListener() {
            public void onAnimationStart(Animator animator) {
                listener.onAnimationStart();
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
        oa.start();
    }

    public void fadeOutView(View target, long duration, final AnimationEndListener listener) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(target, "alpha", new float[]{0.0f});
        oa.setDuration(duration).addListener(new AnimatorListener() {
            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                listener.onAnimationEnd();
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
        oa.start();
    }
}
