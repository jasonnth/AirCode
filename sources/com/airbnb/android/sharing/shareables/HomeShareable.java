package com.airbnb.android.sharing.shareables;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.utils.WeChatHelper;
import com.airbnb.android.sharing.C0921R;
import com.airbnb.android.sharing.enums.CustomShareActivities;
import com.airbnb.android.sharing.utils.ShareChannelsHelper;
import com.airbnb.p027n2.primitives.imaging.Image;
import com.airbnb.p027n2.primitives.imaging.ImageSize;
import com.facebook.internal.AnalyticsEvents;
import java.util.List;

public final class HomeShareable extends Shareable {
    private static final int DEFAULT_IMAGE_ID = -1;
    private final AirDate checkIn;
    private final AirDate checkOut;
    private final GuestDetails guestData;
    private String imagePath;
    private final Listing listing;
    private final int pictureId;

    private HomeShareable(Context context, Listing listing2, AirDate checkIn2, AirDate checkOut2, GuestDetails guestData2, int pictureId2) {
        super(context);
        this.listing = listing2;
        this.checkIn = checkIn2;
        this.checkOut = checkOut2;
        this.guestData = guestData2;
        this.pictureId = pictureId2;
    }

    public HomeShareable(Context context, Listing listing2, AirDate checkIn2, AirDate checkOut2, GuestDetails guestData2) {
        this(context, listing2, checkIn2, checkOut2, guestData2, -1);
    }

    public HomeShareable(Context context, Listing listing2, int pictureId2) {
        this(context, listing2, null, null, null, pictureId2);
    }

    public HomeShareable(Context context, Listing listing2, String screenshotPath) {
        this(context, listing2, null, null, null, -1);
        this.imagePath = screenshotPath;
    }

    public String getUrl() {
        Builder builder = Uri.parse(this.context.getString(C0921R.string.rooms_base_url, new Object[]{Long.valueOf(this.listing.getId())})).buildUpon();
        addGuestAndDateParamsToUri(builder);
        if (this.pictureId != -1) {
            builder.appendQueryParameter(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO, Integer.toString(this.pictureId));
        }
        return builder.toString();
    }

    public String getDeeplinkPath() {
        Uri uri = addGuestAndDateParamsToUri(new Builder().appendPath("d").appendPath("listing").appendPath(String.valueOf(this.listing.getId()))).build();
        return uri.getPath() + "?" + uri.getQuery();
    }

    private Builder addGuestAndDateParamsToUri(Builder builder) {
        if (!(this.checkIn == null || this.checkOut == null)) {
            builder.appendQueryParameter("check_in", this.checkIn.getIsoDateString());
            builder.appendQueryParameter("check_out", this.checkOut.getIsoDateString());
        }
        if (this.guestData != null) {
            builder.appendQueryParameter(FindTweenAnalytics.GUESTS, String.valueOf(this.guestData.totalGuestCount()));
            builder.appendQueryParameter(FindTweenAnalytics.ADULTS, String.valueOf(this.guestData.adultsCount()));
            if (this.guestData.hasChildren()) {
                builder.appendQueryParameter(FindTweenAnalytics.CHILDREN, String.valueOf(this.guestData.childrenCount()));
            }
            if (this.guestData.hasInfants()) {
                builder.appendQueryParameter(FindTweenAnalytics.INFANTS, String.valueOf(this.guestData.infantsCount()));
            }
        }
        return builder;
    }

    public String getName() {
        return this.listing.getName();
    }

    /* access modifiers changed from: protected */
    public String getThumbnailUri() {
        return getPhoto().getUrlForSize(ImageSize.LandscapeSmall);
    }

    public String getImageUrl() {
        return getPhoto().getUrlForSize(ImageSize.LandscapeLarge);
    }

    public String getImagePath() {
        return this.imagePath;
    }

    private Image getPhoto() {
        List<Photo> photos = this.listing.getPhotos();
        if (this.pictureId == -1 || photos == null || photos.size() <= this.pictureId) {
            return this.listing.getPhoto();
        }
        return (Image) photos.get(this.pictureId);
    }

    public Intent buildIntentForPackage(Intent baseIntent, CustomShareActivities csa, String packageName) {
        AirbnbEventLogger.Builder event = AirbnbEventLogger.event().name("sharing").mo8238kv(BaseAnalytics.OPERATION, "click").mo8238kv(BaseAnalytics.TARGET, "share_button").mo8238kv(ShareActivityIntents.ARG_ENTRY_POINT, "listing").mo8237kv("listing_id", this.listing.getId()).mo8238kv("service", packageName);
        if (this.pictureId != -1) {
            event.mo8236kv("photo_id", this.pictureId);
        }
        event.track();
        String listingUrl = buildShareUriString(csa);
        switch (csa) {
            case FB_MESSENGER:
                if (ShareChannelsHelper.shareToFacebookMessenger((Activity) this.context, Uri.parse(listingUrl), Uri.parse(getThumbnailUri()), this.listing.getName(), this.context.getString(C0921R.string.p3_sharetext))) {
                    return null;
                }
                return buildDefaultIntent(baseIntent, csa);
            case KAKAOTALK:
                Intent kakaoIntent = ShareChannelsHelper.shareToKakaoTalk(this.context, listingUrl, getThumbnailUri(), this.listing.getName());
                if (kakaoIntent == null) {
                    kakaoIntent = buildDefaultIntent(baseIntent, listingUrl);
                }
                return kakaoIntent;
            case WECHAT:
                WeChatHelper.shareWebPageToWechat(this.context, getName(), this.context.getString(C0921R.string.p3_sharetext), listingUrl, getThumbnailUri());
                return null;
            default:
                return buildDefaultIntent(baseIntent, listingUrl);
        }
    }

    public Intent buildDefaultIntent(Intent baseIntent, String url) {
        return baseIntent.putExtra("android.intent.extra.TEXT", url).putExtra("android.intent.extra.SUBJECT", this.context.getString(C0921R.string.share_listing_subject, new Object[]{this.listing.getName()}));
    }
}
