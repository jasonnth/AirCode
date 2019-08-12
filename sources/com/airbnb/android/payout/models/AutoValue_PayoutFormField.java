package com.airbnb.android.payout.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

final class AutoValue_PayoutFormField extends C$AutoValue_PayoutFormField {
    public static final Creator<AutoValue_PayoutFormField> CREATOR = new Creator<AutoValue_PayoutFormField>() {
        public AutoValue_PayoutFormField createFromParcel(Parcel in) {
            boolean z;
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            Integer num;
            Integer num2;
            boolean z2 = true;
            String str6 = null;
            if (in.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            if (in.readInt() != 1) {
                z2 = false;
            }
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            if (in.readInt() == 0) {
                str2 = in.readString();
            } else {
                str2 = null;
            }
            if (in.readInt() == 0) {
                str3 = in.readString();
            } else {
                str3 = null;
            }
            if (in.readInt() == 0) {
                str4 = in.readString();
            } else {
                str4 = null;
            }
            if (in.readInt() == 0) {
                str5 = in.readString();
            } else {
                str5 = null;
            }
            String readString = in.readString();
            if (in.readInt() == 0) {
                num = Integer.valueOf(in.readInt());
            } else {
                num = null;
            }
            if (in.readInt() == 0) {
                num2 = Integer.valueOf(in.readInt());
            } else {
                num2 = null;
            }
            ArrayList readArrayList = in.readArrayList(String.class.getClassLoader());
            if (in.readInt() == 0) {
                str6 = in.readString();
            }
            return new AutoValue_PayoutFormField(z, z2, str, str2, str3, str4, str5, readString, num, num2, readArrayList, str6);
        }

        public AutoValue_PayoutFormField[] newArray(int size) {
            return new AutoValue_PayoutFormField[size];
        }
    };

    AutoValue_PayoutFormField(boolean confirmField, boolean required, String errorText, String fieldType, String helperText, String hintText, String name, String label, Integer maxLength, Integer minLength, List<String> values, String regex) {
        super(confirmField, required, errorText, fieldType, helperText, hintText, name, label, maxLength, minLength, values, regex);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        dest.writeInt(confirmField() ? 1 : 0);
        if (required()) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        if (errorText() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(errorText());
        }
        if (fieldType() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(fieldType());
        }
        if (helperText() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(helperText());
        }
        if (hintText() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(hintText());
        }
        if (name() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(name());
        }
        dest.writeString(label());
        if (maxLength() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(maxLength().intValue());
        }
        if (minLength() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(minLength().intValue());
        }
        dest.writeList(values());
        if (regex() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(regex());
    }

    public int describeContents() {
        return 0;
    }
}
