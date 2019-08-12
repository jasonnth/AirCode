package com.airbnb.jitney.event.logging.ListingManagerAttribute.p135v1;

import com.airbnb.android.core.intents.CohostingIntents;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ListingManagerAttribute.v1.ListingManagerAttribute */
public final class C2371ListingManagerAttribute implements Struct {
    public static final Adapter<C2371ListingManagerAttribute, Builder> ADAPTER = new ListingManagerAttributeAdapter();
    public final String acceptance_date;
    public final String amount_currency;
    public final Boolean contract_cleaning_fee;
    public final String contract_end_date;
    public final Long contract_percent;
    public final String contract_start_date;
    public final Long fixed_fee;
    public final Boolean is_primary_host;
    public final Long listing_manager_user_id;
    public final Long maximum_fee;
    public final Long minimum_fee;

    /* renamed from: com.airbnb.jitney.event.logging.ListingManagerAttribute.v1.ListingManagerAttribute$Builder */
    public static final class Builder implements StructBuilder<C2371ListingManagerAttribute> {
        /* access modifiers changed from: private */
        public String acceptance_date;
        /* access modifiers changed from: private */
        public String amount_currency;
        /* access modifiers changed from: private */
        public Boolean contract_cleaning_fee;
        /* access modifiers changed from: private */
        public String contract_end_date;
        /* access modifiers changed from: private */
        public Long contract_percent;
        /* access modifiers changed from: private */
        public String contract_start_date;
        /* access modifiers changed from: private */
        public Long fixed_fee;
        /* access modifiers changed from: private */
        public Boolean is_primary_host;
        /* access modifiers changed from: private */
        public Long listing_manager_user_id;
        /* access modifiers changed from: private */
        public Long maximum_fee;
        /* access modifiers changed from: private */
        public Long minimum_fee;

        private Builder() {
        }

        public Builder(Long listing_manager_user_id2) {
            this.listing_manager_user_id = listing_manager_user_id2;
        }

        public Builder contract_percent(Long contract_percent2) {
            this.contract_percent = contract_percent2;
            return this;
        }

        public Builder contract_cleaning_fee(Boolean contract_cleaning_fee2) {
            this.contract_cleaning_fee = contract_cleaning_fee2;
            return this;
        }

        public Builder acceptance_date(String acceptance_date2) {
            this.acceptance_date = acceptance_date2;
            return this;
        }

        public Builder contract_start_date(String contract_start_date2) {
            this.contract_start_date = contract_start_date2;
            return this;
        }

        public Builder contract_end_date(String contract_end_date2) {
            this.contract_end_date = contract_end_date2;
            return this;
        }

        public Builder is_primary_host(Boolean is_primary_host2) {
            this.is_primary_host = is_primary_host2;
            return this;
        }

        public Builder fixed_fee(Long fixed_fee2) {
            this.fixed_fee = fixed_fee2;
            return this;
        }

        public Builder maximum_fee(Long maximum_fee2) {
            this.maximum_fee = maximum_fee2;
            return this;
        }

        public Builder minimum_fee(Long minimum_fee2) {
            this.minimum_fee = minimum_fee2;
            return this;
        }

        public Builder amount_currency(String amount_currency2) {
            this.amount_currency = amount_currency2;
            return this;
        }

