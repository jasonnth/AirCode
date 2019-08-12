package com.facebook.react.bridge;

import java.lang.reflect.Constructor;
import javax.inject.Provider;

public class ModuleSpec {
    /* access modifiers changed from: private */
    public static final Class[] CONTEXT_SIGNATURE = {ReactApplicationContext.class};
    /* access modifiers changed from: private */
    public static final Class[] EMPTY_SIGNATURE = new Class[0];
    private final Provider<? extends NativeModule> mProvider;
    private final Class<? extends NativeModule> mType;

    private static abstract class ConstructorProvider implements Provider<NativeModule> {
        protected Constructor<? extends NativeModule> mConstructor;

        public ConstructorProvider(Class<? extends NativeModule> cls, Class[] signature) {
        }

        /* access modifiers changed from: protected */
        public Constructor<? extends NativeModule> getConstructor(Class<? extends NativeModule> mType, Class[] signature) throws NoSuchMethodException {
            if (this.mConstructor != null) {
                return this.mConstructor;
            }
            return mType.getConstructor(signature);
        }
    }

    public static ModuleSpec simple(final Class<? extends NativeModule> type) {
        return new ModuleSpec(type, new ConstructorProvider(EMPTY_SIGNATURE, type) {
            public NativeModule get() {
                try {
                    return (NativeModule) getConstructor(type, ModuleSpec.EMPTY_SIGNATURE).newInstance(new Object[0]);
                } catch (Exception e) {
                    throw new RuntimeException("ModuleSpec with class: " + type.getName(), e);
                }
            }
        });
    }

    public static ModuleSpec simple(final Class<? extends NativeModule> type, final ReactApplicationContext context) {
        return new ModuleSpec(type, new ConstructorProvider(CONTEXT_SIGNATURE, type) {
            public NativeModule get() {
                try {
                    return (NativeModule) getConstructor(type, ModuleSpec.CONTEXT_SIGNATURE).newInstance(new Object[]{context});
                } catch (Exception e) {
                    throw new RuntimeException("ModuleSpec with class: " + type.getName(), e);
                }
            }
        });
    }

    public ModuleSpec(Class<? extends NativeModule> type, Provider<? extends NativeModule> provider) {
        this.mType = type;
        this.mProvider = provider;
    }

    public Class<? extends NativeModule> getType() {
        return this.mType;
    }

    public Provider<? extends NativeModule> getProvider() {
        return this.mProvider;
    }
}
