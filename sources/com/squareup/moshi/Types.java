package com.squareup.moshi;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

public final class Types {
    static final Type[] EMPTY_TYPE_ARRAY = new Type[0];

    private static final class GenericArrayTypeImpl implements GenericArrayType {
        private final Type componentType;

        public GenericArrayTypeImpl(Type componentType2) {
            this.componentType = Types.canonicalize(componentType2);
        }

        public Type getGenericComponentType() {
            return this.componentType;
        }

        public boolean equals(Object o) {
            return (o instanceof GenericArrayType) && Types.equals(this, (GenericArrayType) o);
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            return Types.typeToString(this.componentType) + "[]";
        }
    }

    private static final class ParameterizedTypeImpl implements ParameterizedType {
        private final Type ownerType;
        private final Type rawType;
        final Type[] typeArguments;

        ParameterizedTypeImpl(Type ownerType2, Type rawType2, Type... typeArguments2) {
            boolean z;
            boolean z2 = true;
            if (rawType2 instanceof Class) {
                if (ownerType2 == null) {
                    z = true;
                } else {
                    z = false;
                }
                if (((Class) rawType2).getEnclosingClass() != null) {
                    z2 = false;
                }
                if (z != z2) {
                    throw new IllegalArgumentException("unexpected owner type for " + rawType2 + ": " + ownerType2);
                }
            }
            this.ownerType = ownerType2 == null ? null : Types.canonicalize(ownerType2);
            this.rawType = Types.canonicalize(rawType2);
            this.typeArguments = (Type[]) typeArguments2.clone();
            for (int t = 0; t < this.typeArguments.length; t++) {
                if (this.typeArguments[t] == null) {
                    throw new NullPointerException();
                }
                Types.checkNotPrimitive(this.typeArguments[t]);
                this.typeArguments[t] = Types.canonicalize(this.typeArguments[t]);
            }
        }

        public Type[] getActualTypeArguments() {
            return (Type[]) this.typeArguments.clone();
        }

        public Type getRawType() {
            return this.rawType;
        }

        public Type getOwnerType() {
            return this.ownerType;
        }

        public boolean equals(Object other) {
            return (other instanceof ParameterizedType) && Types.equals(this, (ParameterizedType) other);
        }

        public int hashCode() {
            return (Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode()) ^ Types.hashCodeOrZero(this.ownerType);
        }

        public String toString() {
            StringBuilder result = new StringBuilder((this.typeArguments.length + 1) * 30);
            result.append(Types.typeToString(this.rawType));
            if (this.typeArguments.length == 0) {
                return result.toString();
            }
            result.append("<").append(Types.typeToString(this.typeArguments[0]));
            for (int i = 1; i < this.typeArguments.length; i++) {
                result.append(", ").append(Types.typeToString(this.typeArguments[i]));
            }
            return result.append(">").toString();
        }
    }

    private static final class WildcardTypeImpl implements WildcardType {
        private final Type lowerBound;
        private final Type upperBound;

        public WildcardTypeImpl(Type[] upperBounds, Type[] lowerBounds) {
            if (lowerBounds.length > 1) {
                throw new IllegalArgumentException();
            } else if (upperBounds.length != 1) {
                throw new IllegalArgumentException();
            } else if (lowerBounds.length == 1) {
                if (lowerBounds[0] == null) {
                    throw new NullPointerException();
                }
                Types.checkNotPrimitive(lowerBounds[0]);
                if (upperBounds[0] != Object.class) {
                    throw new IllegalArgumentException();
                }
                this.lowerBound = Types.canonicalize(lowerBounds[0]);
                this.upperBound = Object.class;
            } else if (upperBounds[0] == null) {
                throw new NullPointerException();
            } else {
                Types.checkNotPrimitive(upperBounds[0]);
                this.lowerBound = null;
                this.upperBound = Types.canonicalize(upperBounds[0]);
            }
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        public Type[] getLowerBounds() {
            if (this.lowerBound == null) {
                return Types.EMPTY_TYPE_ARRAY;
            }
            return new Type[]{this.lowerBound};
        }

        public boolean equals(Object other) {
            return (other instanceof WildcardType) && Types.equals(this, (WildcardType) other);
        }

        public int hashCode() {
            return (this.lowerBound != null ? this.lowerBound.hashCode() + 31 : 1) ^ (this.upperBound.hashCode() + 31);
        }

        public String toString() {
            if (this.lowerBound != null) {
                return "? super " + Types.typeToString(this.lowerBound);
            }
            if (this.upperBound == Object.class) {
                return "?";
            }
            return "? extends " + Types.typeToString(this.upperBound);
        }
    }

