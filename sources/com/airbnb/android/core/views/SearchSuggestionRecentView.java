package com.airbnb.android.core.views;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.SavedSearch;
import com.airbnb.android.core.models.SearchParams;
import com.airbnb.p027n2.components.SearchSuggestionItem;
import java.util.ArrayList;
import java.util.List;

public class SearchSuggestionRecentView extends LinearLayout {
    private OnItemClickListener itemClickListener;
    @BindView
    LinearLayout itemContainer;

    public interface OnItemClickListener {
        void onItemClick(SavedSearch savedSearch);
    }

    public SearchSuggestionRecentView(Context context) {
        super(context);
        init();
    }

    public SearchSuggestionRecentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchSuggestionRecentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        setGravity(3);
        inflate(getContext(), C0716R.layout.search_suggestion_recent_view, this);
        setBackgroundResource(C0716R.C0717drawable.n2_search_suggestion_background);
        ButterKnife.bind((View) this);
    }

    public void setRecentSearchItem(List<SavedSearch> savedSearchList) {
        this.itemContainer.removeAllViews();
        int subitemHorizontalMargin = SearchSuggestionViewHelper.getItemMarginHorizontal(getContext());
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, subitemHorizontalMargin, 0);
        for (SavedSearch savedSearch : savedSearchList) {
            SearchSuggestionItem item = new SearchSuggestionItem(getContext());
            item.setSizeFlexible(false);
            item.setText(makeItemText(savedSearch));
            item.setOnClickListener(SearchSuggestionRecentView$$Lambda$1.lambdaFactory$(this, savedSearch));
            this.itemContainer.addView(item, layoutParams);
        }
    }

    static /* synthetic */ void lambda$setRecentSearchItem$0(SearchSuggestionRecentView searchSuggestionRecentView, SavedSearch savedSearch, View v) {
        if (searchSuggestionRecentView.itemClickListener != null) {
            searchSuggestionRecentView.itemClickListener.onItemClick(savedSearch);
        }
    }

    public void setItemClickListener(OnItemClickListener itemClickListener2) {
        this.itemClickListener = itemClickListener2;
    }

    private String makeItemText(SavedSearch savedSearch) {
        String formattedLocation;
        Context context = getContext();
        Resources res = context.getResources();
        SearchParams searchParams = savedSearch.getSearchParams();
        String location = savedSearch.getSearchParams().getLocation();
        if (TextUtils.isEmpty(location)) {
            formattedLocation = context.getResources().getString(C0716R.string.explore_anywhere);
        } else {
            formattedLocation = location;
        }
        String formattedDate = searchParams.getCheckin() == null ? null : searchParams.getCheckin().getDateSpanString(context, searchParams.getCheckout());
        int numGuests = searchParams.getGuests();
        String formattedGuests = res.getQuantityString(C0716R.plurals.x_guests, numGuests, new Object[]{Integer.valueOf(numGuests)});
        List<String> subtitleStringList = new ArrayList<>();
        subtitleStringList.add(formattedLocation);
        if (!TextUtils.isEmpty(formattedDate)) {
            subtitleStringList.add(formattedDate);
        }
        if (numGuests != 0) {
            subtitleStringList.add(formattedGuests);
        }
        return TextUtils.join(res.getString(C0716R.string.room_type_comma), subtitleStringList);
    }
}
