package com.jumio.persistence;

import android.content.Context;
import com.jumio.commons.PersistWith;
import com.jumio.commons.log.Log;
import com.jumio.commons.utils.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Persistor {
    private static final Set<Class> WRAPPER_TYPES = new HashSet(Arrays.asList(new Class[]{Boolean.class, Character.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Void.class}));
    private PersistenceTarget mDataLayer;

    public Persistor(Context c, Class clazz) throws PersistenceException {
        this.mDataLayer = new SqliteSink(c, checkAnnotated(clazz));
    }

    public Persistor(Context c, String key) throws PersistenceException {
        this.mDataLayer = new SqliteSink(c, key);
    }

    public Persistor(PersistenceTarget sink) {
        this.mDataLayer = sink;
    }

    public static String getPersistenceName(Class<?> clazz) throws PersistenceException {
        return checkAnnotated(clazz);
    }

    static String checkAnnotated(Class c) throws PersistenceException {
        Annotation annotation = c.getAnnotation(PersistWith.class);
        if (isWrapperType(c)) {
            return c.getName();
        }
        if (annotation != null && (annotation instanceof PersistWith)) {
            return ((PersistWith) annotation).value();
        }
        throw new PersistenceException("Class " + c.getName() + " must be annotated with PersistWith!");
    }

    public static boolean isWrapperType(Class clazz) {
        return WRAPPER_TYPES.contains(clazz);
    }

    public <T extends Serializable> long storeAsBlob(T object) throws PersistenceException, IOException {
        checkAnnotated(object.getClass());
        return this.mDataLayer.storeBlob(serialize(object));
    }

    public <T extends Serializable> T restoreFromBlob() throws PersistenceException, IOException {
        byte[] object = this.mDataLayer.readBlob();
        if (object == null) {
            return null;
        }
        return deserialize(object);
    }

    public boolean delete() throws PersistenceException {
        return this.mDataLayer.delete();
    }

    private <T extends Serializable> byte[] serialize(T object) throws IOException {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            byte[] bytes = bos.toByteArray();
            oos.flush();
            IOUtils.closeQuietly(bos);
            IOUtils.closeQuietly(oos);
            return bytes;
        } catch (Exception e) {
            Log.m1930w("Persistor", "error in serialize()", (Throwable) e);
            throw new IOException(e);
        }
    }

    private <T extends Serializable> T deserialize(byte[] object) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(object);
        ObjectInputStream ois = new ObjectInputStream(bis);
        try {
            T t = (Serializable) ois.readObject();
            IOUtils.closeQuietly(ois);
            IOUtils.closeQuietly(bis);
            return t;
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        } catch (Throwable th) {
            IOUtils.closeQuietly(ois);
            IOUtils.closeQuietly(bis);
            throw th;
        }
    }
}
