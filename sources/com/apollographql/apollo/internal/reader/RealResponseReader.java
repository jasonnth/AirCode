package com.apollographql.apollo.internal.reader;

import com.apollographql.apollo.CustomTypeAdapter;
import com.apollographql.apollo.api.C3107Operation.Variables;
import com.apollographql.apollo.api.Field;
import com.apollographql.apollo.api.Field.ConditionalTypeField;
import com.apollographql.apollo.api.Field.CustomTypeField;
import com.apollographql.apollo.api.Field.ObjectField;
import com.apollographql.apollo.api.Field.ObjectListField;
import com.apollographql.apollo.api.Field.ScalarListField;
import com.apollographql.apollo.api.Field.Type;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ScalarType;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.internal.field.FieldValueResolver;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class RealResponseReader<R> implements ResponseReader {
    private final Map<ScalarType, CustomTypeAdapter> customTypeAdapters;
    private final FieldValueResolver<R> fieldValueResolver;
    private final Variables operationVariables;
    private final ResponseReaderShadow<R> readerShadow;
    private final R recordSet;

    private static class ListItemReader implements com.apollographql.apollo.api.Field.ListItemReader {
        private final Map<ScalarType, CustomTypeAdapter> customTypeAdapters;
        private final Object value;

        ListItemReader(Object value2, Map<ScalarType, CustomTypeAdapter> customTypeAdapters2) {
            this.value = value2;
            this.customTypeAdapters = customTypeAdapters2;
        }

        public String readString() throws IOException {
            return (String) this.value;
        }
    }

    public RealResponseReader(Variables operationVariables2, R recordSet2, FieldValueResolver<R> fieldValueResolver2, Map<ScalarType, CustomTypeAdapter> customTypeAdapters2, ResponseReaderShadow<R> readerShadow2) {
        this.operationVariables = operationVariables2;
        this.recordSet = recordSet2;
        this.fieldValueResolver = fieldValueResolver2;
        this.customTypeAdapters = customTypeAdapters2;
        this.readerShadow = readerShadow2;
    }

    public <T> T read(Field field) throws IOException {
        T readConditional;
        willResolve(field);
        switch (field.type()) {
            case STRING:
                readConditional = readString(field);
                break;
            case INT:
                readConditional = readInt(field);
                break;
            case LONG:
                readConditional = readLong(field);
                break;
            case DOUBLE:
                readConditional = readDouble(field);
                break;
            case BOOLEAN:
                readConditional = readBoolean(field);
                break;
            case OBJECT:
                readConditional = readObject((ObjectField) field);
                break;
            case SCALAR_LIST:
                readConditional = readScalarList((ScalarListField) field);
                break;
            case OBJECT_LIST:
                readConditional = readObjectList((ObjectListField) field);
                break;
            case CUSTOM:
                readConditional = readCustomType((CustomTypeField) field);
                break;
            case CONDITIONAL:
                readConditional = readConditional((ConditionalTypeField) field, this.operationVariables);
                break;
            default:
                throw new IllegalArgumentException("Unsupported field type");
        }
        didResolve(field);
        return readConditional;
    }

    private void willResolve(Field field) {
        if (field.type() != Type.CONDITIONAL) {
            this.readerShadow.willResolve(field, this.operationVariables);
        }
    }

    private void didResolve(Field field) {
        if (field.type() != Type.CONDITIONAL) {
            this.readerShadow.didResolve(field, this.operationVariables);
        }
    }

    /* access modifiers changed from: 0000 */
    public String readString(Field field) throws IOException {
        String value = (String) this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(value, field.optional());
        if (value == null) {
            this.readerShadow.didParseNull();
            return null;
        }
        this.readerShadow.didParseScalar(value);
        return value;
    }

    /* access modifiers changed from: 0000 */
    public Integer readInt(Field field) throws IOException {
        BigDecimal value = (BigDecimal) this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(value, field.optional());
        if (value == null) {
            this.readerShadow.didParseNull();
            return null;
        }
        this.readerShadow.didParseScalar(value);
        return Integer.valueOf(value.intValue());
    }

    /* access modifiers changed from: 0000 */
    public Long readLong(Field field) throws IOException {
        BigDecimal value = (BigDecimal) this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(value, field.optional());
        if (value == null) {
            this.readerShadow.didParseNull();
            return null;
        }
        this.readerShadow.didParseScalar(value);
        return Long.valueOf(value.longValue());
    }

    /* access modifiers changed from: 0000 */
    public Double readDouble(Field field) throws IOException {
        BigDecimal value = (BigDecimal) this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(value, field.optional());
        if (value == null) {
            this.readerShadow.didParseNull();
            return null;
        }
        this.readerShadow.didParseScalar(value);
        return Double.valueOf(value.doubleValue());
    }

    /* access modifiers changed from: 0000 */
    public Boolean readBoolean(Field field) throws IOException {
        Boolean value = (Boolean) this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(value, field.optional());
        if (value == null) {
            this.readerShadow.didParseNull();
            return null;
        }
        this.readerShadow.didParseScalar(value);
        return value;
    }

    /* access modifiers changed from: 0000 */
    public <T> T readObject(ObjectField field) throws IOException {
        R value = this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(value, field.optional());
        this.readerShadow.willParseObject(field, Optional.fromNullable(value));
        if (value == null) {
            this.readerShadow.didParseNull();
            return null;
        }
        T read = field.objectReader().read(new RealResponseReader(this.operationVariables, value, this.fieldValueResolver, this.customTypeAdapters, this.readerShadow));
        this.readerShadow.didParseObject(field, Optional.fromNullable(value));
        return read;
    }

    /* access modifiers changed from: 0000 */
    public <T> List<T> readScalarList(ScalarListField field) throws IOException {
        List values = (List) this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(values, field.optional());
        if (values == null) {
            this.readerShadow.didParseNull();
            return null;
        }
        List<T> result = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            this.readerShadow.willParseElement(i);
            Object value = values.get(i);
            T item = field.listReader().read(new ListItemReader(value, this.customTypeAdapters));
            this.readerShadow.didParseScalar(value);
            this.readerShadow.didParseElement(i);
            result.add(item);
        }
        this.readerShadow.didParseList(values);
        return Collections.unmodifiableList(result);
    }

    /* access modifiers changed from: 0000 */
    public <T> List<T> readObjectList(ObjectListField field) throws IOException {
        List<R> values = (List) this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(values, field.optional());
        if (values == null) {
            return null;
        }
        List<T> result = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            this.readerShadow.willParseElement(i);
            R value = values.get(i);
            this.readerShadow.willParseObject(field, Optional.fromNullable(value));
            T item = field.objectReader().read(new RealResponseReader(this.operationVariables, value, this.fieldValueResolver, this.customTypeAdapters, this.readerShadow));
            this.readerShadow.didParseObject(field, Optional.fromNullable(value));
            this.readerShadow.didParseElement(i);
            result.add(item);
        }
        this.readerShadow.didParseList(values);
        return Collections.unmodifiableList(result);
    }

    private <T> T readCustomType(CustomTypeField field) throws IOException {
        Object value = this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(value, field.optional());
        if (value == null) {
            this.readerShadow.didParseNull();
            return null;
        }
        CustomTypeAdapter<T> typeAdapter = (CustomTypeAdapter) this.customTypeAdapters.get(field.scalarType());
        if (typeAdapter == null) {
            this.readerShadow.didParseScalar(value);
            return value;
        }
        this.readerShadow.didParseScalar(value);
        return typeAdapter.decode(value.toString());
    }

    private <T> T readConditional(ConditionalTypeField field, Variables variables) throws IOException {
        this.readerShadow.willResolve(field, variables);
        String value = (String) this.fieldValueResolver.valueFor(this.recordSet, field);
        checkValue(value, field.optional());
        if (value == null) {
            this.readerShadow.didParseNull();
            this.readerShadow.didResolve(field, variables);
            return null;
        }
        this.readerShadow.didParseScalar(value);
        this.readerShadow.didResolve(field, variables);
        return field.conditionalTypeReader().read(value, this);
    }

    private void checkValue(Object value, boolean optional) {
        if (!optional && value == null) {
            throw new NullPointerException("corrupted response reader, expected non null value");
        }
    }
}
