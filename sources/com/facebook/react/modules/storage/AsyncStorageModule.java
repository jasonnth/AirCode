package com.facebook.react.modules.storage;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.SetBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.common.ModuleDataCleaner.Cleanable;
import java.util.HashSet;
import java.util.Iterator;

@ReactModule(name = "AsyncSQLiteDBStorage")
public final class AsyncStorageModule extends ReactContextBaseJavaModule implements Cleanable {
    private static final int MAX_SQL_KEYS = 999;
    /* access modifiers changed from: private */
    public ReactDatabaseSupplier mReactDatabaseSupplier;
    private boolean mShuttingDown = false;

    public AsyncStorageModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.mReactDatabaseSupplier = ReactDatabaseSupplier.getInstance(reactContext);
    }

    public String getName() {
        return "AsyncSQLiteDBStorage";
    }

    public void initialize() {
        super.initialize();
        this.mShuttingDown = false;
    }

    public void onCatalystInstanceDestroy() {
        this.mShuttingDown = true;
    }

    public void clearSensitiveData() {
        this.mReactDatabaseSupplier.clearAndCloseDatabase();
    }

    @ReactMethod
    public void multiGet(final ReadableArray keys, final Callback callback) {
        if (keys == null) {
            callback.invoke(AsyncStorageErrorUtil.getInvalidKeyError(null), null);
            return;
        }
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* access modifiers changed from: protected */
            public void doInBackgroundGuarded(Void... params) {
                if (!AsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(AsyncStorageErrorUtil.getDBError(null), null);
                    return;
                }
                String[] columns = {"key", "value"};
                HashSet<String> keysRemaining = SetBuilder.newHashSet();
                WritableArray data = Arguments.createArray();
                int keyStart = 0;
                while (keyStart < keys.size()) {
                    int keyCount = Math.min(keys.size() - keyStart, AsyncStorageModule.MAX_SQL_KEYS);
                    Cursor cursor = AsyncStorageModule.this.mReactDatabaseSupplier.get().query("catalystLocalStorage", columns, AsyncLocalStorageUtil.buildKeySelection(keyCount), AsyncLocalStorageUtil.buildKeySelectionArgs(keys, keyStart, keyCount), null, null, null);
                    keysRemaining.clear();
                    try {
                        if (cursor.getCount() != keys.size()) {
                            for (int keyIndex = keyStart; keyIndex < keyStart + keyCount; keyIndex++) {
                                keysRemaining.add(keys.getString(keyIndex));
                            }
                        }
                        if (cursor.moveToFirst()) {
                            do {
                                WritableArray row = Arguments.createArray();
                                row.pushString(cursor.getString(0));
                                row.pushString(cursor.getString(1));
                                data.pushArray(row);
                                keysRemaining.remove(cursor.getString(0));
                            } while (cursor.moveToNext());
                        }
                        cursor.close();
                        Iterator it = keysRemaining.iterator();
                        while (it.hasNext()) {
                            String key = (String) it.next();
                            WritableArray row2 = Arguments.createArray();
                            row2.pushString(key);
                            row2.pushNull();
                            data.pushArray(row2);
                        }
                        keysRemaining.clear();
                        keyStart += AsyncStorageModule.MAX_SQL_KEYS;
                    } catch (Exception e) {
                        FLog.m1848w(ReactConstants.TAG, e.getMessage(), (Throwable) e);
                        callback.invoke(AsyncStorageErrorUtil.getError(null, e.getMessage()), null);
                        cursor.close();
                        return;
                    } catch (Throwable th) {
                        cursor.close();
                        throw th;
                    }
                }
                callback.invoke(null, data);
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public void multiSet(final ReadableArray keyValueArray, final Callback callback) {
        if (keyValueArray.size() == 0) {
            callback.invoke(AsyncStorageErrorUtil.getInvalidKeyError(null));
            return;
        }
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* access modifiers changed from: protected */
            public void doInBackgroundGuarded(Void... params) {
                if (!AsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(AsyncStorageErrorUtil.getDBError(null));
                    return;
                }
                SQLiteStatement statement = AsyncStorageModule.this.mReactDatabaseSupplier.get().compileStatement("INSERT OR REPLACE INTO catalystLocalStorage VALUES (?, ?);");
                WritableMap error = null;
                try {
                    AsyncStorageModule.this.mReactDatabaseSupplier.get().beginTransaction();
                    int idx = 0;
                    while (idx < keyValueArray.size()) {
                        if (keyValueArray.getArray(idx).size() != 2) {
                            WritableMap error2 = AsyncStorageErrorUtil.getInvalidValueError(null);
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                return;
                            } catch (Exception e) {
                                FLog.m1848w(ReactConstants.TAG, e.getMessage(), (Throwable) e);
                                if (error2 == null) {
                                    WritableMap error3 = AsyncStorageErrorUtil.getError(null, e.getMessage());
                                    return;
                                }
                                return;
                            }
                        } else if (keyValueArray.getArray(idx).getString(0) == null) {
                            WritableMap error4 = AsyncStorageErrorUtil.getInvalidKeyError(null);
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                return;
                            } catch (Exception e2) {
                                FLog.m1848w(ReactConstants.TAG, e2.getMessage(), (Throwable) e2);
                                if (error4 == null) {
                                    WritableMap error5 = AsyncStorageErrorUtil.getError(null, e2.getMessage());
                                    return;
                                }
                                return;
                            }
                        } else if (keyValueArray.getArray(idx).getString(1) == null) {
                            WritableMap error6 = AsyncStorageErrorUtil.getInvalidValueError(null);
                            try {
                                return;
                            } catch (Exception e3) {
                                FLog.m1848w(ReactConstants.TAG, e3.getMessage(), (Throwable) e3);
                                if (error6 == null) {
                                    WritableMap error7 = AsyncStorageErrorUtil.getError(null, e3.getMessage());
                                    return;
                                }
                                return;
                            }
                        } else {
                            statement.clearBindings();
                            statement.bindString(1, keyValueArray.getArray(idx).getString(0));
                            statement.bindString(2, keyValueArray.getArray(idx).getString(1));
                            statement.execute();
                            idx++;
                        }
                    }
                    AsyncStorageModule.this.mReactDatabaseSupplier.get().setTransactionSuccessful();
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                    } catch (Exception e4) {
                        FLog.m1848w(ReactConstants.TAG, e4.getMessage(), (Throwable) e4);
                        if (error == null) {
                            error = AsyncStorageErrorUtil.getError(null, e4.getMessage());
                        }
                    }
                } catch (Exception e5) {
                    FLog.m1848w(ReactConstants.TAG, e5.getMessage(), (Throwable) e5);
                    error = AsyncStorageErrorUtil.getError(null, e5.getMessage());
                    try {
                    } catch (Exception e6) {
                        FLog.m1848w(ReactConstants.TAG, e6.getMessage(), (Throwable) e6);
                        if (error == null) {
                            error = AsyncStorageErrorUtil.getError(null, e6.getMessage());
                        }
                    }
                } finally {
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                    } catch (Exception e7) {
                        FLog.m1848w(ReactConstants.TAG, e7.getMessage(), (Throwable) e7);
                        if (error == null) {
                            WritableMap error8 = AsyncStorageErrorUtil.getError(null, e7.getMessage());
                        }
                    }
                }
                if (error != null) {
                    callback.invoke(error);
                    return;
                }
                callback.invoke(new Object[0]);
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public void multiRemove(final ReadableArray keys, final Callback callback) {
        if (keys.size() == 0) {
            callback.invoke(AsyncStorageErrorUtil.getInvalidKeyError(null));
            return;
        }
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* access modifiers changed from: protected */
            public void doInBackgroundGuarded(Void... params) {
                if (!AsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(AsyncStorageErrorUtil.getDBError(null));
                    return;
                }
                WritableMap error = null;
                try {
                    AsyncStorageModule.this.mReactDatabaseSupplier.get().beginTransaction();
                    for (int keyStart = 0; keyStart < keys.size(); keyStart += AsyncStorageModule.MAX_SQL_KEYS) {
                        int keyCount = Math.min(keys.size() - keyStart, AsyncStorageModule.MAX_SQL_KEYS);
                        AsyncStorageModule.this.mReactDatabaseSupplier.get().delete("catalystLocalStorage", AsyncLocalStorageUtil.buildKeySelection(keyCount), AsyncLocalStorageUtil.buildKeySelectionArgs(keys, keyStart, keyCount));
                    }
                    AsyncStorageModule.this.mReactDatabaseSupplier.get().setTransactionSuccessful();
                    try {
                    } catch (Exception e) {
                        FLog.m1848w(ReactConstants.TAG, e.getMessage(), (Throwable) e);
                        if (error == null) {
                            error = AsyncStorageErrorUtil.getError(null, e.getMessage());
                        }
                    }
                } catch (Exception e2) {
                    FLog.m1848w(ReactConstants.TAG, e2.getMessage(), (Throwable) e2);
                    error = AsyncStorageErrorUtil.getError(null, e2.getMessage());
                    try {
                    } catch (Exception e3) {
                        FLog.m1848w(ReactConstants.TAG, e3.getMessage(), (Throwable) e3);
                        if (error == null) {
                            error = AsyncStorageErrorUtil.getError(null, e3.getMessage());
                        }
                    }
                } finally {
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                    } catch (Exception e4) {
                        FLog.m1848w(ReactConstants.TAG, e4.getMessage(), (Throwable) e4);
                        if (error == null) {
                            WritableMap error2 = AsyncStorageErrorUtil.getError(null, e4.getMessage());
                        }
                    }
                }
                if (error != null) {
                    callback.invoke(error);
                    return;
                }
                callback.invoke(new Object[0]);
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public void multiMerge(final ReadableArray keyValueArray, final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* access modifiers changed from: protected */
            public void doInBackgroundGuarded(Void... params) {
                if (!AsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(AsyncStorageErrorUtil.getDBError(null));
                    return;
                }
                WritableMap error = null;
                try {
                    AsyncStorageModule.this.mReactDatabaseSupplier.get().beginTransaction();
                    int idx = 0;
                    while (idx < keyValueArray.size()) {
                        if (keyValueArray.getArray(idx).size() != 2) {
                            WritableMap error2 = AsyncStorageErrorUtil.getInvalidValueError(null);
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                return;
                            } catch (Exception e) {
                                FLog.m1848w(ReactConstants.TAG, e.getMessage(), (Throwable) e);
                                if (error2 == null) {
                                    WritableMap error3 = AsyncStorageErrorUtil.getError(null, e.getMessage());
                                    return;
                                }
                                return;
                            }
                        } else if (keyValueArray.getArray(idx).getString(0) == null) {
                            WritableMap error4 = AsyncStorageErrorUtil.getInvalidKeyError(null);
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                return;
                            } catch (Exception e2) {
                                FLog.m1848w(ReactConstants.TAG, e2.getMessage(), (Throwable) e2);
                                if (error4 == null) {
                                    WritableMap error5 = AsyncStorageErrorUtil.getError(null, e2.getMessage());
                                    return;
                                }
                                return;
                            }
                        } else if (keyValueArray.getArray(idx).getString(1) == null) {
                            WritableMap error6 = AsyncStorageErrorUtil.getInvalidValueError(null);
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                return;
                            } catch (Exception e3) {
                                FLog.m1848w(ReactConstants.TAG, e3.getMessage(), (Throwable) e3);
                                if (error6 == null) {
                                    WritableMap error7 = AsyncStorageErrorUtil.getError(null, e3.getMessage());
                                    return;
                                }
                                return;
                            }
                        } else if (!AsyncLocalStorageUtil.mergeImpl(AsyncStorageModule.this.mReactDatabaseSupplier.get(), keyValueArray.getArray(idx).getString(0), keyValueArray.getArray(idx).getString(1))) {
                            WritableMap error8 = AsyncStorageErrorUtil.getDBError(null);
                            try {
                                return;
                            } catch (Exception e4) {
                                FLog.m1848w(ReactConstants.TAG, e4.getMessage(), (Throwable) e4);
                                if (error8 == null) {
                                    WritableMap error9 = AsyncStorageErrorUtil.getError(null, e4.getMessage());
                                    return;
                                }
                                return;
                            }
                        } else {
                            idx++;
                        }
                    }
                    AsyncStorageModule.this.mReactDatabaseSupplier.get().setTransactionSuccessful();
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                    } catch (Exception e5) {
                        FLog.m1848w(ReactConstants.TAG, e5.getMessage(), (Throwable) e5);
                        if (error == null) {
                            error = AsyncStorageErrorUtil.getError(null, e5.getMessage());
                        }
                    }
                } catch (Exception e6) {
                    FLog.m1848w(ReactConstants.TAG, e6.getMessage(), (Throwable) e6);
                    error = AsyncStorageErrorUtil.getError(null, e6.getMessage());
                    try {
                    } catch (Exception e7) {
                        FLog.m1848w(ReactConstants.TAG, e7.getMessage(), (Throwable) e7);
                        if (error == null) {
                            error = AsyncStorageErrorUtil.getError(null, e7.getMessage());
                        }
                    }
                } finally {
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                    } catch (Exception e8) {
                        FLog.m1848w(ReactConstants.TAG, e8.getMessage(), (Throwable) e8);
                        if (error == null) {
                            WritableMap error10 = AsyncStorageErrorUtil.getError(null, e8.getMessage());
                        }
                    }
                }
                if (error != null) {
                    callback.invoke(error);
                    return;
                }
                callback.invoke(new Object[0]);
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public void clear(final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* access modifiers changed from: protected */
            public void doInBackgroundGuarded(Void... params) {
                if (!AsyncStorageModule.this.mReactDatabaseSupplier.ensureDatabase()) {
                    callback.invoke(AsyncStorageErrorUtil.getDBError(null));
                    return;
                }
                try {
                    AsyncStorageModule.this.mReactDatabaseSupplier.clear();
                    callback.invoke(new Object[0]);
                } catch (Exception e) {
                    FLog.m1848w(ReactConstants.TAG, e.getMessage(), (Throwable) e);
                    callback.invoke(AsyncStorageErrorUtil.getError(null, e.getMessage()));
                }
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public void getAllKeys(final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* access modifiers changed from: protected */
            public void doInBackgroundGuarded(Void... params) {
                if (!AsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(AsyncStorageErrorUtil.getDBError(null), null);
                    return;
                }
                WritableArray data = Arguments.createArray();
                Cursor cursor = AsyncStorageModule.this.mReactDatabaseSupplier.get().query("catalystLocalStorage", new String[]{"key"}, null, null, null, null, null);
                try {
                    if (cursor.moveToFirst()) {
                        do {
                            data.pushString(cursor.getString(0));
                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                    callback.invoke(null, data);
                } catch (Exception e) {
                    FLog.m1848w(ReactConstants.TAG, e.getMessage(), (Throwable) e);
                    callback.invoke(AsyncStorageErrorUtil.getError(null, e.getMessage()), null);
                    cursor.close();
                } catch (Throwable th) {
                    cursor.close();
                    throw th;
                }
            }
        }.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public boolean ensureDatabase() {
        return !this.mShuttingDown && this.mReactDatabaseSupplier.ensureDatabase();
    }
}
