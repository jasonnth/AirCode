package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.MessageInputRow_ViewBinding */
public class MessageInputRow_ViewBinding implements Unbinder {
    private MessageInputRow target;

    public MessageInputRow_ViewBinding(MessageInputRow target2, View source) {
        this.target = target2;
        target2.sendButton = (AirTextView) Utils.findRequiredViewAsType(source, R.id.send_button, "field 'sendButton'", AirTextView.class);
        target2.imagePreview = (AirImageView) Utils.findRequiredViewAsType(source, R.id.img_preview, "field 'imagePreview'", AirImageView.class);
    }

    public void unbind() {
        MessageInputRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.sendButton = null;
        target2.imagePreview = null;
    }
}
