package com.airbnb.android.lib.fragments;

final /* synthetic */ class TextInputSheetFragment$$Lambda$1 implements Runnable {
    private final TextInputSheetFragment arg$1;

    private TextInputSheetFragment$$Lambda$1(TextInputSheetFragment textInputSheetFragment) {
        this.arg$1 = textInputSheetFragment;
    }

    public static Runnable lambdaFactory$(TextInputSheetFragment textInputSheetFragment) {
        return new TextInputSheetFragment$$Lambda$1(textInputSheetFragment);
    }

    public void run() {
        TextInputSheetFragment.lambda$onCreateView$0(this.arg$1);
    }
}
