package com.jumio.persistence;

import android.content.Context;
import com.jumio.commons.log.Log;
import java.io.IOException;
import java.io.Serializable;

public class DataAccess {
    private static final String TAG = "DataAccess";

    public static synchronized <T extends Serializable> T load(Context context, Class<T> clazz) {
        Throwable th;
        T model;
        synchronized (DataAccess.class) {
            long d = System.currentTimeMillis();
            try {
                model = new Persistor(context, (Class) clazz).restoreFromBlob();
                Log.m1924v(TAG, "loading " + clazz.getSimpleName() + " took " + (System.currentTimeMillis() - d) + " ms" + (model == null ? " (was null)" : ""));
            } catch (PersistenceException e) {
                th = e;
                Log.m1930w(TAG, String.format("Error loading %s", new Object[]{clazz.getName()}), th);
                model = null;
                return model;
            } catch (IOException e2) {
                th = e2;
                Log.m1930w(TAG, String.format("Error loading %s", new Object[]{clazz.getName()}), th);
                model = null;
                return model;
            }
        }
        return model;
    }

    public static synchronized <T extends Serializable> T load(Context context, String key) {
        Throwable th;
        T model;
        synchronized (DataAccess.class) {
            long d = System.currentTimeMillis();
            try {
                model = new Persistor(context, key).restoreFromBlob();
                Log.m1924v(TAG, "loading " + key + " took " + (System.currentTimeMillis() - d) + " ms" + (model == null ? " (was null)" : ""));
            } catch (PersistenceException e) {
                th = e;
                Log.m1930w(TAG, String.format("Error loading %s", new Object[]{key}), th);
                model = null;
                return model;
            } catch (IOException e2) {
                th = e2;
                Log.m1930w(TAG, String.format("Error loading %s", new Object[]{key}), th);
                model = null;
                return model;
            }
        }
        return model;
    }

    @Deprecated
    public static synchronized <T extends Serializable> void store(Context context, Class<T> clazz, T object) {
        synchronized (DataAccess.class) {
            update(context, clazz, object);
        }
    }

    @Deprecated
    public static synchronized <T extends Serializable> void store(Context context, String key, T object) {
        synchronized (DataAccess.class) {
            update(context, key, object);
        }
    }

    public static synchronized <T extends Serializable> void update(Context context, Class<T> clazz, T object) {
        Throwable th;
        synchronized (DataAccess.class) {
            if (object != null) {
                long d = System.currentTimeMillis();
                try {
                    Persistor persistor = new Persistor(context, (Class) clazz);
                    persistor.delete();
                    persistor.storeAsBlob(object);
                    Log.m1924v(TAG, "storing " + object.getClass().getSimpleName() + "took " + (System.currentTimeMillis() - d) + " ms");
                } catch (PersistenceException e) {
                    th = e;
                } catch (IOException e2) {
                    th = e2;
                }
            }
        }
        Log.m1930w(TAG, String.format("Error persisting %s", new Object[]{clazz.getName()}), th);
    }

    public static synchronized <T extends Serializable> void update(Context context, String key, T object) {
        Throwable th;
        synchronized (DataAccess.class) {
            if (object != null) {
                long d = System.currentTimeMillis();
                try {
                    Persistor persistor = new Persistor(context, key);
                    persistor.delete();
                    persistor.storeAsBlob(object);
                    Log.m1924v(TAG, "storing " + key + "took " + (System.currentTimeMillis() - d) + " ms");
                } catch (PersistenceException e) {
                    th = e;
                } catch (IOException e2) {
                    th = e2;
                }
            }
        }
        Log.m1930w(TAG, String.format("Error persisting %s", new Object[]{key}), th);
    }

    public static synchronized <T extends Serializable> void delete(Context context, Class<T> clazz) {
        synchronized (DataAccess.class) {
            long d = System.currentTimeMillis();
            try {
                new Persistor(context, (Class) clazz).delete();
                Log.m1924v(TAG, "deleting " + clazz.getSimpleName() + "took " + (System.currentTimeMillis() - d) + " ms");
            } catch (PersistenceException e) {
                Log.m1930w(TAG, String.format("Error deleting %s", new Object[]{clazz.getName()}), (Throwable) e);
            }
        }
        return;
    }

    public static synchronized <T extends Serializable> void delete(Context context, String key) {
        synchronized (DataAccess.class) {
            long d = System.currentTimeMillis();
            try {
                new Persistor(context, key).delete();
                Log.m1924v(TAG, "deleting " + key + "took " + (System.currentTimeMillis() - d) + " ms");
            } catch (PersistenceException e) {
                Log.m1930w(TAG, String.format("Error deleting %s", new Object[]{key}), (Throwable) e);
            }
        }
        return;
    }
}
