package com.jumio.commons.json;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class JumioJSONObject extends JSONObject {
    private JSONStringer stringer = null;

    public JumioJSONObject() {
    }

    public JumioJSONObject(String json) throws JSONException {
        super(json);
    }

    public StringBuilder getStringBuilder(String name) throws JSONException {
        Object object = get(name);
        StringBuilder result = JSON.toStringBuilder(object);
        if (result != null) {
            return result;
        }
        throw JSON.typeMismatch(name, object, "StringBuilder");
    }

    public StringBuilder optStringBuilder(String name) {
        return optStringBuilder(name, new StringBuilder().append(""));
    }

    public StringBuilder optStringBuilder(String name, StringBuilder fallback) {
        StringBuilder result = JSON.toStringBuilder(opt(name));
        return result != null ? result : fallback;
    }

    public void clear() {
        if (this.stringer != null) {
            this.stringer.clear();
        }
        Iterator<?> it = keys();
        while (it.hasNext()) {
            try {
                Object next = opt((String) it.next());
                if (next != null) {
                    if (next instanceof StringBuilder) {
                        overwriteStringBuilder((StringBuilder) next);
                    } else if (next instanceof JumioJSONArray) {
                        ((JumioJSONArray) next).clear();
                    } else if (next instanceof JumioJSONObject) {
                        ((JumioJSONObject) next).clear();
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    private void overwriteStringBuilder(StringBuilder reference) {
        if (reference != null) {
            for (int i = 0; i < reference.length(); i++) {
                reference.setCharAt(i, 0);
            }
        }
    }

    public String toString() {
        try {
            if (this.stringer != null) {
                this.stringer.clear();
            }
            this.stringer = new JSONStringer();
            writeTo(this.stringer);
            return this.stringer.toString();
        } catch (JSONException e) {
            return super.toString();
        }
    }

    public String toString(int indentSpaces) throws JSONException {
        if (this.stringer != null) {
            this.stringer.clear();
        }
        this.stringer = new JSONStringer(indentSpaces);
        writeTo(this.stringer);
        return this.stringer.toString();
    }

    public void writeTo(JSONStringer stringer2) throws JSONException {
        stringer2.object();
        Iterator<?> it = keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            stringer2.key(key).value(opt(key));
        }
        stringer2.endObject();
    }
}
