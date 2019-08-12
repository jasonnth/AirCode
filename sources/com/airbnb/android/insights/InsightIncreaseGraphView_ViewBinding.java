package com.airbnb.android.insights;

import android.content.Context;
import android.content.res.Resources;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import butterknife.Unbinder;

public class InsightIncreaseGraphView_ViewBinding implements Unbinder {
    public InsightIncreaseGraphView_ViewBinding(InsightIncreaseGraphView target) {
        this(target, target.getContext());
    }

    @Deprecated
    public InsightIncreaseGraphView_ViewBinding(InsightIncreaseGraphView target, View source) {
        this(target, source.getContext());
    }

    public InsightIncreaseGraphView_ViewBinding(InsightIncreaseGraphView target, Context context) {
        Resources res = context.getResources();
        target.babu = ContextCompat.getColor(context, C6552R.color.n2_babu);
        target.babuFilled = ContextCompat.getColor(context, C6552R.color.n2_babu_dark);
        target.barGraphHeight = res.getDimensionPixelSize(C6552R.dimen.insight_increase_graph_height);
        target.padding = res.getDimensionPixelSize(C6552R.dimen.insight_graph_padding);
        target.captionPadding = res.getDimensionPixelSize(C6552R.dimen.insight_graph_caption_padding);
        target.textSize = res.getDimensionPixelSize(C6552R.dimen.insight_increase_text_size);
    }

    public void unbind() {
    }
}
