package p315io.branch.indexing;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.AbsListView;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p315io.branch.referral.PrefHelper;

/* renamed from: io.branch.indexing.ContentDiscoverer */
public class ContentDiscoverer {
    private static ContentDiscoverer thisInstance_;
    /* access modifiers changed from: private */
    public ContentDiscoveryManifest cdManifest_;
    /* access modifiers changed from: private */
    public JSONObject contentEvent_;
    /* access modifiers changed from: private */
    public ArrayList<String> discoveredViewList_ = new ArrayList<>();
    /* access modifiers changed from: private */
    public int discoveryRepeatCnt_;
    /* access modifiers changed from: private */
    public Handler handler_ = new Handler();
    private final HashHelper hashHelper_ = new HashHelper();
    /* access modifiers changed from: private */
    public WeakReference<Activity> lastActivityReference_;
    /* access modifiers changed from: private */
    public int maxDiscoveryRepeatCnt = 15;
    /* access modifiers changed from: private */
    public Runnable readContentRunnable = new Runnable() {
        public void run() {
            try {
                ContentDiscoverer.this.discoveryRepeatCnt_ = ContentDiscoverer.this.discoveryRepeatCnt_ + 1;
                if (ContentDiscoverer.this.cdManifest_.isCDEnabled() && ContentDiscoverer.this.lastActivityReference_ != null && ContentDiscoverer.this.lastActivityReference_.get() != null) {
                    Activity activity = (Activity) ContentDiscoverer.this.lastActivityReference_.get();
                    ContentDiscoverer.this.contentEvent_ = new JSONObject();
                    ContentDiscoverer.this.contentEvent_.put("ts", System.currentTimeMillis());
                    if (!TextUtils.isEmpty(ContentDiscoverer.this.referredUrl_)) {
                        ContentDiscoverer.this.contentEvent_.put("rl", ContentDiscoverer.this.referredUrl_);
                    }
                    String viewName = "/" + activity.getClass().getSimpleName();
                    ContentDiscoverer.this.contentEvent_.put("v", viewName);
                    ViewGroup rootView = (ViewGroup) activity.findViewById(16908290);
                    if (rootView != null) {
                        CDPathProperties cdPathProperties = ContentDiscoverer.this.cdManifest_.getCDPathProperties(activity);
                        boolean isClearText = cdPathProperties != null && cdPathProperties.isClearTextRequested();
                        JSONArray filteredElements = null;
                        if (cdPathProperties != null) {
                            isClearText = cdPathProperties.isClearTextRequested();
                            ContentDiscoverer.this.contentEvent_.put("h", !isClearText);
                            filteredElements = cdPathProperties.getFilteredElements();
                        }
                        if (filteredElements != null && filteredElements.length() > 0) {
                            JSONArray contentKeysArray = new JSONArray();
                            ContentDiscoverer.this.contentEvent_.put("ck", contentKeysArray);
                            JSONArray contentDataArray = new JSONArray();
                            ContentDiscoverer.this.contentEvent_.put("cd", contentDataArray);
                            ContentDiscoverer.this.discoverContentData(filteredElements, contentDataArray, contentKeysArray, activity, isClearText);
                        } else if (!ContentDiscoverer.this.discoveredViewList_.contains(viewName)) {
                            JSONArray contentKeysArray2 = new JSONArray();
                            ContentDiscoverer.this.contentEvent_.put("ck", contentKeysArray2);
                            ContentDiscoverer.this.discoverContentKeys(rootView, contentKeysArray2, activity.getResources());
                        }
                        ContentDiscoverer.this.discoveredViewList_.add(viewName);
                        PrefHelper.getInstance(activity).saveBranchAnalyticsData(ContentDiscoverer.this.contentEvent_);
                        int discoveryRepeatTime = ContentDiscoverer.this.cdManifest_.getCDPathProperties(activity).getDiscoveryRepeatInterval();
                        ContentDiscoverer.this.maxDiscoveryRepeatCnt = ContentDiscoverer.this.cdManifest_.getCDPathProperties(activity).getMaxDiscoveryRepeatNumber();
                        if (ContentDiscoverer.this.discoveryRepeatCnt_ < ContentDiscoverer.this.maxDiscoveryRepeatCnt && discoveryRepeatTime >= 500 && filteredElements != null && filteredElements.length() > 0) {
                            ContentDiscoverer.this.handler_.postDelayed(ContentDiscoverer.this.readContentRunnable, (long) discoveryRepeatTime);
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    };
    /* access modifiers changed from: private */
    public Runnable readListRunnable = new Runnable() {
        public void run() {
            ContentDiscoverer.this.readContentRunnable.run();
        }
    };
    /* access modifiers changed from: private */
    public String referredUrl_;
    private OnScrollChangedListener scrollChangedListener = new OnScrollChangedListener() {
        public void onScrollChanged() {
            ContentDiscoverer.this.handler_.removeCallbacks(ContentDiscoverer.this.readListRunnable);
            if (ContentDiscoverer.this.maxDiscoveryRepeatCnt > ContentDiscoverer.this.discoveryRepeatCnt_) {
                ContentDiscoverer.this.handler_.postDelayed(ContentDiscoverer.this.readListRunnable, 1500);
            }
        }
    };
    private final Map<String, WeakReference<ViewTreeObserver>> viewTreeObserverMap = new HashMap();

    /* renamed from: io.branch.indexing.ContentDiscoverer$HashHelper */
    private class HashHelper {
        MessageDigest messageDigest_;

        HashHelper() {
            try {
                this.messageDigest_ = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
            }
        }

        /* access modifiers changed from: 0000 */
        public String hashContent(String content) {
            String hashedVal = "";
            if (this.messageDigest_ == null) {
                return hashedVal;
            }
            this.messageDigest_.reset();
            this.messageDigest_.update(content.getBytes());
            return new String(this.messageDigest_.digest());
        }
    }

    public static ContentDiscoverer getInstance() {
        if (thisInstance_ == null) {
            thisInstance_ = new ContentDiscoverer();
        }
        return thisInstance_;
    }

    private ContentDiscoverer() {
    }

    public void discoverContent(Activity activity, String referredUrl) {
        this.cdManifest_ = ContentDiscoveryManifest.getInstance(activity);
        this.referredUrl_ = referredUrl;
        CDPathProperties pathProperties = this.cdManifest_.getCDPathProperties(activity);
        if (pathProperties != null) {
            if (!pathProperties.isSkipContentDiscovery()) {
                discoverContent(activity);
            }
        } else if (!TextUtils.isEmpty(this.referredUrl_)) {
            discoverContent(activity);
        }
    }

    public void onActivityStopped(Activity activity) {
        if (!(this.lastActivityReference_ == null || this.lastActivityReference_.get() == null || !((Activity) this.lastActivityReference_.get()).getClass().getName().equals(activity.getClass().getName()))) {
            this.handler_.removeCallbacks(this.readContentRunnable);
            this.lastActivityReference_ = null;
        }
        updateLastViewTimeStampIfNeeded();
        for (WeakReference<ViewTreeObserver> viewTreeObserverRef : this.viewTreeObserverMap.values()) {
            ViewTreeObserver observer = (ViewTreeObserver) viewTreeObserverRef.get();
            if (observer != null) {
                observer.removeOnScrollChangedListener(this.scrollChangedListener);
            }
        }
        this.viewTreeObserverMap.clear();
    }

    public void onSessionStarted(Activity activity, String referredUrl) {
        this.discoveredViewList_ = new ArrayList<>();
        discoverContent(activity, referredUrl);
    }

    private void discoverContent(Activity activity) {
        this.discoveryRepeatCnt_ = 0;
        if (this.discoveredViewList_.size() < this.cdManifest_.getMaxViewHistorySize()) {
            this.handler_.removeCallbacks(this.readContentRunnable);
            this.lastActivityReference_ = new WeakReference<>(activity);
            this.handler_.postDelayed(this.readContentRunnable, 1000);
        }
    }

    private void updateLastViewTimeStampIfNeeded() {
        try {
            if (this.contentEvent_ != null) {
                this.contentEvent_.put("tc", System.currentTimeMillis());
            }
        } catch (JSONException e) {
        }
    }

    /* access modifiers changed from: private */
    public void discoverContentKeys(ViewGroup viewGroup, JSONArray contentKeysArray, Resources res) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childView = viewGroup.getChildAt(i);
            if (childView.getVisibility() == 0) {
                if ((childView instanceof AbsListView) || childView.getClass().getSimpleName().equals("RecyclerView")) {
                    discoverListViewContentKeys((ViewGroup) childView, res, contentKeysArray);
                } else if (childView instanceof ViewGroup) {
                    discoverContentKeys((ViewGroup) childView, contentKeysArray, res);
                } else if (childView instanceof TextView) {
                    contentKeysArray.put(getViewName(childView, res));
                }
            }
        }
    }

    private void discoverListViewContentKeys(ViewGroup listView, Resources res, JSONArray contentKeysArray) {
        int i = 1;
        JSONObject absListKeyObj = new JSONObject();
        if (listView != null && listView.getChildCount() > -1) {
            if (listView.getChildCount() <= 1) {
                i = 0;
            }
            View candidateItemView = listView.getChildAt(i);
            if (candidateItemView != null) {
                JSONArray itemViewArray = new JSONArray();
                try {
                    absListKeyObj.put(getViewName(listView, res), itemViewArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (candidateItemView instanceof ViewGroup) {
                    discoverContentKeys((ViewGroup) candidateItemView, itemViewArray, res);
                } else if (candidateItemView instanceof TextView) {
                    itemViewArray.put(getViewName(candidateItemView, res));
                }
                if (absListKeyObj.length() > 0) {
                    contentKeysArray.put("$" + absListKeyObj);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void discoverContentData(JSONArray viewIDArray, JSONArray contentDataArray, JSONArray contentKeysArray, Activity activity, boolean isClearText) {
        int i = 0;
        while (i < viewIDArray.length()) {
            try {
                String viewName = viewIDArray.getString(i);
                if (viewName.startsWith("$")) {
                    discoverListViewContentData(viewName, activity, isClearText, contentDataArray, contentKeysArray);
                } else {
                    updateElementData(viewName, activity.findViewById(activity.getResources().getIdentifier(viewIDArray.getString(i), "id", activity.getPackageName())), isClearText, contentDataArray, contentKeysArray);
                }
                i++;
            } catch (JSONException e) {
                return;
            }
        }
    }

    private void discoverListViewContentData(String viewKeyString, Activity activity, boolean isClearText, JSONArray contentDataArray, JSONArray contentKeysArray) {
        JSONObject listViewContentObj = new JSONObject();
        contentKeysArray.put(viewKeyString);
        contentDataArray.put(listViewContentObj);
        String viewKeyString2 = viewKeyString.replace("$", "");
        try {
            JSONObject jSONObject = new JSONObject(viewKeyString2);
            if (jSONObject.length() > 0) {
                String listViewID = (String) jSONObject.keys().next();
                int id = activity.getResources().getIdentifier(listViewID, "id", activity.getPackageName());
                View view = activity.getCurrentFocus() != null ? activity.getCurrentFocus().findViewById(id) : null;
                if (view == null) {
                    view = activity.findViewById(id);
                }
                if (view != null && (view instanceof ViewGroup)) {
                    ViewGroup listView = (ViewGroup) view;
                    JSONArray itemViewChildIdArray = jSONObject.getJSONArray(listViewID);
                    int[] itemViewIds = new int[itemViewChildIdArray.length()];
                    for (int i = 0; i < itemViewChildIdArray.length(); i++) {
                        itemViewIds[i] = activity.getResources().getIdentifier(itemViewChildIdArray.getString(i), "id", activity.getPackageName());
                    }
                    int firstVisibleItemIdx = listView instanceof AbsListView ? ((AbsListView) listView).getFirstVisiblePosition() : 0;
                    for (int j = 0; j < listView.getChildCount(); j++) {
                        if (listView.getChildAt(j) != null) {
                            JSONObject itemObj = new JSONObject();
                            listViewContentObj.put("" + (j + firstVisibleItemIdx), itemObj);
                            for (int i2 = 0; i2 < itemViewIds.length; i2++) {
                                if (listView.getChildAt(j) != null) {
                                    View itemViewChild = listView.getChildAt(j).findViewById(itemViewIds[i2]);
                                    if (itemViewChild instanceof TextView) {
                                        itemObj.put(itemViewChildIdArray.getString(i2), getTextViewValue(itemViewChild, isClearText));
                                    }
                                }
                            }
                        }
                    }
                    if ((jSONObject.has("bnc_esw") && jSONObject.getBoolean("bnc_esw")) && !this.viewTreeObserverMap.containsKey(viewKeyString2)) {
                        listView.getViewTreeObserver().addOnScrollChangedListener(this.scrollChangedListener);
                        this.viewTreeObserverMap.put(viewKeyString2, new WeakReference(listView.getViewTreeObserver()));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getViewName(View view, Resources res) {
        String viewName = String.valueOf(view.getId());
        try {
            return res.getResourceEntryName(view.getId());
        } catch (Exception e) {
            return viewName;
        }
    }

    private String getTextViewValue(View view, boolean isClearText) {
        TextView txtView = (TextView) view;
        if (txtView.getText() == null) {
            return null;
        }
        String viewVal = txtView.getText().toString().substring(0, Math.min(txtView.getText().toString().length(), this.cdManifest_.getMaxTextLen()));
        if (isClearText) {
            return viewVal;
        }
        return this.hashHelper_.hashContent(viewVal);
    }

    private void updateElementData(String viewName, View view, boolean isClearText, JSONArray contentDataArray, JSONArray contentKeysArray) {
        if (view instanceof TextView) {
            contentDataArray.put(getTextViewValue(view, isClearText));
            contentKeysArray.put(viewName);
        }
    }

    public JSONObject getContentDiscoverDataForCloseRequest(Context context) {
        JSONObject cdObj = null;
        JSONObject branchAnalyticalData = PrefHelper.getInstance(context).getBranchAnalyticsData();
        if (branchAnalyticalData.length() > 0 && branchAnalyticalData.toString().length() < this.cdManifest_.getMaxPacketSize()) {
            cdObj = new JSONObject();
            try {
                cdObj.put("mv", ContentDiscoveryManifest.getInstance(context).getManifestVersion()).put("e", branchAnalyticalData);
                if (context != null) {
                    cdObj.put("p", context.getPackageName());
                    cdObj.put("p", context.getPackageName());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        PrefHelper.getInstance(context).clearBranchAnalyticsData();
        return cdObj;
    }
}
