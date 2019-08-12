package com.airbnb.android.core.views;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.android.core.C0716R;
import com.airbnb.lottie.LottieAnimationView;

public class AnimatedLoadingOverlay extends LottieAnimationView {
    public AnimatedLoadingOverlay(Context context) {
        this(context, null);
    }

    public AnimatedLoadingOverlay(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimatedLoadingOverlay(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setBackgroundResource(C0716R.C0717drawable.loader_background);
        setAnimation("property-spinner.json");
        loop(true);
    }
}
