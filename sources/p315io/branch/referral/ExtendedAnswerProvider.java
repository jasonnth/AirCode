package p315io.branch.referral;

import android.text.TextUtils;
import com.crashlytics.android.answers.shim.AnswersOptionalLogger;
import com.crashlytics.android.answers.shim.KitEvent;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p315io.branch.referral.Defines.Jsonkey;

/* renamed from: io.branch.referral.ExtendedAnswerProvider */
class ExtendedAnswerProvider {
    ExtendedAnswerProvider() {
    }

    public void provideData(String eventName, JSONObject eventData, String identityID) {
        try {
            KitEvent kitEvent = new KitEvent(eventName);
            if (eventData != null) {
                addJsonObjectToKitEvent(kitEvent, eventData, "");
                kitEvent.putAttribute(Jsonkey.BranchIdentity.getKey(), identityID);
                AnswersOptionalLogger.get().logKitEvent(kitEvent);
            }
        } catch (Throwable th) {
        }
    }

    private void addJsonObjectToKitEvent(KitEvent kitEvent, JSONObject jsonData, String keyPathPrepend) throws JSONException {
        Iterator<String> keyIterator = jsonData.keys();
        while (keyIterator.hasNext()) {
            String key = (String) keyIterator.next();
            Object value = jsonData.get(key);
            if (!key.startsWith("+")) {
                if (value instanceof JSONObject) {
                    addJsonObjectToKitEvent(kitEvent, (JSONObject) value, keyPathPrepend + key + ".");
                } else if (value instanceof JSONArray) {
                    addJsonArrayToKitEvent(kitEvent, (JSONArray) value, key + ".");
                } else {
                    addBranchAttributes(kitEvent, keyPathPrepend, key, jsonData.getString(key));
                }
            }
        }
    }

    private void addJsonArrayToKitEvent(KitEvent kitEvent, JSONArray jsonArray, String keyPathPrepend) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            addBranchAttributes(kitEvent, keyPathPrepend, "~" + Integer.toString(i), jsonArray.getString(i));
        }
    }

    private void addBranchAttributes(KitEvent kitEvent, String keyPathPrepend, String key, String value) {
        if (TextUtils.isEmpty(value)) {
            return;
        }
        if (key.startsWith("~")) {
            kitEvent.putAttribute(keyPathPrepend.replaceFirst("~", "") + key.replaceFirst("~", ""), value);
        } else if (key.equals("$" + Jsonkey.IdentityID.getKey())) {
            kitEvent.putAttribute(Jsonkey.ReferringBranchIdentity.getKey(), value);
        }
    }
}
