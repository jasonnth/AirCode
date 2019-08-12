package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.SimpleTitleContentRow_ViewBinding */
public class SimpleTitleContentRow_ViewBinding implements Unbinder {
    private SimpleTitleContentRow target;

    public SimpleTitleContentRow_ViewBinding(SimpleTitleContentRow target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", AirTextView.class);
        target2.content = (AirTextView) Utils.findRequiredViewAsType(source, R.id.content, "field 'content'", AirTextView.class);
    }

    public void unbind() {
        SimpleTitleContentRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.content = null;
    }
}
