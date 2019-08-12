package com.airbnb.p027n2.components;

import android.content.Context;
import android.support.p000v4.view.ViewCompat;
import android.support.percent.PercentRelativeLayout;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RatingBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.AirmojiEnum;
import com.airbnb.p027n2.primitives.WishListIconView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
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

/* renamed from: com.airbnb.n2.components.HomeCardChina */
public class HomeCardChina extends PercentRelativeLayout {
    @BindView
    AirTextView bookingDetailsView;
    @BindView
    View clickOverlay;
    /* access modifiers changed from: private */
    public OnClickListener externalClickListener;
    @BindView
    AirTextView firstTag;
    @BindView
    HaloImageView hostAvatar;
    @BindView
    AirImageView hostBadge;
    private final HomeCardIconComparator iconComparator = new HomeCardIconComparator();
    @BindView
    View iconVisibilityGradient;
    private TreeSet<HomeCardIcon> icons = Sets.newTreeSet(this.iconComparator);
    @BindView
    AirImageView imageView;
    private final OnClickListener internalClickListener = new DebouncedOnClickListener() {
        public void onClickDebounced(View v) {
            if (HomeCardChina.this.externalClickListener != null) {
                HomeCardChina.this.externalClickListener.onClick(HomeCardChina.this);
            }
        }
    };
    @BindView
    AirTextView listingDetailsView;
    @BindView
    AirTextView numReviewsView;
    private CharSequence priceText;
    private CharSequence rateType;
    @BindView
    RatingBar ratingBar;
    @BindView
    AirTextView secondTag;
    @BindView
    AirTextView thirdTag;
    @BindView
    AirTextView titleView;
    @BindView
    WishListIconView wishListHeart;

    /* renamed from: com.airbnb.n2.components.HomeCardChina$HomeCardIcon */
    private enum HomeCardIcon {
        InstantBook(AirmojiEnum.AIRMOJI_CORE_INSTANTBOOK, 0);
        
        final AirmojiEnum airmoji;
        final int weight;

        private HomeCardIcon(AirmojiEnum airmoji2, int weight2) {
            this.airmoji = airmoji2;
            this.weight = weight2;
        }
    }

    /* renamed from: com.airbnb.n2.components.HomeCardChina$HomeCardIconComparator */
    private class HomeCardIconComparator implements Comparator<HomeCardIcon> {
        private HomeCardIconComparator() {
        }

        public int compare(HomeCardIcon o1, HomeCardIcon o2) {
            return o1.weight - o2.weight;
        }
    }

    public HomeCardChina(Context context) {
        super(context);
        init();
    }

    public HomeCardChina(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HomeCardChina(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_home_card_china, this);
        ButterKnife.bind((View) this);
        this.imageView.setPlaceholderResId(R.color.n2_loading_background);
        this.clickOverlay.setOnClickListener(this.internalClickListener);
        WishListHeartStyle.MEDIUM.updateView(this.wishListHeart);
    }

    public void setListingTitleText(CharSequence listingTitle) {
        ViewLibUtils.setVisibleIf(this.titleView, !TextUtils.isEmpty(listingTitle));
        this.titleView.setText(listingTitle);
    }

    public void setRating(float rating, int numReviews) {
        ViewLibUtils.setVisibleIf(this.ratingBar, numReviews >= 3);
        this.ratingBar.setRating(rating);
    }

    public void setReviewsText(CharSequence reviewsText) {
        ViewLibUtils.setVisibleIf(this.numReviewsView, !TextUtils.isEmpty(reviewsText));
        this.numReviewsView.setText(reviewsText);
    }

    public void setPriceText(CharSequence priceText2, CharSequence rateType2) {
        this.priceText = priceText2;
        this.rateType = rateType2;
        updateBookingDetails();
    }

    public void setInstantBookAvailable(boolean instantBookAvailable) {
        updateIcon(instantBookAvailable, HomeCardIcon.InstantBook);
        updateBookingDetails();
    }

    public void setHostAvatar(String avatarUrl, int badgeIcon) {
        this.hostAvatar.setImageUrl(avatarUrl);
        ViewLibUtils.setVisibleIf(this.hostBadge, badgeIcon != 0);
        if (badgeIcon != 0) {
            this.hostBadge.setImageResource(badgeIcon);
        }
    }

