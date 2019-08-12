package com.facebook.react.animated;

import com.facebook.react.bridge.WritableArray;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.List;

class EventAnimationDriver implements RCTEventEmitter {
    private List<String> mEventPath;
    ValueAnimatedNode mValueNode;

    public EventAnimationDriver(List<String> eventPath, ValueAnimatedNode valueNode) {
        this.mEventPath = eventPath;
        this.mValueNode = valueNode;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=com.facebook.react.bridge.WritableMap, code=com.facebook.react.bridge.ReadableMap, for r9v0, types: [com.facebook.react.bridge.ReadableMap, com.facebook.react.bridge.WritableMap] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void receiveEvent(int r7, java.lang.String r8, com.facebook.react.bridge.ReadableMap r9) {
        /*
            r6 = this;
            if (r9 != 0) goto L_0x000b
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Native animated events must have event data."
            r2.<init>(r3)
            throw r2
        L_0x000b:
            r0 = r9
            r1 = 0
        L_0x000d:
            java.util.List<java.lang.String> r2 = r6.mEventPath
            int r2 = r2.size()
            int r2 = r2 + -1
            if (r1 >= r2) goto L_0x0026
            java.util.List<java.lang.String> r2 = r6.mEventPath
            java.lang.Object r2 = r2.get(r1)
            java.lang.String r2 = (java.lang.String) r2
            com.facebook.react.bridge.ReadableMap r0 = r0.getMap(r2)
            int r1 = r1 + 1
            goto L_0x000d
        L_0x0026:
            com.facebook.react.animated.ValueAnimatedNode r3 = r6.mValueNode
            java.util.List<java.lang.String> r2 = r6.mEventPath
            java.util.List<java.lang.String> r4 = r6.mEventPath
            int r4 = r4.size()
            int r4 = r4 + -1
            java.lang.Object r2 = r2.get(r4)
            java.lang.String r2 = (java.lang.String) r2
            double r4 = r0.getDouble(r2)
            r3.mValue = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.EventAnimationDriver.receiveEvent(int, java.lang.String, com.facebook.react.bridge.WritableMap):void");
    }

    public void receiveTouches(String eventName, WritableArray touches, WritableArray changedIndices) {
        throw new RuntimeException("receiveTouches is not support by native animated events");
    }
}
