package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.GroupedCheck;

public class DebugSettingsVerifiedId_ViewBinding implements Unbinder {
    private DebugSettingsVerifiedId target;

    public DebugSettingsVerifiedId_ViewBinding(DebugSettingsVerifiedId target2, View source) {
        this.target = target2;
        target2.mProfilePictureGroupedCheck = (GroupedCheck) Utils.findRequiredViewAsType(source, C0880R.C0882id.profile_picture_groupedCheck, "field 'mProfilePictureGroupedCheck'", GroupedCheck.class);
        target2.mEmailGroupedCheck = (GroupedCheck) Utils.findRequiredViewAsType(source, C0880R.C0882id.email_groupedCheck, "field 'mEmailGroupedCheck'", GroupedCheck.class);
        target2.mPhoneGroupedCheck = (GroupedCheck) Utils.findRequiredViewAsType(source, C0880R.C0882id.phone_groupedCheck, "field 'mPhoneGroupedCheck'", GroupedCheck.class);
        target2.mOfflineGroupedCheck = (GroupedCheck) Utils.findRequiredViewAsType(source, C0880R.C0882id.offline_groupedCheck, "field 'mOfflineGroupedCheck'", GroupedCheck.class);
        target2.mOnlineGroupedCheck = (GroupedCheck) Utils.findRequiredViewAsType(source, C0880R.C0882id.online_groupedCheck, "field 'mOnlineGroupedCheck'", GroupedCheck.class);
    }

    public void unbind() {
        DebugSettingsVerifiedId target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mProfilePictureGroupedCheck = null;
        target2.mEmailGroupedCheck = null;
        target2.mPhoneGroupedCheck = null;
        target2.mOfflineGroupedCheck = null;
        target2.mOnlineGroupedCheck = null;
    }
}