    public static ParameterizedType newParameterizedType(Type rawType, Type... typeArguments) {
        return new ParameterizedTypeImpl(null, rawType, typeArguments);
    }

    public static GenericArrayType arrayOf(Type componentType) {
        return new GenericArrayTypeImpl(componentType);
    }

    public static WildcardType subtypeOf(Type bound) {
        return new WildcardTypeImpl(new Type[]{bound}, EMPTY_TYPE_ARRAY);
    }

    public static WildcardType supertypeOf(Type bound) {
        return new WildcardTypeImpl(new Type[]{Object.class}, new Type[]{bound});
    }

    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r7v4, types: [java.lang.reflect.Type] */
    /* JADX WARNING: type inference failed for: r4v11, types: [com.squareup.moshi.Types$GenericArrayTypeImpl] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.reflect.Type canonicalize(java.lang.reflect.Type r7) {
        /*
            boolean r4 = r7 instanceof java.lang.Class
            if (r4 == 0) goto L_0x001d
            r0 = r7
            java.lang.Class r0 = (java.lang.Class) r0
            boolean r4 = r0.isArray()
            if (r4 == 0) goto L_0x001b
            com.squareup.moshi.Types$GenericArrayTypeImpl r4 = new com.squareup.moshi.Types$GenericArrayTypeImpl
            java.lang.Class r5 = r0.getComponentType()
            java.lang.reflect.Type r5 = canonicalize(r5)
            r4.<init>(r5)
            r0 = r4
        L_0x001b:
            r7 = r0
        L_0x001c:
            return r7
        L_0x001d:
            boolean r4 = r7 instanceof java.lang.reflect.ParameterizedType
            if (r4 == 0) goto L_0x003a
            boolean r4 = r7 instanceof com.squareup.moshi.Types.ParameterizedTypeImpl
            if (r4 != 0) goto L_0x001c
            r2 = r7
            java.lang.reflect.ParameterizedType r2 = (java.lang.reflect.ParameterizedType) r2
            com.squareup.moshi.Types$ParameterizedTypeImpl r7 = new com.squareup.moshi.Types$ParameterizedTypeImpl
            java.lang.reflect.Type r4 = r2.getOwnerType()
            java.lang.reflect.Type r5 = r2.getRawType()
            java.lang.reflect.Type[] r6 = r2.getActualTypeArguments()
            r7.<init>(r4, r5, r6)
            goto L_0x001c
        L_0x003a:
            boolean r4 = r7 instanceof java.lang.reflect.GenericArrayType
            if (r4 == 0) goto L_0x004f
            boolean r4 = r7 instanceof com.squareup.moshi.Types.GenericArrayTypeImpl
            if (r4 != 0) goto L_0x001c
            r1 = r7
            java.lang.reflect.GenericArrayType r1 = (java.lang.reflect.GenericArrayType) r1
            com.squareup.moshi.Types$GenericArrayTypeImpl r7 = new com.squareup.moshi.Types$GenericArrayTypeImpl
            java.lang.reflect.Type r4 = r1.getGenericComponentType()
            r7.<init>(r4)
            goto L_0x001c
        L_0x004f:
            boolean r4 = r7 instanceof java.lang.reflect.WildcardType
            if (r4 == 0) goto L_0x001c
            boolean r4 = r7 instanceof com.squareup.moshi.Types.WildcardTypeImpl
            if (r4 != 0) goto L_0x001c
            r3 = r7
            java.lang.reflect.WildcardType r3 = (java.lang.reflect.WildcardType) r3
            com.squareup.moshi.Types$WildcardTypeImpl r7 = new com.squareup.moshi.Types$WildcardTypeImpl
            java.lang.reflect.Type[] r4 = r3.getUpperBounds()
            java.lang.reflect.Type[] r5 = r3.getLowerBounds()
            r7.<init>(r4, r5)
            goto L_0x001c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.Types.canonicalize(java.lang.reflect.Type):java.lang.reflect.Type");
    }

    public static Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(getRawType(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return getRawType(((WildcardType) type).getUpperBounds()[0]);
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
    }

    static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    static boolean equals(Type a, Type b) {
        Type[] aTypeArguments;
        Type[] bTypeArguments;
        boolean z;
        boolean z2 = true;
        if (a == b) {
            return true;
        }
        if (a instanceof Class) {
            return a.equals(b);
        }
        if (a instanceof ParameterizedType) {
            if (!(b instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType pa = (ParameterizedType) a;
            ParameterizedType pb = (ParameterizedType) b;
            if (pa instanceof ParameterizedTypeImpl) {
                aTypeArguments = ((ParameterizedTypeImpl) pa).typeArguments;
            } else {
                aTypeArguments = pa.getActualTypeArguments();
            }
            if (pb instanceof ParameterizedTypeImpl) {
                bTypeArguments = ((ParameterizedTypeImpl) pb).typeArguments;
            } else {
                bTypeArguments = pb.getActualTypeArguments();
            }
            if (!equal(pa.getOwnerType(), pb.getOwnerType()) || !pa.getRawType().equals(pb.getRawType()) || !Arrays.equals(aTypeArguments, bTypeArguments)) {
                z = false;
            } else {
                z = true;
            }
            return z;
        } else if (a instanceof GenericArrayType) {
            if (!(b instanceof GenericArrayType)) {
                return false;
            }
            return equals(((GenericArrayType) a).getGenericComponentType(), ((GenericArrayType) b).getGenericComponentType());
        } else if (a instanceof WildcardType) {
            if (!(b instanceof WildcardType)) {
                return false;
            }
            WildcardType wa = (WildcardType) a;
            WildcardType wb = (WildcardType) b;
            if (!Arrays.equals(wa.getUpperBounds(), wb.getUpperBounds()) || !Arrays.equals(wa.getLowerBounds(), wb.getLowerBounds())) {
                z2 = false;
            }
            return z2;
        } else if (!(a instanceof TypeVariable) || !(b instanceof TypeVariable)) {
            return false;
        } else {
            TypeVariable<?> va = (TypeVariable) a;
            TypeVariable<?> vb = (TypeVariable) b;
            if (va.getGenericDeclaration() != vb.getGenericDeclaration() || !va.getName().equals(vb.getName())) {
                z2 = false;
            }
            return z2;
        }
    }

    static int hashCodeOrZero(Object o) {
        if (o != null) {
            return o.hashCode();
        }
        return 0;
    }

    static String typeToString(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    static Type getGenericSupertype(Type context, Class<?> rawType, Class<?> toResolve) {
        if (toResolve == rawType) {
            return context;
        }
        if (toResolve.isInterface()) {
            Class<?>[] interfaces = rawType.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                if (interfaces[i] == toResolve) {
                    return rawType.getGenericInterfaces()[i];
                }
                if (toResolve.isAssignableFrom(interfaces[i])) {
                    return getGenericSupertype(rawType.getGenericInterfaces()[i], interfaces[i], toResolve);
                }
            }
        }
        if (!rawType.isInterface()) {
            while (rawType != Object.class) {
                Class<?> rawSupertype = rawType.getSuperclass();
                if (rawSupertype == toResolve) {
                    return rawType.getGenericSuperclass();
                }
                if (toResolve.isAssignableFrom(rawSupertype)) {
                    return getGenericSupertype(rawType.getGenericSuperclass(), rawSupertype, toResolve);
                }
                rawType = rawSupertype;
            }
        }
        return toResolve;
    }

    static Type getSupertype(Type context, Class<?> contextRawType, Class<?> supertype) {
        if (supertype.isAssignableFrom(contextRawType)) {
            return resolve(context, contextRawType, getGenericSupertype(context, contextRawType, supertype));
        }
        throw new IllegalArgumentException();
    }

    static Type getGenericSuperclass(Type type) {
        Class<?> rawType = getRawType(type);
        return resolve(type, rawType, rawType.getGenericSuperclass());
    }

    static Type arrayComponentType(Type type) {
        if (type instanceof GenericArrayType) {
            return ((GenericArrayType) type).getGenericComponentType();
        }
        if (type instanceof Class) {
            return ((Class) type).getComponentType();
        }
        return null;
    }

    public static Type collectionElementType(Type context, Class<?> contextRawType) {
        Type collectionType = getSupertype(context, contextRawType, Collection.class);
        if (collectionType instanceof WildcardType) {
            collectionType = ((WildcardType) collectionType).getUpperBounds()[0];
        }
        if (collectionType instanceof ParameterizedType) {
            return ((ParameterizedType) collectionType).getActualTypeArguments()[0];
        }
        return Object.class;
    }

    static Type[] mapKeyAndValueTypes(Type context, Class<?> contextRawType) {
        if (context == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type mapType = getSupertype(context, contextRawType, Map.class);
        if (mapType instanceof ParameterizedType) {
            return ((ParameterizedType) mapType).getActualTypeArguments();
        }
        return new Type[]{Object.class, Object.class};
    }

    /* JADX WARNING: type inference failed for: r11v2, types: [java.lang.reflect.GenericArrayType] */
    /* JADX WARNING: type inference failed for: r11v3 */
    /* JADX WARNING: type inference failed for: r10v11, types: [java.lang.reflect.Type] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.reflect.Type resolve(java.lang.reflect.Type r21, java.lang.Class<?> r22, java.lang.reflect.Type r23) {
        /*
        L_0x0000:
            r0 = r23
            boolean r0 = r0 instanceof java.lang.reflect.TypeVariable
            r19 = r0
            if (r19 == 0) goto L_0x001f
            r17 = r23
            java.lang.reflect.TypeVariable r17 = (java.lang.reflect.TypeVariable) r17
            r0 = r21
            r1 = r22
            r2 = r17
            java.lang.reflect.Type r23 = resolveTypeVariable(r0, r1, r2)
            r0 = r23
            r1 = r17
            if (r0 != r1) goto L_0x0000
            r10 = r23
        L_0x001e:
            return r10
        L_0x001f:
            r0 = r23
            boolean r0 = r0 instanceof java.lang.Class
            r19 = r0
            if (r19 == 0) goto L_0x004a
            r19 = r23
            java.lang.Class r19 = (java.lang.Class) r19
            boolean r19 = r19.isArray()
            if (r19 == 0) goto L_0x004a
            r11 = r23
            java.lang.Class r11 = (java.lang.Class) r11
            java.lang.Class r5 = r11.getComponentType()
            r0 = r21
            r1 = r22
            java.lang.reflect.Type r8 = resolve(r0, r1, r5)
            if (r5 != r8) goto L_0x0045
        L_0x0043:
            r10 = r11
            goto L_0x001e
        L_0x0045:
            java.lang.reflect.GenericArrayType r11 = arrayOf(r8)
            goto L_0x0043
        L_0x004a:
            r0 = r23
            boolean r0 = r0 instanceof java.lang.reflect.GenericArrayType
            r19 = r0
            if (r19 == 0) goto L_0x0069
            r10 = r23
            java.lang.reflect.GenericArrayType r10 = (java.lang.reflect.GenericArrayType) r10
            java.lang.reflect.Type r5 = r10.getGenericComponentType()
            r0 = r21
            r1 = r22
            java.lang.reflect.Type r8 = resolve(r0, r1, r5)
            if (r5 == r8) goto L_0x001e
            java.lang.reflect.GenericArrayType r10 = arrayOf(r8)
            goto L_0x001e
        L_0x0069:
            r0 = r23
            boolean r0 = r0 instanceof java.lang.reflect.ParameterizedType
            r19 = r0
            if (r19 == 0) goto L_0x00c4
            r10 = r23
            java.lang.reflect.ParameterizedType r10 = (java.lang.reflect.ParameterizedType) r10
            java.lang.reflect.Type r14 = r10.getOwnerType()
            r0 = r21
            r1 = r22
            java.lang.reflect.Type r9 = resolve(r0, r1, r14)
            if (r9 == r14) goto L_0x00af
            r4 = 1
        L_0x0084:
            java.lang.reflect.Type[] r3 = r10.getActualTypeArguments()
            r16 = 0
            int r6 = r3.length
        L_0x008b:
            r0 = r16
            if (r0 >= r6) goto L_0x00b1
            r19 = r3[r16]
            r0 = r21
            r1 = r22
            r2 = r19
            java.lang.reflect.Type r15 = resolve(r0, r1, r2)
            r19 = r3[r16]
            r0 = r19
            if (r15 == r0) goto L_0x00ac
            if (r4 != 0) goto L_0x00aa
            java.lang.Object r3 = r3.clone()
            java.lang.reflect.Type[] r3 = (java.lang.reflect.Type[]) r3
            r4 = 1
        L_0x00aa:
            r3[r16] = r15
        L_0x00ac:
            int r16 = r16 + 1
            goto L_0x008b
        L_0x00af:
            r4 = 0
            goto L_0x0084
        L_0x00b1:
            if (r4 == 0) goto L_0x001e
            com.squareup.moshi.Types$ParameterizedTypeImpl r19 = new com.squareup.moshi.Types$ParameterizedTypeImpl
            java.lang.reflect.Type r20 = r10.getRawType()
            r0 = r19
            r1 = r20
            r0.<init>(r9, r1, r3)
            r10 = r19
            goto L_0x001e
        L_0x00c4:
            r0 = r23
            boolean r0 = r0 instanceof java.lang.reflect.WildcardType
            r19 = r0
            if (r19 == 0) goto L_0x0128
            r10 = r23
            java.lang.reflect.WildcardType r10 = (java.lang.reflect.WildcardType) r10
            java.lang.reflect.Type[] r12 = r10.getLowerBounds()
            java.lang.reflect.Type[] r13 = r10.getUpperBounds()
            int r0 = r12.length
            r19 = r0
            r20 = 1
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x00ff
            r19 = 0
            r19 = r12[r19]
            r0 = r21
            r1 = r22
            r2 = r19
            java.lang.reflect.Type r7 = resolve(r0, r1, r2)
            r19 = 0
            r19 = r12[r19]
            r0 = r19
            if (r7 == r0) goto L_0x001e
            java.lang.reflect.WildcardType r10 = supertypeOf(r7)
            goto L_0x001e
        L_0x00ff:
            int r0 = r13.length
            r19 = r0
            r20 = 1
            r0 = r19
            r1 = r20
            if (r0 != r1) goto L_0x001e
            r19 = 0
            r19 = r13[r19]
            r0 = r21
            r1 = r22
            r2 = r19
            java.lang.reflect.Type r18 = resolve(r0, r1, r2)
            r19 = 0
            r19 = r13[r19]
            r0 = r18
            r1 = r19
            if (r0 == r1) goto L_0x001e
            java.lang.reflect.WildcardType r10 = subtypeOf(r18)
            goto L_0x001e
        L_0x0128:
            r10 = r23
            goto L_0x001e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.Types.resolve(java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type):java.lang.reflect.Type");
    }

    static Type resolveTypeVariable(Type context, Class<?> contextRawType, TypeVariable<?> unknown) {
        Class<?> declaredByRaw = declaringClassOf(unknown);
        if (declaredByRaw == null) {
            return unknown;
        }
        Type declaredBy = getGenericSupertype(context, contextRawType, declaredByRaw);
        if (!(declaredBy instanceof ParameterizedType)) {
            return unknown;
        }
        return ((ParameterizedType) declaredBy).getActualTypeArguments()[indexOf(declaredByRaw.getTypeParameters(), unknown)];
    }

    static boolean isAllowedPlatformType(Type type) {
        return type == Boolean.class || type == Byte.class || type == Character.class || type == Double.class || type == Float.class || type == Integer.class || type == Long.class || type == Short.class || type == String.class || type == Object.class;
    }

    private static int indexOf(Object[] array, Object toFind) {
        for (int i = 0; i < array.length; i++) {
            if (toFind.equals(array[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    private static Class<?> declaringClassOf(TypeVariable<?> typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            return (Class) genericDeclaration;
        }
        return null;
    }

    static void checkNotPrimitive(Type type) {
        if ((type instanceof Class) && ((Class) type).isPrimitive()) {
            throw new IllegalArgumentException();
        }
    }
}
