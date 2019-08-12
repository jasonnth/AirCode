package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector.MixInResolver;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Basic;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.ClassUtil.Ctor;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class AnnotatedClass extends Annotated implements TypeResolutionContext {
    private static final AnnotationMap[] NO_ANNOTATION_MAPS = new AnnotationMap[0];
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final TypeBindings _bindings;
    protected final Class<?> _class;
    protected final AnnotationMap _classAnnotations;
    protected List<AnnotatedConstructor> _constructors;
    protected List<AnnotatedMethod> _creatorMethods;
    protected boolean _creatorsResolved = false;
    protected AnnotatedConstructor _defaultConstructor;
    protected List<AnnotatedField> _fields;
    protected AnnotatedMethodMap _memberMethods;
    protected final MixInResolver _mixInResolver;
    protected transient Boolean _nonStaticInnerClass;
    protected final Class<?> _primaryMixIn;
    protected final List<JavaType> _superTypes;
    protected final JavaType _type;
    protected final TypeFactory _typeFactory;

    private AnnotatedClass(JavaType type, Class<?> rawType, TypeBindings bindings, List<JavaType> superTypes, AnnotationIntrospector aintr, MixInResolver mir, TypeFactory tf) {
        this._type = type;
        this._class = rawType;
        this._bindings = bindings;
        this._superTypes = superTypes;
        this._annotationIntrospector = aintr;
        this._typeFactory = tf;
        this._mixInResolver = mir;
        this._primaryMixIn = this._mixInResolver == null ? null : this._mixInResolver.findMixInClassFor(this._class);
        this._classAnnotations = _resolveClassAnnotations();
    }

    private AnnotatedClass(AnnotatedClass base, AnnotationMap clsAnn) {
        this._type = base._type;
        this._class = base._class;
        this._bindings = base._bindings;
        this._superTypes = base._superTypes;
        this._annotationIntrospector = base._annotationIntrospector;
        this._typeFactory = base._typeFactory;
        this._mixInResolver = base._mixInResolver;
        this._primaryMixIn = base._primaryMixIn;
        this._classAnnotations = clsAnn;
    }

    public AnnotatedClass withAnnotations(AnnotationMap ann) {
        return new AnnotatedClass(this, ann);
    }

    public static AnnotatedClass construct(JavaType type, MapperConfig<?> config) {
        AnnotationIntrospector intr;
        if (config.isAnnotationProcessingEnabled()) {
            intr = config.getAnnotationIntrospector();
        } else {
            intr = null;
        }
        return new AnnotatedClass(type, type.getRawClass(), type.getBindings(), ClassUtil.findSuperTypes(type, null, false), intr, config, config.getTypeFactory());
    }

    public static AnnotatedClass construct(JavaType type, MapperConfig<?> config, MixInResolver mir) {
        AnnotationIntrospector intr;
        if (config.isAnnotationProcessingEnabled()) {
            intr = config.getAnnotationIntrospector();
        } else {
            intr = null;
        }
        return new AnnotatedClass(type, type.getRawClass(), type.getBindings(), ClassUtil.findSuperTypes(type, null, false), intr, mir, config.getTypeFactory());
    }

    public static AnnotatedClass constructWithoutSuperTypes(Class<?> cls, MapperConfig<?> config) {
        AnnotationIntrospector intr;
        if (config == null) {
            return new AnnotatedClass(null, cls, TypeBindings.emptyBindings(), Collections.emptyList(), null, null, null);
        }
        if (config.isAnnotationProcessingEnabled()) {
            intr = config.getAnnotationIntrospector();
        } else {
            intr = null;
        }
        return new AnnotatedClass(null, cls, TypeBindings.emptyBindings(), Collections.emptyList(), intr, config, config.getTypeFactory());
    }

    public JavaType resolveType(Type type) {
        return this._typeFactory.constructType(type, this._bindings);
    }

    public Class<?> getAnnotated() {
        return this._class;
    }

    public String getName() {
        return this._class.getName();
    }

    public <A extends Annotation> A getAnnotation(Class<A> acls) {
        return this._classAnnotations.get(acls);
    }

    public boolean hasAnnotation(Class<?> acls) {
        return this._classAnnotations.has(acls);
    }

    public boolean hasOneOf(Class<? extends Annotation>[] annoClasses) {
        return this._classAnnotations.hasOneOf(annoClasses);
    }

    public Class<?> getRawType() {
        return this._class;
    }

    /* access modifiers changed from: protected */
    public AnnotationMap getAllAnnotations() {
        return this._classAnnotations;
    }

    public JavaType getType() {
        return this._type;
    }

    public Annotations getAnnotations() {
        return this._classAnnotations;
    }

    public boolean hasAnnotations() {
        return this._classAnnotations.size() > 0;
    }

    public AnnotatedConstructor getDefaultConstructor() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._defaultConstructor;
    }

    public List<AnnotatedConstructor> getConstructors() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._constructors;
    }

    public List<AnnotatedMethod> getStaticMethods() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._creatorMethods;
    }

    public Iterable<AnnotatedMethod> memberMethods() {
        if (this._memberMethods == null) {
            resolveMemberMethods();
        }
        return this._memberMethods;
    }

    public AnnotatedMethod findMethod(String name, Class<?>[] paramTypes) {
        if (this._memberMethods == null) {
            resolveMemberMethods();
        }
        return this._memberMethods.find(name, paramTypes);
    }

    public Iterable<AnnotatedField> fields() {
        if (this._fields == null) {
            resolveFields();
        }
        return this._fields;
    }

    public boolean isNonStaticInnerClass() {
        Boolean B = this._nonStaticInnerClass;
        if (B == null) {
            B = Boolean.valueOf(ClassUtil.isNonStaticInnerClass(this._class));
            this._nonStaticInnerClass = B;
        }
        return B.booleanValue();
    }

    private AnnotationMap _resolveClassAnnotations() {
        AnnotationMap ca = new AnnotationMap();
        if (this._annotationIntrospector != null) {
            if (this._primaryMixIn != null) {
                _addClassMixIns(ca, this._class, this._primaryMixIn);
            }
            _addAnnotationsIfNotPresent(ca, ClassUtil.findClassAnnotations(this._class));
            for (JavaType type : this._superTypes) {
                _addClassMixIns(ca, type);
                _addAnnotationsIfNotPresent(ca, ClassUtil.findClassAnnotations(type.getRawClass()));
            }
            _addClassMixIns(ca, Object.class);
        }
        return ca;
    }

    private void resolveCreators() {
        Method[] arr$;
        Ctor[] arr$2;
        List<AnnotatedConstructor> constructors = null;
        if (!this._type.isEnumType()) {
            Ctor[] declaredCtors = ClassUtil.getConstructors(this._class);
            for (Ctor ctor : declaredCtors) {
                if (_isIncludableConstructor(ctor.getConstructor())) {
                    if (ctor.getParamCount() == 0) {
                        this._defaultConstructor = _constructDefaultConstructor(ctor, this);
                    } else {
                        if (constructors == null) {
                            constructors = new ArrayList<>(Math.max(10, declaredCtors.length));
                        }
                        constructors.add(_constructNonDefaultConstructor(ctor, this));
                    }
                }
            }
        }
        if (constructors == null) {
            this._constructors = Collections.emptyList();
        } else {
            this._constructors = constructors;
        }
        if (this._primaryMixIn != null && (this._defaultConstructor != null || !this._constructors.isEmpty())) {
            _addConstructorMixIns(this._primaryMixIn);
        }
        if (this._annotationIntrospector != null) {
            if (this._defaultConstructor != null && this._annotationIntrospector.hasIgnoreMarker(this._defaultConstructor)) {
                this._defaultConstructor = null;
            }
            if (this._constructors != null) {
                int i = this._constructors.size();
                while (true) {
                    i--;
                    if (i < 0) {
                        break;
                    } else if (this._annotationIntrospector.hasIgnoreMarker((AnnotatedMember) this._constructors.get(i))) {
                        this._constructors.remove(i);
                    }
                }
            }
        }
        List<AnnotatedMethod> creatorMethods = null;
        for (Method m : _findClassMethods(this._class)) {
            if (Modifier.isStatic(m.getModifiers())) {
                if (creatorMethods == null) {
                    creatorMethods = new ArrayList<>(8);
                }
                creatorMethods.add(_constructCreatorMethod(m, this));
            }
        }
        if (creatorMethods == null) {
            this._creatorMethods = Collections.emptyList();
        } else {
            this._creatorMethods = creatorMethods;
            if (this._primaryMixIn != null) {
                _addFactoryMixIns(this._primaryMixIn);
            }
            if (this._annotationIntrospector != null) {
                int i2 = this._creatorMethods.size();
                while (true) {
                    i2--;
                    if (i2 < 0) {
                        break;
                    } else if (this._annotationIntrospector.hasIgnoreMarker((AnnotatedMember) this._creatorMethods.get(i2))) {
                        this._creatorMethods.remove(i2);
                    }
                }
            }
        }
        this._creatorsResolved = true;
    }

    private void resolveMemberMethods() {
        this._memberMethods = new AnnotatedMethodMap();
        AnnotatedMethodMap mixins = new AnnotatedMethodMap();
        _addMemberMethods(this._class, this, this._memberMethods, this._primaryMixIn, mixins);
        for (JavaType type : this._superTypes) {
            _addMemberMethods(type.getRawClass(), new Basic(this._typeFactory, type.getBindings()), this._memberMethods, this._mixInResolver == null ? null : this._mixInResolver.findMixInClassFor(type.getRawClass()), mixins);
        }
        if (this._mixInResolver != null) {
            Class<?> mixin = this._mixInResolver.findMixInClassFor(Object.class);
            if (mixin != null) {
                _addMethodMixIns(this._class, this._memberMethods, mixin, mixins);
            }
        }
        if (this._annotationIntrospector != null && !mixins.isEmpty()) {
            Iterator<AnnotatedMethod> it = mixins.iterator();
            while (it.hasNext()) {
                AnnotatedMethod mixIn = (AnnotatedMethod) it.next();
                try {
                    Method m = Object.class.getDeclaredMethod(mixIn.getName(), mixIn.getRawParameterTypes());
                    if (m != null) {
                        AnnotatedMethod am = _constructMethod(m, this);
                        _addMixOvers(mixIn.getAnnotated(), am, false);
                        this._memberMethods.add(am);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    private void resolveFields() {
        Map<String, AnnotatedField> foundFields = _findFields(this._type, this, null);
        if (foundFields == null || foundFields.size() == 0) {
            this._fields = Collections.emptyList();
            return;
        }
        this._fields = new ArrayList(foundFields.size());
        this._fields.addAll(foundFields.values());
    }

    /* access modifiers changed from: protected */
    public void _addClassMixIns(AnnotationMap annotations, JavaType target) {
        if (this._mixInResolver != null) {
            Class<?> toMask = target.getRawClass();
            _addClassMixIns(annotations, toMask, this._mixInResolver.findMixInClassFor(toMask));
        }
    }

    /* access modifiers changed from: protected */
    public void _addClassMixIns(AnnotationMap annotations, Class<?> target) {
        if (this._mixInResolver != null) {
            _addClassMixIns(annotations, target, this._mixInResolver.findMixInClassFor(target));
        }
    }

    /* access modifiers changed from: protected */
    public void _addClassMixIns(AnnotationMap annotations, Class<?> toMask, Class<?> mixin) {
        if (mixin != null) {
            _addAnnotationsIfNotPresent(annotations, ClassUtil.findClassAnnotations(mixin));
            for (Class<?> parent : ClassUtil.findSuperClasses(mixin, toMask, false)) {
                _addAnnotationsIfNotPresent(annotations, ClassUtil.findClassAnnotations(parent));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addConstructorMixIns(Class<?> mixin) {
        MemberKey[] ctorKeys = null;
        int ctorCount = this._constructors == null ? 0 : this._constructors.size();
        for (Ctor ctor0 : ClassUtil.getConstructors(mixin)) {
            Constructor<?> ctor = ctor0.getConstructor();
            if (ctor.getParameterTypes().length != 0) {
                if (ctorKeys == null) {
                    ctorKeys = new MemberKey[ctorCount];
                    for (int i = 0; i < ctorCount; i++) {
                        ctorKeys[i] = new MemberKey(((AnnotatedConstructor) this._constructors.get(i)).getAnnotated());
                    }
                }
                MemberKey key = new MemberKey(ctor);
                int i2 = 0;
                while (true) {
                    if (i2 < ctorCount) {
                        if (key.equals(ctorKeys[i2])) {
                            _addMixOvers(ctor, (AnnotatedConstructor) this._constructors.get(i2), true);
                            break;
                        }
                        i2++;
                    } else {
                        break;
                    }
                }
            } else if (this._defaultConstructor != null) {
                _addMixOvers(ctor, this._defaultConstructor, false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addFactoryMixIns(Class<?> mixin) {
        Method[] arr$;
        MemberKey[] methodKeys = null;
        int methodCount = this._creatorMethods.size();
        for (Method m : ClassUtil.getDeclaredMethods(mixin)) {
            if (Modifier.isStatic(m.getModifiers()) && m.getParameterTypes().length != 0) {
                if (methodKeys == null) {
                    methodKeys = new MemberKey[methodCount];
                    for (int i = 0; i < methodCount; i++) {
                        methodKeys[i] = new MemberKey(((AnnotatedMethod) this._creatorMethods.get(i)).getAnnotated());
                    }
                }
                MemberKey key = new MemberKey(m);
                int i2 = 0;
                while (true) {
                    if (i2 < methodCount) {
                        if (key.equals(methodKeys[i2])) {
                            _addMixOvers(m, (AnnotatedMethod) this._creatorMethods.get(i2), true);
                            break;
                        }
                        i2++;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMemberMethods(Class<?> cls, TypeResolutionContext typeContext, AnnotatedMethodMap methods, Class<?> mixInCls, AnnotatedMethodMap mixIns) {
        Method[] arr$;
        if (mixInCls != null) {
            _addMethodMixIns(cls, methods, mixInCls, mixIns);
        }
        if (cls != null) {
            for (Method m : _findClassMethods(cls)) {
                if (_isIncludableMemberMethod(m)) {
                    AnnotatedMethod old = methods.find(m);
                    if (old == null) {
                        AnnotatedMethod newM = _constructMethod(m, typeContext);
                        methods.add(newM);
                        AnnotatedMethod old2 = mixIns.remove(m);
                        if (old2 != null) {
                            _addMixOvers(old2.getAnnotated(), newM, false);
                        }
                    } else {
                        _addMixUnders(m, old);
                        if (old.getDeclaringClass().isInterface() && !m.getDeclaringClass().isInterface()) {
                            methods.add(old.withMethod(m));
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMethodMixIns(Class<?> targetClass, AnnotatedMethodMap methods, Class<?> mixInCls, AnnotatedMethodMap mixIns) {
        Method[] arr$;
        for (Class<?> mixin : ClassUtil.findRawSuperTypes(mixInCls, targetClass, true)) {
            for (Method m : ClassUtil.getDeclaredMethods(mixin)) {
                if (_isIncludableMemberMethod(m)) {
                    AnnotatedMethod am = methods.find(m);
                    if (am != null) {
                        _addMixUnders(m, am);
                    } else {
                        AnnotatedMethod am2 = mixIns.find(m);
                        if (am2 != null) {
                            _addMixUnders(m, am2);
                        } else {
                            mixIns.add(_constructMethod(m, this));
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public Map<String, AnnotatedField> _findFields(JavaType type, TypeResolutionContext typeContext, Map<String, AnnotatedField> fields) {
        Field[] arr$;
        JavaType parent = type.getSuperClass();
        if (parent != null) {
            Class<?> cls = type.getRawClass();
            fields = _findFields(parent, new Basic(this._typeFactory, parent.getBindings()), fields);
            for (Field f : ClassUtil.getDeclaredFields(cls)) {
                if (_isIncludableField(f)) {
                    if (fields == null) {
                        fields = new LinkedHashMap<>();
                    }
                    fields.put(f.getName(), _constructField(f, typeContext));
                }
            }
            if (this._mixInResolver != null) {
                Class<?> mixin = this._mixInResolver.findMixInClassFor(cls);
                if (mixin != null) {
                    _addFieldMixIns(mixin, cls, fields);
                }
            }
        }
        return fields;
    }

    /* access modifiers changed from: protected */
    public void _addFieldMixIns(Class<?> mixInCls, Class<?> targetClass, Map<String, AnnotatedField> fields) {
        Field[] arr$;
        for (Class<?> mixin : ClassUtil.findSuperClasses(mixInCls, targetClass, true)) {
            for (Field mixinField : ClassUtil.getDeclaredFields(mixin)) {
                if (_isIncludableField(mixinField)) {
                    AnnotatedField maskedField = (AnnotatedField) fields.get(mixinField.getName());
                    if (maskedField != null) {
                        _addOrOverrideAnnotations(maskedField, mixinField.getDeclaredAnnotations());
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public AnnotatedMethod _constructMethod(Method m, TypeResolutionContext typeContext) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedMethod(typeContext, m, _emptyAnnotationMap(), null);
        }
        return new AnnotatedMethod(typeContext, m, _collectRelevantAnnotations(m.getDeclaredAnnotations()), null);
    }

    /* access modifiers changed from: protected */
    public AnnotatedConstructor _constructDefaultConstructor(Ctor ctor, TypeResolutionContext typeContext) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedConstructor(typeContext, ctor.getConstructor(), _emptyAnnotationMap(), NO_ANNOTATION_MAPS);
        }
        return new AnnotatedConstructor(typeContext, ctor.getConstructor(), _collectRelevantAnnotations(ctor.getDeclaredAnnotations()), NO_ANNOTATION_MAPS);
    }

    /* access modifiers changed from: protected */
    public AnnotatedConstructor _constructNonDefaultConstructor(Ctor ctor, TypeResolutionContext typeContext) {
        AnnotationMap[] resolvedAnnotations;
        int paramCount = ctor.getParamCount();
        if (this._annotationIntrospector == null) {
            return new AnnotatedConstructor(typeContext, ctor.getConstructor(), _emptyAnnotationMap(), _emptyAnnotationMaps(paramCount));
        }
        if (paramCount == 0) {
            return new AnnotatedConstructor(typeContext, ctor.getConstructor(), _collectRelevantAnnotations(ctor.getDeclaredAnnotations()), NO_ANNOTATION_MAPS);
        }
        Annotation[][] paramAnns = ctor.getParameterAnnotations();
        if (paramCount != paramAnns.length) {
            resolvedAnnotations = null;
            Class<?> dc = ctor.getDeclaringClass();
            if (dc.isEnum() && paramCount == paramAnns.length + 2) {
                Annotation[][] old = paramAnns;
                paramAnns = new Annotation[(old.length + 2)][];
                System.arraycopy(old, 0, paramAnns, 2, old.length);
                resolvedAnnotations = _collectRelevantAnnotations(paramAnns);
            } else if (dc.isMemberClass() && paramCount == paramAnns.length + 1) {
                Annotation[][] old2 = paramAnns;
                paramAnns = new Annotation[(old2.length + 1)][];
                System.arraycopy(old2, 0, paramAnns, 1, old2.length);
                resolvedAnnotations = _collectRelevantAnnotations(paramAnns);
            }
            if (resolvedAnnotations == null) {
                throw new IllegalStateException("Internal error: constructor for " + ctor.getDeclaringClass().getName() + " has mismatch: " + paramCount + " parameters; " + paramAnns.length + " sets of annotations");
            }
        } else {
            resolvedAnnotations = _collectRelevantAnnotations(paramAnns);
        }
        return new AnnotatedConstructor(typeContext, ctor.getConstructor(), _collectRelevantAnnotations(ctor.getDeclaredAnnotations()), resolvedAnnotations);
    }

    /* access modifiers changed from: protected */
    public AnnotatedMethod _constructCreatorMethod(Method m, TypeResolutionContext typeContext) {
        int paramCount = m.getParameterTypes().length;
        if (this._annotationIntrospector == null) {
            return new AnnotatedMethod(typeContext, m, _emptyAnnotationMap(), _emptyAnnotationMaps(paramCount));
        }
        if (paramCount == 0) {
            return new AnnotatedMethod(typeContext, m, _collectRelevantAnnotations(m.getDeclaredAnnotations()), NO_ANNOTATION_MAPS);
        }
        return new AnnotatedMethod(typeContext, m, _collectRelevantAnnotations(m.getDeclaredAnnotations()), _collectRelevantAnnotations(m.getParameterAnnotations()));
    }

    /* access modifiers changed from: protected */
    public AnnotatedField _constructField(Field f, TypeResolutionContext typeContext) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedField(typeContext, f, _emptyAnnotationMap());
        }
        return new AnnotatedField(typeContext, f, _collectRelevantAnnotations(f.getDeclaredAnnotations()));
    }

    private AnnotationMap _emptyAnnotationMap() {
        return new AnnotationMap();
    }

    private AnnotationMap[] _emptyAnnotationMaps(int count) {
        if (count == 0) {
            return NO_ANNOTATION_MAPS;
        }
        AnnotationMap[] maps = new AnnotationMap[count];
        for (int i = 0; i < count; i++) {
            maps[i] = _emptyAnnotationMap();
        }
        return maps;
    }

    /* access modifiers changed from: protected */
    public boolean _isIncludableMemberMethod(Method m) {
        if (!Modifier.isStatic(m.getModifiers()) && !m.isSynthetic() && !m.isBridge() && m.getParameterTypes().length <= 2) {
            return true;
        }
        return false;
    }

    private boolean _isIncludableField(Field f) {
        if (!f.isSynthetic() && !Modifier.isStatic(f.getModifiers())) {
            return true;
        }
        return false;
    }

    private boolean _isIncludableConstructor(Constructor<?> c) {
        return !c.isSynthetic();
    }

    /* access modifiers changed from: protected */
    public AnnotationMap[] _collectRelevantAnnotations(Annotation[][] anns) {
        int len = anns.length;
        AnnotationMap[] result = new AnnotationMap[len];
        for (int i = 0; i < len; i++) {
            result[i] = _collectRelevantAnnotations(anns[i]);
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public AnnotationMap _collectRelevantAnnotations(Annotation[] anns) {
        return _addAnnotationsIfNotPresent(new AnnotationMap(), anns);
    }

    private AnnotationMap _addAnnotationsIfNotPresent(AnnotationMap result, Annotation[] anns) {
        Annotation[] arr$;
        if (anns != null) {
            List<Annotation> fromBundles = null;
            for (Annotation ann : anns) {
                if (result.addIfNotPresent(ann) && _isAnnotationBundle(ann)) {
                    fromBundles = _addFromBundle(ann, fromBundles);
                }
            }
            if (fromBundles != null) {
                _addAnnotationsIfNotPresent(result, (Annotation[]) fromBundles.toArray(new Annotation[fromBundles.size()]));
            }
        }
        return result;
    }

    private List<Annotation> _addFromBundle(Annotation bundle, List<Annotation> result) {
        Annotation[] arr$;
        for (Annotation a : ClassUtil.findClassAnnotations(bundle.annotationType())) {
            if (!(a instanceof Target) && !(a instanceof Retention)) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                result.add(a);
            }
        }
        return result;
    }

    private void _addAnnotationsIfNotPresent(AnnotatedMember target, Annotation[] anns) {
        Annotation[] arr$;
        if (anns != null) {
            List<Annotation> fromBundles = null;
            for (Annotation ann : anns) {
                if (target.addIfNotPresent(ann) && _isAnnotationBundle(ann)) {
                    fromBundles = _addFromBundle(ann, fromBundles);
                }
            }
            if (fromBundles != null) {
                _addAnnotationsIfNotPresent(target, (Annotation[]) fromBundles.toArray(new Annotation[fromBundles.size()]));
            }
        }
    }

    private void _addOrOverrideAnnotations(AnnotatedMember target, Annotation[] anns) {
        Annotation[] arr$;
        if (anns != null) {
            List<Annotation> fromBundles = null;
            for (Annotation ann : anns) {
                if (target.addOrOverride(ann) && _isAnnotationBundle(ann)) {
                    fromBundles = _addFromBundle(ann, fromBundles);
                }
            }
            if (fromBundles != null) {
                _addOrOverrideAnnotations(target, (Annotation[]) fromBundles.toArray(new Annotation[fromBundles.size()]));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMixOvers(Constructor<?> mixin, AnnotatedConstructor target, boolean addParamAnnotations) {
        _addOrOverrideAnnotations(target, mixin.getDeclaredAnnotations());
        if (addParamAnnotations) {
            Annotation[][] pa = mixin.getParameterAnnotations();
            int len = pa.length;
            for (int i = 0; i < len; i++) {
                for (Annotation a : pa[i]) {
                    target.addOrOverrideParam(i, a);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMixOvers(Method mixin, AnnotatedMethod target, boolean addParamAnnotations) {
        _addOrOverrideAnnotations(target, mixin.getDeclaredAnnotations());
        if (addParamAnnotations) {
            Annotation[][] pa = mixin.getParameterAnnotations();
            int len = pa.length;
            for (int i = 0; i < len; i++) {
                for (Annotation a : pa[i]) {
                    target.addOrOverrideParam(i, a);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMixUnders(Method src, AnnotatedMethod target) {
        _addAnnotationsIfNotPresent((AnnotatedMember) target, src.getDeclaredAnnotations());
    }

    private final boolean _isAnnotationBundle(Annotation ann) {
        return this._annotationIntrospector != null && this._annotationIntrospector.isAnnotationBundle(ann);
    }

    /* access modifiers changed from: protected */
    public Method[] _findClassMethods(Class<?> cls) {
        try {
            return ClassUtil.getDeclaredMethods(cls);
        } catch (NoClassDefFoundError ex) {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            if (loader == null) {
                throw ex;
            }
            try {
                return loader.loadClass(cls.getName()).getDeclaredMethods();
            } catch (ClassNotFoundException e) {
                throw ex;
            }
        }
    }

    public String toString() {
        return "[AnnotedClass " + this._class.getName() + "]";
    }

    public int hashCode() {
        return this._class.getName().hashCode();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        if (((AnnotatedClass) o)._class != this._class) {
            return false;
        }
        return true;
    }
}
