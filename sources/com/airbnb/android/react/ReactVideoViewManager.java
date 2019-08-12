package com.airbnb.android.react;

import com.airbnb.android.react.ReactVideoView.Events;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.MapBuilder.Builder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.yqritc.scalablevideoview.ScalableType;
import java.util.Map;

public class ReactVideoViewManager extends SimpleViewManager<ReactVideoView> {
    public static final String PROP_CONTROLS = "controls";
    public static final String PROP_MUTED = "muted";
    public static final String PROP_PAUSED = "paused";
    public static final String PROP_PLAY_IN_BACKGROUND = "playInBackground";
    public static final String PROP_RATE = "rate";
    public static final String PROP_REPEAT = "repeat";
    public static final String PROP_RESIZE_MODE = "resizeMode";
    public static final String PROP_SEEK = "seek";
    public static final String PROP_SRC = "src";
    public static final String PROP_SRC_IS_ASSET = "isAsset";
    public static final String PROP_SRC_IS_NETWORK = "isNetwork";
    public static final String PROP_SRC_TYPE = "type";
    public static final String PROP_SRC_URI = "uri";
    public static final String PROP_VOLUME = "volume";
    public static final String REACT_CLASS = "RCTVideo";

    public String getName() {
        return "RCTVideo";
    }

    /* access modifiers changed from: protected */
    public ReactVideoView createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactVideoView(themedReactContext);
    }

    public void onDropViewInstance(ReactVideoView view) {
        super.onDropViewInstance(view);
        view.cleanupMediaPlayerResources();
    }

    public Map getExportedCustomDirectEventTypeConstants() {
        Events[] values;
        Builder builder = MapBuilder.builder();
        for (Events event : Events.values()) {
            builder.put(event.toString(), MapBuilder.m1882of("registrationName", event.toString()));
        }
        return builder.build();
    }

    public Map getExportedViewConstants() {
        return MapBuilder.m1885of("ScaleNone", Integer.toString(ScalableType.LEFT_TOP.ordinal()), "ScaleToFill", Integer.toString(ScalableType.FIT_XY.ordinal()), "ScaleAspectFit", Integer.toString(ScalableType.FIT_CENTER.ordinal()), "ScaleAspectFill", Integer.toString(ScalableType.CENTER_CROP.ordinal()));
    }

    @ReactProp(name = "src")
    public void setSrc(ReactVideoView videoView, ReadableMap src) {
        videoView.setSrc(src.getString("uri"), src.getString("type"), src.getBoolean("isNetwork"), src.getBoolean("isAsset"));
    }

    @ReactProp(name = "resizeMode")
    public void setResizeMode(ReactVideoView videoView, String resizeModeOrdinalString) {
        videoView.setResizeModeModifier(ScalableType.values()[Integer.parseInt(resizeModeOrdinalString)]);
    }

    @ReactProp(defaultBoolean = false, name = "repeat")
    public void setRepeat(ReactVideoView videoView, boolean repeat) {
        videoView.setRepeatModifier(repeat);
    }

    @ReactProp(defaultBoolean = false, name = "paused")
    public void setPaused(ReactVideoView videoView, boolean paused) {
        videoView.setPausedModifier(paused);
    }

    @ReactProp(defaultBoolean = false, name = "muted")
    public void setMuted(ReactVideoView videoView, boolean muted) {
        videoView.setMutedModifier(muted);
    }

    @ReactProp(defaultFloat = 1.0f, name = "volume")
    public void setVolume(ReactVideoView videoView, float volume) {
        videoView.setVolumeModifier(volume);
    }

    @ReactProp(name = "seek")
    public void setSeek(ReactVideoView videoView, float seek) {
        videoView.seekTo(Math.round(1000.0f * seek));
    }

    @ReactProp(name = "rate")
    public void setRate(ReactVideoView videoView, float rate) {
        videoView.setRateModifier(rate);
    }

    @ReactProp(defaultBoolean = false, name = "playInBackground")
    public void setPlayInBackground(ReactVideoView videoView, boolean playInBackground) {
        videoView.setPlayInBackground(playInBackground);
    }

    @ReactProp(defaultBoolean = false, name = "controls")
    public void setControls(ReactVideoView videoView, boolean controls) {
        videoView.setControls(controls);
    }
}
