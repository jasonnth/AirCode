package com.jumio.ocr.impl.smartEngines.swig;

public class ResultAcceptorInterfaceSettings {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    public static final class FieldName {
        public static final FieldName AMEX_CVV = new FieldName("AMEX_CVV");
        public static final FieldName EXPIRY = new FieldName("EXPIRY");
        public static final FieldName NAME = new FieldName("NAME");
        public static final FieldName NAME_2NDLINE = new FieldName("NAME_2NDLINE");
        public static final FieldName NUMBER = new FieldName("NUMBER");
        public static final FieldName SB_CODE = new FieldName("SB_CODE");
        public static final FieldName UK_ACCOUNTNUM = new FieldName("UK_ACCOUNTNUM");
        public static final FieldName UK_SORTCODE = new FieldName("UK_SORTCODE");
        private static int swigNext = 0;
        private static FieldName[] swigValues = {NUMBER, EXPIRY, NAME, UK_SORTCODE, UK_ACCOUNTNUM, NAME_2NDLINE, AMEX_CVV, SB_CODE};
        private final String swigName;
        private final int swigValue;

        public final int swigValue() {
            return this.swigValue;
        }

        public String toString() {
            return this.swigName;
        }

        public static FieldName swigToEnum(int swigValue2) {
            if (swigValue2 < swigValues.length && swigValue2 >= 0 && swigValues[swigValue2].swigValue == swigValue2) {
                return swigValues[swigValue2];
            }
            for (int i = 0; i < swigValues.length; i++) {
                if (swigValues[i].swigValue == swigValue2) {
                    return swigValues[i];
                }
            }
            throw new IllegalArgumentException("No enum " + FieldName.class + " with value " + swigValue2);
        }

        private FieldName(String swigName2) {
            this.swigName = swigName2;
            int i = swigNext;
            swigNext = i + 1;
            this.swigValue = i;
        }

        private FieldName(String swigName2, int swigValue2) {
            this.swigName = swigName2;
            this.swigValue = swigValue2;
            swigNext = swigValue2 + 1;
        }

        private FieldName(String swigName2, FieldName swigEnum) {
            this.swigName = swigName2;
            this.swigValue = swigEnum.swigValue;
            swigNext = this.swigValue + 1;
        }
    }

    protected ResultAcceptorInterfaceSettings(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ResultAcceptorInterfaceSettings obj) {
        if (obj == null) {
            return 0;
        }
        return obj.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                jniInterfaceJNI.delete_ResultAcceptorInterfaceSettings(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public ResultAcceptorInterfaceSettings() {
        this(jniInterfaceJNI.new_ResultAcceptorInterfaceSettings__SWIG_0(), true);
    }

    public ResultAcceptorInterfaceSettings(ResultAcceptorInterfaceSettings other) {
        this(jniInterfaceJNI.new_ResultAcceptorInterfaceSettings__SWIG_1(getCPtr(other), other), true);
    }

    public boolean getOcrEnabledFlag(FieldName fieldName) {
        return jniInterfaceJNI.ResultAcceptorInterfaceSettings_getOcrEnabledFlag(this.swigCPtr, this, fieldName.swigValue());
    }

    public void setOcrEnabledFlag(FieldName fieldName, boolean value) {
        jniInterfaceJNI.ResultAcceptorInterfaceSettings_setOcrEnabledFlag(this.swigCPtr, this, fieldName.swigValue(), value);
    }

    public void setAllOcrEnabledFlag(boolean value) {
        jniInterfaceJNI.ResultAcceptorInterfaceSettings_setAllOcrEnabledFlag(this.swigCPtr, this, value);
    }
}
