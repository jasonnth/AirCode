package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

public class PropertyBasedObjectIdGenerator extends PropertyGenerator {
    public PropertyBasedObjectIdGenerator(Class<?> scope) {
        super(scope);
    }

    public Object generateId(Object forPojo) {
        throw new UnsupportedOperationException();
    }

    /* Debug info: failed to restart local var, previous not found, register: 1 */
    public ObjectIdGenerator<Object> forScope(Class<?> scope) {
        return scope == this._scope ? this : new PropertyBasedObjectIdGenerator(scope);
    }

    public ObjectIdGenerator<Object> newForSerialization(Object context) {
        return this;
    }

    public IdKey key(Object key) {
        if (key == null) {
            return null;
        }
        return new IdKey(getClass(), this._scope, key);
    }
}
