package com.facebook.react.views.modal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.modal.ReactModalHostView.OnRequestCloseListener;
import java.util.Map;

@ReactModule(name = "RCTModalHostView")
public class ReactModalHostManager extends ViewGroupManager<ReactModalHostView> {
    protected static final String REACT_CLASS = "RCTModalHostView";

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public ReactModalHostView createViewInstance(ThemedReactContext reactContext) {
        return new ReactModalHostView(reactContext);
    }

    public LayoutShadowNode createShadowNodeInstance() {
        return new ModalHostShadowNode();
    }

    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return ModalHostShadowNode.class;
    }

    public void onDropViewInstance(ReactModalHostView view) {
        super.onDropViewInstance(view);
        view.onDropInstance();
    }

    @ReactProp(name = "animationType")
    public void setAnimationType(ReactModalHostView view, String animationType) {
        view.setAnimationType(animationType);
    }

    @ReactProp(name = "transparent")
    public void setTransparent(ReactModalHostView view, boolean transparent) {
        view.setTransparent(transparent);
    }

    @ReactProp(name = "hardwareAccelerated")
    public void setHardwareAccelerated(ReactModalHostView view, boolean hardwareAccelerated) {
        view.setHardwareAccelerated(hardwareAccelerated);
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext reactContext, final ReactModalHostView view) {
        final EventDispatcher dispatcher = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        view.setOnRequestCloseListener(new OnRequestCloseListener() {
            public void onRequestClose(DialogInterface dialog) {
                dispatcher.dispatchEvent(new RequestCloseEvent(view.getId()));
            }
        });
        view.setOnShowListener(new OnShowListener() {
            public void onShow(DialogInterface dialog) {
                dispatcher.dispatchEvent(new ShowEvent(view.getId()));
            }
        });
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put(RequestCloseEvent.EVENT_NAME, MapBuilder.m1882of("registrationName", "onRequestClose")).put(ShowEvent.EVENT_NAME, MapBuilder.m1882of("registrationName", "onShow")).build();
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(ReactModalHostView view) {
        super.onAfterUpdateTransaction(view);
        view.showOrUpdate();
    }
}
