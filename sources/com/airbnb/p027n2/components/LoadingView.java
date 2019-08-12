package com.airbnb.p027n2.components;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.LoadingDrawable;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.LoadingView */
public class LoadingView extends View implements DividerView {
    private final LoadingDrawable loaderDrawable = new LoadingDrawable();

    public LoadingView(Context context) {
        super(context);
        init(null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setBackground(this.loaderDrawable);
        Paris.style(this).apply(attrs);
    }

    public void setColor(int color) {
        this.loaderDrawable.setColor(color);
    }

    public void setColorRes(int colorRes) {
        setColor(ContextCompat.getColor(getContext(), colorRes));
    }

    public void showDivider(boolean showDivider) {
    }
}
