package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class MessageImageFullScreenFragment_ViewBinding implements Unbinder {
    private MessageImageFullScreenFragment target;

    public MessageImageFullScreenFragment_ViewBinding(MessageImageFullScreenFragment target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.image, "field 'imageView'", AirImageView.class);
    }

    public void unbind() {
        MessageImageFullScreenFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
    }
}
