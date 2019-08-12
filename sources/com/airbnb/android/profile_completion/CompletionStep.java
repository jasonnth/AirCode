package com.airbnb.android.profile_completion;

public enum CompletionStep {
    SignUp(C7646R.string.sign_up, C7646R.string.sign_up, true),
    Verificaton(C7646R.string.profile_completion_step_verification, C7646R.string.profile_completion_step_verification_description, true),
    AddPaymentMethod(C7646R.string.profile_completion_step_add_payment_method, C7646R.string.profile_completion_step_add_payment_method_description, true),
    CompleteAboutMe(C7646R.string.profile_completion_step_describe_yourself, C7646R.string.profile_completion_step_describe_yourself_description, true);
    
    private final int description;
    private final boolean isBadgingStep;
    private final int title;

    private CompletionStep(int title2, int description2, boolean isBadgingStep2) {
        this.title = title2;
        this.description = description2;
        this.isBadgingStep = isBadgingStep2;
    }

    public int getTitle() {
        return this.title;
    }

    public int getDescription() {
        return this.description;
    }

    public boolean isBadgingStep() {
        return this.isBadgingStep;
    }
}
