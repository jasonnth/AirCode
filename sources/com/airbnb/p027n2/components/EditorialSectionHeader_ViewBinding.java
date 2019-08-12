package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.EditorialSectionHeader_ViewBinding */
public class EditorialSectionHeader_ViewBinding implements Unbinder {
    private EditorialSectionHeader target;

    public EditorialSectionHeader_ViewBinding(EditorialSectionHeader target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", AirTextView.class);
        target2.description = (AirTextView) Utils.findRequiredViewAsType(source, R.id.description, "field 'description'", AirTextView.class);
        target2.topSpace = Utils.findRequiredView(source, R.id.top_space, "field 'topSpace'");
        target2.button = (AirTextView) Utils.findRequiredViewAsType(source, R.id.button, "field 'button'", AirTextView.class);
    }

    public void unbind() {
        EditorialSectionHeader target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.description = null;
        target2.topSpace = null;
        target2.button = null;
    }
}
