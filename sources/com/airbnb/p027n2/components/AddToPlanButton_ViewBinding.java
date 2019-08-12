package com.airbnb.p027n2.components;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.AddToPlanButton_ViewBinding */
public class AddToPlanButton_ViewBinding implements Unbinder {
    private AddToPlanButton target;

    public AddToPlanButton_ViewBinding(AddToPlanButton target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", AirTextView.class);
        target2.subtitle = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle, "field 'subtitle'", AirTextView.class);
        Context context = source.getContext();
        target2.invertedTextColor = ContextCompat.getColor(context, R.color.n2_text_color_main_inverted);
        target2.actionableTextColor = ContextCompat.getColor(context, R.color.n2_text_color_actionable);
        target2.selectedBackground = ContextCompat.getDrawable(context, R.drawable.n2_button_bar_button_background);
        target2.unselectedBackground = ContextCompat.getDrawable(context, R.drawable.n2_button_white_babu_border);
    }

    public void unbind() {
        AddToPlanButton target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.subtitle = null;
    }
}
