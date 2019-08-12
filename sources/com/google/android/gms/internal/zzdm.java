package com.google.android.gms.internal;

import java.io.IOException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

@zzme
public class zzdm {
    private final boolean zzyA = false;
    private final int zzyB;
    private final int zzyC;
    private final int zzyo;
    private final zzdg zzyq;
    private String zzyy;
    private String zzyz;

    public class zza implements Comparator<zzdf> {
        public zza(zzdm zzdm) {
        }

        /* renamed from: zza */
        public int compare(zzdf zzdf, zzdf zzdf2) {
            if (zzdf.zzep() < zzdf2.zzep()) {
                return -1;
            }
            if (zzdf.zzep() > zzdf2.zzep()) {
                return 1;
            }
            if (zzdf.zzeo() < zzdf2.zzeo()) {
                return -1;
            }
            if (zzdf.zzeo() > zzdf2.zzeo()) {
                return 1;
            }
            float zzer = (zzdf.zzer() - zzdf.zzep()) * (zzdf.zzeq() - zzdf.zzeo());
            float zzer2 = (zzdf2.zzer() - zzdf2.zzep()) * (zzdf2.zzeq() - zzdf2.zzeo());
            if (zzer <= zzer2) {
                return zzer < zzer2 ? 1 : 0;
            }
            return -1;
        }
    }

    public zzdm(int i, int i2, int i3) {
        this.zzyo = i;
        if (i2 > 64 || i2 < 0) {
            this.zzyB = 64;
        } else {
            this.zzyB = i2;
        }
        if (i3 < 1) {
            this.zzyC = 1;
        } else {
            this.zzyC = i3;
        }
        this.zzyq = new zzdl(this.zzyB);
    }

    /* access modifiers changed from: 0000 */
    public String zza(String str, char c) {
        StringBuilder sb = new StringBuilder(str);
        boolean z = false;
        int i = 1;
        while (i + 2 <= sb.length()) {
            if (sb.charAt(i) == '\'') {
                if (sb.charAt(i - 1) == c || !((sb.charAt(i + 1) == 's' || sb.charAt(i + 1) == 'S') && (i + 2 == sb.length() || sb.charAt(i + 2) == c))) {
                    sb.setCharAt(i, c);
                } else {
                    sb.insert(i, c);
                    i += 2;
                }
                z = true;
            }
            i++;
        }
        if (z) {
            return sb.toString();
        }
        return null;
    }

    public String zza(ArrayList<String> arrayList, ArrayList<zzdf> arrayList2) {
        Collections.sort(arrayList2, new zza(this));
        HashSet hashSet = new HashSet();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= arrayList2.size() || !zza(Normalizer.normalize((CharSequence) arrayList.get(((zzdf) arrayList2.get(i2)).zzes()), Form.NFKC).toLowerCase(Locale.US), hashSet)) {
                zza zza2 = new zza();
                this.zzyy = "";
                Iterator it = hashSet.iterator();
            } else {
                i = i2 + 1;
            }
        }
        zza zza22 = new zza();
        this.zzyy = "";
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            try {
                zza22.write(this.zzyq.zzF((String) it2.next()));
            } catch (IOException e) {
                zzpk.zzb("Error while writing hash to byteStream", e);
            }
        }
        return zza22.toString();
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0031 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean zza(java.lang.String r11, java.util.HashSet<java.lang.String> r12) {
        /*
            r10 = this;
            r5 = 1
            r1 = 0
            java.lang.String r0 = "\n"
            java.lang.String[] r6 = r11.split(r0)
            int r0 = r6.length
            if (r0 != 0) goto L_0x000e
            r1 = r5
        L_0x000d:
            return r1
        L_0x000e:
            r0 = r1
        L_0x000f:
            int r2 = r6.length
            if (r0 >= r2) goto L_0x008b
            r3 = r6[r0]
            java.lang.String r2 = "'"
            int r2 = r3.indexOf(r2)
            r4 = -1
            if (r2 == r4) goto L_0x008f
            r2 = 32
            java.lang.String r2 = r10.zza(r3, r2)
            if (r2 == 0) goto L_0x008f
            r10.zzyz = r2
        L_0x0028:
            java.lang.String[] r7 = com.google.android.gms.internal.zzdi.zzd(r2, r5)
            int r2 = r7.length
            int r3 = r10.zzyC
            if (r2 >= r3) goto L_0x0034
        L_0x0031:
            int r0 = r0 + 1
            goto L_0x000f
        L_0x0034:
            r2 = r1
        L_0x0035:
            int r3 = r7.length
            if (r2 >= r3) goto L_0x0048
            java.lang.String r3 = ""
            r4 = r1
        L_0x003c:
            int r8 = r10.zzyC
            if (r4 >= r8) goto L_0x008d
            int r8 = r2 + r4
            int r9 = r7.length
            if (r8 < r9) goto L_0x0051
            r4 = r1
        L_0x0046:
            if (r4 != 0) goto L_0x007d
        L_0x0048:
            int r2 = r12.size()
            int r3 = r10.zzyo
            if (r2 < r3) goto L_0x0031
            goto L_0x000d
        L_0x0051:
            if (r4 <= 0) goto L_0x005e
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r8 = " "
            java.lang.String r3 = r3.concat(r8)
        L_0x005e:
            java.lang.String r8 = java.lang.String.valueOf(r3)
            int r3 = r2 + r4
            r3 = r7[r3]
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r9 = r3.length()
            if (r9 == 0) goto L_0x0077
            java.lang.String r3 = r8.concat(r3)
        L_0x0074:
            int r4 = r4 + 1
            goto L_0x003c
        L_0x0077:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r8)
            goto L_0x0074
        L_0x007d:
            r12.add(r3)
            int r3 = r12.size()
            int r4 = r10.zzyo
            if (r3 >= r4) goto L_0x000d
            int r2 = r2 + 1
            goto L_0x0035
        L_0x008b:
            r1 = r5
            goto L_0x000d
        L_0x008d:
            r4 = r5
            goto L_0x0046
        L_0x008f:
            r2 = r3
            goto L_0x0028
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdm.zza(java.lang.String, java.util.HashSet):boolean");
    }
}
