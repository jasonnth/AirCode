package com.facebook.react.flat;

import com.facebook.react.views.text.ReactVirtualTextViewManager;

final class RCTVirtualTextManager extends VirtualViewManager<RCTVirtualText> {
    RCTVirtualTextManager() {
    }

    public String getName() {
        return ReactVirtualTextViewManager.REACT_CLASS;
    }

    public RCTVirtualText createShadowNodeInstance() {
        return new RCTVirtualText();
    }

    public Class<RCTVirtualText> getShadowNodeClass() {
        return RCTVirtualText.class;
    }
}
