package com.airbnb.epoxy;

import android.content.Context;
import java.util.Arrays;

public class StringAttributeData {
    private final CharSequence defaultString;
    private Object[] formatArgs;
    private final boolean hasDefault;
    private int pluralRes;
    private int quantity;
    private CharSequence string;
    private int stringRes;

    public StringAttributeData() {
        this.hasDefault = false;
        this.defaultString = null;
    }

    public StringAttributeData(String defaultString2) {
        this.hasDefault = true;
        this.defaultString = defaultString2;
        this.string = defaultString2;
    }

    public void setValue(CharSequence string2) {
        this.string = string2;
        this.stringRes = 0;
        this.pluralRes = 0;
    }

    public void setValue(int stringRes2) {
        setValue(stringRes2, null);
    }

    public void setValue(int stringRes2, Object[] formatArgs2) {
        if (stringRes2 > 0) {
            this.stringRes = stringRes2;
            this.formatArgs = formatArgs2;
            this.string = null;
            this.pluralRes = 0;
            return;
        }
        handleInvalidStringRes(stringRes2);
    }

    private void handleInvalidStringRes(int stringRes2) {
        if (stringRes2 != 0) {
            throw new IllegalArgumentException("String resource cannot be negative: " + stringRes2);
        } else if (this.hasDefault) {
            setValue(this.defaultString);
        } else {
            throw new IllegalArgumentException("0 is an invalid value for required strings.");
        }
    }

    public CharSequence toString(Context context) {
        if (this.pluralRes > 0) {
            if (this.formatArgs != null) {
                return context.getResources().getQuantityString(this.pluralRes, this.quantity, this.formatArgs);
            }
            return context.getResources().getQuantityString(this.pluralRes, this.quantity);
        } else if (this.stringRes <= 0) {
            return this.string;
        } else {
            if (this.formatArgs != null) {
                return context.getResources().getString(this.stringRes, this.formatArgs);
            }
            return context.getResources().getText(this.stringRes);
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StringAttributeData)) {
            return false;
        }
        StringAttributeData that = (StringAttributeData) o;
        if (this.stringRes != that.stringRes || this.pluralRes != that.pluralRes || this.quantity != that.quantity) {
            return false;
        }
        if (this.string != null) {
            if (!this.string.equals(that.string)) {
                return false;
            }
        } else if (that.string != null) {
            return false;
        }
        return Arrays.equals(this.formatArgs, that.formatArgs);
    }

    public int hashCode() {
        return ((((((((this.string != null ? this.string.hashCode() : 0) * 31) + this.stringRes) * 31) + this.pluralRes) * 31) + this.quantity) * 31) + Arrays.hashCode(this.formatArgs);
    }
}
