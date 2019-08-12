package com.airbnb.android.sharing.referral;

import com.flipboard.bottomsheet.commons.IntentPickerSheetView.ActivityInfo;
import java.util.Comparator;

final /* synthetic */ class SharingManager$$Lambda$2 implements Comparator {
    private final SharingManager arg$1;

    private SharingManager$$Lambda$2(SharingManager sharingManager) {
        this.arg$1 = sharingManager;
    }

    public static Comparator lambdaFactory$(SharingManager sharingManager) {
        return new SharingManager$$Lambda$2(sharingManager);
    }

    public int compare(Object obj, Object obj2) {
        return SharingManager.lambda$share$1(this.arg$1, (ActivityInfo) obj, (ActivityInfo) obj2);
    }
}
