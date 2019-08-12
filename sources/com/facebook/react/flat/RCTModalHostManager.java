package com.facebook.react.flat;

import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.views.modal.ReactModalHostManager;

class RCTModalHostManager extends ReactModalHostManager {
    RCTModalHostManager() {
    }

    public LayoutShadowNode createShadowNodeInstance() {
        return new FlatReactModalShadowNode();
    }

    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return FlatReactModalShadowNode.class;
    }
}
