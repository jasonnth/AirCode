package p315io.branch.referral;

import android.content.Context;
import org.json.JSONObject;
import p315io.branch.referral.Branch.BranchListResponseListener;

/* renamed from: io.branch.referral.ServerRequestGetRewardHistory */
class ServerRequestGetRewardHistory extends ServerRequest {
    BranchListResponseListener callback_;

    public ServerRequestGetRewardHistory(String requestPath, JSONObject post, Context context) {
        super(requestPath, post, context);
    }

    public void onRequestSucceeded(ServerResponse resp, Branch branch) {
        if (this.callback_ != null) {
            this.callback_.onReceivingResponse(resp.getArray(), null);
        }
    }

    public void handleFailure(int statusCode, String causeMsg) {
        if (this.callback_ != null) {
            this.callback_.onReceivingResponse(null, new BranchError("Trouble retrieving user credit history. " + causeMsg, statusCode));
        }
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        if (this.callback_ != null) {
            this.callback_.onReceivingResponse(null, new BranchError("Trouble retrieving user credit history.", -102));
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
