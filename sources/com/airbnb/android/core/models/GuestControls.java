package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.generated.GenGuestControls;
import com.google.common.base.Objects;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import java.util.ArrayList;
import java.util.List;

public class GuestControls extends GenGuestControls {
    public static final Creator<GuestControls> CREATOR = new Creator<GuestControls>() {
        public GuestControls[] newArray(int size) {
            return new GuestControls[size];
        }

        public GuestControls createFromParcel(Parcel source) {
            GuestControls object = new GuestControls();
            object.readFromParcel(source);
            return object;
        }
    };

    public Boolean isGuestControlTypeAllowed(GuestControlType type) {
        switch (type) {
            case Children:
                return isAllowsChildrenAsHost();
            case Infants:
                return isAllowsInfantsAsHost();
            case Pets:
                return isAllowsPetsAsHost();
            case Smoking:
                return isAllowsSmokingAsHost();
            case Events:
                return isAllowsEventsAsHost();
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) getUnknownTypeException(type));
                return null;
        }
    }

    public void setGuestControlTypeAllowed(GuestControlType type, Boolean isAllowed) {
        switch (type) {
            case Children:
                setAllowsChildrenAsHost(isAllowed);
                return;
            case Infants:
                setAllowsInfantsAsHost(isAllowed);
                return;
            case Pets:
                setAllowsPetsAsHost(isAllowed);
                return;
            case Smoking:
                setAllowsSmokingAsHost(isAllowed);
                return;
            case Events:
                setAllowsEventsAsHost(isAllowed);
                return;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) getUnknownTypeException(type));
                return;
        }
    }

    public List<String> getLongTermHouseRules() {
        if (getStructuredHouseRulesWithTips() == null) {
            return getStructuredHouseRules();
        }
        return FluentIterable.from((Iterable<E>) getStructuredHouseRulesWithTips()).transform(GuestControls$$Lambda$1.lambdaFactory$()).toList();
    }

    static /* synthetic */ String lambda$getLongTermHouseRules$0(StructuredHouseRule rule) {
        return !TextUtils.isEmpty(rule.getLongTermText()) ? rule.getLongTermText() : rule.getText();
    }

    public List<String> getStructuredHouseRules() {
        return this.mStructuredHouseRules == null ? new ArrayList() : this.mStructuredHouseRules;
    }

    private IllegalStateException getUnknownTypeException(GuestControlType type) {
        return new IllegalStateException("unknown GuestControlType: " + type);
    }

    public boolean hasHouseRulesSet() {
        for (GuestControlType type : GuestControlType.values()) {
            if (isGuestControlTypeAllowed(type) != null) {
                return true;
            }
        }
        return false;
    }

    public ImmutableList<GuestControlType> getRules(boolean allowed) {
        GuestControlType[] values;
        Builder<GuestControlType> builder = ImmutableList.builder();
        for (GuestControlType type : GuestControlType.values()) {
            Boolean isAllowed = isGuestControlTypeAllowed(type);
            if (isAllowed != null && isAllowed.booleanValue() == allowed) {
                builder.add((Object) type);
            }
        }
        return builder.build();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GuestControls that = (GuestControls) o;
        if (this.mAllowsChildren != that.mAllowsChildren || this.mAllowsInfants != that.mAllowsInfants || this.mAllowsPets != that.mAllowsPets || this.mAllowsSmoking != that.mAllowsSmoking || this.mAllowsEvents != that.mAllowsEvents || this.mId != that.mId || !Objects.equal(this.mAllowsChildrenAsHost, that.mAllowsChildrenAsHost) || !Objects.equal(this.mAllowsInfantsAsHost, that.mAllowsInfantsAsHost) || !Objects.equal(this.mAllowsPetsAsHost, that.mAllowsPetsAsHost) || !Objects.equal(this.mAllowsSmokingAsHost, that.mAllowsSmokingAsHost) || !Objects.equal(this.mAllowsEventsAsHost, that.mAllowsEventsAsHost) || !Objects.equal(this.mStructuredHouseRules, that.mStructuredHouseRules) || !Objects.equal(this.mHostCheckInTimeMessage, that.mHostCheckInTimeMessage)) {
            return false;
        }
        return true;
    }

    public static GuestControls copy(GuestControls original) {
        GuestControls copy = new GuestControls();
        copy.mAllowsChildrenAsHost = original.mAllowsChildrenAsHost;
        copy.mAllowsInfantsAsHost = original.mAllowsInfantsAsHost;
        copy.mAllowsPetsAsHost = original.mAllowsPetsAsHost;
        copy.mAllowsSmokingAsHost = original.mAllowsSmokingAsHost;
        copy.mAllowsEventsAsHost = original.mAllowsEventsAsHost;
        if (original.mStructuredHouseRules != null) {
            copy.mStructuredHouseRules = new ArrayList(original.mStructuredHouseRules);
        }
        copy.mHostCheckInTimeMessage = original.mHostCheckInTimeMessage;
        copy.mAllowsChildren = original.mAllowsChildren;
        copy.mAllowsInfants = original.mAllowsInfants;
        copy.mAllowsPets = original.mAllowsPets;
        copy.mAllowsSmoking = original.mAllowsSmoking;
        copy.mAllowsEvents = original.mAllowsEvents;
        copy.mId = original.mId;
        return copy;
    }
}
