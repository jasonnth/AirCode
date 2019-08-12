package com.airbnb.android.lib.payments.networking.requests.requestbodies;

import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.ProductParam;
import java.util.List;

public abstract class HomesBillPriceQuoteRequestBody extends BillPriceQuoteRequestBodyV2 {

    public static abstract class Builder {
        /* access modifiers changed from: 0000 */
        public abstract HomesBillPriceQuoteRequestBody autoBuild();

        public abstract Builder paymentParams(PaymentParam paymentParam);

        public abstract Builder products(List<ProductParam> list);

        /* access modifiers changed from: 0000 */
        public abstract Builder version(int i);

        public HomesBillPriceQuoteRequestBody build() {
            version(2);
            return autoBuild();
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
