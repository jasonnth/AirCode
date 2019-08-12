package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.models.ExplorePlaylist;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PlaceActivity;
import com.airbnb.android.core.models.WishList;

public class ShareActivityIntents {
    public static final String ARG_ACTIVITY_BASE_URL = "activity_base_url";
    public static final String ARG_ACTIVITY_ID = "activity_id";
    public static final String ARG_ACTIVITY_IS_MEETUP = "activity_is_meetup";
    public static final String ARG_ACTIVITY_LOCATION = "activity_location";
    public static final String ARG_ACTIVITY_PICTURE = "activity_picture";
    public static final String ARG_ACTIVITY_TITLE = "activity_title";
    public static final String ARG_CHECK_IN = "check_in";
    public static final String ARG_CHECK_OUT = "check_out";
    public static final String ARG_ENTRY_POINT = "entry_point";
    public static final String ARG_GUEST_DETAILS = "guest_details";
    public static final String ARG_LISTING = "listing";
    public static final String ARG_PICTURE_ID = "picture_id";
    public static final String ARG_PLAYLIST_BASE_URL = "playlist_base_url";
    public static final String ARG_PLAYLIST_ID = "playlist_id";
    public static final String ARG_PLAYLIST_LOCATION = "playlist_location";
    public static final String ARG_PLAYLIST_PICTURE = "playlist_picture";
    public static final String ARG_PLAYLIST_TITLE = "playlist_title";
    public static final String ARG_PUBLIC_SHARE = "public_share";
    public static final String ARG_SCREENSHOT_PATH = "screenshot_path";
    public static final String ARG_STORY = "story";
    public static final String ARG_WISHLIST = "wishlist";
    public static final String ENTRY_POINT_EXPERIENCE = "experience";
    public static final String ENTRY_POINT_GUIDEBOOK_DETOUR = "guidebook_detour";
    public static final String ENTRY_POINT_GUIDEBOOK_INSIDER = "guidebook_insider";
    public static final String ENTRY_POINT_GUIDEBOOK_PLACE = "guidebook_place";
    public static final String ENTRY_POINT_LISTING = "listing";
    public static final String ENTRY_POINT_LISTING_PHOTO = "listing_photo";
    public static final String ENTRY_POINT_LISTING_SCREENSHOT = "listing_screenshot";
    public static final String ENTRY_POINT_PLACE_ACTIVITY = "place_activity";
    public static final String ENTRY_POINT_PLAYLIST = "playlist";
    public static final String ENTRY_POINT_STORY = "story";
    public static final String ENTRY_POINT_WISHLIST = "wishlist";

    private static Intent newIntent(Context context, String entryPoint) {
        return new Intent(context, Activities.share()).putExtra(ARG_ENTRY_POINT, entryPoint);
    }

    public static Intent newIntentForListing(Context context, Listing listing, AirDate checkIn, AirDate checkOut, GuestDetails guestDetails) {
        return newIntent(context, "listing").putExtra("listing", listing).putExtra("check_in", checkIn).putExtra("check_out", checkOut).putExtra(ARG_GUEST_DETAILS, guestDetails);
    }

    public static Intent newIntentForListingPhoto(Context context, Listing listing, int pictureId) {
        return newIntent(context, "listing_photo").putExtra("listing", listing).putExtra(ARG_PICTURE_ID, pictureId);
    }

    public static Intent newIntentForListingScreenshot(Context context, Listing listing, String screenshotPath) {
        return newIntent(context, ENTRY_POINT_LISTING_SCREENSHOT).putExtra("listing", listing).putExtra(ARG_SCREENSHOT_PATH, screenshotPath);
    }

    public static Intent newIntentForWishlistShare(Context context, WishList wishList) {
        return newIntentForWishlist(context, wishList, true);
    }

    public static Intent newIntentForWishlistInvite(Context context, WishList wishList) {
        return newIntentForWishlist(context, wishList, false);
    }

    private static Intent newIntentForWishlist(Context context, WishList wishList, boolean publicShare) {
        return newIntent(context, "wishlist").putExtra("wishlist", wishList).putExtra(ARG_PUBLIC_SHARE, publicShare);
    }

    public static Intent newIntentForArticle(Context context, Article article) {
        return newIntent(context, "story").putExtra("story", article);
    }

    public static Intent newIntentForPlaceActivity(Context context, PlaceActivity placeActivity, boolean isMeetup) {
        return newIntent(context, "place_activity").putExtra("activity_id", placeActivity.getId()).putExtra(ARG_ACTIVITY_BASE_URL, placeActivity.getShareUrl()).putExtra(ARG_ACTIVITY_TITLE, placeActivity.getTitle()).putExtra(ARG_ACTIVITY_LOCATION, placeActivity.getPlace().getCity()).putExtra(ARG_ACTIVITY_PICTURE, (Parcelable) placeActivity.getCoverPhotos().get(0)).putExtra(ARG_ACTIVITY_IS_MEETUP, isMeetup);
    }

    public static Intent newIntentForPlaylist(Context context, ExplorePlaylist playlist) {
        return newIntent(context, ENTRY_POINT_PLAYLIST).putExtra("playlist_id", playlist.getCampaignId()).putExtra(ARG_PLAYLIST_BASE_URL, playlist.getShareUrl()).putExtra(ARG_PLAYLIST_TITLE, playlist.getTitle()).putExtra(ARG_PLAYLIST_LOCATION, playlist.getMarket()).putExtra(ARG_PLAYLIST_PICTURE, playlist.getHeaderPictureSm());
    }
}
