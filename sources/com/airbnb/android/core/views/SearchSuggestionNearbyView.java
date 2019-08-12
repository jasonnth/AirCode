package com.airbnb.android.core.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.SearchSuggestionItem;

public class SearchSuggestionNearbyView extends FrameLayout {
    private SearchSuggestionItem anywhereItem;
    private SearchSuggestionItem cityItem;
    @BindView
    LinearLayout itemContainer;
    private SearchSuggestionItem nearbyItem;

    public SearchSuggestionNearbyView(Context context) {
        super(context);
        init();
    }

    public SearchSuggestionNearbyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchSuggestionNearbyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C0716R.layout.search_suggestion_nearby_view, this);
        ButterKnife.bind((View) this);
        int itemHorizontalMargin = SearchSuggestionViewHelper.getItemMarginHorizontal(getContext());
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, itemHorizontalMargin, 0);
        this.nearbyItem = new SearchSuggestionItem(getContext());
        this.nearbyItem.setSizeFlexible(false);
        this.nearbyItem.setText(getContext().getString(C0716R.string.nearby));
        this.cityItem = new SearchSuggestionItem(getContext());
        this.cityItem.setSizeFlexible(false);
        this.anywhereItem = new SearchSuggestionItem(getContext());
        this.anywhereItem.setText(getContext().getString(C0716R.string.explore_anywhere));
        this.anywhereItem.setSizeFlexible(false);
        this.itemContainer.addView(this.nearbyItem, layoutParams);
        this.itemContainer.addView(this.cityItem, layoutParams);
        this.itemContainer.addView(this.anywhereItem, layoutParams);
        setBackgroundResource(C0716R.C0717drawable.n2_search_suggestion_background);
    }

    public void setNearbyVisible(boolean isVisible) {
        ViewUtils.setVisibleIf((View) this.nearbyItem, isVisible);
    }

    public void setCityName(String cityName) {
        boolean z = true;
        if (!TextUtils.isEmpty(cityName)) {
            this.cityItem.setText(getContext().getString(C0716R.string.search_suggestion_current_city, new Object[]{cityName}));
        }
        SearchSuggestionItem searchSuggestionItem = this.cityItem;
        if (TextUtils.isEmpty(cityName)) {
            z = false;
        }
        ViewUtils.setVisibleIf((View) searchSuggestionItem, z);
    }

    public void setNearbyItemClickListener(OnClickListener onClickListener) {
        this.nearbyItem.setOnClickListener(onClickListener);
    }

    public void setCityItemClickListener(OnClickListener onClickListener) {
        this.cityItem.setOnClickListener(onClickListener);
    }

    public void setAnywhereItemClickListener(OnClickListener onClickListener) {
        this.anywhereItem.setOnClickListener(onClickListener);
    }
}
