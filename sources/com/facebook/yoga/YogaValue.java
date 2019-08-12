package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class YogaValue {
    static final YogaValue UNDEFINED = new YogaValue(Float.NaN, YogaUnit.UNDEFINED);
    static final YogaValue ZERO = new YogaValue(0.0f, YogaUnit.PIXEL);
    public final YogaUnit unit;
    public final float value;

    YogaValue(float value2, YogaUnit unit2) {
        this.value = value2;
        this.unit = unit2;
    }

    @DoNotStrip
    YogaValue(float value2, int unit2) {
        this(value2, YogaUnit.fromInt(unit2));
    }

    public boolean equals(Object other) {
        if (!(other instanceof YogaValue)) {
            return false;
        }
        YogaValue otherValue = (YogaValue) other;
        if (this.value == otherValue.value && this.unit == otherValue.unit) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.value) + this.unit.intValue();
    }
}
