package com.appboy.models;

import com.appboy.enums.inappmessage.MessageType;
import org.json.JSONException;
import org.json.JSONObject;
import p004bo.app.C0375bd;

public class InAppMessageHtmlFull extends InAppMessageHtmlBase {
    public InAppMessageHtmlFull() {
    }

    public InAppMessageHtmlFull(JSONObject object, C0375bd appboyManager) {
        super(object, appboyManager);
    }

    public JSONObject forJsonPut() {
        if (this.f2798g != null) {
            return this.f2798g;
        }
        try {
            JSONObject forJsonPut = super.forJsonPut();
            forJsonPut.put("type", MessageType.HTML_FULL.name());
            return forJsonPut;
        } catch (JSONException e) {
            return null;
        }
    }
}
