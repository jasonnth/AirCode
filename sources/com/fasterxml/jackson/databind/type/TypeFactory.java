package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LRUMap;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

public final class TypeFactory implements Serializable {
    private static final Class<?> CLS_BOOL = Boolean.TYPE;
    private static final Class<?> CLS_CLASS = Class.class;
    private static final Class<?> CLS_COMPARABLE = Comparable.class;
    private static final Class<?> CLS_ENUM = Enum.class;
    private static final Class<?> CLS_INT = Integer.TYPE;
    private static final Class<?> CLS_LONG = Long.TYPE;
    private static final Class<?> CLS_OBJECT = Object.class;
    private static final Class<?> CLS_STRING = String.class;
    protected static final SimpleType CORE_TYPE_BOOL = new SimpleType(CLS_BOOL);
    protected static final SimpleType CORE_TYPE_CLASS = new SimpleType(CLS_CLASS);
    protected static final SimpleType CORE_TYPE_COMPARABLE = new SimpleType(CLS_COMPARABLE);
    protected static final SimpleType CORE_TYPE_ENUM = new SimpleType(CLS_ENUM);
    protected static final SimpleType CORE_TYPE_INT = new SimpleType(CLS_INT);
    protected static final SimpleType CORE_TYPE_LONG = new SimpleType(CLS_LONG);
    protected static final SimpleType CORE_TYPE_OBJECT = new SimpleType(CLS_OBJECT);
    protected static final SimpleType CORE_TYPE_STRING = new SimpleType(CLS_STRING);
    protected static final TypeBindings EMPTY_BINDINGS = TypeBindings.emptyBindings();
    private static final JavaType[] NO_TYPES = new JavaType[0];
    protected static final TypeFactory instance = new TypeFactory();
    protected final ClassLoader _classLoader;
    protected final TypeModifier[] _modifiers;
    protected final TypeParser _parser;
    protected final LRUMap<Object, JavaType> _typeCache;

    private TypeFactory() {
        this(null);
    }

    protected TypeFactory(LRUMap<Object, JavaType> typeCache) {
        if (typeCache == null) {
            typeCache = new LRUMap<>(16, 200);
        }
        this._typeCache = typeCache;
        this._parser = new TypeParser(this);
        this._modifiers = null;
        this._classLoader = null;
    }

    public static TypeFactory defaultInstance() {
        return instance;
    }

    public ClassLoader getClassLoader() {
        return this._classLoader;
    }

    public static JavaType unknownType() {
        return defaultInstance()._unknownType();
    }

    public static Class<?> rawClass(Type t) {
        if (t instanceof Class) {
            return (Class) t;
        }
        return defaultInstance().constructType(t).getRawClass();
    }

    public Class<?> findClass(String className) throws ClassNotFoundException {
        if (className.indexOf(46) < 0) {
            Class<?> prim = _findPrimitive(className);
            if (prim != null) {
                return prim;
            }
        }
        Throwable prob = null;
        ClassLoader loader = getClassLoader();
        if (loader == null) {
            loader = Thread.currentThread().getContextClassLoader();
        }
        if (loader != null) {
            try {
                return classForName(className, true, loader);
            } catch (Exception e) {
                prob = ClassUtil.getRootCause(e);
            }
        }
        try {
            return classForName(className);
        } catch (Exception e2) {
            if (prob == null) {
                prob = ClassUtil.getRootCause(e2);
            }
            if (prob instanceof RuntimeException) {
                throw ((RuntimeException) prob);
            }
            throw new ClassNotFoundException(prob.getMessage(), prob);
        }
    }

    /* access modifiers changed from: protected */
    public Class<?> classForName(String name, boolean initialize, ClassLoader loader) throws ClassNotFoundException {
        return Class.forName(name, true, loader);
    }

    /* access modifiers changed from: protected */
    public Class<?> classForName(String name) throws ClassNotFoundException {
        return Class.forName(name);
    }

