package com.airbnb.android.lib.adapters.viewholders;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class InboxViewHolder_ViewBinding implements Unbinder {
    private InboxViewHolder target;

    public InboxViewHolder_ViewBinding(InboxViewHolder target2, View source) {
        this.target = target2;
        target2.mProfileImageView = (HaloImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.profile_image, "field 'mProfileImageView'", HaloImageView.class);
        target2.mNameTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_name, "field 'mNameTextView'", TextView.class);
        target2.mPreviewTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_preview, "field 'mPreviewTextView'", TextView.class);
        target2.mDetailsTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_details, "field 'mDetailsTextView'", TextView.class);
        target2.mTimeTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_time, "field 'mTimeTextView'", TextView.class);
        target2.mInboxItemFrame = Utils.findRequiredView(source, C0880R.C0882id.frame_inbox_item, "field 'mInboxItemFrame'");
    }

    public void unbind() {
        InboxViewHolder target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mProfileImageView = null;
        target2.mNameTextView = null;
        target2.mPreviewTextView = null;
        target2.mDetailsTextView = null;
        target2.mTimeTextView = null;
        target2.mInboxItemFrame = null;
    }
}
