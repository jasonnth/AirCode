package com.airbnb.p027n2.epoxy;

import android.content.Context;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.epoxy.NumCarouselItemsShown */
public class NumCarouselItemsShown {
    private final float forPhone;
    private final float forTablet;
    private final float forWideTablet;

    public NumCarouselItemsShown(float forPhone2, float forTablet2, float forWideTablet2) {
        this.forPhone = forPhone2;
        this.forTablet = forTablet2;
        this.forWideTablet = forWideTablet2;
    }

    public static NumCarouselItemsShown forPhoneWithDefaultScaling(float numItemsToShowOnPhone) {
        return new NumCarouselItemsShown(numItemsToShowOnPhone, 1.5f * numItemsToShowOnPhone, 2.0f * numItemsToShowOnPhone);
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (!(o instanceof NumCarouselItemsShown)) {
            return false;
        }
        NumCarouselItemsShown that = (NumCarouselItemsShown) o;
        if (Float.compare(that.forPhone, this.forPhone) != 0 || Float.compare(that.forTablet, this.forTablet) != 0) {
            return false;
        }
        if (Float.compare(that.forWideTablet, this.forWideTablet) != 0) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int result;
        int i;
        int i2 = 0;
        if (this.forPhone != 0.0f) {
            result = Float.floatToIntBits(this.forPhone);
        } else {
            result = 0;
        }
        int i3 = result * 31;
        if (this.forTablet != 0.0f) {
            i = Float.floatToIntBits(this.forTablet);
        } else {
            i = 0;
        }
        int i4 = (i3 + i) * 31;
        if (this.forWideTablet != 0.0f) {
            i2 = Float.floatToIntBits(this.forWideTablet);
        }
        return i4 + i2;
    }

    public float forCurrentScreen(Context context) {
        if (ViewLibUtils.isWideTabletScreen(context)) {
            return this.forWideTablet;
        }
        return ViewLibUtils.isTabletScreen(context) ? this.forTablet : this.forPhone;
    }
}
