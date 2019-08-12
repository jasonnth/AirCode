package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter.Factory;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Set;

abstract class CollectionJsonAdapter<C extends Collection<T>, T> extends JsonAdapter<C> {
    public static final Factory FACTORY = new Factory() {
        public JsonAdapter<?> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {
            Class<?> rawType = Types.getRawType(type);
            if (!annotations.isEmpty()) {
                return null;
            }
            if (rawType == List.class || rawType == Collection.class) {
                return CollectionJsonAdapter.newArrayListAdapter(type, moshi).nullSafe();
            }
            if (rawType == Set.class) {
                return CollectionJsonAdapter.newLinkedHashSetAdapter(type, moshi).nullSafe();
            }
            return null;
        }
    };
    private final JsonAdapter<T> elementAdapter;

    private CollectionJsonAdapter(JsonAdapter<T> elementAdapter2) {
        this.elementAdapter = elementAdapter2;
    }

    static <T> JsonAdapter<Collection<T>> newArrayListAdapter(Type type, Moshi moshi) {
        return new CollectionJsonAdapter<Collection<T>, T>(moshi.adapter(Types.collectionElementType(type, Collection.class))) {
            public /* bridge */ /* synthetic */ void toJson(JsonWriter jsonWriter, Object obj) throws IOException {
                CollectionJsonAdapter.super.toJson(jsonWriter, (Collection) obj);
            }
        };
    }

    static <T> JsonAdapter<Set<T>> newLinkedHashSetAdapter(Type type, Moshi moshi) {
        return new CollectionJsonAdapter<Set<T>, T>(moshi.adapter(Types.collectionElementType(type, Collection.class))) {
            public /* bridge */ /* synthetic */ void toJson(JsonWriter jsonWriter, Object obj) throws IOException {
                CollectionJsonAdapter.super.toJson(jsonWriter, (Set) obj);
            }
        };
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=C, code=C<T>, for r5v0, types: [C<T>, C, java.util.Collection] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void toJson(com.squareup.moshi.JsonWriter r4, C<T> r5) throws java.io.IOException {
        /*
            r3 = this;
            r4.beginArray()
            java.util.Iterator r1 = r5.iterator()
        L_0x0007:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0017
            java.lang.Object r0 = r1.next()
            com.squareup.moshi.JsonAdapter<T> r2 = r3.elementAdapter
            r2.toJson(r4, r0)
            goto L_0x0007
        L_0x0017:
            r4.endArray()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.CollectionJsonAdapter.toJson(com.squareup.moshi.JsonWriter, java.util.Collection):void");
    }

    public String toString() {
        return this.elementAdapter + ".collection()";
    }
}
