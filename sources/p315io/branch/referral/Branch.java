package p315io.branch.referral;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.BadParcelableException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdPhotoSelectionFragment;
import com.facebook.common.util.UriUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p315io.branch.indexing.BranchUniversalObject;
import p315io.branch.indexing.BranchUniversalObject.RegisterViewStatusListener;
import p315io.branch.indexing.ContentDiscoverer;
import p315io.branch.referral.BranchViewHandler.IBranchViewEvents;
import p315io.branch.referral.DeferredAppLinkDataHandler.AppLinkFetchEvents;
import p315io.branch.referral.Defines.Jsonkey;
import p315io.branch.referral.Defines.RequestPath;
import p315io.branch.referral.SharingHelper.SHARE_WITH;
import p315io.branch.referral.network.BranchRemoteInterface;

/* renamed from: io.branch.referral.Branch */
public class Branch implements IBranchViewEvents, IInstallReferrerEvents, GAdsParamsFetchEvents {
    private static final String[] EXTERNAL_INTENT_EXTRA_KEY_WHITE_LIST = {"extra_launch_uri"};
    private static int LATCH_WAIT_UNTIL = 2500;
    /* access modifiers changed from: private */
    public static Branch branchReferral_;
    static boolean checkInstallReferrer_ = true;
    private static String cookieBasedMatchDomain_ = "app.link";
    private static CUSTOM_REFERRABLE_SETTINGS customReferrableSettings_ = CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT;
    private static boolean disableDeviceIDFetch_;
    private static boolean isActivityLifeCycleCallbackRegistered_ = false;
    private static boolean isAutoSessionMode_ = false;
    private static boolean isLogging_ = false;
    private static boolean isSimulatingInstalls_;
    private static long playStoreReferrerFetchTime = 1500;
    /* access modifiers changed from: private */
    public BranchRemoteInterface branchRemoteInterface_;
    /* access modifiers changed from: private */
    public Context context_;
    WeakReference<Activity> currentActivityReference_;
    private JSONObject deeplinkDebugParams_;
    private boolean enableFacebookAppLinkCheck_ = false;
    private List<String> externalUriWhiteList_;
    /* access modifiers changed from: private */
    public CountDownLatch getFirstReferringParamsLatch = null;
    /* access modifiers changed from: private */
    public CountDownLatch getLatestReferringParamsLatch = null;
    /* access modifiers changed from: private */
    public boolean handleDelayedNewIntents_ = false;
    /* access modifiers changed from: private */
    public boolean hasNetwork_;
    /* access modifiers changed from: private */
    public SESSION_STATE initState_ = SESSION_STATE.UNINITIALISED;
    /* access modifiers changed from: private */
    public final ConcurrentHashMap<String, String> instrumentationExtraData_;
    /* access modifiers changed from: private */
    public INTENT_STATE intentState_ = INTENT_STATE.PENDING;
    private boolean isGAParamsFetchInProgress_ = false;
    /* access modifiers changed from: private */
    public boolean isInitReportedThroughCallBack = false;
    /* access modifiers changed from: private */
    public Map<BranchLinkData, String> linkCache_;
    final Object lock;
    /* access modifiers changed from: private */
    public int networkCount_;
    private boolean performCookieBasedStrongMatchingOnGAIDAvailable = false;
    /* access modifiers changed from: private */
    public PrefHelper prefHelper_;
    /* access modifiers changed from: private */
    public ServerRequestQueue requestQueue_;
    private Semaphore serverSema_;
    String sessionReferredLink_;
    /* access modifiers changed from: private */
    public ShareLinkManager shareLinkManager_;
    private List<String> skipExternalUriHosts_;
    /* access modifiers changed from: private */
    public final SystemObserver systemObserver_;

    @TargetApi(14)
    /* renamed from: io.branch.referral.Branch$BranchActivityLifeCycleObserver */
    private class BranchActivityLifeCycleObserver implements ActivityLifecycleCallbacks {
        private int activityCnt_;

        private BranchActivityLifeCycleObserver() {
            this.activityCnt_ = 0;
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            Branch.this.intentState_ = Branch.this.handleDelayedNewIntents_ ? INTENT_STATE.PENDING : INTENT_STATE.READY;
            if (BranchViewHandler.getInstance().isInstallOrOpenBranchViewPending(activity.getApplicationContext())) {
                BranchViewHandler.getInstance().showPendingBranchView(activity);
            }
        }

        public void onActivityStarted(Activity activity) {
            Branch.this.intentState_ = Branch.this.handleDelayedNewIntents_ ? INTENT_STATE.PENDING : INTENT_STATE.READY;
            if (Branch.this.initState_ == SESSION_STATE.INITIALISED) {
                try {
                    ContentDiscoverer.getInstance().discoverContent(activity, Branch.this.sessionReferredLink_);
                } catch (Exception e) {
                }
            }
            if (this.activityCnt_ < 1) {
                if (Branch.this.initState_ == SESSION_STATE.INITIALISED) {
                    Branch.this.initState_ = SESSION_STATE.UNINITIALISED;
                }
                if (BranchUtil.isTestModeEnabled(Branch.this.context_)) {
                    Branch.this.prefHelper_.setExternDebug();
                }
                Branch.this.prefHelper_.setLogging(Branch.getIsLogging());
                Branch.this.startSession(activity);
            } else if (Branch.this.checkIntentForSessionRestart(activity.getIntent())) {
                Branch.this.initState_ = SESSION_STATE.UNINITIALISED;
                Branch.this.startSession(activity);
            }
            this.activityCnt_++;
        }

        public void onActivityResumed(Activity activity) {
            if (Branch.this.checkIntentForSessionRestart(activity.getIntent())) {
                Branch.this.initState_ = SESSION_STATE.UNINITIALISED;
                Branch.this.startSession(activity);
            }
            Branch.this.currentActivityReference_ = new WeakReference<>(activity);
            if (Branch.this.handleDelayedNewIntents_) {
                Branch.this.intentState_ = INTENT_STATE.READY;
                Branch.this.onIntentReady(activity, (activity.getIntent() == null || Branch.this.initState_ == SESSION_STATE.INITIALISED) ? false : true);
            }
        }

        public void onActivityPaused(Activity activity) {
            if (Branch.this.shareLinkManager_ != null) {
                Branch.this.shareLinkManager_.cancelShareLinkDialog(true);
            }
        }

        public void onActivityStopped(Activity activity) {
            ContentDiscoverer.getInstance().onActivityStopped(activity);
            this.activityCnt_--;
            if (this.activityCnt_ < 1) {
                Branch.this.closeSessionInternal();
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
            if (Branch.this.currentActivityReference_ != null && Branch.this.currentActivityReference_.get() == activity) {
                Branch.this.currentActivityReference_.clear();
            }
            BranchViewHandler.getInstance().onCurrentActivityDestroyed(activity);
        }
    }

    /* renamed from: io.branch.referral.Branch$BranchLinkCreateListener */
    public interface BranchLinkCreateListener {
        void onLinkCreate(String str, BranchError branchError);
    }

