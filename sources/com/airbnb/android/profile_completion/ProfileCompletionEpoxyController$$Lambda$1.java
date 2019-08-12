package com.airbnb.android.profile_completion;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ProfileCompletionEpoxyController$$Lambda$1 implements OnClickListener {
    private final ProfileCompletionEpoxyController arg$1;
    private final CompletionStep arg$2;

    private ProfileCompletionEpoxyController$$Lambda$1(ProfileCompletionEpoxyController profileCompletionEpoxyController, CompletionStep completionStep) {
        this.arg$1 = profileCompletionEpoxyController;
        this.arg$2 = completionStep;
    }

    public static OnClickListener lambdaFactory$(ProfileCompletionEpoxyController profileCompletionEpoxyController, CompletionStep completionStep) {
        return new ProfileCompletionEpoxyController$$Lambda$1(profileCompletionEpoxyController, completionStep);
    }

    public void onClick(View view) {
        this.arg$1.incompleteStepOnClickListener.onClick(this.arg$2);
    }
}
