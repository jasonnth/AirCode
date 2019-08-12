package org.apache.sanselan.formats.tiff;

import com.facebook.internal.AnalyticsEvents;
import java.util.ArrayList;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.formats.tiff.constants.TagInfo;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;

public class TiffDirectory extends TiffElement implements TiffConstants {
    public final ArrayList entries;
    private JpegImageData jpegImageData = null;
    public final int nextDirectoryOffset;
    public final int type;

    public final class ImageDataElement extends TiffElement {
        public ImageDataElement(int offset, int length) {
            super(offset, length);
        }
    }

    public static final String description(int type2) {
        switch (type2) {
            case -4:
                return "Interoperability";
            case -3:
                return "Gps";
            case -2:
                return "Exif";
            case -1:
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            case 0:
                return "Root";
            case 1:
                return "Sub";
            case 2:
                return "Thumbnail";
            default:
                return "Bad Type";
        }
    }

    public TiffDirectory(int type2, ArrayList entries2, int offset, int nextDirectoryOffset2) {
        super(offset, (entries2.size() * 12) + 2 + 4);
        this.type = type2;
        this.entries = entries2;
        this.nextDirectoryOffset = nextDirectoryOffset2;
    }

    public ArrayList getDirectoryEntrys() {
        return new ArrayList(this.entries);
    }

    public boolean hasJpegImageData() throws ImageReadException {
        if (findField(TIFF_TAG_JPEG_INTERCHANGE_FORMAT) != null) {
            return true;
        }
        return false;
    }

    public TiffField findField(TagInfo tag) throws ImageReadException {
        return findField(tag, false);
    }

    public TiffField findField(TagInfo tag, boolean failIfMissing) throws ImageReadException {
        if (this.entries == null) {
            return null;
        }
        for (int i = 0; i < this.entries.size(); i++) {
            TiffField field = (TiffField) this.entries.get(i);
            if (field.tag == tag.tag) {
                return field;
            }
        }
        if (!failIfMissing) {
            return null;
        }
        throw new ImageReadException("Missing expected field: " + tag.getDescription());
    }

    public ImageDataElement getJpegRawImageDataElement() throws ImageReadException {
        TiffField jpegInterchangeFormat = findField(TIFF_TAG_JPEG_INTERCHANGE_FORMAT);
        TiffField jpegInterchangeFormatLength = findField(TIFF_TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
        if (jpegInterchangeFormat != null && jpegInterchangeFormatLength != null) {
            return new ImageDataElement(jpegInterchangeFormat.getIntArrayValue()[0], jpegInterchangeFormatLength.getIntArrayValue()[0]);
        }
        throw new ImageReadException("Couldn't find image data.");
    }

    public void setJpegImageData(JpegImageData value) {
        this.jpegImageData = value;
    }

    public JpegImageData getJpegImageData() {
        return this.jpegImageData;
    }
}
