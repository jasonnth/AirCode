package com.airbnb.android.core.payments.models;

import com.airbnb.android.core.payments.models.BookingResult.Error;
import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.core.payments.models.$AutoValue_BookingResult_Error reason: invalid class name */
abstract class C$AutoValue_BookingResult_Error extends Error {
    private final long existingInstrumentId;
    private final String field;
    private final long invalidateOption;
    private final String msg;

    /* renamed from: com.airbnb.android.core.payments.models.$AutoValue_BookingResult_Error$Builder */
    static final class Builder extends com.airbnb.android.core.payments.models.BookingResult.Error.Builder {
        private Long existingInstrumentId;
        private String field;
        private Long invalidateOption;
        private String msg;

        Builder() {
        }

        public com.airbnb.android.core.payments.models.BookingResult.Error.Builder msg(String msg2) {
            if (msg2 == null) {
                throw new NullPointerException("Null msg");
            }
            this.msg = msg2;
            return this;
        }

        public com.airbnb.android.core.payments.models.BookingResult.Error.Builder field(String field2) {
            if (field2 == null) {
                throw new NullPointerException("Null field");
            }
            this.field = field2;
            return this;
        }

        public com.airbnb.android.core.payments.models.BookingResult.Error.Builder invalidateOption(long invalidateOption2) {
            this.invalidateOption = Long.valueOf(invalidateOption2);
            return this;
        }

        public com.airbnb.android.core.payments.models.BookingResult.Error.Builder existingInstrumentId(long existingInstrumentId2) {
            this.existingInstrumentId = Long.valueOf(existingInstrumentId2);
            return this;
        }

        public Error build() {
            String missing = "";
            if (this.msg == null) {
                missing = missing + " msg";
            }
            if (this.field == null) {
                missing = missing + " field";
            }
            if (this.invalidateOption == null) {
                missing = missing + " invalidateOption";
            }
            if (this.existingInstrumentId == null) {
                missing = missing + " existingInstrumentId";
            }
            if (missing.isEmpty()) {
                return new AutoValue_BookingResult_Error(this.msg, this.field, this.invalidateOption.longValue(), this.existingInstrumentId.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_BookingResult_Error(String msg2, String field2, long invalidateOption2, long existingInstrumentId2) {
        if (msg2 == null) {
            throw new NullPointerException("Null msg");
        }
        this.msg = msg2;
        if (field2 == null) {
            throw new NullPointerException("Null field");
        }
        this.field = field2;
        this.invalidateOption = invalidateOption2;
        this.existingInstrumentId = existingInstrumentId2;
    }

    @JsonProperty("msg")
    public String msg() {
        return this.msg;
    }

    @JsonProperty("field")
    public String field() {
        return this.field;
    }

    @JsonProperty("invalidate_option")
    public long invalidateOption() {
        return this.invalidateOption;
    }

    @JsonProperty("existing_instrument_id")
    public long existingInstrumentId() {
        return this.existingInstrumentId;
    }

    public String toString() {
        return "Error{msg=" + this.msg + ", field=" + this.field + ", invalidateOption=" + this.invalidateOption + ", existingInstrumentId=" + this.existingInstrumentId + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Error)) {
            return false;
        }
        Error that = (Error) o;
        if (!this.msg.equals(that.msg()) || !this.field.equals(that.field()) || this.invalidateOption != that.invalidateOption() || this.existingInstrumentId != that.existingInstrumentId()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (((long) (((int) (((long) (((((1 * 1000003) ^ this.msg.hashCode()) * 1000003) ^ this.field.hashCode()) * 1000003)) ^ ((this.invalidateOption >>> 32) ^ this.invalidateOption))) * 1000003)) ^ ((this.existingInstrumentId >>> 32) ^ this.existingInstrumentId));
    }
}
