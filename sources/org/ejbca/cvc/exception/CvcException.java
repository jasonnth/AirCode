package org.ejbca.cvc.exception;

public class CvcException extends Exception {
    private static final long serialVersionUID = 1;

    public CvcException() {
    }

    public CvcException(String str) {
        super(str);
    }

    public CvcException(Throwable th) {
        super(th);
    }

    public CvcException(String str, Throwable th) {
        super(str, th);
    }
}
