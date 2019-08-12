package com.airbnb.android.sharing.referral;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.utils.WeChatHelper;
import com.airbnb.android.sharing.C0921R;
import com.airbnb.android.sharing.SharingGraph;
import com.airbnb.android.sharing.analytics.SharingAnalytics;
import com.airbnb.android.sharing.enums.GlobalSharingServiceRankingPackageName;
import com.airbnb.android.sharing.enums.SharingService;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareLinkContent.Builder;
import com.facebook.share.widget.MessageDialog;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.IntentPickerSheetView;
import com.flipboard.bottomsheet.commons.IntentPickerSheetView.ActivityInfo;
import com.flipboard.bottomsheet.commons.IntentPickerSheetView.Filter;
import com.kakao.KakaoLink;
import com.kakao.KakaoParameterException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashSet;

public final class SharingManager {
    public static final String LISTING = "listing";
    public static final String LISTING_PHOTO = "listing_photo";
    private static final int RC_SHARING_SERVICE = 169;
    public static final String REFERRALS = "referrals";
    public static final String STORY = "story";
    public static final String WISHLIST = "wishlist";
    private String bodyWithoutSharingURL;
    private final Activity context;
    private String description;
    private final String entryPoint;
    private String lastSelectedSharingService = "";
    AirbnbPreferences preferences;
    private final String sharingURL;
    private String title;

    @Retention(RetentionPolicy.SOURCE)
    public @interface EntryPoint {
    }

    public SharingManager(Activity activity, String sharingURL2, String entryPoint2) {
        this.context = activity;
        this.sharingURL = sharingURL2;
        this.entryPoint = entryPoint2;
        this.title = "";
        this.bodyWithoutSharingURL = "";
        this.description = "";
        ((SharingGraph) CoreApplication.instance(this.context).component()).inject(this);
    }

    public SharingManager withBodyNoSharingURL(String bodyWithoutSharingURL2) {
        this.bodyWithoutSharingURL = bodyWithoutSharingURL2;
        return this;
    }

    public SharingManager withTitle(String title2) {
        this.title = title2;
        return this;
    }

    public SharingManager withDescription(String description2) {
        this.description = description2;
        return this;
    }

    public void share(BottomSheetLayout bottomSheetLayout) {
        Intent shareIntent = new Intent("android.intent.action.SEND");
        shareIntent.setType("*/*");
        shareIntent.putExtra("android.intent.extra.MIME_TYPES", new String[]{"text/plain", "image/jpg"});
        shareIntent.putExtra("android.intent.extra.SUBJECT", this.title);
        IntentPickerSheetView intentPickerSheet = new IntentPickerSheetView(this.context, shareIntent, this.context.getString(C0921R.string.sharing_sheet_title), SharingManager$$Lambda$1.lambdaFactory$(this, bottomSheetLayout, shareIntent));
        intentPickerSheet.setFilter(new Filter() {
            HashSet<String> sharingPackageNames = new HashSet<>();

            public boolean include(ActivityInfo info) {
                String packageName = info.componentName.getPackageName() + info.label;
                if (this.sharingPackageNames.contains(packageName)) {
                    return false;
                }
                this.sharingPackageNames.add(packageName);
                return true;
            }
        });
        intentPickerSheet.setSortMethod(SharingManager$$Lambda$2.lambdaFactory$(this));
        bottomSheetLayout.showWithSheetView(intentPickerSheet);
    }

    static /* synthetic */ void lambda$share$0(SharingManager sharingManager, BottomSheetLayout bottomSheetLayout, Intent shareIntent, ActivityInfo activityInfo) {
        sharingManager.lastSelectedSharingService = activityInfo.componentName.getClassName();
        SharingAnalytics.trackShareButtonClick(sharingManager.lastSelectedSharingService, sharingManager.entryPoint, null);
        bottomSheetLayout.dismissSheet();
        sharingManager.shareWithIntent(activityInfo, shareIntent);
    }

