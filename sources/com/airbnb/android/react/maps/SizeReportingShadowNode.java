package com.airbnb.android.react.maps;

import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.UIViewOperationQueue;
import java.util.HashMap;
import java.util.Map;

public class SizeReportingShadowNode extends LayoutShadowNode {
    public void onCollectExtraUpdates(UIViewOperationQueue uiViewOperationQueue) {
        super.onCollectExtraUpdates(uiViewOperationQueue);
        Map<String, Float> data = new HashMap<>();
        data.put("width", Float.valueOf(getLayoutWidth()));
        data.put("height", Float.valueOf(getLayoutHeight()));
        uiViewOperationQueue.enqueueUpdateExtraData(getReactTag(), data);
    }
}
