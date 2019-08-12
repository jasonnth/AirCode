package com.airbnb.paris;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SparseIntArrayTypedArrayWrapper.kt */
public final class SparseIntArrayTypedArrayWrapper implements TypedArrayWrapper {
    private final SparseIntArray attributeMap;
    private final Resources resources;

    public SparseIntArrayTypedArrayWrapper(Resources resources2, SparseIntArray attributeMap2) {
        Intrinsics.checkParameterIsNotNull(resources2, "resources");
        Intrinsics.checkParameterIsNotNull(attributeMap2, "attributeMap");
        this.resources = resources2;
        this.attributeMap = attributeMap2;
    }

    public boolean isNull(int index) {
        return false;
    }

    public boolean hasValue(int index) {
        return this.attributeMap.get(index, -1) != -1;
    }

    public boolean getBoolean(int index, boolean defValue) {
        return this.resources.getBoolean(this.attributeMap.get(index));
    }

    public int getColor(int index, int defValue) {
        return this.resources.getColor(this.attributeMap.get(index));
    }

    public ColorStateList getColorStateList(int index) {
        ColorStateList colorStateList = this.resources.getColorStateList(this.attributeMap.get(index));
        Intrinsics.checkExpressionValueIsNotNull(colorStateList, "resources.getColorStateList(attributeMap[index])");
        return colorStateList;
    }

    public int getDimensionPixelSize(int index, int defValue) {
        return this.resources.getDimensionPixelSize(this.attributeMap.get(index));
    }

    public Drawable getDrawable(int index) {
        Drawable drawable = this.resources.getDrawable(this.attributeMap.get(index));
        Intrinsics.checkExpressionValueIsNotNull(drawable, "resources.getDrawable(attributeMap[index])");
        return drawable;
    }

    public float getFloat(int index, float defValue) {
        return this.resources.getFraction(this.attributeMap.get(index), 1, 1);
    }

    public int getInt(int index, int defValue) {
        return this.resources.getInteger(this.attributeMap.get(index));
    }

    public int getLayoutDimension(int index, int defValue) {
        return this.resources.getDimensionPixelSize(this.attributeMap.get(index));
    }

    public int getResourceId(int index, int defValue) {
        return this.resources.getInteger(this.attributeMap.get(index));
    }

    public String getString(int index) {
        String string = this.resources.getString(this.attributeMap.get(index));
        Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(attributeMap[index])");
        return string;
    }

    public CharSequence getText(int index) {
        CharSequence text = this.resources.getText(this.attributeMap.get(index));
        Intrinsics.checkExpressionValueIsNotNull(text, "resources.getText(attributeMap[index])");
        return text;
    }

    public void recycle() {
        this.attributeMap.clear();
    }
}
