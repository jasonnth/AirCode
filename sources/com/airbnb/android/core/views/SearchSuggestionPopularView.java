package com.airbnb.android.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.PopularDestination;
import com.airbnb.android.core.models.PopularDestinationGroup;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.components.SearchSuggestionItem;
import com.airbnb.p027n2.primitives.AirTextView;
import java.util.Collection;
import java.util.List;

public class SearchSuggestionPopularView extends LinearLayout {
    private int columCount;
    private int currentTabIndex;
    @BindView
    GridLayout gridLayout;
    private OnItemClickListener itemClickListener;
    private int itemMarginHorizontal;
    private int itemMarginVertical;
    private int itemWidth;
    private List<PopularDestinationGroup> popularDestinations;
    @BindView
    LinearLayout tabLayout;

    public interface OnItemClickListener {
        void onItemClick(String str);
    }

    public SearchSuggestionPopularView(Context context) {
        super(context);
        init();
    }

    public SearchSuggestionPopularView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchSuggestionPopularView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        setGravity(3);
        inflate(getContext(), C0716R.layout.search_suggestion_popular_view, this);
        int itemHorizontalMargin = getContext().getResources().getDimensionPixelSize(C0716R.dimen.search_suggestion_horizontal_margin);
        setPadding(itemHorizontalMargin, 0, itemHorizontalMargin, 0);
        ButterKnife.bind((View) this);
        setupGridLayout();
    }

    private void setupGridLayout() {
        this.itemWidth = getContext().getResources().getDimensionPixelSize(C0716R.dimen.search_suggestion_popular_item_width);
        this.itemMarginHorizontal = SearchSuggestionViewHelper.getPopularDestinationItemMarginHorizontal(getContext());
        this.itemMarginVertical = SearchSuggestionViewHelper.getPopularDestinationItemVertical(getContext());
        this.columCount = SearchSuggestionViewHelper.getItemColumnCount(getContext());
        this.gridLayout.setColumnCount(this.columCount);
    }

    public void setPopularDestinations(List<PopularDestinationGroup> popularDestinations2) {
        this.popularDestinations = popularDestinations2;
        this.tabLayout.removeAllViews();
        this.currentTabIndex = -1;
        for (PopularDestinationGroup group : popularDestinations2) {
            AirTextView tab = (AirTextView) inflate(getContext(), C0716R.layout.search_suggestion_popular_tab_bar_item, null);
            tab.setText(group.getName());
            tab.setOnClickListener(SearchSuggestionPopularView$$Lambda$1.lambdaFactory$(this, tab));
            LayoutParams params = new LayoutParams(-2, -2);
            params.setMargins(0, 0, getContext().getResources().getDimensionPixelSize(C0716R.dimen.search_suggestion_tab_horizontal_margin), 0);
            this.tabLayout.addView(tab, params);
            if (this.currentTabIndex == -1) {
                this.currentTabIndex = 0;
                tab.setSelected(true);
            }
        }
        refreshGridLayout();
    }

    static /* synthetic */ void lambda$setPopularDestinations$0(SearchSuggestionPopularView searchSuggestionPopularView, AirTextView tab, View view) {
        for (int i = 0; i < searchSuggestionPopularView.tabLayout.getChildCount(); i++) {
            KeyboardUtils.dismissSoftKeyboard((View) tab);
            View child = searchSuggestionPopularView.tabLayout.getChildAt(i);
            if (child == view) {
                searchSuggestionPopularView.currentTabIndex = i;
                view.setSelected(true);
                searchSuggestionPopularView.refreshGridLayout();
            } else {
                child.setSelected(false);
            }
        }
    }

    public void setItemClickListener(OnItemClickListener itemClickListener2) {
        this.itemClickListener = itemClickListener2;
    }

    private void refreshGridLayout() {
        boolean isLastInRow;
        this.gridLayout.removeAllViews();
        if (this.currentTabIndex != -1) {
            PopularDestinationGroup group = (PopularDestinationGroup) this.popularDestinations.get(this.currentTabIndex);
            if (!ListUtils.isEmpty((Collection<?>) group.getDestinations())) {
                for (int i = 0; i < group.getDestinations().size(); i++) {
                    String locale = ((PopularDestination) group.getDestinations().get(i)).getLocale();
                    SearchSuggestionItem item = new SearchSuggestionItem(getContext());
                    item.setSizeFlexible(true);
                    item.setText(locale);
                    item.setOnClickListener(SearchSuggestionPopularView$$Lambda$2.lambdaFactory$(this, locale));
                    if (i % this.columCount == this.columCount - 1) {
                        isLastInRow = true;
                    } else {
                        isLastInRow = false;
                    }
                    GridLayout.LayoutParams params = new GridLayout.LayoutParams(new MarginLayoutParams(this.itemWidth, -2));
                    params.setMargins(0, 0, isLastInRow ? 0 : this.itemMarginHorizontal, this.itemMarginVertical);
                    this.gridLayout.addView(item, params);
                }
            }
        }
    }

    static /* synthetic */ void lambda$refreshGridLayout$1(SearchSuggestionPopularView searchSuggestionPopularView, String locale, View v) {
        if (searchSuggestionPopularView.itemClickListener != null) {
            searchSuggestionPopularView.itemClickListener.onItemClick(locale);
        }
    }
}
