package net.p318sf.scuba.smartcards;

import android.annotation.TargetApi;
import android.nfc.tech.IsoDep;
import android.os.Build.VERSION;
import java.io.IOException;

/* renamed from: net.sf.scuba.smartcards.aIsoDepCardService */
public class aIsoDepCardService extends CardService {
    private static final long serialVersionUID = -8123218195642784731L;
    private int apduCount = 0;
    private IsoDep nfc;

    public aIsoDepCardService(IsoDep isoDep) {
        this.nfc = isoDep;
    }

    public void open() throws CardServiceException {
        if (!isOpen()) {
            try {
                this.nfc.connect();
                if (!this.nfc.isConnected()) {
                    throw new CardServiceException("failed to connect");
                }
                this.state = 1;
            } catch (IOException e) {
                e.printStackTrace();
                throw new CardServiceException(e.toString());
            }
        }
    }

    public boolean isOpen() {
        if (this.nfc.isConnected()) {
            this.state = 1;
            return true;
        }
        this.state = 0;
        return false;
    }

    public ResponseAPDU transmit(CommandAPDU commandAPDU) throws CardServiceException {
        try {
            if (!this.nfc.isConnected()) {
                throw new CardServiceException("Not connected");
            }
            byte[] transceive = this.nfc.transceive(commandAPDU.getBytes());
            if (transceive == null || transceive.length < 2) {
                throw new CardServiceException("Failed response");
            }
            ResponseAPDU responseAPDU = new ResponseAPDU(transceive);
            int i = this.apduCount + 1;
            this.apduCount = i;
            notifyExchangedAPDU(i, commandAPDU, responseAPDU);
            return responseAPDU;
        } catch (IOException e) {
            throw new CardServiceException(e.getMessage());
        } catch (Exception e2) {
            throw new CardServiceException(e2.getMessage());
        }
    }

    public byte[] getATR() {
        return null;
    }

    @TargetApi(16)
    public boolean isExtendedAPDULengthSupported() {
        if (VERSION.SDK_INT >= 16) {
            return this.nfc.isExtendedLengthApduSupported();
        }
        if (this.nfc.getMaxTransceiveLength() > 261) {
            return true;
        }
        return false;
    }

    public void close() {
        try {
            this.nfc.close();
            this.state = 0;
        } catch (IOException e) {
        }
    }
}
