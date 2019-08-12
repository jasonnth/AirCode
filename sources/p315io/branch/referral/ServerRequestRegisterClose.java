package p315io.branch.referral;

import android.content.Context;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import p315io.branch.indexing.ContentDiscoverer;
import p315io.branch.referral.Defines.Jsonkey;
import p315io.branch.referral.Defines.RequestPath;

/* renamed from: io.branch.referral.ServerRequestRegisterClose */
class ServerRequestRegisterClose extends ServerRequest {
    public ServerRequestRegisterClose(Context context) {
        super(context, RequestPath.RegisterClose.getPath());
        JSONObject closePost = new JSONObject();
        try {
            closePost.put(Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
            closePost.put(Jsonkey.IdentityID.getKey(), this.prefHelper_.getIdentityID());
            closePost.put(Jsonkey.SessionID.getKey(), this.prefHelper_.getSessionID());
            if (!this.prefHelper_.getLinkClickID().equals("bnc_no_value")) {
                closePost.put(Jsonkey.LinkClickID.getKey(), this.prefHelper_.getLinkClickID());
            }
            JSONObject ciObject = ContentDiscoverer.getInstance().getContentDiscoverDataForCloseRequest(context);
            if (ciObject != null) {
                closePost.put(Jsonkey.ContentDiscovery.getKey(), ciObject);
            }
            if (DeviceInfo.getInstance() != null) {
                closePost.put(Jsonkey.AppVersion.getKey(), DeviceInfo.getInstance().getAppVersion());
            }
            setPost(closePost);
        } catch (JSONException ex) {
            ex.printStackTrace();
            this.constructError_ = true;
        }
    }

    public ServerRequestRegisterClose(String requestPath, JSONObject post, Context context) {
        super(requestPath, post, context);
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        Log.i("BranchSDK", "Trouble executing your request. Please add 'android.permission.INTERNET' in your applications manifest file");
        return true;
    }

    public void onRequestSucceeded(ServerResponse resp, Branch branch) {
        this.prefHelper_.setSessionParams("bnc_no_value");
    }

    public void handleFailure(int statusCode, String causeMsg) {
    }

    public boolean isGetRequest() {
        return false;
    }

    public void clearCallbacks() {
    }
}
