package com.airbnb.android.core.views;

import android.content.Context;
import com.airbnb.android.core.C0716R;

public class SearchSuggestionViewHelper {
    private SearchSuggestionViewHelper() {
    }

    public static int getItemColumnCount(Context context) {
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int marginHorizontal = context.getResources().getDimensionPixelOffset(C0716R.dimen.search_suggestion_horizontal_margin);
        int itemMarginHorizontalMin = context.getResources().getDimensionPixelOffset(C0716R.dimen.search_suggestion_popular_item_margin_horizontal_min);
        int itemWidth = context.getResources().getDimensionPixelSize(C0716R.dimen.search_suggestion_popular_item_width);
        int columCount = (screenWidth - (marginHorizontal * 2)) / itemWidth;
        if ((columCount * itemWidth) + ((columCount - 1) * itemMarginHorizontalMin) > screenWidth - (marginHorizontal * 2)) {
            return columCount - 1;
        }
        return columCount;
    }

    public static int getItemMarginHorizontal(Context context) {
        return Math.min(context.getResources().getDimensionPixelOffset(C0716R.dimen.search_suggestion_popular_item_margin_horizontal_max), getPopularDestinationItemMarginHorizontal(context));
    }

    public static int getPopularDestinationItemMarginHorizontal(Context context) {
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int marginHorizontal = context.getResources().getDimensionPixelOffset(C0716R.dimen.search_suggestion_horizontal_margin);
        int itemWidth = context.getResources().getDimensionPixelSize(C0716R.dimen.search_suggestion_popular_item_width);
        int columCount = getItemColumnCount(context);
        return ((screenWidth - (marginHorizontal * 2)) - (columCount * itemWidth)) / (columCount - 1);
    }

    public static int getPopularDestinationItemVertical(Context context) {
        return Math.min(context.getResources().getDimensionPixelOffset(C0716R.dimen.search_suggestion_popular_item_margin_vertical_max), getItemMarginHorizontal(context));
    }
}
