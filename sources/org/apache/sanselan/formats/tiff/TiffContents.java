package org.apache.sanselan.formats.tiff;

import java.util.ArrayList;

public class TiffContents {
    public final ArrayList directories;
    public final TiffHeader header;

    public TiffContents(TiffHeader tiffHeader, ArrayList directories2) {
        this.header = tiffHeader;
        this.directories = directories2;
    }
}
