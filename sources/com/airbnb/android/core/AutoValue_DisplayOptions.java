package com.airbnb.android.core;

import com.airbnb.android.core.DisplayOptions.DisplayType;

final class AutoValue_DisplayOptions extends DisplayOptions {
    private final float cardsPerRow;
    private final DisplayType displayType;

    AutoValue_DisplayOptions(DisplayType displayType2, float cardsPerRow2) {
        if (displayType2 == null) {
            throw new NullPointerException("Null displayType");
        }
        this.displayType = displayType2;
        this.cardsPerRow = cardsPerRow2;
    }

    public DisplayType displayType() {
        return this.displayType;
    }

    public float cardsPerRow() {
        return this.cardsPerRow;
    }

    public String toString() {
        return "DisplayOptions{displayType=" + this.displayType + ", cardsPerRow=" + this.cardsPerRow + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DisplayOptions)) {
            return false;
        }
        DisplayOptions that = (DisplayOptions) o;
        if (!this.displayType.equals(that.displayType()) || Float.floatToIntBits(this.cardsPerRow) != Float.floatToIntBits(that.cardsPerRow())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.displayType.hashCode()) * 1000003) ^ Float.floatToIntBits(this.cardsPerRow);
    }
}
