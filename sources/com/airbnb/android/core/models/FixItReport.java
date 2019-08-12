package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenFixItReport;
import com.google.common.collect.FluentIterable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class FixItReport extends GenFixItReport {
    public static final Creator<FixItReport> CREATOR = new Creator<FixItReport>() {
        public FixItReport[] newArray(int size) {
            return new FixItReport[size];
        }

        public FixItReport createFromParcel(Parcel source) {
            FixItReport object = new FixItReport();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final int STATUS_PUBLISHED = 1;
    public static final int STATUS_UNPUBLISHED = 0;
    public static final int TYPE_SELECT_POST_INSPECTION = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    public int getStatus() {
        return super.getStatus();
    }

    public int getReportType() {
        return super.getReportType();
    }

    public boolean isComplete() {
        return getAwaitingReviewItems().size() == 0 && getRequiredToDoItems().size() == 0;
    }

    public List<FixItItem> getAwaitingReviewItems() {
        return FluentIterable.from((Iterable<E>) getItems()).filter(FixItReport$$Lambda$1.lambdaFactory$()).toList();
    }

    static /* synthetic */ boolean lambda$getAwaitingReviewItems$0(FixItItem item) {
        return item != null && item.isAwaitingReview();
    }

    public List<FixItItem> getApprovedItems() {
        return FluentIterable.from((Iterable<E>) getItems()).filter(FixItReport$$Lambda$2.lambdaFactory$()).toList();
    }

    static /* synthetic */ boolean lambda$getApprovedItems$1(FixItItem item) {
        return item != null && item.isApproved();
    }

    public List<FixItItem> getRequiredToDoItems() {
        return FluentIterable.from((Iterable<E>) getItems()).filter(FixItReport$$Lambda$3.lambdaFactory$()).toList();
    }

    static /* synthetic */ boolean lambda$getRequiredToDoItems$2(FixItItem item) {
        if (item == null || item.getSeverityLevel() != 1 || !item.requiresAction()) {
            return false;
        }
        return true;
    }

    public List<FixItItem> getNotRequiredToDoItems() {
        return FluentIterable.from((Iterable<E>) getItems()).filter(FixItReport$$Lambda$4.lambdaFactory$()).toList();
    }

    static /* synthetic */ boolean lambda$getNotRequiredToDoItems$3(FixItItem item) {
        return item != null && item.getSeverityLevel() == 0 && item.requiresAction();
    }
}
