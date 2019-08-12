package com.airbnb.android.misnap;

import android.content.Context;
import com.airbnb.android.core.utils.PhotoCompressor;

public class MisnapModule {
    public TakeSelfieController provideTakeSelfieController(Context context, PhotoCompressor photoCompressor) {
        return new TakeSelfieController(context, photoCompressor);
    }

    public PhotoCompressor providePhotoCompressor(Context context) {
        return new PhotoCompressor(context);
    }
}