    /* access modifiers changed from: protected */
    public Class<?> _findPrimitive(String className) {
        if ("int".equals(className)) {
            return Integer.TYPE;
        }
        if ("long".equals(className)) {
            return Long.TYPE;
        }
        if ("float".equals(className)) {
            return Float.TYPE;
        }
        if ("double".equals(className)) {
            return Double.TYPE;
        }
        if ("boolean".equals(className)) {
            return Boolean.TYPE;
        }
        if ("byte".equals(className)) {
            return Byte.TYPE;
        }
        if ("char".equals(className)) {
            return Character.TYPE;
        }
        if ("short".equals(className)) {
            return Short.TYPE;
        }
        if ("void".equals(className)) {
            return Void.TYPE;
        }
        return null;
    }

    public JavaType constructSpecializedType(JavaType baseType, Class<?> subclass) {
        JavaType newType;
        Class<?> rawBase = baseType.getRawClass();
        if (rawBase == subclass) {
            return baseType;
        }
        if (rawBase == Object.class) {
            newType = _fromClass(null, subclass, TypeBindings.emptyBindings());
        } else if (!rawBase.isAssignableFrom(subclass)) {
            throw new IllegalArgumentException(String.format("Class %s not subtype of %s", new Object[]{subclass.getName(), baseType}));
        } else if (baseType.getBindings().isEmpty()) {
            newType = _fromClass(null, subclass, TypeBindings.emptyBindings());
        } else {
            if (baseType.isContainerType()) {
                if (baseType.isMapLikeType()) {
                    if (subclass == HashMap.class || subclass == LinkedHashMap.class || subclass == EnumMap.class || subclass == TreeMap.class) {
                        newType = _fromClass(null, subclass, TypeBindings.create(subclass, baseType.getKeyType(), baseType.getContentType()));
                    }
                } else if (baseType.isCollectionLikeType()) {
                    if (subclass == ArrayList.class || subclass == LinkedList.class || subclass == HashSet.class || subclass == TreeSet.class) {
                        newType = _fromClass(null, subclass, TypeBindings.create(subclass, baseType.getContentType()));
                    } else if (rawBase == EnumSet.class) {
                        return baseType;
                    }
                }
            }
            int typeParamCount = subclass.getTypeParameters().length;
            if (typeParamCount == 0) {
                newType = _fromClass(null, subclass, TypeBindings.emptyBindings());
            } else {
                TypeBindings tb = _bindingsForSubtype(baseType, typeParamCount, subclass);
                if (baseType.isInterface()) {
                    newType = baseType.refine(subclass, tb, null, new JavaType[]{baseType});
                } else {
                    newType = baseType.refine(subclass, tb, baseType, NO_TYPES);
                }
                if (newType == null) {
                    newType = _fromClass(null, subclass, tb);
                }
            }
        }
        return newType.withHandlersFrom(baseType);
    }

    private TypeBindings _bindingsForSubtype(JavaType baseType, int typeParamCount, Class<?> subclass) {
        int baseCount = baseType.containedTypeCount();
        if (baseCount != typeParamCount) {
            return TypeBindings.emptyBindings();
        }
        if (typeParamCount == 1) {
            return TypeBindings.create(subclass, baseType.containedType(0));
        }
        if (typeParamCount == 2) {
            return TypeBindings.create(subclass, baseType.containedType(0), baseType.containedType(1));
        }
        List<JavaType> types = new ArrayList<>(baseCount);
        for (int i = 0; i < baseCount; i++) {
            types.add(baseType.containedType(i));
        }
        return TypeBindings.create(subclass, types);
    }

    public JavaType constructGeneralizedType(JavaType baseType, Class<?> superClass) {
        Class<?> rawBase = baseType.getRawClass();
        if (rawBase == superClass) {
            return baseType;
        }
        JavaType superType = baseType.findSuperType(superClass);
        if (superType != null) {
            return superType;
        }
        if (!superClass.isAssignableFrom(rawBase)) {
            throw new IllegalArgumentException(String.format("Class %s not a super-type of %s", new Object[]{superClass.getName(), baseType}));
        }
        throw new IllegalArgumentException(String.format("Internal error: class %s not included as super-type for %s", new Object[]{superClass.getName(), baseType}));
    }

    public JavaType constructFromCanonical(String canonical) throws IllegalArgumentException {
        return this._parser.parse(canonical);
    }

