package com.airbnb.android.lib.tripassistant.amenities;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HTPhotoPickerActivity$PhotoAdapter$$Lambda$1 implements OnClickListener {
    private final PhotoAdapter arg$1;

    private HTPhotoPickerActivity$PhotoAdapter$$Lambda$1(PhotoAdapter photoAdapter) {
        this.arg$1 = photoAdapter;
    }

    public static OnClickListener lambdaFactory$(PhotoAdapter photoAdapter) {
        return new HTPhotoPickerActivity$PhotoAdapter$$Lambda$1(photoAdapter);
    }

    public void onClick(View view) {
        HTPhotoPickerActivity.this.launchAddPhotoFlow();
    }
}
