package com.airbnb.android.lib.adapters.viewholders;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirTextView;

public class CheckedTextRowViewHolder_ViewBinding implements Unbinder {
    private CheckedTextRowViewHolder target;

    public CheckedTextRowViewHolder_ViewBinding(CheckedTextRowViewHolder target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.checked_row_title, "field 'title'", AirTextView.class);
        target2.description = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.checked_row_description, "field 'description'", AirTextView.class);
        target2.check = Utils.findRequiredView(source, C0880R.C0882id.checked_row_check_mark, "field 'check'");
    }

    public void unbind() {
        CheckedTextRowViewHolder target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.description = null;
        target2.check = null;
    }
}
