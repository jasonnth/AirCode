package com.airbnb.android.explore.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.collections.MaxWidthFrameLayout;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class FilterRemovalSuggestionPill extends MaxWidthFrameLayout {
    @BindView
    ImageView closeButton;
    @BindView
    AirTextView filterView;

    public FilterRemovalSuggestionPill(Context context) {
        super(context);
        init(null);
    }

    public FilterRemovalSuggestionPill(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FilterRemovalSuggestionPill(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), C0857R.layout.filter_removal_suggestion_pill, this);
        ButterKnife.bind((View) this);
    }

    public void setFilterText(int filterRes) {
        this.filterView.setText(filterRes);
    }

    public void setFilterText(CharSequence filterText) {
        this.filterView.setText(filterText);
    }

    public void showCloseButton(boolean show) {
        ViewLibUtils.setVisibleIf(this.closeButton, show);
    }
}
