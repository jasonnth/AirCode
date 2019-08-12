package p315io.branch.referral;

import android.content.Context;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import p315io.branch.referral.Branch.BranchReferralStateChangedListener;

/* renamed from: io.branch.referral.ServerRequestGetRewards */
class ServerRequestGetRewards extends ServerRequest {
    BranchReferralStateChangedListener callback_;

    public ServerRequestGetRewards(String requestPath, JSONObject post, Context context) {
        super(requestPath, post, context);
    }

    public String getRequestUrl() {
        return super.getRequestUrl() + this.prefHelper_.getIdentityID();
    }

    public void onRequestSucceeded(ServerResponse resp, Branch branch) {
        boolean updateListener = false;
        Iterator<?> keys = resp.getObject().keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            try {
                int credits = resp.getObject().getInt(key);
                if (credits != this.prefHelper_.getCreditCount(key)) {
                    updateListener = true;
                }
                this.prefHelper_.setCreditCount(key, credits);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (this.callback_ != null) {
            this.callback_.onStateChanged(updateListener, null);
        }
    }

    public void handleFailure(int statusCode, String causeMsg) {
        if (this.callback_ != null) {
            this.callback_.onStateChanged(false, new BranchError("Trouble retrieving user credits. " + causeMsg, statusCode));
        }
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        if (this.callback_ != null) {
            this.callback_.onStateChanged(false, new BranchError("Trouble retrieving user credits.", -102));
        }
        return true;
    }

    public boolean isGetRequest() {
        return true;
    }

    public void clearCallbacks() {
        this.callback_ = null;
    }
}
