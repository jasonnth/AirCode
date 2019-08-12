package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

public class ReactStylesDiffMap {
    final ReadableMap mBackingMap;

    public ReactStylesDiffMap(ReadableMap props) {
        this.mBackingMap = props;
    }

    public boolean hasKey(String name) {
        return this.mBackingMap.hasKey(name);
    }

    public boolean isNull(String name) {
        return this.mBackingMap.isNull(name);
    }

    public boolean getBoolean(String name, boolean restoreNullToDefaultValue) {
        return this.mBackingMap.isNull(name) ? restoreNullToDefaultValue : this.mBackingMap.getBoolean(name);
    }

    public double getDouble(String name, double restoreNullToDefaultValue) {
        return this.mBackingMap.isNull(name) ? restoreNullToDefaultValue : this.mBackingMap.getDouble(name);
    }

    public float getFloat(String name, float restoreNullToDefaultValue) {
        return this.mBackingMap.isNull(name) ? restoreNullToDefaultValue : (float) this.mBackingMap.getDouble(name);
    }

    public int getInt(String name, int restoreNullToDefaultValue) {
        return this.mBackingMap.isNull(name) ? restoreNullToDefaultValue : this.mBackingMap.getInt(name);
    }

    public String getString(String name) {
        return this.mBackingMap.getString(name);
    }

    public ReadableArray getArray(String key) {
        return this.mBackingMap.getArray(key);
    }

    public ReadableMap getMap(String key) {
        return this.mBackingMap.getMap(key);
    }

    public String toString() {
        return "{ " + getClass().getSimpleName() + ": " + this.mBackingMap.toString() + " }";
    }
}
