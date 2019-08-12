package com.mparticle;

import android.util.Log;
import com.mparticle.MParticle.EventType;
import com.mparticle.MParticle.LogLevel;
import com.mparticle.internal.ConfigManager;
import com.mparticle.internal.MPUtility;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MPEvent {
    private String category;
    private Map<String, List<String>> customFlags;
    /* access modifiers changed from: private */
    public Double duration;
    /* access modifiers changed from: private */
    public Double endTime;
    /* access modifiers changed from: private */
    public boolean entering;
    private int eventHash;
    private String eventName;
    private EventType eventType;
    private Map<String, String> info;
    /* access modifiers changed from: private */
    public boolean screenEvent;
    /* access modifiers changed from: private */
    public Double startTime;

    public static class Builder {
        private static final String EVENT_CATEGORY = "category";
        private static final String EVENT_CUSTOM_FLAGS = "customFlags";
        private static final String EVENT_DURATION = "duration";
        private static final String EVENT_END_TIME = "endTime";
        private static final String EVENT_INFO = "info";
        private static final String EVENT_NAME = "eventName";
        private static final String EVENT_START_TIME = "startTime";
        private static final String EVENT_TYPE = "eventType";
        /* access modifiers changed from: private */
        public String category;
        /* access modifiers changed from: private */
        public Map<String, List<String>> customFlags = null;
        /* access modifiers changed from: private */
        public Double duration = null;
        /* access modifiers changed from: private */
        public Double endTime = null;
        /* access modifiers changed from: private */
        public boolean entering = true;
        /* access modifiers changed from: private */
        public String eventName;
        /* access modifiers changed from: private */
        public EventType eventType;
        /* access modifiers changed from: private */
        public Map<String, String> info;
        /* access modifiers changed from: private */
        public boolean screenEvent;
        /* access modifiers changed from: private */
        public Double startTime = null;

        private Builder() {
        }

        public Builder(String eventName2, EventType eventType2) {
            this.eventName = eventName2;
            this.eventType = eventType2;
        }

        public Builder(String eventName2) {
            this.eventName = eventName2;
            this.eventType = EventType.Other;
        }

        public Builder(MPEvent event) {
            this.eventName = event.getEventName();
            this.eventType = event.getEventType();
            this.category = event.getCategory();
            this.info = event.getInfo();
            this.duration = event.duration;
            this.startTime = event.startTime;
            this.endTime = event.endTime;
            this.customFlags = event.getCustomFlags();
            this.entering = event.entering;
            this.screenEvent = event.screenEvent;
        }

        public Builder eventName(String eventName2) {
            if (eventName2 != null) {
                this.eventName = eventName2;
            }
            return this;
        }

        public Builder eventType(EventType eventType2) {
            if (eventType2 != null) {
                this.eventType = eventType2;
            }
            return this;
        }

        public Builder addCustomFlag(String key, String value) {
            if (this.customFlags == null) {
                this.customFlags = new HashMap();
            }
            if (!this.customFlags.containsKey(key)) {
                this.customFlags.put(key, new LinkedList());
            }
            ((List) this.customFlags.get(key)).add(value);
            return this;
        }

        public Builder category(String category2) {
            this.category = category2;
            return this;
        }

        public Builder duration(double durationMillis) {
            this.duration = Double.valueOf(durationMillis);
            return this;
        }

        public Builder info(Map<String, String> info2) {
            this.info = info2;
            return this;
        }

        private Builder startTime(double startTimeMillis) {
            this.startTime = Double.valueOf(startTimeMillis);
            return this;
        }

        public Builder startTime() {
            return startTime((double) System.currentTimeMillis());
        }

        public Builder endTime() {
            return endTime((double) System.currentTimeMillis());
        }

        private Builder endTime(double endTimeMillis) {
            this.endTime = Double.valueOf(endTimeMillis);
            return this;
        }

        public Builder internalNavigationDirection(boolean entering2) {
            this.entering = entering2;
            return this;
        }

        public MPEvent build() {
            return new MPEvent(this);
        }

        public static Builder parseString(String builderString) {
            Builder builder;
            Exception exc;
            try {
                JSONObject jSONObject = new JSONObject(builderString);
                Builder builder2 = new Builder(jSONObject.getString(EVENT_NAME), EventType.valueOf(jSONObject.getString(EVENT_TYPE)));
                try {
                    builder2.category = jSONObject.optString("category");
                    if (jSONObject.has("duration")) {
                        builder2.duration = Double.valueOf(jSONObject.getDouble("duration"));
                    }
                    if (jSONObject.has(EVENT_START_TIME)) {
                        builder2.startTime = Double.valueOf(jSONObject.getDouble(EVENT_START_TIME));
                    }
                    if (jSONObject.has(EVENT_END_TIME)) {
                        builder2.endTime = Double.valueOf(jSONObject.getDouble(EVENT_END_TIME));
                    }
                    if (jSONObject.has(EVENT_INFO)) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject(EVENT_INFO);
                        HashMap hashMap = new HashMap();
                        Iterator keys = jSONObject2.keys();
                        while (keys.hasNext()) {
                            String str = (String) keys.next();
                            hashMap.put(str, jSONObject2.getString(str));
                        }
                        builder2.info = hashMap;
                    }
                    if (jSONObject.has(EVENT_CUSTOM_FLAGS)) {
                        JSONObject jSONObject3 = jSONObject.getJSONObject(EVENT_CUSTOM_FLAGS);
                        HashMap hashMap2 = new HashMap();
                        Iterator keys2 = jSONObject3.keys();
                        while (keys2.hasNext()) {
                            String str2 = (String) keys2.next();
                            JSONArray jSONArray = jSONObject3.getJSONArray(str2);
                            hashMap2.put(str2, new LinkedList());
                            for (int i = 0; i < jSONArray.length(); i++) {
                                ((List) hashMap2.get(str2)).add(jSONArray.getString(i));
                            }
                        }
                        builder2.customFlags = hashMap2;
                    }
                    return builder2;
                } catch (Exception e) {
                    exc = e;
                    builder = builder2;
                    Log.w("mParticle SDK", "Failed to deserialize MPEvent.Builder: " + exc.toString());
                    return builder;
                }
            } catch (Exception e2) {
                Exception exc2 = e2;
                builder = null;
                exc = exc2;
                Log.w("mParticle SDK", "Failed to deserialize MPEvent.Builder: " + exc.toString());
                return builder;
            }
        }

        public String toString() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(EVENT_TYPE, this.eventType.toString());
                jSONObject.put(EVENT_NAME, this.eventName);
                if (this.category != null) {
                    jSONObject.put("category", this.category);
                }
                if (this.duration != null) {
                    jSONObject.put("duration", this.duration);
                }
                if (this.info != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    for (Entry entry : this.info.entrySet()) {
                        jSONObject2.put((String) entry.getKey(), entry.getValue());
                    }
                    jSONObject.put(EVENT_INFO, jSONObject2);
                }
                if (this.startTime != null) {
                    jSONObject.put(EVENT_START_TIME, this.startTime);
                }
                if (this.endTime != null) {
                    jSONObject.put(EVENT_END_TIME, this.endTime);
                }
                if (this.customFlags != null) {
                    JSONObject jSONObject3 = new JSONObject();
                    for (Entry entry2 : this.customFlags.entrySet()) {
                        jSONObject3.put((String) entry2.getKey(), new JSONArray((List) entry2.getValue()));
                    }
                    jSONObject.put(EVENT_CUSTOM_FLAGS, jSONObject3);
                }
                return jSONObject.toString();
            } catch (JSONException e) {
                Log.w("mParticle SDK", "Failed to serialize MPEvent.Builder: " + e.toString());
                return super.toString();
            }
        }
    }

    private MPEvent() {
        this.duration = null;
        this.startTime = null;
        this.endTime = null;
        this.entering = true;
    }

    private MPEvent(Builder builder) {
        this.duration = null;
        this.startTime = null;
        this.endTime = null;
        this.entering = true;
        if (builder.eventType == null) {
            ConfigManager.log(LogLevel.ERROR, "MPEvent created with no event type!");
        } else {
            this.eventType = builder.eventType;
        }
        if (builder.eventName == null) {
            ConfigManager.log(LogLevel.ERROR, "MPEvent created with no event name!");
        } else {
            if (builder.eventName.length() > 256) {
                ConfigManager.log(LogLevel.ERROR, "MPEvent created with too long of a name, the limit is: 256");
            }
            this.eventName = builder.eventName;
        }
        this.entering = builder.entering;
        this.info = builder.info;
        if (builder.category != null) {
            this.category = builder.category;
            if (this.info == null) {
                this.info = new HashMap(1);
            }
            this.info.put("$Category", builder.category);
        }
        if (builder.duration != null) {
            this.duration = builder.duration;
        }
        if (builder.endTime != null) {
            this.endTime = builder.endTime;
        }
        if (builder.startTime != null) {
            this.startTime = builder.startTime;
        }
        if (builder.customFlags != null) {
            this.customFlags = builder.customFlags;
        }
        this.screenEvent = builder.screenEvent;
    }

    public boolean equals(Object o) {
        return super.equals(o) || (o != null && toString().equals(o.toString()));
    }

    public void setInfo(Map<String, String> info2) {
        this.info = info2;
    }

    public MPEvent(MPEvent mpEvent) {
        this.duration = null;
        this.startTime = null;
        this.endTime = null;
        this.entering = true;
        this.eventType = mpEvent.eventType;
        this.eventName = mpEvent.eventName;
        if (mpEvent.info != null) {
            HashMap hashMap = new HashMap();
            hashMap.putAll(mpEvent.info);
            this.info = hashMap;
        } else {
            this.info = null;
        }
        this.category = mpEvent.category;
        this.duration = mpEvent.duration;
        this.endTime = mpEvent.endTime;
        this.startTime = mpEvent.startTime;
        this.customFlags = mpEvent.customFlags;
        this.entering = mpEvent.entering;
        this.screenEvent = mpEvent.screenEvent;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.eventName != null) {
            sb.append("Event name: ").append(this.eventName).append("\n");
        }
        if (this.eventType != null) {
            sb.append("type: ").append(this.eventType.name()).append("\n");
        }
        Double length = getLength();
        if (length != null && length.doubleValue() > 0.0d) {
            sb.append("length: ").append(length).append("ms").append("\n");
        }
        if (this.info != null) {
            sb.append("info:\n");
            ArrayList<String> arrayList = new ArrayList<>(this.info.keySet());
            Collections.sort(arrayList);
            for (String str : arrayList) {
                sb.append(str).append(":").append((String) this.info.get(str)).append("\n");
            }
        }
        if (this.customFlags != null) {
            sb.append("custom flags:\n");
            sb.append(this.customFlags.toString());
        }
        return sb.toString();
    }

    public String getEventName() {
        return this.eventName;
    }

    public boolean isScreenEvent() {
        return this.screenEvent;
    }

    /* access modifiers changed from: 0000 */
    public MPEvent setScreenEvent(boolean screenEvent2) {
        this.screenEvent = screenEvent2;
        return this;
    }

    public int getEventHash() {
        if (this.eventHash == 0) {
            this.eventHash = MPUtility.mpHash(this.eventType.ordinal() + this.eventName);
        }
        return this.eventHash;
    }

    public String getCategory() {
        return this.category;
    }

    public Map<String, String> getInfo() {
        return this.info;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public Double getLength() {
        if (this.duration != null) {
            return this.duration;
        }
        if (this.endTime == null || this.startTime == null) {
            return null;
        }
        double doubleValue = this.endTime.doubleValue() - this.startTime.doubleValue();
        if (doubleValue <= 0.0d) {
            doubleValue = 0.0d;
        }
        return Double.valueOf(doubleValue);
    }

    public Map<String, List<String>> getCustomFlags() {
        return this.customFlags;
    }

    /* access modifiers changed from: 0000 */
    public boolean getNavigationDirection() {
        return this.entering;
    }
}
