package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.b */
/* compiled from: DefaultLDSProvider */
public class C4994b implements C5032c {
    /* JADX WARNING: Removed duplicated region for block: B:11:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x005c  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public jumio.p317nv.nfc.C5112o mo46961a(org.jmrtd.PassportService r10, java.util.Collection<java.lang.Integer> r11, boolean r12) {
        /*
            r9 = this;
            jumio.nv.nfc.o r2 = new jumio.nv.nfc.o
            jumio.nv.nfc.p r0 = jumio.p317nv.nfc.C5113p.READ_LDS
            jumio.nv.nfc.q r1 = jumio.p317nv.nfc.C5114q.SUCCESSFUL
            r2.<init>(r0, r1)
            r3 = 0
            java.lang.String r0 = "DefaultLDSProvider"
            java.lang.String r1 = "setting up LDS"
            jumio.p317nv.nfc.C5100f.m3694a(r0, r1)
            org.jmrtd.lds.LDS r4 = new org.jmrtd.lds.LDS
            r4.<init>()
            r2.mo47203a(r4)
            r0 = 286(0x11e, float:4.01E-43)
            net.sf.scuba.smartcards.CardFileInputStream r0 = r10.getInputStream(r0)     // Catch:{ IOException -> 0x01f1, CardServiceException -> 0x006b }
            r1 = 286(0x11e, float:4.01E-43)
            int r5 = r0.getLength()     // Catch:{ IOException -> 0x01f1, CardServiceException -> 0x006b }
            r4.add(r1, r0, r5)     // Catch:{ IOException -> 0x01f1, CardServiceException -> 0x006b }
            r4.getCOMFile()     // Catch:{ IOException -> 0x01f1, CardServiceException -> 0x006b }
            r0 = 285(0x11d, float:4.0E-43)
            net.sf.scuba.smartcards.CardFileInputStream r0 = r10.getInputStream(r0)     // Catch:{ IOException -> 0x01f1, CardServiceException -> 0x006b }
            org.jmrtd.lds.SODFile r1 = new org.jmrtd.lds.SODFile     // Catch:{ IOException -> 0x01f1, CardServiceException -> 0x006b }
            r1.<init>(r0)     // Catch:{ IOException -> 0x01f1, CardServiceException -> 0x006b }
            r4.add(r1)     // Catch:{ IOException -> 0x01f5, CardServiceException -> 0x01ee }
            r0 = 257(0x101, float:3.6E-43)
            net.sf.scuba.smartcards.CardFileInputStream r0 = r10.getInputStream(r0)     // Catch:{ IOException -> 0x01f5, CardServiceException -> 0x01ee }
            r3 = 257(0x101, float:3.6E-43)
            int r5 = r0.getLength()     // Catch:{ IOException -> 0x01f5, CardServiceException -> 0x01ee }
            r4.add(r3, r0, r5)     // Catch:{ IOException -> 0x01f5, CardServiceException -> 0x01ee }
            r4.getDG1File()     // Catch:{ IOException -> 0x01f5, CardServiceException -> 0x01ee }
            r0 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ IOException -> 0x01f5, CardServiceException -> 0x01ee }
            r11.add(r0)     // Catch:{ IOException -> 0x01f5, CardServiceException -> 0x01ee }
        L_0x0055:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            if (r1 != 0) goto L_0x007f
            jumio.nv.nfc.q r0 = jumio.p317nv.nfc.C5114q.ERROR
            java.lang.Throwable r1 = new java.lang.Throwable
            java.lang.String r3 = "SOD file could not be read"
            r1.<init>(r3)
            r2.mo47206a(r0, r1)
            r0 = r2
        L_0x006a:
            return r0
        L_0x006b:
            r0 = move-exception
            r1 = r3
        L_0x006d:
            r0.printStackTrace()
            java.lang.String r3 = "DefaultLDSProvider"
            java.lang.String r5 = "Could not read file"
            jumio.p317nv.nfc.C5100f.m3695a(r3, r5, r0)
            jumio.nv.nfc.q r3 = jumio.p317nv.nfc.C5114q.ERROR
            r2.mo47206a(r3, r0)
            goto L_0x0055
        L_0x007f:
            java.util.Map r1 = r1.getDataGroupHashes()
            java.util.Set r1 = r1.keySet()
            r0.addAll(r1)
            java.util.Collections.sort(r0)
            java.lang.String r1 = "DefaultLDSProvider"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "prefetching DGs: "
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r3 = r3.toString()
            jumio.p317nv.nfc.C5100f.m3694a(r1, r3)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r3 = 5
            java.lang.Integer[] r3 = new java.lang.Integer[r3]
            r5 = 0
            r6 = 8
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r3[r5] = r6
            r5 = 1
            r6 = 9
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r3[r5] = r6
            r5 = 2
            r6 = 10
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r3[r5] = r6
            r5 = 3
            r6 = 13
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r3[r5] = r6
            r5 = 4
            r6 = 16
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r3[r5] = r6
            java.util.Collections.addAll(r1, r3)
            java.util.Iterator r3 = r0.iterator()
        L_0x00e3:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0195
            java.lang.Object r0 = r3.next()
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r5 = r0.intValue()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            boolean r0 = r1.contains(r0)
            if (r0 == 0) goto L_0x0115
            java.lang.String r0 = "DefaultLDSProvider"
            java.lang.String r6 = "DG%d is not valid - skipping!"
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]
            r8 = 0
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r7[r8] = r5
            java.lang.String r5 = java.lang.String.format(r6, r7)
            jumio.p317nv.nfc.C5100f.m3696b(r0, r5)
            goto L_0x00e3
        L_0x0115:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            boolean r0 = r11.contains(r0)
            if (r0 != 0) goto L_0x00e3
            if (r12 != 0) goto L_0x0124
            r0 = 2
            if (r5 == r0) goto L_0x00e3
        L_0x0124:
            r0 = 3
            if (r5 == r0) goto L_0x00e3
            r0 = 4
            if (r5 == r0) goto L_0x00e3
            java.lang.String r0 = "DefaultLDSProvider"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0166, CardServiceException -> 0x0198, NumberFormatException -> 0x01c9 }
            r6.<init>()     // Catch:{ IOException -> 0x0166, CardServiceException -> 0x0198, NumberFormatException -> 0x01c9 }
            java.lang.String r7 = "reading DG"
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ IOException -> 0x0166, CardServiceException -> 0x0198, NumberFormatException -> 0x01c9 }
            java.lang.StringBuilder r6 = r6.append(r5)     // Catch:{ IOException -> 0x0166, CardServiceException -> 0x0198, NumberFormatException -> 0x01c9 }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x0166, CardServiceException -> 0x0198, NumberFormatException -> 0x01c9 }
            jumio.p317nv.nfc.C5100f.m3694a(r0, r6)     // Catch:{ IOException -> 0x0166, CardServiceException -> 0x0198, NumberFormatException -> 0x01c9 }
            short r0 = org.jmrtd.lds.LDSFileUtil.lookupFIDByDataGroupNumber(r5)     // Catch:{ IOException -> 0x0166, CardServiceException -> 0x0198, NumberFormatException -> 0x01c9 }
            net.sf.scuba.smartcards.CardFileInputStream r6 = r10.getInputStream(r0)     // Catch:{ IOException -> 0x0166, CardServiceException -> 0x0198, NumberFormatException -> 0x01c9 }
            int r7 = r6.getLength()     // Catch:{ IOException -> 0x0166, CardServiceException -> 0x0198, NumberFormatException -> 0x01c9 }
            r4.add(r0, r6, r7)     // Catch:{ IOException -> 0x0166, CardServiceException -> 0x0198, NumberFormatException -> 0x01c9 }
            org.jmrtd.lds.LDSFile r0 = r4.getFile(r0)     // Catch:{ IOException -> 0x0166, CardServiceException -> 0x0198, NumberFormatException -> 0x01c9 }
            r0.getEncoded()     // Catch:{ IOException -> 0x0166, CardServiceException -> 0x0198, NumberFormatException -> 0x01c9 }
            r6.close()     // Catch:{ IOException -> 0x0166, CardServiceException -> 0x0198, NumberFormatException -> 0x01c9 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)     // Catch:{ IOException -> 0x0166, CardServiceException -> 0x0198, NumberFormatException -> 0x01c9 }
            r11.add(r0)     // Catch:{ IOException -> 0x0166, CardServiceException -> 0x0198, NumberFormatException -> 0x01c9 }
            goto L_0x00e3
        L_0x0166:
            r0 = move-exception
            java.lang.String r1 = "DefaultLDSProvider"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Error reading DG"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.String r4 = ": "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = r0.getMessage()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            jumio.p317nv.nfc.C5100f.m3698c(r1, r3)
            jumio.nv.nfc.q r1 = jumio.p317nv.nfc.C5114q.ERROR
            r2.mo47206a(r1, r0)
        L_0x0195:
            r0 = r2
            goto L_0x006a
        L_0x0198:
            r0 = move-exception
            java.lang.String r6 = "DefaultLDSProvider"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Could not read DG"
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r5 = r7.append(r5)
            java.lang.String r7 = ": "
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r7 = r0.getMessage()
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r5 = r5.toString()
            jumio.p317nv.nfc.C5100f.m3698c(r6, r5)
            jumio.nv.nfc.q r5 = jumio.p317nv.nfc.C5114q.ERROR
            r2.mo47206a(r5, r0)
            goto L_0x00e3
        L_0x01c9:
            r0 = move-exception
            java.lang.String r6 = "DefaultLDSProvider"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "NumberFormatException trying to get FID for DG"
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r5 = r7.append(r5)
            java.lang.String r5 = r5.toString()
            jumio.p317nv.nfc.C5100f.m3698c(r6, r5)
            r0.printStackTrace()
            jumio.nv.nfc.q r5 = jumio.p317nv.nfc.C5114q.ERROR
            r2.mo47206a(r5, r0)
            goto L_0x00e3
        L_0x01ee:
            r0 = move-exception
            goto L_0x006d
        L_0x01f1:
            r0 = move-exception
            r1 = r3
            goto L_0x006d
        L_0x01f5:
            r0 = move-exception
            goto L_0x006d
        */
        throw new UnsupportedOperationException("Method not decompiled: jumio.p317nv.nfc.C4994b.mo46961a(org.jmrtd.PassportService, java.util.Collection, boolean):jumio.nv.nfc.o");
    }
}
