package com.google.gson.jpush;

import java.lang.reflect.Type;

public interface JsonSerializationContext {
    JsonElement serialize(Object obj);

    JsonElement serialize(Object obj, Type type);
}
