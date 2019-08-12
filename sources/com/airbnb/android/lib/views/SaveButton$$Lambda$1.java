package com.airbnb.android.lib.views;

final /* synthetic */ class SaveButton$$Lambda$1 implements Runnable {
    private final SaveButton arg$1;

    private SaveButton$$Lambda$1(SaveButton saveButton) {
        this.arg$1 = saveButton;
    }

    public static Runnable lambdaFactory$(SaveButton saveButton) {
        return new SaveButton$$Lambda$1(saveButton);
    }

    public void run() {
        SaveButton.lambda$new$0(this.arg$1);
    }
}
