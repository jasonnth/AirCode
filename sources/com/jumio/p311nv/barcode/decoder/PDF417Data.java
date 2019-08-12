package com.jumio.p311nv.barcode.decoder;

import com.jumio.p311nv.barcode.enums.EyeColor;
import com.jumio.p311nv.enums.NVGender;
import java.util.Date;

/* renamed from: com.jumio.nv.barcode.decoder.PDF417Data */
public class PDF417Data {
    private String addressCity = "";
    private String addressState = "";
    private String addressStreet1 = "";
    private String addressStreet2 = "";
    private String addressZip = "";
    private Date dateOfBirth = null;
    private String endorsementCodes = "";
    private Date expiryDate = null;
    private EyeColor eyeColor = null;
    private String firstName = "";
    private NVGender gender = null;
    private String height = "";
    private String idNumber = "";
    private Date issueDate = null;
    private String issuingCountry = "";
    private String lastName = "";
    private String middleName = "";
    private String restrictionCodes = "";
    private StringBuilder unparsedData = new StringBuilder();
    private String vehicleClass = "";

    public String getIdNumber() {
        return this.idNumber;
    }

    public void setIdNumber(String str) {
        if (str != null && !str.trim().equals("")) {
            this.idNumber = str;
        }
    }

    public Date getIssueDate() {
        return this.issueDate;
    }

