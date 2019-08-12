package com.google.gson.jpush;

import java.lang.reflect.Field;

public interface FieldNamingStrategy {
    String translateName(Field field);
}
