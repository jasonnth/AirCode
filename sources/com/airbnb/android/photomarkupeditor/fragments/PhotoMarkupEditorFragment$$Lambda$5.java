package com.airbnb.android.photomarkupeditor.fragments;

final /* synthetic */ class PhotoMarkupEditorFragment$$Lambda$5 implements Runnable {
    private final PhotoMarkupEditorFragment arg$1;

    private PhotoMarkupEditorFragment$$Lambda$5(PhotoMarkupEditorFragment photoMarkupEditorFragment) {
        this.arg$1 = photoMarkupEditorFragment;
    }

    public static Runnable lambdaFactory$(PhotoMarkupEditorFragment photoMarkupEditorFragment) {
        return new PhotoMarkupEditorFragment$$Lambda$5(photoMarkupEditorFragment);
    }

    public void run() {
        this.arg$1.animateColorPickerClosed();
    }
}
