package net.p318sf.scuba.smartcards;

import java.util.EventObject;

/* renamed from: net.sf.scuba.smartcards.APDUEvent */
public class APDUEvent extends EventObject {
    private static final long serialVersionUID = 7152351242541552732L;
    private CommandAPDU capdu;
    private ResponseAPDU rapdu;
    private int sequenceNumber;
    private Object type;

    public APDUEvent(Object obj, Object obj2, int i, CommandAPDU commandAPDU, ResponseAPDU responseAPDU) {
        super(obj);
        this.type = obj2;
        this.sequenceNumber = i;
        this.capdu = commandAPDU;
        this.rapdu = responseAPDU;
    }

    public CommandAPDU getCommandAPDU() {
        return this.capdu;
    }

    public ResponseAPDU getResponseAPDU() {
        return this.rapdu;
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    public Object getType() {
        return this.type;
    }
}
