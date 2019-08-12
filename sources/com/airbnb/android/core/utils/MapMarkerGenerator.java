package com.airbnb.android.core.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.p000v4.util.ArrayMap;
import android.support.p002v7.content.res.AppCompatResources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.beta.models.guidebook.LocalAttraction;
import com.airbnb.android.core.beta.models.guidebook.LocalAttraction.GuidebookPin;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class MapMarkerGenerator {
    private final Drawable btrBriefcaseFaded;
    private final Drawable btrBriefcaseHighlighted;
    private final ImageView btrBriefcaseImage;
    private final ArrayMap<Integer, Drawable> drawableCache = new ArrayMap<>();
    private final Drawable ibBoltFaded;
    private final Drawable ibBoltHighlighted;
    private final ImageView ibBoltImage;
    private final int markerTextColor;
    private final int markerTextColorHighlighted;
    private final View markerView;
    private final int markerViewedTextColor;
    private final TextView priceTextView;
    private final Drawable wlHeartHighlighted;
    private final ImageView wlHeartImage;

    public MapMarkerGenerator(Context context) {
        this.markerView = LayoutInflater.from(context).inflate(C0716R.layout.map_marker, null);
        this.priceTextView = (TextView) ViewLibUtils.findById(this.markerView, C0716R.C0718id.price);
        this.btrBriefcaseImage = (ImageView) ViewLibUtils.findById(this.markerView, C0716R.C0718id.btr_briefcase_icon);
        this.ibBoltImage = (ImageView) ViewLibUtils.findById(this.markerView, C0716R.C0718id.ib_bolt_icon);
        this.wlHeartImage = (ImageView) ViewLibUtils.findById(this.markerView, C0716R.C0718id.wl_heart_icon);
        Resources res = context.getResources();
        this.markerTextColor = res.getColor(C0716R.color.black);
        this.markerTextColorHighlighted = res.getColor(C0716R.color.white);
        this.markerViewedTextColor = res.getColor(C0716R.color.n2_map_marker_viewed_text_color);
        this.ibBoltFaded = ColorizedDrawable.forIdWithColor(context, C0716R.C0717drawable.ic_ib_bolt_map, C0716R.color.n2_beach_35);
        this.btrBriefcaseFaded = ColorizedDrawable.forIdWithColor(context, C0716R.C0717drawable.ic_btr_briefcase_map, C0716R.color.n2_map_marker_viewed_text_color);
        this.btrBriefcaseHighlighted = ColorizedDrawable.forIdWithColor(context, C0716R.C0717drawable.ic_btr_briefcase_map, C0716R.color.white);
        this.ibBoltHighlighted = ColorizedDrawable.forIdWithColor(context, C0716R.C0717drawable.ic_ib_bolt_map, C0716R.color.white);
        this.wlHeartHighlighted = ColorizedDrawable.forIdWithColor(context, C0716R.C0717drawable.ic_wl_heart_map, C0716R.color.white);
    }

    public Bitmap getPriceMarker(Context context, String price, boolean isViewed, boolean isInstantBookable, boolean isWishListed, boolean isHighlighted) {
        return getPriceMarker(context, price, isViewed, isInstantBookable, isWishListed, isHighlighted, false);
    }

    public Bitmap getPriceMarker(Context context, String price, boolean isViewed, boolean isInstantBookable, boolean isWishListed, boolean isHighlighted, boolean isBusinessTravelReady) {
        boolean showFaded = isViewed && !isWishListed;
        this.markerView.setBackground(getDrawableWithCaching(context, isHighlighted ? C0716R.C0717drawable.marker_babu : C0716R.C0717drawable.marker_white));
        TextView textView = this.priceTextView;
        int i = isHighlighted ? this.markerTextColorHighlighted : showFaded ? this.markerViewedTextColor : this.markerTextColor;
        textView.setTextColor(i);
        this.priceTextView.setText(MiscUtils.makeCurrencyTextSmall(context, price));
        ViewLibUtils.setVisibleIf(this.btrBriefcaseImage, isBusinessTravelReady);
        Drawable btrBriefcase = getDrawableWithCaching(context, C0716R.C0717drawable.ic_btr_briefcase_map);
        ImageView imageView = this.btrBriefcaseImage;
        if (isHighlighted) {
            btrBriefcase = this.btrBriefcaseHighlighted;
        } else if (showFaded) {
            btrBriefcase = this.btrBriefcaseFaded;
        }
        imageView.setImageDrawable(btrBriefcase);
        ViewLibUtils.setVisibleIf(this.ibBoltImage, isInstantBookable);
        Drawable ibBolt = getDrawableWithCaching(context, C0716R.C0717drawable.ic_ib_bolt_map);
        ImageView imageView2 = this.ibBoltImage;
        if (isHighlighted) {
            ibBolt = this.ibBoltHighlighted;
        } else if (showFaded) {
            ibBolt = this.ibBoltFaded;
        }
        imageView2.setImageDrawable(ibBolt);
        ViewLibUtils.setVisibleIf(this.wlHeartImage, isWishListed);
        Drawable wlHeart = getDrawableWithCaching(context, C0716R.C0717drawable.ic_wl_heart_map);
        ImageView imageView3 = this.wlHeartImage;
        if (isHighlighted) {
            wlHeart = this.wlHeartHighlighted;
        }
        imageView3.setImageDrawable(wlHeart);
        return ViewUtils.getBitmapFromUnmeasuredView(this.markerView);
    }

    public Drawable getDrawableWithCaching(Context context, int drawableRes) {
        if (!this.drawableCache.containsKey(Integer.valueOf(drawableRes))) {
            this.drawableCache.put(Integer.valueOf(drawableRes), AppCompatResources.getDrawable(context, drawableRes));
        }
        return (Drawable) this.drawableCache.get(Integer.valueOf(drawableRes));
    }

    public static Bitmap getLocalAttractionMarker(Context context, LocalAttraction attraction, boolean selected) {
        GuidebookPin pin = GuidebookPin.getGuidebookPin(attraction.getPin());
        return BitmapFactory.decodeResource(context.getResources(), selected ? pin.getResourceIdLarge() : pin.getResourceIdSmall());
    }
}
