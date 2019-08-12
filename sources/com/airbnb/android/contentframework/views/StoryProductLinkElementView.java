package com.airbnb.android.contentframework.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class StoryProductLinkElementView extends LinearLayout {
    @BindView
    AirTextView header;
    @BindView
    RatingBar ratingBar;
    @BindView
    AirTextView subtitle;
    @BindView
    AirImageView thumbnailImage;
    @BindView
    AirTextView title;
    @BindView
    View topDivider;
    @BindView
    AirImageView wishlistButton;

    public StoryProductLinkElementView(Context context) {
        super(context);
        init(context);
    }

    public StoryProductLinkElementView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StoryProductLinkElementView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(C5709R.layout.story_product_link_element_view, this);
        setOrientation(1);
        ButterKnife.bind(this, view);
    }

    public void setOptionalHeader(String headerText) {
        ViewLibUtils.setVisibleIf(this.header, !TextUtils.isEmpty(headerText));
        this.header.setText(headerText);
    }

    public void setThumbnailImageUrl(String url) {
        this.thumbnailImage.setImageUrl(url);
    }

    public void setTitle(String title2) {
        this.title.setText(title2);
    }

    public void setStarRating(float rating) {
        ViewLibUtils.setVisibleIf(this.ratingBar, rating > 0.0f);
        this.ratingBar.setRating(rating);
    }

    public void showTopDivider(boolean showTopDivider) {
        ViewLibUtils.setVisibleIf(this.topDivider, showTopDivider);
    }

    public void setSubtitle(String subtitle2) {
        ViewLibUtils.setVisibleIf(this.subtitle, !TextUtils.isEmpty(subtitle2));
        this.subtitle.setText(subtitle2);
    }

    public void setWishlisted(boolean wishlisted) {
        this.wishlistButton.setImageResource(wishlisted ? C5709R.C5710drawable.product_link_wishlist_wishlisted : C5709R.C5710drawable.product_link_wishlist_unwishlisted);
    }

    public void setOnClickWishlistListener(OnClickListener onClickWishlistListener) {
        this.wishlistButton.setOnClickListener(onClickWishlistListener);
    }
}
