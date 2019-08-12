package com.airbnb.android.core.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.beta.models.guidebook.LocalAttraction;
import com.airbnb.android.core.beta.models.guidebook.Place;
import com.airbnb.android.core.beta.models.guidebook.PlaceHour;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.PlaceRecommendationRequest;
import com.airbnb.android.core.responses.PlaceRecommendationResponse;
import com.airbnb.android.core.utils.MapUtil;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.views.ColorizedIconView;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import java.util.HashSet;
import org.spongycastle.i18n.ErrorBundle;

public class LocalAttractionDetailFragment extends AirFragment {
    private static final String ARG_LISTING = "listing";
    private static final String ARG_RECOMMENDATION = "recommendation";
    private static final long ROTATION_DURATION = 300;
    @BindView
    AirTextView mAttractionAddress;
    @BindView
    View mAttractionAddressSection;
    @BindView
    AirTextView mAttractionCategoryPrice;
    @BindView
    AirTextView mAttractionDaysOpen;
    @BindView
    ColorizedIconView mAttractionDaysOpenCaret;
    @BindView
    View mAttractionDaysOpenContainer;
    @BindView
    AirTextView mAttractionDescription;
    @BindView
    AirImageView mAttractionHeroImage;
    @BindView
    LinearLayout mAttractionHoursContainer;
    @BindView
    AirTextView mAttractionName;
    @BindView
    AirTextView mAttractionPhone;
    @BindView
    View mAttractionPhoneSection;
    @BindView
    AirTextView mAttractionUrl;
    @BindView
    View mAttractionUrlSection;
    @BindView
    HaloImageView mHostImageView;
    @BindView
    AirTextView mHostName;
    private boolean mHoursDisplayed;
    private Listing mListing;
    @BindView
    AirTextView mNumReview;
    @BindView
    View mRatingContainer;
    /* access modifiers changed from: private */
    public LocalAttraction mRecommendation;
    @BindView
    RatingBar mStarRating;

