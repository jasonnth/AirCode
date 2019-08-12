package com.airbnb.android.p011p3;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.PricingQuote.RateType;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.SearchPricingUtil;
import com.airbnb.android.core.utils.SearchUtil;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.fonts.CustomFontSpan;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.utils.TextUtil;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.android.p3.ExploreBookButton */
public class ExploreBookButton extends RelativeLayout {
    @BindView
    AirTextView buttonText;
    @BindView
    View contactHostButton;
    @BindView
    LoadingView loadingView;
    @BindView
    AirTextView numReviewsView;
    @BindView
    AirTextView percentageRecommend;
    @BindView
    AirTextView priceDetails;
    @BindView
    RatingBar ratingBar;
    @BindView
    ViewGroup ratingContainer;
    @BindView
    View referralCreditLayout;
    @BindView
    AirTextView referralCreditView;

    public ExploreBookButton(Context context) {
        super(context);
        init();
    }

    public ExploreBookButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExploreBookButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C7532R.layout.explore_book_button, this);
        if (ChinaUtils.enableExploreBookButtonOptimization(getContext())) {
            setupUIWithContactHostButton();
        } else {
            setupUIWithoutContactHostButton();
        }
    }

    private void setupUIWithoutContactHostButton() {
        inflateActionButtons(C7532R.layout.explore_book_action_buttons);
        ButterKnife.bind((View) this);
        Resources resources = getResources();
        this.buttonText.setMaxWidth(Math.min(resources.getDimensionPixelSize(C7532R.dimen.p3_book_button_max_width), (ViewLibUtils.getScreenWidth(getContext()) - resources.getDimensionPixelSize(C7532R.dimen.p3_price_rating_container_min_width)) - (resources.getDimensionPixelSize(C7532R.dimen.n2_horizontal_padding_medium) * 3)));
    }

    private void setupUIWithContactHostButton() {
        inflateActionButtons(C7532R.layout.explore_book_action_buttons_cn);
        ButterKnife.bind((View) this);
    }

    private void inflateActionButtons(int layoutId) {
        inflate(getContext(), layoutId, (LinearLayout) findViewById(C7532R.C7534id.action_button_container));
    }

    public void setText(int stringRes) {
        this.buttonText.setText(stringRes);
    }

    public void setReferralCredit(String travelCredit) {
        ViewLibUtils.setVisibleIf(this.referralCreditLayout, !TextUtils.isEmpty(travelCredit));
        if (!TextUtils.isEmpty(travelCredit)) {
            this.referralCreditView.setText(TextUtil.makeCircularBold(getContext(), getContext().getString(C7532R.string.referral_credit_banner, new Object[]{travelCredit}), travelCredit));
        }
    }

    public void setReferralCreditClickListener(OnClickListener travelCreditOnClickListener) {
        this.referralCreditLayout.setOnClickListener(travelCreditOnClickListener);
    }

    public View getView() {
        return this;
    }

    public void setDrawableLeft(Drawable drawableLeft) {
        this.buttonText.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
    }

    public void setLoading() {
        this.buttonText.setVisibility(4);
        this.loadingView.setVisibility(0);
    }

    public void setNormal() {
        this.loadingView.setVisibility(8);
        this.buttonText.setVisibility(0);
    }

    public void configureListingDetails(Listing listing) {
        boolean showRating;
        int numReviews = listing.getReviewsCount();
        if (numReviews >= 3) {
            showRating = true;
        } else {
            showRating = false;
        }
        ViewLibUtils.setVisibleIf(this.ratingBar, showRating);
        if (showRating) {
            setReviewsText(String.valueOf(numReviews));
            this.ratingBar.setRating(listing.getStarRating());
            this.ratingContainer.setContentDescription(getContext().getResources().getQuantityString(C7532R.plurals.ratings, numReviews, new Object[]{Integer.valueOf(numReviews)}) + getContext().getResources().getQuantityString(C7532R.plurals.rating_bar_stars_content_description, (int) Math.ceil((double) this.ratingBar.getRating()), new Object[]{Float.valueOf(this.ratingBar.getRating())}));
        } else if (listing.isIsNewListing() == null || !listing.isIsNewListing().booleanValue()) {
            setReviewsText(null);
        } else {
            setReviewsText(getResources().getString(C7532R.string.word_new));
        }
    }

    public boolean isShowingRating() {
        return this.ratingBar.getVisibility() == 0;
    }

    public void configurePricingDetails(PricingQuote pricingQuote) {
        String priceTag = SearchUtil.getPriceTagText(pricingQuote);
        String formattedPriceText = getFormattedPriceText(getContext(), priceTag, pricingQuote);
        SpannableStringBuilder builder = new SpannableStringBuilder().append(formattedPriceText);
        int start = formattedPriceText.indexOf(priceTag);
        builder.setSpan(new CustomFontSpan(getContext(), Font.CircularBold), start, priceTag.length() + start, 0);
        this.priceDetails.setText(builder);
        if (pricingQuote.getP3PercentageRecommended() == 0 || !Experiments.showPercentageRecommend()) {
            this.percentageRecommend.setVisibility(8);
            return;
        }
        this.percentageRecommend.setVisibility(0);
        SpannableStringBuilder percentageRecommendBuilder = new SpannableStringBuilder();
        SpannableString boldedPercentageRecommended = SpannableUtils.makeBoldedString((CharSequence) "优质房源", getContext());
        String formattedRecommendataionText = String.format(" %d%%房客推荐", new Object[]{Integer.valueOf(pricingQuote.getP3PercentageRecommended())});
        percentageRecommendBuilder.append(boldedPercentageRecommended);
        percentageRecommendBuilder.append(formattedRecommendataionText);
        this.percentageRecommend.setText(percentageRecommendBuilder);
    }

    private void setReviewsText(CharSequence reviewsText) {
        ViewLibUtils.setVisibleIf(this.numReviewsView, !TextUtils.isEmpty(reviewsText));
        this.numReviewsView.setText(reviewsText);
    }

    public static String getFormattedPriceText(Context context, String priceTag, PricingQuote pricingQuote) {
        RateType rateType = pricingQuote.getRateType();
        if (SearchPricingUtil.isTotalPricingEnabled() && pricingQuote.hasTotalPrice()) {
            return context.getString(C7532R.string.book_button_price_total, new Object[]{priceTag});
        } else if (rateType == RateType.Nightly) {
            return context.getString(C7532R.string.book_button_price_per_night, new Object[]{priceTag});
        } else if (rateType == RateType.Monthly) {
            return context.getString(C7532R.string.book_button_price_per_month, new Object[]{priceTag});
        } else {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Unsupported rate type: " + rateType));
            return priceTag;
        }
    }

    public void setReviewClickListener(OnClickListener reviewsClickListener) {
        this.ratingContainer.setOnClickListener(reviewsClickListener);
        this.ratingContainer.setClickable(reviewsClickListener != null);
    }

    public void setContactHostButtonListener(OnClickListener listener) {
        if (this.contactHostButton != null) {
            this.contactHostButton.setOnClickListener(listener);
        }
    }
}
