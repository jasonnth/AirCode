package com.airbnb.p027n2.components.decide.select;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

/* renamed from: com.airbnb.n2.components.decide.select.SelectHostRow_ViewBinding */
public class SelectHostRow_ViewBinding implements Unbinder {
    private SelectHostRow target;

    public SelectHostRow_ViewBinding(SelectHostRow target2, View source) {
        this.target = target2;
        target2.hostJoinedTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.host_row_title, "field 'hostJoinedTextView'", AirTextView.class);
        target2.hostRowDescription = (AirTextView) Utils.findRequiredViewAsType(source, R.id.host_row_description, "field 'hostRowDescription'", AirTextView.class);
        target2.contactHostButton = (AirButton) Utils.findRequiredViewAsType(source, R.id.contact_host_button, "field 'contactHostButton'", AirButton.class);
        target2.hostAvatar = (HaloImageView) Utils.findRequiredViewAsType(source, R.id.host_avatar, "field 'hostAvatar'", HaloImageView.class);
    }

    public void unbind() {
        SelectHostRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.hostJoinedTextView = null;
        target2.hostRowDescription = null;
        target2.contactHostButton = null;
        target2.hostAvatar = null;
    }
}
