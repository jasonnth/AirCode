package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.CategorizedFilterButtons_ViewBinding */
public class CategorizedFilterButtons_ViewBinding implements Unbinder {
    private CategorizedFilterButtons target;

    public CategorizedFilterButtons_ViewBinding(CategorizedFilterButtons target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", AirTextView.class);
        target2.button1 = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.button1, "field 'button1'", LinearLayout.class);
        target2.button1Text = (AirTextView) Utils.findRequiredViewAsType(source, R.id.button1_text, "field 'button1Text'", AirTextView.class);
        target2.button1CloseIcon = (ImageView) Utils.findRequiredViewAsType(source, R.id.button1_close_icon, "field 'button1CloseIcon'", ImageView.class);
        target2.button1Divider = Utils.findRequiredView(source, R.id.button1_divider, "field 'button1Divider'");
        target2.button2 = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.button2, "field 'button2'", LinearLayout.class);
        target2.button2Text = (AirTextView) Utils.findRequiredViewAsType(source, R.id.button2_text, "field 'button2Text'", AirTextView.class);
        target2.button2CloseIcon = (ImageView) Utils.findRequiredViewAsType(source, R.id.button2_close_icon, "field 'button2CloseIcon'", ImageView.class);
        target2.button2Divider = Utils.findRequiredView(source, R.id.button2_divider, "field 'button2Divider'");
        target2.button3 = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.button3, "field 'button3'", LinearLayout.class);
        target2.button3Text = (AirTextView) Utils.findRequiredViewAsType(source, R.id.button3_text, "field 'button3Text'", AirTextView.class);
        target2.button3CloseIcon = (ImageView) Utils.findRequiredViewAsType(source, R.id.button3_close_icon, "field 'button3CloseIcon'", ImageView.class);
        target2.buttonContainer = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.button_container, "field 'buttonContainer'", LinearLayout.class);
    }

    public void unbind() {
        CategorizedFilterButtons target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.button1 = null;
        target2.button1Text = null;
        target2.button1CloseIcon = null;
        target2.button1Divider = null;
        target2.button2 = null;
        target2.button2Text = null;
        target2.button2CloseIcon = null;
        target2.button2Divider = null;
        target2.button3 = null;
        target2.button3Text = null;
        target2.button3CloseIcon = null;
        target2.buttonContainer = null;
    }
}
