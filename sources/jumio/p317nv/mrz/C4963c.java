package jumio.p317nv.mrz;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.jumio.commons.PersistWith;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.core.mvp.model.StaticModel;
import com.jumio.mrz.impl.smartEngines.swig.MrzDateField;
import com.jumio.mrz.impl.smartEngines.swig.MrzField;
import com.jumio.mrz.impl.smartEngines.swig.MrzResult;
import com.jumio.mrz.impl.smartEngines.swig.StringVector;
import com.jumio.p311nv.NetverifyDocumentData;
import com.jumio.p311nv.NetverifyMrzData;
import com.jumio.p311nv.data.document.NVMRZFormat;
import com.jumio.p311nv.enums.EPassportStatus;
import com.jumio.p311nv.extraction.JumioRect;
import com.jumio.p311nv.models.DocumentDataModel;
import com.jumio.p311nv.models.MrtdDataModel;
import com.jumio.p311nv.models.SelectionModel;
import com.jumio.p311nv.models.UploadDataModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@PersistWith("MrzDataModel")
/* renamed from: jumio.nv.mrz.c */
/* compiled from: MrzDataModel */
public class C4963c extends DocumentDataModel implements StaticModel {
    public static final Creator<C4963c> CREATOR = new Creator<C4963c>() {
        /* renamed from: a */
        public C4963c createFromParcel(Parcel parcel) {
            return new C4963c(parcel);
        }

        /* renamed from: a */
        public C4963c[] newArray(int i) {
            return new C4963c[i];
        }
    };

    /* renamed from: a */
    private ArrayList<ArrayList<JumioRect>> f4921a;

    public C4963c() {
        this.mrzData = new NetverifyMrzData();
    }

    public C4963c(Parcel parcel) {
        super(parcel);
        int readInt = parcel.readInt();
        if (readInt != 0) {
            this.f4921a = new ArrayList<>();
            for (int i = 0; i < readInt; i++) {
                ArrayList arrayList = new ArrayList();
                parcel.readList(arrayList, JumioRect.class.getClassLoader());
                this.f4921a.add(arrayList);
            }
        }
    }

    /* renamed from: a */
    public void mo46926a(MrzResult mrzResult, NVMRZFormat nVMRZFormat) {
        boolean z;
        boolean z2;
        boolean z3;
        this.mrzData.setFormat(nVMRZFormat);
        MrzDateField birthdate = mrzResult.getBirthdate();
        this.mrzData.setDobValid(birthdate.hasChecksumOcrChar() ? birthdate.hasCorrectChecksum() : true);
        MrzField docNum = mrzResult.getDocNum();
        NetverifyMrzData netverifyMrzData = this.mrzData;
        if (docNum.hasChecksumOcrChar()) {
            z = docNum.hasCorrectChecksum();
        } else {
            z = true;
        }
        netverifyMrzData.setIdNumberValid(z);
        if (nVMRZFormat != NVMRZFormat.CNIS) {
            MrzDateField expidate = mrzResult.getExpidate();
            NetverifyMrzData netverifyMrzData2 = this.mrzData;
            if (expidate.hasChecksumOcrChar()) {
                z3 = expidate.hasCorrectChecksum();
            } else {
                z3 = true;
            }
            netverifyMrzData2.setExpiryDateValid(z3);
        } else {
            this.mrzData.setExpiryDateValid(true);
        }
        if (nVMRZFormat == NVMRZFormat.MRP) {
            MrzField optData2 = mrzResult.getOptData2();
            NetverifyMrzData netverifyMrzData3 = this.mrzData;
            if (optData2.hasChecksumOcrChar()) {
                z2 = optData2.hasCorrectChecksum();
            } else {
                z2 = true;
            }
            netverifyMrzData3.setPersonalNumberValid(z2);
        } else {
            this.mrzData.setPersonalNumberValid(true);
        }
        if (nVMRZFormat == NVMRZFormat.MRV_A || nVMRZFormat == NVMRZFormat.MRV_B) {
            this.mrzData.setCompositeValid(true);
        } else {
            this.mrzData.setCompositeValid(mrzResult.hasCorrectCompositeChecksum());
        }
    }

    /* renamed from: a */
    public void mo46927a(StringVector stringVector) {
        if (stringVector.size() >= 1) {
            this.mrzData.setMrzLine1(stringVector.get(0));
        }
        if (stringVector.size() >= 2) {
            this.mrzData.setMrzLine2(stringVector.get(1));
        }
        if (stringVector.size() >= 3) {
            this.mrzData.setMrzLine3(stringVector.get(2));
        }
    }

