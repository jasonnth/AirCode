package com.airbnb.android.lib.fragments.unlist;

import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class UnlistNoLongerHaveAccessFragment_ViewBinding extends BaseSnoozeListingFragment_ViewBinding {
    private UnlistNoLongerHaveAccessFragment target;

    public UnlistNoLongerHaveAccessFragment_ViewBinding(UnlistNoLongerHaveAccessFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.textView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.unlist_subtitle, "field 'textView'", TextView.class);
    }

    public void unbind() {
        UnlistNoLongerHaveAccessFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.textView = null;
        super.unbind();
    }
}
