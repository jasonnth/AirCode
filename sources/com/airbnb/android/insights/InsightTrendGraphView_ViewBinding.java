package com.airbnb.android.insights;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import butterknife.Unbinder;

public class InsightTrendGraphView_ViewBinding implements Unbinder {
    public InsightTrendGraphView_ViewBinding(InsightTrendGraphView target) {
        this(target, target.getContext());
    }

    @Deprecated
    public InsightTrendGraphView_ViewBinding(InsightTrendGraphView target, View source) {
        this(target, source.getContext());
    }

    public InsightTrendGraphView_ViewBinding(InsightTrendGraphView target, Context context) {
        Resources res = context.getResources();
        target.tickHeight = res.getDimensionPixelSize(C6552R.dimen.insight_tick_height);
        target.tickWidth = res.getDimensionPixelSize(C6552R.dimen.insight_tick_stroke_width);
        target.graphStrokeWidth = res.getDimensionPixelSize(C6552R.dimen.insight_graph_stroke_width);
        target.padding = res.getDimensionPixelSize(C6552R.dimen.insight_graph_padding);
        target.graphAndTickPadding = res.getDimensionPixelSize(C6552R.dimen.insight_graph_and_tick_padding);
    }

    public void unbind() {
    }
}
