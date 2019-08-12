package p315io.branch.referral;

import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: io.branch.referral.ServerResponse */
public class ServerResponse {
    private Object post_;
    private int statusCode_;
    private String tag_;

    public ServerResponse(String tag, int statusCode) {
        this.tag_ = tag;
        this.statusCode_ = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode_;
    }

    public void setPost(Object post) {
        this.post_ = post;
    }

    public JSONObject getObject() {
        if (this.post_ instanceof JSONObject) {
            return (JSONObject) this.post_;
        }
        return new JSONObject();
    }

    public JSONArray getArray() {
        if (this.post_ instanceof JSONArray) {
            return (JSONArray) this.post_;
        }
        return null;
    }

    public String getFailReason() {
        String causeMsg = "";
        try {
            JSONObject postObj = getObject();
            if (postObj == null || !postObj.has("error") || !postObj.getJSONObject("error").has("message")) {
                return causeMsg;
            }
            String causeMsg2 = postObj.getJSONObject("error").getString("message");
            if (causeMsg2 == null || causeMsg2.trim().length() <= 0) {
                return causeMsg2;
            }
            return causeMsg2 + ".";
        } catch (Exception e) {
            return causeMsg;
        }
    }
}
