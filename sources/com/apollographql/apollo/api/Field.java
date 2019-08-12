package com.apollographql.apollo.api;

import com.apollographql.apollo.api.C3107Operation.Variables;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Field {
    private final Map<String, Object> arguments;
    private final String fieldName;
    private final boolean optional;
    private final String responseName;
    private final Type type;

    public static final class ConditionalTypeField extends Field {
        private final ConditionalTypeReader conditionalTypeReader;

        public ConditionalTypeReader conditionalTypeReader() {
            return this.conditionalTypeReader;
        }
    }

    public interface ConditionalTypeReader<T> {
        T read(String str, ResponseReader responseReader) throws IOException;
    }

    public static final class CustomTypeField extends Field {
        private final ScalarType scalarType;

        public ScalarType scalarType() {
            return this.scalarType;
        }
    }

    public interface ListItemReader {
        String readString() throws IOException;
    }

    public interface ListReader<T> {
        T read(ListItemReader listItemReader) throws IOException;
    }

    public static final class ObjectField extends Field {
        private final ObjectReader objectReader;

        ObjectField(String responseName, String fieldName, Map<String, Object> arguments, boolean optional, ObjectReader objectReader2) {
            super(Type.OBJECT, responseName, fieldName, arguments, optional);
            this.objectReader = objectReader2;
        }

        public ObjectReader objectReader() {
            return this.objectReader;
        }
    }

    public static final class ObjectListField extends Field {
        private final ObjectReader objectReader;

        public ObjectReader objectReader() {
            return this.objectReader;
        }
    }

    public interface ObjectReader<T> {
        T read(ResponseReader responseReader) throws IOException;
    }

    public static final class ScalarListField extends Field {
        private final ListReader listReader;

        ScalarListField(String responseName, String fieldName, Map<String, Object> arguments, boolean optional, ListReader listReader2) {
            super(Type.SCALAR_LIST, responseName, fieldName, arguments, optional);
            this.listReader = listReader2;
        }

        public ListReader listReader() {
            return this.listReader;
        }
    }

    public enum Type {
        STRING,
        INT,
        LONG,
        DOUBLE,
        BOOLEAN,
        OBJECT,
        SCALAR_LIST,
        OBJECT_LIST,
        CUSTOM,
        CONDITIONAL
    }

    public static Field forString(String responseName2, String fieldName2, Map<String, Object> arguments2, boolean optional2) {
        return new Field(Type.STRING, responseName2, fieldName2, arguments2, optional2);
    }

    public static <T> Field forObject(String responseName2, String fieldName2, Map<String, Object> arguments2, boolean optional2, ObjectReader<T> objectReader) {
        return new ObjectField(responseName2, fieldName2, arguments2, optional2, objectReader);
    }

    public static <T> Field forList(String responseName2, String fieldName2, Map<String, Object> arguments2, boolean optional2, ListReader<T> listReader) {
        return new ScalarListField(responseName2, fieldName2, arguments2, optional2, listReader);
    }

    private Field(Type type2, String responseName2, String fieldName2, Map<String, Object> arguments2, boolean optional2) {
        Map<String, Object> unmodifiableMap;
        this.type = type2;
        this.responseName = responseName2;
        this.fieldName = fieldName2;
        if (arguments2 == null) {
            unmodifiableMap = Collections.emptyMap();
        } else {
            unmodifiableMap = Collections.unmodifiableMap(arguments2);
        }
        this.arguments = unmodifiableMap;
        this.optional = optional2;
    }

    public Type type() {
        return this.type;
    }

    public String responseName() {
        return this.responseName;
    }

    public String fieldName() {
        return this.fieldName;
    }

    public boolean optional() {
        return this.optional;
    }

    public String cacheKey(Variables variables) {
        if (this.arguments.isEmpty()) {
            return fieldName();
        }
        return String.format("%s(%s)", new Object[]{fieldName(), orderIndependentKey(this.arguments, variables)});
    }

    private String orderIndependentKey(Map<String, Object> objectMap, Variables variables) {
        if (isArgumentValueVariableType(objectMap)) {
            return orderIndependentKeyForVariableArgument(objectMap, variables);
        }
        List<Entry<String, Object>> sortedArguments = new ArrayList<>(objectMap.entrySet());
        Collections.sort(sortedArguments, new Comparator<Entry<String, Object>>() {
            public int compare(Entry<String, Object> argumentOne, Entry<String, Object> argumentTwo) {
                return ((String) argumentOne.getKey()).compareTo((String) argumentTwo.getKey());
            }
        });
        StringBuilder independentKey = new StringBuilder();
        for (int i = 0; i < sortedArguments.size(); i++) {
            Entry<String, Object> argument = (Entry) sortedArguments.get(i);
            if (argument.getValue() instanceof Map) {
                Map<String, Object> objectArg = (Map) argument.getValue();
                boolean isArgumentVariable = isArgumentValueVariableType(objectArg);
                independentKey.append((String) argument.getKey()).append(":").append(isArgumentVariable ? "" : "[").append(orderIndependentKey(objectArg, variables)).append(isArgumentVariable ? "" : "]");
            } else {
                independentKey.append((String) argument.getKey()).append(":").append(argument.getValue().toString());
            }
            if (i < sortedArguments.size() - 1) {
                independentKey.append(",");
            }
        }
        return independentKey.toString();
    }

    private boolean isArgumentValueVariableType(Map<String, Object> objectMap) {
        return objectMap.containsKey("kind") && objectMap.get("kind").equals("Variable") && objectMap.containsKey("variableName");
    }

    private String orderIndependentKeyForVariableArgument(Map<String, Object> objectMap, Variables variables) {
        Object resolvedVariable = variables.valueMap().get(objectMap.get("variableName"));
        if (resolvedVariable == null) {
            return null;
        }
        if (resolvedVariable instanceof Map) {
            return orderIndependentKey((Map) resolvedVariable, variables);
        }
        return resolvedVariable.toString();
    }
}
