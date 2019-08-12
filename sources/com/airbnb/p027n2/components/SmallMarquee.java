package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.SmallMarquee */
public class SmallMarquee extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    AirTextView marqueeTitleView;

    public SmallMarquee(Context context) {
        super(context);
        init(null);
    }

    public SmallMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SmallMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_small_marquee, this);
        ButterKnife.bind((View) this);
        setupAttrs(attrs);
        setOrientation(1);
    }

    private void setupAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_SmallMarquee, 0, 0);
        setMarqueeTitle((CharSequence) ta.getString(R.styleable.n2_SmallMarquee_n2_marqueeTitleText));
        ta.recycle();
    }

    public void setMarqueeTitle(CharSequence title) {
        this.marqueeTitleView.setText(title);
    }

    public void setMarqueeTitle(int stringRes) {
        setMarqueeTitle((CharSequence) getContext().getString(stringRes));
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(SmallMarquee view) {
        view.setMarqueeTitle((CharSequence) "Title");
    }
}
