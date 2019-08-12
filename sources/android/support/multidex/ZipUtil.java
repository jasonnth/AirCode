package android.support.multidex;

import android.support.p000v4.media.session.PlaybackStateCompat;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.CRC32;
import java.util.zip.ZipException;

final class ZipUtil {

    static class CentralDirectory {
        long offset;
        long size;

        CentralDirectory() {
        }
    }

    static long getZipCrc(File apk) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(apk, "r");
        try {
            return computeCrcOfCentralDir(raf, findCentralDirectory(raf));
        } finally {
            raf.close();
        }
    }

    static CentralDirectory findCentralDirectory(RandomAccessFile raf) throws IOException, ZipException {
        long scanOffset = raf.length() - 22;
        if (scanOffset < 0) {
            throw new ZipException("File too short to be a zip file: " + raf.length());
        }
        long stopOffset = scanOffset - PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        if (stopOffset < 0) {
            stopOffset = 0;
        }
        int endSig = Integer.reverseBytes(101010256);
        do {
            raf.seek(scanOffset);
            if (raf.readInt() == endSig) {
                raf.skipBytes(2);
                raf.skipBytes(2);
                raf.skipBytes(2);
                raf.skipBytes(2);
                CentralDirectory dir = new CentralDirectory();
                dir.size = ((long) Integer.reverseBytes(raf.readInt())) & 4294967295L;
                dir.offset = ((long) Integer.reverseBytes(raf.readInt())) & 4294967295L;
                return dir;
            }
            scanOffset--;
        } while (scanOffset >= stopOffset);
        throw new ZipException("End Of Central Directory signature not found");
    }

    static long computeCrcOfCentralDir(RandomAccessFile raf, CentralDirectory dir) throws IOException {
        CRC32 crc = new CRC32();
        long stillToRead = dir.size;
        raf.seek(dir.offset);
        byte[] buffer = new byte[16384];
        int length = raf.read(buffer, 0, (int) Math.min(PlaybackStateCompat.ACTION_PREPARE, stillToRead));
        while (length != -1) {
            crc.update(buffer, 0, length);
            stillToRead -= (long) length;
            if (stillToRead == 0) {
                break;
            }
            length = raf.read(buffer, 0, (int) Math.min(PlaybackStateCompat.ACTION_PREPARE, stillToRead));
        }
        return crc.getValue();
    }
}