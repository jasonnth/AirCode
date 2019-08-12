package com.airbnb.android.sharing.p033ui;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.sharing.C0921R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.android.sharing.ui.SharePreview_ViewBinding */
public class SharePreview_ViewBinding implements Unbinder {
    private SharePreview target;

    public SharePreview_ViewBinding(SharePreview target2) {
        this(target2, target2);
    }

    public SharePreview_ViewBinding(SharePreview target2, View source) {
        this.target = target2;
        target2.image = (AirImageView) Utils.findRequiredViewAsType(source, C0921R.C0923id.image, "field 'image'", AirImageView.class);
        target2.title = (TextView) Utils.findRequiredViewAsType(source, C0921R.C0923id.title, "field 'title'", TextView.class);
    }

    public void unbind() {
        SharePreview target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.image = null;
        target2.title = null;
    }
}
