package com.airbnb.android.lib.tripassistant.amenities;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.tripassistant.HelpThreadPhoto;

final /* synthetic */ class HTPhotoPickerActivity$PhotoAdapter$$Lambda$2 implements OnClickListener {
    private final PhotoAdapter arg$1;
    private final HelpThreadPhoto arg$2;

    private HTPhotoPickerActivity$PhotoAdapter$$Lambda$2(PhotoAdapter photoAdapter, HelpThreadPhoto helpThreadPhoto) {
        this.arg$1 = photoAdapter;
        this.arg$2 = helpThreadPhoto;
    }

    public static OnClickListener lambdaFactory$(PhotoAdapter photoAdapter, HelpThreadPhoto helpThreadPhoto) {
        return new HTPhotoPickerActivity$PhotoAdapter$$Lambda$2(photoAdapter, helpThreadPhoto);
    }

    public void onClick(View view) {
        HTPhotoPickerActivity.this.deletePhoto(this.arg$2);
    }
}
