package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.ButtonBar_ViewBinding */
public class ButtonBar_ViewBinding implements Unbinder {
    private ButtonBar target;

    public ButtonBar_ViewBinding(ButtonBar target2, View source) {
        this.target = target2;
        target2.button1 = Utils.findRequiredView(source, R.id.button1, "field 'button1'");
        target2.button2 = Utils.findRequiredView(source, R.id.button2, "field 'button2'");
        target2.button3 = Utils.findRequiredView(source, R.id.button3, "field 'button3'");
        target2.button4 = Utils.findRequiredView(source, R.id.button4, "field 'button4'");
        target2.icon1 = (AirImageView) Utils.findRequiredViewAsType(source, R.id.icon1, "field 'icon1'", AirImageView.class);
        target2.icon2 = (AirImageView) Utils.findRequiredViewAsType(source, R.id.icon2, "field 'icon2'", AirImageView.class);
        target2.icon3 = (AirImageView) Utils.findRequiredViewAsType(source, R.id.icon3, "field 'icon3'", AirImageView.class);
        target2.icon4 = (AirImageView) Utils.findRequiredViewAsType(source, R.id.icon4, "field 'icon4'", AirImageView.class);
        target2.label1 = (AirTextView) Utils.findRequiredViewAsType(source, R.id.label1, "field 'label1'", AirTextView.class);
        target2.label2 = (AirTextView) Utils.findRequiredViewAsType(source, R.id.label2, "field 'label2'", AirTextView.class);
        target2.label3 = (AirTextView) Utils.findRequiredViewAsType(source, R.id.label3, "field 'label3'", AirTextView.class);
        target2.label4 = (AirTextView) Utils.findRequiredViewAsType(source, R.id.label4, "field 'label4'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
    }

    public void unbind() {
        ButtonBar target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.button1 = null;
        target2.button2 = null;
        target2.button3 = null;
        target2.button4 = null;
        target2.icon1 = null;
        target2.icon2 = null;
        target2.icon3 = null;
        target2.icon4 = null;
        target2.label1 = null;
        target2.label2 = null;
        target2.label3 = null;
        target2.label4 = null;
        target2.divider = null;
    }
}
