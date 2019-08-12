package com.airbnb.android.react;

import com.airbnb.android.core.superhero.SuperHeroManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;

public class SuperHeroModule extends VersionedReactModuleBase {
    private static final int VERSION = 1;
    private final SuperHeroManager superHeroManager;

    public SuperHeroModule(ReactApplicationContext reactContext, SuperHeroManager superHeroManager2) {
        super(reactContext, 1);
        this.superHeroManager = superHeroManager2;
    }

    public String getName() {
        return "SuperHeroBridge";
    }

    @ReactMethod
    public void presentFullHeroMessage() {
        this.superHeroManager.presentFull();
    }

    @ReactMethod
    public void dismissHeroMessage() {
        this.superHeroManager.dismiss();
    }

    @ReactMethod
    public void clickedHeroActionWithMessageID(int messageId) {
        this.superHeroManager.clickedHeroAction((long) messageId);
    }
}
