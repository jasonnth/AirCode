package com.airbnb.android.core.modules;

import com.airbnb.android.airdate.AirDateModule;
import com.airbnb.android.core.data.AirEventModule;
import com.airbnb.android.core.data.ConverterFactory;
import com.airbnb.android.core.data.JacksonConverterFactory;
import com.airbnb.android.core.data.PriceFactorModule;
import com.airbnb.android.core.data.QueryStrapModule;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import dagger.Lazy;

public class DataModule {
    /* access modifiers changed from: 0000 */
    public ObjectMapper provideObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true).setSerializationInclusion(Include.NON_NULL).setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE).registerModules(new AirDateModule(), new QueryStrapModule(), new AirEventModule(), new PriceFactorModule());
        VisibilityChecker<?> serializationConfig = objectMapper.getSerializationConfig().getDefaultVisibilityChecker().withFieldVisibility(Visibility.PROTECTED_AND_PUBLIC).withGetterVisibility(Visibility.NONE).withSetterVisibility(Visibility.NONE).withIsGetterVisibility(Visibility.NONE);
        return objectMapper.setVisibility(serializationConfig).setVisibility(objectMapper.getDeserializationConfig().getDefaultVisibilityChecker().withFieldVisibility(Visibility.PROTECTED_AND_PUBLIC).withGetterVisibility(Visibility.NONE).withSetterVisibility(Visibility.PUBLIC_ONLY).withIsGetterVisibility(Visibility.NONE));
    }

    /* access modifiers changed from: 0000 */
    public ConverterFactory provideConverterFactory(Lazy<ObjectMapper> objectMapper) {
        return JacksonConverterFactory.create(objectMapper);
    }
}
