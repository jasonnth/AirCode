package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.p307io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@JacksonStdImpl
public class StdKeyDeserializer extends KeyDeserializer implements Serializable {
    protected final FromStringDeserializer<?> _deser;
    protected final Class<?> _keyClass;
    protected final int _kind;

    @JacksonStdImpl
    static final class EnumKD extends StdKeyDeserializer {
        protected final EnumResolver _byNameResolver;
        protected EnumResolver _byToStringResolver;
        protected final AnnotatedMethod _factory;

        protected EnumKD(EnumResolver er, AnnotatedMethod factory) {
            super(-1, er.getEnumClass());
            this._byNameResolver = er;
            this._factory = factory;
        }

        public Object _parse(String key, DeserializationContext ctxt) throws IOException {
            if (this._factory != null) {
                try {
                    return this._factory.call1(key);
                } catch (Exception e) {
                    ClassUtil.unwrapAndThrowAsIAE(e);
                }
            }
            EnumResolver res = ctxt.isEnabled(DeserializationFeature.READ_ENUMS_USING_TO_STRING) ? _getToStringResolver(ctxt) : this._byNameResolver;
            Enum<?> e2 = res.findEnum(key);
            if (e2 != null || ctxt.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return e2;
            }
            return ctxt.handleWeirdKey(this._keyClass, key, "not one of values excepted for Enum class: %s", res.getEnumIds());
        }

        private EnumResolver _getToStringResolver(DeserializationContext ctxt) {
            EnumResolver res = this._byToStringResolver;
            if (res == null) {
                synchronized (this) {
                    res = EnumResolver.constructUnsafeUsingToString(this._byNameResolver.getEnumClass(), ctxt.getAnnotationIntrospector());
                }
            }
            return res;
        }
    }

    @JacksonStdImpl
    static final class StringKD extends StdKeyDeserializer {
        private static final StringKD sObject = new StringKD(Object.class);
        private static final StringKD sString = new StringKD(String.class);

        private StringKD(Class<?> nominalType) {
            super(-1, nominalType);
        }

        public static StringKD forType(Class<?> nominalType) {
            if (nominalType == String.class) {
                return sString;
            }
            if (nominalType == Object.class) {
                return sObject;
            }
            return new StringKD(nominalType);
        }