    /* renamed from: io.branch.referral.Branch$BranchLinkShareListener */
    public interface BranchLinkShareListener {
        void onChannelSelected(String str);

        void onLinkShareResponse(String str, String str2, BranchError branchError);

        void onShareLinkDialogDismissed();

        void onShareLinkDialogLaunched();
    }

    /* renamed from: io.branch.referral.Branch$BranchListResponseListener */
    public interface BranchListResponseListener {
        void onReceivingResponse(JSONArray jSONArray, BranchError branchError);
    }

    /* renamed from: io.branch.referral.Branch$BranchPostTask */
    private class BranchPostTask extends BranchAsyncTask<Void, Void, ServerResponse> {
        ServerRequest thisReq_;

        public BranchPostTask(ServerRequest request) {
            this.thisReq_ = request;
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            this.thisReq_.onPreExecute();
        }

        /* access modifiers changed from: protected */
        public ServerResponse doInBackground(Void... voids) {
            if (this.thisReq_ instanceof ServerRequestInitSession) {
                ((ServerRequestInitSession) this.thisReq_).updateLinkReferrerParams();
            }
            Branch.this.addExtraInstrumentationData(this.thisReq_.getRequestPath() + "-" + Jsonkey.Queue_Wait_Time.getKey(), String.valueOf(this.thisReq_.getQueueWaitTime()));
            if (this.thisReq_.isGAdsParamsRequired() && !BranchUtil.isTestModeEnabled(Branch.this.context_)) {
                this.thisReq_.updateGAdsParams(Branch.this.systemObserver_);
            }
            if (this.thisReq_.isGetRequest()) {
                return Branch.this.branchRemoteInterface_.make_restful_get(this.thisReq_.getRequestUrl(), this.thisReq_.getGetParams(), this.thisReq_.getRequestPath(), Branch.this.prefHelper_.getBranchKey());
            }
            return Branch.this.branchRemoteInterface_.make_restful_post(this.thisReq_.getPostWithInstrumentationValues(Branch.this.instrumentationExtraData_), this.thisReq_.getRequestUrl(), this.thisReq_.getRequestPath(), Branch.this.prefHelper_.getBranchKey());
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(ServerResponse serverResponse) {
            super.onPostExecute(serverResponse);
            if (serverResponse != null) {
                try {
                    int status = serverResponse.getStatusCode();
                    Branch.this.hasNetwork_ = true;
                    if (status != 200) {
                        if (this.thisReq_ instanceof ServerRequestInitSession) {
                            Branch.this.initState_ = SESSION_STATE.UNINITIALISED;
                        }
                        if (status == 409) {
                            Branch.this.requestQueue_.remove(this.thisReq_);
                            if (this.thisReq_ instanceof ServerRequestCreateUrl) {
                                ((ServerRequestCreateUrl) this.thisReq_).handleDuplicateURLError();
                            } else {
                                Log.i("BranchSDK", "Branch API Error: Conflicting resource error code from API");
                                Branch.this.handleFailure(0, status);
                            }
                        } else {
                            Branch.this.hasNetwork_ = false;
                            ArrayList<ServerRequest> requestToFail = new ArrayList<>();
                            for (int i = 0; i < Branch.this.requestQueue_.getSize(); i++) {
                                requestToFail.add(Branch.this.requestQueue_.peekAt(i));
                            }
                            Iterator it = requestToFail.iterator();
                            while (it.hasNext()) {
                                ServerRequest req = (ServerRequest) it.next();
                                if (req == null || !req.shouldRetryOnFail()) {
                                    Branch.this.requestQueue_.remove(req);
                                }
                            }
                            Branch.this.networkCount_ = 0;
                            Iterator it2 = requestToFail.iterator();
                            while (it2.hasNext()) {
                                ServerRequest req2 = (ServerRequest) it2.next();
                                if (req2 != null) {
                                    req2.handleFailure(status, serverResponse.getFailReason());
                                    if (req2.shouldRetryOnFail()) {
                                        req2.clearCallbacks();
                                    }
                                }
                            }
                        }
                    } else {
                        Branch.this.hasNetwork_ = true;
                        if (this.thisReq_ instanceof ServerRequestCreateUrl) {
                            if (serverResponse.getObject() != null) {
                                Branch.this.linkCache_.put(((ServerRequestCreateUrl) this.thisReq_).getLinkPost(), serverResponse.getObject().getString("url"));
                            }
                        } else if (this.thisReq_ instanceof ServerRequestLogout) {
                            Branch.this.linkCache_.clear();
                            Branch.this.requestQueue_.clear();
                        }
                        Branch.this.requestQueue_.dequeue();
                        if ((this.thisReq_ instanceof ServerRequestInitSession) || (this.thisReq_ instanceof ServerRequestIdentifyUserRequest)) {
                            JSONObject respJson = serverResponse.getObject();
                            if (respJson != null) {
                                boolean updateRequestsInQueue = false;
                                if (respJson.has(Jsonkey.SessionID.getKey())) {
                                    Branch.this.prefHelper_.setSessionID(respJson.getString(Jsonkey.SessionID.getKey()));
                                    updateRequestsInQueue = true;
                                }
                                if (respJson.has(Jsonkey.IdentityID.getKey())) {
                                    if (!Branch.this.prefHelper_.getIdentityID().equals(respJson.getString(Jsonkey.IdentityID.getKey()))) {
                                        Branch.this.linkCache_.clear();
                                        Branch.this.prefHelper_.setIdentityID(respJson.getString(Jsonkey.IdentityID.getKey()));
                                        updateRequestsInQueue = true;
                                    }
                                }
                                if (respJson.has(Jsonkey.DeviceFingerprintID.getKey())) {
                                    Branch.this.prefHelper_.setDeviceFingerPrintID(respJson.getString(Jsonkey.DeviceFingerprintID.getKey()));
                                    updateRequestsInQueue = true;
                                }
                                if (updateRequestsInQueue) {
                                    Branch.this.updateAllRequestsInQueue();
                                }
                                if (this.thisReq_ instanceof ServerRequestInitSession) {
                                    Branch.this.initState_ = SESSION_STATE.INITIALISED;
                                    this.thisReq_.onRequestSucceeded(serverResponse, Branch.branchReferral_);
                                    Branch.this.isInitReportedThroughCallBack = ((ServerRequestInitSession) this.thisReq_).hasCallBack();
                                    if (!((ServerRequestInitSession) this.thisReq_).handleBranchViewIfAvailable(serverResponse)) {
                                        Branch.this.checkForAutoDeepLinkConfiguration();
                                    }
                                    if (Branch.this.getLatestReferringParamsLatch != null) {
                                        Branch.this.getLatestReferringParamsLatch.countDown();
                                    }
                                    if (Branch.this.getFirstReferringParamsLatch != null) {
                                        Branch.this.getFirstReferringParamsLatch.countDown();
                                    }
                                } else {
                                    this.thisReq_.onRequestSucceeded(serverResponse, Branch.branchReferral_);
                                }
                            }
                        } else {
                            this.thisReq_.onRequestSucceeded(serverResponse, Branch.branchReferral_);
                        }
                    }
                    Branch.this.networkCount_ = 0;
                    if (Branch.this.hasNetwork_ && Branch.this.initState_ != SESSION_STATE.UNINITIALISED) {
                        Branch.this.processNextQueueItem();
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /* renamed from: io.branch.referral.Branch$BranchReferralInitListener */
    public interface BranchReferralInitListener {
        void onInitFinished(JSONObject jSONObject, BranchError branchError);
    }

    /* renamed from: io.branch.referral.Branch$BranchReferralStateChangedListener */
    public interface BranchReferralStateChangedListener {
        void onStateChanged(boolean z, BranchError branchError);
    }

    /* renamed from: io.branch.referral.Branch$CUSTOM_REFERRABLE_SETTINGS */
    private enum CUSTOM_REFERRABLE_SETTINGS {
        USE_DEFAULT,
        REFERRABLE,
        NON_REFERRABLE
    }

    /* renamed from: io.branch.referral.Branch$IBranchViewControl */
    public interface IBranchViewControl {
        boolean skipBranchViewsOnThisActivity();
    }

    /* renamed from: io.branch.referral.Branch$IChannelProperties */
    public interface IChannelProperties {
        String getSharingMessageForChannel(String str);

        String getSharingTitleForChannel(String str);
    }

    /* renamed from: io.branch.referral.Branch$INTENT_STATE */
    private enum INTENT_STATE {
        PENDING,
        READY
    }

    /* renamed from: io.branch.referral.Branch$LogoutStatusListener */
    public interface LogoutStatusListener {
        void onLogoutFinished(boolean z, BranchError branchError);
    }

    /* renamed from: io.branch.referral.Branch$SESSION_STATE */
    private enum SESSION_STATE {
        INITIALISED,
        INITIALISING,
        UNINITIALISED
    }

    /* renamed from: io.branch.referral.Branch$ShareLinkBuilder */
    public static class ShareLinkBuilder {
        private final Activity activity_;
        private final Branch branch_;
        private BranchLinkShareListener callback_;
        private IChannelProperties channelPropertiesCallback_;
        private String copyURlText_;
        private Drawable copyUrlIcon_;
        private String defaultURL_;
        private int dividerHeight;
        private List<String> excludeFromShareSheet;
        private List<String> includeInShareSheet;
        private Drawable moreOptionIcon_;
        private String moreOptionText_;
        private ArrayList<SHARE_WITH> preferredOptions_;
        private boolean setFullWidthStyle_;
        private String shareMsg_;
        private String shareSub_;
        private String sharingTitle;
        private View sharingTitleView;
        BranchShortLinkBuilder shortLinkBuilder_;
        private int styleResourceID_;
        private String urlCopiedMessage_;

        public ShareLinkBuilder(Activity activity, JSONObject parameters) {
            this.callback_ = null;
            this.channelPropertiesCallback_ = null;
            this.dividerHeight = -1;
            this.sharingTitle = null;
            this.sharingTitleView = null;
            this.includeInShareSheet = new ArrayList();
            this.excludeFromShareSheet = new ArrayList();
            this.activity_ = activity;
            this.branch_ = Branch.branchReferral_;
            this.shortLinkBuilder_ = new BranchShortLinkBuilder(activity);
            try {
                Iterator<String> keys = parameters.keys();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    this.shortLinkBuilder_.addParameters(key, (String) parameters.get(key));
                }
            } catch (Exception e) {
            }
            this.shareMsg_ = "";
            this.callback_ = null;
            this.channelPropertiesCallback_ = null;
            this.preferredOptions_ = new ArrayList<>();
            this.defaultURL_ = null;
            this.moreOptionIcon_ = BranchUtil.getDrawable(activity.getApplicationContext(), 17301573);
            this.moreOptionText_ = "More...";
            this.copyUrlIcon_ = BranchUtil.getDrawable(activity.getApplicationContext(), 17301582);
            this.copyURlText_ = "Copy link";
            this.urlCopiedMessage_ = "Copied link to clipboard!";
        }

        public ShareLinkBuilder(Activity activity, BranchShortLinkBuilder shortLinkBuilder) {
            this(activity, new JSONObject());
            this.shortLinkBuilder_ = shortLinkBuilder;
        }

        public ShareLinkBuilder setMessage(String message) {
            this.shareMsg_ = message;
            return this;
        }

        public ShareLinkBuilder setSubject(String subject) {
            this.shareSub_ = subject;
            return this;
        }

        public ShareLinkBuilder setCallback(BranchLinkShareListener callback) {
            this.callback_ = callback;
            return this;
        }

        public ShareLinkBuilder setChannelProperties(IChannelProperties channelPropertiesCallback) {
            this.channelPropertiesCallback_ = channelPropertiesCallback;
            return this;
        }

        public ShareLinkBuilder addPreferredSharingOptions(ArrayList<SHARE_WITH> preferredOptions) {
            this.preferredOptions_.addAll(preferredOptions);
            return this;
        }

        public ShareLinkBuilder setDefaultURL(String url) {
            this.defaultURL_ = url;
            return this;
        }

        public ShareLinkBuilder setMoreOptionStyle(Drawable icon, String label) {
            this.moreOptionIcon_ = icon;
            this.moreOptionText_ = label;
            return this;
        }

        public ShareLinkBuilder setCopyUrlStyle(Drawable icon, String label, String message) {
            this.copyUrlIcon_ = icon;
            this.copyURlText_ = label;
            this.urlCopiedMessage_ = message;
            return this;
        }

        public ShareLinkBuilder setAsFullWidthStyle(boolean setFullWidthStyle) {
            this.setFullWidthStyle_ = setFullWidthStyle;
            return this;
        }

        public ShareLinkBuilder setDividerHeight(int height) {
            this.dividerHeight = height;
            return this;
        }

        public ShareLinkBuilder setSharingTitle(String title) {
            this.sharingTitle = title;
            return this;
        }

        public ShareLinkBuilder setSharingTitle(View titleView) {
            this.sharingTitleView = titleView;
            return this;
        }

        public ShareLinkBuilder excludeFromShareSheet(List<String> packageNames) {
            this.excludeFromShareSheet.addAll(packageNames);
            return this;
        }

        public ShareLinkBuilder includeInShareSheet(List<String> packageNames) {
            this.includeInShareSheet.addAll(packageNames);
            return this;
        }

        public void setStyleResourceID(int resourceID) {
            this.styleResourceID_ = resourceID;
        }

        public void shareLink() {
            Branch.branchReferral_.shareLink(this);
        }

        public Activity getActivity() {
            return this.activity_;
        }

        public ArrayList<SHARE_WITH> getPreferredOptions() {
            return this.preferredOptions_;
        }

        /* access modifiers changed from: 0000 */
        public List<String> getExcludedFromShareSheet() {
            return this.excludeFromShareSheet;
        }

        /* access modifiers changed from: 0000 */
        public List<String> getIncludedInShareSheet() {
            return this.includeInShareSheet;
        }

        public String getShareMsg() {
            return this.shareMsg_;
        }

        public String getShareSub() {
            return this.shareSub_;
        }

        public BranchLinkShareListener getCallback() {
            return this.callback_;
        }

        public IChannelProperties getChannelPropertiesCallback() {
            return this.channelPropertiesCallback_;
        }

        public String getDefaultURL() {
            return this.defaultURL_;
        }

        public Drawable getMoreOptionIcon() {
            return this.moreOptionIcon_;
        }

        public String getMoreOptionText() {
            return this.moreOptionText_;
        }

        public Drawable getCopyUrlIcon() {
            return this.copyUrlIcon_;
        }

        public String getCopyURlText() {
            return this.copyURlText_;
        }

        public String getUrlCopiedMessage() {
            return this.urlCopiedMessage_;
        }

        public BranchShortLinkBuilder getShortLinkBuilder() {
            return this.shortLinkBuilder_;
        }

        public boolean getIsFullWidthStyle() {
            return this.setFullWidthStyle_;
        }

        public int getDividerHeight() {
            return this.dividerHeight;
        }

        public String getSharingTitle() {
            return this.sharingTitle;
        }

        public View getSharingTitleView() {
            return this.sharingTitleView;
        }

        public int getStyleResourceID() {
            return this.styleResourceID_;
        }
    }

    /* renamed from: io.branch.referral.Branch$getShortLinkTask */
    private class getShortLinkTask extends AsyncTask<ServerRequest, Void, ServerResponse> {
        private getShortLinkTask() {
        }

        /* access modifiers changed from: protected */
        public ServerResponse doInBackground(ServerRequest... serverRequests) {
            return Branch.this.branchRemoteInterface_.make_restful_post(serverRequests[0].getPost(), Branch.this.prefHelper_.getAPIBaseUrl() + "v1/url", RequestPath.GetURL.getPath(), Branch.this.prefHelper_.getBranchKey());
        }
    }

    private Branch(Context context) {
        this.prefHelper_ = PrefHelper.getInstance(context);
        this.branchRemoteInterface_ = BranchRemoteInterface.getDefaultBranchRemoteInterface(context);
        this.systemObserver_ = new SystemObserver(context);
        this.requestQueue_ = ServerRequestQueue.getInstance(context);
        this.serverSema_ = new Semaphore(1);
        this.lock = new Object();
        this.networkCount_ = 0;
        this.hasNetwork_ = true;
        this.linkCache_ = new HashMap();
        this.instrumentationExtraData_ = new ConcurrentHashMap<>();
        this.isGAParamsFetchInProgress_ = this.systemObserver_.prefetchGAdsParams(this);
        InstallListener.setListener(this);
        if (VERSION.SDK_INT >= 15) {
            this.handleDelayedNewIntents_ = true;
            this.intentState_ = INTENT_STATE.PENDING;
        } else {
            this.handleDelayedNewIntents_ = false;
            this.intentState_ = INTENT_STATE.READY;
        }
        this.externalUriWhiteList_ = new ArrayList();
        this.skipExternalUriHosts_ = new ArrayList();
    }

    @TargetApi(14)
    public static Branch getInstance() {
        if (branchReferral_ == null) {
            Log.e("BranchSDK", "Branch instance is not created yet. Make sure you have initialised Branch. [Consider Calling getInstance(Context ctx) if you still have issue.]");
        } else if (isAutoSessionMode_ && !isActivityLifeCycleCallbackRegistered_) {
            Log.e("BranchSDK", "Branch instance is not properly initialised. Make sure your Application class is extending BranchApp class. If you are not extending BranchApp class make sure you are initialising Branch in your Applications onCreate()");
        }
        return branchReferral_;
    }

    private static Branch getBranchInstance(Context context, boolean isLive) {
        boolean isNewBranchKeySet;
        if (branchReferral_ == null) {
            branchReferral_ = initInstance(context);
            String branchKey = branchReferral_.prefHelper_.readBranchKey(isLive);
            if (branchKey == null || branchKey.equalsIgnoreCase("bnc_no_value")) {
                String fabricBranchApiKey = null;
                try {
                    Resources resources = context.getResources();
                    fabricBranchApiKey = resources.getString(resources.getIdentifier("io.branch.apiKey", "string", context.getPackageName()));
                } catch (Exception e) {
                }
                if (!TextUtils.isEmpty(fabricBranchApiKey)) {
                    isNewBranchKeySet = branchReferral_.prefHelper_.setBranchKey(fabricBranchApiKey);
                } else {
                    Log.i("BranchSDK", "Branch Warning: Please enter your branch_key in your project's Manifest file!");
                    isNewBranchKeySet = branchReferral_.prefHelper_.setBranchKey("bnc_no_value");
                }
            } else {
                isNewBranchKeySet = branchReferral_.prefHelper_.setBranchKey(branchKey);
            }
            if (isNewBranchKeySet) {
                branchReferral_.linkCache_.clear();
                branchReferral_.requestQueue_.clear();
            }
            branchReferral_.context_ = context.getApplicationContext();
            if (context instanceof Application) {
                isAutoSessionMode_ = true;
                branchReferral_.setActivityLifeCycleObserver((Application) context);
            }
        }
        return branchReferral_;
    }

    @TargetApi(14)
    public static Branch getAutoInstance(Context context) {
        boolean isLive = true;
        isAutoSessionMode_ = true;
        customReferrableSettings_ = CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT;
        if (BranchUtil.isTestModeEnabled(context)) {
            isLive = false;
        }
        getBranchInstance(context, isLive);
        return branchReferral_;
    }

    private static Branch initInstance(Context context) {
        return new Branch(context.getApplicationContext());
    }

    public static void disableDeviceIDFetch(Boolean deviceIdFetch) {
        disableDeviceIDFetch_ = deviceIdFetch.booleanValue();
    }

    public static boolean isDeviceIDFetchDisabled() {
        return disableDeviceIDFetch_;
    }

    public boolean initSession(BranchReferralInitListener callback, Activity activity) {
        if (customReferrableSettings_ == CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT) {
            initUserSessionInternal(callback, activity, true);
        } else {
            initUserSessionInternal(callback, activity, customReferrableSettings_ == CUSTOM_REFERRABLE_SETTINGS.REFERRABLE);
        }
        return true;
    }

    public boolean initSession(BranchReferralInitListener callback, Uri data, Activity activity) {
        readAndStripParam(data, activity);
        return initSession(callback, activity);
    }

    public boolean initSessionWithData(Uri data, Activity activity) {
        readAndStripParam(data, activity);
        return initSession(null, activity);
    }

    private void initUserSessionInternal(BranchReferralInitListener callback, Activity activity, boolean isReferrable) {
        if (activity != null) {
            this.currentActivityReference_ = new WeakReference<>(activity);
        }
        if (!hasUser() || !hasSession() || this.initState_ != SESSION_STATE.INITIALISED) {
            if (isReferrable) {
                this.prefHelper_.setIsReferrable();
            } else {
                this.prefHelper_.clearIsReferrable();
            }
            if (this.initState_ != SESSION_STATE.INITIALISING) {
                this.initState_ = SESSION_STATE.INITIALISING;
                initializeSession(callback);
            } else if (callback != null) {
                this.requestQueue_.setInstallOrOpenCallback(callback);
            }
        } else if (callback == null) {
        } else {
            if (!isAutoSessionMode_) {
                callback.onInitFinished(new JSONObject(), null);
            } else if (!this.isInitReportedThroughCallBack) {
                callback.onInitFinished(getLatestReferringParams(), null);
                this.isInitReportedThroughCallBack = true;
            } else {
                callback.onInitFinished(new JSONObject(), null);
            }
        }
    }

    /* access modifiers changed from: private */
    public void closeSessionInternal() {
        executeClose();
        this.sessionReferredLink_ = null;
    }

    private void executeClose() {
        if (this.initState_ != SESSION_STATE.UNINITIALISED) {
            if (!this.hasNetwork_) {
                ServerRequest req = this.requestQueue_.peek();
                if ((req != null && (req instanceof ServerRequestRegisterInstall)) || (req instanceof ServerRequestRegisterOpen)) {
                    this.requestQueue_.dequeue();
                }
            } else if (!this.requestQueue_.containsClose()) {
                handleNewRequest(new ServerRequestRegisterClose(this.context_));
            }
            this.initState_ = SESSION_STATE.UNINITIALISED;
        }
    }

    private boolean readAndStripParam(Uri data, Activity activity) {
        String paramString;
        boolean foundSchemeMatch;
        if (this.intentState_ == INTENT_STATE.READY) {
            if (data != null) {
                boolean skipThisHost = false;
                try {
                    if (this.externalUriWhiteList_.size() > 0) {
                        foundSchemeMatch = this.externalUriWhiteList_.contains(data.getScheme());
                    } else {
                        foundSchemeMatch = true;
                    }
                    if (this.skipExternalUriHosts_.size() > 0) {
                        Iterator it = this.skipExternalUriHosts_.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            String host = (String) it.next();
                            String externalHost = data.getHost();
                            if (externalHost != null && externalHost.equals(host)) {
                                skipThisHost = true;
                                break;
                            }
                        }
                    }
                    if (foundSchemeMatch && !skipThisHost) {
                        this.sessionReferredLink_ = data.toString();
                        this.prefHelper_.setExternalIntentUri(data.toString());
                        if (!(activity == null || activity.getIntent() == null || activity.getIntent().getExtras() == null)) {
                            Bundle bundle = activity.getIntent().getExtras();
                            Set<String> extraKeys = bundle.keySet();
                            if (extraKeys.size() > 0) {
                                JSONObject extrasJson = new JSONObject();
                                String[] strArr = EXTERNAL_INTENT_EXTRA_KEY_WHITE_LIST;
                                int length = strArr.length;
                                for (int i = 0; i < length; i++) {
                                    String key = strArr[i];
                                    if (extraKeys.contains(key)) {
                                        extrasJson.put(key, bundle.get(key));
                                    }
                                }
                                if (extrasJson.length() > 0) {
                                    this.prefHelper_.setExternalIntentExtra(extrasJson.toString());
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
            if (activity != null) {
                try {
                    if (!(activity.getIntent() == null || activity.getIntent().getExtras() == null || activity.getIntent().getExtras().getBoolean(Jsonkey.BranchLinkUsed.getKey()))) {
                        String pushIdentifier = activity.getIntent().getExtras().getString(Jsonkey.AndroidPushNotificationKey.getKey());
                        if (pushIdentifier != null && pushIdentifier.length() > 0) {
                            this.prefHelper_.setPushIdentifier(pushIdentifier);
                            Intent thisIntent = activity.getIntent();
                            thisIntent.putExtra(Jsonkey.BranchLinkUsed.getKey(), true);
                            activity.setIntent(thisIntent);
                            return false;
                        }
                    }
                } catch (Exception e2) {
                }
            }
            if (!(data == null || !data.isHierarchical() || activity == null)) {
                try {
                    if (data.getQueryParameter(Jsonkey.LinkClickID.getKey()) != null) {
                        this.prefHelper_.setLinkClickIdentifier(data.getQueryParameter(Jsonkey.LinkClickID.getKey()));
                        String paramString2 = "link_click_id=" + data.getQueryParameter(Jsonkey.LinkClickID.getKey());
                        String uriString = null;
                        if (activity.getIntent() != null) {
                            uriString = activity.getIntent().getDataString();
                        }
                        if (data.getQuery().length() == paramString2.length()) {
                            paramString = "\\?" + paramString2;
                        } else if (uriString == null || uriString.length() - paramString2.length() != uriString.indexOf(paramString2)) {
                            paramString = paramString2 + "&";
                        } else {
                            paramString = "&" + paramString2;
                        }
                        if (uriString != null) {
                            activity.getIntent().setData(Uri.parse(uriString.replaceFirst(paramString, "")));
                        } else {
                            Log.w("BranchSDK", "Branch Warning. URI for the launcher activity is null. Please make sure that intent data is not set to null before calling Branch#InitSession ");
                        }
                        return true;
                    }
                    String scheme = data.getScheme();
                    Intent intent = activity.getIntent();
                    if (scheme != null && intent != null && (intent.getFlags() & 1048576) == 0 && ((scheme.equalsIgnoreCase(UriUtil.HTTP_SCHEME) || scheme.equalsIgnoreCase(UriUtil.HTTPS_SCHEME)) && data.getHost() != null && data.getHost().length() > 0 && !intent.getBooleanExtra(Jsonkey.BranchLinkUsed.getKey(), false))) {
                        this.prefHelper_.setAppLink(data.toString());
                        intent.putExtra(Jsonkey.BranchLinkUsed.getKey(), true);
                        activity.setIntent(intent);
                        return false;
                    }
                } catch (Exception e3) {
                }
            }
        }
        return false;
    }

    public void onGAdsFetchFinished() {
        this.isGAParamsFetchInProgress_ = false;
        this.requestQueue_.unlockProcessWait(PROCESS_WAIT_LOCK.GAID_FETCH_WAIT_LOCK);
        if (this.performCookieBasedStrongMatchingOnGAIDAvailable) {
            performCookieBasedStrongMatch();
            this.performCookieBasedStrongMatchingOnGAIDAvailable = false;
            return;
        }
        processNextQueueItem();
    }

    public void onInstallReferrerEventsFinished() {
        this.requestQueue_.unlockProcessWait(PROCESS_WAIT_LOCK.INSTALL_REFERRER_FETCH_WAIT_LOCK);
        processNextQueueItem();
    }

    public void userCompletedAction(String action, JSONObject metadata) {
        userCompletedAction(action, metadata, null);
    }

    public void userCompletedAction(String action, JSONObject metadata, IBranchViewEvents callback) {
        if (metadata != null) {
            metadata = BranchUtil.filterOutBadCharacters(metadata);
        }
        ServerRequest req = new ServerRequestActionCompleted(this.context_, action, metadata, callback);
        if (!req.constructError_ && !req.handleErrors(this.context_)) {
            handleNewRequest(req);
        }
    }

    public JSONObject getFirstReferringParams() {
        return appendDebugParams(convertParamsStringToDictionary(this.prefHelper_.getInstallParams()));
    }

    public JSONObject getLatestReferringParams() {
        return appendDebugParams(convertParamsStringToDictionary(this.prefHelper_.getSessionParams()));
    }

    private JSONObject appendDebugParams(JSONObject originalParams) {
        if (originalParams != null) {
            try {
                if (this.deeplinkDebugParams_ != null) {
                    if (this.deeplinkDebugParams_.length() > 0) {
                        Log.w("BranchSDK", "You're currently in deep link debug mode. Please comment out 'setDeepLinkDebugMode' to receive the deep link parameters from a real Branch link");
                    }
                    Iterator<String> keys = this.deeplinkDebugParams_.keys();
                    while (keys.hasNext()) {
                        String key = (String) keys.next();
                        originalParams.put(key, this.deeplinkDebugParams_.get(key));
                    }
                }
            } catch (Exception e) {
            }
        }
        return originalParams;
    }

    public JSONObject getDeeplinkDebugParams() {
        if (this.deeplinkDebugParams_ != null && this.deeplinkDebugParams_.length() > 0) {
            Log.w("BranchSDK", "You're currently in deep link debug mode. Please comment out 'setDeepLinkDebugMode' to receive the deep link parameters from a real Branch link");
        }
        return this.deeplinkDebugParams_;
    }

    /* access modifiers changed from: 0000 */
    public String generateShortLinkInternal(ServerRequestCreateUrl req) {
        if (!req.constructError_ && !req.handleErrors(this.context_)) {
            if (this.linkCache_.containsKey(req.getLinkPost())) {
                String url = (String) this.linkCache_.get(req.getLinkPost());
                req.onUrlAvailable(url);
                return url;
            } else if (!req.isAsync()) {
                return generateShortLinkSync(req);
            } else {
                generateShortLinkAsync(req);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void shareLink(ShareLinkBuilder builder) {
        if (this.shareLinkManager_ != null) {
            this.shareLinkManager_.cancelShareLinkDialog(true);
        }
        this.shareLinkManager_ = new ShareLinkManager();
        this.shareLinkManager_.shareLink(builder);
    }

    private String generateShortLinkSync(ServerRequestCreateUrl req) {
        String url = null;
        if (this.initState_ == SESSION_STATE.INITIALISED) {
            ServerResponse response = null;
            try {
                int timeOut = this.prefHelper_.getTimeout() + OfficialIdPhotoSelectionFragment.MAX_IMAGE_SIZE;
                response = (ServerResponse) new getShortLinkTask().execute(new ServerRequest[]{req}).get((long) timeOut, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
            }
            url = null;
            if (req.isDefaultToLongUrl()) {
                url = req.getLongUrl();
            }
            if (response != null && response.getStatusCode() == 200) {
                try {
                    url = response.getObject().getString("url");
                    if (req.getLinkPost() != null) {
                        this.linkCache_.put(req.getLinkPost(), url);
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        } else {
            Log.i("BranchSDK", "Branch Warning: User session has not been initialized");
        }
        return url;
    }

    private void generateShortLinkAsync(ServerRequest req) {
        handleNewRequest(req);
    }

    private JSONObject convertParamsStringToDictionary(String paramString) {
        if (paramString.equals("bnc_no_value")) {
            return new JSONObject();
        }
        try {
            return new JSONObject(paramString);
        } catch (JSONException e) {
            try {
                return new JSONObject(new String(Base64.decode(paramString.getBytes(), 2)));
            } catch (JSONException ex) {
                ex.printStackTrace();
                return new JSONObject();
            }
        }
    }

    /* access modifiers changed from: private */
    public void processNextQueueItem() {
        try {
            this.serverSema_.acquire();
            if (this.networkCount_ != 0 || this.requestQueue_.getSize() <= 0) {
                this.serverSema_.release();
                return;
            }
            this.networkCount_ = 1;
            ServerRequest req = this.requestQueue_.peek();
            this.serverSema_.release();
            if (req == null) {
                this.requestQueue_.remove(null);
            } else if (req.isWaitingOnProcessToFinish()) {
                this.networkCount_ = 0;
            } else if (!(req instanceof ServerRequestRegisterInstall) && !hasUser()) {
                Log.i("BranchSDK", "Branch Error: User session has not been initialized!");
                this.networkCount_ = 0;
                handleFailure(this.requestQueue_.getSize() - 1, -101);
            } else if ((req instanceof ServerRequestInitSession) || (hasSession() && hasDeviceFingerPrint())) {
                new BranchPostTask(req).executeTask(new Void[0]);
            } else {
                this.networkCount_ = 0;
                handleFailure(this.requestQueue_.getSize() - 1, -101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void handleFailure(int index, int statusCode) {
        ServerRequest req;
        if (index >= this.requestQueue_.getSize()) {
            req = this.requestQueue_.peekAt(this.requestQueue_.getSize() - 1);
        } else {
            req = this.requestQueue_.peekAt(index);
        }
        handleFailure(req, statusCode);
    }

    private void handleFailure(ServerRequest req, int statusCode) {
        if (req != null) {
            req.handleFailure(statusCode, "");
        }
    }

    /* access modifiers changed from: private */
    public void updateAllRequestsInQueue() {
        int i = 0;
        while (i < this.requestQueue_.getSize()) {
            try {
                ServerRequest req = this.requestQueue_.peekAt(i);
                if (req != null) {
                    JSONObject reqJson = req.getPost();
                    if (reqJson != null) {
                        if (reqJson.has(Jsonkey.SessionID.getKey())) {
                            req.getPost().put(Jsonkey.SessionID.getKey(), this.prefHelper_.getSessionID());
                        }
                        if (reqJson.has(Jsonkey.IdentityID.getKey())) {
                            req.getPost().put(Jsonkey.IdentityID.getKey(), this.prefHelper_.getIdentityID());
                        }
                        if (reqJson.has(Jsonkey.DeviceFingerprintID.getKey())) {
                            req.getPost().put(Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
                        }
                    }
                }
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private boolean hasSession() {
        return !this.prefHelper_.getSessionID().equals("bnc_no_value");
    }

    private boolean hasDeviceFingerPrint() {
        return !this.prefHelper_.getDeviceFingerPrintID().equals("bnc_no_value");
    }

    private boolean hasUser() {
        return !this.prefHelper_.getIdentityID().equals("bnc_no_value");
    }

    private void insertRequestAtFront(ServerRequest req) {
        if (this.networkCount_ == 0) {
            this.requestQueue_.insert(req, 0);
        } else {
            this.requestQueue_.insert(req, 1);
        }
    }

    private void registerInstallOrOpen(ServerRequest req, BranchReferralInitListener callback) {
        if (!this.requestQueue_.containsInstallOrOpen()) {
            insertRequestAtFront(req);
        } else {
            if (callback != null) {
                this.requestQueue_.setInstallOrOpenCallback(callback);
            }
            this.requestQueue_.moveInstallOrOpenToFront(req, this.networkCount_, callback);
        }
        processNextQueueItem();
    }

    private void initializeSession(BranchReferralInitListener callback) {
        if (this.prefHelper_.getBranchKey() == null || this.prefHelper_.getBranchKey().equalsIgnoreCase("bnc_no_value")) {
            this.initState_ = SESSION_STATE.UNINITIALISED;
            if (callback != null) {
                callback.onInitFinished(null, new BranchError("Trouble initializing Branch.", -114));
            }
            Log.i("BranchSDK", "Branch Warning: Please enter your branch_key in your project's res/values/strings.xml!");
            return;
        }
        if (this.prefHelper_.getBranchKey() != null && this.prefHelper_.getBranchKey().startsWith("key_test_")) {
            Log.i("BranchSDK", "Branch Warning: You are using your test app's Branch Key. Remember to change it to live Branch Key during deployment.");
        }
        if (!this.prefHelper_.getExternalIntentUri().equals("bnc_no_value") || !this.enableFacebookAppLinkCheck_) {
            registerAppInit(callback, null);
        } else if (DeferredAppLinkDataHandler.fetchDeferredAppLinkData(this.context_, new AppLinkFetchEvents() {
            public void onAppLinkFetchFinished(String nativeAppLinkUrl) {
                Branch.this.prefHelper_.setIsAppLinkTriggeredInit(Boolean.valueOf(true));
                if (nativeAppLinkUrl != null) {
                    String bncLinkClickId = Uri.parse(nativeAppLinkUrl).getQueryParameter(Jsonkey.LinkClickID.getKey());
                    if (!TextUtils.isEmpty(bncLinkClickId)) {
                        Branch.this.prefHelper_.setLinkClickIdentifier(bncLinkClickId);
                    }
                }
                Branch.this.requestQueue_.unlockProcessWait(PROCESS_WAIT_LOCK.FB_APP_LINK_WAIT_LOCK);
                Branch.this.processNextQueueItem();
            }
        }).booleanValue()) {
            registerAppInit(callback, PROCESS_WAIT_LOCK.FB_APP_LINK_WAIT_LOCK);
        } else {
            registerAppInit(callback, null);
        }
    }

    private void registerAppInit(BranchReferralInitListener callback, PROCESS_WAIT_LOCK lock2) {
        ServerRequest request;
        if (hasUser()) {
            request = new ServerRequestRegisterOpen(this.context_, callback, this.systemObserver_);
        } else {
            request = new ServerRequestRegisterInstall(this.context_, callback, this.systemObserver_, InstallListener.getInstallationID());
        }
        request.addProcessWaitLock(lock2);
        if (this.isGAParamsFetchInProgress_) {
            request.addProcessWaitLock(PROCESS_WAIT_LOCK.GAID_FETCH_WAIT_LOCK);
        }
        if (this.intentState_ != INTENT_STATE.READY) {
            request.addProcessWaitLock(PROCESS_WAIT_LOCK.INTENT_PENDING_WAIT_LOCK);
        }
        if (checkInstallReferrer_ && (request instanceof ServerRequestRegisterInstall)) {
            request.addProcessWaitLock(PROCESS_WAIT_LOCK.INSTALL_REFERRER_FETCH_WAIT_LOCK);
            InstallListener.captureInstallReferrer(playStoreReferrerFetchTime);
        }
        registerInstallOrOpen(request, callback);
    }

    /* access modifiers changed from: private */
    public void onIntentReady(Activity activity, boolean grabIntentParams) {
        this.requestQueue_.unlockProcessWait(PROCESS_WAIT_LOCK.INTENT_PENDING_WAIT_LOCK);
        if (grabIntentParams) {
            readAndStripParam(activity.getIntent().getData(), activity);
            if (cookieBasedMatchDomain_ == null || this.prefHelper_.getBranchKey() == null || this.prefHelper_.getBranchKey().equalsIgnoreCase("bnc_no_value")) {
                processNextQueueItem();
            } else if (this.isGAParamsFetchInProgress_) {
                this.performCookieBasedStrongMatchingOnGAIDAvailable = true;
            } else {
                performCookieBasedStrongMatch();
            }
        } else {
            processNextQueueItem();
        }
    }

    private void performCookieBasedStrongMatch() {
        DeviceInfo deviceInfo = DeviceInfo.getInstance(this.prefHelper_.getExternDebug() || isSimulatingInstalls(), this.systemObserver_, disableDeviceIDFetch_);
        Activity currentActivity = null;
        if (this.currentActivityReference_ != null) {
            currentActivity = (Activity) this.currentActivityReference_.get();
        }
        Context context = currentActivity != null ? currentActivity.getApplicationContext() : null;
        if (context != null) {
            this.requestQueue_.setStrongMatchWaitLock();
            BranchStrongMatchHelper.getInstance().checkForStrongMatch(context, cookieBasedMatchDomain_, deviceInfo, this.prefHelper_, this.systemObserver_, new StrongMatchCheckEvents() {
                public void onStrongMatchCheckFinished() {
                    Branch.this.requestQueue_.unlockProcessWait(PROCESS_WAIT_LOCK.STRONG_MATCH_PENDING_WAIT_LOCK);
                    Branch.this.processNextQueueItem();
                }
            });
        }
    }

    public void handleNewRequest(ServerRequest req) {
        boolean isReferrable = true;
        if (this.initState_ != SESSION_STATE.INITIALISED && !(req instanceof ServerRequestInitSession)) {
            if (req instanceof ServerRequestLogout) {
                req.handleFailure(-101, "");
                Log.i("BranchSDK", "Branch is not initialized, cannot logout");
                return;
            } else if (req instanceof ServerRequestRegisterClose) {
                Log.i("BranchSDK", "Branch is not initialized, cannot close session");
                return;
            } else {
                Activity currentActivity = null;
                if (this.currentActivityReference_ != null) {
                    currentActivity = (Activity) this.currentActivityReference_.get();
                }
                if (customReferrableSettings_ == CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT) {
                    initUserSessionInternal(null, currentActivity, true);
                } else {
                    if (customReferrableSettings_ != CUSTOM_REFERRABLE_SETTINGS.REFERRABLE) {
                        isReferrable = false;
                    }
                    initUserSessionInternal(null, currentActivity, isReferrable);
                }
            }
        }
        this.requestQueue_.enqueue(req);
        req.onRequestQueued();
        processNextQueueItem();
    }

    @TargetApi(14)
    private void setActivityLifeCycleObserver(Application application) {
        try {
            BranchActivityLifeCycleObserver activityLifeCycleObserver = new BranchActivityLifeCycleObserver();
            application.unregisterActivityLifecycleCallbacks(activityLifeCycleObserver);
            application.registerActivityLifecycleCallbacks(activityLifeCycleObserver);
            isActivityLifeCycleCallbackRegistered_ = true;
        } catch (NoClassDefFoundError | NoSuchMethodError e) {
            isActivityLifeCycleCallbackRegistered_ = false;
            isAutoSessionMode_ = false;
            Log.w("BranchSDK", new BranchError("", -108).getMessage());
        }
    }

    /* access modifiers changed from: private */
    public void startSession(Activity activity) {
        Uri intentData = null;
        if (activity.getIntent() != null) {
            intentData = activity.getIntent().getData();
        }
        initSessionWithData(intentData, activity);
    }

    /* access modifiers changed from: private */
    public boolean checkIntentForSessionRestart(Intent intent) {
        boolean isRestartSessionRequested = false;
        if (intent != null) {
            try {
                isRestartSessionRequested = intent.getBooleanExtra(Jsonkey.ForceNewBranchSession.getKey(), false);
            } catch (BadParcelableException e) {
            }
            if (isRestartSessionRequested) {
                intent.putExtra(Jsonkey.ForceNewBranchSession.getKey(), false);
            }
        }
        return isRestartSessionRequested;
    }

    /* access modifiers changed from: private */
    public void checkForAutoDeepLinkConfiguration() {
        ActivityInfo activityInfo;
        JSONObject latestParams = getLatestReferringParams();
        String deepLinkActivity = null;
        try {
            if (latestParams.has(Jsonkey.Clicked_Branch_Link.getKey()) && latestParams.getBoolean(Jsonkey.Clicked_Branch_Link.getKey()) && latestParams.length() > 0) {
                ApplicationInfo appInfo = this.context_.getPackageManager().getApplicationInfo(this.context_.getPackageName(), 128);
                if (appInfo.metaData == null || !appInfo.metaData.getBoolean("io.branch.sdk.auto_link_disable", false)) {
                    ActivityInfo[] activityInfos = this.context_.getPackageManager().getPackageInfo(this.context_.getPackageName(), 129).activities;
                    int deepLinkActivityReqCode = 1501;
                    if (activityInfos != null) {
                        int length = activityInfos.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                break;
                            }
                            activityInfo = activityInfos[i];
                            if (activityInfo == null || activityInfo.metaData == null || ((activityInfo.metaData.getString("io.branch.sdk.auto_link_keys") == null && activityInfo.metaData.getString("io.branch.sdk.auto_link_path") == null) || (!checkForAutoDeepLinkKeys(latestParams, activityInfo) && !checkForAutoDeepLinkPath(latestParams, activityInfo)))) {
                                i++;
                            }
                        }
                        deepLinkActivity = activityInfo.name;
                        deepLinkActivityReqCode = activityInfo.metaData.getInt("io.branch.sdk.auto_link_request_code", 1501);
                    }
                    if (deepLinkActivity != null && this.currentActivityReference_ != null) {
                        Activity currentActivity = (Activity) this.currentActivityReference_.get();
                        if (currentActivity != null) {
                            Intent intent = new Intent(currentActivity, Class.forName(deepLinkActivity));
                            intent.putExtra("io.branch.sdk.auto_linked", "true");
                            intent.putExtra(Jsonkey.ReferringData.getKey(), latestParams.toString());
                            Iterator<?> keys = latestParams.keys();
                            while (keys.hasNext()) {
                                String key = (String) keys.next();
                                intent.putExtra(key, latestParams.getString(key));
                            }
                            currentActivity.startActivityForResult(intent, deepLinkActivityReqCode);
                            return;
                        }
                        Log.w("BranchSDK", "No activity reference to launch deep linked activity");
                    }
                }
            }
        } catch (NameNotFoundException e) {
            Log.i("BranchSDK", "Branch Warning: Please make sure Activity names set for auto deep link are correct!");
        } catch (ClassNotFoundException e2) {
            Log.i("BranchSDK", "Branch Warning: Please make sure Activity names set for auto deep link are correct! Error while looking for activity " + deepLinkActivity);
        } catch (Exception e3) {
        }
    }

    private boolean checkForAutoDeepLinkKeys(JSONObject params, ActivityInfo activityInfo) {
        if (activityInfo.metaData.getString("io.branch.sdk.auto_link_keys") == null) {
            return false;
        }
        for (String activityLinkKey : activityInfo.metaData.getString("io.branch.sdk.auto_link_keys").split(",")) {
            if (params.has(activityLinkKey)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkForAutoDeepLinkPath(JSONObject params, ActivityInfo activityInfo) {
        String deepLinkPath = null;
        try {
            if (params.has(Jsonkey.AndroidDeepLinkPath.getKey())) {
                deepLinkPath = params.getString(Jsonkey.AndroidDeepLinkPath.getKey());
            } else if (params.has(Jsonkey.DeepLinkPath.getKey())) {
                deepLinkPath = params.getString(Jsonkey.DeepLinkPath.getKey());
            }
        } catch (JSONException e) {
        }
        if (activityInfo.metaData.getString("io.branch.sdk.auto_link_path") == null || deepLinkPath == null) {
            return false;
        }
        for (String activityLinkPath : activityInfo.metaData.getString("io.branch.sdk.auto_link_path").split(",")) {
            if (pathMatch(activityLinkPath.trim(), deepLinkPath)) {
                return true;
            }
        }
        return false;
    }

    private boolean pathMatch(String templatePath, String path) {
        boolean matched = true;
        String[] pathSegmentsTemplate = templatePath.split("\\?")[0].split("/");
        String[] pathSegmentsTarget = path.split("\\?")[0].split("/");
        if (pathSegmentsTemplate.length != pathSegmentsTarget.length) {
            return false;
        }
        int i = 0;
        while (true) {
            if (i >= pathSegmentsTemplate.length || i >= pathSegmentsTarget.length) {
                break;
            }
            String pathSegmentTemplate = pathSegmentsTemplate[i];
            if (!pathSegmentTemplate.equals(pathSegmentsTarget[i]) && !pathSegmentTemplate.contains("*")) {
                matched = false;
                break;
            }
            i++;
        }
        return matched;
    }

    public static boolean isSimulatingInstalls() {
        return isSimulatingInstalls_;
    }

    public static boolean getIsLogging() {
        return isLogging_;
    }

    public void registerView(BranchUniversalObject branchUniversalObject, RegisterViewStatusListener callback) {
        if (this.context_ != null) {
            ServerRequest req = new ServerRequestRegisterView(this.context_, branchUniversalObject, this.systemObserver_, callback);
            if (!req.constructError_ && !req.handleErrors(this.context_)) {
                handleNewRequest(req);
            }
        }
    }

    public void addExtraInstrumentationData(String key, String value) {
        this.instrumentationExtraData_.put(key, value);
    }

    public void onBranchViewVisible(String action, String branchViewID) {
    }

    public void onBranchViewAccepted(String action, String branchViewID) {
        if (ServerRequestInitSession.isInitSessionAction(action)) {
            checkForAutoDeepLinkConfiguration();
        }
    }

    public void onBranchViewCancelled(String action, String branchViewID) {
        if (ServerRequestInitSession.isInitSessionAction(action)) {
            checkForAutoDeepLinkConfiguration();
        }
    }

    public void onBranchViewError(int errorCode, String errorMsg, String action) {
        if (ServerRequestInitSession.isInitSessionAction(action)) {
            checkForAutoDeepLinkConfiguration();
        }
    }
}
