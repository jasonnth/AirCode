package com.airbnb.p027n2.components.decide.select;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.decide.select.SelectIconBulletRow_ViewBinding */
public class SelectIconBulletRow_ViewBinding implements Unbinder {
    private SelectIconBulletRow target;

    public SelectIconBulletRow_ViewBinding(SelectIconBulletRow target2, View source) {
        this.target = target2;
        target2.iconView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.icon, "field 'iconView'", AirImageView.class);
        target2.textView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.text, "field 'textView'", AirTextView.class);
    }

    public void unbind() {
        SelectIconBulletRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.iconView = null;
        target2.textView = null;
    }
}
