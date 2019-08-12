package com.airbnb.android.core.interfaces;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.C0716R;
import com.airbnb.jitney.event.logging.IdType.p117v1.C2217IdType;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionViewItemPresenter;

public interface GuestIdentity extends Parcelable, SelectionViewItemPresenter {

    public enum Type implements Parcelable, SelectionViewItemPresenter {
        Passport(C0716R.string.passport, C0716R.string.passport_number),
        ChineseNationalID(C0716R.string.select_identification_type_option_national_id, C0716R.string.guest_identification_number);
        
        public static final Creator<Type> CREATOR = null;
        final int displayString;
        final int hintString;

        static {
            CREATOR = new Creator<Type>() {
                public Type createFromParcel(Parcel source) {
                    return Type.values()[source.readInt()];
                }

                public Type[] newArray(int size) {
                    return new Type[size];
                }
            };
        }

        private Type(int displayString2, int hintString2) {
            this.displayString = displayString2;
            this.hintString = hintString2;
        }

        public String getDisplayText(Context context) {
            return context.getString(this.displayString);
        }

        public String getHintText(Context context) {
            return context.getString(this.hintString);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(ordinal());
        }

        public C2217IdType getJitneyIdType() {
            return this == Passport ? C2217IdType.Passport : C2217IdType.GovernmentId;
        }
    }

    String getGivenNames();

    int getId();

    String getIdentityString();

    String getSurname();

    Type getType();

    boolean isBooker();

    boolean isSelected();

    void setBooker(boolean z);

    void setSelected(boolean z);

    void toggleSelected();
}
