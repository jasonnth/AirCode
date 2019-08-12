package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.ser.Serializers;
import java.io.Serializable;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class OptionalHandlerFactory implements Serializable {
    private static final Class<?> CLASS_DOM_DOCUMENT;
    private static final Class<?> CLASS_DOM_NODE = Node.class;
    private static final Java7Support _jdk7Helper;
    public static final OptionalHandlerFactory instance = new OptionalHandlerFactory();

    static {
        Class<Document> cls = Document.class;
        CLASS_DOM_DOCUMENT = cls;
        Java7Support x = null;
        try {
            x = Java7Support.instance();
        } catch (Throwable th) {
        }
        _jdk7Helper = x;
    }

    protected OptionalHandlerFactory() {
    }

    public JsonSerializer<?> findSerializer(SerializationConfig config, JavaType type, BeanDescription beanDesc) {
        Class<?> rawType = type.getRawClass();
        if (_jdk7Helper != null) {
            JsonSerializer<?> ser = _jdk7Helper.getSerializerForJavaNioFilePath(rawType);
            if (ser != null) {
                return ser;
            }
        }
        if (CLASS_DOM_NODE != null && CLASS_DOM_NODE.isAssignableFrom(rawType)) {
            return (JsonSerializer) instantiate("com.fasterxml.jackson.databind.ext.DOMSerializer");
        }
        if (!rawType.getName().startsWith("javax.xml.") && !hasSuperClassStartingWith(rawType, "javax.xml.")) {
            return null;
        }
        Object ob = instantiate("com.fasterxml.jackson.databind.ext.CoreXMLSerializers");
        if (ob == null) {
            return null;
        }
        return ((Serializers) ob).findSerializer(config, type, beanDesc);
    }

    public JsonDeserializer<?> findDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
        Class<?> rawType = type.getRawClass();
        if (_jdk7Helper != null) {
            JsonDeserializer<?> deser = _jdk7Helper.getDeserializerForJavaNioFilePath(rawType);
            if (deser != null) {
                return deser;
            }
        }
        if (CLASS_DOM_NODE != null && CLASS_DOM_NODE.isAssignableFrom(rawType)) {
            return (JsonDeserializer) instantiate("com.fasterxml.jackson.databind.ext.DOMDeserializer$NodeDeserializer");
        }
        if (CLASS_DOM_DOCUMENT != null && CLASS_DOM_DOCUMENT.isAssignableFrom(rawType)) {
            return (JsonDeserializer) instantiate("com.fasterxml.jackson.databind.ext.DOMDeserializer$DocumentDeserializer");
        }
        if (!rawType.getName().startsWith("javax.xml.") && !hasSuperClassStartingWith(rawType, "javax.xml.")) {
            return null;
        }
        Object ob = instantiate("com.fasterxml.jackson.databind.ext.CoreXMLDeserializers");
        if (ob != null) {
            return ((Deserializers) ob).findBeanDeserializer(type, config, beanDesc);
        }
        return null;
    }

    private Object instantiate(String className) {
        try {
            return Class.forName(className).newInstance();
        } catch (Exception | LinkageError e) {
            return null;
        }
    }

    private boolean hasSuperClassStartingWith(Class<?> rawType, String prefix) {
        Class<?> supertype = rawType.getSuperclass();
        while (supertype != null && supertype != Object.class) {
            if (supertype.getName().startsWith(prefix)) {
                return true;
            }
            supertype = supertype.getSuperclass();
        }
        return false;
    }
}
