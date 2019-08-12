package com.airbnb.jitney.event.logging.ProductInfo.p210v1;

import com.airbnb.jitney.event.logging.ProductType.p211v1.C2589ProductType;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;

/* renamed from: com.airbnb.jitney.event.logging.ProductInfo.v1.ProductInfo */
public final class C2587ProductInfo implements Struct {
    public static final Adapter<C2587ProductInfo, Object> ADAPTER = new ProductInfoAdapter();
    public final C2589ProductType product_type;
    public final Long trip_template_id;

    /* renamed from: com.airbnb.jitney.event.logging.ProductInfo.v1.ProductInfo$ProductInfoAdapter */
    private static final class ProductInfoAdapter implements Adapter<C2587ProductInfo, Object> {
        private ProductInfoAdapter() {
        }

        public void write(Protocol protocol, C2587ProductInfo struct) throws IOException {
            protocol.writeStructBegin("ProductInfo");
            protocol.writeFieldBegin("product_type", 1, 8);
            protocol.writeI32(struct.product_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("trip_template_id", 2, 10);
            protocol.writeI64(struct.trip_template_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C2587ProductInfo)) {
            return false;
        }
        C2587ProductInfo that = (C2587ProductInfo) other;
        if ((this.product_type == that.product_type || this.product_type.equals(that.product_type)) && (this.trip_template_id == that.trip_template_id || this.trip_template_id.equals(that.trip_template_id))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((16777619 ^ this.product_type.hashCode()) * -2128831035) ^ this.trip_template_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ProductInfo{product_type=" + this.product_type + ", trip_template_id=" + this.trip_template_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
