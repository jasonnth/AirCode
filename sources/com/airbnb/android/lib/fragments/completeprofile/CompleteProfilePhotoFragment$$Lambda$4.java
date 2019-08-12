package com.airbnb.android.lib.fragments.completeprofile;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

final /* synthetic */ class CompleteProfilePhotoFragment$$Lambda$4 implements OnClickListener {
    private final CompleteProfilePhotoFragment arg$1;
    private final Button arg$2;
    private final Button arg$3;

    private CompleteProfilePhotoFragment$$Lambda$4(CompleteProfilePhotoFragment completeProfilePhotoFragment, Button button, Button button2) {
        this.arg$1 = completeProfilePhotoFragment;
        this.arg$2 = button;
        this.arg$3 = button2;
    }

    public static OnClickListener lambdaFactory$(CompleteProfilePhotoFragment completeProfilePhotoFragment, Button button, Button button2) {
        return new CompleteProfilePhotoFragment$$Lambda$4(completeProfilePhotoFragment, button, button2);
    }

    public void onClick(View view) {
        CompleteProfilePhotoFragment.lambda$onCreateView$1(this.arg$1, this.arg$2, this.arg$3, view);
    }
}
