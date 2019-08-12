package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.RefreshLoader */
public final class RefreshLoader extends BaseComponent {
    @BindView
    LoadingView loadingView;

    public RefreshLoader(Context context) {
        super(context);
    }

    public RefreshLoader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshLoader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_refresh_loader;
    }

    public static void mock(RefreshLoader view) {
    }

    public static void mockCarousel(RefreshLoader view) {
        Paris.style(view).applyCarousel();
    }

    public static void mockInverse(RefreshLoader view) {
        Paris.style(view).applyInverse();
    }
}
