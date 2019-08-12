package com.airbnb.android.photomarkupeditor.fragments;

import com.airbnb.android.core.interfaces.OnBackListener;

final /* synthetic */ class PhotoMarkupEditorFragment$$Lambda$1 implements OnBackListener {
    private final PhotoMarkupEditorFragment arg$1;

    private PhotoMarkupEditorFragment$$Lambda$1(PhotoMarkupEditorFragment photoMarkupEditorFragment) {
        this.arg$1 = photoMarkupEditorFragment;
    }

    public static OnBackListener lambdaFactory$(PhotoMarkupEditorFragment photoMarkupEditorFragment) {
        return new PhotoMarkupEditorFragment$$Lambda$1(photoMarkupEditorFragment);
    }

    public boolean onBackPressed() {
        return this.arg$1.onBackPressed();
    }
}
