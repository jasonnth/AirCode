package com.daon.sdk.face;

import android.graphics.Rect;
import android.os.Bundle;

public class RecognitionResult {

    /* renamed from: a */
    private Bundle f3031a;

    RecognitionResult(Bundle bundle) {
        this.f3031a = bundle;
    }

    public Rect getFacePosition() {
        Rect rect = (Rect) this.f3031a.getParcelable("result.face.position");
        return rect != null ? rect : new Rect(0, 0, 0, 0);
    }
}
