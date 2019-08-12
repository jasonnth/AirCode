package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import android.util.Log;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public abstract class UnpackingSoSource extends DirectorySoSource {
    private static final String DEPS_FILE_NAME = "dso_deps";
    private static final String LOCK_FILE_NAME = "dso_lock";
    private static final String MANIFEST_FILE_NAME = "dso_manifest";
    private static final byte MANIFEST_VERSION = 1;
    private static final byte STATE_CLEAN = 1;
    private static final byte STATE_DIRTY = 0;
    private static final String STATE_FILE_NAME = "dso_state";
    private static final String TAG = "fb-UnpackingSoSource";
    protected final Context mContext;

    public static class Dso {
        public final String hash;
        public final String name;

        public Dso(String name2, String hash2) {
            this.name = name2;
            this.hash = hash2;
        }
    }

    public static final class DsoManifest {
        public final Dso[] dsos;

        public DsoManifest(Dso[] dsos2) {
            this.dsos = dsos2;
        }

        static final DsoManifest read(DataInput xdi) throws IOException {
            if (xdi.readByte() != 1) {
                throw new RuntimeException("wrong dso manifest version");
            }
            int nrDso = xdi.readInt();
            if (nrDso < 0) {
                throw new RuntimeException("illegal number of shared libraries");
            }
            Dso[] dsos2 = new Dso[nrDso];
            for (int i = 0; i < nrDso; i++) {
                dsos2[i] = new Dso(xdi.readUTF(), xdi.readUTF());
            }
            return new DsoManifest(dsos2);
        }

        public final void write(DataOutput xdo) throws IOException {
            xdo.writeByte(1);
            xdo.writeInt(this.dsos.length);
            for (int i = 0; i < this.dsos.length; i++) {
                xdo.writeUTF(this.dsos[i].name);
                xdo.writeUTF(this.dsos[i].hash);
            }
        }
    }

    protected static final class InputDso implements Closeable {
        public final InputStream content;
        public final Dso dso;

        public InputDso(Dso dso2, InputStream content2) {
            this.dso = dso2;
            this.content = content2;
        }

        public void close() throws IOException {
            this.content.close();
        }
    }

    protected static abstract class InputDsoIterator implements Closeable {
        public abstract boolean hasNext();

        public abstract InputDso next() throws IOException;

        protected InputDsoIterator() {
        }

        public void close() throws IOException {
        }
    }

    protected static abstract class Unpacker implements Closeable {
        /* access modifiers changed from: protected */
        public abstract DsoManifest getDsoManifest() throws IOException;

        /* access modifiers changed from: protected */
        public abstract InputDsoIterator openDsoIterator() throws IOException;

        protected Unpacker() {
        }

        public void close() throws IOException {
        }
    }

    /* access modifiers changed from: protected */
    public abstract Unpacker makeUnpacker() throws IOException;

    protected UnpackingSoSource(Context context, String name) {
        super(getSoStorePath(context, name), 1);
        this.mContext = context;
    }

    public static File getSoStorePath(Context context, String name) {
        return new File(context.getApplicationInfo().dataDir + "/" + name);
    }

    /* access modifiers changed from: private */
    public static void writeState(File stateFileName, byte state) throws IOException {
        RandomAccessFile stateFile = new RandomAccessFile(stateFileName, "rw");
        Throwable th = null;
        try {
            stateFile.seek(0);
            stateFile.write(state);
            stateFile.setLength(stateFile.getFilePointer());
            stateFile.getFD().sync();
            if (stateFile == null) {
                return;
            }
            if (th != null) {
                try {
                    stateFile.close();
                    return;
                } catch (Throwable x2) {
                    th.addSuppressed(x2);
                    return;
                }
            } else {
                stateFile.close();
                return;
            }
        } catch (Throwable th2) {
            Throwable th3 = th2;
            th = r2;
            th = th3;
        }
        if (stateFile != null) {
            if (th != null) {
                try {
                    stateFile.close();
                } catch (Throwable x22) {
                    th.addSuppressed(x22);
                }
            } else {
                stateFile.close();
            }
        }
        throw th;
        throw th;
    }

    private void deleteUnmentionedFiles(Dso[] dsos) throws IOException {
        String[] existingFiles = this.soDirectory.list();
        if (existingFiles == null) {
            throw new IOException("unable to list directory " + this.soDirectory);
        }
        for (String fileName : existingFiles) {
            if (!fileName.equals(STATE_FILE_NAME) && !fileName.equals(LOCK_FILE_NAME) && !fileName.equals(DEPS_FILE_NAME) && !fileName.equals(MANIFEST_FILE_NAME)) {
                boolean found = false;
                int j = 0;
                while (!found && j < dsos.length) {
                    if (dsos[j].name.equals(fileName)) {
                        found = true;
                    }
                    j++;
                }
                if (!found) {
                    File fileNameToDelete = new File(this.soDirectory, fileName);
                    Log.v(TAG, "deleting unaccounted-for file " + fileNameToDelete);
                    SysUtil.dumbDeleteRecursive(fileNameToDelete);
                }
            }
        }
    }

    private void extractDso(InputDso iDso, byte[] ioBuffer) throws IOException {
        RandomAccessFile dsoFile;
        Log.i(TAG, "extracting DSO " + iDso.dso.name);
        File dsoFileName = new File(this.soDirectory, iDso.dso.name);
        try {
            dsoFile = new RandomAccessFile(dsoFileName, "rw");
        } catch (IOException ex) {
            Log.w(TAG, "error overwriting " + dsoFileName + " trying to delete and start over", ex);
            dsoFileName.delete();
            dsoFile = new RandomAccessFile(dsoFileName, "rw");
        }
        try {
            int sizeHint = iDso.content.available();
            if (sizeHint > 1) {
                SysUtil.fallocateIfSupported(dsoFile.getFD(), (long) sizeHint);
            }
            SysUtil.copyBytes(dsoFile, iDso.content, Integer.MAX_VALUE, ioBuffer);
            dsoFile.setLength(dsoFile.getFilePointer());
            if (!dsoFileName.setExecutable(true, false)) {
                throw new IOException("cannot make file executable: " + dsoFileName);
            }
        } finally {
            dsoFile.close();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b2, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ba, code lost:
        if (r12 != null) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00c4, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00c5, code lost:
        r12 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00d5, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00d6, code lost:
        r12.addSuppressed(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00ef, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x00f0, code lost:
        r12.addSuppressed(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00f4, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x00f8, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x00f9, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0060 A[Catch:{ Throwable -> 0x00b2, all -> 0x00c4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c4 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0044 A[SYNTHETIC, Splitter:B:6:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:95:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void regenerate(byte r17, com.facebook.soloader.UnpackingSoSource.DsoManifest r18, com.facebook.soloader.UnpackingSoSource.InputDsoIterator r19) throws java.io.IOException {
        /*
            r16 = this;
            java.lang.String r11 = "fb-UnpackingSoSource"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "regenerating DSO store "
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.Class r13 = r16.getClass()
            java.lang.String r13 = r13.getName()
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            android.util.Log.v(r11, r12)
            java.io.File r8 = new java.io.File
            r0 = r16
            java.io.File r11 = r0.soDirectory
            java.lang.String r12 = "dso_manifest"
            r8.<init>(r11, r12)
            java.io.RandomAccessFile r7 = new java.io.RandomAccessFile
            java.lang.String r11 = "rw"
            r7.<init>(r8, r11)
            r13 = 0
            r2 = 0
            r11 = 1
            r0 = r17
            if (r0 != r11) goto L_0x009c
            com.facebook.soloader.UnpackingSoSource$DsoManifest r2 = com.facebook.soloader.UnpackingSoSource.DsoManifest.read(r7)     // Catch:{ Exception -> 0x0092 }
            r3 = r2
        L_0x0042:
            if (r3 != 0) goto L_0x00fd
            com.facebook.soloader.UnpackingSoSource$DsoManifest r2 = new com.facebook.soloader.UnpackingSoSource$DsoManifest     // Catch:{ Throwable -> 0x00f8, all -> 0x00c4 }
            r11 = 0
            com.facebook.soloader.UnpackingSoSource$Dso[] r11 = new com.facebook.soloader.UnpackingSoSource.Dso[r11]     // Catch:{ Throwable -> 0x00f8, all -> 0x00c4 }
            r2.<init>(r11)     // Catch:{ Throwable -> 0x00f8, all -> 0x00c4 }
        L_0x004c:
            r0 = r18
            com.facebook.soloader.UnpackingSoSource$Dso[] r11 = r0.dsos     // Catch:{ Throwable -> 0x00b2, all -> 0x00c4 }
            r0 = r16
            r0.deleteUnmentionedFiles(r11)     // Catch:{ Throwable -> 0x00b2, all -> 0x00c4 }
            r11 = 32768(0x8000, float:4.5918E-41)
            byte[] r6 = new byte[r11]     // Catch:{ Throwable -> 0x00b2, all -> 0x00c4 }
        L_0x005a:
            boolean r11 = r19.hasNext()     // Catch:{ Throwable -> 0x00b2, all -> 0x00c4 }
            if (r11 == 0) goto L_0x00de
            com.facebook.soloader.UnpackingSoSource$InputDso r5 = r19.next()     // Catch:{ Throwable -> 0x00b2, all -> 0x00c4 }
            r12 = 0
            r9 = 1
            r4 = 0
        L_0x0067:
            if (r9 == 0) goto L_0x009e
            com.facebook.soloader.UnpackingSoSource$Dso[] r11 = r2.dsos     // Catch:{ Throwable -> 0x00c7, all -> 0x00fb }
            int r11 = r11.length     // Catch:{ Throwable -> 0x00c7, all -> 0x00fb }
            if (r4 >= r11) goto L_0x009e
            com.facebook.soloader.UnpackingSoSource$Dso[] r11 = r2.dsos     // Catch:{ Throwable -> 0x00c7, all -> 0x00fb }
            r11 = r11[r4]     // Catch:{ Throwable -> 0x00c7, all -> 0x00fb }
            java.lang.String r11 = r11.name     // Catch:{ Throwable -> 0x00c7, all -> 0x00fb }
            com.facebook.soloader.UnpackingSoSource$Dso r14 = r5.dso     // Catch:{ Throwable -> 0x00c7, all -> 0x00fb }
            java.lang.String r14 = r14.name     // Catch:{ Throwable -> 0x00c7, all -> 0x00fb }
            boolean r11 = r11.equals(r14)     // Catch:{ Throwable -> 0x00c7, all -> 0x00fb }
            if (r11 == 0) goto L_0x008f
            com.facebook.soloader.UnpackingSoSource$Dso[] r11 = r2.dsos     // Catch:{ Throwable -> 0x00c7, all -> 0x00fb }
            r11 = r11[r4]     // Catch:{ Throwable -> 0x00c7, all -> 0x00fb }
            java.lang.String r11 = r11.hash     // Catch:{ Throwable -> 0x00c7, all -> 0x00fb }
            com.facebook.soloader.UnpackingSoSource$Dso r14 = r5.dso     // Catch:{ Throwable -> 0x00c7, all -> 0x00fb }
            java.lang.String r14 = r14.hash     // Catch:{ Throwable -> 0x00c7, all -> 0x00fb }
            boolean r11 = r11.equals(r14)     // Catch:{ Throwable -> 0x00c7, all -> 0x00fb }
            if (r11 == 0) goto L_0x008f
            r9 = 0
        L_0x008f:
            int r4 = r4 + 1
            goto L_0x0067
        L_0x0092:
            r1 = move-exception
            java.lang.String r11 = "fb-UnpackingSoSource"
            java.lang.String r12 = "error reading existing DSO manifest"
            android.util.Log.i(r11, r12, r1)     // Catch:{ Throwable -> 0x00b2, all -> 0x00c4 }
        L_0x009c:
            r3 = r2
            goto L_0x0042
        L_0x009e:
            if (r9 == 0) goto L_0x00a5
            r0 = r16
            r0.extractDso(r5, r6)     // Catch:{ Throwable -> 0x00c7, all -> 0x00fb }
        L_0x00a5:
            if (r5 == 0) goto L_0x005a
            if (r12 == 0) goto L_0x00c0
            r5.close()     // Catch:{ Throwable -> 0x00ad, all -> 0x00c4 }
            goto L_0x005a
        L_0x00ad:
            r10 = move-exception
            r12.addSuppressed(r10)     // Catch:{ Throwable -> 0x00b2, all -> 0x00c4 }
            goto L_0x005a
        L_0x00b2:
            r11 = move-exception
        L_0x00b3:
            throw r11     // Catch:{ all -> 0x00b4 }
        L_0x00b4:
            r12 = move-exception
            r15 = r12
            r12 = r11
            r11 = r15
        L_0x00b8:
            if (r7 == 0) goto L_0x00bf
            if (r12 == 0) goto L_0x00f4
            r7.close()     // Catch:{ Throwable -> 0x00ef }
        L_0x00bf:
            throw r11
        L_0x00c0:
            r5.close()     // Catch:{ Throwable -> 0x00b2, all -> 0x00c4 }
            goto L_0x005a
        L_0x00c4:
            r11 = move-exception
            r12 = r13
            goto L_0x00b8
        L_0x00c7:
            r11 = move-exception
            throw r11     // Catch:{ all -> 0x00c9 }
        L_0x00c9:
            r12 = move-exception
            r15 = r12
            r12 = r11
            r11 = r15
        L_0x00cd:
            if (r5 == 0) goto L_0x00d4
            if (r12 == 0) goto L_0x00da
            r5.close()     // Catch:{ Throwable -> 0x00d5, all -> 0x00c4 }
        L_0x00d4:
            throw r11     // Catch:{ Throwable -> 0x00b2, all -> 0x00c4 }
        L_0x00d5:
            r10 = move-exception
            r12.addSuppressed(r10)     // Catch:{ Throwable -> 0x00b2, all -> 0x00c4 }
            goto L_0x00d4
        L_0x00da:
            r5.close()     // Catch:{ Throwable -> 0x00b2, all -> 0x00c4 }
            goto L_0x00d4
        L_0x00de:
            if (r7 == 0) goto L_0x00e5
            if (r13 == 0) goto L_0x00eb
            r7.close()     // Catch:{ Throwable -> 0x00e6 }
        L_0x00e5:
            return
        L_0x00e6:
            r10 = move-exception
            r13.addSuppressed(r10)
            goto L_0x00e5
        L_0x00eb:
            r7.close()
            goto L_0x00e5
        L_0x00ef:
            r10 = move-exception
            r12.addSuppressed(r10)
            goto L_0x00bf
        L_0x00f4:
            r7.close()
            goto L_0x00bf
        L_0x00f8:
            r11 = move-exception
            r2 = r3
            goto L_0x00b3
        L_0x00fb:
            r11 = move-exception
            goto L_0x00cd
        L_0x00fd:
            r2 = r3
            goto L_0x004c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.UnpackingSoSource.regenerate(byte, com.facebook.soloader.UnpackingSoSource$DsoManifest, com.facebook.soloader.UnpackingSoSource$InputDsoIterator):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0147, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0148, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0150, code lost:
        r17 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0151, code lost:
        r5.addSuppressed(r17);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0157, code lost:
        r16.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00fb, code lost:
        r17 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        r5.addSuppressed(r17);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x010c, code lost:
        if (r5 != null) goto L_0x010e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        r16.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0114, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0115, code lost:
        r22 = r5;
        r5 = r3;
        r3 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0126, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0127, code lost:
        r5 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x012b, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x012c, code lost:
        if (r13 != null) goto L_0x012e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x012e, code lost:
        if (r5 != null) goto L_0x0130;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:?, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:?, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0134, code lost:
        r17 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0135, code lost:
        r5.addSuppressed(r17);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x013b, code lost:
        r13.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0147 A[Catch:{ all -> 0x0147, all -> 0x0114 }, ExcHandler: all (th java.lang.Throwable), Splitter:B:11:0x0068] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0126 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:21:0x00a8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean refreshLocked(com.facebook.soloader.FileLocker r24, int r25, byte[] r26) throws java.io.IOException {
        /*
            r23 = this;
            java.io.File r7 = new java.io.File
            r0 = r23
            java.io.File r3 = r0.soDirectory
            java.lang.String r5 = "dso_state"
            r7.<init>(r3, r5)
            java.io.RandomAccessFile r15 = new java.io.RandomAccessFile
            java.lang.String r3 = "rw"
            r15.<init>(r7, r3)
            r5 = 0
            byte r14 = r15.readByte()     // Catch:{ EOFException -> 0x00cf, Throwable -> 0x00e0, all -> 0x01b0 }
            r3 = 1
            if (r14 == r3) goto L_0x004a
            java.lang.String r3 = "fb-UnpackingSoSource"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ EOFException -> 0x00cf, Throwable -> 0x00e0, all -> 0x01b0 }
            r8.<init>()     // Catch:{ EOFException -> 0x00cf, Throwable -> 0x00e0, all -> 0x01b0 }
            java.lang.String r18 = "dso store "
            r0 = r18
            java.lang.StringBuilder r8 = r8.append(r0)     // Catch:{ EOFException -> 0x00cf, Throwable -> 0x00e0, all -> 0x01b0 }
            r0 = r23
            java.io.File r0 = r0.soDirectory     // Catch:{ EOFException -> 0x00cf, Throwable -> 0x00e0, all -> 0x01b0 }
            r18 = r0
            r0 = r18
            java.lang.StringBuilder r8 = r8.append(r0)     // Catch:{ EOFException -> 0x00cf, Throwable -> 0x00e0, all -> 0x01b0 }
            java.lang.String r18 = " regeneration interrupted: wiping clean"
            r0 = r18
            java.lang.StringBuilder r8 = r8.append(r0)     // Catch:{ EOFException -> 0x00cf, Throwable -> 0x00e0, all -> 0x01b0 }
            java.lang.String r8 = r8.toString()     // Catch:{ EOFException -> 0x00cf, Throwable -> 0x00e0, all -> 0x01b0 }
            android.util.Log.v(r3, r8)     // Catch:{ EOFException -> 0x00cf, Throwable -> 0x00e0, all -> 0x01b0 }
            r14 = 0
        L_0x004a:
            if (r15 == 0) goto L_0x0051
            if (r5 == 0) goto L_0x00db
            r15.close()     // Catch:{ Throwable -> 0x00d3 }
        L_0x0051:
            java.io.File r4 = new java.io.File
            r0 = r23
            java.io.File r3 = r0.soDirectory
            java.lang.String r5 = "dso_deps"
            r4.<init>(r3, r5)
            r10 = 0
            java.io.RandomAccessFile r9 = new java.io.RandomAccessFile
            java.lang.String r3 = "rw"
            r9.<init>(r4, r3)
            r18 = 0
            long r20 = r9.length()     // Catch:{ Throwable -> 0x0112, all -> 0x0147 }
            r0 = r20
            int r3 = (int) r0     // Catch:{ Throwable -> 0x0112, all -> 0x0147 }
            byte[] r12 = new byte[r3]     // Catch:{ Throwable -> 0x0112, all -> 0x0147 }
            int r3 = r9.read(r12)     // Catch:{ Throwable -> 0x0112, all -> 0x0147 }
            int r5 = r12.length     // Catch:{ Throwable -> 0x0112, all -> 0x0147 }
            if (r3 == r5) goto L_0x0082
            java.lang.String r3 = "fb-UnpackingSoSource"
            java.lang.String r5 = "short read of so store deps file: marking unclean"
            android.util.Log.v(r3, r5)     // Catch:{ Throwable -> 0x0112, all -> 0x0147 }
            r14 = 0
        L_0x0082:
            r0 = r26
            boolean r3 = java.util.Arrays.equals(r12, r0)     // Catch:{ Throwable -> 0x0112, all -> 0x0147 }
            if (r3 != 0) goto L_0x0094
            java.lang.String r3 = "fb-UnpackingSoSource"
            java.lang.String r5 = "deps mismatch on deps store: regenerating"
            android.util.Log.v(r3, r5)     // Catch:{ Throwable -> 0x0112, all -> 0x0147 }
            r14 = 0
        L_0x0094:
            if (r14 != 0) goto L_0x00c4
            java.lang.String r3 = "fb-UnpackingSoSource"
            java.lang.String r5 = "so store dirty: regenerating"
            android.util.Log.v(r3, r5)     // Catch:{ Throwable -> 0x0112, all -> 0x0147 }
            r3 = 0
            writeState(r7, r3)     // Catch:{ Throwable -> 0x0112, all -> 0x0147 }
            com.facebook.soloader.UnpackingSoSource$Unpacker r16 = r23.makeUnpacker()     // Catch:{ Throwable -> 0x0112, all -> 0x0147 }
            r8 = 0
            com.facebook.soloader.UnpackingSoSource$DsoManifest r10 = r16.getDsoManifest()     // Catch:{ Throwable -> 0x0102, all -> 0x0126 }
            com.facebook.soloader.UnpackingSoSource$InputDsoIterator r13 = r16.openDsoIterator()     // Catch:{ Throwable -> 0x0102, all -> 0x0126 }
            r5 = 0
            r0 = r23
            r0.regenerate(r14, r10, r13)     // Catch:{ Throwable -> 0x0129 }
            if (r13 == 0) goto L_0x00bd
            if (r5 == 0) goto L_0x0122
            r13.close()     // Catch:{ Throwable -> 0x00fb, all -> 0x0126 }
        L_0x00bd:
            if (r16 == 0) goto L_0x00c4
            if (r8 == 0) goto L_0x014b
            r16.close()     // Catch:{ Throwable -> 0x013f, all -> 0x0147 }
        L_0x00c4:
            if (r9 == 0) goto L_0x00cb
            if (r18 == 0) goto L_0x0165
            r9.close()     // Catch:{ Throwable -> 0x015b }
        L_0x00cb:
            if (r10 != 0) goto L_0x0175
            r3 = 0
        L_0x00ce:
            return r3
        L_0x00cf:
            r11 = move-exception
            r14 = 0
            goto L_0x004a
        L_0x00d3:
            r17 = move-exception
            r0 = r17
            r5.addSuppressed(r0)
            goto L_0x0051
        L_0x00db:
            r15.close()
            goto L_0x0051
        L_0x00e0:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x00e2 }
        L_0x00e2:
            r5 = move-exception
            r22 = r5
            r5 = r3
            r3 = r22
        L_0x00e8:
            if (r15 == 0) goto L_0x00ef
            if (r5 == 0) goto L_0x00f7
            r15.close()     // Catch:{ Throwable -> 0x00f0 }
        L_0x00ef:
            throw r3
        L_0x00f0:
            r17 = move-exception
            r0 = r17
            r5.addSuppressed(r0)
            goto L_0x00ef
        L_0x00f7:
            r15.close()
            goto L_0x00ef
        L_0x00fb:
            r17 = move-exception
            r0 = r17
            r5.addSuppressed(r0)     // Catch:{ Throwable -> 0x0102, all -> 0x0126 }
            goto L_0x00bd
        L_0x0102:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0104 }
        L_0x0104:
            r5 = move-exception
            r22 = r5
            r5 = r3
            r3 = r22
        L_0x010a:
            if (r16 == 0) goto L_0x0111
            if (r5 == 0) goto L_0x0157
            r16.close()     // Catch:{ Throwable -> 0x0150, all -> 0x0147 }
        L_0x0111:
            throw r3     // Catch:{ Throwable -> 0x0112, all -> 0x0147 }
        L_0x0112:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0114 }
        L_0x0114:
            r5 = move-exception
            r22 = r5
            r5 = r3
            r3 = r22
        L_0x011a:
            if (r9 == 0) goto L_0x0121
            if (r5 == 0) goto L_0x0171
            r9.close()     // Catch:{ Throwable -> 0x016a }
        L_0x0121:
            throw r3
        L_0x0122:
            r13.close()     // Catch:{ Throwable -> 0x0102, all -> 0x0126 }
            goto L_0x00bd
        L_0x0126:
            r3 = move-exception
            r5 = r8
            goto L_0x010a
        L_0x0129:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x012b }
        L_0x012b:
            r3 = move-exception
            if (r13 == 0) goto L_0x0133
            if (r5 == 0) goto L_0x013b
            r13.close()     // Catch:{ Throwable -> 0x0134, all -> 0x0126 }
        L_0x0133:
            throw r3     // Catch:{ Throwable -> 0x0102, all -> 0x0126 }
        L_0x0134:
            r17 = move-exception
            r0 = r17
            r5.addSuppressed(r0)     // Catch:{ Throwable -> 0x0102, all -> 0x0126 }
            goto L_0x0133
        L_0x013b:
            r13.close()     // Catch:{ Throwable -> 0x0102, all -> 0x0126 }
            goto L_0x0133
        L_0x013f:
            r17 = move-exception
            r0 = r17
            r8.addSuppressed(r0)     // Catch:{ Throwable -> 0x0112, all -> 0x0147 }
            goto L_0x00c4
        L_0x0147:
            r3 = move-exception
            r5 = r18
            goto L_0x011a
        L_0x014b:
            r16.close()     // Catch:{ Throwable -> 0x0112, all -> 0x0147 }
            goto L_0x00c4
        L_0x0150:
            r17 = move-exception
            r0 = r17
            r5.addSuppressed(r0)     // Catch:{ Throwable -> 0x0112, all -> 0x0147 }
            goto L_0x0111
        L_0x0157:
            r16.close()     // Catch:{ Throwable -> 0x0112, all -> 0x0147 }
            goto L_0x0111
        L_0x015b:
            r17 = move-exception
            r0 = r18
            r1 = r17
            r0.addSuppressed(r1)
            goto L_0x00cb
        L_0x0165:
            r9.close()
            goto L_0x00cb
        L_0x016a:
            r17 = move-exception
            r0 = r17
            r5.addSuppressed(r0)
            goto L_0x0121
        L_0x0171:
            r9.close()
            goto L_0x0121
        L_0x0175:
            r6 = r10
            com.facebook.soloader.UnpackingSoSource$1 r2 = new com.facebook.soloader.UnpackingSoSource$1
            r3 = r23
            r5 = r26
            r8 = r24
            r2.<init>(r4, r5, r6, r7, r8)
            r3 = r25 & 1
            if (r3 == 0) goto L_0x01ac
            java.lang.Thread r3 = new java.lang.Thread
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r8 = "SoSync:"
            java.lang.StringBuilder r5 = r5.append(r8)
            r0 = r23
            java.io.File r8 = r0.soDirectory
            java.lang.String r8 = r8.getName()
            java.lang.StringBuilder r5 = r5.append(r8)
            java.lang.String r5 = r5.toString()
            r3.<init>(r2, r5)
            r3.start()
        L_0x01a9:
            r3 = 1
            goto L_0x00ce
        L_0x01ac:
            r2.run()
            goto L_0x01a9
        L_0x01b0:
            r3 = move-exception
            goto L_0x00e8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.UnpackingSoSource.refreshLocked(com.facebook.soloader.FileLocker, int, byte[]):boolean");
    }

    /* access modifiers changed from: protected */
    public byte[] getDepsBlock() throws IOException {
        Parcel parcel = Parcel.obtain();
        Unpacker u = makeUnpacker();
        Throwable th = null;
        try {
            Dso[] dsos = u.getDsoManifest().dsos;
            parcel.writeByte(1);
            parcel.writeInt(dsos.length);
            for (int i = 0; i < dsos.length; i++) {
                parcel.writeString(dsos[i].name);
                parcel.writeString(dsos[i].hash);
            }
            if (u != null) {
                if (th != null) {
                    try {
                        u.close();
                    } catch (Throwable x2) {
                        th.addSuppressed(x2);
                    }
                } else {
                    u.close();
                }
            }
            byte[] depsBlock = parcel.marshall();
            parcel.recycle();
            return depsBlock;
        } catch (Throwable th2) {
            Throwable th3 = th2;
            th = r6;
            th = th3;
        }
        throw th;
        if (u != null) {
            if (th != null) {
                try {
                    u.close();
                } catch (Throwable x22) {
                    th.addSuppressed(x22);
                }
            } else {
                u.close();
            }
        }
        throw th;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public void prepare(int flags) throws IOException {
        SysUtil.mkdirOrThrow(this.soDirectory);
        FileLocker lock = FileLocker.lock(new File(this.soDirectory, LOCK_FILE_NAME));
        try {
            Log.v(TAG, "locked dso store " + this.soDirectory);
            if (refreshLocked(lock, flags, getDepsBlock())) {
                lock = null;
            } else {
                Log.i(TAG, "dso store is up-to-date: " + this.soDirectory);
            }
            if (lock != null) {
                Log.v(TAG, "releasing dso store lock for " + this.soDirectory);
                lock.close();
                return;
            }
            Log.v(TAG, "not releasing dso store lock for " + this.soDirectory + " (syncer thread started)");
        } catch (Throwable th) {
            if (lock != null) {
                Log.v(TAG, "releasing dso store lock for " + this.soDirectory);
                lock.close();
            } else {
                Log.v(TAG, "not releasing dso store lock for " + this.soDirectory + " (syncer thread started)");
            }
            throw th;
        }
    }
}
