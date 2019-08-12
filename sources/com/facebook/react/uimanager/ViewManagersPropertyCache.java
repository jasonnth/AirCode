package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class ViewManagersPropertyCache {
    private static final Map<Class, Map<String, PropSetter>> CLASS_PROPS_CACHE = new HashMap();
    private static final Map<String, PropSetter> EMPTY_PROPS_MAP = new HashMap();

    private static class ArrayPropSetter extends PropSetter {
        public ArrayPropSetter(ReactProp prop, Method setter) {
            super(prop, "Array", setter);
        }

        /* access modifiers changed from: protected */
        public Object extractProperty(ReactStylesDiffMap props) {
            return props.getArray(this.mPropName);
        }
    }

    private static class BooleanPropSetter extends PropSetter {
        private final boolean mDefaultValue;

        public BooleanPropSetter(ReactProp prop, Method setter, boolean defaultValue) {
            super(prop, "boolean", setter);
            this.mDefaultValue = defaultValue;
        }

        /* access modifiers changed from: protected */
        public Object extractProperty(ReactStylesDiffMap props) {
            return props.getBoolean(this.mPropName, this.mDefaultValue) ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    private static class BoxedBooleanPropSetter extends PropSetter {
        public BoxedBooleanPropSetter(ReactProp prop, Method setter) {
            super(prop, "boolean", setter);
        }

        /* access modifiers changed from: protected */
        public Object extractProperty(ReactStylesDiffMap props) {
            if (!props.isNull(this.mPropName)) {
                return props.getBoolean(this.mPropName, false) ? Boolean.TRUE : Boolean.FALSE;
            }
            return null;
        }
    }

    private static class BoxedIntPropSetter extends PropSetter {
        public BoxedIntPropSetter(ReactProp prop, Method setter) {
            super(prop, "number", setter);
        }

        public BoxedIntPropSetter(ReactPropGroup prop, Method setter, int index) {
            super(prop, "number", setter, index);
        }

        /* access modifiers changed from: protected */
        public Object extractProperty(ReactStylesDiffMap props) {
            if (!props.isNull(this.mPropName)) {
                return Integer.valueOf(props.getInt(this.mPropName, 0));
            }
            return null;
        }
    }

    private static class DoublePropSetter extends PropSetter {
        private final double mDefaultValue;

        public DoublePropSetter(ReactProp prop, Method setter, double defaultValue) {
            super(prop, "number", setter);
            this.mDefaultValue = defaultValue;
        }

        public DoublePropSetter(ReactPropGroup prop, Method setter, int index, double defaultValue) {
            super(prop, "number", setter, index);
            this.mDefaultValue = defaultValue;
        }

        /* access modifiers changed from: protected */
        public Object extractProperty(ReactStylesDiffMap props) {
            return Double.valueOf(props.getDouble(this.mPropName, this.mDefaultValue));
        }
    }

    private static class FloatPropSetter extends PropSetter {
        private final float mDefaultValue;

        public FloatPropSetter(ReactProp prop, Method setter, float defaultValue) {
            super(prop, "number", setter);
            this.mDefaultValue = defaultValue;
        }

        public FloatPropSetter(ReactPropGroup prop, Method setter, int index, float defaultValue) {
            super(prop, "number", setter, index);
            this.mDefaultValue = defaultValue;
        }

        /* access modifiers changed from: protected */
        public Object extractProperty(ReactStylesDiffMap props) {
            return Float.valueOf(props.getFloat(this.mPropName, this.mDefaultValue));
        }
    }

    private static class IntPropSetter extends PropSetter {
        private final int mDefaultValue;

        public IntPropSetter(ReactProp prop, Method setter, int defaultValue) {
            super(prop, "number", setter);
            this.mDefaultValue = defaultValue;
        }

        public IntPropSetter(ReactPropGroup prop, Method setter, int index, int defaultValue) {
            super(prop, "number", setter, index);
            this.mDefaultValue = defaultValue;
        }

        /* access modifiers changed from: protected */
        public Object extractProperty(ReactStylesDiffMap props) {
            return Integer.valueOf(props.getInt(this.mPropName, this.mDefaultValue));
        }
    }

    private static class MapPropSetter extends PropSetter {
        public MapPropSetter(ReactProp prop, Method setter) {
            super(prop, "Map", setter);
        }

        /* access modifiers changed from: protected */
        public Object extractProperty(ReactStylesDiffMap props) {
            return props.getMap(this.mPropName);
        }
    }

    static abstract class PropSetter {
        private static final Object[] SHADOW_ARGS = new Object[1];
        private static final Object[] SHADOW_GROUP_ARGS = new Object[2];
        private static final Object[] VIEW_MGR_ARGS = new Object[2];
        private static final Object[] VIEW_MGR_GROUP_ARGS = new Object[3];
        protected final Integer mIndex;
        protected final String mPropName;
        protected final String mPropType;
        protected final Method mSetter;

        /* access modifiers changed from: protected */
        public abstract Object extractProperty(ReactStylesDiffMap reactStylesDiffMap);

        private PropSetter(ReactProp prop, String defaultType, Method setter) {
            this.mPropName = prop.name();
            if (!"__default_type__".equals(prop.customType())) {
                defaultType = prop.customType();
            }
            this.mPropType = defaultType;
            this.mSetter = setter;
            this.mIndex = null;
        }

        private PropSetter(ReactPropGroup prop, String defaultType, Method setter, int index) {
            this.mPropName = prop.names()[index];
            if (!"__default_type__".equals(prop.customType())) {
                defaultType = prop.customType();
            }
            this.mPropType = defaultType;
            this.mSetter = setter;
            this.mIndex = Integer.valueOf(index);
        }

        public String getPropName() {
            return this.mPropName;
        }

        public String getPropType() {
            return this.mPropType;
        }

        public void updateViewProp(ViewManager viewManager, View viewToUpdate, ReactStylesDiffMap props) {
            try {
                if (this.mIndex == null) {
                    VIEW_MGR_ARGS[0] = viewToUpdate;
                    VIEW_MGR_ARGS[1] = extractProperty(props);
                    this.mSetter.invoke(viewManager, VIEW_MGR_ARGS);
                    Arrays.fill(VIEW_MGR_ARGS, null);
                    return;
                }
                VIEW_MGR_GROUP_ARGS[0] = viewToUpdate;
                VIEW_MGR_GROUP_ARGS[1] = this.mIndex;
                VIEW_MGR_GROUP_ARGS[2] = extractProperty(props);
                this.mSetter.invoke(viewManager, VIEW_MGR_GROUP_ARGS);
                Arrays.fill(VIEW_MGR_GROUP_ARGS, null);
            } catch (Throwable t) {
                FLog.m1804e(ViewManager.class, "Error while updating prop " + this.mPropName, t);
                throw new JSApplicationIllegalArgumentException("Error while updating property '" + this.mPropName + "' of a view managed by: " + viewManager.getName(), t);
            }
        }

        public void updateShadowNodeProp(ReactShadowNode nodeToUpdate, ReactStylesDiffMap props) {
            try {
                if (this.mIndex == null) {
                    SHADOW_ARGS[0] = extractProperty(props);
                    this.mSetter.invoke(nodeToUpdate, SHADOW_ARGS);
                    Arrays.fill(SHADOW_ARGS, null);
                    return;
                }
                SHADOW_GROUP_ARGS[0] = this.mIndex;
                SHADOW_GROUP_ARGS[1] = extractProperty(props);
                this.mSetter.invoke(nodeToUpdate, SHADOW_GROUP_ARGS);
                Arrays.fill(SHADOW_GROUP_ARGS, null);
            } catch (Throwable t) {
                FLog.m1804e(ViewManager.class, "Error while updating prop " + this.mPropName, t);
                throw new JSApplicationIllegalArgumentException("Error while updating property '" + this.mPropName + "' in shadow node of type: " + nodeToUpdate.getViewClass(), t);
            }
        }
    }

    private static class StringPropSetter extends PropSetter {
        public StringPropSetter(ReactProp prop, Method setter) {
            super(prop, "String", setter);
        }

        /* access modifiers changed from: protected */
        public Object extractProperty(ReactStylesDiffMap props) {
            return props.getString(this.mPropName);
        }
    }

    ViewManagersPropertyCache() {
    }

    static Map<String, String> getNativePropsForView(Class<? extends ViewManager> viewManagerTopClass, Class<? extends ReactShadowNode> shadowNodeTopClass) {
        Map<String, String> nativeProps = new HashMap<>();
        for (PropSetter setter : getNativePropSettersForViewManagerClass(viewManagerTopClass).values()) {
            nativeProps.put(setter.getPropName(), setter.getPropType());
        }
        for (PropSetter setter2 : getNativePropSettersForShadowNodeClass(shadowNodeTopClass).values()) {
            nativeProps.put(setter2.getPropName(), setter2.getPropType());
        }
        return nativeProps;
    }

    static Map<String, PropSetter> getNativePropSettersForViewManagerClass(Class<? extends ViewManager> cls) {
        if (cls == ViewManager.class) {
            return EMPTY_PROPS_MAP;
        }
        Map<String, PropSetter> props = (Map) CLASS_PROPS_CACHE.get(cls);
        if (props != null) {
            return props;
        }
        Map<String, PropSetter> props2 = new HashMap<>(getNativePropSettersForViewManagerClass(cls.getSuperclass()));
        extractPropSettersFromViewManagerClassDefinition(cls, props2);
        CLASS_PROPS_CACHE.put(cls, props2);
        return props2;
    }

    static Map<String, PropSetter> getNativePropSettersForShadowNodeClass(Class<? extends ReactShadowNode> cls) {
        if (cls == ReactShadowNode.class) {
            return EMPTY_PROPS_MAP;
        }
        Map<String, PropSetter> props = (Map) CLASS_PROPS_CACHE.get(cls);
        if (props != null) {
            return props;
        }
        Map<String, PropSetter> props2 = new HashMap<>(getNativePropSettersForShadowNodeClass(cls.getSuperclass()));
        extractPropSettersFromShadowNodeClassDefinition(cls, props2);
        CLASS_PROPS_CACHE.put(cls, props2);
        return props2;
    }

    private static PropSetter createPropSetter(ReactProp annotation, Method method, Class<?> propTypeClass) {
        if (propTypeClass == Boolean.TYPE) {
            return new BooleanPropSetter(annotation, method, annotation.defaultBoolean());
        }
        if (propTypeClass == Integer.TYPE) {
            return new IntPropSetter(annotation, method, annotation.defaultInt());
        }
        if (propTypeClass == Float.TYPE) {
            return new FloatPropSetter(annotation, method, annotation.defaultFloat());
        }
        if (propTypeClass == Double.TYPE) {
            return new DoublePropSetter(annotation, method, annotation.defaultDouble());
        }
        if (propTypeClass == String.class) {
            return new StringPropSetter(annotation, method);
        }
        if (propTypeClass == Boolean.class) {
            return new BoxedBooleanPropSetter(annotation, method);
        }
        if (propTypeClass == Integer.class) {
            return new BoxedIntPropSetter(annotation, method);
        }
        if (propTypeClass == ReadableArray.class) {
            return new ArrayPropSetter(annotation, method);
        }
        if (propTypeClass == ReadableMap.class) {
            return new MapPropSetter(annotation, method);
        }
        throw new RuntimeException("Unrecognized type: " + propTypeClass + " for method: " + method.getDeclaringClass().getName() + "#" + method.getName());
    }

    private static void createPropSetters(ReactPropGroup annotation, Method method, Class<?> propTypeClass, Map<String, PropSetter> props) {
        String[] names = annotation.names();
        if (propTypeClass == Integer.TYPE) {
            for (int i = 0; i < names.length; i++) {
                props.put(names[i], new IntPropSetter(annotation, method, i, annotation.defaultInt()));
            }
        } else if (propTypeClass == Float.TYPE) {
            for (int i2 = 0; i2 < names.length; i2++) {
                props.put(names[i2], new FloatPropSetter(annotation, method, i2, annotation.defaultFloat()));
            }
        } else if (propTypeClass == Double.TYPE) {
            for (int i3 = 0; i3 < names.length; i3++) {
                props.put(names[i3], new DoublePropSetter(annotation, method, i3, annotation.defaultDouble()));
            }
        } else if (propTypeClass == Integer.class) {
            for (int i4 = 0; i4 < names.length; i4++) {
                props.put(names[i4], new BoxedIntPropSetter(annotation, method, i4));
            }
        } else {
            throw new RuntimeException("Unrecognized type: " + propTypeClass + " for method: " + method.getDeclaringClass().getName() + "#" + method.getName());
        }
    }

    private static void extractPropSettersFromViewManagerClassDefinition(Class<? extends ViewManager> cls, Map<String, PropSetter> props) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method method : declaredMethods) {
            ReactProp annotation = (ReactProp) method.getAnnotation(ReactProp.class);
            if (annotation != null) {
                Class<?>[] paramTypes = method.getParameterTypes();
                if (paramTypes.length != 2) {
                    throw new RuntimeException("Wrong number of args for prop setter: " + cls.getName() + "#" + method.getName());
                } else if (!View.class.isAssignableFrom(paramTypes[0])) {
                    throw new RuntimeException("First param should be a view subclass to be updated: " + cls.getName() + "#" + method.getName());
                } else {
                    props.put(annotation.name(), createPropSetter(annotation, method, paramTypes[1]));
                }
            }
            ReactPropGroup groupAnnotation = (ReactPropGroup) method.getAnnotation(ReactPropGroup.class);
            if (groupAnnotation != null) {
                Class<?>[] paramTypes2 = method.getParameterTypes();
                if (paramTypes2.length != 3) {
                    throw new RuntimeException("Wrong number of args for group prop setter: " + cls.getName() + "#" + method.getName());
                } else if (!View.class.isAssignableFrom(paramTypes2[0])) {
                    throw new RuntimeException("First param should be a view subclass to be updated: " + cls.getName() + "#" + method.getName());
                } else if (paramTypes2[1] != Integer.TYPE) {
                    throw new RuntimeException("Second argument should be property index: " + cls.getName() + "#" + method.getName());
                } else {
                    createPropSetters(groupAnnotation, method, paramTypes2[2], props);
                }
            }
        }
    }

    private static void extractPropSettersFromShadowNodeClassDefinition(Class<? extends ReactShadowNode> cls, Map<String, PropSetter> props) {
        Method[] declaredMethods;
        for (Method method : cls.getDeclaredMethods()) {
            ReactProp annotation = (ReactProp) method.getAnnotation(ReactProp.class);
            if (annotation != null) {
                Class<?>[] paramTypes = method.getParameterTypes();
                if (paramTypes.length != 1) {
                    throw new RuntimeException("Wrong number of args for prop setter: " + cls.getName() + "#" + method.getName());
                }
                props.put(annotation.name(), createPropSetter(annotation, method, paramTypes[0]));
            }
            ReactPropGroup groupAnnotation = (ReactPropGroup) method.getAnnotation(ReactPropGroup.class);
            if (groupAnnotation != null) {
                Class<?>[] paramTypes2 = method.getParameterTypes();
                if (paramTypes2.length != 2) {
                    throw new RuntimeException("Wrong number of args for group prop setter: " + cls.getName() + "#" + method.getName());
                } else if (paramTypes2[0] != Integer.TYPE) {
                    throw new RuntimeException("Second argument should be property index: " + cls.getName() + "#" + method.getName());
                } else {
                    createPropSetters(groupAnnotation, method, paramTypes2[1], props);
                }
            }
        }
    }
}
