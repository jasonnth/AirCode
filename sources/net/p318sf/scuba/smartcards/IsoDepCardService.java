package net.p318sf.scuba.smartcards;

import android.annotation.TargetApi;
import android.nfc.tech.IsoDep;
import android.os.Build.VERSION;
import java.io.IOException;

/* renamed from: net.sf.scuba.smartcards.IsoDepCardService */
public class IsoDepCardService extends CardService {
    private static final long serialVersionUID = -8123218195642784731L;
    private int apduCount = 0;
    private IsoDep nfc;

    public IsoDepCardService(IsoDep nfc2) {
        this.nfc = nfc2;
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

    public ResponseAPDU transmit(CommandAPDU ourCommandAPDU) throws CardServiceException {
        try {
            if (!this.nfc.isConnected()) {
                throw new CardServiceException("Not connected");
            }
            byte[] responseBytes = this.nfc.transceive(ourCommandAPDU.getBytes());
            if (responseBytes == null || responseBytes.length < 2) {
                throw new CardServiceException("Failed response");
            }
            ResponseAPDU ourResponseAPDU = new ResponseAPDU(responseBytes);
            int i = this.apduCount + 1;
            this.apduCount = i;
            notifyExchangedAPDU(i, ourCommandAPDU, ourResponseAPDU);
            return ourResponseAPDU;
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
