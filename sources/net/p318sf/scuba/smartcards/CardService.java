package net.p318sf.scuba.smartcards;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: net.sf.scuba.smartcards.CardService */
public abstract class CardService implements Serializable {
    protected static final int SESSION_STARTED_STATE = 1;
    protected static final int SESSION_STOPPED_STATE = 0;
    private static final Map<String, String> objectToServiceMap = new HashMap();
    private static final long serialVersionUID = 5618527358158494957L;
    private Collection<APDUListener> apduListeners = new HashSet();
    protected int state = 0;

    static {
        objectToServiceMap.put("javax.smartcardio.CardTerminal", "net.sf.scuba.smartcards.TerminalCardService");
        objectToServiceMap.put("sun.security.smartcardio.TerminalImpl", "net.sf.scuba.smartcards.TerminalCardService");
        objectToServiceMap.put("android.nfc.tech.IsoDep", "net.sf.scuba.smartcards.IsoDepCardService");
    }

    public static CardService getInstance(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        String canonicalName = obj.getClass().getCanonicalName();
        for (Entry entry : objectToServiceMap.entrySet()) {
            try {
                Class cls = Class.forName((String) entry.getKey());
                String str = (String) entry.getValue();
                if (cls.isInstance(obj)) {
                    return (CardService) Class.forName(str).getConstructor(new Class[]{cls}).newInstance(new Object[]{obj});
                }
                continue;
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            } catch (ClassNotFoundException e2) {
            }
        }
        throw new IllegalArgumentException("Could not find a CardService for object of class \"" + canonicalName + "\"");
    }

    public void addAPDUListener(APDUListener aPDUListener) {
        if (this.apduListeners != null) {
            this.apduListeners.add(aPDUListener);
        }
    }

    public abstract void close();

    public abstract byte[] getATR() throws CardServiceException;

    public boolean isExtendedAPDULengthSupported() {
        return false;
    }

    public abstract boolean isOpen();

    /* access modifiers changed from: protected */
    public void notifyExchangedAPDU(int i, CommandAPDU commandAPDU, ResponseAPDU responseAPDU) {
        if (this.apduListeners != null && this.apduListeners.size() >= 1) {
            APDUEvent aPDUEvent = new APDUEvent(this, "RAW", i, commandAPDU, responseAPDU);
            for (APDUListener exchangedAPDU : this.apduListeners) {
                exchangedAPDU.exchangedAPDU(aPDUEvent);
            }
        }
    }

    public abstract void open() throws CardServiceException;

    public void removeAPDUListener(APDUListener aPDUListener) {
        if (this.apduListeners != null) {
            this.apduListeners.remove(aPDUListener);
        }
    }

    public abstract ResponseAPDU transmit(CommandAPDU commandAPDU) throws CardServiceException;
}
