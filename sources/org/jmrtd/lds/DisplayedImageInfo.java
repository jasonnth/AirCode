package org.jmrtd.lds;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import net.p318sf.scuba.tlv.TLVInputStream;
import net.p318sf.scuba.tlv.TLVOutputStream;
import net.p318sf.scuba.tlv.TLVUtil;

public class DisplayedImageInfo extends AbstractImageInfo {
    protected static final int DISPLAYED_PORTRAIT_TAG = 24384;
    protected static final int DISPLAYED_SIGNATURE_OR_MARK_TAG = 24387;
    private static final long serialVersionUID = 3801320585294302721L;
    private int displayedImageTag;

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ byte[] getEncoded() {
        return super.getEncoded();
    }

    public /* bridge */ /* synthetic */ int getHeight() {
        return super.getHeight();
    }

    public /* bridge */ /* synthetic */ InputStream getImageInputStream() {
        return super.getImageInputStream();
    }

    public /* bridge */ /* synthetic */ int getImageLength() {
        return super.getImageLength();
    }

    public /* bridge */ /* synthetic */ String getMimeType() {
        return super.getMimeType();
    }

    public /* bridge */ /* synthetic */ int getType() {
        return super.getType();
    }

    public /* bridge */ /* synthetic */ int getWidth() {
        return super.getWidth();
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public DisplayedImageInfo(int i, byte[] bArr) {
        super(i);
        this.displayedImageTag = getDisplayedImageTagFromType(i);
        setMimeType(getMimeTypeFromType(i));
        setImageBytes(bArr);
    }

    public DisplayedImageInfo(InputStream inputStream) throws IOException {
        readObject(inputStream);
    }

    /* access modifiers changed from: protected */
    public void readObject(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        this.displayedImageTag = tLVInputStream.readTag();
        if (this.displayedImageTag == 24384 || this.displayedImageTag == 24387) {
            int typeFromDisplayedImageTag = getTypeFromDisplayedImageTag(this.displayedImageTag);
            setType(typeFromDisplayedImageTag);
            setMimeType(getMimeTypeFromType(typeFromDisplayedImageTag));
            readImage(tLVInputStream, (long) tLVInputStream.readLength());
            return;
        }
        throw new IllegalArgumentException("Expected tag 0x5F40 or 0x5F43, found " + Integer.toHexString(this.displayedImageTag));
    }

    /* access modifiers changed from: protected */
    public void writeObject(OutputStream outputStream) throws IOException {
        TLVOutputStream tLVOutputStream = outputStream instanceof TLVOutputStream ? (TLVOutputStream) outputStream : new TLVOutputStream(outputStream);
        tLVOutputStream.writeTag(getDisplayedImageTagFromType(getType()));
        writeImage(tLVOutputStream);
        tLVOutputStream.writeValueEnd();
    }

    /* access modifiers changed from: 0000 */
    public int getDisplayedImageTag() {
        return this.displayedImageTag;
    }

    public long getRecordLength() {
        int imageLength = getImageLength();
        return 0 + ((long) TLVUtil.getTagLength(getDisplayedImageTagFromType(getType()))) + ((long) TLVUtil.getLengthLength(imageLength)) + ((long) imageLength);
    }

    private static String getMimeTypeFromType(int i) {
        switch (i) {
            case 0:
                return "image/jpeg";
            case 1:
                return "image/jpeg";
            case 2:
                return ImageInfo.WSQ_MIME_TYPE;
            default:
                throw new NumberFormatException("Unknown type: " + Integer.toHexString(i));
        }
    }

    private static int getDisplayedImageTagFromType(int i) {
        switch (i) {
            case 0:
                return 24384;
            case 1:
                return 24387;
            default:
                throw new NumberFormatException("Unknown type: " + Integer.toHexString(i));
        }
    }

    private static int getTypeFromDisplayedImageTag(int i) {
        switch (i) {
            case 24384:
                return 0;
            case 24387:
                return 1;
            default:
                throw new NumberFormatException("Unknown tag: " + Integer.toHexString(i));
        }
    }
}
