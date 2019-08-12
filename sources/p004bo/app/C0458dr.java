package p004bo.app;

import com.appboy.enums.inappmessage.MessageType;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageFull;
import com.appboy.models.InAppMessageHtmlFull;
import com.appboy.models.InAppMessageModal;
import com.appboy.models.InAppMessageSlideup;
import com.appboy.support.AppboyLogger;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.dr */
public final class C0458dr {

    /* renamed from: a */
    private static final String f385a = AppboyLogger.getAppboyLogTag(C0458dr.class);

    /* renamed from: a */
    public static IInAppMessage m526a(JSONObject jSONObject, C0375bd bdVar) {
        if (jSONObject == null) {
            try {
                AppboyLogger.m1733d(f385a, "In-app message Json was null. Not de-serializing message.");
                return null;
            } catch (JSONException e) {
                AppboyLogger.m1740w(f385a, "Encountered JSONException processing in-app message: " + jSONObject.toString(), e);
                return null;
            } catch (Exception e2) {
                AppboyLogger.m1736e(f385a, "Failed to deserialize the in-app message: " + jSONObject.toString(), e2);
                return null;
            }
        } else {
            MessageType messageType = (MessageType) C0460ds.m528a(jSONObject, "type", MessageType.class, null);
            if (messageType == null) {
                AppboyLogger.m1737i(f385a, "In-app message type was null. Not de-serializing message: " + jSONObject.toString());
                return null;
            }
            switch (messageType) {
                case FULL:
                    return new InAppMessageFull(jSONObject, bdVar);
                case MODAL:
                    return new InAppMessageModal(jSONObject, bdVar);
                case SLIDEUP:
                    return new InAppMessageSlideup(jSONObject, bdVar);
                case HTML_FULL:
                    return new InAppMessageHtmlFull(jSONObject, bdVar);
                default:
                    AppboyLogger.m1735e(f385a, "Unknown in-app message type. Not de-serializing message: " + jSONObject.toString());
                    return null;
            }
        }
    }
}
