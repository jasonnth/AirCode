package com.facebook.react.flat;

import com.facebook.react.views.text.ReactTextViewManager;

final class RCTTextManager extends FlatViewManager {
    RCTTextManager() {
    }

    public String getName() {
        return ReactTextViewManager.REACT_CLASS;
    }

    public RCTText createShadowNodeInstance() {
        return new RCTText();
    }

    public Class<RCTText> getShadowNodeClass() {
        return RCTText.class;
    }
}
