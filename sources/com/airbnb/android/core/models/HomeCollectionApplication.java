package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenHomeCollectionApplication;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class HomeCollectionApplication extends GenHomeCollectionApplication {
    public static final Creator<HomeCollectionApplication> CREATOR = new Creator<HomeCollectionApplication>() {
        public HomeCollectionApplication[] newArray(int size) {
            return new HomeCollectionApplication[size];
        }

        public HomeCollectionApplication createFromParcel(Parcel source) {
            HomeCollectionApplication object = new HomeCollectionApplication();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final int STATUS_APPLIED = 1;
    public static final int STATUS_APPROVED = 5;
    public static final int STATUS_CLOSE_TO_PASS = 7;
    public static final int STATUS_DESIGN_CONSULTATION = 8;
    public static final int STATUS_DESIGN_CONSULTATION_COMPLETED = 9;
    public static final int STATUS_FAR_FROM_PASS = 6;
    public static final int STATUS_INSPECTION_CANCELED = 3;
    public static final int STATUS_INSPECTION_COMPLETED = 4;
    public static final int STATUS_INSPECTION_SCHEDULED = 2;
    public static final int STATUS_INVITED = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
    }

    public int getStatus() {
        return super.getStatus();
    }
}
