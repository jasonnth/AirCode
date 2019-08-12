package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.collections.MaxWidthFrameLayout;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.FilterSuggestionPill */
public class FilterSuggestionPill extends MaxWidthFrameLayout {
    @BindView
    AirTextView filterView;

    public FilterSuggestionPill(Context context) {
        super(context);
        init(null);
    }

    public FilterSuggestionPill(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FilterSuggestionPill(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_filter_suggestion_pill, this);
        ButterKnife.bind((View) this);
    }

    public void setFilterText(int filterRes) {
        this.filterView.setText(filterRes);
    }

    public void setFilterText(CharSequence filterText) {
        this.filterView.setText(filterText);
    }

    public static void mock(FilterSuggestionPill view) {
        view.setFilterText((CharSequence) "Entire home");
        view.setMaxWidth(ViewLibUtils.dpToPx(view.getContext(), 200.0f));
    }
}
