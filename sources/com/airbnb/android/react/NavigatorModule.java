package com.airbnb.android.react;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.p000v4.content.ContextCompat;
import android.support.p002v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.airbnb.android.core.AirActivityFacade;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.utils.ActivityUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.transitions.AutoSharedElementCallback;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.share.internal.ShareConstants;
import com.facebook.share.widget.ShareDialog;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Maps;
import com.jumio.p311nv.data.NVStrings;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p032rx.functions.Action1;

class NavigatorModule extends VersionedReactModuleBase {
    private static final Map<String, Integer> BACKGROUND_COLOR_MAP = ImmutableMap.m1297of("celebratory", Integer.valueOf(C7663R.color.n2_rausch), "valid", Integer.valueOf(C7663R.color.n2_babu), "invalid", Integer.valueOf(C7663R.color.n2_arches), "unvalidated", Integer.valueOf(C7663R.color.n2_babu), "white", Integer.valueOf(17170443));
    private static final Map<String, MenuButton> BUTTON_MAP = new Builder().put(ShareConstants.WEB_DIALOG_PARAM_FILTERS, new MenuButton(C7663R.C7664drawable.n2_ic_filters, C7663R.string.filters)).put("heart", new MenuButton(C7663R.C7664drawable.n2_heart_red_fill, C7663R.string.remove_from_wish_list, false)).put("heart-alt", new MenuButton(C7663R.C7664drawable.n2_heart_light_outline, C7663R.string.save_to_wish_list)).put(P3Arguments.FROM_MAP, new MenuButton(C7663R.C7664drawable.n2_ic_map, C7663R.string.menu_title_map)).put("more", new MenuButton(C7663R.C7664drawable.icon_more, C7663R.string.more)).put(ShareDialog.WEB_SHARE_DIALOG, new MenuButton(C7663R.C7664drawable.n2_ic_share, C7663R.string.share)).put(NVStrings.SEARCH, new MenuButton(C7663R.C7664drawable.n2_icon_search, C7663R.string.search)).put("archive", new MenuButton(C7663R.C7664drawable.n2_icon_archive, C7663R.string.archive)).build();
    private static final String CLOSE_BEHAVIOR_DISMISS = "dismiss";
    static final String EXTRA_CODE = "code";
    static final String EXTRA_IS_DISMISS = "isDismiss";
    static final String EXTRA_PAYLOAD = "payload";
    static final String EXTRA_POP_INCLUSIVE = "popInclusive";
    static final String EXTRA_POP_NAVIGATION_TAG = "navigationTag";
    private static final Map<String, Integer> FOREGROUND_COLOR_MAP = ImmutableMap.m1297of("celebratory", Integer.valueOf(C7663R.color.n2_action_bar_foreground_light), "valid", Integer.valueOf(C7663R.color.n2_action_bar_foreground_light), "invalid", Integer.valueOf(C7663R.color.n2_action_bar_foreground_light), "unvalidated", Integer.valueOf(C7663R.color.n2_action_bar_foreground_light), "white", Integer.valueOf(C7663R.color.n2_action_bar_foreground_dark));
    private static final Map<String, Integer> LEFT_ICON_MAP = ImmutableMap.m1296of("close", Integer.valueOf(2), "menu", Integer.valueOf(3), "none", Integer.valueOf(0), "nav-left", Integer.valueOf(1));
    private static final String RESULT_CODE = "resultCode";
    private static final String SHARED_ELEMENT_TRANSITION_GROUP_OPTION = "transitionGroup";
    private static final Map<String, Integer> THEME_MAP = ImmutableMap.m1295of("opaque", Integer.valueOf(1), "transparent-dark", Integer.valueOf(3), "transparent-light", Integer.valueOf(2));
    private static final int VERSION = 2;
    private final ReactNavigationCoordinator coordinator;
    private final Handler handler;

    interface OnMenuButtonClickListener {
        void onClick(MenuButton menuButton, int i);
    }

    private interface ToolbarModifier extends Action1<AirToolbar> {
    }

    NavigatorModule(ReactApplicationContext reactContext, ReactNavigationCoordinator coordinator2, Handler handler2) {
        super(reactContext, 2);
        this.coordinator = coordinator2;
        this.handler = handler2;
    }

    NavigatorModule(ReactApplicationContext reactContext, ReactNavigationCoordinator coordinator2) {
        this(reactContext, coordinator2, new Handler(Looper.getMainLooper()));
    }

    public String getName() {
        return "AirbnbNavigatorModule";
    }

    @ReactMethod
    public void registerSceneBackgroundColor(String sceneName, Integer color) {
        this.coordinator.setBackgroundColorForModuleName(sceneName, color);
    }

    @ReactMethod
    public void registerSceneNavigationBarTheme(String sceneName, String theme) {
        this.coordinator.setToolbarThemeForModuleName(sceneName, (Integer) THEME_MAP.get(theme));
    }

