package com.facebook.react.bridge;

import android.os.Bundle;

public class Arguments {
    public static WritableArray createArray() {
        return new WritableNativeArray();
    }

    public static WritableMap createMap() {
        return new WritableNativeMap();
    }

    public static WritableNativeArray fromJavaArgs(Object[] args) {
        WritableNativeArray arguments = new WritableNativeArray();
        for (Object argument : args) {
            if (argument == null) {
                arguments.pushNull();
            } else {
                Class argumentClass = argument.getClass();
                if (argumentClass == Boolean.class) {
                    arguments.pushBoolean(((Boolean) argument).booleanValue());
                } else if (argumentClass == Integer.class) {
                    arguments.pushDouble(((Integer) argument).doubleValue());
                } else if (argumentClass == Double.class) {
                    arguments.pushDouble(((Double) argument).doubleValue());
                } else if (argumentClass == Float.class) {
                    arguments.pushDouble(((Float) argument).doubleValue());
                } else if (argumentClass == String.class) {
                    arguments.pushString(argument.toString());
                } else if (argumentClass == WritableNativeMap.class) {
                    arguments.pushMap((WritableNativeMap) argument);
                } else if (argumentClass == WritableNativeArray.class) {
                    arguments.pushArray((WritableNativeArray) argument);
                } else {
                    throw new RuntimeException("Cannot convert argument of type " + argumentClass);
                }
            }
        }
        return arguments;
    }

    public static WritableArray fromArray(Object array) {
        int i = 0;
        WritableArray catalystArray = createArray();
        if (array instanceof String[]) {
            String[] strArr = (String[]) array;
            int length = strArr.length;
            while (i < length) {
                catalystArray.pushString(strArr[i]);
                i++;
            }
        } else if (array instanceof Bundle[]) {
            Bundle[] bundleArr = (Bundle[]) array;
            int length2 = bundleArr.length;
            while (i < length2) {
                catalystArray.pushMap(fromBundle(bundleArr[i]));
                i++;
            }
        } else if (array instanceof int[]) {
            int[] iArr = (int[]) array;
            int length3 = iArr.length;
            while (i < length3) {
                catalystArray.pushInt(iArr[i]);
                i++;
            }
        } else if (array instanceof float[]) {
            float[] fArr = (float[]) array;
            int length4 = fArr.length;
            while (i < length4) {
                catalystArray.pushDouble((double) fArr[i]);
                i++;
            }
        } else if (array instanceof double[]) {
            double[] dArr = (double[]) array;
            int length5 = dArr.length;
            while (i < length5) {
                catalystArray.pushDouble(dArr[i]);
                i++;
            }
        } else if (array instanceof boolean[]) {
            boolean[] array2 = (boolean[]) array;
            int length6 = array2.length;
            while (i < length6) {
                catalystArray.pushBoolean(array2[i]);
                i++;
            }
        } else {
            throw new IllegalArgumentException("Unknown array type " + array.getClass());
        }
        return catalystArray;
    }

    public static WritableMap fromBundle(Bundle bundle) {
        WritableMap map = createMap();
        for (String key : bundle.keySet()) {
            Object value = bundle.get(key);
            if (value == null) {
                map.putNull(key);
            } else if (value.getClass().isArray()) {
                map.putArray(key, fromArray(value));
            } else if (value instanceof String) {
                map.putString(key, (String) value);
            } else if (value instanceof Number) {
                if (value instanceof Integer) {
                    map.putInt(key, ((Integer) value).intValue());
                } else {
                    map.putDouble(key, ((Number) value).doubleValue());
                }
            } else if (value instanceof Boolean) {
                map.putBoolean(key, ((Boolean) value).booleanValue());
            } else if (value instanceof Bundle) {
                map.putMap(key, fromBundle((Bundle) value));
            } else {
                throw new IllegalArgumentException("Could not convert " + value.getClass());
            }
        }
        return map;
    }

    public static Bundle toBundle(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        Bundle bundle = new Bundle();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (readableMap.getType(key)) {
                case Null:
                    bundle.putString(key, null);
                    break;
                case Boolean:
                    bundle.putBoolean(key, readableMap.getBoolean(key));
                    break;
                case Number:
                    bundle.putDouble(key, readableMap.getDouble(key));
                    break;
                case String:
                    bundle.putString(key, readableMap.getString(key));
                    break;
                case Map:
                    bundle.putBundle(key, toBundle(readableMap.getMap(key)));
                    break;
                case Array:
                    throw new UnsupportedOperationException("Arrays aren't supported yet.");
                default:
                    throw new IllegalArgumentException("Could not convert object with key: " + key + ".");
            }
        }
        return bundle;
    }
}
