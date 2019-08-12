package com.airbnb.android.insights;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.TextRow;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;

public class LastInsightView extends LinearLayout {
    @BindView
    TextRow descriptionRow;
    @BindView
    DocumentMarquee documentMarquee;
    @BindView
    AirButton primaryButton;

    public LastInsightView(Context context) {
        super(context);
        init();
    }

    public LastInsightView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LastInsightView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C6552R.layout.last_insight_view, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
        setBackgroundResource(C6552R.C6553drawable.last_card_border_background);
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
        this.primaryButton.setState(loading ? State.Loading : State.Normal);
    }
}
