package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.BottomButtonBar */
public class BottomButtonBar extends RelativeLayout {
    private boolean isLoading;
    @BindView
    LoadingView loadingView;
    private OnClickListener negativeAction;
    @BindView
    AirTextView negativeButtonText;
    private OnClickListener positiveAction;
    @BindView
    AirTextView positiveButtonText;

    public BottomButtonBar(Context context) {
        super(context);
        init();
    }

    public BottomButtonBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomButtonBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_bottom_button_bar, this);
        ButterKnife.bind((View) this);
        int areaForText = (int) ((((float) ViewLibUtils.getScreenWidth(getContext())) - (getResources().getDimension(R.dimen.n2_horizontal_padding_medium) * 2.0f)) - (getResources().getDimension(R.dimen.n2_horizontal_padding_small) * 2.0f));
        this.positiveButtonText.setMaxWidth(areaForText / 2);
        this.negativeButtonText.setMaxWidth(areaForText / 2);
    }

    public void setPositiveActionText(int stringRes) {
        setPositiveActionText((CharSequence) getResources().getString(stringRes));
    }

    public void setPositiveActionText(CharSequence text) {
        this.positiveButtonText.setText(text);
    }

    public void setPositiveAction(OnClickListener listener) {
        this.positiveAction = listener;
    }

    public void setNegativeActionText(int stringRes) {
        setNegativeActionText((CharSequence) getResources().getString(stringRes));
    }

    public void setNegativeActionText(CharSequence text) {
        this.negativeButtonText.setText(text);
    }

    public void setNegativeAction(OnClickListener listener) {
        this.negativeAction = listener;
    }

    public void setLoading(boolean isLoading2) {
        this.isLoading = isLoading2;
        if (isLoading2) {
            this.positiveButtonText.setVisibility(4);
            this.loadingView.setVisibility(0);
            return;
        }
        this.positiveButtonText.setVisibility(0);
        this.loadingView.setVisibility(4);
    }

    @OnClick
    public void positiveButtonClicked(View v) {
        if (!this.isLoading && this.positiveAction != null) {
            this.positiveAction.onClick(v);
        }
    }

    @OnClick
    public void negativeButtonClicked(View v) {
        if (!this.isLoading && this.negativeAction != null) {
            this.negativeAction.onClick(v);
        }
    }

    public View getView() {
        return this;
    }

    public static void mock(BottomButtonBar view) {
        view.setPositiveActionText((CharSequence) "Positive action");
        view.setNegativeActionText((CharSequence) "Negative action");
    }
}
