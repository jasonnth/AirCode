package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.annotations.ReactPropertyHolder;
import java.util.Map;

@ReactPropertyHolder
public abstract class ViewManager<T extends View, C extends ReactShadowNode> extends BaseJavaModule {
    public abstract C createShadowNodeInstance();

    /* access modifiers changed from: protected */
    public abstract T createViewInstance(ThemedReactContext themedReactContext);

    public abstract String getName();

    public abstract Class<? extends C> getShadowNodeClass();

    public abstract void updateExtraData(T t, Object obj);

    public final void updateProperties(T viewToUpdate, ReactStylesDiffMap props) {
        ViewManagerPropertyUpdater.updateProps(this, viewToUpdate, props);
        onAfterUpdateTransaction(viewToUpdate);
    }

    public final T createView(ThemedReactContext reactContext, JSResponderHandler jsResponderHandler) {
        T view = createViewInstance(reactContext);
        addEventEmitters(reactContext, view);
        if (view instanceof ReactInterceptingViewGroup) {
            ((ReactInterceptingViewGroup) view).setOnInterceptTouchEventListener(jsResponderHandler);
        }
        return view;
    }

    public void onDropViewInstance(T t) {
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext reactContext, T t) {
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(T t) {
    }

    public void receiveCommand(T t, int commandId, ReadableArray args) {
    }

    public Map<String, Integer> getCommandsMap() {
        return null;
    }

    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return null;
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return null;
    }

    public Map<String, Object> getExportedViewConstants() {
        return null;
    }

    public Map<String, String> getNativeProps() {
        return ViewManagerPropertyUpdater.getNativeProps(getClass(), getShadowNodeClass());
    }
}
