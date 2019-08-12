package com.airbnb.android.core.payments.models;

import com.airbnb.android.core.payments.models.Bill.BillItem;
import com.airbnb.android.core.payments.models.Bill.ProductMetadata;
import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.core.payments.models.$AutoValue_Bill_BillItem reason: invalid class name */
abstract class C$AutoValue_Bill_BillItem extends BillItem {
    private final long billId;
    private final long productId;
    private final ProductMetadata productMetadata;
    private final String productType;
    private final long status;
    private final String token;

    /* renamed from: com.airbnb.android.core.payments.models.$AutoValue_Bill_BillItem$Builder */
    static final class Builder extends com.airbnb.android.core.payments.models.Bill.BillItem.Builder {
        private Long billId;
        private Long productId;
        private ProductMetadata productMetadata;
        private String productType;
        private Long status;
        private String token;

        Builder() {
        }

        public com.airbnb.android.core.payments.models.Bill.BillItem.Builder billId(long billId2) {
            this.billId = Long.valueOf(billId2);
            return this;
        }

        public com.airbnb.android.core.payments.models.Bill.BillItem.Builder productId(long productId2) {
            this.productId = Long.valueOf(productId2);
            return this;
        }

        public com.airbnb.android.core.payments.models.Bill.BillItem.Builder productMetadata(ProductMetadata productMetadata2) {
            this.productMetadata = productMetadata2;
            return this;
        }

        public com.airbnb.android.core.payments.models.Bill.BillItem.Builder productType(String productType2) {
            if (productType2 == null) {
                throw new NullPointerException("Null productType");
            }
            this.productType = productType2;
            return this;
        }

        public com.airbnb.android.core.payments.models.Bill.BillItem.Builder status(long status2) {
            this.status = Long.valueOf(status2);
            return this;
        }

        public com.airbnb.android.core.payments.models.Bill.BillItem.Builder token(String token2) {
            if (token2 == null) {
                throw new NullPointerException("Null token");
            }
            this.token = token2;
            return this;
        }

        public BillItem build() {
            String missing = "";
            if (this.billId == null) {
                missing = missing + " billId";
            }
            if (this.productId == null) {
                missing = missing + " productId";
            }
            if (this.productType == null) {
                missing = missing + " productType";
            }
            if (this.status == null) {
                missing = missing + " status";
            }
            if (this.token == null) {
                missing = missing + " token";
            }
            if (missing.isEmpty()) {
                return new AutoValue_Bill_BillItem(this.billId.longValue(), this.productId.longValue(), this.productMetadata, this.productType, this.status.longValue(), this.token);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_Bill_BillItem(long billId2, long productId2, ProductMetadata productMetadata2, String productType2, long status2, String token2) {
        this.billId = billId2;
        this.productId = productId2;
        this.productMetadata = productMetadata2;
        if (productType2 == null) {
            throw new NullPointerException("Null productType");
        }
        this.productType = productType2;
        this.status = status2;
        if (token2 == null) {
            throw new NullPointerException("Null token");
        }
        this.token = token2;
    }

    @JsonProperty("bill_id")
    public long billId() {
        return this.billId;
    }

    @JsonProperty("product_id")
    public long productId() {
        return this.productId;
    }

    @JsonProperty("product_metadata")
    public ProductMetadata productMetadata() {
        return this.productMetadata;
    }

    @JsonProperty("product_type")
    public String productType() {
        return this.productType;
    }

    @JsonProperty("status")
    public long status() {
        return this.status;
    }

    @JsonProperty("token")
    public String token() {
        return this.token;
    }

    public String toString() {
        return "BillItem{billId=" + this.billId + ", productId=" + this.productId + ", productMetadata=" + this.productMetadata + ", productType=" + this.productType + ", status=" + this.status + ", token=" + this.token + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BillItem)) {
            return false;
        }
        BillItem that = (BillItem) o;
        if (this.billId != that.billId() || this.productId != that.productId() || (this.productMetadata != null ? !this.productMetadata.equals(that.productMetadata()) : that.productMetadata() != null) || !this.productType.equals(that.productType()) || this.status != that.status() || !this.token.equals(that.token())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((int) (((long) (((((((int) (((long) (((int) (((long) (1 * 1000003)) ^ ((this.billId >>> 32) ^ this.billId))) * 1000003)) ^ ((this.productId >>> 32) ^ this.productId))) * 1000003) ^ (this.productMetadata == null ? 0 : this.productMetadata.hashCode())) * 1000003) ^ this.productType.hashCode()) * 1000003)) ^ ((this.status >>> 32) ^ this.status))) * 1000003) ^ this.token.hashCode();
    }
}
