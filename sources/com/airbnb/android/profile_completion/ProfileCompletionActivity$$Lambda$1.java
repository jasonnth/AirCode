package com.airbnb.android.profile_completion;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ProfileCompletionActivity$$Lambda$1 implements OnClickListener {
    private final ProfileCompletionActivity arg$1;
    private final CompletionStep arg$2;

    private ProfileCompletionActivity$$Lambda$1(ProfileCompletionActivity profileCompletionActivity, CompletionStep completionStep) {
        this.arg$1 = profileCompletionActivity;
        this.arg$2 = completionStep;
    }

    public static OnClickListener lambdaFactory$(ProfileCompletionActivity profileCompletionActivity, CompletionStep completionStep) {
        return new ProfileCompletionActivity$$Lambda$1(profileCompletionActivity, completionStep);
    }

    public void onClick(View view) {
        this.arg$1.onClick(this.arg$2);
    }
}