    static /* synthetic */ int lambda$share$1(SharingManager sharingManager, ActivityInfo lhs, ActivityInfo rhs) {
        int leftRanking = GlobalSharingServiceRankingPackageName.findRankingByPackageName(lhs.componentName.getPackageName(), lhs.componentName.getClassName());
        int rightRanking = GlobalSharingServiceRankingPackageName.findRankingByPackageName(rhs.componentName.getPackageName(), rhs.componentName.getClassName());
        long leftLastTimeStamp = sharingManager.preferences.getSharedPreferences().getLong(lhs.componentName.getClassName(), 0);
        long rightLastTimeStamp = sharingManager.preferences.getSharedPreferences().getLong(rhs.componentName.getClassName(), 0);
        if (leftLastTimeStamp > 0 && rightLastTimeStamp > 0) {
            return Long.valueOf(rightLastTimeStamp).compareTo(Long.valueOf(leftLastTimeStamp));
        }
        if (leftLastTimeStamp > 0) {
            return -1;
        }
        if (rightLastTimeStamp > 0) {
            return 1;
        }
        return leftRanking - rightRanking;
    }

    private void shareWithIntent(ActivityInfo activityInfo, Intent shareIntent) {
        String sharingPackageName = activityInfo.componentName.getPackageName();
        this.preferences.getSharedPreferences().edit().putLong(activityInfo.componentName.getClassName(), System.currentTimeMillis()).apply();
        SharingService service = SharingService.findServiceByPackageName(sharingPackageName);
        String sharingURLWithTracking = getSharingURLwithTrackingCode(this.sharingURL, service);
        shareIntent.setType("text/plain");
        shareIntent.putExtra("android.intent.extra.TEXT", this.bodyWithoutSharingURL + " " + sharingURLWithTracking);
        switch (service) {
            case FB_MESSENGER:
                FacebookSdk.sdkInitialize(this.context);
                ShareLinkContent linkContent = ((Builder) new Builder().setContentTitle(this.title).setContentDescription(this.description).setContentUrl(Uri.parse(sharingURLWithTracking))).setImageUrl(Uri.parse(this.context.getString(C0921R.string.airbnb_logo_for_facebook))).build();
                if (MessageDialog.canShow(ShareLinkContent.class)) {
                    MessageDialog.show(this.context, (ShareContent) linkContent);
                    return;
                }
                return;
            case KAKAOTALK:
                try {
                    shareIntent = new Intent("android.intent.action.SEND", Uri.parse(KakaoLink.getKakaoLink(this.context).createKakaoTalkLinkMessageBuilder().addText(this.description).addImage(this.context.getString(C0921R.string.airbnb_logo_for_kakao), 258, 81).addWebButton(this.context.getString(C0921R.string.invite_sharebutton_kakao), sharingURLWithTracking).build()));
                } catch (KakaoParameterException e) {
                    BugsnagWrapper.throwOrNotify(new RuntimeException(e));
                }
                this.context.startActivityForResult(activityInfo.getConcreteIntent(shareIntent), 169);
                return;
            case WECHAT:
                WeChatHelper.shareWebPageToWechat(this.context, this.title, this.description, sharingURLWithTracking, BitmapFactory.decodeResource(this.context.getResources(), C0921R.C0922drawable.airbnb_logo_white_bg), activityInfo.componentName.getClassName());
                return;
            case COPY_TO_CLIPBOARD:
                shareIntent.putExtra("android.intent.extra.TEXT", sharingURLWithTracking);
                break;
        }
        this.context.startActivityForResult(activityInfo.getConcreteIntent(shareIntent), 169);
    }

    private static String getSharingURLwithTrackingCode(String sharingURL2, SharingService service) {
        return (sharingURL2 + "?s=") + service.trackingCode;
    }

    public void onShareSent(int requestCode, int resultCode) {
        if (requestCode == 169 && !TextUtils.isEmpty(this.lastSelectedSharingService)) {
            if (resultCode == 0) {
                SharingAnalytics.trackShareCanceld(this.lastSelectedSharingService, this.entryPoint, null);
            } else {
                SharingAnalytics.trackShareSent(this.lastSelectedSharingService, this.entryPoint, null);
            }
            this.lastSelectedSharingService = "";
        }
    }
}
