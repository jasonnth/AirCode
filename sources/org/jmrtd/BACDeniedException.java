package org.jmrtd;

import java.util.List;
import net.p318sf.scuba.smartcards.CardServiceException;

public class BACDeniedException extends CardServiceException {
    private static final long serialVersionUID = -7094953658210693249L;
    private List<BACKeySpec> triedBACEntries;

    public BACDeniedException(String str, List<BACKeySpec> list, int i) {
        super(str, i);
        this.triedBACEntries = list;
    }

    public List<BACKeySpec> getTriedEntries() {
        return this.triedBACEntries;
    }
}
