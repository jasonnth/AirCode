package p315io.branch.referral;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.airbnb.android.utils.AirbnbConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p315io.branch.referral.Branch.BranchLinkCreateListener;
import p315io.branch.referral.Branch.BranchLinkShareListener;
import p315io.branch.referral.Branch.IChannelProperties;
import p315io.branch.referral.Branch.ShareLinkBuilder;
import p315io.branch.referral.SharingHelper.SHARE_WITH;

/* renamed from: io.branch.referral.ShareLinkManager */
class ShareLinkManager {
    /* access modifiers changed from: private */
    public static int viewItemMinHeight_ = 100;
    /* access modifiers changed from: private */
    public final int BG_COLOR_DISABLED = Color.argb(20, 17, 4, 56);
    /* access modifiers changed from: private */
    public final int BG_COLOR_ENABLED = Color.argb(60, 17, 4, 56);
    /* access modifiers changed from: private */
    public List<ResolveInfo> appList_;
    /* access modifiers changed from: private */
    public ShareLinkBuilder builder_;
    BranchLinkShareListener callback_;
    IChannelProperties channelPropertiesCallback_;
    Context context_;
    private List<String> excludeFromShareSheet = new ArrayList();
    private List<String> includeInShareSheet = new ArrayList();
    /* access modifiers changed from: private */
    public boolean isShareInProgress_ = false;
    final int leftMargin = 100;
    final int padding = 5;
    private int shareDialogThemeID_ = -1;
    AnimatedDialog shareDlg_;
    private Intent shareLinkIntent_;

    /* renamed from: io.branch.referral.ShareLinkManager$ChooserArrayAdapter */
    private class ChooserArrayAdapter extends BaseAdapter {
        public int selectedPos;

        private ChooserArrayAdapter() {
            this.selectedPos = -1;
        }

        public int getCount() {
            return ShareLinkManager.this.appList_.size();
        }

        public Object getItem(int position) {
            return ShareLinkManager.this.appList_.get(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ShareItemView itemView;
            boolean setSelected;
            if (convertView == null) {
                itemView = new ShareItemView(ShareLinkManager.this.context_);
            } else {
                itemView = (ShareItemView) convertView;
            }
            ResolveInfo resolveInfo = (ResolveInfo) ShareLinkManager.this.appList_.get(position);
            if (position == this.selectedPos) {
                setSelected = true;
            } else {
                setSelected = false;
            }
            itemView.setLabel(resolveInfo.loadLabel(ShareLinkManager.this.context_.getPackageManager()).toString(), resolveInfo.loadIcon(ShareLinkManager.this.context_.getPackageManager()), setSelected);
            itemView.setTag(resolveInfo);
            itemView.setClickable(false);
            return itemView;
        }

        public boolean isEnabled(int position) {
            return this.selectedPos < 0;
        }
    }

    /* renamed from: io.branch.referral.ShareLinkManager$CopyLinkItem */
    private class CopyLinkItem extends ResolveInfo {
        private CopyLinkItem() {
        }

        public CharSequence loadLabel(PackageManager pm) {
            return ShareLinkManager.this.builder_.getCopyURlText();
        }

        public Drawable loadIcon(PackageManager pm) {
            return ShareLinkManager.this.builder_.getCopyUrlIcon();
        }
    }

    /* renamed from: io.branch.referral.ShareLinkManager$MoreShareItem */
    private class MoreShareItem extends ResolveInfo {
        private MoreShareItem() {
        }

        public CharSequence loadLabel(PackageManager pm) {
            return ShareLinkManager.this.builder_.getMoreOptionText();
        }

        public Drawable loadIcon(PackageManager pm) {
            return ShareLinkManager.this.builder_.getMoreOptionIcon();
        }
    }

    /* renamed from: io.branch.referral.ShareLinkManager$ShareItemView */
    private class ShareItemView extends TextView {
        Context context_;

        public ShareItemView(Context context) {
            super(context);
            this.context_ = context;
            setPadding(100, 5, 5, 5);
            setGravity(8388627);
            setMinWidth(this.context_.getResources().getDisplayMetrics().widthPixels);
        }

