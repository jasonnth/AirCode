package com.airbnb.p027n2.components;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.p000v4.content.ContextCompat;
import android.support.percent.PercentRelativeLayout;
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
import com.airbnb.p027n2.primitives.imaging.Image;
import com.airbnb.p027n2.primitives.imaging.ImageSize;
import com.airbnb.p027n2.primitives.wish_lists.WishListHeartInterface;
import com.airbnb.p027n2.primitives.wish_lists.WishListHeartStyle;
import com.airbnb.p027n2.transitions.TransitionName;
import com.airbnb.p027n2.utils.TextUtil;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.PosterCard */
public class PosterCard extends PercentRelativeLayout {
    @BindView
    View bottomSpace;
    @BindView
    View clickOverlay;
    @BindView
    View iconVisibilityGradient;
    @BindView
    AirTextView numReviewsView;
    @BindView
    AirImageView posterImage;
    @BindView
    AirTextView posterTag;
    @BindView
    AirTextView priceAndDescriptionText;
    @BindView
    RatingBar ratingBar;
    @BindView
    WishListIconView wishListHeart;

    public PosterCard(Context context) {
        super(context);
        init(null);
    }

    public PosterCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PosterCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_poster_card, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_PosterCard);
        showBottomSpace(ta.getBoolean(R.styleable.n2_PosterCard_n2_showDivider, true));
        this.posterImage.setPlaceholderResId(R.color.n2_loading_background);
        ta.recycle();
    }

    @TargetApi(21)
    public void setTransitionNames(String type, long id) {
        if (VERSION.SDK_INT >= 21) {
            this.posterImage.setTransitionName(TransitionName.toString(type, id, "coverPhoto"));
        }
    }

    public void setPriceAndDescriptionText(CharSequence price, CharSequence description, boolean isSocialGood) {
        ViewLibUtils.setVisibleIf(this.priceAndDescriptionText, !TextUtils.isEmpty(price) || !TextUtils.isEmpty(description));
        StringBuilder sb = new StringBuilder().append(price);
        if (isSocialGood) {
            sb.append(" ").append(AirmojiEnum.AIRMOJI_SOCIAL_IMPACT_RIBBON.character);
        }
        String priceString = sb.toString();
        sb.append("  ").append(description);
        this.priceAndDescriptionText.setText(TextUtil.makeCircularBold(getContext(), sb.toString(), priceString));
    }

    public void setImage(Image image) {
        if (image != null) {
            this.posterImage.setBackground(null);
            this.posterImage.setImageUrl(image.getUrlForSize(ImageSize.PortraitLarge));
            return;
        }
        this.posterImage.clear();
        this.posterImage.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.n2_loading_background));
    }

    public void setRating(float rating, int numReviews, CharSequence reviewsText) {
        boolean shouldShowReviews;
        boolean shouldShowStars = true;
        if (numReviews > 0) {
            shouldShowReviews = true;
        } else {
            shouldShowReviews = false;
        }
        if (rating <= 0.0f || numReviews < 3) {
            shouldShowStars = false;
        }
        ViewLibUtils.setVisibleIf(this.ratingBar, shouldShowStars);
        ViewLibUtils.setVisibleIf(this.numReviewsView, shouldShowReviews);
        this.ratingBar.setRating(rating);
        this.numReviewsView.setText(reviewsText);
    }

    public void showBottomSpace(boolean showBottomSpace) {
        ViewLibUtils.setVisibleIf(this.bottomSpace, showBottomSpace);
    }

    public void clearImages() {
        this.posterImage.clear();
    }

    public void setOnClickListener(OnClickListener l) {
        if (l == null) {
            this.clickOverlay.setOnClickListener(null);
            this.clickOverlay.setClickable(false);
            return;
        }
        this.clickOverlay.setOnClickListener(PosterCard$$Lambda$1.lambdaFactory$(this, l));
    }

    public void setWishListHeartInterface(WishListHeartInterface heartInterface) {
        this.iconVisibilityGradient.setVisibility(0);
        this.wishListHeart.setWishListInterface(heartInterface);
    }

    public void clearWishListHeartInterface() {
        this.iconVisibilityGradient.setVisibility(8);
        this.wishListHeart.clearWishListInterface();
    }

    public void setWishListHeartStyle(WishListHeartStyle wishListHeartStyle) {
        wishListHeartStyle.updateView(this.wishListHeart);
    }

    public void setPosterTag(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.posterTag, !TextUtils.isEmpty(text));
        this.posterTag.setText(text);
    }

    public static void mock(PosterCard view) {
        view.setPriceAndDescriptionText("Price", "Description", true);
        view.setRating(4.0f, 45, "45 reviews");
    }
}