    /* renamed from: a */
    public void mo46928a(ArrayList<ArrayList<JumioRect>> arrayList) {
        this.f4921a = arrayList;
    }

    public void populateData(NetverifyDocumentData netverifyDocumentData) {
        super.populateData(netverifyDocumentData);
        netverifyDocumentData.setMrzData(this.mrzData);
        netverifyDocumentData.setEPassportStatus(getEPassportStatus());
        netverifyDocumentData.setPlaceOfBirth(getPlaceOfBirth());
    }

    public void fillRequest(JSONObject jSONObject, SimpleDateFormat simpleDateFormat, SelectionModel selectionModel, UploadDataModel uploadDataModel) throws JSONException {
        String format;
        ScanSide documentScanSide = selectionModel.getSelectedDoctype().getDocumentScanSide();
        appendValue(jSONObject, "firstName", getFirstName());
        appendValue(jSONObject, "lastName", getLastName());
        appendValue(jSONObject, "extractionSide", documentScanSide == ScanSide.FRONT ? "FRONT" : "BACK");
        String str = "issuingDate";
        if (getIssuingDate() == null) {
            format = null;
        } else {
            format = simpleDateFormat.format(getIssuingDate());
        }
        appendValue(jSONObject, str, format);
        List scanModes = uploadDataModel.getScanModes();
        if (scanModes.contains(DocumentScanMode.MRP) || scanModes.contains(DocumentScanMode.TD1) || scanModes.contains(DocumentScanMode.TD2) || scanModes.contains(DocumentScanMode.CNIS) || scanModes.contains(DocumentScanMode.MRV)) {
            NetverifyMrzData mrzData = getMrzData();
            appendValue(jSONObject, "mrzLine1", mrzData.getMrzLine1());
            appendValue(jSONObject, "mrzLine2", mrzData.getMrzLine2());
            appendValue(jSONObject, "mrzLine3", mrzData.getMrzLine3());
            appendValue(jSONObject, "mrzLinesCoordinates", mo46925a());
        }
        MrtdDataModel mrtdData = uploadDataModel.getMrtdData();
        if (mrtdData != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("BAC", mrtdData.getBacCheckResult());
            jSONObject2.put("AA", mrtdData.getActiveAuthResult());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("DSC", mrtdData.getDscCheckResult());
            jSONObject3.put("CSCA", mrtdData.getRootCertCheck());
            JSONObject jSONObject4 = new JSONObject();
            Map htCheckResults = mrtdData.getHtCheckResults();
            if (htCheckResults != null) {
                for (Entry entry : htCheckResults.entrySet()) {
                    jSONObject4.put((String) entry.getKey(), entry.getValue());
                }
                jSONObject3.put("HTC", jSONObject4);
                jSONObject2.put("PA", jSONObject3);
                int[] encodedDataItems = mrtdData.getEncodedDataItems();
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put("authenticationSteps", "\"" + jSONObject2.toString() + "\"");
                jSONObject5.put("authenticationValid", mrtdData.getVerificationStatus() == EPassportStatus.VERIFIED);
                jSONObject5.put("additionalData", Arrays.toString(encodedDataItems));
                jSONObject.put("ePassport", jSONObject5);
            }
        }
    }

    /* renamed from: a */
    public JSONArray mo46925a() throws JSONException {
        if (this.f4921a == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < this.f4921a.size(); i++) {
            ArrayList arrayList = (ArrayList) this.f4921a.get(i);
            JSONArray jSONArray2 = new JSONArray();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("height", ((JumioRect) arrayList.get(i2)).height());
                jSONObject.put("width", ((JumioRect) arrayList.get(i2)).width());
                jSONObject.put("x", ((JumioRect) arrayList.get(i2)).left);
                jSONObject.put("y", ((JumioRect) arrayList.get(i2)).top);
                jSONArray2.put(jSONObject);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("mrzLineCoordinates", jSONArray2);
            jSONArray.put(jSONObject2);
        }
        return jSONArray;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        super.writeToParcel(parcel, i);
        if (this.f4921a != null) {
            i2 = this.f4921a.size();
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (this.f4921a != null) {
            for (int i3 = 0; i3 < this.f4921a.size(); i3++) {
                parcel.writeList((List) this.f4921a.get(i3));
            }
        }
    }
}