        public C2371ListingManagerAttribute build() {
            if (this.listing_manager_user_id != null) {
                return new C2371ListingManagerAttribute(this);
            }
            throw new IllegalStateException("Required field 'listing_manager_user_id' is missing");
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.ListingManagerAttribute.v1.ListingManagerAttribute$ListingManagerAttributeAdapter */
    private static final class ListingManagerAttributeAdapter implements Adapter<C2371ListingManagerAttribute, Builder> {
        private ListingManagerAttributeAdapter() {
        }

        public void write(Protocol protocol, C2371ListingManagerAttribute struct) throws IOException {
            protocol.writeStructBegin("ListingManagerAttribute");
            protocol.writeFieldBegin(CohostingIntents.INTENT_EXTRA_LISTING_MANAGER_USER_ID, 1, 10);
            protocol.writeI64(struct.listing_manager_user_id.longValue());
            protocol.writeFieldEnd();
            if (struct.contract_percent != null) {
                protocol.writeFieldBegin("contract_percent", 2, 10);
                protocol.writeI64(struct.contract_percent.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.contract_cleaning_fee != null) {
                protocol.writeFieldBegin("contract_cleaning_fee", 3, 2);
                protocol.writeBool(struct.contract_cleaning_fee.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.acceptance_date != null) {
                protocol.writeFieldBegin("acceptance_date", 4, PassportService.SF_DG11);
                protocol.writeString(struct.acceptance_date);
                protocol.writeFieldEnd();
            }
            if (struct.contract_start_date != null) {
                protocol.writeFieldBegin("contract_start_date", 5, PassportService.SF_DG11);
                protocol.writeString(struct.contract_start_date);
                protocol.writeFieldEnd();
            }
            if (struct.contract_end_date != null) {
                protocol.writeFieldBegin("contract_end_date", 6, PassportService.SF_DG11);
                protocol.writeString(struct.contract_end_date);
                protocol.writeFieldEnd();
            }
            if (struct.is_primary_host != null) {
                protocol.writeFieldBegin("is_primary_host", 7, 2);
                protocol.writeBool(struct.is_primary_host.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.fixed_fee != null) {
                protocol.writeFieldBegin("fixed_fee", 8, 10);
                protocol.writeI64(struct.fixed_fee.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.maximum_fee != null) {
                protocol.writeFieldBegin("maximum_fee", 9, 10);
                protocol.writeI64(struct.maximum_fee.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.minimum_fee != null) {
                protocol.writeFieldBegin("minimum_fee", 10, 10);
                protocol.writeI64(struct.minimum_fee.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.amount_currency != null) {
                protocol.writeFieldBegin("amount_currency", 11, PassportService.SF_DG11);
                protocol.writeString(struct.amount_currency);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private C2371ListingManagerAttribute(Builder builder) {
        this.listing_manager_user_id = builder.listing_manager_user_id;
        this.contract_percent = builder.contract_percent;
        this.contract_cleaning_fee = builder.contract_cleaning_fee;
        this.acceptance_date = builder.acceptance_date;
        this.contract_start_date = builder.contract_start_date;
        this.contract_end_date = builder.contract_end_date;
        this.is_primary_host = builder.is_primary_host;
        this.fixed_fee = builder.fixed_fee;
        this.maximum_fee = builder.maximum_fee;
        this.minimum_fee = builder.minimum_fee;
        this.amount_currency = builder.amount_currency;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C2371ListingManagerAttribute)) {
            return false;
        }
        C2371ListingManagerAttribute that = (C2371ListingManagerAttribute) other;
        if ((this.listing_manager_user_id == that.listing_manager_user_id || this.listing_manager_user_id.equals(that.listing_manager_user_id)) && ((this.contract_percent == that.contract_percent || (this.contract_percent != null && this.contract_percent.equals(that.contract_percent))) && ((this.contract_cleaning_fee == that.contract_cleaning_fee || (this.contract_cleaning_fee != null && this.contract_cleaning_fee.equals(that.contract_cleaning_fee))) && ((this.acceptance_date == that.acceptance_date || (this.acceptance_date != null && this.acceptance_date.equals(that.acceptance_date))) && ((this.contract_start_date == that.contract_start_date || (this.contract_start_date != null && this.contract_start_date.equals(that.contract_start_date))) && ((this.contract_end_date == that.contract_end_date || (this.contract_end_date != null && this.contract_end_date.equals(that.contract_end_date))) && ((this.is_primary_host == that.is_primary_host || (this.is_primary_host != null && this.is_primary_host.equals(that.is_primary_host))) && ((this.fixed_fee == that.fixed_fee || (this.fixed_fee != null && this.fixed_fee.equals(that.fixed_fee))) && ((this.maximum_fee == that.maximum_fee || (this.maximum_fee != null && this.maximum_fee.equals(that.maximum_fee))) && (this.minimum_fee == that.minimum_fee || (this.minimum_fee != null && this.minimum_fee.equals(that.minimum_fee)))))))))))) {
            if (this.amount_currency == that.amount_currency) {
                return true;
            }
            if (this.amount_currency != null && this.amount_currency.equals(that.amount_currency)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((((((((16777619 ^ this.listing_manager_user_id.hashCode()) * -2128831035) ^ (this.contract_percent == null ? 0 : this.contract_percent.hashCode())) * -2128831035) ^ (this.contract_cleaning_fee == null ? 0 : this.contract_cleaning_fee.hashCode())) * -2128831035) ^ (this.acceptance_date == null ? 0 : this.acceptance_date.hashCode())) * -2128831035) ^ (this.contract_start_date == null ? 0 : this.contract_start_date.hashCode())) * -2128831035) ^ (this.contract_end_date == null ? 0 : this.contract_end_date.hashCode())) * -2128831035) ^ (this.is_primary_host == null ? 0 : this.is_primary_host.hashCode())) * -2128831035) ^ (this.fixed_fee == null ? 0 : this.fixed_fee.hashCode())) * -2128831035) ^ (this.maximum_fee == null ? 0 : this.maximum_fee.hashCode())) * -2128831035) ^ (this.minimum_fee == null ? 0 : this.minimum_fee.hashCode())) * -2128831035;
        if (this.amount_currency != null) {
            i = this.amount_currency.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ListingManagerAttribute{listing_manager_user_id=" + this.listing_manager_user_id + ", contract_percent=" + this.contract_percent + ", contract_cleaning_fee=" + this.contract_cleaning_fee + ", acceptance_date=" + this.acceptance_date + ", contract_start_date=" + this.contract_start_date + ", contract_end_date=" + this.contract_end_date + ", is_primary_host=" + this.is_primary_host + ", fixed_fee=" + this.fixed_fee + ", maximum_fee=" + this.maximum_fee + ", minimum_fee=" + this.minimum_fee + ", amount_currency=" + this.amount_currency + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
