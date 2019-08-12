package com.jumio.p311nv.api.helpers;

import com.jumio.sdk.exception.JumioErrorCase;
import com.jumio.sdk.exception.JumioException;

/* renamed from: com.jumio.nv.api.helpers.UploadException */
public class UploadException extends JumioException {
    public UploadException(JumioErrorCase jumioErrorCase, int i) {
        super(jumioErrorCase, i);
    }
}
