package com.airbnb.android.places.views;

import android.content.Context;
import android.content.res.Resources;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.places.C7627R;
import com.airbnb.p027n2.primitives.AirTextView;

public class ResyTimeSlotView_ViewBinding implements Unbinder {
    private ResyTimeSlotView target;

    public ResyTimeSlotView_ViewBinding(ResyTimeSlotView target2) {
        this(target2, target2);
    }

    public ResyTimeSlotView_ViewBinding(ResyTimeSlotView target2, View source) {
        this.target = target2;
        target2.titleView = (AirTextView) Utils.findRequiredViewAsType(source, C7627R.C7629id.title, "field 'titleView'", AirTextView.class);
        target2.subtitleView = (AirTextView) Utils.findRequiredViewAsType(source, C7627R.C7629id.subtitle, "field 'subtitleView'", AirTextView.class);
        Context context = source.getContext();
        Resources res = context.getResources();
        target2.invertedTextColor = ContextCompat.getColor(context, C7627R.color.n2_text_color_main_inverted);
        target2.actionableTextColor = ContextCompat.getColor(context, C7627R.color.n2_text_color_actionable);
        target2.sideMargin = res.getDimensionPixelSize(C7627R.dimen.n2_horizontal_padding_tiny);
        target2.selectedBackground = ContextCompat.getDrawable(context, C7627R.C7628drawable.n2_button_bar_button_background);
        target2.unselectedBackground = ContextCompat.getDrawable(context, C7627R.C7628drawable.n2_button_white_babu_border);
    }

    public void unbind() {
        ResyTimeSlotView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleView = null;
        target2.subtitleView = null;
    }
}
