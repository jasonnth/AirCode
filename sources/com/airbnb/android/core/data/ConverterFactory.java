package com.airbnb.android.core.data;

import com.airbnb.android.aireventlogger.Converter;
import java.lang.reflect.Type;
import retrofit2.Converter.Factory;

public abstract class ConverterFactory extends Factory implements Converter.Factory {
    public abstract Converter<?> get(Type type);
}
