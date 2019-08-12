package com.facebook.react.flat;

import com.facebook.react.views.text.ReactRawTextManager;

final class RCTRawTextManager extends VirtualViewManager<RCTRawText> {
    RCTRawTextManager() {
    }

    public String getName() {
        return ReactRawTextManager.REACT_CLASS;
    }

    public RCTRawText createShadowNodeInstance() {
        return new RCTRawText();
    }

    public Class<RCTRawText> getShadowNodeClass() {
        return RCTRawText.class;
    }
}