    public JavaType[] findTypeParameters(JavaType type, Class<?> expType) {
        JavaType match = type.findSuperType(expType);
        if (match == null) {
            return NO_TYPES;
        }
        return match.getBindings().typeParameterArray();
    }

    public JavaType moreSpecificType(JavaType type1, JavaType type2) {
        if (type1 == null) {
            return type2;
        }
        if (type2 == null) {
            return type1;
        }
        Class<?> raw1 = type1.getRawClass();
        Class<?> raw2 = type2.getRawClass();
        if (raw1 == raw2 || !raw1.isAssignableFrom(raw2)) {
            return type1;
        }
        return type2;
    }

    public JavaType constructType(Type type) {
        return _fromAny(null, type, EMPTY_BINDINGS);
    }

    public JavaType constructType(Type type, TypeBindings bindings) {
        return _fromAny(null, type, bindings);
    }

    public JavaType constructType(TypeReference<?> typeRef) {
        return _fromAny(null, typeRef.getType(), EMPTY_BINDINGS);
    }

    public CollectionType constructCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
        return constructCollectionType(collectionClass, _fromClass(null, elementClass, EMPTY_BINDINGS));
    }

    public CollectionType constructCollectionType(Class<? extends Collection> collectionClass, JavaType elementType) {
        return (CollectionType) _fromClass(null, collectionClass, TypeBindings.create(collectionClass, elementType));
    }

    public MapType constructMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
        JavaType kt;
        JavaType vt;
        if (mapClass == Properties.class) {
            vt = CORE_TYPE_STRING;
            kt = vt;
        } else {
            kt = _fromClass(null, keyClass, EMPTY_BINDINGS);
            vt = _fromClass(null, valueClass, EMPTY_BINDINGS);
        }
        return constructMapType(mapClass, kt, vt);
    }

    public MapType constructMapType(Class<? extends Map> mapClass, JavaType keyType, JavaType valueType) {
        return (MapType) _fromClass(null, mapClass, TypeBindings.create(mapClass, keyType, valueType));
    }

    @Deprecated
    public JavaType uncheckedSimpleType(Class<?> cls) {
        return _constructSimple(cls, EMPTY_BINDINGS, null, null);
    }

    private JavaType _mapType(Class<?> rawClass, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        JavaType kt;
        JavaType vt;
        if (rawClass == Properties.class) {
            vt = CORE_TYPE_STRING;
            kt = vt;
        } else {
            List<JavaType> typeParams = bindings.getTypeParameters();
            switch (typeParams.size()) {
                case 0:
                    vt = _unknownType();
                    kt = vt;
                    break;
                case 2:
                    kt = (JavaType) typeParams.get(0);
                    vt = (JavaType) typeParams.get(1);
                    break;
                default:
                    throw new IllegalArgumentException("Strange Map type " + rawClass.getName() + ": can not determine type parameters");
            }
        }
        return MapType.construct(rawClass, bindings, superClass, superInterfaces, kt, vt);
    }

    private JavaType _collectionType(Class<?> rawClass, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        JavaType ct;
        List<JavaType> typeParams = bindings.getTypeParameters();
        if (typeParams.isEmpty()) {
            ct = _unknownType();
        } else if (typeParams.size() == 1) {
            ct = (JavaType) typeParams.get(0);
        } else {
            throw new IllegalArgumentException("Strange Collection type " + rawClass.getName() + ": can not determine type parameters");
        }
        return CollectionType.construct(rawClass, bindings, superClass, superInterfaces, ct);
    }

    private JavaType _referenceType(Class<?> rawClass, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        JavaType ct;
        List<JavaType> typeParams = bindings.getTypeParameters();
        if (typeParams.isEmpty()) {
            ct = _unknownType();
        } else if (typeParams.size() == 1) {
            ct = (JavaType) typeParams.get(0);
        } else {
            throw new IllegalArgumentException("Strange Reference type " + rawClass.getName() + ": can not determine type parameters");
        }
        return ReferenceType.construct(rawClass, bindings, superClass, superInterfaces, ct);
    }

    /* access modifiers changed from: protected */
    public JavaType _constructSimple(Class<?> raw, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        if (bindings.isEmpty()) {
            JavaType result = _findWellKnownSimple(raw);
            if (result != null) {
                return result;
            }
        }
        return _newSimpleType(raw, bindings, superClass, superInterfaces);
    }

    /* access modifiers changed from: protected */
    public JavaType _newSimpleType(Class<?> raw, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        return new SimpleType(raw, bindings, superClass, superInterfaces);
    }

    /* access modifiers changed from: protected */
    public JavaType _unknownType() {
        return CORE_TYPE_OBJECT;
    }

    /* access modifiers changed from: protected */
    public JavaType _findWellKnownSimple(Class<?> clz) {
        if (clz.isPrimitive()) {
            if (clz == CLS_BOOL) {
                return CORE_TYPE_BOOL;
            }
            if (clz == CLS_INT) {
                return CORE_TYPE_INT;
            }
            if (clz == CLS_LONG) {
                return CORE_TYPE_LONG;
            }
        } else if (clz == CLS_STRING) {
            return CORE_TYPE_STRING;
        } else {
            if (clz == CLS_OBJECT) {
                return CORE_TYPE_OBJECT;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JavaType _fromAny(ClassStack context, Type type, TypeBindings bindings) {
        JavaType resultType;
        TypeModifier[] arr$;
        if (type instanceof Class) {
            resultType = _fromClass(context, (Class) type, EMPTY_BINDINGS);
        } else if (type instanceof ParameterizedType) {
            resultType = _fromParamType(context, (ParameterizedType) type, bindings);
        } else if (type instanceof JavaType) {
            return (JavaType) type;
        } else {
            if (type instanceof GenericArrayType) {
                resultType = _fromArrayType(context, (GenericArrayType) type, bindings);
            } else if (type instanceof TypeVariable) {
                resultType = _fromVariable(context, (TypeVariable) type, bindings);
            } else if (type instanceof WildcardType) {
                resultType = _fromWildcard(context, (WildcardType) type, bindings);
            } else {
                throw new IllegalArgumentException("Unrecognized Type: " + (type == null ? "[null]" : type.toString()));
            }
        }
        if (this._modifiers != null) {
            TypeBindings b = resultType.getBindings();
            if (b == null) {
                b = EMPTY_BINDINGS;
            }
            for (TypeModifier mod : this._modifiers) {
                JavaType t = mod.modifyType(resultType, type, b, this);
                if (t == null) {
                    throw new IllegalStateException(String.format("TypeModifier %s (of type %s) return null for type %s", new Object[]{mod, mod.getClass().getName(), resultType}));
                }
                resultType = t;
            }
        }
        return resultType;
    }

    /* access modifiers changed from: protected */
    public JavaType _fromClass(ClassStack context, Class<?> rawType, TypeBindings bindings) {
        Object obj;
        ClassStack context2;
        JavaType superClass;
        JavaType[] superInterfaces;
        JavaType result = _findWellKnownSimple(rawType);
        if (result != null) {
            return result;
        }
        if (bindings == null || bindings.isEmpty()) {
            obj = rawType;
        } else {
            obj = bindings.asKey(rawType);
        }
        JavaType result2 = (JavaType) this._typeCache.get(obj);
        if (result2 != null) {
            return result2;
        }
        if (context == null) {
            context2 = new ClassStack(rawType);
        } else {
            ClassStack prev = context.find(rawType);
            if (prev != null) {
                ResolvedRecursiveType resolvedRecursiveType = new ResolvedRecursiveType(rawType, EMPTY_BINDINGS);
                prev.addSelfReference(resolvedRecursiveType);
                return resolvedRecursiveType;
            }
            context2 = context.child(rawType);
        }
        if (rawType.isArray()) {
            result2 = ArrayType.construct(_fromAny(context2, rawType.getComponentType(), bindings), bindings);
        } else {
            if (rawType.isInterface()) {
                superClass = null;
                superInterfaces = _resolveSuperInterfaces(context2, rawType, bindings);
            } else {
                superClass = _resolveSuperClass(context2, rawType, bindings);
                superInterfaces = _resolveSuperInterfaces(context2, rawType, bindings);
            }
            if (rawType == Properties.class) {
                result2 = MapType.construct(rawType, bindings, superClass, superInterfaces, CORE_TYPE_STRING, CORE_TYPE_STRING);
            } else if (superClass != null) {
                result2 = superClass.refine(rawType, bindings, superClass, superInterfaces);
            }
            if (result2 == null) {
                result2 = _fromWellKnownClass(context2, rawType, bindings, superClass, superInterfaces);
                if (result2 == null) {
                    result2 = _fromWellKnownInterface(context2, rawType, bindings, superClass, superInterfaces);
                    if (result2 == null) {
                        result2 = _newSimpleType(rawType, bindings, superClass, superInterfaces);
                    }
                }
            }
        }
        context2.resolveSelfReferences(result2);
        if (!result2.hasHandlers()) {
            this._typeCache.putIfAbsent(obj, result2);
        }
        return result2;
    }

    /* access modifiers changed from: protected */
    public JavaType _resolveSuperClass(ClassStack context, Class<?> rawType, TypeBindings parentBindings) {
        Type parent = ClassUtil.getGenericSuperclass(rawType);
        if (parent == null) {
            return null;
        }
        return _fromAny(context, parent, parentBindings);
    }

    /* access modifiers changed from: protected */
    public JavaType[] _resolveSuperInterfaces(ClassStack context, Class<?> rawType, TypeBindings parentBindings) {
        Type[] types = ClassUtil.getGenericInterfaces(rawType);
        if (types == null || types.length == 0) {
            return NO_TYPES;
        }
        int len = types.length;
        JavaType[] resolved = new JavaType[len];
        for (int i = 0; i < len; i++) {
            resolved[i] = _fromAny(context, types[i], parentBindings);
        }
        return resolved;
    }

    /* access modifiers changed from: protected */
    public JavaType _fromWellKnownClass(ClassStack context, Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        if (bindings == null) {
            bindings = TypeBindings.emptyBindings();
        }
        if (rawType == Map.class) {
            return _mapType(rawType, bindings, superClass, superInterfaces);
        }
        if (rawType == Collection.class) {
            return _collectionType(rawType, bindings, superClass, superInterfaces);
        }
        if (rawType == AtomicReference.class) {
            return _referenceType(rawType, bindings, superClass, superInterfaces);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JavaType _fromWellKnownInterface(ClassStack context, Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        for (JavaType refine : superInterfaces) {
            JavaType result = refine.refine(rawType, bindings, superClass, superInterfaces);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JavaType _fromParamType(ClassStack context, ParameterizedType ptype, TypeBindings parentBindings) {
        TypeBindings newBindings;
        Class<?> rawType = (Class) ptype.getRawType();
        if (rawType == CLS_ENUM) {
            return CORE_TYPE_ENUM;
        }
        if (rawType == CLS_COMPARABLE) {
            return CORE_TYPE_COMPARABLE;
        }
        if (rawType == CLS_CLASS) {
            return CORE_TYPE_CLASS;
        }
        Type[] args = ptype.getActualTypeArguments();
        int paramCount = args == null ? 0 : args.length;
        if (paramCount == 0) {
            newBindings = EMPTY_BINDINGS;
        } else {
            JavaType[] pt = new JavaType[paramCount];
            for (int i = 0; i < paramCount; i++) {
                pt[i] = _fromAny(context, args[i], parentBindings);
            }
            newBindings = TypeBindings.create(rawType, pt);
        }
        return _fromClass(context, rawType, newBindings);
    }

    /* access modifiers changed from: protected */
    public JavaType _fromArrayType(ClassStack context, GenericArrayType type, TypeBindings bindings) {
        return ArrayType.construct(_fromAny(context, type.getGenericComponentType(), bindings), bindings);
    }

    /* access modifiers changed from: protected */
    public JavaType _fromVariable(ClassStack context, TypeVariable<?> var, TypeBindings bindings) {
        String name = var.getName();
        JavaType type = bindings.findBoundType(name);
        if (type != null) {
            return type;
        }
        if (bindings.hasUnbound(name)) {
            return CORE_TYPE_OBJECT;
        }
        return _fromAny(context, var.getBounds()[0], bindings.withUnboundVariable(name));
    }

    /* access modifiers changed from: protected */
    public JavaType _fromWildcard(ClassStack context, WildcardType type, TypeBindings bindings) {
        return _fromAny(context, type.getUpperBounds()[0], bindings);
    }
}