    @ReactMethod
    public void registerSceneNavigationBarColor(String sceneName, String color) {
        this.coordinator.setToolbarForegroundColorForModuleName(sceneName, (Integer) FOREGROUND_COLOR_MAP.get(color));
        this.coordinator.setToolbarBackgroundColorForModuleName(sceneName, (Integer) BACKGROUND_COLOR_MAP.get(color));
    }

    @ReactMethod
    public void registerSceneNavigationBarType(String sceneName, String barType) {
    }

    @ReactMethod
    public void setTitle(String title, String id) {
        withToolbar(this.coordinator.componentFromId(id), NavigatorModule$$Lambda$1.lambdaFactory$(title));
    }

    @ReactMethod
    public void setLink(String link, String id) {
        ReactInterface component = this.coordinator.componentFromId(id);
        withToolbar(component, NavigatorModule$$Lambda$2.lambdaFactory$(component, link));
    }

    @ReactMethod
    public void setLeftIcon(String leftIcon, String id) {
        withToolbar(this.coordinator.componentFromId(id), NavigatorModule$$Lambda$3.lambdaFactory$(leftIcon != null ? ((Integer) LEFT_ICON_MAP.get(leftIcon)).intValue() : 1));
    }

    @ReactMethod
    public void setHideStatusBarUntilFoldOffset(boolean hideStatusBarUntilFoldOffset) {
    }

    @ReactMethod
    public void setButtons(ReadableArray buttons, String id) {
        FluentIterable from = FluentIterable.from((Iterable<E>) ConversionUtil.toStringArray(buttons));
        Map<String, MenuButton> map = BUTTON_MAP;
        map.getClass();
        List<MenuButton> menuButtons = from.transform(NavigatorModule$$Lambda$4.lambdaFactory$(map)).toList();
        ReactInterface component = this.coordinator.componentFromId(id);
        withToolbar(component, NavigatorModule$$Lambda$5.lambdaFactory$(component, menuButtons));
    }

    @ReactMethod
    public void setBackgroundColor(String color, String id) {
        int colorId = color != null ? ((Integer) BACKGROUND_COLOR_MAP.get(color)).intValue() : ((Integer) BACKGROUND_COLOR_MAP.get("white")).intValue();
        int fgColorId = color != null ? ((Integer) FOREGROUND_COLOR_MAP.get(color)).intValue() : ((Integer) FOREGROUND_COLOR_MAP.get("white")).intValue();
        ReactInterface component = this.coordinator.componentFromId(id);
        withToolbar(component, NavigatorModule$$Lambda$6.lambdaFactory$(component, colorId, fgColorId));
    }

    static /* synthetic */ void lambda$setBackgroundColor$4(ReactInterface component, int colorId, int fgColorId, AirToolbar toolbar) {
        Context context = component.getAirActivity().getBaseContext();
        toolbar.setBackgroundColor(ContextCompat.getColor(context, colorId));
        toolbar.setForegroundColor(ContextCompat.getColor(context, fgColorId));
    }

    @ReactMethod
    public void setTheme(String theme, String id) {
        withToolbar(this.coordinator.componentFromId(id), NavigatorModule$$Lambda$7.lambdaFactory$(theme != null ? ((Integer) THEME_MAP.get(theme)).intValue() : 1));
    }

