package com.jumio.p311nv.data.templatematcher;

import com.jumio.p311nv.enums.NVErrorCase;

/* renamed from: com.jumio.nv.data.templatematcher.TemplateLoadingException */
public class TemplateLoadingException extends Exception {
    private static final long serialVersionUID = 3430256624864688129L;
    private NVErrorCase mErrorCase;

    public TemplateLoadingException(String str) {
        super(str);
    }

    public TemplateLoadingException(Exception exc) {
        super(exc.getMessage());
        setStackTrace(exc.getStackTrace());
    }

    public TemplateLoadingException(NVErrorCase nVErrorCase) {
        this.mErrorCase = nVErrorCase;
    }

    public NVErrorCase getErrorCase() {
        return this.mErrorCase;
    }
}
