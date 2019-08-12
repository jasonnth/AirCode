package org.apache.sanselan.formats.tiff.write;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.common.BinaryOutputStream;
import org.apache.sanselan.formats.tiff.JpegImageData;
import org.apache.sanselan.formats.tiff.TiffDirectory;
import org.apache.sanselan.formats.tiff.constants.TagInfo;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldType;
import org.apache.sanselan.formats.tiff.write.TiffOutputItem.Value;

public final class TiffOutputDirectory extends TiffOutputItem implements TiffConstants {
    private final ArrayList fields = new ArrayList();
    private JpegImageData jpegImageData = null;
    private TiffOutputDirectory nextDirectory = null;
    public final int type;

    public void setNextDirectory(TiffOutputDirectory nextDirectory2) {
        this.nextDirectory = nextDirectory2;
    }

    public TiffOutputDirectory(int type2) {
        this.type = type2;
    }

    public void add(TiffOutputField field) {
        this.fields.add(field);
    }

    public ArrayList getFields() {
        return new ArrayList(this.fields);
    }

    public TiffOutputField findField(TagInfo tagInfo) {
        return findField(tagInfo.tag);
    }

    public TiffOutputField findField(int tag) {
        for (int i = 0; i < this.fields.size(); i++) {
            TiffOutputField field = (TiffOutputField) this.fields.get(i);
            if (field.tag == tag) {
                return field;
            }
        }
        return null;
    }

    public void sortFields() {
        Collections.sort(this.fields, new Comparator() {
            public int compare(Object o1, Object o2) {
                TiffOutputField e1 = (TiffOutputField) o1;
                TiffOutputField e2 = (TiffOutputField) o2;
                if (e1.tag != e2.tag) {
                    return e1.tag - e2.tag;
                }
                return e1.getSortHint() - e2.getSortHint();
            }
        });
    }

    public String description() {
        return TiffDirectory.description(this.type);
    }

    public void writeItem(BinaryOutputStream bos) throws IOException, ImageWriteException {
        bos.write2Bytes(this.fields.size());
        for (int i = 0; i < this.fields.size(); i++) {
            ((TiffOutputField) this.fields.get(i)).writeField(bos);
        }
        int nextDirectoryOffset = 0;
        if (this.nextDirectory != null) {
            nextDirectoryOffset = this.nextDirectory.getOffset();
        }
        if (nextDirectoryOffset == -1) {
            bos.write4Bytes(0);
        } else {
            bos.write4Bytes(nextDirectoryOffset);
        }
    }

    public int getItemLength() {
        return (this.fields.size() * 12) + 2 + 4;
    }

    private void removeFieldIfPresent(TagInfo tagInfo) {
        TiffOutputField field = findField(tagInfo);
        if (field != null) {
            this.fields.remove(field);
        }
    }

    /* access modifiers changed from: protected */
    public List getOutputItems(TiffOutputSummary outputSummary) throws ImageWriteException {
        removeFieldIfPresent(TIFF_TAG_JPEG_INTERCHANGE_FORMAT);
        removeFieldIfPresent(TIFF_TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
        TiffOutputField jpegOffsetField = null;
        if (this.jpegImageData != null) {
            jpegOffsetField = new TiffOutputField(TIFF_TAG_JPEG_INTERCHANGE_FORMAT, FIELD_TYPE_LONG, 1, FieldType.getStubLocalValue());
            add(jpegOffsetField);
            add(new TiffOutputField(TIFF_TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, FIELD_TYPE_LONG, 1, FIELD_TYPE_LONG.writeData(new int[]{this.jpegImageData.length}, outputSummary.byteOrder)));
        }
        removeFieldIfPresent(TIFF_TAG_STRIP_OFFSETS);
        removeFieldIfPresent(TIFF_TAG_STRIP_BYTE_COUNTS);
        removeFieldIfPresent(TIFF_TAG_TILE_OFFSETS);
        removeFieldIfPresent(TIFF_TAG_TILE_BYTE_COUNTS);
        List result = new ArrayList();
        result.add(this);
        sortFields();
        for (int i = 0; i < this.fields.size(); i++) {
            TiffOutputField field = (TiffOutputField) this.fields.get(i);
            if (!field.isLocalValue()) {
                result.add(field.getSeperateValue());
            }
        }
        if (0 != 0) {
            for (TiffOutputItem add : null.outputItems) {
                result.add(add);
            }
            outputSummary.addTiffImageData(null);
        }
        if (this.jpegImageData != null) {
            TiffOutputItem item = new Value("JPEG image data", this.jpegImageData.data);
            result.add(item);
            outputSummary.add(item, jpegOffsetField);
        }
        return result;
    }
}
