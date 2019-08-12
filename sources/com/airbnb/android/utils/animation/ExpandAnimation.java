package com.airbnb.android.utils.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout.LayoutParams;

public class ExpandAnimation extends ValueAnimator implements AnimatorUpdateListener {
    private View view;

    public ExpandAnimation(final View view2) {
        init(view2);
        final int initialHeight = view2.getLayoutParams().height;
        setIntValues(new int[]{0, getViewHeight(view2)});
        addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animation) {
                view2.setLayerType(0, null);
                view2.getLayoutParams().height = initialHeight;
                view2.requestLayout();
            }
        });
    }

    public ExpandAnimation(final View view2, int startAnimationHeight, final int endHeight) {
        init(view2);
        setIntValues(new int[]{startAnimationHeight, endHeight});
        addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animation) {
                view2.setLayerType(0, null);
                view2.setLayoutParams(new LayoutParams(view2.getLayoutParams().width, endHeight));
            }
        });
    }

    private void init(View view2) {
        this.view = view2;
        view2.setLayerType(2, null);
        addUpdateListener(this);
    }

    public void onAnimationUpdate(ValueAnimator animation) {
        this.view.getLayoutParams().height = ((Integer) animation.getAnimatedValue()).intValue();
        this.view.requestLayout();
    }

    private int getViewHeight(View view2) {
        view2.measure(MeasureSpec.makeMeasureSpec(view2.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(0, 0));
        return view2.getMeasuredHeight();
    }
}
