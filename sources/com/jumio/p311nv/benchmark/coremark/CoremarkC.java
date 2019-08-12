package com.jumio.p311nv.benchmark.coremark;

import com.jumio.p311nv.benchmark.BenchmarkAlgorithm;
import com.jumio.p311nv.benchmark.BenchmarkAlgorithm.DeviceCategory;

/* renamed from: com.jumio.nv.benchmark.coremark.CoremarkC */
public class CoremarkC extends BenchmarkAlgorithm {
    public static native double runCoremark();

    /* access modifiers changed from: protected */
    public double run() {
        return runCoremark();
    }

    public String getName() {
        return m1971a();
    }

    public String getUnitName() {
        return "Instr./Sec";
    }

    public DeviceCategory getDeviceCategory() {
        if (getResult() == null) {
            execute();
        }
        if (getResult().doubleValue() > 3000.0d) {
            return DeviceCategory.FAST;
        }
        if (getResult().doubleValue() < 2000.0d) {
            return DeviceCategory.SLOW;
        }
        return DeviceCategory.MEDIUM;
    }

    /* renamed from: a */
    public static String m1971a() {
        return "CoreMark";
    }
}
