package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class OfficialIdPhotoSelectionFragment$$Lambda$1 implements OnClickListener {
    private final OfficialIdPhotoSelectionFragment arg$1;

    private OfficialIdPhotoSelectionFragment$$Lambda$1(OfficialIdPhotoSelectionFragment officialIdPhotoSelectionFragment) {
        this.arg$1 = officialIdPhotoSelectionFragment;
    }

    public static OnClickListener lambdaFactory$(OfficialIdPhotoSelectionFragment officialIdPhotoSelectionFragment) {
        return new OfficialIdPhotoSelectionFragment$$Lambda$1(officialIdPhotoSelectionFragment);
    }

    public void onClick(View view) {
        OfficialIdPhotoSelectionFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
