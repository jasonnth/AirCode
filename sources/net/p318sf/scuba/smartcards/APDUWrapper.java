package net.p318sf.scuba.smartcards;

/* renamed from: net.sf.scuba.smartcards.APDUWrapper */
public interface APDUWrapper {
    ResponseAPDU unwrap(ResponseAPDU responseAPDU, int i);

    CommandAPDU wrap(CommandAPDU commandAPDU);
}
