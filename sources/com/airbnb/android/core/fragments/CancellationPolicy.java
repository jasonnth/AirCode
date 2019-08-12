package com.airbnb.android.core.fragments;

import com.apollographql.apollo.api.Field;
import com.apollographql.apollo.api.Field.ListItemReader;
import com.apollographql.apollo.api.Field.ListReader;
import com.apollographql.apollo.api.Field.ObjectReader;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.internal.UnmodifiableMapBuilder;
import com.apollographql.apollo.api.internal.Utils;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class CancellationPolicy implements Query<Data, Optional<Data>, Variables> {
    public static final String OPERATION_DEFINITION = "query CancellationPolicy($id: PrimaryKey!) {\n  CancellationPolicy(id: $id) {\n    __typename\n    id\n    body\n    title\n  }\n}";
    private static final OperationName OPERATION_NAME = new OperationName() {
        public String name() {
            return "CancellationPolicy";
        }
    };
    public static final String QUERY_DOCUMENT = "query CancellationPolicy($id: PrimaryKey!) {\n  CancellationPolicy(id: $id) {\n    __typename\n    id\n    body\n    title\n  }\n}";
    private final Variables variables;

    public static final class Builder {

        /* renamed from: id */
        private Object f8443id;

        Builder() {
        }

        /* renamed from: id */
        public Builder mo58333id(Object id) {
            this.f8443id = id;
            return this;
        }

        public CancellationPolicy build() {
            if (this.f8443id != null) {
                return new CancellationPolicy(this.f8443id);
            }
            throw new IllegalStateException("id can't be null");
        }
    }

    public static class CancellationPolicy1 {
        private volatile int $hashCode;
        private volatile boolean $hashCodeMemoized;
        private volatile String $toString;
        private final String __typename;
        private final List<String> body;

        /* renamed from: id */
        private final String f8444id;
        private final Optional<String> title;

        public static final class Mapper implements ResponseFieldMapper<CancellationPolicy1> {
            final Field[] fields = {Field.forString("__typename", "__typename", null, false), Field.forString("id", "id", null, false), Field.forList("body", "body", null, false, new ListReader<String>() {
                public String read(ListItemReader reader) throws IOException {
                    return reader.readString();
                }
            }), Field.forString("title", "title", null, true)};

            public CancellationPolicy1 map(ResponseReader reader) throws IOException {
                return new CancellationPolicy1((String) reader.read(this.fields[0]), (String) reader.read(this.fields[1]), (List) reader.read(this.fields[2]), (String) reader.read(this.fields[3]));
            }
        }

        public CancellationPolicy1(String __typename2, String id, List<String> body2, String title2) {
            this.__typename = __typename2;
            this.f8444id = id;
            this.body = body2;
            this.title = Optional.fromNullable(title2);
        }

        public String __typename() {
            return this.__typename;
        }

        /* renamed from: id */
        public String mo58338id() {
            return this.f8444id;
        }

        public List<String> body() {
            return this.body;
        }

        public Optional<String> title() {
            return this.title;
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "CancellationPolicy1{__typename=" + this.__typename + ", id=" + this.f8444id + ", body=" + this.body + ", title=" + this.title + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CancellationPolicy1)) {
                return false;
            }
            CancellationPolicy1 that = (CancellationPolicy1) o;
            if (!this.__typename.equals(that.__typename) || !this.f8444id.equals(that.f8444id) || !this.body.equals(that.body) || !this.title.equals(that.title)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = (((((((1 * 1000003) ^ this.__typename.hashCode()) * 1000003) ^ this.f8444id.hashCode()) * 1000003) ^ this.body.hashCode()) * 1000003) ^ this.title.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }
    }

    public static class Data implements com.apollographql.apollo.api.C3107Operation.Data {
        private volatile int $hashCode;
        private volatile boolean $hashCodeMemoized;
        private volatile String $toString;
        private final Optional<CancellationPolicy1> CancellationPolicy;

        public static final class Mapper implements ResponseFieldMapper<Data> {
            final Mapper cancellationPolicy1FieldMapper = new Mapper();
            final Field[] fields = {Field.forObject("CancellationPolicy", "CancellationPolicy", new UnmodifiableMapBuilder(1).put("id", new UnmodifiableMapBuilder(2).put("kind", "Variable").put("variableName", "id").build()).build(), true, new ObjectReader<CancellationPolicy1>() {
                public CancellationPolicy1 read(ResponseReader reader) throws IOException {
                    return Mapper.this.cancellationPolicy1FieldMapper.map(reader);
                }
            })};

            public Data map(ResponseReader reader) throws IOException {
                return new Data((CancellationPolicy1) reader.read(this.fields[0]));
            }
        }

        public Data(CancellationPolicy1 CancellationPolicy2) {
            this.CancellationPolicy = Optional.fromNullable(CancellationPolicy2);
        }

        public Optional<CancellationPolicy1> CancellationPolicy() {
            return this.CancellationPolicy;
        }

        public String toString() {
            if (this.$toString == null) {
                this.$toString = "Data{CancellationPolicy=" + this.CancellationPolicy + "}";
            }
            return this.$toString;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Data)) {
                return false;
            }
            return this.CancellationPolicy.equals(((Data) o).CancellationPolicy);
        }

        public int hashCode() {
            if (!this.$hashCodeMemoized) {
                this.$hashCode = (1 * 1000003) ^ this.CancellationPolicy.hashCode();
                this.$hashCodeMemoized = true;
            }
            return this.$hashCode;
        }
    }

    public static final class Variables extends com.apollographql.apollo.api.C3107Operation.Variables {

        /* renamed from: id */
        private final Object f8445id;
        private final transient Map<String, Object> valueMap = new LinkedHashMap();

        Variables(Object id) {
            this.f8445id = id;
            this.valueMap.put("id", id);
        }

        /* renamed from: id */
        public Object mo58345id() {
            return this.f8445id;
        }

        public Map<String, Object> valueMap() {
            return Collections.unmodifiableMap(this.valueMap);
        }
    }

    public CancellationPolicy(Object id) {
        Utils.checkNotNull(id, "id == null");
        this.variables = new Variables(id);
    }

    public String queryDocument() {
        return "query CancellationPolicy($id: PrimaryKey!) {\n  CancellationPolicy(id: $id) {\n    __typename\n    id\n    body\n    title\n  }\n}";
    }

    public Optional<Data> wrapData(Data data) {
        return Optional.fromNullable(data);
    }

    public Variables variables() {
        return this.variables;
    }

    public ResponseFieldMapper<Data> responseFieldMapper() {
        return new Mapper();
    }

    public static Builder builder() {
        return new Builder();
    }

    public OperationName name() {
        return OPERATION_NAME;
    }
}
