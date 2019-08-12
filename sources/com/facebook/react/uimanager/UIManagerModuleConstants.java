package com.facebook.react.uimanager;

import android.util.DisplayMetrics;
import android.widget.ImageView.ScaleType;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.events.TouchEventType;
import com.facebook.react.views.picker.events.PickerItemSelectEvent;
import com.facebook.react.views.webview.events.TopLoadingErrorEvent;
import com.facebook.react.views.webview.events.TopLoadingFinishEvent;
import com.facebook.react.views.webview.events.TopLoadingStartEvent;
import com.facebook.react.views.webview.events.TopMessageEvent;
import java.util.HashMap;
import java.util.Map;

class UIManagerModuleConstants {
    public static final String ACTION_DISMISSED = "dismissed";
    public static final String ACTION_ITEM_SELECTED = "itemSelected";

    UIManagerModuleConstants() {
    }

    static Map getBubblingEventTypeConstants() {
        return MapBuilder.builder().put("topChange", MapBuilder.m1882of("phasedRegistrationNames", MapBuilder.m1883of("bubbled", "onChange", "captured", "onChangeCapture"))).put(PickerItemSelectEvent.EVENT_NAME, MapBuilder.m1882of("phasedRegistrationNames", MapBuilder.m1883of("bubbled", "onSelect", "captured", "onSelectCapture"))).put(TouchEventType.START.getJSEventName(), MapBuilder.m1882of("phasedRegistrationNames", MapBuilder.m1883of("bubbled", "onTouchStart", "captured", "onTouchStartCapture"))).put(TouchEventType.MOVE.getJSEventName(), MapBuilder.m1882of("phasedRegistrationNames", MapBuilder.m1883of("bubbled", "onTouchMove", "captured", "onTouchMoveCapture"))).put(TouchEventType.END.getJSEventName(), MapBuilder.m1882of("phasedRegistrationNames", MapBuilder.m1883of("bubbled", "onTouchEnd", "captured", "onTouchEndCapture"))).build();
    }

    static Map getDirectEventTypeConstants() {
        return MapBuilder.builder().put("topContentSizeChange", MapBuilder.m1882of("registrationName", "onContentSizeChange")).put("topLayout", MapBuilder.m1882of("registrationName", "onLayout")).put(TopLoadingErrorEvent.EVENT_NAME, MapBuilder.m1882of("registrationName", "onLoadingError")).put(TopLoadingFinishEvent.EVENT_NAME, MapBuilder.m1882of("registrationName", "onLoadingFinish")).put(TopLoadingStartEvent.EVENT_NAME, MapBuilder.m1882of("registrationName", "onLoadingStart")).put("topSelectionChange", MapBuilder.m1882of("registrationName", "onSelectionChange")).put(TopMessageEvent.EVENT_NAME, MapBuilder.m1882of("registrationName", "onMessage")).build();
    }

    public static Map<String, Object> getConstants() {
        HashMap<String, Object> constants = new HashMap<>();
        constants.put("UIView", MapBuilder.m1882of("ContentMode", MapBuilder.m1884of("ScaleAspectFit", Integer.valueOf(ScaleType.FIT_CENTER.ordinal()), "ScaleAspectFill", Integer.valueOf(ScaleType.CENTER_CROP.ordinal()), "ScaleAspectCenter", Integer.valueOf(ScaleType.CENTER_INSIDE.ordinal()))));
        DisplayMetrics displayMetrics = DisplayMetricsHolder.getWindowDisplayMetrics();
        DisplayMetrics screenDisplayMetrics = DisplayMetricsHolder.getScreenDisplayMetrics();
        constants.put("Dimensions", MapBuilder.m1883of("windowPhysicalPixels", MapBuilder.m1886of("width", Integer.valueOf(displayMetrics.widthPixels), "height", Integer.valueOf(displayMetrics.heightPixels), "scale", Float.valueOf(displayMetrics.density), "fontScale", Float.valueOf(displayMetrics.scaledDensity), "densityDpi", Integer.valueOf(displayMetrics.densityDpi)), "screenPhysicalPixels", MapBuilder.m1886of("width", Integer.valueOf(screenDisplayMetrics.widthPixels), "height", Integer.valueOf(screenDisplayMetrics.heightPixels), "scale", Float.valueOf(screenDisplayMetrics.density), "fontScale", Float.valueOf(screenDisplayMetrics.scaledDensity), "densityDpi", Integer.valueOf(screenDisplayMetrics.densityDpi))));
        constants.put("StyleConstants", MapBuilder.m1882of("PointerEventsValues", MapBuilder.m1885of("none", Integer.valueOf(PointerEvents.NONE.ordinal()), "boxNone", Integer.valueOf(PointerEvents.BOX_NONE.ordinal()), "boxOnly", Integer.valueOf(PointerEvents.BOX_ONLY.ordinal()), "unspecified", Integer.valueOf(PointerEvents.AUTO.ordinal()))));
        constants.put("PopupMenu", MapBuilder.m1883of("dismissed", "dismissed", ACTION_ITEM_SELECTED, ACTION_ITEM_SELECTED));
        constants.put("AccessibilityEventTypes", MapBuilder.m1883of("typeWindowStateChanged", Integer.valueOf(32), "typeViewClicked", Integer.valueOf(1)));
        return constants;
    }
}
