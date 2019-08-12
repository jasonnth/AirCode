package p315io.branch.referral;

import android.content.Context;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import p315io.branch.referral.Branch.LogoutStatusListener;
import p315io.branch.referral.Defines.Jsonkey;

/* renamed from: io.branch.referral.ServerRequestLogout */
class ServerRequestLogout extends ServerRequest {
    private LogoutStatusListener callback_;

    public ServerRequestLogout(String requestPath, JSONObject post, Context context) {
        super(requestPath, post, context);
    }

    public void onRequestSucceeded(ServerResponse resp, Branch branch) {
        try {
            this.prefHelper_.setSessionID(resp.getObject().getString(Jsonkey.SessionID.getKey()));
            this.prefHelper_.setIdentityID(resp.getObject().getString(Jsonkey.IdentityID.getKey()));
            this.prefHelper_.setUserURL(resp.getObject().getString(Jsonkey.Link.getKey()));
            this.prefHelper_.setInstallParams("bnc_no_value");
            this.prefHelper_.setSessionParams("bnc_no_value");
            this.prefHelper_.setIdentity("bnc_no_value");
            this.prefHelper_.clearUserValues();
            if (this.callback_ != null) {
                this.callback_.onLogoutFinished(true, null);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            if (this.callback_ != null) {
                this.callback_.onLogoutFinished(true, null);
            }
        } catch (Throwable th) {
            if (this.callback_ != null) {
                this.callback_.onLogoutFinished(true, null);
            }
            throw th;
        }
    }

    public void handleFailure(int statusCode, String causeMsg) {
        if (this.callback_ != null) {
            this.callback_.onLogoutFinished(false, new BranchError("Logout error. " + causeMsg, statusCode));
        }
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        Log.i("BranchSDK", "Trouble executing your request. Please add 'android.permission.INTERNET' in your applications manifest file");
        if (this.callback_ != null) {
            this.callback_.onLogoutFinished(false, new BranchError("Logout failed", -102));
        }
        return true;
    }

    public boolean isGetRequest() {
        return false;
    }

    public void clearCallbacks() {
        this.callback_ = null;
    }
}
