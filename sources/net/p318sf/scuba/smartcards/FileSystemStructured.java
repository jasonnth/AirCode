package net.p318sf.scuba.smartcards;

/* renamed from: net.sf.scuba.smartcards.FileSystemStructured */
public interface FileSystemStructured {
    FileInfo[] getSelectedPath() throws CardServiceException;

    byte[] readBinary(int i, int i2) throws CardServiceException;

    void selectFile(short s) throws CardServiceException;
}
