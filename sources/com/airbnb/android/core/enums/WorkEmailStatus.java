package com.airbnb.android.core.enums;

import com.airbnb.android.core.C0716R;

public enum WorkEmailStatus {
    None(8, -1, -1, C0716R.string.bt_profile_work_email_add_details, C0716R.color.c_gray_3),
    Pending(0, C0716R.color.c_yellow, C0716R.string.bt_profile_work_email_status_pending, C0716R.string.bt_profile_work_email_status_pending_details, C0716R.color.c_hof),
    Verified(0, C0716R.color.c_green, C0716R.string.bt_profile_work_email_status_verified, C0716R.string.bt_profile_work_email_status_verified_details, C0716R.color.c_hof);
    
    public final int inputTextColor;
    public final int statusColor;
    public final int statusDetails;
    public final int statusTitle;
    public final int statusVisibility;

    private WorkEmailStatus(int statusVisibility2, int statusColor2, int statusTitle2, int statusDetails2, int inputTextColor2) {
        this.statusVisibility = statusVisibility2;
        this.statusColor = statusColor2;
        this.statusTitle = statusTitle2;
        this.statusDetails = statusDetails2;
        this.inputTextColor = inputTextColor2;
    }
}
