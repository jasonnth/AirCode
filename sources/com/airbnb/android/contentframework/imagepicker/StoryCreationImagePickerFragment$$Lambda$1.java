package com.airbnb.android.contentframework.imagepicker;

final /* synthetic */ class StoryCreationImagePickerFragment$$Lambda$1 implements Runnable {
    private final StoryCreationImagePickerFragment arg$1;

    private StoryCreationImagePickerFragment$$Lambda$1(StoryCreationImagePickerFragment storyCreationImagePickerFragment) {
        this.arg$1 = storyCreationImagePickerFragment;
    }

    public static Runnable lambdaFactory$(StoryCreationImagePickerFragment storyCreationImagePickerFragment) {
        return new StoryCreationImagePickerFragment$$Lambda$1(storyCreationImagePickerFragment);
    }

    public void run() {
        this.arg$1.updateActionMenuState();
    }
}
