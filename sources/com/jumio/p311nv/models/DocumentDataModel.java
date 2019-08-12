package com.jumio.p311nv.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.braintreepayments.api.models.PostalAddress;
import com.jumio.commons.PersistWith;
import com.jumio.commons.log.Log;
import com.jumio.commons.utils.StringUtil;
import com.jumio.core.data.document.ScanSide;
import com.jumio.core.mvp.model.StaticModel;
import com.jumio.p311nv.NetverifyDocumentData;
import com.jumio.p311nv.data.country.Country;
import com.jumio.p311nv.enums.NVGender;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;
import jumio.p317nv.core.C4931k;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@PersistWith("DocumentDataModel")
/* renamed from: com.jumio.nv.models.DocumentDataModel */
public class DocumentDataModel extends NetverifyDocumentData implements StaticModel {
    public static final Creator<DocumentDataModel> CREATOR = new Creator<DocumentDataModel>() {
        /* renamed from: a */
        public DocumentDataModel createFromParcel(Parcel parcel) {
            return new DocumentDataModel(parcel);
        }

        /* renamed from: a */
        public DocumentDataModel[] newArray(int i) {
            return new DocumentDataModel[i];
        }
    };

    /* renamed from: a */
    private Boolean f3387a = null;

    public DocumentDataModel() {
    }

    public DocumentDataModel(Parcel parcel) {
        super(parcel);
        this.f3387a = m1979a(parcel.readInt());
    }

    /* renamed from: a */
    private Boolean m1979a(int i) {
        if (i == 2) {
            return Boolean.valueOf(true);
        }
        if (i == 1) {
            return Boolean.valueOf(false);
        }
        return null;
    }