    public void setFirstTagText(int stringId) {
        ViewLibUtils.setVisibleIf(this.firstTag, stringId != 0);
        if (stringId != 0) {
            this.firstTag.setText(stringId);
        }
    }

    public void setSecondTagText(int stringId) {
        ViewLibUtils.setVisibleIf(this.secondTag, stringId != 0);
        if (stringId != 0) {
            this.secondTag.setText(stringId);
        }
    }

    public void setThirdTagText(int stringId) {
        ViewLibUtils.setVisibleIf(this.thirdTag, stringId != 0);
        if (stringId != 0) {
            this.thirdTag.setText(stringId);
        }
    }

    public void setListingDetails(String detailsText, int amenityIcon) {
        this.listingDetailsView.setText(detailsText);
        if (amenityIcon != 0) {
            this.listingDetailsView.append(" · ");
        }
        this.listingDetailsView.setCompoundDrawablesWithIntrinsicBounds(0, 0, amenityIcon, 0);
    }

    public void setListingImageUrl(String url) {
        this.imageView.setImageUrl(url);
    }

    public void setListingUrlWithBlurredPreview(String url, String encodedImageHash) {
        this.imageView.setImageUrlWithBlurredPreview(url, encodedImageHash);
    }

    private void updateIcon(boolean hasIcon, HomeCardIcon iconToUpdate) {
        if (hasIcon) {
            this.icons.add(iconToUpdate);
        } else {
            this.icons.remove(iconToUpdate);
        }
    }

    public void setWishListHeartInterface(WishListHeartInterface heartInterface) {
        this.iconVisibilityGradient.setVisibility(0);
        this.wishListHeart.setWishListInterface(heartInterface);
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
        this.hostAvatar.clear();
    }

    public void clearTags() {
        this.firstTag.setVisibility(8);
        this.secondTag.setVisibility(8);
    }

    public void setOnClickListener(OnClickListener l) {
        if (l != this.externalClickListener) {
            this.externalClickListener = l;
            this.clickOverlay.setClickable(l != null);
        }
    }

    private void updateBookingDetails() {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        if (!TextUtils.isEmpty(this.priceText)) {
            CharSequence priceAndRate = this.priceText;
            if (!TextUtils.isEmpty(this.rateType)) {
                priceAndRate = priceAndRate + " " + this.rateType;
            }
            builder.append(TextUtil.makeCircularBold(getContext(), priceAndRate));
        }
        builder.append(TextUtil.makeCircularBold(getContext(), (CharSequence) getIcons(), R.color.n2_beach));
        this.bookingDetailsView.setText(builder);
    }

    private String getIcons() {
        StringBuilder builder = new StringBuilder();
        if (!this.icons.isEmpty()) {
            Iterator it = this.icons.iterator();
            while (it.hasNext()) {
                HomeCardIcon icon = (HomeCardIcon) it.next();
                builder.append("  ");
                builder.append(icon.airmoji.character);
            }
        }
        return builder.toString();
    }

    public static void mockDefault(HomeCardChina card) {
        card.setPriceText("$555", "");
        card.setRating(4.0f, 45);
        card.setReviewsText("45 Reviews");
        card.setOnClickListener(HomeCardChina$$Lambda$1.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockDefault$0(View view) {
    }

    public static void mockInstantBookSuperhost(HomeCardChina card) {
        mockDefault(card);
        card.setInstantBookAvailable(true);
    }

    public static void mockInstantBook(HomeCardChina card) {
        mockDefault(card);
        card.setInstantBookAvailable(true);
    }

    public static void mockSuperhost(HomeCardChina card) {
        mockDefault(card);
    }

    public static void mockNoReviewsNewHome(HomeCardChina card) {
        card.setPriceText("$555", "");
        card.setReviewsText("New Home");
        card.setOnClickListener(HomeCardChina$$Lambda$2.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockNoReviewsNewHome$1(View view) {
    }

    public static void mockNoReviews(HomeCardChina card) {
        card.setPriceText("$555", "");
        card.setOnClickListener(HomeCardChina$$Lambda$3.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockNoReviews$2(View view) {
    }
}
