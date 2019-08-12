package com.airbnb.android.insights;

import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class ListingInsightsView extends PercentRelativeLayout {
    @BindView
    AirTextView descriptionView;
    @BindView
    AirImageView listingImage;
    @BindDimen
    int peekWidth;
    @BindView
    AirTextView titleView;

    public ListingInsightsView(Context context) {
        super(context);
        init();
    }

    public ListingInsightsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ListingInsightsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C6552R.layout.listing_insights_view, this);
        ButterKnife.bind((View) this);
        setClickable(true);
    }

    public void setTitle(CharSequence title) {
        ViewLibUtils.setGoneIf(this.titleView, TextUtils.isEmpty(title));
        this.titleView.setText(title);
    }

    public void setDescription(CharSequence description) {
        ViewLibUtils.setGoneIf(this.descriptionView, TextUtils.isEmpty(description));
        this.descriptionView.setText(description);
    }

    public void setListingImage(String imageUrl) {
        this.listingImage.setImageUrl(imageUrl);
    }
}
