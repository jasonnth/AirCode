package org.jmrtd.lds;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.spongycastle.asn1.eac.EACTags;

public class DG5File extends DisplayedImageDataGroup {
    private static final long serialVersionUID = 923840683207218244L;

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

    public DG5File(List<DisplayedImageInfo> list) {
        super(101, list, EACTags.CARDHOLDER_PORTRAIT_IMAGE);
    }

    public DG5File(InputStream inputStream) throws IOException {
        super(101, inputStream);
    }
}
