package p315io.branch.referral;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.mparticle.commerce.Product;
import org.json.JSONException;
import org.json.JSONObject;
import p315io.branch.referral.BranchViewHandler.IBranchViewEvents;
import p315io.branch.referral.Defines.Jsonkey;
import p315io.branch.referral.Defines.RequestPath;

/* renamed from: io.branch.referral.ServerRequestActionCompleted */
class ServerRequestActionCompleted extends ServerRequest {
    private final IBranchViewEvents callback_;

    public ServerRequestActionCompleted(Context context, String action, JSONObject metadata, IBranchViewEvents callback) {
        super(context, RequestPath.CompletedAction.getPath());
        this.callback_ = callback;
        JSONObject post = new JSONObject();
        try {
            post.put(Jsonkey.IdentityID.getKey(), this.prefHelper_.getIdentityID());
            post.put(Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
            post.put(Jsonkey.SessionID.getKey(), this.prefHelper_.getSessionID());
            if (!this.prefHelper_.getLinkClickID().equals("bnc_no_value")) {
                post.put(Jsonkey.LinkClickID.getKey(), this.prefHelper_.getLinkClickID());
            }
            post.put(Jsonkey.Event.getKey(), action);
            if (metadata != null) {
                post.put(Jsonkey.Metadata.getKey(), metadata);
            }
            updateEnvironment(context, post);
            setPost(post);
        } catch (JSONException ex) {
            ex.printStackTrace();
            this.constructError_ = true;
        }
        if (action != null && action.equalsIgnoreCase(Product.PURCHASE)) {
            Log.e("BranchSDK", "Warning: You are sending a purchase event with our non-dedicated purchase function. Please see function sendCommerceEvent");
        }
    }

    public ServerRequestActionCompleted(String requestPath, JSONObject post, Context context) {
        super(requestPath, post, context);
        this.callback_ = null;
    }

    public void onRequestSucceeded(ServerResponse resp, Branch branch) {
        if (resp.getObject() != null && resp.getObject().has(Jsonkey.BranchViewData.getKey()) && Branch.getInstance().currentActivityReference_ != null && Branch.getInstance().currentActivityReference_.get() != null) {
            String actionName = "";
            try {
                JSONObject post = getPost();
                if (post != null && post.has(Jsonkey.Event.getKey())) {
                    actionName = post.getString(Jsonkey.Event.getKey());
                }
                if (Branch.getInstance().currentActivityReference_ != null) {
                    Activity currentActivity = (Activity) Branch.getInstance().currentActivityReference_.get();
                    BranchViewHandler.getInstance().showBranchView(resp.getObject().getJSONObject(Jsonkey.BranchViewData.getKey()), actionName, currentActivity, this.callback_);
                }
            } catch (JSONException e) {
                if (this.callback_ != null) {
                    this.callback_.onBranchViewError(-201, "Unable to show branch view. Branch view received is invalid ", actionName);
                }
            }
        }
    }

    public void handleFailure(int statusCode, String causeMsg) {
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        Log.i("BranchSDK", "Trouble executing your request. Please add 'android.permission.INTERNET' in your applications manifest file");
        return true;
    }

    public boolean isGetRequest() {
        return false;
    }

    public void clearCallbacks() {
    }

    public boolean shouldRetryOnFail() {
        return true;
    }
}