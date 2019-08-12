package p315io.branch.referral;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import p315io.branch.referral.Branch.BranchReferralStateChangedListener;
import p315io.branch.referral.Defines.Jsonkey;

/* renamed from: io.branch.referral.ServerRequestRedeemRewards */
class ServerRequestRedeemRewards extends ServerRequest {
    int actualNumOfCreditsToRedeem_ = 0;
    BranchReferralStateChangedListener callback_;

    public ServerRequestRedeemRewards(String requestPath, JSONObject post, Context context) {
        super(requestPath, post, context);
    }

    public boolean handleErrors(Context context) {
        if (!super.doesAppHasInternetPermission(context)) {
            if (this.callback_ == null) {
                return true;
            }
            this.callback_.onStateChanged(false, new BranchError("Trouble redeeming rewards.", -102));
            return true;
        } else if (this.actualNumOfCreditsToRedeem_ > 0) {
            return false;
        } else {
            if (this.callback_ == null) {
                return true;
            }
            this.callback_.onStateChanged(false, new BranchError("Trouble redeeming rewards.", -107));
            return true;
        }
    }

    public void onRequestSucceeded(ServerResponse resp, Branch branch) {
        boolean isRedemptionSucceeded = false;
        JSONObject post = getPost();
        if (post != null && post.has(Jsonkey.Bucket.getKey()) && post.has(Jsonkey.Amount.getKey())) {
            try {
                int redeemedCredits = post.getInt(Jsonkey.Amount.getKey());
                String creditBucket = post.getString(Jsonkey.Bucket.getKey());
                isRedemptionSucceeded = redeemedCredits > 0;
                this.prefHelper_.setCreditCount(creditBucket, this.prefHelper_.getCreditCount(creditBucket) - redeemedCredits);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (this.callback_ != null) {
            this.callback_.onStateChanged(isRedemptionSucceeded, isRedemptionSucceeded ? null : new BranchError("Trouble redeeming rewards.", -107));
        }
    }

    public void handleFailure(int statusCode, String causeMsg) {
        if (this.callback_ != null) {
            this.callback_.onStateChanged(false, new BranchError("Trouble redeeming rewards. " + causeMsg, statusCode));
        }
    }

    public boolean isGetRequest() {
        return false;
    }

    public void clearCallbacks() {
        this.callback_ = null;
    }
}
