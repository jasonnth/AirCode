package com.jumio.p311nv.models;

import com.jumio.commons.PersistWith;
import com.jumio.commons.camera.ImageData;
import com.jumio.commons.log.Log;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.core.mvp.model.StaticModel;
import com.jumio.p311nv.enums.NVExtractionMethod;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@PersistWith("UploadDataModel")
/* renamed from: com.jumio.nv.models.UploadDataModel */
public class UploadDataModel implements StaticModel {

    /* renamed from: a */
    private List<NVScanPartModel> f3438a = new ArrayList();

    /* renamed from: b */
    private Comparator<? super NVScanPartModel> f3439b = new C4462a();

    /* renamed from: c */
    private MrtdDataModel f3440c;

    /* renamed from: d */
    private int f3441d;

    /* renamed from: com.jumio.nv.models.UploadDataModel$a */
    class C4462a implements Serializable, Comparator<NVScanPartModel> {
        private C4462a() {
        }

        /* renamed from: a */
        public int compare(NVScanPartModel nVScanPartModel, NVScanPartModel nVScanPartModel2) {
            if (nVScanPartModel.getSideToScan() == nVScanPartModel2.getSideToScan()) {
                return 0;
            }
            return nVScanPartModel.getSideToScan().getPartNumber() < nVScanPartModel2.getSideToScan().getPartNumber() ? -1 : 1;
        }
    }

    public ImageData getImageDataForPart(ScanSide scanSide) {
        for (NVScanPartModel nVScanPartModel : this.f3438a) {
            if (nVScanPartModel.getSideToScan().equals(scanSide)) {
                return nVScanPartModel.getScannedImage();
            }
        }
        Log.m1919i("UploadDataModel", "getImageDataForPart(): no scan side yet has an ImageData");
        return null;
    }

    public List<DocumentScanMode> getScanModes() {
        ArrayList arrayList = new ArrayList();
        for (NVScanPartModel scanMode : this.f3438a) {
            arrayList.add(scanMode.getScanMode());
        }
        return arrayList;
    }

    public NVExtractionMethod getExtractionMethod() {
        ArrayList arrayList = new ArrayList();
        for (NVScanPartModel nVScanPartModel : this.f3438a) {
            switch (nVScanPartModel.getScanMode()) {
                case MRP:
                case MRV:
                case TD1:
                case TD2:
                case CNIS:
                    arrayList.add(NVExtractionMethod.MRZ);
                    break;
                case PDF417:
                    arrayList.add(NVExtractionMethod.BARCODE);
                    break;
                case CSSN:
                case TEMPLATEMATCHER:
                    arrayList.add(NVExtractionMethod.OCR);
                    break;
            }
        }
        NVExtractionMethod nVExtractionMethod = NVExtractionMethod.NONE;
        if (arrayList.size() == 1) {
            return (NVExtractionMethod) arrayList.get(0);
        }
        if (arrayList.size() <= 1) {
            return nVExtractionMethod;
        }
        if (!arrayList.contains(NVExtractionMethod.BARCODE) || !arrayList.contains(NVExtractionMethod.OCR)) {
            return (NVExtractionMethod) arrayList.get(0);
        }
        return NVExtractionMethod.BARCODE_OCR;
    }

    public DocumentDataModel getDocumentData() {
        for (NVScanPartModel nVScanPartModel : this.f3438a) {
            if (nVScanPartModel.getDocumentInfo() != null) {
                return nVScanPartModel.getDocumentInfo();
            }
        }
        Log.m1919i("UploadDataModel", "getDocumentData(): no scan side yet has a DocumentDataModel");
        return null;
    }

    public UploadDataModel setDocumentData(ScanSide scanSide, DocumentDataModel documentDataModel) {
        Iterator it = this.f3438a.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            NVScanPartModel nVScanPartModel = (NVScanPartModel) it.next();
            if (nVScanPartModel.getSideToScan() == scanSide) {
                nVScanPartModel.setDocumentInfo(documentDataModel);
                break;
            }
        }
        return this;
    }

    public List<NVScanPartModel> getScans() {
        return this.f3438a;
    }

    public NVScanPartModel getScan(ScanSide scanSide) {
        for (NVScanPartModel nVScanPartModel : this.f3438a) {
            if (nVScanPartModel.getSideToScan().equals(scanSide)) {
                return nVScanPartModel;
            }
        }
        Log.m1919i("UploadDataModel", "getScan(): no scan yet");
        return null;
    }

    public UploadDataModel add(NVScanPartModel nVScanPartModel) {
        this.f3438a.add(nVScanPartModel);
        Collections.sort(this.f3438a, this.f3439b);
        return this;
    }

    public void remove(ScanSide scanSide) {
        int size = this.f3438a.size();
        for (int i = 0; i < size; i++) {
            NVScanPartModel nVScanPartModel = (NVScanPartModel) this.f3438a.get(i);
            if (nVScanPartModel.getSideToScan() == scanSide) {
                this.f3438a.remove(nVScanPartModel);
            }
        }
    }

    public boolean has(ScanSide scanSide) {
        int size = this.f3438a.size();
        for (int i = 0; i < size; i++) {
            if (((NVScanPartModel) this.f3438a.get(i)).getSideToScan() == scanSide) {
                return true;
            }
        }
        return false;
    }

    public UploadDataModel replace(ScanSide scanSide, NVScanPartModel nVScanPartModel) {
        int size = this.f3438a.size();
        for (int i = 0; i < size; i++) {
            if (((NVScanPartModel) this.f3438a.get(i)).getSideToScan() == scanSide) {
                this.f3438a.set(i, nVScanPartModel);
            }
        }
        return this;
    }

    public MrtdDataModel getMrtdData() {
        return this.f3440c;
    }

    public void setMrtdData(MrtdDataModel mrtdDataModel) {
        this.f3440c = mrtdDataModel;
        if (getDocumentData() != null) {
            getDocumentData().mo43472a(mrtdDataModel);
        }
    }

    public NVScanPartModel nextPart() {
        this.f3441d++;
        return getActivePart();
    }

    public NVScanPartModel getActivePart() {
        return (NVScanPartModel) this.f3438a.get(this.f3441d);
    }

    public void setActivePart(int i) {
        this.f3441d = i;
    }

    public boolean hasNext() {
        return this.f3438a.size() > this.f3441d + 1;
    }
}
