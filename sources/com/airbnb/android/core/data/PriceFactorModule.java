package com.airbnb.android.core.data;

import com.airbnb.android.core.models.PriceFactor;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class PriceFactorModule extends SimpleModule {
    public PriceFactorModule() {
        addSerializer(PriceFactor.class, new PriceFactorSerializer());
        addDeserializer(PriceFactor.class, new PriceFactorDeserializer());
    }

    public String getModuleName() {
        return getClass().getSimpleName();
    }

    public int hashCode() {
        return getClass().hashCode();
    }

    public boolean equals(Object o) {
        return this == o;
    }
}
