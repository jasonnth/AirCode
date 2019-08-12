package net.p318sf.scuba.smartcards;

/* renamed from: net.sf.scuba.smartcards.AbstractFileSystemStructured */
public abstract class AbstractFileSystemStructured implements FileSystemStructured {
    public static short MF_ID = 16128;
    private ISOFileInfo fileInfo = null;
    private int length = -1;

    /* renamed from: p2 */
    private int f6302p2 = 0;
    private int selectLe = 256;
    private short selectedFID = 0;
    private CardService service = null;

    public AbstractFileSystemStructured(CardService cardService) {
        this.service = cardService;
    }

    public AbstractFileSystemStructured(CardService cardService, boolean z) {
        int i = 0;
        this.service = cardService;
        this.f6302p2 = z ? 0 : 12;
        if (z) {
            i = 256;
        }
        this.selectLe = i;
    }

    private CommandAPDU createSelectFileAPDU(int i, int i2, byte[] bArr, int i3) {
        return i3 == 0 ? new CommandAPDU(0, -92, i, i2, bArr) : new CommandAPDU(0, -92, i, i2, bArr, i3);
    }

    private void selectFile(short s, int i) throws CardServiceException {
        selectFile(s == 0 ? new byte[0] : new byte[]{(byte) ((s >> 8) & 255), (byte) (s & 255)}, i);
    }

    private void selectFile(byte[] bArr, int i) throws CardServiceException {
        ResponseAPDU transmit = this.service.transmit(createSelectFileAPDU(i, this.f6302p2, bArr, this.selectLe));
        int sw = transmit.getSW();
        byte[] data = transmit.getData();
        if (sw != -28672) {
            throw new CardServiceException("File could not be selected.", sw);
        }
        this.fileInfo = new ISOFileInfo(data);
        if (this.fileInfo.fid != -1) {
            this.selectedFID = this.fileInfo.fid;
        }
        if (this.fileInfo.fileLength != -1) {
            this.length = this.fileInfo.fileLength;
        }
    }

    public int getFileLength() throws CardServiceException {
        return this.length;
    }

    public short getSelectedFID() {
        return this.selectedFID;
    }

    public abstract byte[] readBinary(int i, int i2);

    public void selectAID(byte[] bArr) throws CardServiceException {
        selectFile(bArr, 4);
    }

    public void selectDFRelative(short s) throws CardServiceException {
        selectFile(s, 1);
    }

    public void selectEFRelative(short s) throws CardServiceException {
        selectFile(s, 2);
    }

    public void selectFile(short s) throws CardServiceException {
        selectFile(s, 0);
    }

    public void selectMF() throws CardServiceException {
        selectFile(0, 0);
    }

    public void selectParent() throws CardServiceException {
        selectFile(0, 3);
    }

    public void selectPath(byte[] bArr) throws CardServiceException {
        selectFile(bArr, 8);
    }

    public void selectPathRelative(byte[] bArr) throws CardServiceException {
        selectFile(bArr, 9);
    }
}
