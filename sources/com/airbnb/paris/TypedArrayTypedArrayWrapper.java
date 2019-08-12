package com.airbnb.paris;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import java.util.Arrays;
import java.util.HashSet;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TypedArrayTypedArrayWrapper.kt */
public final class TypedArrayTypedArrayWrapper implements TypedArrayWrapper {
    private final HashSet<Integer> NULL_RESOURCE_IDS = new HashSet<>(Arrays.asList(new Integer[]{Integer.valueOf(R.anim.null_), Integer.valueOf(R.color.null_), Integer.valueOf(R.drawable.null_)}));
    private final TypedArray typedArray;

    public TypedArrayTypedArrayWrapper(TypedArray typedArray2) {
        Intrinsics.checkParameterIsNotNull(typedArray2, "typedArray");
        this.typedArray = typedArray2;
    }

    public boolean isNull(int index) {
        return this.NULL_RESOURCE_IDS.contains(Integer.valueOf(this.typedArray.getResourceId(index, 0)));
    }

    public boolean hasValue(int index) {
        return this.typedArray.hasValue(index);
    }

    public boolean getBoolean(int index, boolean defValue) {
        return this.typedArray.getBoolean(index, defValue);
    }

    public int getColor(int index, int defValue) {
        return this.typedArray.getColor(index, defValue);
    }

    public ColorStateList getColorStateList(int index) {
        ColorStateList colorStateList = this.typedArray.getColorStateList(index);
        Intrinsics.checkExpressionValueIsNotNull(colorStateList, "typedArray.getColorStateList(index)");
        return colorStateList;
    }

    public int getDimensionPixelSize(int index, int defValue) {
        return this.typedArray.getDimensionPixelSize(index, defValue);
    }

    public Drawable getDrawable(int index) {
        Drawable drawable = this.typedArray.getDrawable(index);
        Intrinsics.checkExpressionValueIsNotNull(drawable, "typedArray.getDrawable(index)");
        return drawable;
    }

    public float getFloat(int index, float defValue) {
        return this.typedArray.getFloat(index, defValue);
    }

    public int getInt(int index, int defValue) {
        return this.typedArray.getInt(index, defValue);
    }

    public int getLayoutDimension(int index, int defValue) {
        return this.typedArray.getLayoutDimension(index, defValue);
    }

    public int getResourceId(int index, int defValue) {
        if (isNull(index)) {
            return 0;
        }
        return this.typedArray.getResourceId(index, 0);
    }

    public String getString(int index) {
        String string = this.typedArray.getString(index);
        Intrinsics.checkExpressionValueIsNotNull(string, "typedArray.getString(index)");
        return string;
    }

    public CharSequence getText(int index) {
        CharSequence text = this.typedArray.getText(index);
        Intrinsics.checkExpressionValueIsNotNull(text, "typedArray.getText(index)");
        return text;
    }

    public void recycle() {
        this.typedArray.recycle();
    }
}