    @ReactMethod
    public void setLeadingButtonVisible(boolean leadingButtonVisible, String id) {
        AirActivityFacade activity = this.coordinator.activityFromId(id);
        if (activity != null) {
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayShowHomeEnabled(leadingButtonVisible);
            }
        }
    }

    @ReactMethod
    public void setCloseBehavior(String closeBehavior, String id) {
        if ("dismiss".equals(closeBehavior)) {
            this.coordinator.setDismissCloseBehavior(id, true);
        }
    }

    @ReactMethod
    public void signalFirstRenderComplete(String id) {
        ReactInterface component = this.coordinator.componentFromId(id);
        if (component != null) {
            AirActivityFacade activity = component.getAirActivity();
            component.getClass();
            activity.runOnUiThread(NavigatorModule$$Lambda$8.lambdaFactory$(component));
        }
    }

    @ReactMethod
    public void push(String screenName, ReadableMap props, ReadableMap options, Promise promise) {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            startActivityWithPromise(activity, ReactNativeIntents.intent(getReactApplicationContext(), screenName, ConversionUtil.toBundle(props), ConversionUtil.toBundle(options)), promise, options);
        }
    }

    @ReactMethod
    public void pushNative(String name, ReadableMap props, ReadableMap options, Promise promise) {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            startActivityWithPromise(activity, this.coordinator.intentForKey(activity.getBaseContext(), name, props), promise, options);
        }
    }

    @ReactMethod
    public void present(String screenName, ReadableMap props, ReadableMap options, Promise promise) {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            startActivityWithPromise(activity, ReactNativeIntents.modalIntent(getReactApplicationContext(), screenName, ConversionUtil.toBundle(props), ConversionUtil.toBundle(options)), promise, options);
        }
    }

    @ReactMethod
    public void presentNative(String name, ReadableMap props, ReadableMap options, Promise promise) {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            startActivityWithPromise(activity, this.coordinator.intentForKey(activity.getBaseContext(), name, props), promise, options);
        }
    }

    @ReactMethod
    public void dismiss(ReadableMap payload, ReadableMap options) {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            dismiss(activity, payload);
        }
    }

    @ReactMethod
    public void pop(ReadableMap payload, ReadableMap options) {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            pop(activity, payload, options);
        }
    }

    private void withToolbar(ReactInterface component, ToolbarModifier modifier) {
        if (component != null) {
            component.getAirActivity().runOnUiThread(NavigatorModule$$Lambda$9.lambdaFactory$(component, modifier));
        }
    }

    static /* synthetic */ void lambda$withToolbar$6(ReactInterface component, ToolbarModifier modifier) {
        AirToolbar toolbar = component.getToolbar();
        if (toolbar != null) {
            modifier.call(toolbar);
        }
    }

    private void startActivityWithPromise(Activity activity, Intent intent, Promise promise, ReadableMap options) {
        this.handler.post(NavigatorModule$$Lambda$10.lambdaFactory$(activity, options, intent, promise));
    }

    static /* synthetic */ void lambda$startActivityWithPromise$7(Activity activity, ReadableMap options, Intent intent, Promise promise) {
        if (!ActivityUtils.hasActivityStopped(activity)) {
            Bundle optionsBundle = null;
            if (options != null && options.hasKey(SHARED_ELEMENT_TRANSITION_GROUP_OPTION) && (activity instanceof ReactInterface)) {
                ViewGroup transitionGroup = ViewLibUtils.findViewGroupWithTag(((ReactInterface) activity).getReactRootView(), C7663R.C7665id.react_shared_element_group_id, options.getString(SHARED_ELEMENT_TRANSITION_GROUP_OPTION));
                if (transitionGroup != null) {
                    ReactNativeUtils.setHasSharedElementTransition(intent, true);
                    optionsBundle = AutoSharedElementCallback.getActivityOptionsBundle(activity, transitionGroup);
                }
            }
            ReactInterfaceManager.startActivityWithPromise(activity, intent, promise, optionsBundle);
        }
    }

    private void dismiss(Activity activity, ReadableMap payload) {
        Intent intent = new Intent().putExtra(EXTRA_PAYLOAD, payloadToMap(payload));
        if (activity instanceof ReactInterface) {
            intent.putExtra(EXTRA_IS_DISMISS, ((ReactInterface) activity).isDismissible());
        }
        activity.setResult(getResultCodeFromPayload(payload), intent);
        activity.finish();
    }

    private void pop(Activity activity, ReadableMap payload, ReadableMap options) {
        Intent intent = new Intent().putExtra(EXTRA_PAYLOAD, payloadToMap(payload));
        if (((activity instanceof ReactNativeActivity) && ((ReactNativeActivity) activity).canPopPastActivity()) && options != null && options.hasKey("navigationTag")) {
            intent.putExtra("navigationTag", options.getString("navigationTag"));
            intent.putExtra(EXTRA_POP_INCLUSIVE, options.getBoolean(EXTRA_POP_INCLUSIVE));
        }
        activity.setResult(getResultCodeFromPayload(payload), intent);
        activity.finish();
    }

    private static int getResultCodeFromPayload(ReadableMap payload) {
        if (payload == null || !payload.hasKey(RESULT_CODE)) {
            return -1;
        }
        if (payload.getType(RESULT_CODE) == ReadableType.Number) {
            return payload.getInt(RESULT_CODE);
        }
        throw new IllegalArgumentException("Found non-integer resultCode.");
    }

    private static HashMap<String, Object> payloadToMap(ReadableMap payload) {
        if (payload == null) {
            return Maps.newHashMap();
        }
        return Maps.newHashMap(ConversionUtil.toMap(payload));
    }

    static void addButtonsToMenu(Context context, Menu menu, List<MenuButton> buttons, OnMenuButtonClickListener onClickListener) {
        for (int i = 0; i < buttons.size(); i++) {
            MenuButton button = (MenuButton) buttons.get(i);
            MenuItem item = menu.add(button.title);
            item.setShowAsAction(1);
            int buttonIndex = i;
            if (button.useForegroundColor) {
                item.setIcon(button.icon);
                item.setOnMenuItemClickListener(NavigatorModule$$Lambda$11.lambdaFactory$(onClickListener, button, buttonIndex));
            } else {
                ReactMenuItemView itemView = (ReactMenuItemView) LayoutInflater.from(context).inflate(C7663R.layout.menu_item_view, new LinearLayout(context), false);
                itemView.setImageResource(button.icon);
                itemView.setOnClickListener(NavigatorModule$$Lambda$12.lambdaFactory$(onClickListener, button, buttonIndex));
                itemView.setContentDescription(context.getString(button.title));
                item.setActionView(itemView);
            }
        }
    }
}
