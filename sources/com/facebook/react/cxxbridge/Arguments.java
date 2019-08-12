package com.facebook.react.cxxbridge;

import android.os.Bundle;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Arguments {
    private static Object makeNativeObject(Object object) {
        if (object == null) {
            return null;
        }
        if ((object instanceof Float) || (object instanceof Long) || (object instanceof Byte) || (object instanceof Short)) {
            return new Double(((Number) object).doubleValue());
        }
        if (object.getClass().isArray()) {
            return makeNativeArray(object);
        }
        if (object instanceof List) {
            return makeNativeArray((List) object);
        }
        if (object instanceof Map) {
            return makeNativeMap((Map) object);
        }
        if (object instanceof Bundle) {
            return makeNativeMap((Bundle) object);
        }
        return object;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<java.lang.Object>, for r6v0, types: [java.util.List, java.util.List<java.lang.Object>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.react.bridge.WritableNativeArray makeNativeArray(java.util.List<java.lang.Object> r6) {
        /*
            com.facebook.react.bridge.WritableNativeArray r1 = new com.facebook.react.bridge.WritableNativeArray
            r1.<init>()
            if (r6 != 0) goto L_0x0008
        L_0x0007:
            return r1
        L_0x0008:
            java.util.Iterator r2 = r6.iterator()
        L_0x000c:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0007
            java.lang.Object r0 = r2.next()
            java.lang.Object r0 = makeNativeObject(r0)
            if (r0 != 0) goto L_0x0020
            r1.pushNull()
            goto L_0x000c
        L_0x0020:
            boolean r3 = r0 instanceof java.lang.Boolean
            if (r3 == 0) goto L_0x002e
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r3 = r0.booleanValue()
            r1.pushBoolean(r3)
            goto L_0x000c
        L_0x002e:
            boolean r3 = r0 instanceof java.lang.Integer
            if (r3 == 0) goto L_0x003c
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r3 = r0.intValue()
            r1.pushInt(r3)
            goto L_0x000c
        L_0x003c:
            boolean r3 = r0 instanceof java.lang.Double
            if (r3 == 0) goto L_0x004a
            java.lang.Double r0 = (java.lang.Double) r0
            double r4 = r0.doubleValue()
            r1.pushDouble(r4)
            goto L_0x000c
        L_0x004a:
            boolean r3 = r0 instanceof java.lang.String
            if (r3 == 0) goto L_0x0054
            java.lang.String r0 = (java.lang.String) r0
            r1.pushString(r0)
            goto L_0x000c
        L_0x0054:
            boolean r3 = r0 instanceof com.facebook.react.bridge.WritableNativeArray
            if (r3 == 0) goto L_0x005e
            com.facebook.react.bridge.WritableNativeArray r0 = (com.facebook.react.bridge.WritableNativeArray) r0
            r1.pushArray(r0)
            goto L_0x000c
        L_0x005e:
            boolean r3 = r0 instanceof com.facebook.react.bridge.WritableNativeMap
            if (r3 == 0) goto L_0x0068
            com.facebook.react.bridge.WritableNativeMap r0 = (com.facebook.react.bridge.WritableNativeMap) r0
            r1.pushMap(r0)
            goto L_0x000c
        L_0x0068:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Could not convert "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.Class r4 = r0.getClass()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.cxxbridge.Arguments.makeNativeArray(java.util.List):com.facebook.react.bridge.WritableNativeArray");
    }

    public static <T> WritableNativeArray makeNativeArray(final Object objects) {
        if (objects == null) {
            return new WritableNativeArray();
        }
        return makeNativeArray((List) new AbstractList() {
            public int size() {
                return Array.getLength(objects);
            }

            public Object get(int index) {
                return Array.get(objects, index);
            }
        });
    }

    private static void addEntry(WritableNativeMap nativeMap, String key, Object value) {
        Object value2 = makeNativeObject(value);
        if (value2 == null) {
            nativeMap.putNull(key);
        } else if (value2 instanceof Boolean) {
            nativeMap.putBoolean(key, ((Boolean) value2).booleanValue());
        } else if (value2 instanceof Integer) {
            nativeMap.putInt(key, ((Integer) value2).intValue());
        } else if (value2 instanceof Number) {
            nativeMap.putDouble(key, ((Number) value2).doubleValue());
        } else if (value2 instanceof String) {
            nativeMap.putString(key, (String) value2);
        } else if (value2 instanceof WritableNativeArray) {
            nativeMap.putArray(key, (WritableNativeArray) value2);
        } else if (value2 instanceof WritableNativeMap) {
            nativeMap.putMap(key, (WritableNativeMap) value2);
        } else {
            throw new IllegalArgumentException("Could not convert " + value2.getClass());
        }
    }

    public static WritableNativeMap makeNativeMap(Map<String, Object> objects) {
        WritableNativeMap nativeMap = new WritableNativeMap();
        if (objects != null) {
            for (Entry<String, Object> entry : objects.entrySet()) {
                addEntry(nativeMap, (String) entry.getKey(), entry.getValue());
            }
        }
        return nativeMap;
    }

    public static WritableNativeMap makeNativeMap(Bundle bundle) {
        WritableNativeMap nativeMap = new WritableNativeMap();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                addEntry(nativeMap, key, bundle.get(key));
            }
        }
        return nativeMap;
    }
}
