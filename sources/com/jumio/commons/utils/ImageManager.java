package com.jumio.commons.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.SparseArray;

public class ImageManager {
    private SparseArray<Bitmap> mBitmaps = new SparseArray<>();

    public boolean storeBitmap(int id, Bitmap bitmap) {
        if (hasBitmap(id)) {
            return false;
        }
        if (id == -1 && bitmap == null) {
            return false;
        }
        this.mBitmaps.put(id, bitmap);
        return true;
    }

    public Bitmap storeBitmap(int id, Bitmap bitmap, float degrees) {
        if (bitmap == null) {
            return null;
        }
        Matrix lMatrix = new Matrix();
        lMatrix.postRotate(degrees);
        Bitmap result = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), lMatrix, true);
        if (bitmap != result) {
            bitmap.recycle();
        }
        this.mBitmaps.put(id, result);
        return result;
    }

    public Bitmap getBitmap(int id) {
        return (Bitmap) this.mBitmaps.get(id);
    }

    public boolean hasBitmap(int id) {
        return getBitmap(id) != null;
    }

    public void deleteBitmap(int id) {
        if (getBitmap(id) != null) {
            getBitmap(id).recycle();
            this.mBitmaps.remove(id);
            System.gc();
        }
    }

    public void recycleBitmaps() {
        for (int i = 0; i < this.mBitmaps.size(); i++) {
            if (this.mBitmaps.valueAt(i) != null) {
                ((Bitmap) this.mBitmaps.valueAt(i)).recycle();
                this.mBitmaps.setValueAt(i, null);
            }
        }
        this.mBitmaps.clear();
        System.gc();
    }
}
