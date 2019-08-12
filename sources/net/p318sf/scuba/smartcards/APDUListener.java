package net.p318sf.scuba.smartcards;

import java.util.EventListener;

/* renamed from: net.sf.scuba.smartcards.APDUListener */
public interface APDUListener extends EventListener {
    void exchangedAPDU(APDUEvent aPDUEvent);
}
