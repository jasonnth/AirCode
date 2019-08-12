package com.airbnb.android.lib.views;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.p000v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.HeroMarquee;

public class MarketingHeroMarquee extends HeroMarquee {
    private static boolean hasAnimated = false;

    public MarketingHeroMarquee(Context context) {
        super(context);
    }

    public MarketingHeroMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarketingHeroMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!hasAnimated) {
            startAnimation();
            hasAnimated = true;
        }
    }

    @SuppressLint({"NewApi"})
    private void startAnimation() {
        LayoutParams params = getLayoutParams();
        int screenHeight = ViewUtils.getScreenHeight(getContext());
        params.height = screenHeight;
        setLayoutParams(params);
        RelativeLayout.LayoutParams imageParams = (RelativeLayout.LayoutParams) this.imageView.getLayoutParams();
        if (AndroidVersion.isAtLeastJellyBeanMR1()) {
            imageParams.removeRule(6);
            imageParams.removeRule(8);
        }
        imageParams.height = -1;
        this.imageView.setLayoutParams(imageParams);
        ValueAnimator anim = ValueAnimator.ofInt(new int[]{screenHeight, getMeasuredHeight()});
        anim.addUpdateListener(MarketingHeroMarquee$$Lambda$1.lambdaFactory$(this));
        anim.setInterpolator(new FastOutSlowInInterpolator());
        anim.setStartDelay(1000);
        anim.setDuration(500);
        anim.start();
    }

    static /* synthetic */ void lambda$startAnimation$0(MarketingHeroMarquee marketingHeroMarquee, ValueAnimator animation) {
        LayoutParams lp = marketingHeroMarquee.getLayoutParams();
        lp.height = ((Integer) animation.getAnimatedValue()).intValue();
        marketingHeroMarquee.setLayoutParams(lp);
    }
}
