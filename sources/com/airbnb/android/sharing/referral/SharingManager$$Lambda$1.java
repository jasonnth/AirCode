package com.airbnb.android.sharing.referral;

import android.content.Intent;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.IntentPickerSheetView.ActivityInfo;
import com.flipboard.bottomsheet.commons.IntentPickerSheetView.OnIntentPickedListener;

final /* synthetic */ class SharingManager$$Lambda$1 implements OnIntentPickedListener {
    private final SharingManager arg$1;
    private final BottomSheetLayout arg$2;
    private final Intent arg$3;

    private SharingManager$$Lambda$1(SharingManager sharingManager, BottomSheetLayout bottomSheetLayout, Intent intent) {
        this.arg$1 = sharingManager;
        this.arg$2 = bottomSheetLayout;
        this.arg$3 = intent;
    }

    public static OnIntentPickedListener lambdaFactory$(SharingManager sharingManager, BottomSheetLayout bottomSheetLayout, Intent intent) {
        return new SharingManager$$Lambda$1(sharingManager, bottomSheetLayout, intent);
    }

    public void onIntentPicked(ActivityInfo activityInfo) {
        SharingManager.lambda$share$0(this.arg$1, this.arg$2, this.arg$3, activityInfo);
    }
}
