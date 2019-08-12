package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.Space;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.InputSuggestionActionRow_ViewBinding */
public final class InputSuggestionActionRow_ViewBinding implements Unbinder {
    private InputSuggestionActionRow target;

    public InputSuggestionActionRow_ViewBinding(InputSuggestionActionRow target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.input_suggestion_action_row_title, "field 'title'", AirTextView.class);
        target2.subtitle = (AirTextView) Utils.findRequiredViewAsType(source, R.id.input_suggestion_action_row_subtitle, "field 'subtitle'", AirTextView.class);
        target2.label = (AirTextView) Utils.findRequiredViewAsType(source, R.id.input_suggestion_action_row_label, "field 'label'", AirTextView.class);
        target2.space = (Space) Utils.findRequiredViewAsType(source, R.id.input_suggestion_action_row_space, "field 'space'", Space.class);
        target2.iconView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.input_suggestion_action_row_icon, "field 'iconView'", AirImageView.class);
    }

    public void unbind() {
        InputSuggestionActionRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.subtitle = null;
        target2.label = null;
        target2.space = null;
        target2.iconView = null;
    }
}
