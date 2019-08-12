package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.MessageInputOneRow_ViewBinding */
public class MessageInputOneRow_ViewBinding extends MessageInputRow_ViewBinding {
    private MessageInputOneRow target;

    public MessageInputOneRow_ViewBinding(MessageInputOneRow target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.icon = (AirImageView) Utils.findRequiredViewAsType(source, R.id.icon, "field 'icon'", AirImageView.class);
        target2.divider = Utils.findRequiredView(source, R.id.vertical_divider, "field 'divider'");
    }

    public void unbind() {
        MessageInputOneRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.icon = null;
        target2.divider = null;
        super.unbind();
    }
}
