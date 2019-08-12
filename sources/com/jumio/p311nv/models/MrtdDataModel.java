package com.jumio.p311nv.models;

import com.jumio.core.mvp.model.StaticModel;
import com.jumio.p311nv.enums.EPassportStatus;
import java.util.Map;

/* renamed from: com.jumio.nv.models.MrtdDataModel */
public interface MrtdDataModel extends StaticModel {
    int getActiveAuthResult();

    int getBacCheckResult();

    int getDscCheckResult();

    int[] getEncodedDataItems();

    Map<String, Integer> getHtCheckResults();

    String getMrzFirstName();

    String getMrzIdNumber();

    String getMrzIssuingCountry();

    String getMrzLastName();

    String getMrzOriginatingCountry();

    String getMrzPersonalNumber();

    String getMrzString();

    String getPlaceOfBirth();

    int getRootCertCheck();

    EPassportStatus getVerificationStatus();
}
