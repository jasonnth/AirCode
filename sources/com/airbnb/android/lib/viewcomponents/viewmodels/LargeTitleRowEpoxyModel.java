package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;
import com.airbnb.p027n2.components.BigNumberRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class LargeTitleRowEpoxyModel extends AirEpoxyModel<BigNumberRow> {
    private static final long VALUE_ANIMATION_DURATION = 400;
    private BigNumberRow cachedView;
    CharSequence primarySubtitle;
    int primarySubtitleRes;
    CharSequence secondarySubtitle;
    int secondarySubtitleRes;
    CharSequence title;
    int titleRes;

    public interface TitleFormatter<T> {
        CharSequence format(T t);
    }

    public void bind(BigNumberRow view) {
        super.bind(view);
        this.cachedView = view;
        if (this.titleRes > 0) {
            view.setTitle(this.titleRes);
        } else {
            view.setTitle(this.title);
        }
        if (this.primarySubtitleRes > 0) {
            view.setPrimarySubtitle(this.primarySubtitleRes);
        } else {
            view.setPrimarySubtitle(this.primarySubtitle);
        }
        if (this.secondarySubtitleRes > 0) {
            view.setSecondarySubtitle(this.secondarySubtitleRes);
        } else {
            view.setSecondarySubtitle(this.secondarySubtitle);
        }
    }

    public void animateTitleFrom(int oldValue, int newValue, TitleFormatter<Integer> formatter) {
        if (this.cachedView == null) {
            this.title = formatter.format(Integer.valueOf(newValue));
            return;
        }
        ValueAnimator animator = ValueAnimator.ofInt(new int[]{oldValue, newValue});
        animator.setDuration(VALUE_ANIMATION_DURATION);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(LargeTitleRowEpoxyModel$$Lambda$1.lambdaFactory$(this, formatter));
        animator.start();
    }

    static /* synthetic */ void lambda$animateTitleFrom$0(LargeTitleRowEpoxyModel largeTitleRowEpoxyModel, TitleFormatter formatter, ValueAnimator valueAnimator) {
        int animatedValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        if (largeTitleRowEpoxyModel.cachedView != null) {
            largeTitleRowEpoxyModel.cachedView.setTitle(formatter.format(Integer.valueOf(animatedValue)));
        }
    }

    public void animateTitleFrom(float oldValue, float newValue, TitleFormatter<Float> formatter) {
        if (this.cachedView == null) {
            this.title = formatter.format(Float.valueOf(newValue));
            return;
        }
        ValueAnimator animator = ValueAnimator.ofFloat(new float[]{oldValue, newValue});
        animator.setDuration(VALUE_ANIMATION_DURATION);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(LargeTitleRowEpoxyModel$$Lambda$2.lambdaFactory$(this, formatter));
        animator.start();
    }

    static /* synthetic */ void lambda$animateTitleFrom$1(LargeTitleRowEpoxyModel largeTitleRowEpoxyModel, TitleFormatter formatter, ValueAnimator valueAnimator) {
        float animatedValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        if (largeTitleRowEpoxyModel.cachedView != null) {
            largeTitleRowEpoxyModel.cachedView.setTitle(formatter.format(Float.valueOf(animatedValue)));
        }
    }

    public int getDividerViewType() {
        return 0;
    }
}
