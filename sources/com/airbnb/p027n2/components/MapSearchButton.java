package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.PillDrawableFactory;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.MapSearchButton */
public class MapSearchButton extends FrameLayout {
    private Drawable defaultDrawable;
    private Drawable loadingDrawable;
    @BindView
    LoadingView loadingView;
    @BindView
    AirTextView textView;

    public MapSearchButton(Context context) {
        super(context);
        init(null);
    }

    public MapSearchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MapSearchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_explore_map_refresh_button, this);
        ButterKnife.bind((View) this);
        PillDrawableFactory factory = new PillDrawableFactory(getContext());
        this.defaultDrawable = factory.color(R.color.n2_babu).preLollipopPressedColor(R.color.n2_babu_dark).build();
        factory.reset();
        this.loadingDrawable = factory.color(17170443).build();
        setBackground(this.defaultDrawable);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_MapSearchButton, 0, 0);
        this.textView.setText(a.getString(R.styleable.n2_MapSearchButton_n2_buttonText));
        a.recycle();
    }

    public void setText(CharSequence text) {
        this.textView.setText(text);
    }

    public void show(boolean loading) {
        setClickable(!loading);
        setVisibility(0);
        ViewLibUtils.setVisibleIf(this.loadingView, loading);
        ViewLibUtils.setGoneIf(this.textView, loading);
        setBackground(loading ? this.loadingDrawable : this.defaultDrawable);
    }

    public boolean isShown() {
        return getVisibility() == 0;
    }

    public void hide() {
        setVisibility(8);
    }

    public static void mock(MapSearchButton button) {
        button.setText("Text");
    }
}
