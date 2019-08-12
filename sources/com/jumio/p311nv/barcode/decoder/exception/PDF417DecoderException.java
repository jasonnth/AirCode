package com.jumio.p311nv.barcode.decoder.exception;

/* renamed from: com.jumio.nv.barcode.decoder.exception.PDF417DecoderException */
public class PDF417DecoderException extends Exception {
    private static final long serialVersionUID = 8205802214774364823L;

    public PDF417DecoderException(String str) {
        this((Exception) null, str);
    }

    public PDF417DecoderException(Exception exc, String str) {
        super(str);
        if (exc != null) {
            setStackTrace(exc.getStackTrace());
        }
    }

    public PDF417DecoderException(String str, Object... objArr) {
        this(null, str, objArr);
    }

    public PDF417DecoderException(Exception exc, String str, Object... objArr) {
        super(String.format(str, objArr));
        if (exc != null) {
            setStackTrace(exc.getStackTrace());
        }
    }
}
