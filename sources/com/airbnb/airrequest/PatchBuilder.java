package com.airbnb.airrequest;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

public class PatchBuilder {
    private final JSONArray patch = new JSONArray();

    public PatchBuilder add(Map<String, String> toAdd) {
        for (Entry<String, String> entry : toAdd.entrySet()) {
            add((String) entry.getKey(), (String) entry.getValue());
        }
        return this;
    }

    public PatchBuilder replace(Map<String, String> toReplace) {
        for (Entry<String, String> entry : toReplace.entrySet()) {
            replace((String) entry.getKey(), (String) entry.getValue());
        }
        return this;
    }

    public PatchBuilder add(String key, String value) {
        this.patch.put(new JSONObject(ImmutableMap.builder().put("op", "add").put("path", "/" + key).put("value", value).build()));
        return this;
    }

    public PatchBuilder remove(String toRemove) {
        this.patch.put(new JSONObject(ImmutableMap.builder().put("op", "remove").put("path", "/" + toRemove).build()));
        return this;
    }

    public PatchBuilder replace(String key, String value) {
        this.patch.put(new JSONObject(ImmutableMap.builder().put("op", "replace").put("path", "/" + key).put("value", value).build()));
        return this;
    }

    public String toString() {
        return this.patch.toString();
    }
}
