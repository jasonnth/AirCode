package com.airbnb.android.core.requests;

import com.airbnb.android.core.data.ConverterFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AirBatchRequest_MembersInjector implements MembersInjector<AirBatchRequest> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AirBatchRequest_MembersInjector.class.desiredAssertionStatus());
    private final Provider<ConverterFactory> converterFactoryProvider;

    public AirBatchRequest_MembersInjector(Provider<ConverterFactory> converterFactoryProvider2) {
        if ($assertionsDisabled || converterFactoryProvider2 != null) {
            this.converterFactoryProvider = converterFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<AirBatchRequest> create(Provider<ConverterFactory> converterFactoryProvider2) {
        return new AirBatchRequest_MembersInjector(converterFactoryProvider2);
    }

    public void injectMembers(AirBatchRequest instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.converterFactory = (ConverterFactory) this.converterFactoryProvider.get();
    }

    public static void injectConverterFactory(AirBatchRequest instance, Provider<ConverterFactory> converterFactoryProvider2) {
        instance.converterFactory = (ConverterFactory) converterFactoryProvider2.get();
    }
}
