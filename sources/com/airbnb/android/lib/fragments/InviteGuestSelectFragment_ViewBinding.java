package com.airbnb.android.lib.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class InviteGuestSelectFragment_ViewBinding implements Unbinder {
    private InviteGuestSelectFragment target;

    public InviteGuestSelectFragment_ViewBinding(InviteGuestSelectFragment target2, View source) {
        this.target = target2;
        target2.editText = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.edit_text, "field 'editText'", EditText.class);
        target2.emailTokens = (LinearLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.selected, "field 'emailTokens'", LinearLayout.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    }

    public void unbind() {
        InviteGuestSelectFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.editText = null;
        target2.emailTokens = null;
        target2.recyclerView = null;
    }
}
