package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.NativeModule.NativeMethod;
import com.facebook.react.bridge.NativeModule.SyncNativeHook;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

public abstract class BaseJavaModule implements NativeModule {
    /* access modifiers changed from: private */
    public static final ArgumentExtractor<ReadableNativeArray> ARGUMENT_EXTRACTOR_ARRAY = new ArgumentExtractor<ReadableNativeArray>() {
        public ReadableNativeArray extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
            return jsArguments.getArray(atIndex);
        }
    };
    /* access modifiers changed from: private */
    public static final ArgumentExtractor<Boolean> ARGUMENT_EXTRACTOR_BOOLEAN = new ArgumentExtractor<Boolean>() {
        public Boolean extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
            return Boolean.valueOf(jsArguments.getBoolean(atIndex));
        }
    };
    /* access modifiers changed from: private */
    public static final ArgumentExtractor<Callback> ARGUMENT_EXTRACTOR_CALLBACK = new ArgumentExtractor<Callback>() {
        public Callback extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
            if (jsArguments.isNull(atIndex)) {
                return null;
            }
            return new CallbackImpl(catalystInstance, executorToken, (int) jsArguments.getDouble(atIndex));
        }
    };
    /* access modifiers changed from: private */
    public static final ArgumentExtractor<Double> ARGUMENT_EXTRACTOR_DOUBLE = new ArgumentExtractor<Double>() {
        public Double extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
            return Double.valueOf(jsArguments.getDouble(atIndex));
        }
    };
    /* access modifiers changed from: private */
    public static final ArgumentExtractor<Float> ARGUMENT_EXTRACTOR_FLOAT = new ArgumentExtractor<Float>() {
        public Float extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
            return Float.valueOf((float) jsArguments.getDouble(atIndex));
        }
    };
    /* access modifiers changed from: private */
    public static final ArgumentExtractor<Integer> ARGUMENT_EXTRACTOR_INTEGER = new ArgumentExtractor<Integer>() {
        public Integer extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
            return Integer.valueOf((int) jsArguments.getDouble(atIndex));
        }
    };
    /* access modifiers changed from: private */
    public static final ArgumentExtractor<ReadableMap> ARGUMENT_EXTRACTOR_MAP = new ArgumentExtractor<ReadableMap>() {
        public ReadableMap extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
            return jsArguments.getMap(atIndex);
        }
    };
    /* access modifiers changed from: private */
    public static final ArgumentExtractor<Promise> ARGUMENT_EXTRACTOR_PROMISE = new ArgumentExtractor<Promise>() {
        public int getJSArgumentsNeeded() {
            return 2;
        }

        public Promise extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
            return new PromiseImpl((Callback) BaseJavaModule.ARGUMENT_EXTRACTOR_CALLBACK.extractArgument(catalystInstance, executorToken, jsArguments, atIndex), (Callback) BaseJavaModule.ARGUMENT_EXTRACTOR_CALLBACK.extractArgument(catalystInstance, executorToken, jsArguments, atIndex + 1));
        }
    };
    /* access modifiers changed from: private */
    public static final ArgumentExtractor<String> ARGUMENT_EXTRACTOR_STRING = new ArgumentExtractor<String>() {
        public String extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
            return jsArguments.getString(atIndex);
        }
    };
    public static final String METHOD_TYPE_ASYNC = "async";
    public static final String METHOD_TYPE_PROMISE = "promise";
    public static final String METHOD_TYPE_SYNC = "sync";
    private Map<String, SyncNativeHook> mHooks;
    private Map<String, NativeMethod> mMethods;

    private static abstract class ArgumentExtractor<T> {
        public abstract T extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray readableNativeArray, int i);

        private ArgumentExtractor() {
        }

        public int getJSArgumentsNeeded() {
            return 1;
        }
    }

    public class JavaMethod implements NativeMethod {
        private final ArgumentExtractor[] mArgumentExtractors;
        private final Object[] mArguments;
        private final int mJSArgumentsNeeded;
        private Method mMethod;
        private final String mSignature;
        private final String mTraceName;
        private String mType = BaseJavaModule.METHOD_TYPE_ASYNC;

        public JavaMethod(Method method) {
            this.mMethod = method;
            this.mMethod.setAccessible(true);
            Class[] parameterTypes = method.getParameterTypes();
            this.mArgumentExtractors = buildArgumentExtractors(parameterTypes);
            this.mSignature = buildSignature(parameterTypes);
            this.mArguments = new Object[parameterTypes.length];
            this.mJSArgumentsNeeded = calculateJSArgumentsNeeded();
            this.mTraceName = BaseJavaModule.this.getName() + "." + this.mMethod.getName();
        }

        public Method getMethod() {
            return this.mMethod;
        }

        public String getSignature() {
            return this.mSignature;
        }

        private String buildSignature(Class[] paramTypes) {
            StringBuilder builder = new StringBuilder(paramTypes.length);
            builder.append("v.");
            int i = 0;
            while (i < paramTypes.length) {
                Class paramClass = paramTypes[i];
                if (paramClass == ExecutorToken.class) {
                    if (!BaseJavaModule.this.supportsWebWorkers()) {
                        throw new RuntimeException("Module " + BaseJavaModule.this + " doesn't support web workers, but " + this.mMethod.getName() + " takes an ExecutorToken.");
                    }
                } else if (paramClass == Promise.class) {
                    Assertions.assertCondition(i == paramTypes.length + -1, "Promise must be used as last parameter only");
                    this.mType = BaseJavaModule.METHOD_TYPE_PROMISE;
                }
                builder.append(BaseJavaModule.paramTypeToChar(paramClass));
                i++;
            }
            if (!BaseJavaModule.this.supportsWebWorkers() || builder.charAt(2) == 'T') {
                return builder.toString();
            }
            throw new RuntimeException("Module " + BaseJavaModule.this + " supports web workers, but " + this.mMethod.getName() + "does not take an ExecutorToken as its first parameter.");
        }

        private ArgumentExtractor[] buildArgumentExtractors(Class[] paramTypes) {
            boolean z;
            int executorTokenOffset = 0;
            if (BaseJavaModule.this.supportsWebWorkers()) {
                if (paramTypes[0] != ExecutorToken.class) {
                    throw new RuntimeException("Module " + BaseJavaModule.this + " supports web workers, but " + this.mMethod.getName() + "does not take an ExecutorToken as its first parameter.");
                }
                executorTokenOffset = 1;
            }
            ArgumentExtractor[] argumentExtractors = new ArgumentExtractor[(paramTypes.length - executorTokenOffset)];
            for (int i = 0; i < paramTypes.length - executorTokenOffset; i += argumentExtractors[i].getJSArgumentsNeeded()) {
                int paramIndex = i + executorTokenOffset;
                Class argumentClass = paramTypes[paramIndex];
                if (argumentClass == Boolean.class || argumentClass == Boolean.TYPE) {
                    argumentExtractors[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_BOOLEAN;
                } else if (argumentClass == Integer.class || argumentClass == Integer.TYPE) {
                    argumentExtractors[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_INTEGER;
                } else if (argumentClass == Double.class || argumentClass == Double.TYPE) {
                    argumentExtractors[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_DOUBLE;
                } else if (argumentClass == Float.class || argumentClass == Float.TYPE) {
                    argumentExtractors[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_FLOAT;
                } else if (argumentClass == String.class) {
                    argumentExtractors[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_STRING;
                } else if (argumentClass == Callback.class) {
                    argumentExtractors[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_CALLBACK;
                } else if (argumentClass == Promise.class) {
                    argumentExtractors[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_PROMISE;
                    if (paramIndex == paramTypes.length - 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Assertions.assertCondition(z, "Promise must be used as last parameter only");
                    this.mType = BaseJavaModule.METHOD_TYPE_PROMISE;
                } else if (argumentClass == ReadableMap.class) {
                    argumentExtractors[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_MAP;
                } else if (argumentClass == ReadableArray.class) {
                    argumentExtractors[i] = BaseJavaModule.ARGUMENT_EXTRACTOR_ARRAY;
                } else {
                    throw new RuntimeException("Got unknown argument class: " + argumentClass.getSimpleName());
                }
            }
            return argumentExtractors;
        }

        private int calculateJSArgumentsNeeded() {
            int n = 0;
            for (ArgumentExtractor extractor : this.mArgumentExtractors) {
                n += extractor.getJSArgumentsNeeded();
            }
            return n;
        }

        private String getAffectedRange(int startIndex, int jsArgumentsNeeded) {
            return jsArgumentsNeeded > 1 ? "" + startIndex + "-" + ((startIndex + jsArgumentsNeeded) - 1) : "" + startIndex;
        }

        public void invoke(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray parameters) {
            int i;
            int jsArgumentsConsumed;
            SystraceMessage.beginSection(0, "callJavaModuleMethod").arg("method", (Object) this.mTraceName).flush();
            try {
                if (this.mJSArgumentsNeeded != parameters.size()) {
                    throw new NativeArgumentsParseException(BaseJavaModule.this.getName() + "." + this.mMethod.getName() + " got " + parameters.size() + " arguments, expected " + this.mJSArgumentsNeeded);
                }
                i = 0;
                jsArgumentsConsumed = 0;
                int executorTokenOffset = 0;
                if (BaseJavaModule.this.supportsWebWorkers()) {
                    this.mArguments[0] = executorToken;
                    executorTokenOffset = 1;
                }
                while (i < this.mArgumentExtractors.length) {
                    this.mArguments[i + executorTokenOffset] = this.mArgumentExtractors[i].extractArgument(catalystInstance, executorToken, parameters, jsArgumentsConsumed);
                    jsArgumentsConsumed += this.mArgumentExtractors[i].getJSArgumentsNeeded();
                    i++;
                }
                this.mMethod.invoke(BaseJavaModule.this, this.mArguments);
                Systrace.endSection(0);
            } catch (IllegalArgumentException ie) {
                throw new RuntimeException("Could not invoke " + BaseJavaModule.this.getName() + "." + this.mMethod.getName(), ie);
            } catch (IllegalAccessException iae) {
                throw new RuntimeException("Could not invoke " + BaseJavaModule.this.getName() + "." + this.mMethod.getName(), iae);
            } catch (InvocationTargetException ite) {
                if (ite.getCause() instanceof RuntimeException) {
                    throw ((RuntimeException) ite.getCause());
                }
                throw new RuntimeException("Could not invoke " + BaseJavaModule.this.getName() + "." + this.mMethod.getName(), ite);
            } catch (UnexpectedNativeTypeException e) {
                throw new NativeArgumentsParseException(e.getMessage() + " (constructing arguments for " + BaseJavaModule.this.getName() + "." + this.mMethod.getName() + " at argument index " + getAffectedRange(jsArgumentsConsumed, this.mArgumentExtractors[i].getJSArgumentsNeeded()) + ")", e);
            } catch (Throwable th) {
                Systrace.endSection(0);
                throw th;
            }
        }

        public String getType() {
            return this.mType;
        }
    }

    public class SyncJavaHook implements SyncNativeHook {
        private Method mMethod;
        private final String mSignature;

        public SyncJavaHook(Method method) {
            this.mMethod = method;
            this.mMethod.setAccessible(true);
            this.mSignature = buildSignature(method);
        }

        public Method getMethod() {
            return this.mMethod;
        }

        public String getSignature() {
            return this.mSignature;
        }

        private String buildSignature(Method method) {
            Class[] paramTypes = method.getParameterTypes();
            StringBuilder builder = new StringBuilder(paramTypes.length + 2);
            builder.append(BaseJavaModule.returnTypeToChar(method.getReturnType()));
            builder.append('.');
            int i = 0;
            while (i < paramTypes.length) {
                Class paramClass = paramTypes[i];
                if (paramClass == ExecutorToken.class) {
                    if (!BaseJavaModule.this.supportsWebWorkers()) {
                        throw new RuntimeException("Module " + BaseJavaModule.this + " doesn't support web workers, but " + this.mMethod.getName() + " takes an ExecutorToken.");
                    }
                } else if (paramClass == Promise.class) {
                    Assertions.assertCondition(i == paramTypes.length + -1, "Promise must be used as last parameter only");
                }
                builder.append(BaseJavaModule.paramTypeToChar(paramClass));
                i++;
            }
            return builder.toString();
        }
    }

    private void findMethods() {
        Method[] targetMethods;
        if (this.mMethods == null) {
            Systrace.beginSection(0, "findMethods");
            this.mMethods = new HashMap();
            this.mHooks = new HashMap();
            for (Method targetMethod : getClass().getDeclaredMethods()) {
                if (targetMethod.getAnnotation(ReactMethod.class) != null) {
                    String methodName = targetMethod.getName();
                    if (this.mHooks.containsKey(methodName) || this.mMethods.containsKey(methodName)) {
                        throw new IllegalArgumentException("Java Module " + getName() + " sync method name already registered: " + methodName);
                    }
                    this.mMethods.put(methodName, new JavaMethod(targetMethod));
                }
                if (targetMethod.getAnnotation(ReactSyncHook.class) != null) {
                    String methodName2 = targetMethod.getName();
                    if (this.mHooks.containsKey(methodName2) || this.mMethods.containsKey(methodName2)) {
                        throw new IllegalArgumentException("Java Module " + getName() + " sync method name already registered: " + methodName2);
                    }
                    this.mHooks.put(methodName2, new SyncJavaHook(targetMethod));
                }
            }
            Systrace.endSection(0);
        }
    }

    public final Map<String, NativeMethod> getMethods() {
        findMethods();
        return (Map) Assertions.assertNotNull(this.mMethods);
    }

    public Map<String, Object> getConstants() {
        return null;
    }

    public final Map<String, SyncNativeHook> getSyncHooks() {
        findMethods();
        return (Map) Assertions.assertNotNull(this.mHooks);
    }

    public void initialize() {
    }

    public boolean canOverrideExistingModule() {
        return false;
    }

    public void onCatalystInstanceDestroy() {
    }

    public boolean supportsWebWorkers() {
        return false;
    }

    /* access modifiers changed from: private */
    public static char paramTypeToChar(Class paramClass) {
        char tryCommon = commonTypeToChar(paramClass);
        if (tryCommon != 0) {
            return tryCommon;
        }
        if (paramClass == ExecutorToken.class) {
            return 'T';
        }
        if (paramClass == Callback.class) {
            return 'X';
        }
        if (paramClass == Promise.class) {
            return 'P';
        }
        if (paramClass == ReadableMap.class) {
            return 'M';
        }
        if (paramClass == ReadableArray.class) {
            return 'A';
        }
        throw new RuntimeException("Got unknown param class: " + paramClass.getSimpleName());
    }

    /* access modifiers changed from: private */
    public static char returnTypeToChar(Class returnClass) {
        char tryCommon = commonTypeToChar(returnClass);
        if (tryCommon != 0) {
            return tryCommon;
        }
        if (returnClass == Void.TYPE) {
            return 'v';
        }
        if (returnClass == WritableMap.class) {
            return 'M';
        }
        if (returnClass == WritableArray.class) {
            return 'A';
        }
        throw new RuntimeException("Got unknown return class: " + returnClass.getSimpleName());
    }

    private static char commonTypeToChar(Class typeClass) {
        if (typeClass == Boolean.TYPE) {
            return 'z';
        }
        if (typeClass == Boolean.class) {
            return Matrix.MATRIX_TYPE_ZERO;
        }
        if (typeClass == Integer.TYPE) {
            return 'i';
        }
        if (typeClass == Integer.class) {
            return 'I';
        }
        if (typeClass == Double.TYPE) {
            return 'd';
        }
        if (typeClass == Double.class) {
            return 'D';
        }
        if (typeClass == Float.TYPE) {
            return 'f';
        }
        if (typeClass == Float.class) {
            return 'F';
        }
        if (typeClass == String.class) {
            return 'S';
        }
        return 0;
    }
}
