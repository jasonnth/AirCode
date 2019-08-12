package com.airbnb.android.react;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.AirActivityFacade;
import com.facebook.react.bridge.ReadableMap;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReactNavigationCoordinator {
    private final Map<String, WeakReference<ReactInterface>> componentsMap = new HashMap();
    private final Map<String, Boolean> dismissCloseBehaviorMap = new HashMap();
    private final List<ReactExposedActivityParams> exposedActivities;
    private final Map<String, Integer> sceneBackgroundColorMap = new HashMap();
    private final Map<String, Integer> sceneToolbarBackgroundColorMap = new HashMap();
    private final Map<String, Integer> sceneToolbarForegroundColorMap = new HashMap();
    private final Map<String, Integer> sceneToolbarThemeMap = new HashMap();
    private final String targetNavigationTag = null;

    public ReactNavigationCoordinator(List<ReactExposedActivityParams> exposedActivities2) {
        this.exposedActivities = exposedActivities2;
    }

    public void registerComponent(ReactInterface component, String name) {
        this.componentsMap.put(name, new WeakReference(component));
    }

    public void unregisterComponent(String name) {
        this.componentsMap.remove(name);
    }

    /* access modifiers changed from: 0000 */
    public Intent intentForKey(Context context, String key, ReadableMap arguments) {
        Optional<Intent> intent = FluentIterable.from((Iterable<E>) this.exposedActivities).firstMatch(ReactNavigationCoordinator$$Lambda$1.lambdaFactory$(key)).transform(ReactNavigationCoordinator$$Lambda$2.lambdaFactory$(context, arguments));
        if (intent.isPresent()) {
            return (Intent) intent.get();
        }
        throw new IllegalArgumentException(String.format("Tried to push Activity with key '%s', but it could not be found", new Object[]{key}));
    }

    /* access modifiers changed from: 0000 */
    public AirActivityFacade activityFromId(String id) {
        WeakReference<ReactInterface> ref = (WeakReference) this.componentsMap.get(id);
        if (ref == null) {
            return null;
        }
        return ((ReactInterface) ref.get()).getAirActivity();
    }

    /* access modifiers changed from: 0000 */
    public ReactInterface componentFromId(String id) {
        WeakReference<ReactInterface> ref = (WeakReference) this.componentsMap.get(id);
        if (ref == null) {
            return null;
        }
        return (ReactInterface) ref.get();
    }

    public void setDismissCloseBehavior(String id, boolean dismissClose) {
        this.dismissCloseBehaviorMap.put(id, Boolean.valueOf(dismissClose));
    }

    public boolean getDismissCloseBehavior(ReactInterface reactInterface) {
        Boolean dismissClose = (Boolean) this.dismissCloseBehaviorMap.get(reactInterface.getInstanceId());
        return dismissClose != null && dismissClose.booleanValue();
    }

    public void setBackgroundColorForModuleName(String sceneName, Integer color) {
        this.sceneBackgroundColorMap.put(sceneName, color);
    }

    public int getBackgroundColorForModuleName(String sceneName) {
        if (this.sceneBackgroundColorMap.containsKey(sceneName)) {
            return ((Integer) this.sceneBackgroundColorMap.get(sceneName)).intValue();
        }
        return -1;
    }

    public void setToolbarThemeForModuleName(String sceneName, Integer theme) {
        this.sceneToolbarThemeMap.put(sceneName, theme);
    }

    public int getToolbarThemeForModuleName(String sceneName) {
        if (this.sceneToolbarThemeMap.containsKey(sceneName)) {
            return ((Integer) this.sceneToolbarThemeMap.get(sceneName)).intValue();
        }
        return 3;
    }

    public void setToolbarForegroundColorForModuleName(String sceneName, Integer color) {
        this.sceneToolbarForegroundColorMap.put(sceneName, color);
    }

    public Integer getToolbarForegroundColorForModuleName(String sceneName) {
        return (Integer) this.sceneToolbarForegroundColorMap.get(sceneName);
    }

    public void setToolbarBackgroundColorForModuleName(String sceneName, Integer color) {
        this.sceneToolbarBackgroundColorMap.put(sceneName, color);
    }

    public Integer getToolbarBackgroundColorForModuleName(String sceneName) {
        return (Integer) this.sceneToolbarBackgroundColorMap.get(sceneName);
    }
}