    public static Bundle bundleWithLocalAttraction(Listing listing, LocalAttraction recommendation) {
        Bundle args = new Bundle();
        args.putParcelable("recommendation", recommendation);
        args.putParcelable("listing", listing);
        return args;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mRecommendation = (LocalAttraction) savedInstanceState.getParcelable("recommendation");
            this.mListing = (Listing) savedInstanceState.getParcelable("listing");
            return;
        }
        this.mRecommendation = (LocalAttraction) getArguments().getParcelable("recommendation");
        this.mListing = (Listing) getArguments().getParcelable("listing");
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("recommendation", this.mRecommendation);
        outState.putParcelable("listing", this.mListing);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0716R.layout.fragment_local_attraction_details, container, false);
        bindViews(view);
        if (this.mRecommendation.getPlace() == null) {
            PlaceRecommendationRequest placeRecommendationRequest = new PlaceRecommendationRequest(this.mRecommendation.getResourceId(), new NonResubscribableRequestListener<PlaceRecommendationResponse>() {
                public void onResponse(PlaceRecommendationResponse response) {
                    LocalAttractionDetailFragment.this.showLoader(false);
                    LocalAttractionDetailFragment.this.mRecommendation = response.localAttraction;
                    LocalAttractionDetailFragment.this.setupView();
                }

                public void onErrorResponse(AirRequestNetworkException error) {
                    LocalAttractionDetailFragment.this.showLoader(false);
                }
            });
            showLoader(true);
            placeRecommendationRequest.execute(this.requestManager);
        } else {
            setupView();
        }
        getActivity().setTitle(this.mRecommendation.getName());
        AirbnbEventLogger.track("local_attractions", Strap.make().mo11639kv("page", ErrorBundle.DETAIL_ENTRY).mo11639kv("action", "open").mo11639kv("attraction_name", this.mRecommendation.getName()).mo11638kv("attraction_id", this.mRecommendation.getResourceId()));
        return view;
    }

    /* access modifiers changed from: private */
    public void setupView() {
        setupAttractionInfo();
        setupRatings();
        setupHoursInfo();
        setupHostDetails();
        setupAttractionContactInfo();
    }

    private void setupAttractionInfo() {
        String descriptionCaption;
        this.mAttractionHeroImage.post(LocalAttractionDetailFragment$$Lambda$1.lambdaFactory$(this));
        this.mAttractionName.setText(this.mRecommendation.getName());
        int numberDollarSigns = this.mRecommendation.getPrice();
        if (numberDollarSigns > 0) {
            String symbolicPrice = this.mCurrencyHelper.getSymbolicPrice(numberDollarSigns);
            descriptionCaption = getResources().getString(C0716R.string.guidebook_pager_caption, new Object[]{this.mRecommendation.getCategory(), symbolicPrice});
        } else {
            descriptionCaption = this.mRecommendation.getCategory();
        }
        if (!TextUtils.isEmpty(descriptionCaption)) {
            this.mAttractionCategoryPrice.setText(descriptionCaption);
            this.mAttractionCategoryPrice.setVisibility(0);
            return;
        }
        this.mAttractionCategoryPrice.setVisibility(8);
    }

    private void setupRatings() {
        float rating = (float) this.mRecommendation.getPlace().getRating();
        if (rating > 0.0f) {
            this.mStarRating.setRating(rating);
        }
        int numRatings = this.mRecommendation.getPlace().getNumRatings();
        if (numRatings > 0) {
            this.mNumReview.setText(getResources().getQuantityString(C0716R.plurals.x_reviews_google, numRatings, new Object[]{Integer.valueOf(numRatings)}));
            return;
        }
        this.mRatingContainer.setVisibility(8);
    }

    @SuppressLint({"RestrictedApi"})
    private void setupHoursInfo() {
        PlaceHour[] openingHours;
        HashSet<String> daysSet = new HashSet<>();
        this.mAttractionHoursContainer.removeAllViews();
        LayoutInflater inflater = getLayoutInflater(null);
        for (PlaceHour hour : this.mRecommendation.getPlace().getOpeningHours()) {
            LinearLayout hoursForDay = (LinearLayout) inflater.inflate(C0716R.layout.list_item_attraction_hours, null);
            AirTextView hoursTextView = (AirTextView) ButterKnife.findById((View) hoursForDay, C0716R.C0718id.hours);
            ((AirTextView) ButterKnife.findById((View) hoursForDay, C0716R.C0718id.day)).setText(hour.getDay());
            if (!TextUtils.isEmpty(hour.getClose())) {
                hoursTextView.setText(getString(C0716R.string.guidebook_hours, hour.getOpen(), hour.getClose()));
                this.mAttractionHoursContainer.addView(hoursForDay);
            } else {
                hoursTextView.setText(C0716R.string.guidebook_open_24_hr);
            }
            daysSet.add(hour.getDay());
        }
        int numDaysOpen = daysSet.size();
        if (numDaysOpen > 0) {
            this.mAttractionDaysOpen.setText(getResources().getQuantityString(C0716R.plurals.x_days_open, numDaysOpen, new Object[]{Integer.valueOf(numDaysOpen)}));
            this.mAttractionDaysOpenContainer.setVisibility(0);
            this.mHoursDisplayed = false;
            this.mAttractionDaysOpenContainer.setOnClickListener(LocalAttractionDetailFragment$$Lambda$2.lambdaFactory$(this));
        }
    }

    static /* synthetic */ void lambda$setupHoursInfo$0(LocalAttractionDetailFragment localAttractionDetailFragment, View v) {
        float f;
        float f2 = 0.0f;
        localAttractionDetailFragment.mHoursDisplayed = !localAttractionDetailFragment.mHoursDisplayed;
        if (localAttractionDetailFragment.mHoursDisplayed) {
            f = 0.0f;
        } else {
            f = 180.0f;
        }
        if (localAttractionDetailFragment.mHoursDisplayed) {
            f2 = 180.0f;
        }
        RotateAnimation rotateAnimation = new RotateAnimation(f, f2, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(300);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setFillEnabled(true);
        localAttractionDetailFragment.mAttractionDaysOpenCaret.startAnimation(rotateAnimation);
        ViewUtils.setVisibleIf((View) localAttractionDetailFragment.mAttractionHoursContainer, localAttractionDetailFragment.mHoursDisplayed);
    }

    private void setupHostDetails() {
        if (this.mListing.getHost() == null) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Listing without host: " + this.mListing.getId()));
            return;
        }
        this.mHostImageView.setImageUrl(this.mListing.getHost().getPictureUrlForThumbnail());
        this.mHostName.setText(this.mListing.getHost().getFirstName());
        this.mAttractionDescription.setText(this.mRecommendation.getDescription());
    }

    private void setupAttractionContactInfo() {
        String finalAddress;
        Place place = this.mRecommendation.getPlace();
        if (!TextUtils.isEmpty(place.getAddress())) {
            this.mAttractionAddressSection.setVisibility(0);
            if (place.getStreetNumber() != null) {
                finalAddress = getString(C0716R.string.guidebook_address, place.getStreetNumber(), place.getAddress());
            } else {
                finalAddress = place.getAddress();
            }
            this.mAttractionAddress.setText(finalAddress);
            this.mAttractionAddressSection.setOnClickListener(LocalAttractionDetailFragment$$Lambda$3.lambdaFactory$(this, place));
            this.mAttractionAddressSection.setOnLongClickListener(getLongClickListener(finalAddress));
        }
        if (!TextUtils.isEmpty(place.getPhone())) {
            this.mAttractionPhoneSection.setVisibility(0);
            String phoneNumber = place.getPhone();
            this.mAttractionPhone.setText(phoneNumber);
            this.mAttractionPhoneSection.setOnClickListener(LocalAttractionDetailFragment$$Lambda$4.lambdaFactory$(this, phoneNumber));
            this.mAttractionPhoneSection.setOnLongClickListener(getLongClickListener(phoneNumber));
        }
        if (!TextUtils.isEmpty(place.getWebsite())) {
            String website = place.getWebsite();
            this.mAttractionUrl.setText(MiscUtils.getHostFromUrl(website));
            this.mAttractionUrlSection.setVisibility(0);
            this.mAttractionUrlSection.setOnClickListener(LocalAttractionDetailFragment$$Lambda$5.lambdaFactory$(this, website));
            this.mAttractionUrlSection.setOnLongClickListener(getLongClickListener(website));
        }
    }

    static /* synthetic */ void lambda$setupAttractionContactInfo$1(LocalAttractionDetailFragment localAttractionDetailFragment, Place place, View v) {
        localAttractionDetailFragment.getActivity().startActivity(MapUtil.intentFor(localAttractionDetailFragment.getContext(), place.getLat(), place.getLng(), place.getName() == null ? place.getAddress() : place.getName()));
    }

    static /* synthetic */ void lambda$setupAttractionContactInfo$3(LocalAttractionDetailFragment localAttractionDetailFragment, String website, View v) {
        Intent webIntent = new Intent("android.intent.action.VIEW");
        webIntent.setData(Uri.parse(website));
        localAttractionDetailFragment.getActivity().startActivity(webIntent);
    }

    /* access modifiers changed from: private */
    public void loadHeroImage() {
        if (!TextUtils.isEmpty(this.mRecommendation.getPhoto())) {
            this.mAttractionHeroImage.setVisibility(0);
            this.mAttractionHeroImage.setImageUrl(this.mRecommendation.getPhoto());
            this.mAttractionHeroImage.getLayoutParams().height = (int) (((float) this.mAttractionHeroImage.getWidth()) * 0.6666667f);
        }
    }

    private OnLongClickListener getLongClickListener(String text) {
        return LocalAttractionDetailFragment$$Lambda$6.lambdaFactory$(this, text);
    }
}
