package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.Resources;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import butterknife.Unbinder;
import com.airbnb.android.lib.C0880R;

public class ActionCardIncreaseGraphView_ViewBinding implements Unbinder {
    public ActionCardIncreaseGraphView_ViewBinding(ActionCardIncreaseGraphView target) {
        this(target, target.getContext());
    }

    @Deprecated
    public ActionCardIncreaseGraphView_ViewBinding(ActionCardIncreaseGraphView target, View source) {
        this(target, source.getContext());
    }

    public ActionCardIncreaseGraphView_ViewBinding(ActionCardIncreaseGraphView target, Context context) {
        Resources res = context.getResources();
        target.babu = ContextCompat.getColor(context, C0880R.color.n2_babu);
        target.babuFilled = ContextCompat.getColor(context, C0880R.color.n2_babu_dark);
        target.barGraphHeight = res.getDimensionPixelSize(C0880R.dimen.action_card_increase_graph_height);
        target.padding = res.getDimensionPixelSize(C0880R.dimen.action_card_graph_padding);
        target.captionPadding = res.getDimensionPixelSize(C0880R.dimen.action_card_graph_caption_padding);
        target.textSize = res.getDimensionPixelSize(C0880R.dimen.action_card_increase_text_size);
    }

    public void unbind() {
    }
}
