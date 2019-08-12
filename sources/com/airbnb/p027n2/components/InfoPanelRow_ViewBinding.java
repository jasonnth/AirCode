package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.InfoPanelRow_ViewBinding */
public class InfoPanelRow_ViewBinding implements Unbinder {
    private InfoPanelRow target;

    public InfoPanelRow_ViewBinding(InfoPanelRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.contentText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.content, "field 'contentText'", AirTextView.class);
    }

    public void unbind() {
        InfoPanelRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.contentText = null;
    }
}
