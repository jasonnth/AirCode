package com.airbnb.android.listing.adapters;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

class LengthOfStayRuleState implements Parcelable {
    public static final Creator<LengthOfStayRuleState> CREATOR = new Creator<LengthOfStayRuleState>() {
        public LengthOfStayRuleState[] newArray(int size) {
            return new LengthOfStayRuleState[size];
        }

        public LengthOfStayRuleState createFromParcel(Parcel source) {
            return new LengthOfStayRuleState(source);
        }
    };
    private Integer currentValue;
    private int lengthOfStayNights;
    private int origValue;
    private boolean show;
    private boolean showError;
    private Integer tip;

    LengthOfStayRuleState(int lengthOfStayNights2, int origValue2, Integer tip2, boolean show2) {
        this.lengthOfStayNights = lengthOfStayNights2;
        this.origValue = origValue2;
        this.tip = tip2;
        this.show = show2;
        setCurrentValue(Integer.valueOf(origValue2));
    }

    LengthOfStayRuleState(Parcel in) {
        readFromParcel(in);
    }

    public int getLengthOfStayNights() {
        return this.lengthOfStayNights;
    }

    public Integer getTip() {
        return this.tip;
    }

    public Integer getCurrentValue() {
        return this.currentValue;
    }

    public void setCurrentValue(Integer currentValue2) {
        this.currentValue = currentValue2;
    }

    public boolean show() {
        return this.show;
    }

    public void setShow(boolean show2) {
        this.show = show2;
    }

    public boolean showError() {
        return this.showError;
    }

    public boolean setShowError(boolean showError2) {
        boolean hasChanged = this.showError != showError2;
        this.showError = showError2;
        return hasChanged;
    }

    public void remove() {
        this.currentValue = null;
        this.show = false;
    }

    public boolean hasChanged() {
        return this.currentValue == null || this.origValue != this.currentValue.intValue();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        int i = -1;
        parcel.writeInt(this.lengthOfStayNights);
        parcel.writeInt(this.origValue);
        parcel.writeInt(this.currentValue == null ? -1 : this.currentValue.intValue());
        if (this.tip != null) {
            i = this.tip.intValue();
        }
        parcel.writeInt(i);
        parcel.writeInt(this.show ? 1 : 0);
    }

    public void readFromParcel(Parcel source) {
        Integer num = null;
        this.lengthOfStayNights = source.readInt();
        this.origValue = source.readInt();
        int val = source.readInt();
        this.currentValue = val == -1 ? null : Integer.valueOf(val);
        int val2 = source.readInt();
        if (val2 != -1) {
            num = Integer.valueOf(val2);
        }
        this.tip = num;
        this.show = source.readInt() == 1;
    }
}
