package com.airbnb.android.explore.views;

import android.content.Context;
import android.content.res.Resources;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import butterknife.Unbinder;
import com.airbnb.android.explore.C0857R;

public class ExploreInlineRangeSeekBar_ViewBinding implements Unbinder {
    public ExploreInlineRangeSeekBar_ViewBinding(ExploreInlineRangeSeekBar target) {
        this(target, target.getContext());
    }

    @Deprecated
    public ExploreInlineRangeSeekBar_ViewBinding(ExploreInlineRangeSeekBar target, View source) {
        this(target, source.getContext());
    }

    public ExploreInlineRangeSeekBar_ViewBinding(ExploreInlineRangeSeekBar target, Context context) {
        Resources res = context.getResources();
        target.graphColor = ContextCompat.getColor(context, C0857R.color.explore_inline_price_histogram_graph_color);
        target.selectedGraphColor = ContextCompat.getColor(context, C0857R.color.explore_inline_price_histogram_selected_graph_color);
        target.horizontalRangeBarColor = ContextCompat.getColor(context, C0857R.color.n2_babu);
        target.canvasHeight = res.getDimension(C0857R.dimen.explore_inline_price_histogram_height);
        target.graphHeight = res.getDimension(C0857R.dimen.explore_inline_price_histogram_graph_max_height);
        target.horizontalRangeHeight = res.getDimension(C0857R.dimen.explore_price_histogram_horizontal_range_bar_height);
        target.selectedHorizontalRangeHeight = res.getDimension(C0857R.dimen.explore_price_histogram_selected_horizontal_range_bar_height);
    }

    public void unbind() {
    }
}
