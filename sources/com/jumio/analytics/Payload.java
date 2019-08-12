package com.jumio.analytics;

import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Payload<T> {
    private MetaInfo mMetaInfo;
    private T mValue;

    public Payload(T value, MetaInfo metaInfo) {
        this.mValue = value;
        this.mMetaInfo = metaInfo;
    }

    public MetaInfo getMetaInfo() {
        return this.mMetaInfo;
    }

    public T getValue() {
        return this.mValue;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject obj = new JSONObject();
        if (this.mValue instanceof Map) {
            obj.put("value", new JSONObject((Map) this.mValue));
        } else {
            obj.put("value", this.mValue);
        }
        if (this.mMetaInfo != null && this.mMetaInfo.size() > 0) {
            obj.put("metainfo", new JSONObject(this.mMetaInfo));
        }
        return obj;
    }
}
