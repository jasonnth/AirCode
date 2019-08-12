package com.miteksystems.misnap.utils;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MibiData {
    public static final String CHANGED_PARAMETERS = "Changed Parameters";
    public static final String PARAMETERS = "Parameters";
    public static final String UXP = "UXP";
    public static final String WORKFLOW_PARAMETERS = "Workflow Parameters";
    JSONObject mJson;

    public MibiData() {
        this.mJson = new JSONObject();
    }

    public MibiData(String str) {
        try {
            this.mJson = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            this.mJson = new JSONObject();
            try {
                this.mJson.put("Error", str);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public String toString() {
        return this.mJson.toString();
    }

    public String getMibiData() {
        return this.mJson.toString();
    }

    public MibiData put(String str, String str2) {
        this.mJson.put(str, str2);
        return this;
    }

    public MibiData remove(String str) {
        this.mJson.remove(str);
        return this;
    }

    public MibiData putParams(String str) {
        return putParamsOfType(str, PARAMETERS);
    }

    public MibiData putParam(String str, String str2) {
        return putParamOfType(str, str2, PARAMETERS);
    }

    public MibiData removeParam(String str) {
        return removeParamOfType(str, PARAMETERS);
    }

    public MibiData putChangedParams(String str) {
        return putParamsOfType(str, CHANGED_PARAMETERS);
    }

    public MibiData appendChangedParams(String str) {
        return appendParamsOfType(str, CHANGED_PARAMETERS);
    }

    public MibiData putChangedParam(String str, String str2) {
        return putParamOfType(str, str2, CHANGED_PARAMETERS);
    }

    public MibiData removeChangedParam(String str) {
        return removeParamOfType(str, CHANGED_PARAMETERS);
    }

    public MibiData putWorkflowParam(String str, String str2) {
        return putParamOfType(str, str2, WORKFLOW_PARAMETERS);
    }

    public MibiData putWorkflowParams(String str) {
        return putParamsOfType(str, WORKFLOW_PARAMETERS);
    }

    public MibiData putUxpMetrics(JSONArray jSONArray) {
        this.mJson.put(UXP, jSONArray);
        return this;
    }

    /* access modifiers changed from: protected */
    public void changeIntValuesToStrings(JSONObject jSONObject) {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            try {
                jSONObject.put(str, jSONObject.getString(str));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private MibiData putParamsOfType(String str, String str2) {
        JSONObject jSONObject = new JSONObject(str);
        changeIntValuesToStrings(jSONObject);
        this.mJson.put(str2, jSONObject);
        return this;
    }

    private MibiData putParamOfType(String str, String str2, String str3) {
        JSONObject jSONObject;
        try {
            jSONObject = this.mJson.getJSONObject(str3);
        } catch (JSONException e) {
            jSONObject = new JSONObject();
            this.mJson.put(str3, jSONObject);
        }
        jSONObject.put(str, str2);
        return this;
    }

    private MibiData removeParamOfType(String str, String str2) {
        try {
            this.mJson.getJSONObject(str2).remove(str);
        } catch (JSONException e) {
        }
        return this;
    }

    private MibiData appendParamsOfType(String str, String str2) {
        try {
            new JSONObject(str);
            try {
                String jSONObject = this.mJson.getJSONObject(str2).toString();
                return putParamsOfType("{" + jSONObject.substring(1, jSONObject.length() - 1) + "," + str.substring(1, str.length() - 1) + "}", str2);
            } catch (JSONException e) {
                return putParamsOfType(str, str2);
            }
        } catch (JSONException e2) {
            return this;
        }
    }
}