        public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return key;
        }
    }

    static final class DelegatingKD extends KeyDeserializer implements Serializable {
        protected final JsonDeserializer<?> _delegate;
        protected final Class<?> _keyClass;

        protected DelegatingKD(Class<?> cls, JsonDeserializer<?> deser) {
            this._keyClass = cls;
            this._delegate = deser;
        }

        public final Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
            if (key == null) {
                return null;
            }
            TokenBuffer tb = new TokenBuffer(ctxt.getParser(), ctxt);
            tb.writeString(key);
            try {
                JsonParser p = tb.asParser();
                p.nextToken();
                Object result = this._delegate.deserialize(p, ctxt);
                if (result == null) {
                    return ctxt.handleWeirdKey(this._keyClass, key, "not a valid representation", new Object[0]);
                }
                return result;
            } catch (Exception re) {
                return ctxt.handleWeirdKey(this._keyClass, key, "not a valid representation: %s", re.getMessage());
            }
        }
    }

    static final class StringCtorKeyDeserializer extends StdKeyDeserializer {
        protected final Constructor<?> _ctor;

        public StringCtorKeyDeserializer(Constructor<?> ctor) {
            super(-1, ctor.getDeclaringClass());
            this._ctor = ctor;
        }

        public Object _parse(String key, DeserializationContext ctxt) throws Exception {
            return this._ctor.newInstance(new Object[]{key});
        }
    }

    static final class StringFactoryKeyDeserializer extends StdKeyDeserializer {
        final Method _factoryMethod;

        public StringFactoryKeyDeserializer(Method fm) {
            super(-1, fm.getDeclaringClass());
            this._factoryMethod = fm;
        }

        public Object _parse(String key, DeserializationContext ctxt) throws Exception {
            return this._factoryMethod.invoke(null, new Object[]{key});
        }
    }

    protected StdKeyDeserializer(int kind, Class<?> cls) {
        this(kind, cls, null);
    }

    protected StdKeyDeserializer(int kind, Class<?> cls, FromStringDeserializer<?> deser) {
        this._kind = kind;
        this._keyClass = cls;
        this._deser = deser;
    }

    public static StdKeyDeserializer forType(Class<?> raw) {
        int kind;
        if (raw == String.class || raw == Object.class || raw == CharSequence.class) {
            return StringKD.forType(raw);
        }
        if (raw == UUID.class) {
            kind = 12;
        } else if (raw == Integer.class) {
            kind = 5;
        } else if (raw == Long.class) {
            kind = 6;
        } else if (raw == Date.class) {
            kind = 10;
        } else if (raw == Calendar.class) {
            kind = 11;
        } else if (raw == Boolean.class) {
            kind = 1;
        } else if (raw == Byte.class) {
            kind = 2;
        } else if (raw == Character.class) {
            kind = 4;
        } else if (raw == Short.class) {
            kind = 3;
        } else if (raw == Float.class) {
            kind = 7;
        } else if (raw == Double.class) {
            kind = 8;
        } else if (raw == URI.class) {
            kind = 13;
        } else if (raw == URL.class) {
            kind = 14;
        } else if (raw == Class.class) {
            kind = 15;
        } else if (raw == Locale.class) {
            return new StdKeyDeserializer(9, raw, FromStringDeserializer.findDeserializer(Locale.class));
        } else {
            if (raw == Currency.class) {
                return new StdKeyDeserializer(16, raw, FromStringDeserializer.findDeserializer(Currency.class));
            }
            return null;
        }
        return new StdKeyDeserializer(kind, raw);
    }

    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        if (key == null) {
            return null;
        }
        try {
            Object result = _parse(key, ctxt);
            if (result != null) {
                return result;
            }
            if (!this._keyClass.isEnum() || !ctxt.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return ctxt.handleWeirdKey(this._keyClass, key, "not a valid representation", new Object[0]);
            }
            return null;
        } catch (Exception re) {
            return ctxt.handleWeirdKey(this._keyClass, key, "not a valid representation, problem: (%s) %s", re.getClass().getName(), re.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public Object _parse(String key, DeserializationContext ctxt) throws Exception {
        switch (this._kind) {
            case 1:
                if ("true".equals(key)) {
                    return Boolean.TRUE;
                }
                if (InternalLogger.EVENT_PARAM_EXTRAS_FALSE.equals(key)) {
                    return Boolean.FALSE;
                }
                return ctxt.handleWeirdKey(this._keyClass, key, "value not 'true' or 'false'", new Object[0]);
            case 2:
                int value = _parseInt(key);
                if (value < -128 || value > 255) {
                    return ctxt.handleWeirdKey(this._keyClass, key, "overflow, value can not be represented as 8-bit value", new Object[0]);
                }
                return Byte.valueOf((byte) value);
            case 3:
                int value2 = _parseInt(key);
                if (value2 < -32768 || value2 > 32767) {
                    return ctxt.handleWeirdKey(this._keyClass, key, "overflow, value can not be represented as 16-bit value", new Object[0]);
                }
                return Short.valueOf((short) value2);
            case 4:
                if (key.length() == 1) {
                    return Character.valueOf(key.charAt(0));
                }
                return ctxt.handleWeirdKey(this._keyClass, key, "can only convert 1-character Strings", new Object[0]);
            case 5:
                return Integer.valueOf(_parseInt(key));
            case 6:
                return Long.valueOf(_parseLong(key));
            case 7:
                return Float.valueOf((float) _parseDouble(key));
            case 8:
                return Double.valueOf(_parseDouble(key));
            case 9:
                try {
                    return this._deser._deserialize(key, ctxt);
                } catch (IOException e) {
                    return ctxt.handleWeirdKey(this._keyClass, key, "unable to parse key as locale", new Object[0]);
                }
            case 10:
                return ctxt.parseDate(key);
            case 11:
                Date date = ctxt.parseDate(key);
                if (date == null) {
                    return null;
                }
                return ctxt.constructCalendar(date);
            case 12:
                try {
                    return UUID.fromString(key);
                } catch (Exception e2) {
                    return ctxt.handleWeirdKey(this._keyClass, key, "problem: %s", e2.getMessage());
                }
            case 13:
                try {
                    return URI.create(key);
                } catch (Exception e3) {
                    return ctxt.handleWeirdKey(this._keyClass, key, "problem: %s", e3.getMessage());
                }
            case 14:
                try {
                    return new URL(key);
                } catch (MalformedURLException e4) {
                    return ctxt.handleWeirdKey(this._keyClass, key, "problem: %s", e4.getMessage());
                }
            case 15:
                try {
                    return ctxt.findClass(key);
                } catch (Exception e5) {
                    return ctxt.handleWeirdKey(this._keyClass, key, "unable to parse key as Class", new Object[0]);
                }
            case 16:
                try {
                    return this._deser._deserialize(key, ctxt);
                } catch (IOException e6) {
                    return ctxt.handleWeirdKey(this._keyClass, key, "unable to parse key as currency", new Object[0]);
                }
            default:
                throw new IllegalStateException("Internal error: unknown key type " + this._keyClass);
        }
    }

    /* access modifiers changed from: protected */
    public int _parseInt(String key) throws IllegalArgumentException {
        return Integer.parseInt(key);
    }

    /* access modifiers changed from: protected */
    public long _parseLong(String key) throws IllegalArgumentException {
        return Long.parseLong(key);
    }

    /* access modifiers changed from: protected */
    public double _parseDouble(String key) throws IllegalArgumentException {
        return NumberInput.parseDouble(key);
    }
}
