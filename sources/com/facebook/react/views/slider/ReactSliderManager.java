package com.facebook.react.views.slider;

import android.view.View.MeasureSpec;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNodeAPI;
import java.util.Map;

public class ReactSliderManager extends SimpleViewManager<ReactSlider> {
    private static final OnSeekBarChangeListener ON_CHANGE_LISTENER = new OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
            ((UIManagerModule) ((ReactContext) seekbar.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new ReactSliderEvent(seekbar.getId(), ((ReactSlider) seekbar).toRealProgress(progress), fromUser));
        }

        public void onStartTrackingTouch(SeekBar seekbar) {
        }

        public void onStopTrackingTouch(SeekBar seekbar) {
            ((UIManagerModule) ((ReactContext) seekbar.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new ReactSlidingCompleteEvent(seekbar.getId(), ((ReactSlider) seekbar).toRealProgress(seekbar.getProgress())));
        }
    };
    private static final String REACT_CLASS = "RCTSlider";
    private static final int STYLE = 16842875;

    static class ReactSliderShadowNode extends LayoutShadowNode implements YogaMeasureFunction {
        private int mHeight;
        private boolean mMeasured;
        private int mWidth;

        private ReactSliderShadowNode() {
            setMeasureFunction(this);
        }

        public long measure(YogaNodeAPI node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
            if (!this.mMeasured) {
                SeekBar reactSlider = new ReactSlider(getThemedContext(), null, ReactSliderManager.STYLE);
                int spec = MeasureSpec.makeMeasureSpec(-2, 0);
                reactSlider.measure(spec, spec);
                this.mWidth = reactSlider.getMeasuredWidth();
                this.mHeight = reactSlider.getMeasuredHeight();
                this.mMeasured = true;
            }
            return YogaMeasureOutput.make(this.mWidth, this.mHeight);
        }
    }

    public String getName() {
        return REACT_CLASS;
    }

    public LayoutShadowNode createShadowNodeInstance() {
        return new ReactSliderShadowNode();
    }

    public Class getShadowNodeClass() {
        return ReactSliderShadowNode.class;
    }

    /* access modifiers changed from: protected */
    public ReactSlider createViewInstance(ThemedReactContext context) {
        return new ReactSlider(context, null, STYLE);
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactSlider view, boolean enabled) {
        view.setEnabled(enabled);
    }

    @ReactProp(defaultDouble = 0.0d, name = "value")
    public void setValue(ReactSlider view, double value) {
        view.setOnSeekBarChangeListener(null);
        view.setValue(value);
        view.setOnSeekBarChangeListener(ON_CHANGE_LISTENER);
    }

    @ReactProp(defaultDouble = 0.0d, name = "minimumValue")
    public void setMinimumValue(ReactSlider view, double value) {
        view.setMinValue(value);
    }

    @ReactProp(defaultDouble = 1.0d, name = "maximumValue")
    public void setMaximumValue(ReactSlider view, double value) {
        view.setMaxValue(value);
    }

    @ReactProp(defaultDouble = 0.0d, name = "step")
    public void setStep(ReactSlider view, double value) {
        view.setStep(value);
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext reactContext, ReactSlider view) {
        view.setOnSeekBarChangeListener(ON_CHANGE_LISTENER);
    }

    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.m1882of(ReactSlidingCompleteEvent.EVENT_NAME, MapBuilder.m1882of("registrationName", "onSlidingComplete"));
    }
}
