package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.view.ViewCompat;
import android.support.percent.PercentRelativeLayout;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.AirmojiEnum;
import com.airbnb.p027n2.primitives.WishListIconView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.wish_lists.WishListHeartInterface;
import com.airbnb.p027n2.primitives.wish_lists.WishListHeartStyle;
import com.airbnb.p027n2.transitions.TransitionName;
import com.airbnb.p027n2.utils.DebouncedOnClickListener;
import com.airbnb.p027n2.utils.TextUtil;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.facebook.internal.AnalyticsEvents;
import com.google.common.collect.Sets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/* renamed from: com.airbnb.n2.components.HomeCard */
public class HomeCard extends PercentRelativeLayout implements DividerView {
    @BindView
    View bottomSpace;
    @BindView
    View clickOverlay;
    @BindView
    LinearLayout detailsContainer;
    @BindView
    AirTextView detailsView;
    private CharSequence discountText;
    @BindView
    View divider;
    /* access modifiers changed from: private */
    public OnClickListener externalClickListener;
    private WishListHeartInterface heartInterface;
    private final HomeCardIconComparator iconComparator = new HomeCardIconComparator();
    @BindView
    View iconVisibilityGradient;
    private TreeSet<HomeCardIcon> icons = Sets.newTreeSet(this.iconComparator);
    @BindView
    AirImageView imageView;
    private final OnClickListener internalClickListener = new DebouncedOnClickListener() {
        public void onClickDebounced(View v) {
            if (HomeCard.this.externalClickListener != null) {
                HomeCard.this.externalClickListener.onClick(HomeCard.this);
            }
        }
    };
    private CharSequence listingTitle;
    @BindView
    AirTextView numReviewsView;
    private CharSequence priceText;
    private CharSequence rateType;
    @BindView
    RatingBar ratingBar;
    @BindView
    LinearLayout secondaryDetailsContainer;
    @BindView
    View selectionHighlight;
    private HomeCardStyle style;
    private CharSequence subtitleText;
    @BindView
    AirTextView subtitleView;
    @BindView
    WishListIconView wishListHeart;

    /* renamed from: com.airbnb.n2.components.HomeCard$HomeCardIcon */
    private enum HomeCardIcon {
        InstantBook(AirmojiEnum.AIRMOJI_CORE_INSTANTBOOK, 0),
        SuperHost(AirmojiEnum.AIRMOJI_CORE_SUPERHOST, 1),
        BusinessTravelReady(AirmojiEnum.AIRMOJI_CORE_BUSINESS_TRAVEL_READY, 2);
        
        final AirmojiEnum airmoji;
        final int weight;

        private HomeCardIcon(AirmojiEnum airmoji2, int weight2) {
            this.airmoji = airmoji2;
            this.weight = weight2;
        }
    }

    /* renamed from: com.airbnb.n2.components.HomeCard$HomeCardIconComparator */
    private class HomeCardIconComparator implements Comparator<HomeCardIcon> {
        private HomeCardIconComparator() {
        }

        public int compare(HomeCardIcon o1, HomeCardIcon o2) {
            return o1.weight - o2.weight;
        }
    }

    public HomeCard(Context context) {
        super(context);
        init(null);
    }

