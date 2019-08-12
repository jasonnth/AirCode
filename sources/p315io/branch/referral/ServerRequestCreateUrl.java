package p315io.branch.referral;

import android.content.Context;
import java.net.URLEncoder;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;
import p315io.branch.referral.Branch.BranchLinkCreateListener;
import p315io.branch.referral.Defines.Jsonkey;
import p315io.branch.referral.Defines.LinkParam;
import p315io.branch.referral.Defines.RequestPath;

/* renamed from: io.branch.referral.ServerRequestCreateUrl */
class ServerRequestCreateUrl extends ServerRequest {
    private BranchLinkCreateListener callback_;
    private boolean defaultToLongUrl_ = true;
    private boolean isAsync_ = true;
    private boolean isReqStartedFromBranchShareSheet_;
    private BranchLinkData linkPost_;

    public ServerRequestCreateUrl(Context context, String alias, int type, int duration, Collection<String> tags, String channel, String feature, String stage, String campaign, String params, BranchLinkCreateListener callback, boolean async, boolean defaultToLongUrl) {
        super(context, RequestPath.GetURL.getPath());
        this.callback_ = callback;
        this.isAsync_ = async;
        this.defaultToLongUrl_ = defaultToLongUrl;
        this.linkPost_ = new BranchLinkData();
        try {
            this.linkPost_.put(Jsonkey.IdentityID.getKey(), this.prefHelper_.getIdentityID());
            this.linkPost_.put(Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
            this.linkPost_.put(Jsonkey.SessionID.getKey(), this.prefHelper_.getSessionID());
            if (!this.prefHelper_.getLinkClickID().equals("bnc_no_value")) {
                this.linkPost_.put(Jsonkey.LinkClickID.getKey(), this.prefHelper_.getLinkClickID());
            }
            this.linkPost_.putType(type);
            this.linkPost_.putDuration(duration);
            this.linkPost_.putTags(tags);
            this.linkPost_.putAlias(alias);
            this.linkPost_.putChannel(channel);
            this.linkPost_.putFeature(feature);
            this.linkPost_.putStage(stage);
            this.linkPost_.putCampaign(campaign);
            this.linkPost_.putParams(params);
            setPost(this.linkPost_);
        } catch (JSONException ex) {
            ex.printStackTrace();
            this.constructError_ = true;
        }
    }

    public ServerRequestCreateUrl(String requestPath, JSONObject post, Context context) {
        super(requestPath, post, context);
    }

    public BranchLinkData getLinkPost() {
        return this.linkPost_;
    }

    /* access modifiers changed from: 0000 */
    public boolean isDefaultToLongUrl() {
        return this.defaultToLongUrl_;
    }

    public boolean handleErrors(Context context) {
        if (!super.doesAppHasInternetPermission(context)) {
            if (this.callback_ == null) {
                return true;
            }
            this.callback_.onLinkCreate(null, new BranchError("Trouble creating a URL.", -102));
            return true;
        } else if (this.isAsync_ || hasUser()) {
            return false;
        } else {
            return true;
        }
    }

    public void onRequestSucceeded(ServerResponse resp, Branch branch) {
        try {
            String url = resp.getObject().getString("url");
            if (this.callback_ != null) {
                this.callback_.onLinkCreate(url, null);
            }
            updateShareEventToFabric(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onUrlAvailable(String url) {
        if (this.callback_ != null) {
            this.callback_.onLinkCreate(url, null);
        }
        updateShareEventToFabric(url);
    }

    public void handleFailure(int statusCode, String causeMsg) {
        if (this.callback_ != null) {
            String failedUrl = null;
            if (this.defaultToLongUrl_) {
                failedUrl = getLongUrl();
            }
            this.callback_.onLinkCreate(failedUrl, new BranchError("Trouble creating a URL. " + causeMsg, statusCode));
        }
    }

    public String getLongUrl() {
        if (!this.prefHelper_.getUserURL().equals("bnc_no_value")) {
            return generateLongUrlWithParams(this.prefHelper_.getUserURL());
        }
        return generateLongUrlWithParams("https://bnc.lt/a/" + this.prefHelper_.getBranchKey());
    }

    public void handleDuplicateURLError() {
        if (this.callback_ != null) {
            this.callback_.onLinkCreate(null, new BranchError("Trouble creating a URL.", -105));
        }
    }

    private boolean hasUser() {
        return !this.prefHelper_.getIdentityID().equals("bnc_no_value");
    }

    public boolean isGetRequest() {
        return false;
    }

    public void clearCallbacks() {
        this.callback_ = null;
    }

    public boolean isAsync() {
        return this.isAsync_;
    }

    private String generateLongUrlWithParams(String baseUrl) {
        String longUrl = baseUrl + "?";
        Collection<String> tags = this.linkPost_.getTags();
        if (tags != null) {
            for (String tag : tags) {
                if (tag != null && tag.length() > 0) {
                    longUrl = longUrl + LinkParam.Tags + "=" + tag + "&";
                }
            }
        }
        String alias = this.linkPost_.getAlias();
        if (alias != null && alias.length() > 0) {
            longUrl = longUrl + LinkParam.Alias + "=" + alias + "&";
        }
        String channel = this.linkPost_.getChannel();
        if (channel != null && channel.length() > 0) {
            longUrl = longUrl + LinkParam.Channel + "=" + channel + "&";
        }
        String feature = this.linkPost_.getFeature();
        if (feature != null && feature.length() > 0) {
            longUrl = longUrl + LinkParam.Feature + "=" + feature + "&";
        }
        String stage = this.linkPost_.getStage();
        if (stage != null && stage.length() > 0) {
            longUrl = longUrl + LinkParam.Stage + "=" + stage + "&";
        }
        String campaign = this.linkPost_.getCampaign();
        if (campaign != null && campaign.length() > 0) {
            longUrl = longUrl + LinkParam.Campaign + "=" + campaign + "&";
        }
        long duration = (long) this.linkPost_.getDuration();
        String longUrl2 = (longUrl + LinkParam.Type + "=" + ((long) this.linkPost_.getType()) + "&") + LinkParam.Duration + "=" + duration + "&";
        String params = this.linkPost_.getParams();
        if (params == null || params.length() <= 0) {
            return longUrl2;
        }
        try {
            return longUrl2 + "source=android&data=" + URLEncoder.encode(Base64.encodeToString(params.getBytes(), 2), "UTF8");
        } catch (Exception e) {
            this.callback_.onLinkCreate(null, new BranchError("Trouble creating a URL.", -116));
            return longUrl2;
        }
    }

    /* access modifiers changed from: 0000 */
    public void setIsReqStartedFromBranchShareSheet(boolean startedByShareSheet) {
        this.isReqStartedFromBranchShareSheet_ = startedByShareSheet;
    }

    /* access modifiers changed from: 0000 */
    public boolean isReqStartedFromBranchShareSheet() {
        return this.isReqStartedFromBranchShareSheet_;
    }

    private void updateShareEventToFabric(String url) {
        JSONObject linkDataJsonObj = this.linkPost_.getLinkDataJsonObject();
        if (isReqStartedFromBranchShareSheet() && linkDataJsonObj != null) {
            new ExtendedAnswerProvider().provideData("Branch Share", linkDataJsonObj, this.prefHelper_.getIdentityID());
        }
    }
}
