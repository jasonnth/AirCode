package org.apache.sanselan.formats.tiff.write;

import java.util.ArrayList;
import java.util.List;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;

public final class TiffOutputSet implements TiffConstants {
    private static final String newline = System.getProperty("line.separator");
    public final int byteOrder;
    private final ArrayList directories;

    public TiffOutputSet() {
        this(73);
    }

    public TiffOutputSet(int byteOrder2) {
        this.directories = new ArrayList();
        this.byteOrder = byteOrder2;
    }

    /* access modifiers changed from: protected */
    public List getOutputItems(TiffOutputSummary outputSummary) throws ImageWriteException {
        List result = new ArrayList();
        for (int i = 0; i < this.directories.size(); i++) {
            result.addAll(((TiffOutputDirectory) this.directories.get(i)).getOutputItems(outputSummary));
        }
        return result;
    }

    public void addDirectory(TiffOutputDirectory directory) throws ImageWriteException {
        if (findDirectory(directory.type) != null) {
            throw new ImageWriteException("Output set already contains a directory of that type.");
        }
        this.directories.add(directory);
    }

    public List getDirectories() {
        return new ArrayList(this.directories);
    }

    public TiffOutputDirectory getRootDirectory() {
        return findDirectory(0);
    }

    public TiffOutputDirectory getOrCreateRootDirectory() throws ImageWriteException {
        TiffOutputDirectory result = findDirectory(0);
        return result != null ? result : addRootDirectory();
    }

    public TiffOutputDirectory getOrCreateExifDirectory() throws ImageWriteException {
        getOrCreateRootDirectory();
        TiffOutputDirectory result = findDirectory(-2);
        return result != null ? result : addExifDirectory();
    }

    public TiffOutputDirectory findDirectory(int directoryType) {
        for (int i = 0; i < this.directories.size(); i++) {
            TiffOutputDirectory directory = (TiffOutputDirectory) this.directories.get(i);
            if (directory.type == directoryType) {
                return directory;
            }
        }
        return null;
    }

    public TiffOutputDirectory addRootDirectory() throws ImageWriteException {
        TiffOutputDirectory result = new TiffOutputDirectory(0);
        addDirectory(result);
        return result;
    }

    public TiffOutputDirectory addExifDirectory() throws ImageWriteException {
        TiffOutputDirectory result = new TiffOutputDirectory(-2);
        addDirectory(result);
        return result;
    }

    public String toString() {
        return toString(null);
    }

    public String toString(String prefix) {
        if (prefix == null) {
            prefix = "";
        }
        StringBuffer result = new StringBuffer();
        result.append(prefix);
        result.append("TiffOutputSet {");
        result.append(newline);
        result.append(prefix);
        result.append("byteOrder: " + this.byteOrder);
        result.append(newline);
        for (int i = 0; i < this.directories.size(); i++) {
            TiffOutputDirectory directory = (TiffOutputDirectory) this.directories.get(i);
            result.append(prefix);
            result.append("\tdirectory " + i + ": " + directory.description() + " (" + directory.type + ")");
            result.append(newline);
            ArrayList fields = directory.getFields();
            for (int j = 0; j < fields.size(); j++) {
                TiffOutputField field = (TiffOutputField) fields.get(j);
                result.append(prefix);
                result.append("\t\tfield " + i + ": " + field.tagInfo);
                result.append(newline);
            }
        }
        result.append(prefix);
        result.append("}");
        result.append(newline);
        return result.toString();
    }
}
