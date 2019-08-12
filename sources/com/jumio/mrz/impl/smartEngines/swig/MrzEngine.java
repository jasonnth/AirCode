package com.jumio.mrz.impl.smartEngines.swig;

public class MrzEngine {

    /* renamed from: a */
    private long f3208a;
    protected boolean swigCMemOwn;

    public static final class ImageOrientation {
        public static final ImageOrientation InvertedLandscape = new ImageOrientation("InvertedLandscape");
        public static final ImageOrientation InvertedPortrait = new ImageOrientation("InvertedPortrait");
        public static final ImageOrientation Landscape = new ImageOrientation("Landscape");
        public static final ImageOrientation Portrait = new ImageOrientation("Portrait");

        /* renamed from: a */
        private static ImageOrientation[] f3209a = {Landscape, Portrait, InvertedLandscape, InvertedPortrait};

        /* renamed from: b */
        private static int f3210b = 0;

        /* renamed from: c */
        private final int f3211c;

        /* renamed from: d */
        private final String f3212d;

        public final int swigValue() {
            return this.f3211c;
        }

        public String toString() {
            return this.f3212d;
        }

        public static ImageOrientation swigToEnum(int i) {
            if (i < f3209a.length && i >= 0 && f3209a[i].f3211c == i) {
                return f3209a[i];
            }
            for (int i2 = 0; i2 < f3209a.length; i2++) {
                if (f3209a[i2].f3211c == i) {
                    return f3209a[i2];
                }
            }
            throw new IllegalArgumentException("No enum " + ImageOrientation.class + " with value " + i);
        }

        private ImageOrientation(String str) {
            this.f3212d = str;
            int i = f3210b;
            f3210b = i + 1;
            this.f3211c = i;
        }
    }

    protected MrzEngine(long j, boolean z) {
        this.swigCMemOwn = z;
        this.f3208a = j;
    }

    protected static long getCPtr(MrzEngine mrzEngine) {
        if (mrzEngine == null) {
            return 0;
        }
        return mrzEngine.f3208a;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.f3208a != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                mrzjniInterfaceJNI.delete_MrzEngine(this.f3208a);
            }
            this.f3208a = 0;
        }
    }

    public MrzEngine(MrzEngineInternalSettings mrzEngineInternalSettings) throws Exception {
        this(mrzjniInterfaceJNI.new_MrzEngine(MrzEngineInternalSettings.getCPtr(mrzEngineInternalSettings), mrzEngineInternalSettings), true);
    }

    public void InitializeSession(StreamReporterInterface streamReporterInterface, MrzEngineSessionHelpers mrzEngineSessionHelpers, MrzEngineSessionSettings mrzEngineSessionSettings) throws Exception {
        mrzjniInterfaceJNI.MrzEngine_InitializeSession__SWIG_0(this.f3208a, this, StreamReporterInterface.getCPtr(streamReporterInterface), streamReporterInterface, MrzEngineSessionHelpers.getCPtr(mrzEngineSessionHelpers), mrzEngineSessionHelpers, MrzEngineSessionSettings.getCPtr(mrzEngineSessionSettings), mrzEngineSessionSettings);
    }

    public void InitializeSession(StreamReporterInterface streamReporterInterface, MrzEngineSessionHelpers mrzEngineSessionHelpers) throws Exception {
        mrzjniInterfaceJNI.MrzEngine_InitializeSession__SWIG_1(this.f3208a, this, StreamReporterInterface.getCPtr(streamReporterInterface), streamReporterInterface, MrzEngineSessionHelpers.getCPtr(mrzEngineSessionHelpers), mrzEngineSessionHelpers);
    }

    public void InitializeSession(StreamReporterInterface streamReporterInterface) throws Exception {
        mrzjniInterfaceJNI.MrzEngine_InitializeSession__SWIG_2(this.f3208a, this, StreamReporterInterface.getCPtr(streamReporterInterface), streamReporterInterface);
    }

    public void TerminateSession() throws Exception {
        mrzjniInterfaceJNI.MrzEngine_TerminateSession(this.f3208a, this);
    }

    public void FeedUncompressedImageData(byte[] bArr, int i, int i2, int i3, int i4, MrzRect mrzRect, ImageOrientation imageOrientation) throws Exception {
        mrzjniInterfaceJNI.MrzEngine_FeedUncompressedImageData__SWIG_0(this.f3208a, this, bArr, i, i2, i3, i4, MrzRect.getCPtr(mrzRect), mrzRect, imageOrientation.swigValue());
    }

    public void FeedUncompressedImageData(byte[] bArr, int i, int i2, int i3, int i4, MrzRect mrzRect) throws Exception {
        mrzjniInterfaceJNI.MrzEngine_FeedUncompressedImageData__SWIG_1(this.f3208a, this, bArr, i, i2, i3, i4, MrzRect.getCPtr(mrzRect), mrzRect);
    }

    public void FeedUncompressedYUVImageData(byte[] bArr, int i, int i2, int i3, MrzRect mrzRect, ImageOrientation imageOrientation) throws Exception {
        mrzjniInterfaceJNI.MrzEngine_FeedUncompressedYUVImageData__SWIG_0(this.f3208a, this, bArr, i, i2, i3, MrzRect.getCPtr(mrzRect), mrzRect, imageOrientation.swigValue());
    }

    public void FeedUncompressedYUVImageData(byte[] bArr, int i, int i2, int i3, MrzRect mrzRect) throws Exception {
        mrzjniInterfaceJNI.MrzEngine_FeedUncompressedYUVImageData__SWIG_1(this.f3208a, this, bArr, i, i2, i3, MrzRect.getCPtr(mrzRect), mrzRect);
    }

    public void FeedImageFile(String str, ImageOrientation imageOrientation) throws Exception {
        mrzjniInterfaceJNI.MrzEngine_FeedImageFile__SWIG_0(this.f3208a, this, str, imageOrientation.swigValue());
    }

    public void FeedImageFile(String str) throws Exception {
        mrzjniInterfaceJNI.MrzEngine_FeedImageFile__SWIG_1(this.f3208a, this, str);
    }
}
