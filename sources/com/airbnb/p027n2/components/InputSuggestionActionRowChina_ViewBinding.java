package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.InputSuggestionActionRowChina_ViewBinding */
public final class InputSuggestionActionRowChina_ViewBinding implements Unbinder {
    private InputSuggestionActionRowChina target;

    public InputSuggestionActionRowChina_ViewBinding(InputSuggestionActionRowChina target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", AirTextView.class);
        target2.subtitle = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle, "field 'subtitle'", AirTextView.class);
        target2.label = (AirTextView) Utils.findRequiredViewAsType(source, R.id.label, "field 'label'", AirTextView.class);
    }

    public void unbind() {
        InputSuggestionActionRowChina target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.subtitle = null;
        target2.label = null;
    }
}
