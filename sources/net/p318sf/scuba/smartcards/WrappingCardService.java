package net.p318sf.scuba.smartcards;

/* renamed from: net.sf.scuba.smartcards.WrappingCardService */
public class WrappingCardService extends CardService {
    private static final long serialVersionUID = -1872209495542386286L;
    private boolean enabled;
    private CardService service;
    private APDUWrapper wrapper;

    public WrappingCardService(CardService cardService, APDUWrapper aPDUWrapper) {
        this.service = cardService;
        this.wrapper = aPDUWrapper;
    }

    public void close() {
        this.service.close();
    }

    public void disable() {
        this.enabled = false;
    }

    public void enable() {
        this.enabled = true;
    }

    public byte[] getATR() throws CardServiceException {
        return this.service.getATR();
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isOpen() {
        return this.service.isOpen();
    }

    public void open() throws CardServiceException {
        this.service.open();
    }

    public ResponseAPDU transmit(CommandAPDU commandAPDU) throws CardServiceException {
        if (!isEnabled()) {
            return this.service.transmit(commandAPDU);
        }
        ResponseAPDU transmit = this.service.transmit(this.wrapper.wrap(commandAPDU));
        return this.wrapper.unwrap(transmit, transmit.getBytes().length);
    }
}
