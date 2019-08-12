package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import butterknife.Unbinder;
import com.airbnb.android.lib.C0880R;

public class ActionCardTrendGraphView_ViewBinding implements Unbinder {
    public ActionCardTrendGraphView_ViewBinding(ActionCardTrendGraphView target) {
        this(target, target.getContext());
    }

    @Deprecated
    public ActionCardTrendGraphView_ViewBinding(ActionCardTrendGraphView target, View source) {
        this(target, source.getContext());
    }

    public ActionCardTrendGraphView_ViewBinding(ActionCardTrendGraphView target, Context context) {
        Resources res = context.getResources();
        target.tickHeight = res.getDimensionPixelSize(C0880R.dimen.action_card_tick_height);
        target.tickWidth = res.getDimensionPixelSize(C0880R.dimen.action_card_tick_stroke_width);
        target.graphStrokeWidth = res.getDimensionPixelSize(C0880R.dimen.action_card_graph_stroke_width);
        target.padding = res.getDimensionPixelSize(C0880R.dimen.action_card_graph_padding);
        target.graphAndTickPadding = res.getDimensionPixelSize(C0880R.dimen.action_card_graph_and_tick_padding);
    }

    public void unbind() {
    }
}
