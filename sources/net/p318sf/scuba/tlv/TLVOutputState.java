package net.p318sf.scuba.tlv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;
import net.p318sf.scuba.util.Hex;

/* renamed from: net.sf.scuba.tlv.TLVOutputState */
class TLVOutputState implements Cloneable {
    private boolean isAtStartOfLength;
    private boolean isAtStartOfTag;
    private boolean isReadingValue;
    private Stack<TLVStruct> state;

    /* renamed from: net.sf.scuba.tlv.TLVOutputState$TLVStruct */
    private class TLVStruct implements Cloneable {
        /* access modifiers changed from: private */
        public boolean isLengthSet = false;
        private int length = Integer.MAX_VALUE;
        private int tag;
        private ByteArrayOutputStream value = new ByteArrayOutputStream();

        public TLVStruct(int i) {
            this.tag = i;
        }

        public Object clone() {
            TLVStruct tLVStruct = new TLVStruct(this.tag);
            tLVStruct.length = this.length;
            tLVStruct.value = new ByteArrayOutputStream();
            try {
                tLVStruct.value.write(this.value.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tLVStruct;
        }

        public int getLength() {
            return this.length;
        }

        public int getTag() {
            return this.tag;
        }

        public byte[] getValue() {
            return this.value.toByteArray();
        }

        public int getValueBytesProcessed() {
            return this.value.size();
        }

        public boolean isLengthSet() {
            return this.isLengthSet;
        }

        public void setLength(int i) {
            this.length = i;
            this.isLengthSet = true;
        }

        public String toString() {
            byte[] byteArray = this.value.toByteArray();
            return "[TLVStruct " + Integer.toHexString(this.tag) + ", " + (this.isLengthSet ? Integer.valueOf(this.length) : "UNDEFINED") + ", " + Hex.bytesToHexString(byteArray) + "(" + byteArray.length + ") ]";
        }

        public void write(byte[] bArr, int i, int i2) {
            this.value.write(bArr, i, i2);
        }
    }

    public TLVOutputState() {
        this.state = new Stack<>();
        this.isAtStartOfTag = true;
        this.isAtStartOfLength = false;
        this.isReadingValue = false;
    }

    private TLVOutputState(Stack<TLVStruct> stack, boolean z, boolean z2, boolean z3) {
        this.state = stack;
        this.isAtStartOfTag = z;
        this.isAtStartOfLength = z2;
        this.isReadingValue = z3;
    }

    public boolean canBeWritten() {
        Iterator it = this.state.iterator();
        while (it.hasNext()) {
            if (!((TLVStruct) it.next()).isLengthSet()) {
                return false;
            }
        }
        return true;
    }

    public Object clone() {
        return new TLVOutputState((Stack) this.state.clone(), this.isAtStartOfTag, this.isAtStartOfLength, this.isReadingValue);
    }

    public int getLength() {
        if (this.state.isEmpty()) {
            throw new IllegalStateException("Length not yet known.");
        }
        int length = ((TLVStruct) this.state.peek()).getLength();
        if (length >= 0) {
            return length;
        }
        throw new IllegalStateException("Length not yet knwon.");
    }

    public int getTag() {
        if (!this.state.isEmpty()) {
            return ((TLVStruct) this.state.peek()).getTag();
        }
        throw new IllegalStateException("Tag not yet read.");
    }

    public byte[] getValue() {
        if (!this.state.isEmpty()) {
            return ((TLVStruct) this.state.peek()).getValue();
        }
        throw new IllegalStateException("Cannot get value yet.");
    }

    public int getValueBytesLeft() {
        if (this.state.isEmpty()) {
            throw new IllegalStateException("Length of value is unknown.");
        }
        TLVStruct tLVStruct = (TLVStruct) this.state.peek();
        return tLVStruct.getLength() - tLVStruct.getValueBytesProcessed();
    }

    public int getValueBytesProcessed() {
        return ((TLVStruct) this.state.peek()).getValueBytesProcessed();
    }

    public boolean isAtStartOfLength() {
        return this.isAtStartOfLength;
    }

    public boolean isAtStartOfTag() {
        return this.isAtStartOfTag;
    }

    public boolean isDummyLengthSet() {
        if (this.state.isEmpty()) {
            return false;
        }
        return !((TLVStruct) this.state.peek()).isLengthSet();
    }

    public boolean isProcessingValue() {
        return this.isReadingValue;
    }

    public void setDummyLengthProcessed() {
        this.isAtStartOfTag = false;
        this.isAtStartOfLength = false;
        this.isReadingValue = true;
    }

    public void setLengthProcessed(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Cannot set negative length (length = " + i + ").");
        }
        TLVStruct tLVStruct = (TLVStruct) this.state.pop();
        if (!this.state.isEmpty()) {
            TLVStruct tLVStruct2 = (TLVStruct) this.state.peek();
            byte[] lengthAsBytes = TLVUtil.getLengthAsBytes(i);
            tLVStruct2.write(lengthAsBytes, 0, lengthAsBytes.length);
        }
        tLVStruct.setLength(i);
        this.state.push(tLVStruct);
        this.isAtStartOfTag = false;
        this.isAtStartOfLength = false;
        this.isReadingValue = true;
    }

    public void setTagProcessed(int i) {
        TLVStruct tLVStruct = new TLVStruct(i);
        if (!this.state.isEmpty()) {
            TLVStruct tLVStruct2 = (TLVStruct) this.state.peek();
            byte[] tagAsBytes = TLVUtil.getTagAsBytes(i);
            tLVStruct2.write(tagAsBytes, 0, tagAsBytes.length);
        }
        this.state.push(tLVStruct);
        this.isAtStartOfTag = false;
        this.isAtStartOfLength = true;
        this.isReadingValue = false;
    }

    public String toString() {
        return this.state.toString();
    }

    public void updatePreviousLength(int i) {
        if (!this.state.isEmpty()) {
            TLVStruct tLVStruct = (TLVStruct) this.state.peek();
            if (!tLVStruct.isLengthSet || tLVStruct.getLength() != i) {
                tLVStruct.setLength(i);
                if (tLVStruct.getValueBytesProcessed() == tLVStruct.getLength()) {
                    this.state.pop();
                    byte[] lengthAsBytes = TLVUtil.getLengthAsBytes(i);
                    byte[] value = tLVStruct.getValue();
                    updateValueBytesProcessed(lengthAsBytes, 0, lengthAsBytes.length);
                    updateValueBytesProcessed(value, 0, value.length);
                    this.isAtStartOfTag = true;
                    this.isAtStartOfLength = false;
                    this.isReadingValue = false;
                }
            }
        }
    }

    public void updateValueBytesProcessed(byte[] bArr, int i, int i2) {
        if (!this.state.isEmpty()) {
            TLVStruct tLVStruct = (TLVStruct) this.state.peek();
            int length = tLVStruct.getLength() - tLVStruct.getValueBytesProcessed();
            if (i2 > length) {
                throw new IllegalArgumentException("Cannot process " + i2 + " bytes! Only " + length + " bytes left in this TLV object " + tLVStruct);
            }
            tLVStruct.write(bArr, i, i2);
            if (tLVStruct.getValueBytesProcessed() == tLVStruct.getLength()) {
                this.state.pop();
                updateValueBytesProcessed(tLVStruct.getValue(), 0, tLVStruct.getLength());
                this.isAtStartOfTag = true;
                this.isAtStartOfLength = false;
                this.isReadingValue = false;
                return;
            }
            this.isAtStartOfTag = false;
            this.isAtStartOfLength = false;
            this.isReadingValue = true;
        }
    }
}
