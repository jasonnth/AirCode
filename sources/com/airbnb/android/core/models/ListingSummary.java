package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingSummary;
import com.google.common.collect.FluentIterable;

public class ListingSummary extends GenListingSummary {
    public static final Creator<ListingSummary> CREATOR = new Creator<ListingSummary>() {
        public ListingSummary[] newArray(int size) {
            return new ListingSummary[size];
        }

        public ListingSummary createFromParcel(Parcel source) {
            ListingSummary object = new ListingSummary();
            object.readFromParcel(source);
            return object;
        }
    };

    static /* synthetic */ boolean lambda$isUserCohost$0(long userId, User u) {
        return u.getId() == userId;
    }

    public boolean isUserCohost(long userId) {
        return getPrimaryHost().getId() != userId && FluentIterable.from((Iterable<E>) getHosts()).anyMatch(ListingSummary$$Lambda$1.lambdaFactory$(userId));
    }
}
