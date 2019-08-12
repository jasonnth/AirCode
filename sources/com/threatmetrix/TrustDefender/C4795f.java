package com.threatmetrix.TrustDefender;

import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrength;
import android.telephony.TelephonyManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.threatmetrix.TrustDefender.f */
class C4795f {

    /* renamed from: a */
    private static final String f4342a = C4834w.m2892a(C4795f.class);

    C4795f() {
    }

    @TargetApi(18)
    /* renamed from: a */
    static Map<String, String> m2764a(Context context) {
        Map<String, String> cellTowerInformation = new HashMap<>();
        List<CellInfo> cellInfo = m2767c(context);
        if (cellInfo == null || cellInfo.size() <= 0) {
            return cellTowerInformation;
        }
        for (CellInfo info : cellInfo) {
            if (info.isRegistered()) {
                switch (m2763a(info)) {
                    case WCDMA:
                        m2765a(((CellInfoWcdma) info).getCellSignalStrength(), ((CellInfoWcdma) info).getCellIdentity().toString(), cellTowerInformation);
                        break;
                    case GSM:
                        m2765a(((CellInfoGsm) info).getCellSignalStrength(), ((CellInfoGsm) info).getCellIdentity().toString(), cellTowerInformation);
                        break;
                    case LTE:
                        m2765a(((CellInfoLte) info).getCellSignalStrength(), ((CellInfoLte) info).getCellIdentity().toString(), cellTowerInformation);
                        break;
                    case CDMA:
                        m2765a(((CellInfoCdma) info).getCellSignalStrength(), ((CellInfoCdma) info).getCellIdentity().toString(), cellTowerInformation);
                        break;
                }
                if (cellTowerInformation.size() < 3) {
                    return null;
                }
            }
        }
        return cellTowerInformation;
    }

    @TargetApi(18)
    /* renamed from: b */
    static String m2766b(Context context) {
        StringBuilder cellId = new StringBuilder();
        List<CellInfo> cellInfo = m2767c(context);
        boolean isFirst = true;
        C4827a typeOfFirstMatch = C4827a.LTE;
        if (cellInfo != null && cellInfo.size() > 0) {
            for (CellInfo info : cellInfo) {
                if (info.isRegistered()) {
                    C4827a type = m2763a(info);
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        if (typeOfFirstMatch.mo46090b() <= type.mo46090b()) {
                        }
                    }
                    typeOfFirstMatch = type;
                    switch (type) {
                        case WCDMA:
                            CellIdentityWcdma ciw = ((CellInfoWcdma) info).getCellIdentity();
                            int id = ciw.getCid();
                            int mcc = ciw.getMcc();
                            int mnc = ciw.getMnc();
                            int lac = ciw.getLac();
                            if (!(id == Integer.MAX_VALUE || mcc == Integer.MAX_VALUE || mnc == Integer.MAX_VALUE || lac == Integer.MAX_VALUE)) {
                                cellId.append(type.mo46089a()).append(":").append(String.valueOf(id)).append(":").append(String.valueOf(mcc)).append(":").append(String.valueOf(mnc)).append(":").append(String.valueOf(lac));
                                break;
                            }
                        case GSM:
                            CellIdentityGsm cig = ((CellInfoGsm) info).getCellIdentity();
                            int id2 = cig.getCid();
                            int mcc2 = cig.getMcc();
                            int mnc2 = cig.getMnc();
                            int lac2 = cig.getLac();
                            if (!(id2 == Integer.MAX_VALUE || mcc2 == Integer.MAX_VALUE || mnc2 == Integer.MAX_VALUE || lac2 == Integer.MAX_VALUE)) {
                                cellId.append(type.mo46089a()).append(":").append(String.valueOf(id2)).append(":").append(String.valueOf(mcc2)).append(":").append(String.valueOf(mnc2)).append(":").append(String.valueOf(lac2));
                                break;
                            }
                        case LTE:
                            CellIdentityLte cil = ((CellInfoLte) info).getCellIdentity();
                            int id3 = cil.getCi();
                            int mcc3 = cil.getMcc();
                            int mnc3 = cil.getMnc();
                            int lac3 = cil.getTac();
                            if (!(id3 == Integer.MAX_VALUE || mcc3 == Integer.MAX_VALUE || mnc3 == Integer.MAX_VALUE || lac3 == Integer.MAX_VALUE)) {
                                cellId.append(type.mo46089a()).append(":").append(String.valueOf(id3)).append(":").append(String.valueOf(mcc3)).append(":").append(String.valueOf(mnc3)).append(":").append(String.valueOf(lac3));
                                break;
                            }
                        case CDMA:
                            CellIdentityCdma cic = ((CellInfoCdma) info).getCellIdentity();
                            int id4 = cic.getBasestationId();
                            int mnc4 = cic.getSystemId();
                            int lac4 = cic.getNetworkId();
                            if (!(id4 == Integer.MAX_VALUE || mnc4 == Integer.MAX_VALUE || lac4 == Integer.MAX_VALUE)) {
                                cellId.append(type.mo46089a()).append(":").append(String.valueOf(id4)).append(":").append(String.valueOf(mnc4)).append(":").append(String.valueOf(lac4));
                                break;
                            }
                    }
                }
            }
        }
        return cellId.toString();
    }

    @TargetApi(18)
    /* renamed from: c */
    private static List<CellInfo> m2767c(Context context) {
        if (!C4813n.m2810a()) {
            return null;
        }
        try {
            Object systemService = context.getSystemService("phone");
            if (systemService == null || !(systemService instanceof TelephonyManager)) {
                return null;
            }
            TelephonyManager telephonyManager = (TelephonyManager) systemService;
            if (C4800a.f4363c >= C4801b.f4375k) {
                return telephonyManager.getAllCellInfo();
            }
            return null;
        } catch (SecurityException e) {
            String str = f4342a;
            return null;
        } catch (Exception e2) {
            C4834w.m2901c(f4342a, e2.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    private static C4827a m2763a(CellInfo cellInfo) {
        if (C4813n.m2816g() && (cellInfo instanceof CellInfoWcdma)) {
            return C4827a.WCDMA;
        }
        if (C4813n.m2817h() && (cellInfo instanceof CellInfoGsm)) {
            return C4827a.GSM;
        }
        if (C4813n.m2818i() && (cellInfo instanceof CellInfoLte)) {
            return C4827a.LTE;
        }
        if (!C4813n.m2819j() || !(cellInfo instanceof CellInfoCdma)) {
            return C4827a.UNKOWN;
        }
        return C4827a.CDMA;
    }

    @TargetApi(18)
    /* renamed from: a */
    private static void m2765a(CellSignalStrength cellSignalStrength, String cellIdentityInfo, Map<String, String> cellTowerInformation) {
        if (cellSignalStrength.getAsuLevel() != 99) {
            cellTowerInformation.put("sl_ASU", String.valueOf(cellSignalStrength.getAsuLevel()));
        }
        cellTowerInformation.put("ss_dBm", String.valueOf(cellSignalStrength.getDbm()));
        cellTowerInformation.put("sl_int", String.valueOf(cellSignalStrength.getLevel()));
        cellTowerInformation.put("cii", cellIdentityInfo);
    }
}
