package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter.Factory;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Moshi {
    static final List<Factory> BUILT_IN_FACTORIES = new ArrayList(5);
    private final Map<Object, JsonAdapter<?>> adapterCache = new LinkedHashMap();
    private final List<Factory> factories;
    private final ThreadLocal<List<DeferredAdapter<?>>> reentrantCalls = new ThreadLocal<>();

    public static final class Builder {
        final List<Factory> factories = new ArrayList();

        public <T> Builder add(final Type type, final JsonAdapter<T> jsonAdapter) {
            if (type == null) {
                throw new IllegalArgumentException("type == null");
            } else if (jsonAdapter != null) {
                return add(new Factory() {
                    public JsonAdapter<?> create(Type targetType, Set<? extends Annotation> annotations, Moshi moshi) {
                        if (!annotations.isEmpty() || !Util.typesMatch(type, targetType)) {
                            return null;
                        }
                        return jsonAdapter;
                    }
                });
            } else {
                throw new IllegalArgumentException("jsonAdapter == null");
            }
        }

        public Builder add(Factory factory) {
            if (factory == null) {
                throw new IllegalArgumentException("factory == null");
            }
            this.factories.add(factory);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder addAll(List<Factory> factories2) {
            this.factories.addAll(factories2);
            return this;
        }

        public Moshi build() {
            return new Moshi(this);
        }
    }

    private static class DeferredAdapter<T> extends JsonAdapter<T> {
        Object cacheKey;
        private JsonAdapter<T> delegate;

        DeferredAdapter(Object cacheKey2) {
            this.cacheKey = cacheKey2;
        }

        /* access modifiers changed from: 0000 */
        public void ready(JsonAdapter<T> delegate2) {
            this.delegate = delegate2;
            this.cacheKey = null;
        }

        public void toJson(JsonWriter writer, T value) throws IOException {
            if (this.delegate == null) {
                throw new IllegalStateException("Type adapter isn't ready");
            }
            this.delegate.toJson(writer, value);
        }
    }

    static {
        BUILT_IN_FACTORIES.add(StandardJsonAdapters.FACTORY);
        BUILT_IN_FACTORIES.add(CollectionJsonAdapter.FACTORY);
        BUILT_IN_FACTORIES.add(MapJsonAdapter.FACTORY);
        BUILT_IN_FACTORIES.add(ArrayJsonAdapter.FACTORY);
        BUILT_IN_FACTORIES.add(ClassJsonAdapter.FACTORY);
    }

    Moshi(Builder builder) {
        List<Factory> factories2 = new ArrayList<>(builder.factories.size() + BUILT_IN_FACTORIES.size());
        factories2.addAll(builder.factories);
        factories2.addAll(BUILT_IN_FACTORIES);
        this.factories = Collections.unmodifiableList(factories2);
    }

    public <T> JsonAdapter<T> adapter(Type type) {
        return adapter(type, Util.NO_ANNOTATIONS);
    }

    public <T> JsonAdapter<T> adapter(Class<T> type) {
        return adapter(type, Util.NO_ANNOTATIONS);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0027, code lost:
        if (r4 >= r7) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0029, code lost:
        r1 = (com.squareup.moshi.Moshi.DeferredAdapter) r3.get(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
        if (r1.cacheKey.equals(r0) == false) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003c, code lost:
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003f, code lost:
        r3 = new java.util.ArrayList<>();
        r11.reentrantCalls.set(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0049, code lost:
        r2 = new com.squareup.moshi.Moshi.DeferredAdapter<>(r0);
        r3.add(r2);
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r7 = r11.factories.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0058, code lost:
        if (r4 >= r7) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005a, code lost:
        r6 = ((com.squareup.moshi.JsonAdapter.Factory) r11.factories.get(r4)).create(r12, r13, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0066, code lost:
        if (r6 == null) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0068, code lost:
        r2.ready(r6);
        r9 = r11.adapterCache;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006d, code lost:
        monitor-enter(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r11.adapterCache.put(r0, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0073, code lost:
        monitor-exit(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x008d, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x008e, code lost:
        r3.remove(r3.size() - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x009b, code lost:
        if (r3.isEmpty() != false) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x009d, code lost:
        r11.reentrantCalls.remove();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a2, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a3, code lost:
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a6, code lost:
        r3.remove(r3.size() - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b3, code lost:
        if (r3.isEmpty() == false) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00b5, code lost:
        r11.reentrantCalls.remove();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00de, code lost:
        throw new java.lang.IllegalArgumentException("No JsonAdapter for " + r12 + " annotated " + r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0018, code lost:
        r3 = (java.util.List) r11.reentrantCalls.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
        if (r3 == null) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
        r4 = 0;
        r7 = r3.size();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.squareup.moshi.JsonAdapter<T> adapter(java.lang.reflect.Type r12, java.util.Set<? extends java.lang.annotation.Annotation> r13) {
        /*
            r11 = this;
            java.lang.reflect.Type r12 = com.squareup.moshi.Types.canonicalize(r12)
            java.lang.Object r0 = r11.cacheKey(r12, r13)
            java.util.Map<java.lang.Object, com.squareup.moshi.JsonAdapter<?>> r9 = r11.adapterCache
            monitor-enter(r9)
            java.util.Map<java.lang.Object, com.squareup.moshi.JsonAdapter<?>> r8 = r11.adapterCache     // Catch:{ all -> 0x0039 }
            java.lang.Object r5 = r8.get(r0)     // Catch:{ all -> 0x0039 }
            com.squareup.moshi.JsonAdapter r5 = (com.squareup.moshi.JsonAdapter) r5     // Catch:{ all -> 0x0039 }
            if (r5 == 0) goto L_0x0017
            monitor-exit(r9)     // Catch:{ all -> 0x0039 }
        L_0x0016:
            return r5
        L_0x0017:
            monitor-exit(r9)     // Catch:{ all -> 0x0039 }
            java.lang.ThreadLocal<java.util.List<com.squareup.moshi.Moshi$DeferredAdapter<?>>> r8 = r11.reentrantCalls
            java.lang.Object r3 = r8.get()
            java.util.List r3 = (java.util.List) r3
            if (r3 == 0) goto L_0x003f
            r4 = 0
            int r7 = r3.size()
        L_0x0027:
            if (r4 >= r7) goto L_0x0049
            java.lang.Object r1 = r3.get(r4)
            com.squareup.moshi.Moshi$DeferredAdapter r1 = (com.squareup.moshi.Moshi.DeferredAdapter) r1
            java.lang.Object r8 = r1.cacheKey
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x003c
            r5 = r1
            goto L_0x0016
        L_0x0039:
            r8 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x0039 }
            throw r8
        L_0x003c:
            int r4 = r4 + 1
            goto L_0x0027
        L_0x003f:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.lang.ThreadLocal<java.util.List<com.squareup.moshi.Moshi$DeferredAdapter<?>>> r8 = r11.reentrantCalls
            r8.set(r3)
        L_0x0049:
            com.squareup.moshi.Moshi$DeferredAdapter r2 = new com.squareup.moshi.Moshi$DeferredAdapter
            r2.<init>(r0)
            r3.add(r2)
            r4 = 0
            java.util.List<com.squareup.moshi.JsonAdapter$Factory> r8 = r11.factories     // Catch:{ all -> 0x008d }
            int r7 = r8.size()     // Catch:{ all -> 0x008d }
        L_0x0058:
            if (r4 >= r7) goto L_0x00a6
            java.util.List<com.squareup.moshi.JsonAdapter$Factory> r8 = r11.factories     // Catch:{ all -> 0x008d }
            java.lang.Object r8 = r8.get(r4)     // Catch:{ all -> 0x008d }
            com.squareup.moshi.JsonAdapter$Factory r8 = (com.squareup.moshi.JsonAdapter.Factory) r8     // Catch:{ all -> 0x008d }
            com.squareup.moshi.JsonAdapter r6 = r8.create(r12, r13, r11)     // Catch:{ all -> 0x008d }
            if (r6 == 0) goto L_0x00a3
            r2.ready(r6)     // Catch:{ all -> 0x008d }
            java.util.Map<java.lang.Object, com.squareup.moshi.JsonAdapter<?>> r9 = r11.adapterCache     // Catch:{ all -> 0x008d }
            monitor-enter(r9)     // Catch:{ all -> 0x008d }
            java.util.Map<java.lang.Object, com.squareup.moshi.JsonAdapter<?>> r8 = r11.adapterCache     // Catch:{ all -> 0x008a }
            r8.put(r0, r6)     // Catch:{ all -> 0x008a }
            monitor-exit(r9)     // Catch:{ all -> 0x008a }
            int r8 = r3.size()
            int r8 = r8 + -1
            r3.remove(r8)
            boolean r8 = r3.isEmpty()
            if (r8 == 0) goto L_0x0088
            java.lang.ThreadLocal<java.util.List<com.squareup.moshi.Moshi$DeferredAdapter<?>>> r8 = r11.reentrantCalls
            r8.remove()
        L_0x0088:
            r5 = r6
            goto L_0x0016
        L_0x008a:
            r8 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x008a }
            throw r8     // Catch:{ all -> 0x008d }
        L_0x008d:
            r8 = move-exception
            int r9 = r3.size()
            int r9 = r9 + -1
            r3.remove(r9)
            boolean r9 = r3.isEmpty()
            if (r9 == 0) goto L_0x00a2
            java.lang.ThreadLocal<java.util.List<com.squareup.moshi.Moshi$DeferredAdapter<?>>> r9 = r11.reentrantCalls
            r9.remove()
        L_0x00a2:
            throw r8
        L_0x00a3:
            int r4 = r4 + 1
            goto L_0x0058
        L_0x00a6:
            int r8 = r3.size()
            int r8 = r8 + -1
            r3.remove(r8)
            boolean r8 = r3.isEmpty()
            if (r8 == 0) goto L_0x00ba
            java.lang.ThreadLocal<java.util.List<com.squareup.moshi.Moshi$DeferredAdapter<?>>> r8 = r11.reentrantCalls
            r8.remove()
        L_0x00ba:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "No JsonAdapter for "
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.StringBuilder r9 = r9.append(r12)
            java.lang.String r10 = " annotated "
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.StringBuilder r9 = r9.append(r13)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.Moshi.adapter(java.lang.reflect.Type, java.util.Set):com.squareup.moshi.JsonAdapter");
    }

    public Builder newBuilder() {
        return new Builder().addAll(this.factories.subList(0, this.factories.size() - BUILT_IN_FACTORIES.size()));
    }

    private Object cacheKey(Type type, Set<? extends Annotation> annotations) {
        if (annotations.isEmpty()) {
            return type;
        }
        return Arrays.asList(new Object[]{type, annotations});
    }
}
