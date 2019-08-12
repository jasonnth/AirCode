package com.airbnb.android.react;

import android.os.Handler;
import android.os.Looper;
import android.support.p000v4.view.ViewCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;
import org.json.JSONObject;

public class LottieAnimationViewManager extends SimpleViewManager<LottieAnimationView> {
    private static final int COMMAND_PLAY = 1;
    private static final int COMMAND_RESET = 2;
    private static final String REACT_CLASS = "AirbnbLottieView";
    private static final int VERSION = 1;

    public Map<String, Object> getExportedViewConstants() {
        return MapBuilder.builder().put("VERSION", Integer.valueOf(1)).build();
    }

    public String getName() {
        return REACT_CLASS;
    }

    public LottieAnimationView createViewInstance(ThemedReactContext context) {
        return new LottieAnimationView(context);
    }

    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.m1883of("play", Integer.valueOf(1), "reset", Integer.valueOf(2));
    }

    public void receiveCommand(LottieAnimationView view, int commandId, ReadableArray args) {
        switch (commandId) {
            case 1:
                new Handler(Looper.getMainLooper()).post(LottieAnimationViewManager$$Lambda$1.lambdaFactory$(view));
                return;
            case 2:
                new Handler(Looper.getMainLooper()).post(LottieAnimationViewManager$$Lambda$2.lambdaFactory$(view));
                return;
            default:
                return;
        }
    }

    static /* synthetic */ void lambda$receiveCommand$0(LottieAnimationView view) {
        if (ViewCompat.isAttachedToWindow(view)) {
            view.setProgress(0.0f);
            view.playAnimation();
        }
    }

    static /* synthetic */ void lambda$receiveCommand$1(LottieAnimationView view) {
        if (ViewCompat.isAttachedToWindow(view)) {
            view.cancelAnimation();
            view.setProgress(0.0f);
        }
    }

    @ReactProp(name = "sourceName")
    public void setSourceName(LottieAnimationView view, String name) {
        view.setAnimation(name);
    }

    @ReactProp(name = "sourceJson")
    public void setSourceJson(LottieAnimationView view, ReadableMap json) {
        view.setAnimation((JSONObject) new JSONReadableMap(json));
    }

    @ReactProp(name = "progress")
    public void setProgress(LottieAnimationView view, float progress) {
        view.setProgress(progress);
    }

    @ReactProp(name = "speed")
    public void setSpeed(LottieAnimationView view, double speed) {
    }

    @ReactProp(name = "loop")
    public void setLoop(LottieAnimationView view, boolean loop) {
        view.loop(loop);
    }
}
