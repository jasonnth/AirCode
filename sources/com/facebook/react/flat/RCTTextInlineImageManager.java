package com.facebook.react.flat;

final class RCTTextInlineImageManager extends VirtualViewManager<RCTTextInlineImage> {
    RCTTextInlineImageManager() {
    }

    public String getName() {
        return "RCTTextInlineImage";
    }

    public RCTTextInlineImage createShadowNodeInstance() {
        return new RCTTextInlineImage();
    }

    public Class<RCTTextInlineImage> getShadowNodeClass() {
        return RCTTextInlineImage.class;
    }
}
