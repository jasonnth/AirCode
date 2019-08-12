package org.apache.sanselan.formats.tiff.constants;

public interface AllTagConstants extends ExifTagConstants, GPSTagConstants, TiffTagConstants {
    public static final TagInfo[] ALL_TAGS = TagConstantsUtils.mergeTagLists(new TagInfo[][]{ALL_TIFF_TAGS, ALL_EXIF_TAGS, ALL_GPS_TAGS});
}
