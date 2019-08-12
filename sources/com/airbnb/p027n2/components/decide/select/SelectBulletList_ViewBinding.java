package com.airbnb.p027n2.components.decide.select;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.decide.select.SelectBulletList_ViewBinding */
public class SelectBulletList_ViewBinding implements Unbinder {
    private SelectBulletList target;

    public SelectBulletList_ViewBinding(SelectBulletList target2, View source) {
        this.target = target2;
        target2.textView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.bullet_list, "field 'textView'", AirTextView.class);
    }

    public void unbind() {
        SelectBulletList target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.textView = null;
    }
}
