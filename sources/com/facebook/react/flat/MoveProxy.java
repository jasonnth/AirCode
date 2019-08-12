package com.facebook.react.flat;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ReactShadowNode;

final class MoveProxy {
    private ReactShadowNode[] mChildren = new ReactShadowNode[4];
    private int[] mMapping = new int[8];
    private ReadableArray mMoveTo;
    private int mSize;

    MoveProxy() {
    }

    public int size() {
        return this.mSize;
    }

    public void setChildMoveFrom(int moveFromIndex, ReactShadowNode node) {
        this.mChildren[moveFromToIndex(moveFromIndex)] = node;
    }

    public ReactShadowNode getChildMoveTo(int moveToIndex) {
        return this.mChildren[moveToToIndex(moveToIndex)];
    }

    public int getMoveFrom(int moveFromIndex) {
        return moveFromToValue(moveFromIndex);
    }

    public int getMoveTo(int moveToIndex) {
        return moveToToValue(moveToIndex);
    }

    public void setup(ReadableArray moveFrom, ReadableArray moveTo) {
        this.mMoveTo = moveTo;
        if (moveFrom == null) {
            setSize(0);
            return;
        }
        int size = moveFrom.size();
        int requiredSpace = size + size;
        if (this.mMapping.length < requiredSpace) {
            this.mMapping = new int[requiredSpace];
            this.mChildren = new FlatShadowNode[size];
        }
        setSize(size);
        setKeyValue(0, 0, moveFrom.getInt(0));
        for (int i = 1; i < size; i++) {
            int current = moveFrom.getInt(i);
            int j = i - 1;
            while (j >= 0 && moveFromToValue(j) >= current) {
                setKeyValue(j + 1, moveFromToIndex(j), moveFromToValue(j));
                j--;
            }
            setKeyValue(j + 1, i, current);
        }
    }

    /* renamed from: k */
    private static int m1889k(int i) {
        return i * 2;
    }

    /* renamed from: v */
    private static int m1890v(int i) {
        return (i * 2) + 1;
    }

    private void setKeyValue(int index, int key, int value) {
        this.mMapping[m1889k(index)] = key;
        this.mMapping[m1890v(index)] = value;
    }

    private int moveFromToIndex(int index) {
        return this.mMapping[m1889k(index)];
    }

    private int moveFromToValue(int index) {
        return this.mMapping[m1890v(index)];
    }

    private static int moveToToIndex(int index) {
        return index;
    }

    private int moveToToValue(int index) {
        return ((ReadableArray) Assertions.assumeNotNull(this.mMoveTo)).getInt(index);
    }

    private void setSize(int newSize) {
        for (int i = newSize; i < this.mSize; i++) {
            this.mChildren[i] = null;
        }
        this.mSize = newSize;
    }
}
