package com.jumio.ocr.impl.smartEngines.swig;

public class ConversionUtils {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    public static final class ImageFormat {
        public static final ImageFormat FORMAT_JPEG = new ImageFormat("FORMAT_JPEG");
        public static final ImageFormat FORMAT_PNG = new ImageFormat("FORMAT_PNG");
        public static final ImageFormat FORMAT_TIFF = new ImageFormat("FORMAT_TIFF");
        public static final ImageFormat FORMAT_UNKNOWN = new ImageFormat("FORMAT_UNKNOWN");
        public static final ImageFormat FORMAT_WEBP = new ImageFormat("FORMAT_WEBP");
        private static int swigNext = 0;
        private static ImageFormat[] swigValues = {FORMAT_TIFF, FORMAT_JPEG, FORMAT_PNG, FORMAT_WEBP, FORMAT_UNKNOWN};
        private final String swigName;
        private final int swigValue;

        public final int swigValue() {
            return this.swigValue;
        }

        public String toString() {
            return this.swigName;
        }

        public static ImageFormat swigToEnum(int swigValue2) {
            if (swigValue2 < swigValues.length && swigValue2 >= 0 && swigValues[swigValue2].swigValue == swigValue2) {
                return swigValues[swigValue2];
            }
            for (int i = 0; i < swigValues.length; i++) {
                if (swigValues[i].swigValue == swigValue2) {
                    return swigValues[i];
                }
            }
            throw new IllegalArgumentException("No enum " + ImageFormat.class + " with value " + swigValue2);
        }

        private ImageFormat(String swigName2) {
            this.swigName = swigName2;
            int i = swigNext;
            swigNext = i + 1;
            this.swigValue = i;
        }

        private ImageFormat(String swigName2, int swigValue2) {
            this.swigName = swigName2;
            this.swigValue = swigValue2;
            swigNext = swigValue2 + 1;
        }

        private ImageFormat(String swigName2, ImageFormat swigEnum) {
            this.swigName = swigName2;
            this.swigValue = swigEnum.swigValue;
            swigNext = this.swigValue + 1;
        }
    }

    protected ConversionUtils(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ConversionUtils obj) {
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
                jniInterfaceJNI.delete_ConversionUtils(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public static int convertImage(byte[] src_data, ImageFormat dst_format, byte[] dst_data) {
        return jniInterfaceJNI.ConversionUtils_convertImage(src_data, dst_format.swigValue(), dst_data);
    }

    public ConversionUtils() {
        this(jniInterfaceJNI.new_ConversionUtils(), true);
    }
}