        public void setLabel(String appName, Drawable appIcon, boolean isEnabled) {
            setText("\t" + appName);
            setTag(appName);
            if (appIcon == null) {
                setTextAppearance(this.context_, 16973890);
                setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            } else {
                setTextAppearance(this.context_, 16973892);
                setCompoundDrawablesWithIntrinsicBounds(appIcon, null, null, null);
                ShareLinkManager.viewItemMinHeight_ = Math.max(ShareLinkManager.viewItemMinHeight_, appIcon.getIntrinsicHeight() + 5);
            }
            setMinHeight(ShareLinkManager.viewItemMinHeight_);
            setTextColor(this.context_.getResources().getColor(17170444));
            if (isEnabled) {
                setBackgroundColor(ShareLinkManager.this.BG_COLOR_ENABLED);
            } else {
                setBackgroundColor(ShareLinkManager.this.BG_COLOR_DISABLED);
            }
        }
    }

    ShareLinkManager() {
    }

    public Dialog shareLink(ShareLinkBuilder builder) {
        this.builder_ = builder;
        this.context_ = builder.getActivity();
        this.callback_ = builder.getCallback();
        this.channelPropertiesCallback_ = builder.getChannelPropertiesCallback();
        this.shareLinkIntent_ = new Intent("android.intent.action.SEND");
        this.shareLinkIntent_.setType("text/plain");
        this.shareDialogThemeID_ = builder.getStyleResourceID();
        this.includeInShareSheet = builder.getIncludedInShareSheet();
        this.excludeFromShareSheet = builder.getExcludedFromShareSheet();
        try {
            createShareDialog(builder.getPreferredOptions());
        } catch (Exception e) {
            e.printStackTrace();
            if (this.callback_ != null) {
                this.callback_.onLinkShareResponse(null, null, new BranchError("Trouble sharing link", AirbnbConstants.SUPERHERO_COUPON_ID));
            } else {
                Log.i("BranchSDK", "Unable create share options. Couldn't find applications on device to share the link.");
            }
        }
        return this.shareDlg_;
    }

    public void cancelShareLinkDialog(boolean animateClose) {
        if (this.shareDlg_ != null && this.shareDlg_.isShowing()) {
            if (animateClose) {
                this.shareDlg_.cancel();
            } else {
                this.shareDlg_.dismiss();
            }
        }
    }

