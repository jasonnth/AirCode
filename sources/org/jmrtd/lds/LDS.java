package org.jmrtd.lds;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;
import org.jmrtd.PassportService;
import org.jmrtd.p321io.SplittableInputStream;

public class LDS {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private Map<Short, SplittableInputStream> fetchers = new TreeMap();
    private Map<Short, LDSFile> files = new TreeMap();

    public synchronized boolean isSameDocument(LDS lds) throws IOException {
        boolean z = false;
        synchronized (this) {
            if (lds != null) {
                try {
                    DG1File dG1File = getDG1File();
                    DG1File dG1File2 = lds.getDG1File();
                    if (!(dG1File == null || dG1File2 == null)) {
                        MRZInfo mRZInfo = dG1File.getMRZInfo();
                        MRZInfo mRZInfo2 = dG1File2.getMRZInfo();
                        if (!(mRZInfo == null || mRZInfo2 == null)) {
                            z = mRZInfo.equals(mRZInfo2);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        return z;
    }

    public synchronized void updateFrom(LDS lds) {
        if (lds != null) {
            if (lds.fetchers != null) {
                for (Entry entry : lds.fetchers.entrySet()) {
                    short shortValue = ((Short) entry.getKey()).shortValue();
                    SplittableInputStream splittableInputStream = (SplittableInputStream) this.fetchers.get(Short.valueOf(shortValue));
                    SplittableInputStream splittableInputStream2 = (SplittableInputStream) entry.getValue();
                    if (splittableInputStream == null) {
                        this.fetchers.put(Short.valueOf(shortValue), splittableInputStream2);
                    } else {
                        splittableInputStream.updateFrom(splittableInputStream2);
                    }
                }
            }
        }
    }

    public List<Short> getFileList() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.fetchers.keySet());
        hashSet.addAll(this.files.keySet());
        hashSet.addAll(getDataGroupList());
        if (hashSet.contains(Short.valueOf(PassportService.EF_DG14))) {
            try {
                DG14File dG14File = getDG14File();
                if (dG14File != null) {
                    hashSet.addAll(dG14File.getCVCAFileIds());
                }
            } catch (IOException e) {
                LOGGER.severe("Could not read EF.DG14");
            }
        }
        ArrayList arrayList = new ArrayList(hashSet);
        Collections.sort(arrayList);
        return arrayList;
    }

    public List<Short> getDataGroupList() {
        TreeSet treeSet = new TreeSet();
        try {
            for (int lookupFIDByTag : getCOMFile().getTagList()) {
                treeSet.add(Short.valueOf(LDSFileUtil.lookupFIDByTag(lookupFIDByTag)));
            }
        } catch (IOException e) {
            LOGGER.severe("Could not read EF.COM");
        }
        try {
            for (Integer intValue : getSODFile().getDataGroupHashes().keySet()) {
                treeSet.add(Short.valueOf(LDSFileUtil.lookupFIDByDataGroupNumber(intValue.intValue())));
            }
        } catch (IOException e2) {
            LOGGER.severe("Could not read EF.SOd");
        }
        ArrayList arrayList = new ArrayList(treeSet);
        Collections.sort(arrayList);
        return arrayList;
    }

    public int getBytesBuffered(short s) {
        SplittableInputStream splittableInputStream = (SplittableInputStream) this.fetchers.get(Short.valueOf(s));
        if (splittableInputStream == null) {
            return 0;
        }
        return splittableInputStream.getBytesBuffered();
    }

    public int getLength(short s) {
        SplittableInputStream splittableInputStream = (SplittableInputStream) this.fetchers.get(Short.valueOf(s));
        if (splittableInputStream == null) {
            return 0;
        }
        return splittableInputStream.getLength();
    }

    public int getPosition() {
        int i = 0;
        Iterator it = getFileList().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = getBytesBuffered(((Short) it.next()).shortValue()) + i2;
        }
    }

    public int getLength() {
        int i = 0;
        Iterator it = getFileList().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = getLength(((Short) it.next()).shortValue()) + i2;
        }
    }

    public void add(short s, InputStream inputStream, int i) throws IOException {
        this.fetchers.put(Short.valueOf(s), new SplittableInputStream(inputStream, i));
    }

    public void add(short s, byte[] bArr) throws IOException {
        add(s, new ByteArrayInputStream(bArr), bArr.length);
    }

    public void addAll(Collection<? extends LDSFile> collection) {
        for (LDSFile add : collection) {
            add(add);
        }
    }

    public void add(LDSFile lDSFile) {
        if (lDSFile != null) {
            if (lDSFile instanceof COMFile) {
                put(PassportService.EF_COM, lDSFile);
            } else if (lDSFile instanceof SODFile) {
                put(PassportService.EF_SOD, lDSFile);
            } else if (lDSFile instanceof CVCAFile) {
                CVCAFile cVCAFile = (CVCAFile) lDSFile;
                put(cVCAFile.getFID(), cVCAFile);
            } else if (lDSFile instanceof CardAccessFile) {
                put(284, lDSFile);
            } else if (lDSFile instanceof DataGroup) {
                DataGroup dataGroup = (DataGroup) lDSFile;
                put(LDSFileUtil.lookupFIDByTag(dataGroup.getTag()), dataGroup);
            } else {
                throw new IllegalArgumentException("Unsupported LDS file " + lDSFile.getClass().getCanonicalName());
            }
        }
    }

    public LDSFile getFile(short s) throws IOException {
        LDSFile lDSFile = (LDSFile) this.files.get(Short.valueOf(s));
        if (lDSFile != null) {
            return lDSFile;
        }
        AbstractLDSFile lDSFile2 = LDSFileUtil.getLDSFile(s, getInputStream(s));
        this.files.put(Short.valueOf(s), lDSFile2);
        return lDSFile2;
    }

    public InputStream getInputStream(short s) throws IOException {
        SplittableInputStream splittableInputStream = (SplittableInputStream) this.fetchers.get(Short.valueOf(s));
        if (splittableInputStream != null) {
            return splittableInputStream.getInputStream(0);
        }
        throw new IOException("No stream for " + Integer.toHexString(s));
    }

    public COMFile getCOMFile() throws IOException {
        return (COMFile) getFile(PassportService.EF_COM);
    }

    public SODFile getSODFile() throws IOException {
        return (SODFile) getFile(PassportService.EF_SOD);
    }

    public DG1File getDG1File() throws IOException {
        return (DG1File) getFile(PassportService.EF_DG1);
    }

    public DG2File getDG2File() throws IOException {
        return (DG2File) getFile(PassportService.EF_DG2);
    }

    public DG3File getDG3File() throws IOException {
        return (DG3File) getFile(PassportService.EF_DG3);
    }

    public DG4File getDG4File() throws IOException {
        return (DG4File) getFile(PassportService.EF_DG4);
    }

    public DG5File getDG5File() throws IOException {
        return (DG5File) getFile(PassportService.EF_DG5);
    }

    public DG6File getDG6File() throws IOException {
        return (DG6File) getFile(PassportService.EF_DG6);
    }

    public DG7File getDG7File() throws IOException {
        return (DG7File) getFile(PassportService.EF_DG7);
    }

    public DG11File getDG11File() throws IOException {
        return (DG11File) getFile(PassportService.EF_DG11);
    }

    public DG12File getDG12File() throws IOException {
        return (DG12File) getFile(PassportService.EF_DG12);
    }

    public DG14File getDG14File() throws IOException {
        return (DG14File) getFile(PassportService.EF_DG14);
    }

    public DG15File getDG15File() throws IOException {
        return (DG15File) getFile(PassportService.EF_DG15);
    }

    public CardAccessFile getCardAccessFile() throws IOException {
        return (CardAccessFile) getFile(284);
    }

    public CVCAFile getCVCAFile() throws IOException {
        short s = 284;
        DG14File dG14File = getDG14File();
        if (dG14File == null) {
            throw new IOException("EF.DF14 not available in LDS");
        }
        List cVCAFileIds = dG14File.getCVCAFileIds();
        LOGGER.warning("DEBUG: cvcaFIDs = " + cVCAFileIds);
        if (!(cVCAFileIds == null || cVCAFileIds.size() == 0)) {
            if (cVCAFileIds.size() > 1) {
                LOGGER.warning("More than one CVCA file id present in DG14.");
            }
            s = ((Short) cVCAFileIds.get(0)).shortValue();
        }
        return (CVCAFile) getFile(s);
    }

    private void put(short s, LDSFile lDSFile) {
        this.files.put(Short.valueOf(s), lDSFile);
        byte[] encoded = lDSFile.getEncoded();
        this.fetchers.put(Short.valueOf(s), new SplittableInputStream(new ByteArrayInputStream(encoded), encoded.length));
    }
}
