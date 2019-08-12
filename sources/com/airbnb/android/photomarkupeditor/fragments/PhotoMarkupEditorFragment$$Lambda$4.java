package com.airbnb.android.photomarkupeditor.fragments;

import com.airbnb.android.core.interfaces.OnHomeListener;

final /* synthetic */ class PhotoMarkupEditorFragment$$Lambda$4 implements OnHomeListener {
    private final PhotoMarkupEditorFragment arg$1;

    private PhotoMarkupEditorFragment$$Lambda$4(PhotoMarkupEditorFragment photoMarkupEditorFragment) {
        this.arg$1 = photoMarkupEditorFragment;
    }

    public static OnHomeListener lambdaFactory$(PhotoMarkupEditorFragment photoMarkupEditorFragment) {
        return new PhotoMarkupEditorFragment$$Lambda$4(photoMarkupEditorFragment);
    }

    public boolean onHomePressed() {
        return this.arg$1.onBackPressed();
    }
}
