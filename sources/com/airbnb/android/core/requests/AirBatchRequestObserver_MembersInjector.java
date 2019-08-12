package com.airbnb.android.core.requests;

import com.airbnb.android.core.data.ConverterFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AirBatchRequestObserver_MembersInjector implements MembersInjector<AirBatchRequestObserver> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AirBatchRequestObserver_MembersInjector.class.desiredAssertionStatus());
    private final Provider<ConverterFactory> converterFactoryProvider;

    public AirBatchRequestObserver_MembersInjector(Provider<ConverterFactory> converterFactoryProvider2) {
        if ($assertionsDisabled || converterFactoryProvider2 != null) {
            this.converterFactoryProvider = converterFactoryProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<AirBatchRequestObserver> create(Provider<ConverterFactory> converterFactoryProvider2) {
        return new AirBatchRequestObserver_MembersInjector(converterFactoryProvider2);
    }

    public void injectMembers(AirBatchRequestObserver instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.converterFactory = (ConverterFactory) this.converterFactoryProvider.get();
    }

    public static void injectConverterFactory(AirBatchRequestObserver instance, Provider<ConverterFactory> converterFactoryProvider2) {
        instance.converterFactory = (ConverterFactory) converterFactoryProvider2.get();
    }
}
