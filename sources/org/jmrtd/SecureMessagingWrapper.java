package org.jmrtd;

import net.p318sf.scuba.smartcards.APDUWrapper;

public abstract class SecureMessagingWrapper implements APDUWrapper {
    public abstract long getSendSequenceCounter();
}
