package p315io.branch.referral;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import p315io.branch.referral.Branch.BranchReferralInitListener;
import p315io.branch.referral.Defines.Jsonkey;

/* renamed from: io.branch.referral.ServerRequestIdentifyUserRequest */
class ServerRequestIdentifyUserRequest extends ServerRequest {
    BranchReferralInitListener callback_;
    String userId_ = null;

    public ServerRequestIdentifyUserRequest(String requestPath, JSONObject post, Context context) {
        super(requestPath, post, context);
    }

    public void onRequestSucceeded(ServerResponse resp, Branch branch) {
        try {
            if (getPost() != null && getPost().has(Jsonkey.Identity.getKey())) {
                this.prefHelper_.setIdentity(getPost().getString(Jsonkey.Identity.getKey()));
            }
            this.prefHelper_.setIdentityID(resp.getObject().getString(Jsonkey.IdentityID.getKey()));
            this.prefHelper_.setUserURL(resp.getObject().getString(Jsonkey.Link.getKey()));
            if (resp.getObject().has(Jsonkey.ReferringData.getKey())) {
                this.prefHelper_.setInstallParams(resp.getObject().getString(Jsonkey.ReferringData.getKey()));
            }
            if (this.callback_ != null) {
                this.callback_.onInitFinished(branch.getFirstReferringParams(), null);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void handleFailure(int statusCode, String causeMsg) {
        if (this.callback_ != null) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("error_message", "Trouble reaching server. Please try again in a few minutes");
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            this.callback_.onInitFinished(obj, new BranchError("Trouble setting the user alias. " + causeMsg, statusCode));
        }
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            try {
                String userId = getPost().getString(Jsonkey.Identity.getKey());
                if (userId == null || userId.length() == 0 || userId.equals(this.prefHelper_.getIdentity())) {
                    return true;
                }
                return false;
            } catch (JSONException e) {
                return true;
            }
        } else if (this.callback_ == null) {
            return true;
        } else {
            this.callback_.onInitFinished(null, new BranchError("Trouble setting the user alias.", -102));
            return true;
        }
    }

    public boolean isGetRequest() {
        return false;
    }

    public void clearCallbacks() {
        this.callback_ = null;
    }

    public boolean shouldRetryOnFail() {
        return true;
    }
}
