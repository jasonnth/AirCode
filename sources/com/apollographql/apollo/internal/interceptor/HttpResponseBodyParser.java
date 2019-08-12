package com.apollographql.apollo.internal.interceptor;

import com.apollographql.apollo.CustomTypeAdapter;
import com.apollographql.apollo.api.C3107Operation;
import com.apollographql.apollo.api.C3107Operation.Data;
import com.apollographql.apollo.api.Error;
import com.apollographql.apollo.api.Error.C3105Location;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.internal.cache.normalized.ResponseNormalizer;
import com.apollographql.apollo.internal.field.MapFieldValueResolver;
import com.apollographql.apollo.internal.json.ApolloJsonReader;
import com.apollographql.apollo.internal.json.BufferedSourceJsonReader;
import com.apollographql.apollo.internal.json.ResponseJsonStreamReader;
import com.apollographql.apollo.internal.json.ResponseJsonStreamReader.ListReader;
import com.apollographql.apollo.internal.json.ResponseJsonStreamReader.ObjectReader;
import com.apollographql.apollo.internal.reader.RealResponseReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import okhttp3.ResponseBody;

final class HttpResponseBodyParser<D extends Data, W> {
    /* access modifiers changed from: private */
    public final Map<ScalarType, CustomTypeAdapter> customTypeAdapters;
    /* access modifiers changed from: private */
    public final C3107Operation<D, W, ?> operation;
    /* access modifiers changed from: private */
    public final ResponseFieldMapper responseFieldMapper;

    HttpResponseBodyParser(C3107Operation<D, W, ?> operation2, ResponseFieldMapper responseFieldMapper2, Map<ScalarType, CustomTypeAdapter> customTypeAdapters2) {
        this.operation = operation2;
        this.responseFieldMapper = responseFieldMapper2;
        this.customTypeAdapters = customTypeAdapters2;
    }

    public Response<W> parse(ResponseBody responseBody, final ResponseNormalizer<Map<String, Object>> networkResponseNormalizer) throws IOException {
        networkResponseNormalizer.willResolveRootQuery(this.operation);
        BufferedSourceJsonReader jsonReader = null;
        try {
            BufferedSourceJsonReader jsonReader2 = new BufferedSourceJsonReader(responseBody.source());
            try {
                jsonReader2.beginObject();
                ResponseJsonStreamReader responseStreamReader = ApolloJsonReader.responseJsonStreamReader(jsonReader2);
                D data = null;
                List<Error> errors = null;
                while (responseStreamReader.hasNext()) {
                    String name = responseStreamReader.nextName();
                    if ("data".equals(name)) {
                        data = (Data) responseStreamReader.nextObject(true, new ObjectReader<Object>() {
                            public Object read(ResponseJsonStreamReader reader) throws IOException {
                                return HttpResponseBodyParser.this.responseFieldMapper.map(new RealResponseReader<>(HttpResponseBodyParser.this.operation.variables(), reader.toMap(), new MapFieldValueResolver(), HttpResponseBodyParser.this.customTypeAdapters, networkResponseNormalizer));
                            }
                        });
                    } else if ("errors".equals(name)) {
                        errors = readResponseErrors(responseStreamReader);
                    } else {
                        responseStreamReader.skipNext();
                    }
                }
                jsonReader2.endObject();
                Response<W> build = Response.builder(this.operation).data(this.operation.wrapData(data)).errors(errors).dependentKeys(networkResponseNormalizer.dependentKeys()).build();
                if (jsonReader2 != null) {
                    jsonReader2.close();
                }
                return build;
            } catch (Throwable th) {
                th = th;
                jsonReader = jsonReader2;
            }
        } catch (Throwable th2) {
            th = th2;
            if (jsonReader != null) {
                jsonReader.close();
            }
            throw th;
        }
    }

    private List<Error> readResponseErrors(ResponseJsonStreamReader reader) throws IOException {
        return reader.nextList(true, new ListReader<Error>() {
            public Error read(ResponseJsonStreamReader reader) throws IOException {
                return (Error) reader.nextObject(true, new ObjectReader<Error>() {
                    public Error read(ResponseJsonStreamReader reader) throws IOException {
                        return HttpResponseBodyParser.this.readError(reader);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public Error readError(ResponseJsonStreamReader reader) throws IOException {
        String message = null;
        List<C3105Location> locations = new ArrayList<>();
        Map<String, Object> customAttributes = new HashMap<>();
        for (Entry<String, Object> entry : reader.toMap().entrySet()) {
            if ("message".equals(entry.getKey())) {
                message = entry.getValue().toString();
            } else if ("locations".equals(entry.getKey())) {
                List<Map<String, Object>> locationItems = (List) entry.getValue();
                if (locationItems != null) {
                    for (Map<String, Object> item : locationItems) {
                        locations.add(readErrorLocation(item));
                    }
                }
            } else if (entry.getValue() != null) {
                customAttributes.put(entry.getKey(), entry.getValue());
            }
        }
        return new Error(message, locations, customAttributes);
    }

    private C3105Location readErrorLocation(Map<String, Object> data) throws IOException {
        long line = -1;
        long column = -1;
        if (data != null) {
            for (Entry<String, Object> entry : data.entrySet()) {
                if ("line".equals(entry.getKey())) {
                    line = ((BigDecimal) entry.getValue()).longValue();
                } else if ("column".equals(entry.getKey())) {
                    column = ((BigDecimal) entry.getValue()).longValue();
                }
            }
        }
        return new C3105Location(line, column);
    }
}
