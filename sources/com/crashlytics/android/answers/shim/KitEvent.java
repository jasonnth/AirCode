package com.crashlytics.android.answers.shim;

import com.crashlytics.android.answers.CustomEvent;
import java.util.HashMap;
import java.util.Map;

public class KitEvent {
    private final Map<String, Object> attributes = new HashMap();
    private final String eventName;

    public KitEvent(String eventName2) {
        this.eventName = eventName2;
    }

    public KitEvent putAttribute(String key, String value) {
        this.attributes.put(key, value);
        return this;
    }

    /* access modifiers changed from: 0000 */
    public CustomEvent toCustomEvent() {
        CustomEvent event = new CustomEvent(this.eventName);
        for (String key : this.attributes.keySet()) {
            Object value = this.attributes.get(key);
            if (value instanceof String) {
                event.putCustomAttribute(key, (String) value);
            } else if (value instanceof Number) {
                event.putCustomAttribute(key, (Number) value);
            }
        }
        return event;
    }
}
