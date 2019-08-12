package com.miteksystems.misnap.params;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiParameterBuilder {
    JSONObject jjs = new JSONObject();

    public ApiParameterBuilder addParam(String key, String value) {
        try {
            this.jjs.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ApiParameterBuilder addParam(String key, boolean value) {
        try {
            this.jjs.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ApiParameterBuilder addParam(String key, int value) {
        try {
            this.jjs.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ApiParameterBuilder addParam(String key, int[] values) {
        try {
            JSONArray jsonArray = new JSONArray();
            for (int val : values) {
                jsonArray.put(val);
            }
            this.jjs.put(key, jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean contains(String key) {
        return this.jjs.has(key);
    }

    public JSONObject build() {
        return this.jjs;
    }
}
