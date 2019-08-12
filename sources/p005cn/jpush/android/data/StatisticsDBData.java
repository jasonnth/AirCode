package p005cn.jpush.android.data;

/* renamed from: cn.jpush.android.data.StatisticsDBData */
public class StatisticsDBData {
    private int count_1 = 0;
    private int count_10 = 0;
    private int count_1_3 = 0;
    private int count_3_10 = 0;
    private int failed = 0;
    private String st_conn_ip = "";
    private String st_local_dns = "";
    private String st_net = "";
    private String st_sort = "";
    private String st_source = "";
    private int total = 0;

    public String getSt_sort() {
        return this.st_sort;
    }

    public void setSt_sort(String st_sort2) {
        this.st_sort = st_sort2;
    }

    public String getSt_net() {
        return this.st_net;
    }

    public void setSt_net(String st_net2) {
        this.st_net = st_net2;
    }

    public String getSt_conn_ip() {
        return this.st_conn_ip;
    }

    public void setSt_conn_ip(String st_conn_ip2) {
        this.st_conn_ip = st_conn_ip2;
    }

    public String getSt_local_dns() {
        return this.st_local_dns;
    }

    public void setSt_local_dns(String st_local_dns2) {
        this.st_local_dns = st_local_dns2;
    }

    public String getSt_source() {
        return this.st_source;
    }

    public void setSt_source(String st_source2) {
        this.st_source = st_source2;
    }

    public int getFailed() {
        return this.failed;
    }

    public void setFailed(int failed2) {
        this.failed = failed2;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total2) {
        this.total = total2;
    }

    public int getCount_1() {
        return this.count_1;
    }

    public void setCount_1(int count_12) {
        this.count_1 = count_12;
    }

    public int getCount_1_3() {
        return this.count_1_3;
    }

    public void setCount_1_3(int count_1_32) {
        this.count_1_3 = count_1_32;
    }

    public int getCount_3_10() {
        return this.count_3_10;
    }

    public void setCount_3_10(int count_3_102) {
        this.count_3_10 = count_3_102;
    }

    public int getCount_10() {
        return this.count_10;
    }

    public void setCount_10(int count_102) {
        this.count_10 = count_102;
    }

    public String toString() {
        return "StatisticsDBData [st_sort=" + this.st_sort + ", st_net=" + this.st_net + ", st_conn_ip=" + this.st_conn_ip + ", st_local_dns=" + this.st_local_dns + ", st_source=" + this.st_source + ", failed=" + this.failed + ", total=" + this.total + ", count_1=" + this.count_1 + ", count_1_3=" + this.count_1_3 + ", count_3_10=" + this.count_3_10 + ", count_10=" + this.count_10 + "]";
    }
}