    public HomeCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public HomeCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_home_card, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        this.clickOverlay.setOnClickListener(this.internalClickListener);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_HomeCard, 0, 0);
        boolean showBottomSpace = ta.getBoolean(R.styleable.n2_HomeCard_n2_showDivider, true);
        boolean showDivider = ta.getBoolean(R.styleable.n2_HomeCard_n2_showDivider, true);
        showBottomSpace(showBottomSpace);
        showDivider(showDivider);
        setStyle(HomeCardStyle.FULL);
        ta.recycle();
    }

    public void setStyle(HomeCardStyle style2) {
        this.style = style2;
        style2.updateView(this);
        updateDetails();
    }

    public void setWishListHeartStyle(WishListHeartStyle wishListHeartStyle) {
        wishListHeartStyle.updateView(this.wishListHeart);
    }

    public void setSubtitle(CharSequence subtitleText2) {
        this.subtitleText = subtitleText2;
        updateDetails();
    }

    public void setDiscountText(CharSequence discountText2) {
        this.discountText = discountText2;
        updateDetails();
    }

    public void setRating(float rating, int numReviews) {
        ViewLibUtils.setVisibleIf(this.ratingBar, numReviews >= 3);
        this.ratingBar.setRating(rating);
    }

    public void setReviewsText(CharSequence reviewsText) {
        ViewLibUtils.setVisibleIf(this.numReviewsView, !TextUtils.isEmpty(reviewsText));
        this.numReviewsView.setText(reviewsText);
    }

    public void setListingTitleText(CharSequence listingTitle2) {
        this.listingTitle = listingTitle2;
    }

    public void setPriceText(CharSequence priceText2, CharSequence rateType2) {
        this.priceText = priceText2;
        this.rateType = rateType2;
        updateDetails();
    }

    public void setInstantBookAvailable(boolean instantBookAvailable) {
        updateIcon(instantBookAvailable, HomeCardIcon.InstantBook);
        updateDetails();
    }

    public void setIsBusinessTravelReady(boolean isBusinessTravelReady) {
        updateIcon(isBusinessTravelReady, HomeCardIcon.BusinessTravelReady);
        updateDetails();
    }

    public void setListingImageUrl(String url) {
        this.imageView.setImageUrl(url);
    }

    public void setListingUrlWithBlurredPreview(String url, String encodedImageHash) {
        this.imageView.setImageUrlWithBlurredPreview(url, encodedImageHash);
    }

    public void setIsSuperhost(boolean isSuperhost) {
        updateIcon(isSuperhost, HomeCardIcon.SuperHost);
        updateDetails();
    }

    private void updateIcon(boolean hasIcon, HomeCardIcon iconToUpdate) {
        if (hasIcon) {
            this.icons.add(iconToUpdate);
        } else {
            this.icons.remove(iconToUpdate);
        }
    }

    public void showBottomSpace(boolean showBottomSpace) {
        ViewLibUtils.setVisibleIf(this.bottomSpace, showBottomSpace);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public void setWishListHeartInterface(WishListHeartInterface heartInterface2) {
        this.heartInterface = heartInterface2;
        this.iconVisibilityGradient.setVisibility(0);
        this.wishListHeart.setWishListInterface(heartInterface2);
    }

    public void clearWishListHeartInterface() {
        this.iconVisibilityGradient.setVisibility(8);
        this.wishListHeart.clearWishListInterface();
    }

    public void setTransitionTypeId(long id) {
        ViewCompat.setTransitionName(this.wishListHeart, TransitionName.toString("listing", id, "wishListHeart"));
        ViewCompat.setTransitionName(this.imageView, TransitionName.toString("listing", id, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO, 0));
    }

    public void clearImages() {
        this.imageView.clear();
    }

    private void updateDetails() {
        boolean hasDiscountText;
        boolean hasListingTitleText;
        boolean isMicroStyle;
        if (TextUtils.isEmpty(this.subtitleText)) {
            this.detailsView.setVisibility(8);
            this.subtitleView.setVisibility(8);
            return;
        }
        this.detailsView.setVisibility(0);
        this.subtitleView.setVisibility(0);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        if (!TextUtils.isEmpty(this.priceText)) {
            CharSequence priceAndRate = this.priceText;
            if (!TextUtils.isEmpty(this.rateType)) {
                priceAndRate = priceAndRate + " " + this.rateType;
            }
            builder.append(TextUtil.makeCircularBold(getContext(), priceAndRate));
        }
        if (areIconsEnabled()) {
            builder.append(TextUtil.makeCircularBold(getContext(), getIcons()));
        }
        if (builder.length() > 0) {
            builder.append("  ");
        }
        if (!TextUtils.isEmpty(this.discountText)) {
            hasDiscountText = true;
        } else {
            hasDiscountText = false;
        }
        if (!TextUtils.isEmpty(this.listingTitle)) {
            hasListingTitleText = true;
        } else {
            hasListingTitleText = false;
        }
        if (this.style == HomeCardStyle.MICRO) {
            isMicroStyle = true;
        } else {
            isMicroStyle = false;
        }
        if (hasDiscountText && !hasListingTitleText && !isMicroStyle) {
            builder.append(TextUtil.makeCircularBold(getContext(), "·  " + this.discountText));
            this.subtitleView.setText(this.subtitleText);
            this.detailsView.setMaxLines(1);
        } else if (!hasListingTitleText || isMicroStyle) {
            builder.append(this.subtitleText);
            this.subtitleView.setVisibility(8);
            this.detailsView.setMaxLines(2);
        } else {
            builder.append(TextUtil.makeCircularBold(getContext(), this.listingTitle));
            this.subtitleView.setText(this.subtitleText);
            this.detailsView.setMaxLines(1);
            if (hasDiscountText) {
                this.subtitleView.setText(this.discountText + " · " + this.subtitleText);
            } else {
                this.subtitleView.setText(this.subtitleText);
            }
        }
        this.detailsView.setText(builder);
    }

    private boolean areIconsEnabled() {
        return this.style == HomeCardStyle.FULL;
    }

    public void setOnClickListener(OnClickListener l) {
        if (l != this.externalClickListener) {
            this.externalClickListener = l;
            this.clickOverlay.setClickable(l != null);
        }
    }

    public void showSelectionHighlight(boolean showSelectionHighlight) {
        ViewLibUtils.setVisibleIf(this.selectionHighlight, showSelectionHighlight);
    }

    private String getIcons() {
        StringBuilder builder = new StringBuilder();
        if (!this.icons.isEmpty()) {
            if (this.icons.size() > 1) {
                builder.append(" ");
            }
            Iterator it = this.icons.iterator();
            while (it.hasNext()) {
                builder.append(((HomeCardIcon) it.next()).airmoji.character);
                builder.append(AirmojiEnum.HAIRSPACE.character);
            }
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public static void mockDefault(HomeCard card) {
        card.setPriceText("$555", "");
        card.setSubtitle("Entire home in Haiwaii");
        card.setRating(4.0f, 45);
        card.setReviewsText("45 Reviews");
        card.setOnClickListener(HomeCard$$Lambda$1.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockDefault$0(View view) {
    }

    public static void mock2Lines(HomeCard card) {
        mockDefault(card);
        card.setSubtitle("Entire home in Haiwaii this wraps on 2 lines then ellipsizes when it's very long");
    }

    public static void mockInstantBookSuperhost(HomeCard card) {
        mockDefault(card);
        card.setInstantBookAvailable(true);
        card.setIsSuperhost(true);
    }

    public static void mockInstantBook(HomeCard card) {
        mockDefault(card);
        card.setInstantBookAvailable(true);
    }

    public static void mockSuperhost(HomeCard card) {
        mockDefault(card);
        card.setIsSuperhost(true);
    }

    public static void mockNoReviewsNewHome(HomeCard card) {
        card.setPriceText("$555", "");
        card.setSubtitle("Entire home in Haiwaii");
        card.setReviewsText("New Home");
        card.setOnClickListener(HomeCard$$Lambda$2.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockNoReviewsNewHome$1(View view) {
    }

    public static void mockNoReviews(HomeCard card) {
        card.setPriceText("$555", "");
        card.setSubtitle("Entire home in Haiwaii");
        card.setOnClickListener(HomeCard$$Lambda$3.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockNoReviews$2(View view) {
    }

    public static void mockMini(HomeCard card) {
        mockDefault(card);
        card.setSubtitle("Entire home in Haiwaii this wraps on 2 lines then ellipsizes when it's very long");
        card.setStyle(HomeCardStyle.MINI);
    }

    public static void mockMicro(HomeCard card) {
        mockDefault(card);
        card.setSubtitle("Entire home in Haiwaii this doesn't wrap");
        card.setStyle(HomeCardStyle.MICRO);
    }

    public static void mockListingTitleFull(HomeCard card) {
        mockDefault(card);
        card.setListingTitleText("Beautiful home with great ocean view");
        card.setSubtitle("Entire home in Haiwaii this doesn't wrap even when long");
    }

    public static void mockListingTitleMini(HomeCard card) {
        mockDefault(card);
        card.setListingTitleText("Beautiful home with great ocean view");
        card.setSubtitle("Entire home in Haiwaii");
        card.setStyle(HomeCardStyle.MINI);
    }

    public static void mockListingTitleMicro(HomeCard card) {
        mockDefault(card);
        card.setListingTitleText("Beautiful home with great ocean view");
        card.setSubtitle("Entire home in Haiwaii");
        card.setStyle(HomeCardStyle.MICRO);
    }
}
