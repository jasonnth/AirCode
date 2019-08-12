package com.airbnb.android.insights;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.TextRow;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class InsightView extends FrameLayout {
    @BindView
    TextRow descriptionRow;
    @BindView
    AirTextView dismissTextView;
    @BindView
    DocumentMarquee documentMarquee;
    @BindView
    InsightIncreaseGraphView increaseGraphView;
    @BindView
    AirButton primaryButton;
    @BindView
    AirTextView secondaryButton;
    @BindView
    InsightTrendGraphView trendGraphView;

    public InsightView(Context context) {
        super(context);
        init();
    }

    public InsightView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InsightView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C6552R.layout.insight_view, this);
        ButterKnife.bind((View) this);
        setBackgroundColor(-1);
    }

    public void setTitle(CharSequence title) {
        this.documentMarquee.setTitle(title);
    }

    public void setTitle(int titleRes) {
        setTitle((CharSequence) getContext().getString(titleRes));
    }

    public void setDescription(CharSequence description) {
        this.descriptionRow.setText(description);
    }

    public void setDescription(int descriptionRes) {
        setDescription((CharSequence) getContext().getString(descriptionRes));
    }

    public void setPrimaryButtonText(CharSequence text) {
        this.primaryButton.setText(text);
    }

    public void setPrimaryButtonText(int textRes) {
        setPrimaryButtonText((CharSequence) getContext().getString(textRes));
    }

    public void setPrimaryButtonClickListener(OnClickListener listener) {
        this.primaryButton.setOnClickListener(listener);
    }

    public void setPrimaryButtonLoading(boolean loading) {
        LayoutParams lp = this.primaryButton.getLayoutParams();
        if (loading) {
            lp.width = this.primaryButton.getWidth();
        } else {
            lp.width = -2;
        }
        this.primaryButton.setState(loading ? State.Loading : State.Normal);
    }

    public void setSecondaryButtonText(CharSequence text) {
        ViewLibUtils.setGoneIf(this.secondaryButton, TextUtils.isEmpty(text));
        this.secondaryButton.setText(text);
    }

    public void setSecondaryButtonText(int textRes) {
        setSecondaryButtonText((CharSequence) getContext().getString(textRes));
    }

    public void setSecondaryButtonClickListener(OnClickListener listener) {
        this.secondaryButton.setOnClickListener(listener);
    }

    public void setDismissButtonClickListener(OnClickListener listener) {
        this.dismissTextView.setOnClickListener(listener);
    }
}
