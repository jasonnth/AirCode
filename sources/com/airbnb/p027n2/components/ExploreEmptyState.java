package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.ExploreEmptyState */
public class ExploreEmptyState extends LinearLayout implements DividerView {
    @BindView
    AirButton button;
    @BindView
    View divider;
    @BindView
    AirTextView title;

    public ExploreEmptyState(Context context) {
        super(context);
        init();
    }

    public ExploreEmptyState(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExploreEmptyState(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        inflate(getContext(), R.layout.n2_explore_empty_state, this);
        ButterKnife.bind((View) this);
    }

    public void setButtonClickListener(OnClickListener listener) {
        this.button.setOnClickListener(listener);
    }

    public void setTitle(CharSequence text) {
        this.title.setText(text);
    }

    public void setTitle(int stringId) {
        setTitle((CharSequence) getResources().getString(stringId));
    }

    public void setButton(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.button, !TextUtils.isEmpty(text));
        this.button.setText(text);
    }

    public void setButton(int stringId) {
        setButton((CharSequence) getResources().getString(stringId));
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(ExploreEmptyState view) {
        view.setTitle((CharSequence) "No listings found. Please try removing your filters.");
        view.setButton((CharSequence) "Remove all filters");
    }
}
