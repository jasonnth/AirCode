package com.airbnb.android.react;

import android.support.p000v4.view.ViewCompat;
import android.view.View;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.Map;

public class SharedElementViewManager extends ViewGroupManager<ReactViewGroup> {
    private static final String REACT_CLASS = "AirbnbSharedElement";
    private static final int VERSION = 1;
    private final ReactNavigationCoordinator coordinator;

    SharedElementViewManager(ReactNavigationCoordinator coordinator2) {
        this.coordinator = coordinator2;
    }

    public Map<String, Object> getExportedViewConstants() {
        return MapBuilder.builder().put("VERSION", Integer.valueOf(1)).build();
    }

    public String getName() {
        return REACT_CLASS;
    }

    public ReactViewGroup createViewInstance(ThemedReactContext context) {
        return new ReactViewGroup(context);
    }

    @ReactProp(name = "id")
    public void setIdentifier(ReactViewGroup view, String id) {
        view.setTag(C7663R.C7665id.react_shared_element_transition_name, id);
    }

    @ReactProp(name = "airbnbInstanceId")
    public void setAirbnbInstanceId(ReactViewGroup view, String airbnbInstanceId) {
        view.setTag(C7663R.C7665id.react_shared_element_screen_instance_id, airbnbInstanceId);
    }

    public void addView(ReactViewGroup parent, View child, int index) {
        String transitionName = (String) parent.getTag(C7663R.C7665id.react_shared_element_transition_name);
        ReactInterface component = this.coordinator.componentFromId((String) parent.getTag(C7663R.C7665id.react_shared_element_screen_instance_id));
        if (child instanceof ReactAirImageView) {
            ReactAirImageView reactAirImageView = (ReactAirImageView) child;
        }
        ViewCompat.setTransitionName(child, transitionName);
        parent.addView(child, index);
        if (component != null) {
            component.notifySharedElementAddition();
        }
    }
}
