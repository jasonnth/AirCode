package com.airbnb.p027n2.components;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirSwitch;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.TweenRow_ViewBinding */
public class TweenRow_ViewBinding implements Unbinder {
    private TweenRow target;

    public TweenRow_ViewBinding(TweenRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.inputText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.user_input, "field 'inputText'", AirTextView.class);
        target2.switchView = (AirSwitch) Utils.findRequiredViewAsType(source, R.id.switch_view, "field 'switchView'", AirSwitch.class);
        target2.textContainer = (ViewGroup) Utils.findRequiredViewAsType(source, R.id.text_container, "field 'textContainer'", ViewGroup.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.optional_subtitle, "field 'subtitleText'", AirTextView.class);
        target2.sectionDivider = Utils.findRequiredView(source, R.id.section_divider, "field 'sectionDivider'");
        target2.minInputTextWidth = source.getContext().getResources().getDimensionPixelSize(R.dimen.n2_standard_row_min_input_text_width);
    }

    public void unbind() {
        TweenRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.inputText = null;
        target2.switchView = null;
        target2.textContainer = null;
        target2.subtitleText = null;
        target2.sectionDivider = null;
    }
}
