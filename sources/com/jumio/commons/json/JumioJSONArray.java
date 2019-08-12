package com.jumio.commons.json;

import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;

public class JumioJSONArray extends JSONArray {
    private JSONStringer stringer;

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
        stringer2.array();
        for (int i = 0; i < length(); i++) {
            stringer2.value(opt(i));
        }
        stringer2.endArray();
    }

    public void clear() {
        if (this.stringer != null) {
            this.stringer.clear();
        }
        for (int i = 0; i < length(); i++) {
            Object next = opt(i);
            if (next != null) {
                if (next instanceof StringBuilder) {
                    overwriteStringBuilder((StringBuilder) next);
                } else if (next instanceof char[]) {
                    overwriteCharArray((char[]) next);
                } else if (next instanceof JumioJSONArray) {
                    ((JumioJSONArray) next).clear();
                } else if (next instanceof JumioJSONObject) {
                    ((JumioJSONObject) next).clear();
                }
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

    private void overwriteCharArray(char[] reference) {
        if (reference != null) {
            for (int i = 0; i < reference.length; i++) {
                Arrays.fill(reference, 0);
            }
        }
    }
}
