package com.facebook.accountkit;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.facebook.accountkit.internal.InternalAccountKitError;

public final class AccountKitError implements Parcelable {
    public static final Creator<AccountKitError> CREATOR = new Creator<AccountKitError>() {
        public AccountKitError createFromParcel(Parcel source) {
            return new AccountKitError(source);
        }

        public AccountKitError[] newArray(int size) {
            return new AccountKitError[size];
        }
    };
    private final Type errorType;
    private final InternalAccountKitError internalError;

    public enum Type {
        NETWORK_CONNECTION_ERROR(100, "A request failed due to a network error"),
        SERVER_ERROR(200, "Server generated an error"),
        LOGIN_INVALIDATED(300, "The request timed out"),
        INTERNAL_ERROR(400, "An internal consistency error has occurred"),
        INITIALIZATION_ERROR(500, "Initialization error"),
        ARGUMENT_ERROR(600, "Invalid argument provided");
        
        private final int code;
        private final String message;

        private Type(int code2, String message2) {
            this.code = code2;
            this.message = message2;
        }

        public String getMessage() {
            return this.message;
        }

        public int getCode() {
            return this.code;
        }

        public String toString() {
            return this.code + ": " + this.message;
        }
    }

    public AccountKitError(Type errorType2) {
        this(errorType2, (InternalAccountKitError) null);
    }

    public AccountKitError(Type errorType2, InternalAccountKitError internalError2) {
        this.errorType = errorType2;
        this.internalError = internalError2;
    }

    public int getDetailErrorCode() {
        if (this.internalError == null) {
            return -1;
        }
        return this.internalError.getCode();
    }

    public Type getErrorType() {
        return this.errorType;
    }

    public String getUserFacingMessage() {
        if (this.internalError == null) {
            return null;
        }
        return this.internalError.getUserFacingMessage();
    }

    public String toString() {
        return this.errorType + ": " + this.internalError;
    }

    private AccountKitError(Parcel parcel) {
        this.errorType = Type.values()[parcel.readInt()];
        this.internalError = (InternalAccountKitError) parcel.readParcelable(InternalAccountKitError.class.getClassLoader());
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.errorType.ordinal());
        dest.writeParcelable(this.internalError, flags);
    }

    public int describeContents() {
        return 0;
    }
}
