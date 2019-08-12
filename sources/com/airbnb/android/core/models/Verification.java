package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenVerification;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Verification extends GenVerification {
    public static final Creator<Verification> CREATOR = new Creator<Verification>() {
        public Verification[] newArray(int size) {
            return new Verification[size];
        }

        public Verification createFromParcel(Parcel source) {
            Verification object = new Verification();
            object.readFromParcel(source);
            return object;
        }
    };
    static final int Completed = 1;
    public static final int Incomplete = 0;
    private static final String SERVER_EMAIL_IDENTIFIER = "confirmed_email";
    private static final String SERVER_PHONE_IDENTIFIER = "phone_verified";
    private static final String SERVER_PHOTO_IDENTIFIER = "photo_with_face";
    private int completionStatus;
    private Type type;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
    }

    public enum Type {
        None(""),
        Photo("photo_with_face"),
        Email(Verification.SERVER_EMAIL_IDENTIFIER),
        Phone(Verification.SERVER_PHONE_IDENTIFIER);
        
        public final String serverId;

        private Type(String serverId2) {
            this.serverId = serverId2;
        }
    }

    public Verification() {
        this.completionStatus = 0;
    }

    public Verification(Type type2) {
        this(type2, 0);
    }

    public Verification(Type type2, int completionStatus2) {
        this.completionStatus = 0;
        this.type = type2;
        this.completionStatus = completionStatus2;
        setId(type2.serverId);
        setStatus(completionStatus2 == 0 ? "default" : "complete");
    }

    public static Type getTypeFromAccountVerification(AccountVerification accountVerification) {
        String type2 = accountVerification.getType();
        char c = 65535;
        switch (type2.hashCode()) {
            case 96619420:
                if (type2.equals("email")) {
                    c = 2;
                    break;
                }
                break;
            case 106642798:
                if (type2.equals("phone")) {
                    c = 0;
                    break;
                }
                break;
            case 1714556009:
                if (type2.equals("photo_with_face")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return Type.Phone;
            case 1:
                return Type.Photo;
            case 2:
                return Type.Email;
            default:
                return Type.None;
        }
    }

    public Type getType() {
        if (this.type == null) {
            String str = this.mId;
            char c = 65535;
            switch (str.hashCode()) {
                case -2057130308:
                    if (str.equals(SERVER_EMAIL_IDENTIFIER)) {
                        c = 1;
                        break;
                    }
                    break;
                case -166900103:
                    if (str.equals(SERVER_PHONE_IDENTIFIER)) {
                        c = 2;
                        break;
                    }
                    break;
                case 1714556009:
                    if (str.equals("photo_with_face")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.type = Type.Photo;
                    break;
                case 1:
                    this.type = Type.Email;
                    break;
                case 2:
                    this.type = Type.Phone;
                    break;
                default:
                    this.type = Type.None;
                    break;
            }
        }
        return this.type;
    }

    private int getCompletionStatus() {
        if (serverFlaggedCompleted()) {
            this.completionStatus = 1;
        }
        return this.completionStatus;
    }

    public boolean isIncomplete() {
        return getCompletionStatus() == 0;
    }

    public void markCompleted() {
        this.completionStatus = 1;
    }

    public boolean isCompleted() {
        return getCompletionStatus() == 1;
    }

    private boolean serverFlaggedCompleted() {
        return "complete".equals(this.mStatus);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (getType() != ((Verification) o).getType()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public String toString() {
        return "{Verification: " + this.mId + ", status: " + this.mStatus + "}";
    }

    public int describeContents() {
        return 0;
    }

    private Verification(Parcel in) {
        this.completionStatus = 0;
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : Type.values()[tmpType];
        this.completionStatus = in.readInt();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mId);
        parcel.writeString(this.mStatus);
        parcel.writeInt(this.type == null ? -1 : this.type.ordinal());
        parcel.writeInt(this.completionStatus);
    }

    public void readFromParcel(Parcel source) {
        this.mId = source.readString();
        this.mStatus = source.readString();
        int tmpType = source.readInt();
        this.type = tmpType == -1 ? null : Type.values()[tmpType];
        this.completionStatus = source.readInt();
    }
}
