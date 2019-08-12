package com.facebook.soloader;

import android.content.Context;
import com.facebook.soloader.UnpackingSoSource.Dso;
import com.facebook.soloader.UnpackingSoSource.DsoManifest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public final class ExoSoSource extends UnpackingSoSource {

    private final class ExoUnpacker extends Unpacker {
        /* access modifiers changed from: private */
        public final FileDso[] mDsos;

        private final class FileBackedInputDsoIterator extends InputDsoIterator {
            private int mCurrentDso;

            private FileBackedInputDsoIterator() {
            }

            public boolean hasNext() {
                return this.mCurrentDso < ExoUnpacker.this.mDsos.length;
            }

            public InputDso next() throws IOException {
                FileDso[] access$100 = ExoUnpacker.this.mDsos;
                int i = this.mCurrentDso;
                this.mCurrentDso = i + 1;
                FileDso fileDso = access$100[i];
                FileInputStream dsoFile = new FileInputStream(fileDso.backingFile);
                try {
                    InputDso ret = new InputDso(fileDso, dsoFile);
                    dsoFile = null;
                    return ret;
                } finally {
                    if (dsoFile != null) {
                        dsoFile.close();
                    }
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c8, code lost:
            r23 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c9, code lost:
            r27 = r23;
            r23 = r22;
            r22 = r27;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x014b, code lost:
            if (r7 == null) goto L_0x0152;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x014d, code lost:
            if (0 == 0) goto L_0x0173;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
            r7.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:0x0165, code lost:
            r21 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
            null.addSuppressed(r21);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:66:0x016e, code lost:
            r22 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:67:0x016f, code lost:
            r23 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:68:0x0173, code lost:
            r7.close();
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:66:0x016e A[Catch:{ all -> 0x016e, all -> 0x00c8 }, ExcHandler: all (th java.lang.Throwable), Splitter:B:8:0x006b] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        ExoUnpacker() throws java.io.IOException {
            /*
                r28 = this;
                r0 = r29
                r1 = r28
                com.facebook.soloader.ExoSoSource.this = r0
                r28.<init>()
                r0 = r29
                android.content.Context r8 = r0.mContext
                java.io.File r9 = new java.io.File
                java.lang.StringBuilder r22 = new java.lang.StringBuilder
                r22.<init>()
                java.lang.String r23 = "/data/local/tmp/exopackage/"
                java.lang.StringBuilder r22 = r22.append(r23)
                java.lang.String r23 = r8.getPackageName()
                java.lang.StringBuilder r22 = r22.append(r23)
                java.lang.String r23 = "/native-libs/"
                java.lang.StringBuilder r22 = r22.append(r23)
                java.lang.String r22 = r22.toString()
                r0 = r22
                r9.<init>(r0)
                java.util.ArrayList r18 = new java.util.ArrayList
                r18.<init>()
                java.lang.String[] r5 = com.facebook.soloader.SysUtil.getSupportedAbis()
                int r14 = r5.length
                r13 = 0
            L_0x003e:
                if (r13 >= r14) goto L_0x019a
                r3 = r5[r13]
                java.io.File r4 = new java.io.File
                r4.<init>(r9, r3)
                boolean r22 = r4.isDirectory()
                if (r22 != 0) goto L_0x0050
            L_0x004d:
                int r13 = r13 + 1
                goto L_0x003e
            L_0x0050:
                java.io.File r16 = new java.io.File
                java.lang.String r22 = "metadata.txt"
                r0 = r16
                r1 = r22
                r0.<init>(r4, r1)
                boolean r22 = r16.isFile()
                if (r22 == 0) goto L_0x004d
                java.io.FileReader r11 = new java.io.FileReader
                r0 = r16
                r11.<init>(r0)
                r24 = 0
                java.io.BufferedReader r7 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00c6, all -> 0x016e }
                r7.<init>(r11)     // Catch:{ Throwable -> 0x00c6, all -> 0x016e }
                r23 = 0
            L_0x0072:
                java.lang.String r15 = r7.readLine()     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                if (r15 == 0) goto L_0x014b
                int r22 = r15.length()     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                if (r22 == 0) goto L_0x0072
                r22 = 32
                r0 = r22
                int r19 = r15.indexOf(r0)     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                r22 = -1
                r0 = r19
                r1 = r22
                if (r0 != r1) goto L_0x00d7
                java.lang.RuntimeException r22 = new java.lang.RuntimeException     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                java.lang.StringBuilder r25 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                r25.<init>()     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                java.lang.String r26 = "illegal line in exopackage metadata: ["
                java.lang.StringBuilder r25 = r25.append(r26)     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                r0 = r25
                java.lang.StringBuilder r25 = r0.append(r15)     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                java.lang.String r26 = "]"
                java.lang.StringBuilder r25 = r25.append(r26)     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                java.lang.String r25 = r25.toString()     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                r0 = r22
                r1 = r25
                r0.<init>(r1)     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                throw r22     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
            L_0x00b5:
                r22 = move-exception
                throw r22     // Catch:{ all -> 0x00b7 }
            L_0x00b7:
                r23 = move-exception
                r27 = r23
                r23 = r22
                r22 = r27
            L_0x00be:
                if (r7 == 0) goto L_0x00c5
                if (r23 == 0) goto L_0x0181
                r7.close()     // Catch:{ Throwable -> 0x0177, all -> 0x016e }
            L_0x00c5:
                throw r22     // Catch:{ Throwable -> 0x00c6, all -> 0x016e }
            L_0x00c6:
                r22 = move-exception
                throw r22     // Catch:{ all -> 0x00c8 }
            L_0x00c8:
                r23 = move-exception
                r27 = r23
                r23 = r22
                r22 = r27
            L_0x00cf:
                if (r11 == 0) goto L_0x00d6
                if (r23 == 0) goto L_0x0195
                r11.close()     // Catch:{ Throwable -> 0x018b }
            L_0x00d6:
                throw r22
            L_0x00d7:
                java.lang.StringBuilder r22 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                r22.<init>()     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                r25 = 0
                r0 = r25
                r1 = r19
                java.lang.String r25 = r15.substring(r0, r1)     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                r0 = r22
                r1 = r25
                java.lang.StringBuilder r22 = r0.append(r1)     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                java.lang.String r25 = ".so"
                r0 = r22
                r1 = r25
                java.lang.StringBuilder r22 = r0.append(r1)     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                java.lang.String r20 = r22.toString()     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                int r17 = r18.size()     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                r10 = 0
                r12 = 0
            L_0x0103:
                r0 = r17
                if (r12 >= r0) goto L_0x0120
                r0 = r18
                java.lang.Object r22 = r0.get(r12)     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                com.facebook.soloader.ExoSoSource$FileDso r22 = (com.facebook.soloader.ExoSoSource.FileDso) r22     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                r0 = r22
                java.lang.String r0 = r0.name     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                r22 = r0
                r0 = r22
                r1 = r20
                boolean r22 = r0.equals(r1)     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                if (r22 == 0) goto L_0x0148
                r10 = 1
            L_0x0120:
                if (r10 != 0) goto L_0x0072
                int r22 = r19 + 1
                r0 = r22
                java.lang.String r6 = r15.substring(r0)     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                com.facebook.soloader.ExoSoSource$FileDso r22 = new com.facebook.soloader.ExoSoSource$FileDso     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                java.io.File r25 = new java.io.File     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                r0 = r25
                r0.<init>(r4, r6)     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                r0 = r22
                r1 = r20
                r2 = r25
                r0.<init>(r1, r6, r2)     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                r0 = r18
                r1 = r22
                r0.add(r1)     // Catch:{ Throwable -> 0x00b5, all -> 0x0145 }
                goto L_0x0072
            L_0x0145:
                r22 = move-exception
                goto L_0x00be
            L_0x0148:
                int r12 = r12 + 1
                goto L_0x0103
            L_0x014b:
                if (r7 == 0) goto L_0x0152
                if (r23 == 0) goto L_0x0173
                r7.close()     // Catch:{ Throwable -> 0x0165, all -> 0x016e }
            L_0x0152:
                if (r11 == 0) goto L_0x004d
                if (r24 == 0) goto L_0x0186
                r11.close()     // Catch:{ Throwable -> 0x015b }
                goto L_0x004d
            L_0x015b:
                r21 = move-exception
                r0 = r24
                r1 = r21
                r0.addSuppressed(r1)
                goto L_0x004d
            L_0x0165:
                r21 = move-exception
                r0 = r23
                r1 = r21
                r0.addSuppressed(r1)     // Catch:{ Throwable -> 0x00c6, all -> 0x016e }
                goto L_0x0152
            L_0x016e:
                r22 = move-exception
                r23 = r24
                goto L_0x00cf
            L_0x0173:
                r7.close()     // Catch:{ Throwable -> 0x00c6, all -> 0x016e }
                goto L_0x0152
            L_0x0177:
                r21 = move-exception
                r0 = r23
                r1 = r21
                r0.addSuppressed(r1)     // Catch:{ Throwable -> 0x00c6, all -> 0x016e }
                goto L_0x00c5
            L_0x0181:
                r7.close()     // Catch:{ Throwable -> 0x00c6, all -> 0x016e }
                goto L_0x00c5
            L_0x0186:
                r11.close()
                goto L_0x004d
            L_0x018b:
                r21 = move-exception
                r0 = r23
                r1 = r21
                r0.addSuppressed(r1)
                goto L_0x00d6
            L_0x0195:
                r11.close()
                goto L_0x00d6
            L_0x019a:
                int r22 = r18.size()
                r0 = r22
                com.facebook.soloader.ExoSoSource$FileDso[] r0 = new com.facebook.soloader.ExoSoSource.FileDso[r0]
                r22 = r0
                r0 = r18
                r1 = r22
                java.lang.Object[] r22 = r0.toArray(r1)
                com.facebook.soloader.ExoSoSource$FileDso[] r22 = (com.facebook.soloader.ExoSoSource.FileDso[]) r22
                r0 = r22
                r1 = r28
                r1.mDsos = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.ExoSoSource.ExoUnpacker.<init>(com.facebook.soloader.ExoSoSource):void");
        }

        /* access modifiers changed from: protected */
        public DsoManifest getDsoManifest() throws IOException {
            return new DsoManifest(this.mDsos);
        }

        /* access modifiers changed from: protected */
        public InputDsoIterator openDsoIterator() throws IOException {
            return new FileBackedInputDsoIterator();
        }
    }

    private static final class FileDso extends Dso {
        final File backingFile;

        FileDso(String name, String hash, File backingFile2) {
            super(name, hash);
            this.backingFile = backingFile2;
        }
    }

    public ExoSoSource(Context context, String name) {
        super(context, name);
    }

    /* access modifiers changed from: protected */
    public Unpacker makeUnpacker() throws IOException {
        return new ExoUnpacker();
    }
}
