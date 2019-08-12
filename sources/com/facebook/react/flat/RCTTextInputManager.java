package com.facebook.react.flat;

import com.facebook.react.views.textinput.ReactTextInputManager;

class RCTTextInputManager extends ReactTextInputManager {
    RCTTextInputManager() {
    }

    public RCTTextInput createShadowNodeInstance() {
        return new RCTTextInput();
    }

    public Class<RCTTextInput> getShadowNodeClass() {
        return RCTTextInput.class;
    }
}
