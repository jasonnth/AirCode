package com.flipboard.bottomsheet;

import android.view.View;

public abstract class BaseViewTransformer implements ViewTransformer {
    public float getDimAlpha(float translation, float maxTranslation, float peekedTranslation, BottomSheetLayout parent, View view) {
        return 0.7f * (translation / maxTranslation);
    }
}
