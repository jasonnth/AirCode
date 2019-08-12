package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.AirbnbCredit;
import com.airbnb.android.core.models.ArrivalDetails;
import com.airbnb.android.core.models.C5990Guidebook;
import com.airbnb.android.core.models.FreezeDetails;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.HostCancellationRefundDetails;
import com.airbnb.android.core.models.Incentive;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.RejectionTip;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationAlteration;
import com.airbnb.android.core.models.ReservationCancellationRefundBreakdown;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.TaxDescription;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public abstract class GenReservation implements Parcelable {
    @JsonProperty("airbnb_credit_amount_native")
    protected int mAirbnbCreditAmountNative;
    @JsonProperty("airbnb_credit_breakdown")
    protected List<AirbnbCredit> mAirbnbCredits;
    @JsonProperty("alterations")
    protected List<ReservationAlteration> mAlterations;
    @JsonProperty("arrival_details")
    protected ArrivalDetails mArrivalDetails;
    @JsonProperty("base_price_native")
    protected int mBasePrice;
    @JsonProperty("is_business_travel_verified")
    protected boolean mBusinessTravelVerified;
    @JsonProperty("cancellation_guest_fee_native")
    protected int mCancellationGuestFeeNative;
    @JsonProperty("cancellation_host_fee_native")
    protected int mCancellationHostFeeNative;
    @JsonProperty("cancellation_payout_native")
    protected int mCancellationPayoutNative;
    @JsonProperty("cancellation_policy_formatted")
    protected String mCancellationPolicy;
    @JsonProperty("cancellation_policy")
    protected String mCancellationPolicyKey;
    @JsonProperty("cancellation_policy_short_description")
    protected String mCancellationPolicyShortDescription;
    @JsonProperty("cancellation_refund_native")
    protected int mCancellationRefundNative;
    @JsonProperty("check_in")
    protected AirDate mCheckIn;
    @JsonProperty("check_out")
    protected AirDate mCheckOut;
    @JsonProperty("city_guidebook")
    protected C5990Guidebook mCityGuidebook;
    @JsonProperty("city_photo_url")
    protected String mCityPhotoUrl;
    @JsonProperty("extras_price_native")
    protected int mCleaningFee;
    @JsonProperty("confirmation_code")
    protected String mConfirmationCode;
    @JsonProperty("coupon_code")
    protected String mCouponCode;
    @JsonProperty("coupon_price_native")
    protected int mCouponPriceNative;
    @JsonProperty("coupon_savings")
    protected int mCouponSavings;
    @JsonProperty("is_deferred_payment_booking_request")
    protected boolean mDeferredPayment;
    @JsonProperty("freeze_details")
    protected FreezeDetails mFreezeDetails;
    @JsonProperty("government_id_required_for_instant_book")
    protected boolean mGovernmentIdRequiredForInstantBook;
    @JsonProperty("guest")
    protected User mGuest;
    @JsonProperty("guest_cancellation_refund_breakdown")
    protected ReservationCancellationRefundBreakdown mGuestCancellationRefundBreakdown;
    @JsonProperty("guest_cancellation_refund_total_formatted")
    protected String mGuestCancellationRefundTotalFormatted;
    @JsonProperty("number_of_guests")
    protected int mGuestCount;
    @JsonProperty("guest_details")
    protected GuestDetails mGuestDetails;
    @JsonProperty("guest_fee_native")
    protected int mGuestFee;
    @JsonProperty("guest_identifications_required")
    protected boolean mGuestIdentificationsRequired;
    @JsonProperty("other_user_review")
    protected Review mGuestReview;
    @JsonProperty("has_paid_amenity_orders")
    protected boolean mHasPaidAmenityOrders;
    @JsonProperty("has_unread_messages")
    protected boolean mHasUnreadMessages;
    @JsonProperty("help_thread_id")
    protected long mHelpThreadId;
    @JsonProperty("host")
    protected User mHost;
    @JsonProperty("formatted_host_base_price")
    protected String mHostBasePriceFormatted;
    @JsonProperty("host_cancellation_refund_details")
    protected HostCancellationRefundDetails mHostCancellationRefundDetails;
    @JsonProperty("host_fee_native")
    protected int mHostFee;
    @JsonProperty("host_guidebook")
    protected C5990Guidebook mHostGuidebook;
    @JsonProperty("rounded_per_night_price_string_host")
    protected String mHostPayoutAmountPerNightRounded;
    @JsonProperty("host_payout_breakdown")
    protected Price mHostPayoutBreakdown;
    @JsonProperty("current_user_review")
    protected Review mHostReview;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("incentives")
    protected List<Incentive> mIncentives;
    @JsonProperty("initial_charge_successful")
    protected boolean mInitialChargeSuccessful;
    @JsonProperty("instant_book_enabled_at_booking")
    protected boolean mInstantBookEnabledAtBooking;
    @JsonProperty("instant_bookable")
    protected boolean mInstantBookable;
    @JsonProperty("instant_booked")
    protected boolean mInstantBooked;
    @JsonProperty("is_airbnb_credit_excluded")
    protected boolean mIsAirbnbCreditExcluded;
    @JsonProperty("is_artificial")
    protected boolean mIsArtificial;
    @JsonProperty("launch_check_in_time_v2")
    protected boolean mIsCheckInTimeRequired;
    @JsonProperty("using_identity_flow")
    protected boolean mIsGuestPendingIdentityVerification;
    @JsonProperty("is_korean_strict_booking")
    protected boolean mIsKoreanStrictBooking;
    @JsonProperty("is_mobile_native_alteration_disabled")
    protected boolean mIsMobileNativeAlterationDisabled;
    @JsonProperty("is_shared_itinerary")
    protected boolean mIsSharedItinerary;
    @JsonProperty("last_vaultable_payment_option")
    protected PaymentOption mLastVaultablePaymentOption;
    @JsonProperty("listing")
    protected Listing mListing;
    @JsonProperty("localized_payout_price")
    protected int mLocalizedPayoutPrice;
    @JsonProperty("payment_options")
    protected List<PaymentOption> mPaymentOptions;
    @JsonProperty("payout_price_native")
    protected int mPayoutPriceNative;
    @JsonProperty("pending_began_at")
    protected AirDateTime mPendingBeganAt;
    @JsonProperty("pending_expires_at")
    protected AirDateTime mPendingExpiresAt;
    @JsonProperty("per_night_price_native")
    protected int mPerNightPrice;
    @JsonProperty("pricing_quote")
    protected PricingQuote mPricingQuote;
    @JsonProperty("primary_host")
    protected User mPrimaryHost;
    @JsonProperty("rejection_tips")
    protected List<RejectionTip> mRejectionTips;
    @JsonProperty("reservation")
    protected Reservation mReservation;
    @JsonProperty("reservation_status")
    protected ReservationStatus mReservationStatus;
    @JsonProperty("nights")
    protected int mReservedNightsCount;
    @JsonProperty("review")
    protected Review mReview;
    @JsonProperty("security_price_native")
    protected int mSecurityDeposit;
    @JsonProperty("is_set_for_business_tracking")
    protected boolean mSetForBusinessTracking;
    @JsonProperty("should_show_first_message")
    protected boolean mShouldShowFirstMessage;
    @JsonProperty("start_date")
    protected AirDate mStartDate;
    @JsonProperty("subtotal_native")
    protected int mSubtotalPriceNative;
    @JsonProperty("tax_descriptions")
    protected ArrayList<TaxDescription> mTaxDescriptions;
    @JsonProperty("thread_id")
    protected int mThreadId;
    @JsonProperty("total_price_native")
    protected int mTotalPrice;
    @JsonProperty("urgency_commitment_body")
    protected String mUrgencyCommitmentBody;
    @JsonProperty("urgency_commitment_headline")
    protected String mUrgencyCommitmentHeadline;
    @JsonProperty("urgency_commitment_icon")
    protected String mUrgencyCommitmentIcon;
    @JsonProperty("urgency_commitment_type")
    protected String mUrgencyCommitmentType;
    @JsonProperty("will_auto_accept")
    protected boolean mWillAutoAccept;

    protected GenReservation(AirDate startDate, AirDate checkIn, AirDate checkOut, AirDateTime pendingExpiresAt, AirDateTime pendingBeganAt, ArrayList<TaxDescription> taxDescriptions, ArrivalDetails arrivalDetails, FreezeDetails freezeDetails, GuestDetails guestDetails, C5990Guidebook cityGuidebook, C5990Guidebook hostGuidebook, HostCancellationRefundDetails hostCancellationRefundDetails, List<AirbnbCredit> airbnbCredits, List<Incentive> incentives, List<PaymentOption> paymentOptions, List<RejectionTip> rejectionTips, List<ReservationAlteration> alterations, Listing listing, PaymentOption lastVaultablePaymentOption, Price hostPayoutBreakdown, PricingQuote pricingQuote, Reservation reservation, ReservationCancellationRefundBreakdown guestCancellationRefundBreakdown, ReservationStatus reservationStatus, Review hostReview, Review guestReview, Review review, String confirmationCode, String couponCode, String hostPayoutAmountPerNightRounded, String cancellationPolicyKey, String cancellationPolicy, String cancellationPolicyShortDescription, String hostBasePriceFormatted, String urgencyCommitmentBody, String urgencyCommitmentHeadline, String urgencyCommitmentIcon, String urgencyCommitmentType, String cityPhotoUrl, String guestCancellationRefundTotalFormatted, User primaryHost, User host, User guest, boolean instantBookable, boolean instantBooked, boolean instantBookEnabledAtBooking, boolean deferredPayment, boolean hasUnreadMessages, boolean isArtificial, boolean initialChargeSuccessful, boolean businessTravelVerified, boolean setForBusinessTracking, boolean isSharedItinerary, boolean shouldShowFirstMessage, boolean isAirbnbCreditExcluded, boolean isGuestPendingIdentityVerification, boolean guestIdentificationsRequired, boolean isCheckInTimeRequired, boolean willAutoAccept, boolean hasPaidAmenityOrders, boolean governmentIdRequiredForInstantBook, boolean isMobileNativeAlterationDisabled, boolean isKoreanStrictBooking, int basePrice, int reservedNightsCount, int guestCount, int couponSavings, int couponPriceNative, int totalPrice, int subtotalPriceNative, int payoutPriceNative, int cleaningFee, int hostFee, int guestFee, int securityDeposit, int threadId, int perNightPrice, int cancellationRefundNative, int cancellationGuestFeeNative, int cancellationHostFeeNative, int cancellationPayoutNative, int airbnbCreditAmountNative, int localizedPayoutPrice, long id, long helpThreadId) {
        this();
        this.mStartDate = startDate;
        this.mCheckIn = checkIn;
        this.mCheckOut = checkOut;
        this.mPendingExpiresAt = pendingExpiresAt;
        this.mPendingBeganAt = pendingBeganAt;
        this.mTaxDescriptions = taxDescriptions;
        this.mArrivalDetails = arrivalDetails;
        this.mFreezeDetails = freezeDetails;
        this.mGuestDetails = guestDetails;
        this.mCityGuidebook = cityGuidebook;
        this.mHostGuidebook = hostGuidebook;
        this.mHostCancellationRefundDetails = hostCancellationRefundDetails;
        this.mAirbnbCredits = airbnbCredits;
        this.mIncentives = incentives;
        this.mPaymentOptions = paymentOptions;
        this.mRejectionTips = rejectionTips;
        this.mAlterations = alterations;
        this.mListing = listing;
        this.mLastVaultablePaymentOption = lastVaultablePaymentOption;
        this.mHostPayoutBreakdown = hostPayoutBreakdown;
        this.mPricingQuote = pricingQuote;
        this.mReservation = reservation;
        this.mGuestCancellationRefundBreakdown = guestCancellationRefundBreakdown;
        this.mReservationStatus = reservationStatus;
        this.mHostReview = hostReview;
        this.mGuestReview = guestReview;
        this.mReview = review;
        this.mConfirmationCode = confirmationCode;
        this.mCouponCode = couponCode;
        this.mHostPayoutAmountPerNightRounded = hostPayoutAmountPerNightRounded;
        this.mCancellationPolicyKey = cancellationPolicyKey;
        this.mCancellationPolicy = cancellationPolicy;
        this.mCancellationPolicyShortDescription = cancellationPolicyShortDescription;
        this.mHostBasePriceFormatted = hostBasePriceFormatted;
        this.mUrgencyCommitmentBody = urgencyCommitmentBody;
        this.mUrgencyCommitmentHeadline = urgencyCommitmentHeadline;
        this.mUrgencyCommitmentIcon = urgencyCommitmentIcon;
        this.mUrgencyCommitmentType = urgencyCommitmentType;
        this.mCityPhotoUrl = cityPhotoUrl;
        this.mGuestCancellationRefundTotalFormatted = guestCancellationRefundTotalFormatted;
        this.mPrimaryHost = primaryHost;
        this.mHost = host;
        this.mGuest = guest;
        this.mInstantBookable = instantBookable;
        this.mInstantBooked = instantBooked;
        this.mInstantBookEnabledAtBooking = instantBookEnabledAtBooking;
        this.mDeferredPayment = deferredPayment;
        this.mHasUnreadMessages = hasUnreadMessages;
        this.mIsArtificial = isArtificial;
        this.mInitialChargeSuccessful = initialChargeSuccessful;
        this.mBusinessTravelVerified = businessTravelVerified;
        this.mSetForBusinessTracking = setForBusinessTracking;
        this.mIsSharedItinerary = isSharedItinerary;
        this.mShouldShowFirstMessage = shouldShowFirstMessage;
        this.mIsAirbnbCreditExcluded = isAirbnbCreditExcluded;
        this.mIsGuestPendingIdentityVerification = isGuestPendingIdentityVerification;
        this.mGuestIdentificationsRequired = guestIdentificationsRequired;
        this.mIsCheckInTimeRequired = isCheckInTimeRequired;
        this.mWillAutoAccept = willAutoAccept;
        this.mHasPaidAmenityOrders = hasPaidAmenityOrders;
        this.mGovernmentIdRequiredForInstantBook = governmentIdRequiredForInstantBook;
        this.mIsMobileNativeAlterationDisabled = isMobileNativeAlterationDisabled;
        this.mIsKoreanStrictBooking = isKoreanStrictBooking;
        this.mBasePrice = basePrice;
        this.mReservedNightsCount = reservedNightsCount;
        this.mGuestCount = guestCount;
        this.mCouponSavings = couponSavings;
        this.mCouponPriceNative = couponPriceNative;
        this.mTotalPrice = totalPrice;
        this.mSubtotalPriceNative = subtotalPriceNative;
        this.mPayoutPriceNative = payoutPriceNative;
        this.mCleaningFee = cleaningFee;
        this.mHostFee = hostFee;
        this.mGuestFee = guestFee;
        this.mSecurityDeposit = securityDeposit;
        this.mThreadId = threadId;
        this.mPerNightPrice = perNightPrice;
        this.mCancellationRefundNative = cancellationRefundNative;
        this.mCancellationGuestFeeNative = cancellationGuestFeeNative;
        this.mCancellationHostFeeNative = cancellationHostFeeNative;
        this.mCancellationPayoutNative = cancellationPayoutNative;
        this.mAirbnbCreditAmountNative = airbnbCreditAmountNative;
        this.mLocalizedPayoutPrice = localizedPayoutPrice;
        this.mId = id;
        this.mHelpThreadId = helpThreadId;
    }

    protected GenReservation() {
    }

    public AirDate getStartDate() {
        return this.mStartDate;
    }

    @JsonProperty("start_date")
    public void setStartDate(AirDate value) {
        this.mStartDate = value;
    }

    public AirDate getCheckIn() {
        return this.mCheckIn;
    }

    @JsonProperty("check_in")
    public void setCheckIn(AirDate value) {
        this.mCheckIn = value;
    }

    public AirDate getCheckOut() {
        return this.mCheckOut;
    }

    @JsonProperty("check_out")
    public void setCheckOut(AirDate value) {
        this.mCheckOut = value;
    }

    public AirDateTime getPendingExpiresAt() {
        return this.mPendingExpiresAt;
    }

    @JsonProperty("pending_expires_at")
    public void setPendingExpiresAt(AirDateTime value) {
        this.mPendingExpiresAt = value;
    }

    public AirDateTime getPendingBeganAt() {
        return this.mPendingBeganAt;
    }

    @JsonProperty("pending_began_at")
    public void setPendingBeganAt(AirDateTime value) {
        this.mPendingBeganAt = value;
    }

    public ArrayList<TaxDescription> getTaxDescriptions() {
        return this.mTaxDescriptions;
    }

    @JsonProperty("tax_descriptions")
    public void setTaxDescriptions(ArrayList<TaxDescription> value) {
        this.mTaxDescriptions = value;
    }

    public ArrivalDetails getArrivalDetails() {
        return this.mArrivalDetails;
    }

    @JsonProperty("arrival_details")
    public void setArrivalDetails(ArrivalDetails value) {
        this.mArrivalDetails = value;
    }

    public FreezeDetails getFreezeDetails() {
        return this.mFreezeDetails;
    }

    @JsonProperty("freeze_details")
    public void setFreezeDetails(FreezeDetails value) {
        this.mFreezeDetails = value;
    }

    public GuestDetails getGuestDetails() {
        return this.mGuestDetails;
    }

    @JsonProperty("guest_details")
    public void setGuestDetails(GuestDetails value) {
        this.mGuestDetails = value;
    }

    public C5990Guidebook getCityGuidebook() {
        return this.mCityGuidebook;
    }

    @JsonProperty("city_guidebook")
    public void setCityGuidebook(C5990Guidebook value) {
        this.mCityGuidebook = value;
    }

    public C5990Guidebook getHostGuidebook() {
        return this.mHostGuidebook;
    }

    @JsonProperty("host_guidebook")
    public void setHostGuidebook(C5990Guidebook value) {
        this.mHostGuidebook = value;
    }

    public HostCancellationRefundDetails getHostCancellationRefundDetails() {
        return this.mHostCancellationRefundDetails;
    }

    @JsonProperty("host_cancellation_refund_details")
    public void setHostCancellationRefundDetails(HostCancellationRefundDetails value) {
        this.mHostCancellationRefundDetails = value;
    }

    public List<AirbnbCredit> getAirbnbCredits() {
        return this.mAirbnbCredits;
    }

    @JsonProperty("airbnb_credit_breakdown")
    public void setAirbnbCredits(List<AirbnbCredit> value) {
        this.mAirbnbCredits = value;
    }

    public List<Incentive> getIncentives() {
        return this.mIncentives;
    }

    @JsonProperty("incentives")
    public void setIncentives(List<Incentive> value) {
        this.mIncentives = value;
    }

    public List<PaymentOption> getPaymentOptions() {
        return this.mPaymentOptions;
    }

    @JsonProperty("payment_options")
    public void setPaymentOptions(List<PaymentOption> value) {
        this.mPaymentOptions = value;
    }

    public List<RejectionTip> getRejectionTips() {
        return this.mRejectionTips;
    }

    @JsonProperty("rejection_tips")
    public void setRejectionTips(List<RejectionTip> value) {
        this.mRejectionTips = value;
    }

    public List<ReservationAlteration> getAlterations() {
        return this.mAlterations;
    }

    @JsonProperty("alterations")
    public void setAlterations(List<ReservationAlteration> value) {
        this.mAlterations = value;
    }

    public Listing getListing() {
        return this.mListing;
    }

    public PaymentOption getLastVaultablePaymentOption() {
        return this.mLastVaultablePaymentOption;
    }

    @JsonProperty("last_vaultable_payment_option")
    public void setLastVaultablePaymentOption(PaymentOption value) {
        this.mLastVaultablePaymentOption = value;
    }

    public Price getHostPayoutBreakdown() {
        return this.mHostPayoutBreakdown;
    }

    @JsonProperty("host_payout_breakdown")
    public void setHostPayoutBreakdown(Price value) {
        this.mHostPayoutBreakdown = value;
    }

    public PricingQuote getPricingQuote() {
        return this.mPricingQuote;
    }

    @JsonProperty("pricing_quote")
    public void setPricingQuote(PricingQuote value) {
        this.mPricingQuote = value;
    }

    public Reservation getReservation() {
        return this.mReservation;
    }

    @JsonProperty("reservation")
    public void setReservation(Reservation value) {
        this.mReservation = value;
    }

    public ReservationCancellationRefundBreakdown getGuestCancellationRefundBreakdown() {
        return this.mGuestCancellationRefundBreakdown;
    }

    @JsonProperty("guest_cancellation_refund_breakdown")
    public void setGuestCancellationRefundBreakdown(ReservationCancellationRefundBreakdown value) {
        this.mGuestCancellationRefundBreakdown = value;
    }

    public ReservationStatus getReservationStatus() {
        return this.mReservationStatus;
    }

    public Review getHostReview() {
        return this.mHostReview;
    }

    @JsonProperty("current_user_review")
    public void setHostReview(Review value) {
        this.mHostReview = value;
    }

    public Review getGuestReview() {
        return this.mGuestReview;
    }

    @JsonProperty("other_user_review")
    public void setGuestReview(Review value) {
        this.mGuestReview = value;
    }

    public Review getReview() {
        return this.mReview;
    }

    @JsonProperty("review")
    public void setReview(Review value) {
        this.mReview = value;
    }

    public String getConfirmationCode() {
        return this.mConfirmationCode;
    }

    @JsonProperty("confirmation_code")
    public void setConfirmationCode(String value) {
        this.mConfirmationCode = value;
    }

    public String getCouponCode() {
        return this.mCouponCode;
    }

    @JsonProperty("coupon_code")
    public void setCouponCode(String value) {
        this.mCouponCode = value;
    }

    public String getHostPayoutAmountPerNightRounded() {
        return this.mHostPayoutAmountPerNightRounded;
    }

    @JsonProperty("rounded_per_night_price_string_host")
    public void setHostPayoutAmountPerNightRounded(String value) {
        this.mHostPayoutAmountPerNightRounded = value;
    }

    public String getCancellationPolicyKey() {
        return this.mCancellationPolicyKey;
    }

    @JsonProperty("cancellation_policy")
    public void setCancellationPolicyKey(String value) {
        this.mCancellationPolicyKey = value;
    }

    public String getCancellationPolicy() {
        return this.mCancellationPolicy;
    }

    @JsonProperty("cancellation_policy_formatted")
    public void setCancellationPolicy(String value) {
        this.mCancellationPolicy = value;
    }

    public String getCancellationPolicyShortDescription() {
        return this.mCancellationPolicyShortDescription;
    }

    @JsonProperty("cancellation_policy_short_description")
    public void setCancellationPolicyShortDescription(String value) {
        this.mCancellationPolicyShortDescription = value;
    }

    public String getHostBasePriceFormatted() {
        return this.mHostBasePriceFormatted;
    }

    @JsonProperty("formatted_host_base_price")
    public void setHostBasePriceFormatted(String value) {
        this.mHostBasePriceFormatted = value;
    }

    public String getUrgencyCommitmentBody() {
        return this.mUrgencyCommitmentBody;
    }

    @JsonProperty("urgency_commitment_body")
    public void setUrgencyCommitmentBody(String value) {
        this.mUrgencyCommitmentBody = value;
    }

    public String getUrgencyCommitmentHeadline() {
        return this.mUrgencyCommitmentHeadline;
    }

    @JsonProperty("urgency_commitment_headline")
    public void setUrgencyCommitmentHeadline(String value) {
        this.mUrgencyCommitmentHeadline = value;
    }

    public String getUrgencyCommitmentIcon() {
        return this.mUrgencyCommitmentIcon;
    }

    @JsonProperty("urgency_commitment_icon")
    public void setUrgencyCommitmentIcon(String value) {
        this.mUrgencyCommitmentIcon = value;
    }

    public String getUrgencyCommitmentType() {
        return this.mUrgencyCommitmentType;
    }

    @JsonProperty("urgency_commitment_type")
    public void setUrgencyCommitmentType(String value) {
        this.mUrgencyCommitmentType = value;
    }

    public String getCityPhotoUrl() {
        return this.mCityPhotoUrl;
    }

    @JsonProperty("city_photo_url")
    public void setCityPhotoUrl(String value) {
        this.mCityPhotoUrl = value;
    }

    public String getGuestCancellationRefundTotalFormatted() {
        return this.mGuestCancellationRefundTotalFormatted;
    }

    @JsonProperty("guest_cancellation_refund_total_formatted")
    public void setGuestCancellationRefundTotalFormatted(String value) {
        this.mGuestCancellationRefundTotalFormatted = value;
    }

    public User getPrimaryHost() {
        return this.mPrimaryHost;
    }

    @JsonProperty("primary_host")
    public void setPrimaryHost(User value) {
        this.mPrimaryHost = value;
    }

    public User getHost() {
        return this.mHost;
    }

    public User getGuest() {
        return this.mGuest;
    }

    public boolean isInstantBookable() {
        return this.mInstantBookable;
    }

    @JsonProperty("instant_bookable")
    public void setInstantBookable(boolean value) {
        this.mInstantBookable = value;
    }

    public boolean isInstantBooked() {
        return this.mInstantBooked;
    }

    @JsonProperty("instant_booked")
    public void setInstantBooked(boolean value) {
        this.mInstantBooked = value;
    }

    public boolean isInstantBookEnabledAtBooking() {
        return this.mInstantBookEnabledAtBooking;
    }

    @JsonProperty("instant_book_enabled_at_booking")
    public void setInstantBookEnabledAtBooking(boolean value) {
        this.mInstantBookEnabledAtBooking = value;
    }

    public boolean isDeferredPayment() {
        return this.mDeferredPayment;
    }

    @JsonProperty("is_deferred_payment_booking_request")
    public void setDeferredPayment(boolean value) {
        this.mDeferredPayment = value;
    }

    public boolean hasUnreadMessages() {
        return this.mHasUnreadMessages;
    }

    @JsonProperty("has_unread_messages")
    public void setHasUnreadMessages(boolean value) {
        this.mHasUnreadMessages = value;
    }

    public boolean isArtificial() {
        return this.mIsArtificial;
    }

    public boolean isInitialChargeSuccessful() {
        return this.mInitialChargeSuccessful;
    }

    @JsonProperty("initial_charge_successful")
    public void setInitialChargeSuccessful(boolean value) {
        this.mInitialChargeSuccessful = value;
    }

    public boolean isBusinessTravelVerified() {
        return this.mBusinessTravelVerified;
    }

    @JsonProperty("is_business_travel_verified")
    public void setBusinessTravelVerified(boolean value) {
        this.mBusinessTravelVerified = value;
    }

    public boolean isSetForBusinessTracking() {
        return this.mSetForBusinessTracking;
    }

    @JsonProperty("is_set_for_business_tracking")
    public void setSetForBusinessTracking(boolean value) {
        this.mSetForBusinessTracking = value;
    }

    public boolean isSharedItinerary() {
        return this.mIsSharedItinerary;
    }

    @JsonProperty("is_shared_itinerary")
    public void setIsSharedItinerary(boolean value) {
        this.mIsSharedItinerary = value;
    }

    public boolean isShouldShowFirstMessage() {
        return this.mShouldShowFirstMessage;
    }

    @JsonProperty("should_show_first_message")
    public void setShouldShowFirstMessage(boolean value) {
        this.mShouldShowFirstMessage = value;
    }

    public boolean isAirbnbCreditExcluded() {
        return this.mIsAirbnbCreditExcluded;
    }

    @JsonProperty("is_airbnb_credit_excluded")
    public void setIsAirbnbCreditExcluded(boolean value) {
        this.mIsAirbnbCreditExcluded = value;
    }

    public boolean isGuestPendingIdentityVerification() {
        return this.mIsGuestPendingIdentityVerification;
    }

    @JsonProperty("using_identity_flow")
    public void setIsGuestPendingIdentityVerification(boolean value) {
        this.mIsGuestPendingIdentityVerification = value;
    }

    public boolean isGuestIdentificationsRequired() {
        return this.mGuestIdentificationsRequired;
    }

    @JsonProperty("guest_identifications_required")
    public void setGuestIdentificationsRequired(boolean value) {
        this.mGuestIdentificationsRequired = value;
    }

    public boolean isCheckInTimeRequired() {
        return this.mIsCheckInTimeRequired;
    }

    @JsonProperty("launch_check_in_time_v2")
    public void setIsCheckInTimeRequired(boolean value) {
        this.mIsCheckInTimeRequired = value;
    }

    public boolean isWillAutoAccept() {
        return this.mWillAutoAccept;
    }

    @JsonProperty("will_auto_accept")
    public void setWillAutoAccept(boolean value) {
        this.mWillAutoAccept = value;
    }

    public boolean hasPaidAmenityOrders() {
        return this.mHasPaidAmenityOrders;
    }

    @JsonProperty("has_paid_amenity_orders")
    public void setHasPaidAmenityOrders(boolean value) {
        this.mHasPaidAmenityOrders = value;
    }

    public boolean isGovernmentIdRequiredForInstantBook() {
        return this.mGovernmentIdRequiredForInstantBook;
    }

    @JsonProperty("government_id_required_for_instant_book")
    public void setGovernmentIdRequiredForInstantBook(boolean value) {
        this.mGovernmentIdRequiredForInstantBook = value;
    }

    public boolean isMobileNativeAlterationDisabled() {
        return this.mIsMobileNativeAlterationDisabled;
    }

    @JsonProperty("is_mobile_native_alteration_disabled")
    public void setIsMobileNativeAlterationDisabled(boolean value) {
        this.mIsMobileNativeAlterationDisabled = value;
    }

    public boolean isKoreanStrictBooking() {
        return this.mIsKoreanStrictBooking;
    }

    @JsonProperty("is_korean_strict_booking")
    public void setIsKoreanStrictBooking(boolean value) {
        this.mIsKoreanStrictBooking = value;
    }

    public int getBasePrice() {
        return this.mBasePrice;
    }

    @JsonProperty("base_price_native")
    public void setBasePrice(int value) {
        this.mBasePrice = value;
    }

    public int getReservedNightsCount() {
        return this.mReservedNightsCount;
    }

    public int getGuestCount() {
        return this.mGuestCount;
    }

    @JsonProperty("number_of_guests")
    public void setGuestCount(int value) {
        this.mGuestCount = value;
    }

    public int getCouponSavings() {
        return this.mCouponSavings;
    }

    @JsonProperty("coupon_savings")
    public void setCouponSavings(int value) {
        this.mCouponSavings = value;
    }

    public int getCouponPriceNative() {
        return this.mCouponPriceNative;
    }

    @JsonProperty("coupon_price_native")
    public void setCouponPriceNative(int value) {
        this.mCouponPriceNative = value;
    }

    public int getTotalPrice() {
        return this.mTotalPrice;
    }

    @JsonProperty("total_price_native")
    public void setTotalPrice(int value) {
        this.mTotalPrice = value;
    }

    public int getSubtotalPriceNative() {
        return this.mSubtotalPriceNative;
    }

    @JsonProperty("subtotal_native")
    public void setSubtotalPriceNative(int value) {
        this.mSubtotalPriceNative = value;
    }

    public int getPayoutPriceNative() {
        return this.mPayoutPriceNative;
    }

    @JsonProperty("payout_price_native")
    public void setPayoutPriceNative(int value) {
        this.mPayoutPriceNative = value;
    }

    public int getCleaningFee() {
        return this.mCleaningFee;
    }

    @JsonProperty("extras_price_native")
    public void setCleaningFee(int value) {
        this.mCleaningFee = value;
    }

    public int getHostFee() {
        return this.mHostFee;
    }

    @JsonProperty("host_fee_native")
    public void setHostFee(int value) {
        this.mHostFee = value;
    }

    public int getGuestFee() {
        return this.mGuestFee;
    }

    @JsonProperty("guest_fee_native")
    public void setGuestFee(int value) {
        this.mGuestFee = value;
    }

    public int getSecurityDeposit() {
        return this.mSecurityDeposit;
    }

    @JsonProperty("security_price_native")
    public void setSecurityDeposit(int value) {
        this.mSecurityDeposit = value;
    }

    public int getThreadId() {
        return this.mThreadId;
    }

    @JsonProperty("thread_id")
    public void setThreadId(int value) {
        this.mThreadId = value;
    }

    public int getPerNightPrice() {
        return this.mPerNightPrice;
    }

    @JsonProperty("per_night_price_native")
    public void setPerNightPrice(int value) {
        this.mPerNightPrice = value;
    }

    public int getCancellationRefundNative() {
        return this.mCancellationRefundNative;
    }

    @JsonProperty("cancellation_refund_native")
    public void setCancellationRefundNative(int value) {
        this.mCancellationRefundNative = value;
    }

    public int getCancellationGuestFeeNative() {
        return this.mCancellationGuestFeeNative;
    }

    @JsonProperty("cancellation_guest_fee_native")
    public void setCancellationGuestFeeNative(int value) {
        this.mCancellationGuestFeeNative = value;
    }

    public int getCancellationHostFeeNative() {
        return this.mCancellationHostFeeNative;
    }

    @JsonProperty("cancellation_host_fee_native")
    public void setCancellationHostFeeNative(int value) {
        this.mCancellationHostFeeNative = value;
    }

    public int getCancellationPayoutNative() {
        return this.mCancellationPayoutNative;
    }

    @JsonProperty("cancellation_payout_native")
    public void setCancellationPayoutNative(int value) {
        this.mCancellationPayoutNative = value;
    }

    public int getAirbnbCreditAmountNative() {
        return this.mAirbnbCreditAmountNative;
    }

    @JsonProperty("airbnb_credit_amount_native")
    public void setAirbnbCreditAmountNative(int value) {
        this.mAirbnbCreditAmountNative = value;
    }

    public int getLocalizedPayoutPrice() {
        return this.mLocalizedPayoutPrice;
    }

    @JsonProperty("localized_payout_price")
    public void setLocalizedPayoutPrice(int value) {
        this.mLocalizedPayoutPrice = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getHelpThreadId() {
        return this.mHelpThreadId;
    }

    @JsonProperty("help_thread_id")
    public void setHelpThreadId(long value) {
        this.mHelpThreadId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mStartDate, 0);
        parcel.writeParcelable(this.mCheckIn, 0);
        parcel.writeParcelable(this.mCheckOut, 0);
        parcel.writeParcelable(this.mPendingExpiresAt, 0);
        parcel.writeParcelable(this.mPendingBeganAt, 0);
        parcel.writeTypedList(this.mTaxDescriptions);
        parcel.writeParcelable(this.mArrivalDetails, 0);
        parcel.writeParcelable(this.mFreezeDetails, 0);
        parcel.writeParcelable(this.mGuestDetails, 0);
        parcel.writeParcelable(this.mCityGuidebook, 0);
        parcel.writeParcelable(this.mHostGuidebook, 0);
        parcel.writeParcelable(this.mHostCancellationRefundDetails, 0);
        parcel.writeTypedList(this.mAirbnbCredits);
        parcel.writeTypedList(this.mIncentives);
        parcel.writeTypedList(this.mPaymentOptions);
        parcel.writeTypedList(this.mRejectionTips);
        parcel.writeTypedList(this.mAlterations);
        parcel.writeParcelable(this.mListing, 0);
        parcel.writeParcelable(this.mLastVaultablePaymentOption, 0);
        parcel.writeParcelable(this.mHostPayoutBreakdown, 0);
        parcel.writeParcelable(this.mPricingQuote, 0);
        parcel.writeParcelable(this.mReservation, 0);
        parcel.writeParcelable(this.mGuestCancellationRefundBreakdown, 0);
        parcel.writeParcelable(this.mReservationStatus, 0);
        parcel.writeParcelable(this.mHostReview, 0);
        parcel.writeParcelable(this.mGuestReview, 0);
        parcel.writeParcelable(this.mReview, 0);
        parcel.writeString(this.mConfirmationCode);
        parcel.writeString(this.mCouponCode);
        parcel.writeString(this.mHostPayoutAmountPerNightRounded);
        parcel.writeString(this.mCancellationPolicyKey);
        parcel.writeString(this.mCancellationPolicy);
        parcel.writeString(this.mCancellationPolicyShortDescription);
        parcel.writeString(this.mHostBasePriceFormatted);
        parcel.writeString(this.mUrgencyCommitmentBody);
        parcel.writeString(this.mUrgencyCommitmentHeadline);
        parcel.writeString(this.mUrgencyCommitmentIcon);
        parcel.writeString(this.mUrgencyCommitmentType);
        parcel.writeString(this.mCityPhotoUrl);
        parcel.writeString(this.mGuestCancellationRefundTotalFormatted);
        parcel.writeParcelable(this.mPrimaryHost, 0);
        parcel.writeParcelable(this.mHost, 0);
        parcel.writeParcelable(this.mGuest, 0);
        parcel.writeBooleanArray(new boolean[]{this.mInstantBookable, this.mInstantBooked, this.mInstantBookEnabledAtBooking, this.mDeferredPayment, this.mHasUnreadMessages, this.mIsArtificial, this.mInitialChargeSuccessful, this.mBusinessTravelVerified, this.mSetForBusinessTracking, this.mIsSharedItinerary, this.mShouldShowFirstMessage, this.mIsAirbnbCreditExcluded, this.mIsGuestPendingIdentityVerification, this.mGuestIdentificationsRequired, this.mIsCheckInTimeRequired, this.mWillAutoAccept, this.mHasPaidAmenityOrders, this.mGovernmentIdRequiredForInstantBook, this.mIsMobileNativeAlterationDisabled, this.mIsKoreanStrictBooking});
        parcel.writeInt(this.mBasePrice);
        parcel.writeInt(this.mReservedNightsCount);
        parcel.writeInt(this.mGuestCount);
        parcel.writeInt(this.mCouponSavings);
        parcel.writeInt(this.mCouponPriceNative);
        parcel.writeInt(this.mTotalPrice);
        parcel.writeInt(this.mSubtotalPriceNative);
        parcel.writeInt(this.mPayoutPriceNative);
        parcel.writeInt(this.mCleaningFee);
        parcel.writeInt(this.mHostFee);
        parcel.writeInt(this.mGuestFee);
        parcel.writeInt(this.mSecurityDeposit);
        parcel.writeInt(this.mThreadId);
        parcel.writeInt(this.mPerNightPrice);
        parcel.writeInt(this.mCancellationRefundNative);
        parcel.writeInt(this.mCancellationGuestFeeNative);
        parcel.writeInt(this.mCancellationHostFeeNative);
        parcel.writeInt(this.mCancellationPayoutNative);
        parcel.writeInt(this.mAirbnbCreditAmountNative);
        parcel.writeInt(this.mLocalizedPayoutPrice);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mHelpThreadId);
    }

    public void readFromParcel(Parcel source) {
        this.mStartDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mCheckIn = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mCheckOut = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mPendingExpiresAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mPendingBeganAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mTaxDescriptions = source.createTypedArrayList(TaxDescription.CREATOR);
        this.mArrivalDetails = (ArrivalDetails) source.readParcelable(ArrivalDetails.class.getClassLoader());
        this.mFreezeDetails = (FreezeDetails) source.readParcelable(FreezeDetails.class.getClassLoader());
        this.mGuestDetails = (GuestDetails) source.readParcelable(GuestDetails.class.getClassLoader());
        this.mCityGuidebook = (C5990Guidebook) source.readParcelable(C5990Guidebook.class.getClassLoader());
        this.mHostGuidebook = (C5990Guidebook) source.readParcelable(C5990Guidebook.class.getClassLoader());
        this.mHostCancellationRefundDetails = (HostCancellationRefundDetails) source.readParcelable(HostCancellationRefundDetails.class.getClassLoader());
        this.mAirbnbCredits = source.createTypedArrayList(AirbnbCredit.CREATOR);
        this.mIncentives = source.createTypedArrayList(Incentive.CREATOR);
        this.mPaymentOptions = source.createTypedArrayList(PaymentOption.CREATOR);
        this.mRejectionTips = source.createTypedArrayList(RejectionTip.CREATOR);
        this.mAlterations = source.createTypedArrayList(ReservationAlteration.CREATOR);
        this.mListing = (Listing) source.readParcelable(Listing.class.getClassLoader());
        this.mLastVaultablePaymentOption = (PaymentOption) source.readParcelable(PaymentOption.class.getClassLoader());
        this.mHostPayoutBreakdown = (Price) source.readParcelable(Price.class.getClassLoader());
        this.mPricingQuote = (PricingQuote) source.readParcelable(PricingQuote.class.getClassLoader());
        this.mReservation = (Reservation) source.readParcelable(Reservation.class.getClassLoader());
        this.mGuestCancellationRefundBreakdown = (ReservationCancellationRefundBreakdown) source.readParcelable(ReservationCancellationRefundBreakdown.class.getClassLoader());
        this.mReservationStatus = (ReservationStatus) source.readParcelable(ReservationStatus.class.getClassLoader());
        this.mHostReview = (Review) source.readParcelable(Review.class.getClassLoader());
        this.mGuestReview = (Review) source.readParcelable(Review.class.getClassLoader());
        this.mReview = (Review) source.readParcelable(Review.class.getClassLoader());
        this.mConfirmationCode = source.readString();
        this.mCouponCode = source.readString();
        this.mHostPayoutAmountPerNightRounded = source.readString();
        this.mCancellationPolicyKey = source.readString();
        this.mCancellationPolicy = source.readString();
        this.mCancellationPolicyShortDescription = source.readString();
        this.mHostBasePriceFormatted = source.readString();
        this.mUrgencyCommitmentBody = source.readString();
        this.mUrgencyCommitmentHeadline = source.readString();
        this.mUrgencyCommitmentIcon = source.readString();
        this.mUrgencyCommitmentType = source.readString();
        this.mCityPhotoUrl = source.readString();
        this.mGuestCancellationRefundTotalFormatted = source.readString();
        this.mPrimaryHost = (User) source.readParcelable(User.class.getClassLoader());
        this.mHost = (User) source.readParcelable(User.class.getClassLoader());
        this.mGuest = (User) source.readParcelable(User.class.getClassLoader());
        boolean[] bools = source.createBooleanArray();
        this.mInstantBookable = bools[0];
        this.mInstantBooked = bools[1];
        this.mInstantBookEnabledAtBooking = bools[2];
        this.mDeferredPayment = bools[3];
        this.mHasUnreadMessages = bools[4];
        this.mIsArtificial = bools[5];
        this.mInitialChargeSuccessful = bools[6];
        this.mBusinessTravelVerified = bools[7];
        this.mSetForBusinessTracking = bools[8];
        this.mIsSharedItinerary = bools[9];
        this.mShouldShowFirstMessage = bools[10];
        this.mIsAirbnbCreditExcluded = bools[11];
        this.mIsGuestPendingIdentityVerification = bools[12];
        this.mGuestIdentificationsRequired = bools[13];
        this.mIsCheckInTimeRequired = bools[14];
        this.mWillAutoAccept = bools[15];
        this.mHasPaidAmenityOrders = bools[16];
        this.mGovernmentIdRequiredForInstantBook = bools[17];
        this.mIsMobileNativeAlterationDisabled = bools[18];
        this.mIsKoreanStrictBooking = bools[19];
        this.mBasePrice = source.readInt();
        this.mReservedNightsCount = source.readInt();
        this.mGuestCount = source.readInt();
        this.mCouponSavings = source.readInt();
        this.mCouponPriceNative = source.readInt();
        this.mTotalPrice = source.readInt();
        this.mSubtotalPriceNative = source.readInt();
        this.mPayoutPriceNative = source.readInt();
        this.mCleaningFee = source.readInt();
        this.mHostFee = source.readInt();
        this.mGuestFee = source.readInt();
        this.mSecurityDeposit = source.readInt();
        this.mThreadId = source.readInt();
        this.mPerNightPrice = source.readInt();
        this.mCancellationRefundNative = source.readInt();
        this.mCancellationGuestFeeNative = source.readInt();
        this.mCancellationHostFeeNative = source.readInt();
        this.mCancellationPayoutNative = source.readInt();
        this.mAirbnbCreditAmountNative = source.readInt();
        this.mLocalizedPayoutPrice = source.readInt();
        this.mId = source.readLong();
        this.mHelpThreadId = source.readLong();
    }
}