    private void createShareDialog(List<SHARE_WITH> preferredOptions) {
        ListView shareOptionListView;
        PackageManager packageManager = this.context_.getPackageManager();
        List<ResolveInfo> preferredApps = new ArrayList<>();
        List<ResolveInfo> matchingApps = packageManager.queryIntentActivities(this.shareLinkIntent_, 65536);
        List<ResolveInfo> cleanedMatchingApps = new ArrayList<>();
        final List<ResolveInfo> cleanedMatchingAppsFinal = new ArrayList<>();
        ArrayList<SHARE_WITH> packagesFilterList = new ArrayList<>(preferredOptions);
        for (ResolveInfo resolveInfo : matchingApps) {
            SHARE_WITH foundMatching = null;
            String packageName = resolveInfo.activityInfo.packageName;
            Iterator it = packagesFilterList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                SHARE_WITH PackageFilter = (SHARE_WITH) it.next();
                if (resolveInfo.activityInfo != null && packageName.toLowerCase().contains(PackageFilter.toString().toLowerCase())) {
                    foundMatching = PackageFilter;
                    break;
                }
            }
            if (foundMatching != null) {
                preferredApps.add(resolveInfo);
                preferredOptions.remove(foundMatching);
            }
        }
        matchingApps.removeAll(preferredApps);
        matchingApps.addAll(0, preferredApps);
        if (this.includeInShareSheet.size() > 0) {
            for (ResolveInfo r : matchingApps) {
                if (this.includeInShareSheet.contains(r.activityInfo.packageName)) {
                    cleanedMatchingApps.add(r);
                }
            }
        } else {
            cleanedMatchingApps = matchingApps;
        }
        for (ResolveInfo r2 : cleanedMatchingApps) {
            if (!this.excludeFromShareSheet.contains(r2.activityInfo.packageName)) {
                cleanedMatchingAppsFinal.add(r2);
            }
        }
        for (ResolveInfo r3 : matchingApps) {
            Iterator it2 = packagesFilterList.iterator();
            while (it2.hasNext()) {
                if (((SHARE_WITH) it2.next()).toString().equalsIgnoreCase(r3.activityInfo.packageName)) {
                    cleanedMatchingAppsFinal.add(r3);
                }
            }
        }
        CopyLinkItem copyLinkItem = new CopyLinkItem();
        cleanedMatchingAppsFinal.add(copyLinkItem);
        CopyLinkItem copyLinkItem2 = new CopyLinkItem();
        matchingApps.add(copyLinkItem2);
        CopyLinkItem copyLinkItem3 = new CopyLinkItem();
        preferredApps.add(copyLinkItem3);
        if (preferredApps.size() > 1) {
            if (matchingApps.size() > preferredApps.size()) {
                MoreShareItem moreShareItem = new MoreShareItem();
                preferredApps.add(moreShareItem);
            }
            this.appList_ = preferredApps;
        } else {
            this.appList_ = cleanedMatchingAppsFinal;
        }
        final ChooserArrayAdapter adapter = new ChooserArrayAdapter();
        if (this.shareDialogThemeID_ <= 1 || VERSION.SDK_INT < 21) {
            shareOptionListView = new ListView(this.context_);
        } else {
            shareOptionListView = new ListView(this.context_, null, 0, this.shareDialogThemeID_);
        }
        shareOptionListView.setHorizontalFadingEdgeEnabled(false);
        shareOptionListView.setBackgroundColor(-1);
        if (this.builder_.getSharingTitleView() != null) {
            shareOptionListView.addHeaderView(this.builder_.getSharingTitleView(), null, false);
        } else if (!TextUtils.isEmpty(this.builder_.getSharingTitle())) {
            TextView textView = new TextView(this.context_);
            textView.setText(this.builder_.getSharingTitle());
            textView.setBackgroundColor(this.BG_COLOR_DISABLED);
            textView.setTextColor(this.BG_COLOR_DISABLED);
            textView.setTextAppearance(this.context_, 16973892);
            textView.setTextColor(this.context_.getResources().getColor(17170432));
            textView.setPadding(100, 5, 5, 5);
            shareOptionListView.addHeaderView(textView, null, false);
        }
        shareOptionListView.setAdapter(adapter);
        if (this.builder_.getDividerHeight() >= 0) {
            shareOptionListView.setDividerHeight(this.builder_.getDividerHeight());
        } else if (this.builder_.getIsFullWidthStyle()) {
            shareOptionListView.setDividerHeight(0);
        }
        C48691 r0 = new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                if (view.getTag() instanceof MoreShareItem) {
                    ShareLinkManager.this.appList_ = cleanedMatchingAppsFinal;
                    adapter.notifyDataSetChanged();
                    return;
                }
                if (ShareLinkManager.this.callback_ != null) {
                    String selectedChannelName = "";
                    if (!(view.getTag() == null || ShareLinkManager.this.context_ == null || ((ResolveInfo) view.getTag()).loadLabel(ShareLinkManager.this.context_.getPackageManager()) == null)) {
                        selectedChannelName = ((ResolveInfo) view.getTag()).loadLabel(ShareLinkManager.this.context_.getPackageManager()).toString();
                    }
                    ShareLinkManager.this.callback_.onChannelSelected(selectedChannelName);
                }
                adapter.selectedPos = pos;
                adapter.notifyDataSetChanged();
                ShareLinkManager.this.invokeSharingClient((ResolveInfo) view.getTag());
                if (ShareLinkManager.this.shareDlg_ != null) {
                    ShareLinkManager.this.shareDlg_.cancel();
                }
            }
        };
        shareOptionListView.setOnItemClickListener(r0);
        this.shareDlg_ = new AnimatedDialog(this.context_, this.builder_.getIsFullWidthStyle());
        this.shareDlg_.setContentView(shareOptionListView);
        this.shareDlg_.show();
        if (this.callback_ != null) {
            this.callback_.onShareLinkDialogLaunched();
        }
        AnimatedDialog animatedDialog = this.shareDlg_;
        C48702 r02 = new OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                if (ShareLinkManager.this.callback_ != null) {
                    ShareLinkManager.this.callback_.onShareLinkDialogDismissed();
                    ShareLinkManager.this.callback_ = null;
                }
                if (!ShareLinkManager.this.isShareInProgress_) {
                    ShareLinkManager.this.context_ = null;
                    ShareLinkManager.this.builder_ = null;
                }
                ShareLinkManager.this.shareDlg_ = null;
            }
        };
        animatedDialog.setOnDismissListener(r02);
    }

    /* access modifiers changed from: private */
    public void invokeSharingClient(final ResolveInfo selectedResolveInfo) {
        this.isShareInProgress_ = true;
        final String channelName = selectedResolveInfo.loadLabel(this.context_.getPackageManager()).toString();
        BranchShortLinkBuilder shortLinkBuilder = this.builder_.getShortLinkBuilder();
        shortLinkBuilder.setChannel(channelName);
        shortLinkBuilder.generateShortUrlInternal(new BranchLinkCreateListener() {
            public void onLinkCreate(String url, BranchError error) {
                if (error == null) {
                    ShareLinkManager.this.shareWithClient(selectedResolveInfo, url, channelName);
                    return;
                }
                String defaultUrl = ShareLinkManager.this.builder_.getDefaultURL();
                if (defaultUrl == null || defaultUrl.trim().length() <= 0) {
                    if (ShareLinkManager.this.callback_ != null) {
                        ShareLinkManager.this.callback_.onLinkShareResponse(url, channelName, error);
                    } else {
                        Log.i("BranchSDK", "Unable to share link " + error.getMessage());
                    }
                    if (error.getErrorCode() == -113) {
                        ShareLinkManager.this.shareWithClient(selectedResolveInfo, url, channelName);
                        return;
                    }
                    ShareLinkManager.this.cancelShareLinkDialog(false);
                    ShareLinkManager.this.isShareInProgress_ = false;
                    return;
                }
                ShareLinkManager.this.shareWithClient(selectedResolveInfo, defaultUrl, channelName);
            }
        }, true);
    }

    /* access modifiers changed from: private */
    public void shareWithClient(ResolveInfo selectedResolveInfo, String url, String channelName) {
        if (this.callback_ != null) {
            this.callback_.onLinkShareResponse(url, channelName, null);
        } else {
            Log.i("BranchSDK", "Shared link with " + channelName);
        }
        if (selectedResolveInfo instanceof CopyLinkItem) {
            addLinkToClipBoard(url, this.builder_.getShareMsg());
            return;
        }
        this.shareLinkIntent_.setPackage(selectedResolveInfo.activityInfo.packageName);
        String shareSub = this.builder_.getShareSub();
        String shareMsg = this.builder_.getShareMsg();
        if (this.channelPropertiesCallback_ != null) {
            String customShareSub = this.channelPropertiesCallback_.getSharingTitleForChannel(channelName);
            String customShareMsg = this.channelPropertiesCallback_.getSharingMessageForChannel(channelName);
            if (!TextUtils.isEmpty(customShareSub)) {
                shareSub = customShareSub;
            }
            if (!TextUtils.isEmpty(customShareMsg)) {
                shareMsg = customShareMsg;
            }
        }
        if (shareSub != null && shareSub.trim().length() > 0) {
            this.shareLinkIntent_.putExtra("android.intent.extra.SUBJECT", shareSub);
        }
        this.shareLinkIntent_.putExtra("android.intent.extra.TEXT", shareMsg + "\n" + url);
        this.context_.startActivity(this.shareLinkIntent_);
    }

    @SuppressLint({"NewApi"})
    private void addLinkToClipBoard(String url, String label) {
        if (VERSION.SDK_INT < 11) {
            ((ClipboardManager) this.context_.getSystemService("clipboard")).setText(url);
        } else {
            ((android.content.ClipboardManager) this.context_.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(label, url));
        }
        Toast.makeText(this.context_, this.builder_.getUrlCopiedMessage(), 0).show();
    }
}
