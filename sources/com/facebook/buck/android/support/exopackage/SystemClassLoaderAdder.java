package com.facebook.buck.android.support.exopackage;

import android.annotation.TargetApi;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;

class SystemClassLoaderAdder {
    private SystemClassLoaderAdder() {
    }

    static void installDexJars(ClassLoader appClassLoader, File optimizedDirectory, List<File> dexJars) {
        SystemClassLoaderAdder classLoaderAdder = new SystemClassLoaderAdder();
        for (File dexJar : dexJars) {
            classLoaderAdder.addPathsOfClassLoaderToSystemClassLoader(new DexClassLoader(dexJar.getAbsolutePath(), optimizedDirectory.getAbsolutePath(), null, appClassLoader), (PathClassLoader) appClassLoader);
        }
    }

    private void addPathsOfClassLoaderToSystemClassLoader(DexClassLoader newClassLoader, PathClassLoader systemClassLoader) {
        try {
            if (existsBaseDexClassLoader()) {
                addNewClassLoaderToSystemClassLoaderWithBaseDex(newClassLoader, systemClassLoader);
            } else {
                addNewClassLoaderToSystemClassLoaderPreBaseDex(newClassLoader, systemClassLoader);
            }
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }

    private boolean existsBaseDexClassLoader() {
        try {
            Class.forName("dalvik.system.BaseDexClassLoader");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private void addNewClassLoaderToSystemClassLoaderWithBaseDex(DexClassLoader newClassLoader, PathClassLoader systemClassLoader) throws NoSuchFieldException, IllegalAccessException {
        setDexElementsArray(getDexPathList(systemClassLoader), mergeArrays(getDexElementsArray(getDexPathList(systemClassLoader)), getDexElementsArray(getDexPathList(newClassLoader))));
    }

    private void addNewClassLoaderToSystemClassLoaderPreBaseDex(DexClassLoader newClassLoader, PathClassLoader systemClassLoader) throws NoSuchFieldException, IllegalAccessException {
        try {
            newClassLoader.loadClass("foo");
        } catch (ClassNotFoundException e) {
        }
        setField(systemClassLoader, PathClassLoader.class, "mPaths", mergeArrayAndScalar(getField(systemClassLoader, PathClassLoader.class, "mPaths"), getField(newClassLoader, DexClassLoader.class, "mRawDexPath")));
        setField(systemClassLoader, PathClassLoader.class, "mFiles", mergeArrays(getField(systemClassLoader, PathClassLoader.class, "mFiles"), getField(newClassLoader, DexClassLoader.class, "mFiles")));
        setField(systemClassLoader, PathClassLoader.class, "mZips", mergeArrays(getField(systemClassLoader, PathClassLoader.class, "mZips"), getField(newClassLoader, DexClassLoader.class, "mZips")));
        setField(systemClassLoader, PathClassLoader.class, "mDexs", mergeArrays(getField(systemClassLoader, PathClassLoader.class, "mDexs"), getField(newClassLoader, DexClassLoader.class, "mDexs")));
    }

    @TargetApi(14)
    private Object getDexPathList(BaseDexClassLoader classLoader) throws NoSuchFieldException, IllegalAccessException {
        return getField(classLoader, BaseDexClassLoader.class, "pathList");
    }

    private Object getDexElementsArray(Object dexPathList) throws NoSuchFieldException, IllegalAccessException {
        return getField(dexPathList, dexPathList.getClass(), "dexElements");
    }

    private void setDexElementsArray(Object dexPathList, Object newElementArray) throws NoSuchFieldException, IllegalAccessException {
        setField(dexPathList, dexPathList.getClass(), "dexElements", newElementArray);
    }

    private Object mergeArrays(Object array1, Object array2) {
        Class<?> itemClass = array1.getClass().getComponentType();
        int array1Size = Array.getLength(array1);
        int newSize = array1Size + Array.getLength(array2);
        Object newArray = Array.newInstance(itemClass, newSize);
        for (int i = 0; i < newSize; i++) {
            if (i < array1Size) {
                Array.set(newArray, i, Array.get(array1, i));
            } else {
                Array.set(newArray, i, Array.get(array2, i - array1Size));
            }
        }
        return newArray;
    }

    private Object mergeArrayAndScalar(Object array, Object scalar) {
        Class<?> itemClass = array.getClass().getComponentType();
        int array1Size = Array.getLength(array);
        int newSize = array1Size + 1;
        Object newArray = Array.newInstance(itemClass, newSize);
        for (int i = 0; i < newSize; i++) {
            if (i < array1Size) {
                Array.set(newArray, i, Array.get(array, i));
            } else {
                Array.set(newArray, i, scalar);
            }
        }
        return newArray;
    }

    private Object getField(Object object, Class<?> clazz, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    private void setField(Object object, Class<?> clazz, String fieldName, Object fieldValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, fieldValue);
    }
}
