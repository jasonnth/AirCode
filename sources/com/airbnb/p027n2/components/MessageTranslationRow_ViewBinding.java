package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.MessageTranslationRow_ViewBinding */
public class MessageTranslationRow_ViewBinding implements Unbinder {
    private MessageTranslationRow target;

    public MessageTranslationRow_ViewBinding(MessageTranslationRow target2, View source) {
        this.target = target2;
        target2.textViewStatus = (AirTextView) Utils.findRequiredViewAsType(source, R.id.text_status, "field 'textViewStatus'", AirTextView.class);
        target2.airImageCaption = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image_caption, "field 'airImageCaption'", AirImageView.class);
        target2.loaderView = Utils.findRequiredView(source, R.id.loader_view, "field 'loaderView'");
    }

    public void unbind() {
        MessageTranslationRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.textViewStatus = null;
        target2.airImageCaption = null;
        target2.loaderView = null;
    }
}
