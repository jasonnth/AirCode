package org.jmrtd;

import net.p318sf.scuba.smartcards.CardServiceException;

public class PACEException extends CardServiceException {
    private static final long serialVersionUID = 8383980807753919040L;

    public PACEException(String str) {
        super(str);
    }

    public PACEException(String str, int i) {
        super(str, i);
    }
}
