package org.jmrtd.lds;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import net.p318sf.scuba.tlv.TLVInputStream;
import net.p318sf.scuba.tlv.TLVOutputStream;

abstract class DisplayedImageDataGroup extends DataGroup {
    private static final int DISPLAYED_IMAGE_COUNT_TAG = 2;
    private static final long serialVersionUID = 5994136177872308962L;
    private int displayedImageTagToUse;
    private List<DisplayedImageInfo> imageInfos;

    public DisplayedImageDataGroup(int i, List<DisplayedImageInfo> list, int i2) {
        super(i);
        if (list == null) {
            throw new IllegalArgumentException("imageInfos cannot be null");
        }
        this.displayedImageTagToUse = i2;
        this.imageInfos = new ArrayList(list);
        checkTypesConsistentWithTag();
    }

    public DisplayedImageDataGroup(int i, InputStream inputStream) throws IOException {
        super(i, inputStream);
        if (this.imageInfos == null) {
            this.imageInfos = new ArrayList();
        }
        checkTypesConsistentWithTag();
    }

    /* access modifiers changed from: protected */
    public void readContent(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        int readTag = tLVInputStream.readTag();
        if (readTag != 2) {
            throw new IllegalArgumentException("Expected tag 0x02 in displayed image structure, found " + Integer.toHexString(readTag));
        } else if (tLVInputStream.readLength() != 1) {
            throw new IllegalArgumentException("DISPLAYED_IMAGE_COUNT should have length 1");
        } else {
            byte b = tLVInputStream.readValue()[0] & 255;
            for (int i = 0; i < b; i++) {
                DisplayedImageInfo displayedImageInfo = new DisplayedImageInfo(tLVInputStream);
                if (i == 0) {
                    this.displayedImageTagToUse = displayedImageInfo.getDisplayedImageTag();
                } else if (displayedImageInfo.getDisplayedImageTag() != this.displayedImageTagToUse) {
                    throw new IOException("Found images with different displayed image tags inside displayed image datagroup");
                }
                add(displayedImageInfo);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void writeContent(OutputStream outputStream) throws IOException {
        TLVOutputStream tLVOutputStream = outputStream instanceof TLVOutputStream ? (TLVOutputStream) outputStream : new TLVOutputStream(outputStream);
        tLVOutputStream.writeTag(2);
        tLVOutputStream.writeValue(new byte[]{(byte) this.imageInfos.size()});
        for (DisplayedImageInfo writeObject : this.imageInfos) {
            writeObject.writeObject(tLVOutputStream);
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getClass().getSimpleName());
        stringBuffer.append(" [");
        if (this.imageInfos == null) {
            throw new IllegalStateException("imageInfos cannot be null");
        }
        boolean z = true;
        for (DisplayedImageInfo displayedImageInfo : this.imageInfos) {
            if (z) {
                z = false;
            } else {
                stringBuffer.append(", ");
            }
            stringBuffer.append(displayedImageInfo.toString());
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public int hashCode() {
        return (this.imageInfos == null ? 1 : this.imageInfos.hashCode()) + 1337 + 31337;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        DisplayedImageDataGroup displayedImageDataGroup = (DisplayedImageDataGroup) obj;
        if (this.imageInfos == displayedImageDataGroup.imageInfos || (this.imageInfos != null && this.imageInfos.equals(displayedImageDataGroup.imageInfos))) {
            return true;
        }
        return false;
    }

    public List<DisplayedImageInfo> getImages() {
        return new ArrayList(this.imageInfos);
    }

    private void add(DisplayedImageInfo displayedImageInfo) {
        if (this.imageInfos == null) {
            this.imageInfos = new ArrayList();
        }
        this.imageInfos.add(displayedImageInfo);
    }

    private void checkTypesConsistentWithTag() {
        for (DisplayedImageInfo type : this.imageInfos) {
            switch (type.getType()) {
                case 0:
                    if (this.displayedImageTagToUse == 24384) {
                        break;
                    } else {
                        throw new IllegalArgumentException("'Signature or usual mark' image cannot be part of a 'Portrait' displayed image datagroup");
                    }
                case 1:
                    if (this.displayedImageTagToUse == 24387) {
                        break;
                    } else {
                        throw new IllegalArgumentException("'Portrait' image cannot be part of a 'Signature or usual mark' displayed image datagroup");
                    }
            }
        }
    }
}
