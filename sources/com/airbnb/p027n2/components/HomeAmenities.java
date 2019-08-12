package com.airbnb.p027n2.components;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Space;
import android.widget.TextView;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.AccessibleDrawableResource;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.airbnb.n2.components.HomeAmenities */
public class HomeAmenities extends LinearLayout implements OnPreDrawListener, DividerView {
    private AirTextView aggregationText;
    @BindView
    LinearLayout amenityContainer;
    @BindView
    View divider;
    @BindDimen
    int iconSize;
    private List<AccessibleDrawableResource> items;
    private int maxItemsToShow = -1;
    @BindDimen
    int minSpacing;
    @BindView
    AirTextView title;

    public HomeAmenities(Context context) {
        super(context);
        init();
    }

    public HomeAmenities(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HomeAmenities(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        inflate(getContext(), R.layout.n2_home_amenities, this);
        ButterKnife.bind((View) this);
        getViewTreeObserver().addOnPreDrawListener(this);
    }

    public void setTitle(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.title, text);
    }

    public void setTitle(int stringId) {
        setTitle((CharSequence) getResources().getString(stringId));
    }

    public void setItems(List<AccessibleDrawableResource> items2) {
        if (items2.isEmpty()) {
            throw new IllegalStateException("Amenities list can't be empty");
        }
        this.items = items2;
        this.amenityContainer.removeAllViews();
        if (items2.size() < this.maxItemsToShow) {
            handleLessThanMaxItems(items2);
        } else {
            handleMoreThanOrEqualToMaxItems(items2);
        }
    }

    private void handleLessThanMaxItems(List<AccessibleDrawableResource> items2) {
        LayoutParams spaceParams = new LayoutParams(this.minSpacing, this.minSpacing);
        for (AccessibleDrawableResource item : items2) {
            this.amenityContainer.addView(inflateAndSetupView(item));
            this.amenityContainer.addView(new Space(getContext()), spaceParams);
        }
    }

    private void handleMoreThanOrEqualToMaxItems(List<AccessibleDrawableResource> items2) {
        LayoutParams spaceParams = new LayoutParams(0, -1);
        spaceParams.weight = 1.0f;
        Space space = null;
        for (int i = 0; i < this.maxItemsToShow; i++) {
            this.amenityContainer.addView(inflateAndSetupView((AccessibleDrawableResource) items2.get(i)));
            space = new Space(getContext());
            this.amenityContainer.addView(space, spaceParams);
        }
        if (this.maxItemsToShow == items2.size()) {
            this.amenityContainer.removeView(space);
            return;
        }
        this.aggregationText = (AirTextView) LayoutInflater.from(getContext()).inflate(R.layout.n2_listing_amenity_aggregation_item, this, false);
        String aggregate = String.valueOf(items2.size() - this.maxItemsToShow);
        this.aggregationText.setText(getResources().getString(R.string.n2_listing_amenities_aggregate, new Object[]{aggregate}));
        this.amenityContainer.addView(this.aggregationText);
    }

    private AirImageView inflateAndSetupView(AccessibleDrawableResource item) {
        AirImageView imageView = (AirImageView) LayoutInflater.from(getContext()).inflate(R.layout.n2_listing_amenity_item, this, false);
        imageView.setImageDrawableCompat(item.drawableResource());
        imageView.setContentDescription(item.contentDescription());
        imageView.setColorFilter(ContextCompat.getColor(getContext(), R.color.n2_foggy));
        return imageView;
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public boolean onPreDraw() {
        if (this.items == null || this.maxItemsToShow != -1) {
            return true;
        }
        getViewTreeObserver().removeOnPreDrawListener(this);
        int availableWidth = this.amenityContainer.getWidth() - this.aggregationText.getWidth();
        int itemWidth = this.iconSize + this.minSpacing;
        this.maxItemsToShow = availableWidth / itemWidth;
        int remainingSpace = availableWidth % itemWidth;
        if (this.maxItemsToShow == this.items.size() - 1 && this.aggregationText.getWidth() + remainingSpace >= this.iconSize) {
            this.maxItemsToShow++;
        }
        setItems(this.items);
        requestLayout();
        return false;
    }

    public static void mock(HomeAmenities row) {
        row.setTitle((CharSequence) "Amenities");
        row.setItems(mockGetItems(28));
    }

    public static void mockTwo(HomeAmenities row) {
        row.setItems(mockGetItems(2));
    }

    public static void mockFive(HomeAmenities row) {
        row.setItems(mockGetItems(5));
    }

    public static void mockTen(HomeAmenities row) {
        row.setItems(mockGetItems(10));
    }

    public static void mockHundred(HomeAmenities row) {
        row.setItems(mockGetItems(100));
    }

    public static void mockThousand(HomeAmenities row) {
        row.setItems(mockGetItems(1000));
    }

    private static List<AccessibleDrawableResource> mockGetItems(int itemCount) {
        List<AccessibleDrawableResource> items2 = new ArrayList<>(itemCount);
        for (int i = 0; i < itemCount; i++) {
            items2.add(AccessibleDrawableResource.create(R.drawable.n2_need_assets_from_design, "amenity " + i));
        }
        return items2;
    }
}
