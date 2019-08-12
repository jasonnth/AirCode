package com.airbnb.android.core;

import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.authentication.AuthorizedAccountHelper;
import com.airbnb.android.core.data.DTKPartnerTask;
import com.airbnb.android.core.data.SFRPartnerTask;
import com.airbnb.android.core.filters.LocationTypeaheadFilterForChina;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.DLSCancellationPolicyFragment;
import com.airbnb.android.core.fragments.LottieNuxViewPagerFragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment;
import com.airbnb.android.core.identity.ReplaceVerifiedIdWithIdentityActivity;
import com.airbnb.android.core.instant_promo.InstantPromotionManager;
import com.airbnb.android.core.localpushnotifications.LocalPushNotificationManager;
import com.airbnb.android.core.models.AuthorizedAccount;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.AirBatchRequestObserver;
import com.airbnb.android.core.requests.AuthorizeServiceRequest;
import com.airbnb.android.core.requests.CountriesRequest;
import com.airbnb.android.core.requests.CurrenciesRequest;
import com.airbnb.android.core.requests.DeleteOauthTokenRequest;
import com.airbnb.android.core.requests.GetActiveAccountRequest;
import com.airbnb.android.core.requests.SetProfilePhotoRequest;
import com.airbnb.android.core.requests.UpdateThreadRequest;
import com.airbnb.android.core.services.OldManageListingPhotoUploadService;
import com.airbnb.android.core.services.PushNotificationBuilder;
import com.airbnb.android.core.utils.AirCookieManager;
import com.airbnb.android.core.utils.GCMHelper;
import com.airbnb.android.core.utils.ImageUtils;
import com.airbnb.android.core.utils.InstantBookUpsellManager;
import com.airbnb.android.core.utils.JPushHelper;
import com.airbnb.android.core.views.AirWebView;
import com.airbnb.android.core.views.AnimatedDrawableView;
import com.airbnb.android.core.views.ListingsTray;
import com.airbnb.android.core.views.LoopingViewPager;
import com.airbnb.android.core.views.PhoneNumberInputSheet;
import com.airbnb.android.core.views.calendar.VerticalCalendarAdapter;
import com.airbnb.android.core.wishlists.WishListHeartController;
import com.airbnb.p027n2.primitives.AirAutoCompleteTextView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public interface CoreGraph extends BaseGraph {
    void inject(ButtonPartnership buttonPartnership);

    void inject(ResourceManager resourceManager);

    void inject(AirActivity airActivity);

    void inject(AirbnbEventLogger airbnbEventLogger);

    void inject(NavigationLogging navigationLogging);

    void inject(AirbnbAccountManager airbnbAccountManager);

    void inject(AuthorizedAccountHelper authorizedAccountHelper);

    void inject(DTKPartnerTask dTKPartnerTask);

    void inject(SFRPartnerTask sFRPartnerTask);

    void inject(LocationTypeaheadFilterForChina locationTypeaheadFilterForChina);

    void inject(AirDialogFragment airDialogFragment);

    void inject(AirFragment airFragment);

    void inject(DLSCancellationPolicyFragment dLSCancellationPolicyFragment);

    void inject(LottieNuxViewPagerFragment lottieNuxViewPagerFragment);

    void inject(ZenDialog zenDialog);

    void inject(GuestPickerFragment guestPickerFragment);

    void inject(ReplaceVerifiedIdWithIdentityActivity replaceVerifiedIdWithIdentityActivity);

    void inject(InstantPromotionManager instantPromotionManager);

    void inject(LocalPushNotificationManager localPushNotificationManager);

    void inject(AuthorizedAccount authorizedAccount);

    void inject(Reservation reservation);

    void inject(Review review);

    void inject(AirBatchRequest airBatchRequest);

    void inject(AirBatchRequestObserver airBatchRequestObserver);

    void inject(AuthorizeServiceRequest authorizeServiceRequest);

    void inject(CountriesRequest countriesRequest);

    void inject(CurrenciesRequest currenciesRequest);

    void inject(DeleteOauthTokenRequest deleteOauthTokenRequest);

    void inject(GetActiveAccountRequest getActiveAccountRequest);

    void inject(SetProfilePhotoRequest setProfilePhotoRequest);

    void inject(UpdateThreadRequest updateThreadRequest);

    void inject(OldManageListingPhotoUploadService oldManageListingPhotoUploadService);

    void inject(PushNotificationBuilder pushNotificationBuilder);

    void inject(AirCookieManager airCookieManager);

    void inject(GCMHelper gCMHelper);

    void inject(ImageUtils imageUtils);

    void inject(InstantBookUpsellManager instantBookUpsellManager);

    void inject(JPushHelper jPushHelper);

    void inject(AirWebView airWebView);

    void inject(AnimatedDrawableView animatedDrawableView);

    void inject(ListingsTray listingsTray);

    void inject(LoopingViewPager loopingViewPager);

    void inject(PhoneNumberInputSheet phoneNumberInputSheet);

    void inject(VerticalCalendarAdapter verticalCalendarAdapter);

    void inject(WishListHeartController wishListHeartController);

    void inject(AirAutoCompleteTextView airAutoCompleteTextView);

    void inject(AirButton airButton);

    void inject(AirEditTextView airEditTextView);

    void inject(HaloImageView haloImageView);
}
