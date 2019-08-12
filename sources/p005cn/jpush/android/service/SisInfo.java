package p005cn.jpush.android.service;

import com.google.gson.jpush.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.service.SisInfo */
public class SisInfo {
    private static Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
    private static final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
    private static final String TAG = "SisInfo";
    private static final Gson _gson = new Gson();
    boolean invalidSis = false;
    List<String> ips;
    String mainConnIp;
    int mainConnPort;
    List<String> op_conns;
    List<String> optionConnIp = new ArrayList();
    List<Integer> optionConnPort = new ArrayList();
    String originSis;
    List<String> ssl_ips;
    List<String> ssl_op_conns;
    List<String> udp_report;
    String user;

    /* renamed from: cn.jpush.android.service.SisInfo$Address */
    private static class C0685Address {

        /* renamed from: ip */
        String f966ip;
        int port;

        public C0685Address(String ipPort) throws Exception {
            int colonPos = ipPort.indexOf(58);
            if (colonPos == -1) {
                throw new Exception("Port is needed for a valid address, split by :");
            }
            this.f966ip = ipPort.substring(0, colonPos);
            if (!SisInfo.isValidIPV4(this.f966ip)) {
                throw new Exception("Invalid ip - " + this.f966ip);
            }
            String portString = ipPort.substring(colonPos + 1);
            try {
                this.port = Integer.parseInt(portString);
                if (this.port == 0) {
                    throw new Exception("Invalid port - 0");
                }
            } catch (Exception e) {
                throw new Exception("Invalid port - " + portString);
            }
        }

        public String toString() {
            return this.f966ip + ":" + this.port;
        }
    }

    public static SisInfo fromJson(String json) {
        return (SisInfo) _gson.fromJson(json, SisInfo.class);
    }

    public String getMainConnIp() {
        return this.mainConnIp;
    }

    public int getMainConnPort() {
        return this.mainConnPort;
    }

    public List<String> getOptionConnIp() {
        return this.optionConnIp;
    }

    public List<Integer> getOptionConnPort() {
        return this.optionConnPort;
    }

    public boolean isInvalidSis() {
        return this.invalidSis;
    }

    public String getOriginSis() {
        return this.originSis;
    }

    public void parseAndSet(String originSis2) {
        this.originSis = originSis2;
        if (this.ips == null) {
            Logger.m1422ee(TAG, "Unexpected - Invalid sis - no ips key.");
            this.invalidSis = true;
        } else if (this.ips.size() == 0) {
            Logger.m1422ee(TAG, "Unexpected - invalid sis - ips array len is 0");
            this.invalidSis = true;
        } else {
            try {
                C0685Address mainConn = new C0685Address((String) this.ips.get(0));
                this.mainConnIp = mainConn.f966ip;
                this.mainConnPort = mainConn.port;
                if (this.op_conns == null) {
                    Logger.m1416d(TAG, "No op conn.");
                    return;
                }
                for (String opConn : this.op_conns) {
                    try {
                        C0685Address addr = new C0685Address(opConn);
                        this.optionConnIp.add(addr.f966ip);
                        this.optionConnPort.add(Integer.valueOf(addr.port));
                    } catch (Exception e) {
                        Logger.m1421e(TAG, "Failed to parse op_conn - " + opConn, e);
                    }
                }
            } catch (Exception e2) {
                Logger.m1435ww(TAG, "Failed to parse ips-1 - main ip.", e2);
                this.invalidSis = true;
            }
        }
    }

    public void configure() {
        int ipsLen = this.ips.size();
        if (ipsLen != 0) {
            Configs.setLastGoodSis(this.originSis);
            if (ipsLen > 1) {
                try {
                    C0685Address defaultConn = new C0685Address((String) this.ips.get(1));
                    Configs.setDefaultConnIp(defaultConn.f966ip);
                    Configs.setDefaultConnPort(defaultConn.port);
                } catch (Exception e) {
                    Logger.m1435ww(TAG, "Failed to parse ips-2 - default ip.", e);
                }
            } else {
                Logger.m1434ww(TAG, "Only main ip in sis.");
            }
            if (ipsLen > 2) {
                Configs.setBackupReportAddr((String) this.ips.get(2));
            } else {
                Logger.m1434ww(TAG, "No report backup ip.");
            }
            if (this.user != null) {
                Configs.setBackupUserAddr(this.user);
            }
        }
    }

    public static boolean isValidIPV4(String s) {
        return IPV4_PATTERN.matcher(s).matches();
    }
}
