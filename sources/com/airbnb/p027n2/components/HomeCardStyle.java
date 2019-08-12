package com.airbnb.p027n2.components;

import android.content.res.Resources;
import android.view.ViewGroup.MarginLayoutParams;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.AirTextViewStyleApplier;
import com.airbnb.p027n2.primitives.wish_lists.WishListHeartStyle;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.LayoutParamsStyleApplier.Option;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.HomeCardStyle */
public enum HomeCardStyle {
    MICRO(1, WishListHeartStyle.SMALL, R.dimen.n2_vertical_padding_tiny, R.dimen.n2_vertical_padding_tiny_half, R.style.n2_SmallText, R.dimen.n2_mini_home_card_reviews_text_size),
    MINI(2, WishListHeartStyle.SMALL, R.dimen.n2_vertical_padding_tiny, R.dimen.n2_vertical_padding_tiny_half, R.style.n2_SmallText, R.dimen.n2_mini_home_card_reviews_text_size),
    FULL(2, WishListHeartStyle.MEDIUM, R.dimen.n2_vertical_padding_small, R.dimen.n2_vertical_padding_tiny_half, R.style.n2_RegularText, R.dimen.n2_full_home_card_reviews_text_size);
    
    private final int detailsTextStyle;
    private final int detailsToReviewsSpaceRes;
    private final int imageToDetailsSpaceRes;
    private final int numLines;
    private final int reviewsTextSizeRes;
    private final WishListHeartStyle wishListHeartStyle;

    private HomeCardStyle(int numLines2, WishListHeartStyle wishListHeartStyle2, int imageToDetailsSpaceRes2, int detailsToReviewsSpaceRes2, int detailsTextStyle2, int reviewsTextSizeRes2) {
        this.numLines = numLines2;
        this.wishListHeartStyle = wishListHeartStyle2;
        this.imageToDetailsSpaceRes = imageToDetailsSpaceRes2;
        this.detailsToReviewsSpaceRes = detailsToReviewsSpaceRes2;
        this.detailsTextStyle = detailsTextStyle2;
        this.reviewsTextSizeRes = reviewsTextSizeRes2;
    }

    public void updateView(HomeCard card) {
        boolean z = true;
        Resources r = card.getResources();
        card.detailsView.setMaxLines(this.numLines);
        AirTextView airTextView = card.detailsView;
        if (this.numLines != 1) {
            z = false;
        }
        airTextView.setSingleLine(z);
        ((AirTextViewStyleApplier) Paris.style(card.detailsView).addOption(Option.IgnoreLayoutWidthAndHeight)).apply(this.detailsTextStyle);
        ((AirTextViewStyleApplier) Paris.style(card.subtitleView).addOption(Option.IgnoreLayoutWidthAndHeight)).apply(this.detailsTextStyle);
        card.numReviewsView.setTextSize(0, (float) r.getDimensionPixelSize(this.reviewsTextSizeRes));
        ViewLibUtils.setPaddingTop(card.detailsContainer, r.getDimensionPixelSize(this.imageToDetailsSpaceRes));
        MarginLayoutParams mlp = (MarginLayoutParams) card.secondaryDetailsContainer.getLayoutParams();
        mlp.topMargin = r.getDimensionPixelSize(this.detailsToReviewsSpaceRes);
        card.secondaryDetailsContainer.setLayoutParams(mlp);
        card.setWishListHeartStyle(this.wishListHeartStyle);
    }
}
