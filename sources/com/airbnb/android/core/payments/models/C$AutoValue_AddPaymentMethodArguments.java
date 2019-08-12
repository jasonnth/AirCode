package com.airbnb.android.core.payments.models;

/* renamed from: com.airbnb.android.core.payments.models.$AutoValue_AddPaymentMethodArguments reason: invalid class name */
abstract class C$AutoValue_AddPaymentMethodArguments extends AddPaymentMethodArguments {
    private final AddPaymentMethodClientType clientType;

    /* renamed from: com.airbnb.android.core.payments.models.$AutoValue_AddPaymentMethodArguments$Builder */
    static final class Builder extends com.airbnb.android.core.payments.models.AddPaymentMethodArguments.Builder {
        private AddPaymentMethodClientType clientType;

        Builder() {
        }

        public com.airbnb.android.core.payments.models.AddPaymentMethodArguments.Builder clientType(AddPaymentMethodClientType clientType2) {
            if (clientType2 == null) {
                throw new NullPointerException("Null clientType");
            }
            this.clientType = clientType2;
            return this;
        }

        public AddPaymentMethodArguments build() {
            String missing = "";
            if (this.clientType == null) {
                missing = missing + " clientType";
            }
            if (missing.isEmpty()) {
                return new AutoValue_AddPaymentMethodArguments(this.clientType);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_AddPaymentMethodArguments(AddPaymentMethodClientType clientType2) {
        if (clientType2 == null) {
            throw new NullPointerException("Null clientType");
        }
        this.clientType = clientType2;
    }

    public AddPaymentMethodClientType getClientType() {
        return this.clientType;
    }

    public String toString() {
        return "AddPaymentMethodArguments{clientType=" + this.clientType + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AddPaymentMethodArguments)) {
            return false;
        }
        return this.clientType.equals(((AddPaymentMethodArguments) o).getClientType());
    }

    public int hashCode() {
        return (1 * 1000003) ^ this.clientType.hashCode();
    }
}
