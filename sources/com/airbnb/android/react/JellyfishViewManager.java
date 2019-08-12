package com.airbnb.android.react;

import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Collections;
import java.util.Map;

public class JellyfishViewManager extends ViewGroupManager<JellyfishView> {
    private static final String PALETTE_BABU = "babu";
    private static final String PALETTE_RAUSCH = "rausch";
    private static final String REACT_CLASS = "AirbnbJellyfish";
    private static final int VERSION = 1;

    public Map<String, Object> getExportedViewConstants() {
        return Collections.singletonMap("VERSION", Integer.valueOf(1));
    }

    public String getName() {
        return REACT_CLASS;
    }

    public JellyfishView createViewInstance(ThemedReactContext context) {
        return new JellyfishView(context);
    }

    @ReactProp(name = "palette")
    public void setPalette(JellyfishView view, String palette) {
        char c = 65535;
        switch (palette.hashCode()) {
            case -938063310:
                if (palette.equals(PALETTE_RAUSCH)) {
                    c = 1;
                    break;
                }
                break;
            case 3015890:
                if (palette.equals(PALETTE_BABU)) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                view.setPalette(1);
                return;
            case 1:
                view.setPalette(2);
                return;
            default:
                return;
        }
    }

    @ReactProp(name = "timeScale")
    public void setTimeScale(JellyfishView view, float timeScale) {
        view.setTimeScale(timeScale);
    }
}
