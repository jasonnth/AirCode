package com.facebook.react.flat;

final class RCTImageViewManager extends FlatViewManager {
    RCTImageViewManager() {
    }

    public String getName() {
        return "RCTImageView";
    }

    public RCTImageView createShadowNodeInstance() {
        return new RCTImageView(new DrawImageWithDrawee());
    }

    public Class<RCTImageView> getShadowNodeClass() {
        return RCTImageView.class;
    }
}
