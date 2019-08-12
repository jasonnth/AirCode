package com.airbnb.android.core.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class LocationTypeaheadFilterForChina_MembersInjector implements MembersInjector<LocationTypeaheadFilterForChina> {
    static final /* synthetic */ boolean $assertionsDisabled = (!LocationTypeaheadFilterForChina_MembersInjector.class.desiredAssertionStatus());
    private final Provider<ObjectMapper> objectMapperLazyProvider;

    public LocationTypeaheadFilterForChina_MembersInjector(Provider<ObjectMapper> objectMapperLazyProvider2) {
        if ($assertionsDisabled || objectMapperLazyProvider2 != null) {
            this.objectMapperLazyProvider = objectMapperLazyProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<LocationTypeaheadFilterForChina> create(Provider<ObjectMapper> objectMapperLazyProvider2) {
        return new LocationTypeaheadFilterForChina_MembersInjector(objectMapperLazyProvider2);
    }

    public void injectMembers(LocationTypeaheadFilterForChina instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.objectMapperLazy = DoubleCheck.lazy(this.objectMapperLazyProvider);
    }

    public static void injectObjectMapperLazy(LocationTypeaheadFilterForChina instance, Provider<ObjectMapper> objectMapperLazyProvider2) {
        instance.objectMapperLazy = DoubleCheck.lazy(objectMapperLazyProvider2);
    }
}