    public void setIssueDate(Date date) {
        this.issueDate = date;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(Date date) {
        this.expiryDate = date;
    }

    public String getIssuingCountry() {
        return this.issuingCountry;
    }

    public void setIssuingCountry(String str) {
        if (str != null && !str.trim().equals("")) {
            this.issuingCountry = str;
        }
    }

    public String getVehicleClass() {
        return this.vehicleClass;
    }

    public void setVehicleClass(String str) {
        if (str != null && !str.trim().equals("")) {
            this.vehicleClass = str;
        }
    }

    public String getRestrictionCodes() {
        return this.restrictionCodes;
    }

    public void setRestrictionCodes(String str) {
        if (str != null && !str.trim().equals("")) {
            this.restrictionCodes = str;
        }
    }

    public String getEndorsementCodes() {
        return this.endorsementCodes;
    }

    public void setEndorsementCodes(String str) {
        if (str != null && !str.trim().equals("")) {
            this.endorsementCodes = str;
        }
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String str) {
        if (str != null && !str.trim().equals("") && !str.equalsIgnoreCase("NONE")) {
            this.firstName = str;
        }
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String str) {
        if (str != null && !str.trim().equals("") && !str.equalsIgnoreCase("NONE")) {
            this.lastName = str;
        }
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String str) {
        if (str != null && !str.trim().equals("") && !str.equalsIgnoreCase("NONE")) {
            this.middleName = str;
        }
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date date) {
        this.dateOfBirth = date;
    }

    public NVGender getGender() {
        return this.gender;
    }

    public void setGender(NVGender nVGender) {
        if (nVGender != null) {
            this.gender = nVGender;
        }
    }

    public EyeColor getEyeColor() {
        return this.eyeColor;
    }

    public void setEyeColor(EyeColor eyeColor2) {
        if (eyeColor2 != null) {
            this.eyeColor = eyeColor2;
        }
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String str) {
        if (str != null && !str.trim().equals("")) {
            this.height = str;
        }
    }

    public String getAddressStreet1() {
        return this.addressStreet1;
    }

    public void setAddressStreet1(String str) {
        if (str != null && !str.trim().equals("")) {
            this.addressStreet1 = str;
        }
    }

    public String getAddressStreet2() {
        return this.addressStreet2;
    }

    public void setAddressStreet2(String str) {
        if (str != null && !str.trim().equals("")) {
            this.addressStreet2 = str;
        }
    }

    public String getAddressCity() {
        return this.addressCity;
    }

    public void setAddressCity(String str) {
        if (str != null && !str.trim().equals("")) {
            this.addressCity = str;
        }
    }

    public String getAddressState() {
        return this.addressState;
    }

    public void setAddressState(String str) {
        if (str != null && !str.trim().equals("")) {
            this.addressState = str;
        }
    }

    public String getAddressZip() {
        return this.addressZip;
    }

    public void setAddressZip(String str) {
        if (str != null && !str.trim().equals("")) {
            this.addressZip = str;
        }
    }

    public StringBuilder getUnparsedData() {
        return this.unparsedData;
    }

    public void addUnparsedData(String str, String str2) {
        this.unparsedData.append(str).append(str2);
    }

    public String toString(String str) {
        StringBuilder sb = new StringBuilder();
        if (this.issueDate != null) {
            sb.append("issueDate: ");
            sb.append(this.issueDate.toString());
            sb.append(str);
        }
        if (this.expiryDate != null) {
            sb.append("expiryDate: ");
            sb.append(this.expiryDate.toString());
            sb.append(str);
        }
        if (this.issuingCountry != null && !this.issuingCountry.equals("")) {
            sb.append("issuingCountry: ");
            sb.append(this.issuingCountry);
            sb.append(str);
        }
        if (this.vehicleClass != null && !this.vehicleClass.equals("")) {
            sb.append("vehicleClass: ");
            sb.append(this.vehicleClass);
            sb.append(str);
        }
        if (this.restrictionCodes != null && !this.restrictionCodes.equals("")) {
            sb.append("restrictionCodes: ");
            sb.append(this.restrictionCodes);
            sb.append(str);
        }
        if (this.endorsementCodes != null && !this.endorsementCodes.equals("")) {
            sb.append("endorsementCodes: ");
            sb.append(this.endorsementCodes);
            sb.append(str);
        }
        if (this.firstName != null && !this.firstName.equals("")) {
            sb.append("firstName: ");
            sb.append(this.firstName);
            sb.append(str);
        }
        if (this.lastName != null && !this.lastName.equals("")) {
            sb.append("lastName: ");
            sb.append(this.lastName);
            sb.append(str);
        }
        if (this.middleName != null && !this.middleName.equals("")) {
            sb.append("middleName: ");
            sb.append(this.middleName);
            sb.append(str);
        }
        if (this.dateOfBirth != null) {
            sb.append("dateOfBirth: ");
            sb.append(this.dateOfBirth);
            sb.append(str);
        }
        if (this.gender != null) {
            sb.append("sex: ");
            sb.append(this.gender.name());
            sb.append(str);
        }
        if (this.eyeColor != null) {
            sb.append("eyeColor: ");
            sb.append(this.eyeColor);
            sb.append(str);
        }
        if (this.height != null && !this.height.equals("")) {
            sb.append("height: ");
            sb.append(this.height);
            sb.append(str);
        }
        if (this.addressStreet1 != null && !this.addressStreet1.equals("")) {
            sb.append("addressStreet1: ");
            sb.append(this.addressStreet1);
            sb.append(str);
        }
        if (this.addressStreet2 != null && !this.addressStreet2.equals("")) {
            sb.append("addressStreet2: ");
            sb.append(this.addressStreet2);
            sb.append(str);
        }
        if (this.addressCity != null && !this.addressCity.equals("")) {
            sb.append("addressCity: ");
            sb.append(this.addressCity);
            sb.append(str);
        }
        if (this.addressState != null && !this.addressState.equals("")) {
            sb.append("addressState: ");
            sb.append(this.addressState);
            sb.append(str);
        }
        if (this.addressZip != null && !this.addressZip.equals("")) {
            sb.append("addressZip: ");
            sb.append(this.addressZip);
            sb.append(str);
        }
        if (this.idNumber != null && !this.idNumber.equals("")) {
            sb.append("idNumber: ");
            sb.append(this.idNumber);
        }
        return sb.toString();
    }

    public String toString() {
        return toString(", ");
    }
}
