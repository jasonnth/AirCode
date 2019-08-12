package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenUserBlock;

public class UserBlock extends GenUserBlock {
    public static final Creator<UserBlock> CREATOR = new Creator<UserBlock>() {
        public UserBlock[] newArray(int size) {
            return new UserBlock[size];
        }

        public UserBlock createFromParcel(Parcel source) {
            UserBlock object = new UserBlock();
            object.readFromParcel(source);
            return object;
        }
    };
}
