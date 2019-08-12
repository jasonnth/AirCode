package com.airbnb.android.react;

import com.airbnb.android.core.AirActivityFacade;
import com.airbnb.p027n2.components.AirToolbar;
import com.facebook.react.ReactRootView;
import java.util.List;

public interface ReactInterface {
    AirActivityFacade getAirActivity();

    String getInstanceId();

    ReactRootView getReactRootView();

    AirToolbar getToolbar();

    boolean isDismissible();

    void notifySharedElementAddition();

    void setLink(String str);

    void setMenuButtons(List<MenuButton> list);

    void signalFirstRenderComplete();
}
