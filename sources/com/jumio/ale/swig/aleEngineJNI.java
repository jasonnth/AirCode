package com.jumio.ale.swig;

public class aleEngineJNI {
    public static final native long ALECore_createRequest(long j, ALECore aLECore) throws Exception;

    public static final native void ALECore_destroyRequest(long j, ALECore aLECore, long j2, ALERequest aLERequest);

    public static final native void ALEHeader_add__SWIG_0(long j, ALEHeader aLEHeader, String str, String str2);

    public static final native void ALEHeader_clear(long j, ALEHeader aLEHeader);

    public static final native int ALERequest_calculateRequestFinish(long j, ALERequest aLERequest);

    public static final native int ALERequest_calculateRequestInit(long j, ALERequest aLERequest, long j2, ALEHeader aLEHeader, int i);

    public static final native int ALERequest_calculateRequestSize(long j, ALERequest aLERequest, long j2, ALEHeader aLEHeader, int i);

    public static final native int ALERequest_calculateRequestUpdate(long j, ALERequest aLERequest, int i);

    public static final native int ALERequest_calculateResponseSize(long j, ALERequest aLERequest, byte[] bArr) throws Exception;

    public static final native int ALERequest_finishRequest__SWIG_0(long j, ALERequest aLERequest, byte[] bArr, int i) throws Exception;

    public static final native int ALERequest_finishRequest__SWIG_1(long j, ALERequest aLERequest, byte[] bArr) throws Exception;

    public static final native boolean ALERequest_finishResponse(long j, ALERequest aLERequest) throws Exception;

    public static final native int ALERequest_getErrorCode(long j, ALERequest aLERequest);

    public static final native int ALERequest_initRequest__SWIG_0(long j, ALERequest aLERequest, long j2, ALEHeader aLEHeader, int i, byte[] bArr, int i2) throws Exception;

    public static final native int ALERequest_initRequest__SWIG_1(long j, ALERequest aLERequest, long j2, ALEHeader aLEHeader, int i, byte[] bArr) throws Exception;

    public static final native void ALERequest_initResponse(long j, ALERequest aLERequest);

    public static final native boolean ALERequest_isKeyUpdate(long j, ALERequest aLERequest);

    public static final native boolean ALERequest_isVerified(long j, ALERequest aLERequest);

    public static final native int ALERequest_request__SWIG_0(long j, ALERequest aLERequest, long j2, ALEHeader aLEHeader, byte[] bArr, byte[] bArr2, int i) throws Exception;

    public static final native int ALERequest_request__SWIG_1(long j, ALERequest aLERequest, long j2, ALEHeader aLEHeader, byte[] bArr, byte[] bArr2) throws Exception;

    public static final native int ALERequest_response__SWIG_0(long j, ALERequest aLERequest, byte[] bArr, byte[] bArr2, int i) throws Exception;

    public static final native int ALERequest_response__SWIG_1(long j, ALERequest aLERequest, byte[] bArr, byte[] bArr2) throws Exception;

    public static final native int ALERequest_updateRequest__SWIG_0(long j, ALERequest aLERequest, byte[] bArr, byte[] bArr2, int i) throws Exception;

    public static final native int ALERequest_updateRequest__SWIG_1(long j, ALERequest aLERequest, byte[] bArr, byte[] bArr2) throws Exception;

    public static final native int ALERequest_updateResponse__SWIG_0(long j, ALERequest aLERequest, byte[] bArr, byte[] bArr2, int i) throws Exception;

    public static final native int ALERequest_updateResponse__SWIG_1(long j, ALERequest aLERequest, byte[] bArr, byte[] bArr2) throws Exception;

    public static final native void ALESettings_change_ownership(ALESettings aLESettings, long j, boolean z);

    public static final native void ALESettings_director_connect(ALESettings aLESettings, long j, boolean z, boolean z2);

    public static final native String ALESettings_getDirectory(long j, ALESettings aLESettings);

    public static final native String ALESettings_getKeyID(long j, ALESettings aLESettings);

    public static final native String ALESettings_getPublicKey(long j, ALESettings aLESettings);

    public static final native void ALESettings_setDirectory(long j, ALESettings aLESettings, String str);

    public static final native void ALESettings_setKeyID(long j, ALESettings aLESettings, String str);

    public static final native void ALESettings_setPublicKey(long j, ALESettings aLESettings, String str);

    public static final native void delete_ALECore(long j);

    public static final native void delete_ALEHeader(long j);

    public static final native void delete_ALESettings(long j);

    public static final native long new_ALECore(long j, ALESettings aLESettings);

    public static final native long new_ALEHeader();

    public static final native long new_ALESettings();
}
