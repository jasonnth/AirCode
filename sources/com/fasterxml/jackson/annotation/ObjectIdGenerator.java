package com.fasterxml.jackson.annotation;

import java.io.Serializable;

public abstract class ObjectIdGenerator<T> implements Serializable {

    public static final class IdKey implements Serializable {
        private final int hashCode;
        public final Object key;
        public final Class<?> scope;
        public final Class<?> type;

        public IdKey(Class<?> type2, Class<?> scope2, Object key2) {
            if (key2 == null) {
                throw new IllegalArgumentException("Can not construct IdKey for null key");
            }
            this.type = type2;
            this.scope = scope2;
            this.key = key2;
            int h = key2.hashCode() + type2.getName().hashCode();
            if (scope2 != null) {
                h ^= scope2.getName().hashCode();
            }
            this.hashCode = h;
        }

        public int hashCode() {
            return this.hashCode;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o == null) {
                return false;
            }
            if (o.getClass() != getClass()) {
                return false;
            }
            IdKey other = (IdKey) o;
            if (other.key.equals(this.key) && other.type == this.type && other.scope == this.scope) {
                return true;
            }
            return false;
        }

        public String toString() {
            String name;
            String name2;
            String str = "[ObjectId: key=%s, type=%s, scope=%s]";
            Object[] objArr = new Object[3];
            objArr[0] = this.key;
            if (this.type == null) {
                name = "NONE";
            } else {
                name = this.type.getName();
            }
            objArr[1] = name;
            if (this.scope == null) {
                name2 = "NONE";
            } else {
                name2 = this.scope.getName();
            }
            objArr[2] = name2;
            return String.format(str, objArr);
        }
    }

    public abstract boolean canUseFor(ObjectIdGenerator<?> objectIdGenerator);

    public abstract ObjectIdGenerator<T> forScope(Class<?> cls);

    public abstract T generateId(Object obj);

    public abstract Class<?> getScope();

    public abstract IdKey key(Object obj);

    public abstract ObjectIdGenerator<T> newForSerialization(Object obj);

    public boolean maySerializeAsObject() {
        return false;
    }

    public boolean isValidReferencePropertyName(String name, Object parser) {
        return false;
    }
}
