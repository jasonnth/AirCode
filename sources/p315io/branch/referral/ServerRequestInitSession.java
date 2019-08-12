package p315io.branch.referral;

import android.app.Activity;
import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import p315io.branch.indexing.ContentDiscoverer;
import p315io.branch.indexing.ContentDiscoveryManifest;
import p315io.branch.referral.Branch.IBranchViewControl;
import p315io.branch.referral.Defines.Jsonkey;

/* renamed from: io.branch.referral.ServerRequestInitSession */
abstract class ServerRequestInitSession extends ServerRequest {
    private final ContentDiscoveryManifest contentDiscoveryManifest_ = ContentDiscoveryManifest.getInstance(this.context_);
    private final Context context_;

    public abstract String getRequestActionName();

    public abstract boolean hasCallBack();

    ServerRequestInitSession(Context context, String requestPath) {
        super(context, requestPath);
        this.context_ = context;
    }

    ServerRequestInitSession(String requestPath, JSONObject post, Context context) {
        super(requestPath, post, context);
        this.context_ = context;
    }

    /* access modifiers changed from: protected */
    public void setPost(JSONObject post) {
        super.setPost(post);
        updateEnvironment(this.context_, post);
    }

    public boolean isGAdsParamsRequired() {
        return true;
    }

    static boolean isInitSessionAction(String actionName) {
        if (actionName != null) {
            return actionName.equalsIgnoreCase("open") || actionName.equalsIgnoreCase("install");
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean handleBranchViewIfAvailable(ServerResponse resp) {
        if (resp == null || resp.getObject() == null || !resp.getObject().has(Jsonkey.BranchViewData.getKey())) {
            return false;
        }
        try {
            JSONObject branchViewJsonObj = resp.getObject().getJSONObject(Jsonkey.BranchViewData.getKey());
            String actionName = getRequestActionName();
            if (Branch.getInstance().currentActivityReference_ == null || Branch.getInstance().currentActivityReference_.get() == null) {
                return BranchViewHandler.getInstance().markInstallOrOpenBranchViewPending(branchViewJsonObj, actionName);
            }
            Activity currentActivity = (Activity) Branch.getInstance().currentActivityReference_.get();
            boolean isActivityEnabledForBranchView = true;
            if (currentActivity instanceof IBranchViewControl) {
                isActivityEnabledForBranchView = !((IBranchViewControl) currentActivity).skipBranchViewsOnThisActivity();
            }
            if (isActivityEnabledForBranchView) {
                return BranchViewHandler.getInstance().showBranchView(branchViewJsonObj, actionName, currentActivity, Branch.getInstance());
            }
            return BranchViewHandler.getInstance().markInstallOrOpenBranchViewPending(branchViewJsonObj, actionName);
        } catch (JSONException e) {
            return false;
        }
    }

    public void onRequestSucceeded(ServerResponse response, Branch branch) {
        try {
            this.prefHelper_.setLinkClickIdentifier("bnc_no_value");
            this.prefHelper_.setGoogleSearchInstallIdentifier("bnc_no_value");
            this.prefHelper_.setGooglePlayReferrer("bnc_no_value");
            this.prefHelper_.setExternalIntentUri("bnc_no_value");
            this.prefHelper_.setExternalIntentExtra("bnc_no_value");
            this.prefHelper_.setAppLink("bnc_no_value");
            this.prefHelper_.setPushIdentifier("bnc_no_value");
            this.prefHelper_.setIsAppLinkTriggeredInit(Boolean.valueOf(false));
            this.prefHelper_.setInstallReferrerParams("bnc_no_value");
            this.prefHelper_.setIsFullAppConversion(false);
            if (response.getObject() != null && response.getObject().has(Jsonkey.Data.getKey())) {
                new ExtendedAnswerProvider().provideData(this instanceof ServerRequestRegisterInstall ? "Branch Install" : "Branch Open", new JSONObject(response.getObject().getString(Jsonkey.Data.getKey())), this.prefHelper_.getIdentityID());
            }
        } catch (JSONException e) {
        }
    }

    /* access modifiers changed from: 0000 */
    public void onInitSessionCompleted(ServerResponse response, Branch branch) {
        if (this.contentDiscoveryManifest_ != null) {
            this.contentDiscoveryManifest_.onBranchInitialised(response.getObject());
            if (branch.currentActivityReference_ != null) {
                try {
                    ContentDiscoverer.getInstance().onSessionStarted((Activity) branch.currentActivityReference_.get(), branch.sessionReferredLink_);
                } catch (Exception e) {
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void updateLinkReferrerParams() {
        String linkIdentifier = this.prefHelper_.getLinkClickIdentifier();
        if (!linkIdentifier.equals("bnc_no_value")) {
            try {
                getPost().put(Jsonkey.LinkIdentifier.getKey(), linkIdentifier);
            } catch (JSONException e) {
            }
        }
        String googleSearchInstallIdentifier = this.prefHelper_.getGoogleSearchInstallIdentifier();
        if (!googleSearchInstallIdentifier.equals("bnc_no_value")) {
            try {
                getPost().put(Jsonkey.GoogleSearchInstallReferrer.getKey(), googleSearchInstallIdentifier);
            } catch (JSONException e2) {
            }
        }
        String googlePlayReferrer = this.prefHelper_.getGooglePlayReferrer();
        if (!googlePlayReferrer.equals("bnc_no_value")) {
            try {
                getPost().put(Jsonkey.GooglePlayInstallReferrer.getKey(), googlePlayReferrer);
            } catch (JSONException e3) {
            }
        }
        if (this.prefHelper_.isFullAppConversion()) {
            try {
                getPost().put(Jsonkey.AndroidAppLinkURL.getKey(), this.prefHelper_.getAppLink());
                getPost().put(Jsonkey.IsFullAppConv.getKey(), true);
            } catch (JSONException e4) {
            }
        }
    }

    public void onPreExecute() {
        JSONObject post = getPost();
        try {
            if (!this.prefHelper_.getAppLink().equals("bnc_no_value")) {
                post.put(Jsonkey.AndroidAppLinkURL.getKey(), this.prefHelper_.getAppLink());
            }
            if (!this.prefHelper_.getPushIdentifier().equals("bnc_no_value")) {
                post.put(Jsonkey.AndroidPushIdentifier.getKey(), this.prefHelper_.getPushIdentifier());
            }
            if (!this.prefHelper_.getExternalIntentUri().equals("bnc_no_value")) {
                post.put(Jsonkey.External_Intent_URI.getKey(), this.prefHelper_.getExternalIntentUri());
            }
            if (!this.prefHelper_.getExternalIntentExtra().equals("bnc_no_value")) {
                post.put(Jsonkey.External_Intent_Extra.getKey(), this.prefHelper_.getExternalIntentExtra());
            }
            if (this.contentDiscoveryManifest_ != null) {
                JSONObject cdObj = new JSONObject();
                cdObj.put("mv", this.contentDiscoveryManifest_.getManifestVersion());
                cdObj.put("pn", this.context_.getPackageName());
                post.put("cd", cdObj);
            }
        } catch (JSONException e) {
        }
    }
}