    public void parseJson(String str) {
        if (str != null && !str.isEmpty()) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                setIdNumber(m1980a(jSONObject.optString("idNumber")));
                setPersonalNumber(m1980a(jSONObject.optString("personalNumber")));
                setIssuingDate(m1981a(jSONObject.optString("issueDate"), simpleDateFormat, true));
                setExpiryDate(m1981a(jSONObject.optString("expiryDate"), simpleDateFormat, false));
                setIssuingCountry(m1980a(jSONObject.optString("country")));
                setLastName(m1980a(jSONObject.optString("lastName")));
                setFirstName(m1980a(jSONObject.optString("firstName")));
                setMiddleName(m1980a(jSONObject.optString("middleName")));
                setDob(m1981a(jSONObject.optString("dateOfBirth"), simpleDateFormat, true));
                String a = m1980a(jSONObject.optString("gender"));
                if (NVGender.M.name().equals(a)) {
                    setGender(NVGender.M);
                } else if (NVGender.F.name().equals(a)) {
                    setGender(NVGender.F);
                }
                setAddressLine(m1980a(jSONObject.optString("address")));
                setCity(m1980a(jSONObject.optString("city")));
                setSubdivision(m1980a(jSONObject.optString("state")));
                setPostCode(m1980a(jSONObject.optString("zip")));
                setOriginatingCountry(jSONObject.optString("originatingCountry"));
                setOptionalData1(jSONObject.optString("optData1"));
                setOptionalData2(jSONObject.optString("optData2"));
            } catch (JSONException e) {
                Log.m1930w("DocumentDataModel", "json exception in parseResponse()", (Throwable) e);
            }
        }
    }

    /* renamed from: a */
    private String m1980a(String str) {
        if (str == null || str.length() == 0 || JSONObject.NULL.toString().equals(str)) {
            return null;
        }
        return str;
    }

    /* renamed from: a */
    private Date m1981a(String str, SimpleDateFormat simpleDateFormat, boolean z) {
        String a = m1980a(str);
        if (a == null) {
            return null;
        }
        try {
            if (!Pattern.compile("^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$").matcher(a).matches()) {
                return null;
            }
            Date parse = simpleDateFormat.parse(a);
            if (z) {
                try {
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    if (parse.after(simpleDateFormat2.parse(simpleDateFormat2.format(new Date())))) {
                        return null;
                    }
                } catch (ParseException e) {
                    return parse;
                }
            }
            return parse;
        } catch (ParseException e2) {
            return null;
        }
    }

    public void populateData(NetverifyDocumentData netverifyDocumentData) {
        netverifyDocumentData.setIdNumber(getIdNumber());
        netverifyDocumentData.setPersonalNumber(getPersonalNumber());
        netverifyDocumentData.setIssuingDate(getIssuingDate());
        netverifyDocumentData.setExpiryDate(getExpiryDate());
        netverifyDocumentData.setIssuingCountry(getIssuingCountry());
        netverifyDocumentData.setLastName(getLastName());
        netverifyDocumentData.setFirstName(getFirstName());
        netverifyDocumentData.setMiddleName(getMiddleName());
        netverifyDocumentData.setDob(getDob());
        netverifyDocumentData.setGender(getGender());
        netverifyDocumentData.setOriginatingCountry(getOriginatingCountry());
        netverifyDocumentData.setAddressLine(getAddressLine());
        netverifyDocumentData.setCity(getCity());
        netverifyDocumentData.setSubdivision(getSubdivision());
        netverifyDocumentData.setPostCode(getPostCode());
        netverifyDocumentData.setOptionalData1(getOptionalData1());
        netverifyDocumentData.setOptionalData2(getOptionalData2());
    }

    public void fillRequest(JSONObject jSONObject, SimpleDateFormat simpleDateFormat, SelectionModel selectionModel, UploadDataModel uploadDataModel) throws JSONException {
        String str = null;
        ScanSide documentScanSide = selectionModel.getSelectedDoctype().getDocumentScanSide();
        Country selectedCountry = selectionModel.getSelectedCountry();
        appendValue(jSONObject, "firstName", getFirstName());
        appendValue(jSONObject, "middleName", getMiddleName());
        appendValue(jSONObject, "lastName", getLastName());
        appendValue(jSONObject, "personalNumber", getPersonalNumber());
        appendValue(jSONObject, "number", getIdNumber());
        appendValue(jSONObject, "issuingDate", getIssuingDate() == null ? null : simpleDateFormat.format(getIssuingDate()));
        appendValue(jSONObject, "dob", getDob() == null ? null : simpleDateFormat.format(getDob()));
        String str2 = "expiry";
        if (getExpiryDate() != null) {
            str = simpleDateFormat.format(getExpiryDate());
        }
        appendValue(jSONObject, str2, str);
        appendValue(jSONObject, "country", getOriginatingCountry());
        appendValue(jSONObject, "optionalData1", getOptionalData1());
        appendValue(jSONObject, "optionalData2", getOptionalData2());
        appendValue(jSONObject, "extractionSide", documentScanSide == ScanSide.FRONT ? "FRONT" : "BACK");
        JSONObject jSONObject2 = new JSONObject();
        if (selectedCountry.getAddressFormat() == C4931k.US) {
            appendValue(jSONObject2, "city", getCity());
            appendValue(jSONObject2, "state", getSubdivision());
            appendValue(jSONObject2, "streetName", StringUtil.trim(getAddressLine(), 100));
            String postCode = getPostCode();
            if (postCode != null && postCode.length() == 15) {
                postCode = postCode.substring(0, 14);
            }
            appendValue(jSONObject2, "zip", postCode);
            if (jSONObject2.length() != 0) {
                jSONObject.put("usAddress", jSONObject2);
                return;
            }
            return;
        }
        appendValue(jSONObject2, "city", getCity());
        appendValue(jSONObject2, "province", getSubdivision());
        appendValue(jSONObject2, "streetName", StringUtil.trim(getAddressLine(), 100));
        appendValue(jSONObject2, PostalAddress.POSTAL_CODE_KEY, getPostCode());
        if (jSONObject2.length() != 0) {
            jSONObject.put("euAddress", jSONObject2);
        }
    }

    /* access modifiers changed from: protected */
    public void appendValue(JSONObject jSONObject, String str, String str2) throws JSONException {
        if (str2 != null && str2.length() != 0) {
            jSONObject.put(str, str2);
        }
    }

    /* access modifiers changed from: protected */
    public void appendValue(JSONObject jSONObject, String str, JSONArray jSONArray) throws JSONException {
        if (jSONArray != null && jSONArray.length() != 0) {
            jSONObject.put(str, jSONArray);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        int i2 = this.f3387a == null ? 0 : this.f3387a.booleanValue() ? 2 : 1;
        parcel.writeInt(i2);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo43472a(MrtdDataModel mrtdDataModel) {
        setFirstName(mrtdDataModel.getMrzFirstName());
        setLastName(mrtdDataModel.getMrzLastName());
        setPersonalNumber(mrtdDataModel.getMrzPersonalNumber());
        setIdNumber(mrtdDataModel.getMrzIdNumber());
        setIssuingCountry(mrtdDataModel.getMrzIssuingCountry());
        setOriginatingCountry(mrtdDataModel.getMrzOriginatingCountry());
    }

    public Boolean getLivenessDetected() {
        return this.f3387a;
    }

    public void setLivenessDetected(Boolean bool) {
        this.f3387a = bool;
    }
}
