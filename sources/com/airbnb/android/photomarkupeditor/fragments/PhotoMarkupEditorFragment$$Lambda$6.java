package com.airbnb.android.photomarkupeditor.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class PhotoMarkupEditorFragment$$Lambda$6 implements OnClickListener {
    private final PhotoMarkupEditorFragment arg$1;

    private PhotoMarkupEditorFragment$$Lambda$6(PhotoMarkupEditorFragment photoMarkupEditorFragment) {
        this.arg$1 = photoMarkupEditorFragment;
    }

    public static OnClickListener lambdaFactory$(PhotoMarkupEditorFragment photoMarkupEditorFragment) {
        return new PhotoMarkupEditorFragment$$Lambda$6(photoMarkupEditorFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.discardChangesAndFinish();
    }
}
