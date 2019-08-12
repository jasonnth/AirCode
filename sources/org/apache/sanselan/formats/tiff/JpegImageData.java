package org.apache.sanselan.formats.tiff;

import org.apache.sanselan.formats.tiff.TiffElement.DataElement;

public class JpegImageData extends DataElement {
    public JpegImageData(int offset, int length, byte[] data) {
        super(offset, length, data);
    }
}
