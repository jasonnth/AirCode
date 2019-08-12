package com.airbnb.android.core.payments.models;

import com.airbnb.android.core.models.Price;

/* renamed from: com.airbnb.android.core.payments.models.$AutoValue_QuickPayArguments reason: invalid class name */
abstract class C$AutoValue_QuickPayArguments extends QuickPayArguments {
    private final CartItem cartItem;
    private final QuickPayClientType clientType;
    private final Price price;

    /* renamed from: com.airbnb.android.core.payments.models.$AutoValue_QuickPayArguments$Builder */
    static final class Builder extends com.airbnb.android.core.payments.models.QuickPayArguments.Builder {
        private CartItem cartItem;
        private QuickPayClientType clientType;
        private Price price;

        Builder() {
        }

        public com.airbnb.android.core.payments.models.QuickPayArguments.Builder cartItem(CartItem cartItem2) {
            if (cartItem2 == null) {
                throw new NullPointerException("Null cartItem");
            }
            this.cartItem = cartItem2;
            return this;
        }

        public com.airbnb.android.core.payments.models.QuickPayArguments.Builder price(Price price2) {
            this.price = price2;
            return this;
        }

        public com.airbnb.android.core.payments.models.QuickPayArguments.Builder clientType(QuickPayClientType clientType2) {
            if (clientType2 == null) {
                throw new NullPointerException("Null clientType");
            }
            this.clientType = clientType2;
            return this;
        }

        public QuickPayArguments build() {
            String missing = "";
            if (this.cartItem == null) {
                missing = missing + " cartItem";
            }
            if (this.clientType == null) {
                missing = missing + " clientType";
            }
            if (missing.isEmpty()) {
                return new AutoValue_QuickPayArguments(this.cartItem, this.price, this.clientType);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_QuickPayArguments(CartItem cartItem2, Price price2, QuickPayClientType clientType2) {
        if (cartItem2 == null) {
            throw new NullPointerException("Null cartItem");
        }
        this.cartItem = cartItem2;
        this.price = price2;
        if (clientType2 == null) {
            throw new NullPointerException("Null clientType");
        }
        this.clientType = clientType2;
    }

    public CartItem getCartItem() {
        return this.cartItem;
    }

    public Price getPrice() {
        return this.price;
    }

    public QuickPayClientType getClientType() {
        return this.clientType;
    }

    public String toString() {
        return "QuickPayArguments{cartItem=" + this.cartItem + ", price=" + this.price + ", clientType=" + this.clientType + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof QuickPayArguments)) {
            return false;
        }
        QuickPayArguments that = (QuickPayArguments) o;
        if (!this.cartItem.equals(that.getCartItem()) || (this.price != null ? !this.price.equals(that.getPrice()) : that.getPrice() != null) || !this.clientType.equals(that.getClientType())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((1 * 1000003) ^ this.cartItem.hashCode()) * 1000003) ^ (this.price == null ? 0 : this.price.hashCode())) * 1000003) ^ this.clientType.hashCode();
    }
}
