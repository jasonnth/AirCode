package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.percent.PercentFrameLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

@Deprecated
/* renamed from: com.airbnb.n2.components.ImpactMarquee */
public class ImpactMarquee extends PercentFrameLayout implements DividerView {
    @BindView
    AirTextView subtitleTextView;
    @BindView
    AirTextView titleTextView;

    public ImpactMarquee(Context context) {
        super(context);
        init(null);
    }

    public ImpactMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ImpactMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_impact_marquee, this);
        ButterKnife.bind((View) this);
        setupAttrs(attrs);
    }

    private void setupAttrs(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_ImpactMarquee, 0, 0);
        String title = a.getString(R.styleable.n2_ImpactMarquee_n2_titleText);
        String subtitle = a.getString(R.styleable.n2_ImpactMarquee_n2_subtitleText);
        setTitle((CharSequence) title);
        setSubtitle(subtitle);
        a.recycle();
    }

    public void setTitle(int titleRes) {
        setTitle((CharSequence) getResources().getString(titleRes));
    }

    public void setTitle(CharSequence title) {
        ViewLibUtils.setVisibleIf(this.titleTextView, !TextUtils.isEmpty(title));
        this.titleTextView.setText(title);
    }

    public void setSubtitle(CharSequence subtitle) {
        ViewLibUtils.setVisibleIf(this.subtitleTextView, !TextUtils.isEmpty(subtitle));
        this.subtitleTextView.setText(subtitle);
    }

    public void showDivider(boolean showDivider) {
    }

    public static void mock(ImpactMarquee marquee) {
        marquee.setTitle((CharSequence) "Title");
        marquee.setSubtitle("Optional subtitle");
    }
}
