package com.fasterxml.jackson.core.util;

import com.airbnb.android.lib.fragments.verifiedid.OfficialIdPhotoSelectionFragment;

public class BufferRecycler {
    private static final int[] BYTE_BUFFER_LENGTHS = {8000, 8000, OfficialIdPhotoSelectionFragment.MAX_IMAGE_SIZE, OfficialIdPhotoSelectionFragment.MAX_IMAGE_SIZE};
    private static final int[] CHAR_BUFFER_LENGTHS = {4000, 4000, 200, 200};
    protected final byte[][] _byteBuffers;
    protected final char[][] _charBuffers;

    public BufferRecycler() {
        this(4, 4);
    }

    protected BufferRecycler(int bbCount, int cbCount) {
        this._byteBuffers = new byte[bbCount][];
        this._charBuffers = new char[cbCount][];
    }

    public final byte[] allocByteBuffer(int ix) {
        return allocByteBuffer(ix, 0);
    }

    public byte[] allocByteBuffer(int ix, int minSize) {
        int DEF_SIZE = byteBufferLength(ix);
        if (minSize < DEF_SIZE) {
            minSize = DEF_SIZE;
        }
        byte[] buffer = this._byteBuffers[ix];
        if (buffer == null || buffer.length < minSize) {
            return balloc(minSize);
        }
        this._byteBuffers[ix] = null;
        return buffer;
    }

    public final void releaseByteBuffer(int ix, byte[] buffer) {
        this._byteBuffers[ix] = buffer;
    }

    public final char[] allocCharBuffer(int ix) {
        return allocCharBuffer(ix, 0);
    }

    public char[] allocCharBuffer(int ix, int minSize) {
        int DEF_SIZE = charBufferLength(ix);
        if (minSize < DEF_SIZE) {
            minSize = DEF_SIZE;
        }
        char[] buffer = this._charBuffers[ix];
        if (buffer == null || buffer.length < minSize) {
            return calloc(minSize);
        }
        this._charBuffers[ix] = null;
        return buffer;
    }

    public void releaseCharBuffer(int ix, char[] buffer) {
        this._charBuffers[ix] = buffer;
    }

    /* access modifiers changed from: protected */
    public int byteBufferLength(int ix) {
        return BYTE_BUFFER_LENGTHS[ix];
    }

    /* access modifiers changed from: protected */
    public int charBufferLength(int ix) {
        return CHAR_BUFFER_LENGTHS[ix];
    }

    /* access modifiers changed from: protected */
    public byte[] balloc(int size) {
        return new byte[size];
    }

    /* access modifiers changed from: protected */
    public char[] calloc(int size) {
        return new char[size];
    }
}
