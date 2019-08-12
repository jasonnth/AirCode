package com.horcrux.svg;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RNSvgPackage implements ReactPackage {
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.asList(new ViewManager[]{RNSVGRenderableViewManager.createRNSVGGroupViewManager(), RNSVGRenderableViewManager.createRNSVGPathViewManager(), RNSVGRenderableViewManager.createRNSVGCircleViewManager(), RNSVGRenderableViewManager.createRNSVGEllipseViewManager(), RNSVGRenderableViewManager.createRNSVGLineViewManager(), RNSVGRenderableViewManager.createRNSVGRectViewManager(), RNSVGRenderableViewManager.createRNSVGTextViewManager(), RNSVGRenderableViewManager.createRNSVGImageViewManager(), RNSVGRenderableViewManager.createRNSVGClipPathViewManager(), RNSVGRenderableViewManager.createRNSVGDefsViewManager(), RNSVGRenderableViewManager.createRNSVGUseViewManager(), RNSVGRenderableViewManager.createRNSVGViewBoxViewManager(), RNSVGRenderableViewManager.createRNSVGLinearGradientManager(), RNSVGRenderableViewManager.createRNSVGRadialGradientManager(), new RNSVGSvgViewManager()});
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
