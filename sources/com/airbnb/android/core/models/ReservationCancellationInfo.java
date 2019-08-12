package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.enums.ReservationCancellationReason;
import com.airbnb.android.core.models.generated.GenReservationCancellationInfo;
import java.util.Iterator;

public class ReservationCancellationInfo extends GenReservationCancellationInfo {
    public static final Creator<ReservationCancellationInfo> CREATOR = new Creator<ReservationCancellationInfo>() {
        public ReservationCancellationInfo[] newArray(int size) {
            return new ReservationCancellationInfo[size];
        }

        public ReservationCancellationInfo createFromParcel(Parcel source) {
            ReservationCancellationInfo object = new ReservationCancellationInfo();
            object.readFromParcel(source);
            return object;
        }
    };

    public ReservationCancellationReasonInfo findReason(ReservationCancellationReason reason) {
        if (getCancellationReasonsMobile() != null) {
            Iterator it = getCancellationReasonsMobile().iterator();
            while (it.hasNext()) {
                ReservationCancellationReasonInfo reasonInfo = (ReservationCancellationReasonInfo) it.next();
                if (reasonInfo.getReasonId().equals(reason.getServerKey())) {
                    return reasonInfo;
                }
                if (reasonInfo.getSubReasons() != null) {
                    Iterator it2 = reasonInfo.getSubReasons().iterator();
                    while (it2.hasNext()) {
                        ReservationCancellationReasonInfo subReasonInfo = (ReservationCancellationReasonInfo) it2.next();
                        if (subReasonInfo.getReasonId().equals(reason.getServerKey())) {
                            return subReasonInfo;
                        }
                    }
                    continue;
                }
            }
        }
        return null;
    }
}
