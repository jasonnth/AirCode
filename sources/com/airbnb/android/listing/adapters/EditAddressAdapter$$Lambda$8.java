package com.airbnb.android.listing.adapters;

import com.airbnb.android.listing.utils.AddressFieldType;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;

final /* synthetic */ class EditAddressAdapter$$Lambda$8 implements Function {
    private final ImmutableMap arg$1;

    private EditAddressAdapter$$Lambda$8(ImmutableMap immutableMap) {
        this.arg$1 = immutableMap;
    }

    public static Function lambdaFactory$(ImmutableMap immutableMap) {
        return new EditAddressAdapter$$Lambda$8(immutableMap);
    }

    public Object apply(Object obj) {
        return this.arg$1.get((AddressFieldType) obj);
    }
}
