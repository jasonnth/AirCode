package org.jmrtd.lds;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.spongycastle.asn1.eac.EACTags;

public class DG7File extends DisplayedImageDataGroup {
    private static final long serialVersionUID = 7189545112850471359L;

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ List getImages() {
        return super.getImages();
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public DG7File(List<DisplayedImageInfo> list) {
        super(103, list, EACTags.CARDHOLDER_HANDWRITTEN_SIGNATURE);
    }

    public DG7File(InputStream inputStream) throws IOException {
        super(103, inputStream);
    }
}
