package com.airbnb.p027n2.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.SearchParamsRow */
public final class SearchParamsRow extends LinearLayout {
    @BindView
    AirTextView detailsText;
    @BindView
    AirTextView locationText;

    public SearchParamsRow(Context context) {
        super(context);
    }

    public SearchParamsRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SearchParamsRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void setDetailsText(CharSequence details) {
        this.detailsText.setText(details);
    }

    public void setLocationText(CharSequence location) {
        this.locationText.setText(location);
    }

    @SuppressLint({"NewApi"})
    private void init(AttributeSet attrs) {
        setOrientation(0);
        inflate(getContext(), R.layout.n2_search_params_row, this);
        ButterKnife.bind((View) this);
    }

    public static void mock(SearchParamsRow view) {
        view.setDetailsText("Apr 10 - Apr 12 · 2 guests");
        view.setLocationText("San Francisco");
    }
}
