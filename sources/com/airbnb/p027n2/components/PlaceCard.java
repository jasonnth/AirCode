package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.WishListIconView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.Image;
import com.airbnb.p027n2.primitives.imaging.ImageSize;
import com.airbnb.p027n2.primitives.wish_lists.WishListHeartInterface;
import com.airbnb.p027n2.primitives.wish_lists.WishListHeartStyle;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.PlaceCard */
public class PlaceCard extends LinearLayout {
    @BindView
    View bottomSpace;
    @BindView
    View cardDetails;
    @BindView
    AirTextView cardTag;
    @BindView
    AirTextView cardTitle;
    @BindView
    View iconVisibilityGradient;
    @BindView
    AirImageView imageView;
    @BindView
    View selectionHighlight;
    @BindView
    AirTextView titleTextView;
    @BindView
    WishListIconView wishListIcon;

    public PlaceCard(Context context) {
        super(context);
        init();
    }

    public PlaceCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlaceCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        inflate(getContext(), R.layout.n2_place_card, this);
        ButterKnife.bind((View) this);
    }

    public void setImage(Image image) {
        this.imageView.setImageUrl(image == null ? null : image.getUrlForSize(ImageSize.LandscapeLarge));
    }

    public void clearImage() {
        this.imageView.clear();
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [android.text.Spannable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setupTitle(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            r3 = 1
            r1 = 0
            boolean r4 = android.text.TextUtils.isEmpty(r6)
            if (r4 != 0) goto L_0x0020
            r0 = r3
        L_0x0009:
            if (r0 != 0) goto L_0x0011
            boolean r4 = android.text.TextUtils.isEmpty(r7)
            if (r4 != 0) goto L_0x0012
        L_0x0011:
            r1 = r3
        L_0x0012:
            com.airbnb.n2.primitives.AirTextView r3 = r5.titleTextView
            com.airbnb.p027n2.utils.ViewLibUtils.setVisibleIf(r3, r1)
            if (r1 != 0) goto L_0x0022
            com.airbnb.n2.primitives.AirTextView r3 = r5.titleTextView
            r4 = 0
            r3.setText(r4)
        L_0x001f:
            return
        L_0x0020:
            r0 = r1
            goto L_0x0009
        L_0x0022:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r6)
            java.lang.String r4 = " "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r7)
            java.lang.String r2 = r3.toString()
            com.airbnb.n2.primitives.AirTextView r3 = r5.titleTextView
            if (r0 == 0) goto L_0x0042
            android.content.Context r4 = r5.getContext()
            android.text.Spannable r2 = com.airbnb.p027n2.utils.TextUtil.makeCircularBold(r4, r2, r6)
        L_0x0042:
            r3.setText(r2)
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.p027n2.components.PlaceCard.setupTitle(java.lang.String, java.lang.String):void");
    }

    public void setWishListInterface(WishListHeartInterface heartInterface) {
        this.iconVisibilityGradient.setVisibility(0);
        this.wishListIcon.setWishListInterface(heartInterface);
        this.wishListIcon.setVisibility(0);
    }

    public void clearWishListInterface() {
        this.iconVisibilityGradient.setVisibility(8);
        this.wishListIcon.clearWishListInterface();
        this.wishListIcon.setVisibility(8);
    }

    public void setWishListHeartStyle(WishListHeartStyle wishListHeartStyle) {
        wishListHeartStyle.updateView(this.wishListIcon);
    }

    public void showSelectionHighlight(boolean showSelectionHighlight) {
        ViewLibUtils.setVisibleIf(this.selectionHighlight, showSelectionHighlight);
    }

    public void showBottomSpace(boolean showBottomSpace) {
        ViewLibUtils.setVisibleIf(this.bottomSpace, showBottomSpace);
    }

    public void setCardDetails(String title, String tag) {
        ViewLibUtils.setGoneIf(this.cardDetails, TextUtils.isEmpty(title));
        ViewLibUtils.setGoneIf(this.cardTag, TextUtils.isEmpty(tag));
        this.cardTitle.setText(title);
        this.cardTag.setText(tag);
    }

    public static void mock(PlaceCard card) {
        card.setupTitle("Title", "Subtitle");
    }
}
