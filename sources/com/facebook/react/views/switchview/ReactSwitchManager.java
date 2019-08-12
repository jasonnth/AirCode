package com.facebook.react.views.switchview;

import android.view.View.MeasureSpec;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNodeAPI;

public class ReactSwitchManager extends SimpleViewManager<ReactSwitch> {
    private static final OnCheckedChangeListener ON_CHECKED_CHANGE_LISTENER = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            ((UIManagerModule) ((ReactContext) buttonView.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new ReactSwitchEvent(buttonView.getId(), isChecked));
        }
    };
    private static final String REACT_CLASS = "AndroidSwitch";

    static class ReactSwitchShadowNode extends LayoutShadowNode implements YogaMeasureFunction {
        private int mHeight;
        private boolean mMeasured;
        private int mWidth;

        private ReactSwitchShadowNode() {
            setMeasureFunction(this);
        }

        public long measure(YogaNodeAPI node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
            if (!this.mMeasured) {
                ReactSwitch reactSwitch = new ReactSwitch(getThemedContext());
                int spec = MeasureSpec.makeMeasureSpec(-2, 0);
                reactSwitch.measure(spec, spec);
                this.mWidth = reactSwitch.getMeasuredWidth();
                this.mHeight = reactSwitch.getMeasuredHeight();
                this.mMeasured = true;
            }
            return YogaMeasureOutput.make(this.mWidth, this.mHeight);
        }
    }

    public String getName() {
        return REACT_CLASS;
    }

    public LayoutShadowNode createShadowNodeInstance() {
        return new ReactSwitchShadowNode();
    }

    public Class getShadowNodeClass() {
        return ReactSwitchShadowNode.class;
    }

    /* access modifiers changed from: protected */
    public ReactSwitch createViewInstance(ThemedReactContext context) {
        ReactSwitch view = new ReactSwitch(context);
        view.setShowText(false);
        return view;
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactSwitch view, boolean enabled) {
        view.setEnabled(enabled);
    }

    @ReactProp(name = "on")
    public void setOn(ReactSwitch view, boolean on) {
        view.setOnCheckedChangeListener(null);
        view.setOn(on);
        view.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext reactContext, ReactSwitch view) {
        view.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }
}
